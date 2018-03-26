// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: linstor/proto/MsgModCryptPassphrase.proto

package com.linbit.linstor.proto;

public final class MsgModCryptPassphraseOuterClass {
  private MsgModCryptPassphraseOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MsgModCryptPassphraseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.linbit.linstor.proto.MsgModCryptPassphrase)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string old_passphrase = 1;</code>
     */
    boolean hasOldPassphrase();
    /**
     * <code>required string old_passphrase = 1;</code>
     */
    java.lang.String getOldPassphrase();
    /**
     * <code>required string old_passphrase = 1;</code>
     */
    com.google.protobuf.ByteString
        getOldPassphraseBytes();

    /**
     * <code>required string new_passphrase = 2;</code>
     */
    boolean hasNewPassphrase();
    /**
     * <code>required string new_passphrase = 2;</code>
     */
    java.lang.String getNewPassphrase();
    /**
     * <code>required string new_passphrase = 2;</code>
     */
    com.google.protobuf.ByteString
        getNewPassphraseBytes();
  }
  /**
   * <pre>
   * linstor - Msg used to change the crypt passphrase
   * </pre>
   *
   * Protobuf type {@code com.linbit.linstor.proto.MsgModCryptPassphrase}
   */
  public  static final class MsgModCryptPassphrase extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.linbit.linstor.proto.MsgModCryptPassphrase)
      MsgModCryptPassphraseOrBuilder {
    // Use MsgModCryptPassphrase.newBuilder() to construct.
    private MsgModCryptPassphrase(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private MsgModCryptPassphrase() {
      oldPassphrase_ = "";
      newPassphrase_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private MsgModCryptPassphrase(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              oldPassphrase_ = bs;
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              newPassphrase_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.internal_static_com_linbit_linstor_proto_MsgModCryptPassphrase_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.internal_static_com_linbit_linstor_proto_MsgModCryptPassphrase_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase.class, com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase.Builder.class);
    }

    private int bitField0_;
    public static final int OLD_PASSPHRASE_FIELD_NUMBER = 1;
    private volatile java.lang.Object oldPassphrase_;
    /**
     * <code>required string old_passphrase = 1;</code>
     */
    public boolean hasOldPassphrase() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string old_passphrase = 1;</code>
     */
    public java.lang.String getOldPassphrase() {
      java.lang.Object ref = oldPassphrase_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          oldPassphrase_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string old_passphrase = 1;</code>
     */
    public com.google.protobuf.ByteString
        getOldPassphraseBytes() {
      java.lang.Object ref = oldPassphrase_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        oldPassphrase_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int NEW_PASSPHRASE_FIELD_NUMBER = 2;
    private volatile java.lang.Object newPassphrase_;
    /**
     * <code>required string new_passphrase = 2;</code>
     */
    public boolean hasNewPassphrase() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string new_passphrase = 2;</code>
     */
    public java.lang.String getNewPassphrase() {
      java.lang.Object ref = newPassphrase_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          newPassphrase_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string new_passphrase = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNewPassphraseBytes() {
      java.lang.Object ref = newPassphrase_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        newPassphrase_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasOldPassphrase()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasNewPassphrase()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, oldPassphrase_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, newPassphrase_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, oldPassphrase_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, newPassphrase_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase)) {
        return super.equals(obj);
      }
      com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase other = (com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase) obj;

      boolean result = true;
      result = result && (hasOldPassphrase() == other.hasOldPassphrase());
      if (hasOldPassphrase()) {
        result = result && getOldPassphrase()
            .equals(other.getOldPassphrase());
      }
      result = result && (hasNewPassphrase() == other.hasNewPassphrase());
      if (hasNewPassphrase()) {
        result = result && getNewPassphrase()
            .equals(other.getNewPassphrase());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasOldPassphrase()) {
        hash = (37 * hash) + OLD_PASSPHRASE_FIELD_NUMBER;
        hash = (53 * hash) + getOldPassphrase().hashCode();
      }
      if (hasNewPassphrase()) {
        hash = (37 * hash) + NEW_PASSPHRASE_FIELD_NUMBER;
        hash = (53 * hash) + getNewPassphrase().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * linstor - Msg used to change the crypt passphrase
     * </pre>
     *
     * Protobuf type {@code com.linbit.linstor.proto.MsgModCryptPassphrase}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.linbit.linstor.proto.MsgModCryptPassphrase)
        com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphraseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.internal_static_com_linbit_linstor_proto_MsgModCryptPassphrase_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.internal_static_com_linbit_linstor_proto_MsgModCryptPassphrase_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase.class, com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase.Builder.class);
      }

      // Construct using com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        oldPassphrase_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        newPassphrase_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.internal_static_com_linbit_linstor_proto_MsgModCryptPassphrase_descriptor;
      }

      public com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase getDefaultInstanceForType() {
        return com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase.getDefaultInstance();
      }

      public com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase build() {
        com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase buildPartial() {
        com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase result = new com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.oldPassphrase_ = oldPassphrase_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.newPassphrase_ = newPassphrase_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase) {
          return mergeFrom((com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase other) {
        if (other == com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase.getDefaultInstance()) return this;
        if (other.hasOldPassphrase()) {
          bitField0_ |= 0x00000001;
          oldPassphrase_ = other.oldPassphrase_;
          onChanged();
        }
        if (other.hasNewPassphrase()) {
          bitField0_ |= 0x00000002;
          newPassphrase_ = other.newPassphrase_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasOldPassphrase()) {
          return false;
        }
        if (!hasNewPassphrase()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object oldPassphrase_ = "";
      /**
       * <code>required string old_passphrase = 1;</code>
       */
      public boolean hasOldPassphrase() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string old_passphrase = 1;</code>
       */
      public java.lang.String getOldPassphrase() {
        java.lang.Object ref = oldPassphrase_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            oldPassphrase_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string old_passphrase = 1;</code>
       */
      public com.google.protobuf.ByteString
          getOldPassphraseBytes() {
        java.lang.Object ref = oldPassphrase_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          oldPassphrase_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string old_passphrase = 1;</code>
       */
      public Builder setOldPassphrase(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        oldPassphrase_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string old_passphrase = 1;</code>
       */
      public Builder clearOldPassphrase() {
        bitField0_ = (bitField0_ & ~0x00000001);
        oldPassphrase_ = getDefaultInstance().getOldPassphrase();
        onChanged();
        return this;
      }
      /**
       * <code>required string old_passphrase = 1;</code>
       */
      public Builder setOldPassphraseBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        oldPassphrase_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object newPassphrase_ = "";
      /**
       * <code>required string new_passphrase = 2;</code>
       */
      public boolean hasNewPassphrase() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string new_passphrase = 2;</code>
       */
      public java.lang.String getNewPassphrase() {
        java.lang.Object ref = newPassphrase_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            newPassphrase_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string new_passphrase = 2;</code>
       */
      public com.google.protobuf.ByteString
          getNewPassphraseBytes() {
        java.lang.Object ref = newPassphrase_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          newPassphrase_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string new_passphrase = 2;</code>
       */
      public Builder setNewPassphrase(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        newPassphrase_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string new_passphrase = 2;</code>
       */
      public Builder clearNewPassphrase() {
        bitField0_ = (bitField0_ & ~0x00000002);
        newPassphrase_ = getDefaultInstance().getNewPassphrase();
        onChanged();
        return this;
      }
      /**
       * <code>required string new_passphrase = 2;</code>
       */
      public Builder setNewPassphraseBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        newPassphrase_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.linbit.linstor.proto.MsgModCryptPassphrase)
    }

    // @@protoc_insertion_point(class_scope:com.linbit.linstor.proto.MsgModCryptPassphrase)
    private static final com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase();
    }

    public static com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<MsgModCryptPassphrase>
        PARSER = new com.google.protobuf.AbstractParser<MsgModCryptPassphrase>() {
      public MsgModCryptPassphrase parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new MsgModCryptPassphrase(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<MsgModCryptPassphrase> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MsgModCryptPassphrase> getParserForType() {
      return PARSER;
    }

    public com.linbit.linstor.proto.MsgModCryptPassphraseOuterClass.MsgModCryptPassphrase getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_linbit_linstor_proto_MsgModCryptPassphrase_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_linbit_linstor_proto_MsgModCryptPassphrase_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n)linstor/proto/MsgModCryptPassphrase.pr" +
      "oto\022\030com.linbit.linstor.proto\"G\n\025MsgModC" +
      "ryptPassphrase\022\026\n\016old_passphrase\030\001 \002(\t\022\026" +
      "\n\016new_passphrase\030\002 \002(\t"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_linbit_linstor_proto_MsgModCryptPassphrase_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_linbit_linstor_proto_MsgModCryptPassphrase_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_linbit_linstor_proto_MsgModCryptPassphrase_descriptor,
        new java.lang.String[] { "OldPassphrase", "NewPassphrase", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
