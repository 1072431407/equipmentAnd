// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: student.proto

package lab.jlhgxy520.equipment.rpc.proto.student;

/**
 * Protobuf type {@code lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse}
 */
public  final class EquipmentStateResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse)
    EquipmentStateResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use EquipmentStateResponse.newBuilder() to construct.
  private EquipmentStateResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EquipmentStateResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new EquipmentStateResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private EquipmentStateResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
          case 8: {

            equState_ = input.readInt32();
            break;
          }
          case 17: {

            rotateFuture_ = input.readDouble();
            break;
          }
          case 25: {

            exterFuture_ = input.readDouble();
            break;
          }
          case 33: {

            coreFuture_ = input.readDouble();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
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
    return lab.jlhgxy520.equipment.rpc.proto.student.StudentProto.internal_static_lab_jlhgxy520_equipment_rpc_proto_student_EquipmentStateResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return lab.jlhgxy520.equipment.rpc.proto.student.StudentProto.internal_static_lab_jlhgxy520_equipment_rpc_proto_student_EquipmentStateResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse.class, lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse.Builder.class);
  }

  public static final int EQU_STATE_FIELD_NUMBER = 1;
  private int equState_;
  /**
   * <pre>
   *0标识启动  1标识停止
   * </pre>
   *
   * <code>int32 equ_state = 1;</code>
   * @return The equState.
   */
  public int getEquState() {
    return equState_;
  }

  public static final int ROTATE_FUTURE_FIELD_NUMBER = 2;
  private double rotateFuture_;
  /**
   * <pre>
   *将要达到的转速
   * </pre>
   *
   * <code>double rotate_future = 2;</code>
   * @return The rotateFuture.
   */
  public double getRotateFuture() {
    return rotateFuture_;
  }

  public static final int EXTER_FUTURE_FIELD_NUMBER = 3;
  private double exterFuture_;
  /**
   * <pre>
   *将要达到的外温
   * </pre>
   *
   * <code>double exter_future = 3;</code>
   * @return The exterFuture.
   */
  public double getExterFuture() {
    return exterFuture_;
  }

  public static final int CORE_FUTURE_FIELD_NUMBER = 4;
  private double coreFuture_;
  /**
   * <pre>
   *将要达到的内温
   * </pre>
   *
   * <code>double core_future = 4;</code>
   * @return The coreFuture.
   */
  public double getCoreFuture() {
    return coreFuture_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (equState_ != 0) {
      output.writeInt32(1, equState_);
    }
    if (rotateFuture_ != 0D) {
      output.writeDouble(2, rotateFuture_);
    }
    if (exterFuture_ != 0D) {
      output.writeDouble(3, exterFuture_);
    }
    if (coreFuture_ != 0D) {
      output.writeDouble(4, coreFuture_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (equState_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, equState_);
    }
    if (rotateFuture_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, rotateFuture_);
    }
    if (exterFuture_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, exterFuture_);
    }
    if (coreFuture_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(4, coreFuture_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse)) {
      return super.equals(obj);
    }
    lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse other = (lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse) obj;

    if (getEquState()
        != other.getEquState()) return false;
    if (java.lang.Double.doubleToLongBits(getRotateFuture())
        != java.lang.Double.doubleToLongBits(
            other.getRotateFuture())) return false;
    if (java.lang.Double.doubleToLongBits(getExterFuture())
        != java.lang.Double.doubleToLongBits(
            other.getExterFuture())) return false;
    if (java.lang.Double.doubleToLongBits(getCoreFuture())
        != java.lang.Double.doubleToLongBits(
            other.getCoreFuture())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + EQU_STATE_FIELD_NUMBER;
    hash = (53 * hash) + getEquState();
    hash = (37 * hash) + ROTATE_FUTURE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getRotateFuture()));
    hash = (37 * hash) + EXTER_FUTURE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getExterFuture()));
    hash = (37 * hash) + CORE_FUTURE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getCoreFuture()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   * Protobuf type {@code lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse)
      lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return lab.jlhgxy520.equipment.rpc.proto.student.StudentProto.internal_static_lab_jlhgxy520_equipment_rpc_proto_student_EquipmentStateResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return lab.jlhgxy520.equipment.rpc.proto.student.StudentProto.internal_static_lab_jlhgxy520_equipment_rpc_proto_student_EquipmentStateResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse.class, lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse.Builder.class);
    }

    // Construct using lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse.newBuilder()
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
    @java.lang.Override
    public Builder clear() {
      super.clear();
      equState_ = 0;

      rotateFuture_ = 0D;

      exterFuture_ = 0D;

      coreFuture_ = 0D;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return lab.jlhgxy520.equipment.rpc.proto.student.StudentProto.internal_static_lab_jlhgxy520_equipment_rpc_proto_student_EquipmentStateResponse_descriptor;
    }

    @java.lang.Override
    public lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse getDefaultInstanceForType() {
      return lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse.getDefaultInstance();
    }

    @java.lang.Override
    public lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse build() {
      lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse buildPartial() {
      lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse result = new lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse(this);
      result.equState_ = equState_;
      result.rotateFuture_ = rotateFuture_;
      result.exterFuture_ = exterFuture_;
      result.coreFuture_ = coreFuture_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse) {
        return mergeFrom((lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse other) {
      if (other == lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse.getDefaultInstance()) return this;
      if (other.getEquState() != 0) {
        setEquState(other.getEquState());
      }
      if (other.getRotateFuture() != 0D) {
        setRotateFuture(other.getRotateFuture());
      }
      if (other.getExterFuture() != 0D) {
        setExterFuture(other.getExterFuture());
      }
      if (other.getCoreFuture() != 0D) {
        setCoreFuture(other.getCoreFuture());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int equState_ ;
    /**
     * <pre>
     *0标识启动  1标识停止
     * </pre>
     *
     * <code>int32 equ_state = 1;</code>
     * @return The equState.
     */
    public int getEquState() {
      return equState_;
    }
    /**
     * <pre>
     *0标识启动  1标识停止
     * </pre>
     *
     * <code>int32 equ_state = 1;</code>
     * @param value The equState to set.
     * @return This builder for chaining.
     */
    public Builder setEquState(int value) {
      
      equState_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *0标识启动  1标识停止
     * </pre>
     *
     * <code>int32 equ_state = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearEquState() {
      
      equState_ = 0;
      onChanged();
      return this;
    }

    private double rotateFuture_ ;
    /**
     * <pre>
     *将要达到的转速
     * </pre>
     *
     * <code>double rotate_future = 2;</code>
     * @return The rotateFuture.
     */
    public double getRotateFuture() {
      return rotateFuture_;
    }
    /**
     * <pre>
     *将要达到的转速
     * </pre>
     *
     * <code>double rotate_future = 2;</code>
     * @param value The rotateFuture to set.
     * @return This builder for chaining.
     */
    public Builder setRotateFuture(double value) {
      
      rotateFuture_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *将要达到的转速
     * </pre>
     *
     * <code>double rotate_future = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearRotateFuture() {
      
      rotateFuture_ = 0D;
      onChanged();
      return this;
    }

    private double exterFuture_ ;
    /**
     * <pre>
     *将要达到的外温
     * </pre>
     *
     * <code>double exter_future = 3;</code>
     * @return The exterFuture.
     */
    public double getExterFuture() {
      return exterFuture_;
    }
    /**
     * <pre>
     *将要达到的外温
     * </pre>
     *
     * <code>double exter_future = 3;</code>
     * @param value The exterFuture to set.
     * @return This builder for chaining.
     */
    public Builder setExterFuture(double value) {
      
      exterFuture_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *将要达到的外温
     * </pre>
     *
     * <code>double exter_future = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearExterFuture() {
      
      exterFuture_ = 0D;
      onChanged();
      return this;
    }

    private double coreFuture_ ;
    /**
     * <pre>
     *将要达到的内温
     * </pre>
     *
     * <code>double core_future = 4;</code>
     * @return The coreFuture.
     */
    public double getCoreFuture() {
      return coreFuture_;
    }
    /**
     * <pre>
     *将要达到的内温
     * </pre>
     *
     * <code>double core_future = 4;</code>
     * @param value The coreFuture to set.
     * @return This builder for chaining.
     */
    public Builder setCoreFuture(double value) {
      
      coreFuture_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *将要达到的内温
     * </pre>
     *
     * <code>double core_future = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearCoreFuture() {
      
      coreFuture_ = 0D;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse)
  }

  // @@protoc_insertion_point(class_scope:lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse)
  private static final lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse();
  }

  public static lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EquipmentStateResponse>
      PARSER = new com.google.protobuf.AbstractParser<EquipmentStateResponse>() {
    @java.lang.Override
    public EquipmentStateResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new EquipmentStateResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EquipmentStateResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EquipmentStateResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

