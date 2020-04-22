package com.linbit.linstor.core.apicallhandler.controller.autoplacer;

import com.linbit.linstor.annotation.SystemContext;
import com.linbit.linstor.api.ApiConsts;
import com.linbit.linstor.core.apicallhandler.controller.autoplacer.AutoplaceStrategy.MinMax;
import com.linbit.linstor.core.apicallhandler.controller.autoplacer.AutoplaceStrategy.RatingAdditionalInfo;
import com.linbit.linstor.core.apicallhandler.controller.autoplacer.Autoplacer.StorPoolWithScore;
import com.linbit.linstor.core.apicallhandler.controller.autoplacer.strategies.FreeSpaceStrategy;
import com.linbit.linstor.core.objects.StorPool;
import com.linbit.linstor.core.repository.SystemConfRepository;
import com.linbit.linstor.logging.ErrorReporter;
import com.linbit.linstor.propscon.Props;
import com.linbit.linstor.security.AccessContext;
import com.linbit.linstor.security.AccessDeniedException;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Singleton
class StrategyHandler
{
    private final List<AutoplaceStrategy> strategies;
    private final SystemConfRepository sysCfgRep;
    private final AccessContext apiCtx;
    private final ErrorReporter errorReporter;

    private final Map<AutoplaceStrategy, Double> dfltWeights;

    @Inject
    StrategyHandler(
        FreeSpaceStrategy freeSpaceStratRef,
        SystemConfRepository sysCfgRepRef,
        @SystemContext AccessContext apiCtxRef,
        ErrorReporter errorReporterRef
    )
    {
        strategies = Arrays.asList(freeSpaceStratRef);
        sysCfgRep = sysCfgRepRef;
        apiCtx = apiCtxRef;
        errorReporter = errorReporterRef;

        dfltWeights = new HashMap<>();
        for (AutoplaceStrategy strat : strategies)
        {
            dfltWeights.put(strat, 0.0);
        }
        dfltWeights.put(freeSpaceStratRef, 1.0);
    }

    public Collection<StorPoolWithScore> rate(
        List<StorPool> storPoolListRef
    )
        throws AccessDeniedException
    {
        RatingAdditionalInfo additionalInfo = new RatingAdditionalInfo();

        Map<AutoplaceStrategy, Double> strategyWeights = getWeights();

        Map<StorPool, StorPoolWithScore> lut = new HashMap<>();
        for (AutoplaceStrategy strat : strategies)
        {
            String stratName = strat.getName();
            double weight = strategyWeights.get(strat);

            Map<StorPool, Double> stratRate = strat.rate(storPoolListRef, additionalInfo);

            if (strat.getMinMax() == MinMax.MINIMIZE)
            {
                for (Entry<StorPool, Double> entry : stratRate.entrySet())
                {
                    entry.setValue(1 / entry.getValue());
                }
            }

            double highestValue = Double.MIN_VALUE;
            for (Double stratValue : stratRate.values())
            {
                if (highestValue < stratValue)
                {
                    highestValue = stratValue;
                }
            }

            for (Entry<StorPool, Double> rate : stratRate.entrySet())
            {
                StorPool sp = rate.getKey();
                StorPoolWithScore prevRating = lut.get(sp);
                double stratValue = rate.getValue();

                if (prevRating == null)
                {
                    prevRating = new StorPoolWithScore(sp, 0);
                    lut.put(sp, prevRating);
                }
                // normalize and weight the value
                double normalizedVal = stratValue / highestValue;
                double normalizdWeightedVal = normalizedVal * weight;
                prevRating.score += normalizdWeightedVal;
                errorReporter.logTrace(
                    "Autoplacer.Strategy: Updated score of StorPool '%s' on Node '%s' to %f (%s: %f, %f, %f)",
                    sp.getName().displayValue,
                    sp.getNode().getName().displayValue,
                    prevRating.score,
                    stratName,
                    stratValue,
                    normalizedVal,
                    normalizdWeightedVal
                );
            }
        }

        return lut.values();
    }

    private Map<AutoplaceStrategy, Double> getWeights() throws AccessDeniedException
    {
        Map<AutoplaceStrategy, Double> weights = new HashMap<>(dfltWeights);

        Props ctrlProps = sysCfgRep.getCtrlConfForView(apiCtx);
        Optional<Props> weightsNsOpt = ctrlProps.getNamespace(ApiConsts.NAMESPC_AUTOPLACER_WEIGHTS);
        if (weightsNsOpt.isPresent())
        {
            Props weightsNs = weightsNsOpt.get();
            for (AutoplaceStrategy strat : strategies)
            {
                String stratName = strat.getName();
                String valStr = weightsNs.getProp(stratName);
                double valDouble;
                if (valStr != null)
                {
                    try
                    {
                        valDouble = Double.parseDouble(valStr);
                        errorReporter.logTrace(
                            "Autoplacer.Strategy: Strategy '%s' with weight: %f",
                            stratName,
                            valDouble
                        );
                    }
                    catch (NumberFormatException nfExc)
                    {
                        errorReporter.reportError(
                            nfExc,
                            null,
                            null,
                            "Could not parse '" + valStr + "' for strategy '" + stratName +
                                "'. Defaulting to 1.0"
                        );
                        valDouble = 1.0;
                    }
                }
                else
                {
                    errorReporter.logTrace(
                        "Autoplacer.Strategy: No weight configured for strategy '%s'. Defaulting to 1.0",
                        stratName
                    );
                    valDouble = 1.0;
                }
                weights.put(strat, valDouble);
            }
        }
        return weights;
    }
}
