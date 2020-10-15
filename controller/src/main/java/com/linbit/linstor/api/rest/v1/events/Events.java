package com.linbit.linstor.api.rest.v1.events;

import com.linbit.linstor.api.rest.v1.RequestHelper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import org.glassfish.grizzly.http.server.Request;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

@Path("v1/events")
public class Events {
    private final RequestHelper requestHelper;
    private final EventHandlerBridge eventHandlerBridge;

    @Inject
    public Events(
        RequestHelper requestHelperRef,
        EventHandlerBridge eventHandlerBridgeRef
    )
    {
        requestHelper = requestHelperRef;
        eventHandlerBridge = eventHandlerBridgeRef;
    }

    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    @Path("drbd/promotion")
    public EventOutput promotionEvents(
        @Context Request request,
        @HeaderParam("Last-Event-ID") String lastEventId) throws IOException
    {
        final EventOutput eventOutput = new EventOutput();
        Response resp = requestHelper.doInScope(requestHelper.createContext("Events-drbd-promotion", request), () ->
        {
            eventHandlerBridge.registerResourceClient(
                eventOutput, lastEventId != null && lastEventId.equals("current"));
            return null;
        }, false);

        if (resp != null)
        {
            eventOutput.write(
                new OutboundEvent.Builder()
                    .name("error")
                    .mediaType(MediaType.APPLICATION_JSON_TYPE)
                    .data(resp.getEntity()).build());
            eventOutput.close();
        }
        return eventOutput;
    }
}
