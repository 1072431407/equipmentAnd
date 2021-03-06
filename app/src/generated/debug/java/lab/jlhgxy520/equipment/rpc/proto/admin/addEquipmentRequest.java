// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: admin.proto

package lab.jlhgxy520.equipment.rpc.proto.admin;

/**
 * Protobuf type {@code lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest}
 */
public  final class addEquipmentRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest)
    addEquipmentRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use addEquipmentRequest.newBuilder() to construct.
  private addEquipmentRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private addEquipmentRequest() {
    classId_ = "";
    equipmentNumber_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new addEquipmentRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private addEquipmentRequest(
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            classId_ = s;
            break;
          }
          case 16: {

            time_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            equipmentNumber_ = s;
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
    return lab.jlhgxy520.equipment.rpc.proto.admin.AdminProto.internal_static_lab_jlhgxy520_equipment_rpc_proto_admin_addEquipmentRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return lab.jlhgxy520.equipment.rpc.proto.admin.AdminProto.internal_static_lab_jlhgxy520_equipment_rpc_proto_admin_addEquipmentRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest.class, lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest.Builder.class);
  }

  public static final int CLASS_ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object classId_;
  /**
   * <pre>
   *UUID
   * </pre>
   *
   * <code>string class_id = 1;</code>
   * @return The classId.
   */
  public java.lang.String getClassId() {
    java.lang.Object ref = classId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      classId_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *UUID
   * </pre>
   *
   * <code>string class_id = 1;</code>
   * @return The bytes for classId.
   */
  public com.google.protobuf.ByteString
      getClassIdBytes() {
    java.lang.Object ref = classId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      classId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TIME_FIELD_NUMBER = 2;
  private long time_;
  /**
   * <pre>
   *注册时间
   * </pre>
   *
   * <code>int64 time = 2;</code>
   * @return The time.
   */
  public long getTime() {
    return time_;
  }

  public static final int EQUIPMENT_NUMBER_FIELD_NUMBER = 3;
  private volatile java.lang.Object equipmentNumber_;
  /**
   * <pre>
   *设备ID  用于绑定硬件 教室内部唯一标识
   * </pre>
   *
   * <code>string equipment_number = 3;</code>
   * @return The equipmentNumber.
   */
  public java.lang.String getEquipmentNumber() {
    java.lang.Object ref = equipmentNumber_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      equipmentNumber_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *设备ID  用于绑定硬件 教室内部唯一标识
   * </pre>
   *
   * <code>string equipment_number = 3;</code>
   * @return The bytes for equipmentNumber.
   */
  public com.google.protobuf.ByteString
      getEquipmentNumberBytes() {
    java.lang.Object ref = equipmentNumber_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      equipmentNumber_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getClassIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, classId_);
    }
    if (time_ != 0L) {
      output.writeInt64(2, time_);
    }
    if (!getEquipmentNumberBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, equipmentNumber_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getClassIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, classId_);
    }
    if (time_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, time_);
    }
    if (!getEquipmentNumberBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, equipmentNumber_);
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
    if (!(obj instanceof lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest)) {
      return super.equals(obj);
    }
    lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest other = (lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest) obj;

    if (!getClassId()
        .equals(other.getClassId())) return false;
    if (getTime()
        != other.getTime()) return false;
    if (!getEquipmentNumber()
        .equals(other.getEquipmentNumber())) return false;
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
    hash = (37 * hash) + CLASS_ID_FIELD_NUMBER;
    hash = (53 * hash) + getClassId().hashCode();
    hash = (37 * hash) + TIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTime());
    hash = (37 * hash) + EQUIPMENT_NUMBER_FIELD_NUMBER;
    hash = (53 * hash) + getEquipmentNumber().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parseFrom(
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
  public static Builder newBuilder(lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest prototype) {
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
   * Protobuf type {@code lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest)
      lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return lab.jlhgxy520.equipment.rpc.proto.admin.AdminProto.internal_static_lab_jlhgxy520_equipment_rpc_proto_admin_addEquipmentRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return lab.jlhgxy520.equipment.rpc.proto.admin.AdminProto.internal_static_lab_jlhgxy520_equipment_rpc_proto_admin_addEquipmentRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest.class, lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest.Builder.class);
    }

    // Construct using lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest.newBuilder()
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
      classId_ = "";

      time_ = 0L;

      equipmentNumber_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return lab.jlhgxy520.equipment.rpc.proto.admin.AdminProto.internal_static_lab_jlhgxy520_equipment_rpc_proto_admin_addEquipmentRequest_descriptor;
    }

    @java.lang.Override
    public lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest getDefaultInstanceForType() {
      return lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest.getDefaultInstance();
    }

    @java.lang.Override
    public lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest build() {
      lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest buildPartial() {
      lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest result = new lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest(this);
      result.classId_ = classId_;
      result.time_ = time_;
      result.equipmentNumber_ = equipmentNumber_;
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
      if (other instanceof lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest) {
        return mergeFrom((lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest other) {
      if (other == lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest.getDefaultInstance()) return this;
      if (!other.getClassId().isEmpty()) {
        classId_ = other.classId_;
        onChanged();
      }
      if (other.getTime() != 0L) {
        setTime(other.getTime());
      }
      if (!other.getEquipmentNumber().isEmpty()) {
        equipmentNumber_ = other.equipmentNumber_;
        onChanged();
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
      lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object classId_ = "";
    /**
     * <pre>
     *UUID
     * </pre>
     *
     * <code>string class_id = 1;</code>
     * @return The classId.
     */
    public java.lang.String getClassId() {
      java.lang.Object ref = classId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        classId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *UUID
     * </pre>
     *
     * <code>string class_id = 1;</code>
     * @return The bytes for classId.
     */
    public com.google.protobuf.ByteString
        getClassIdBytes() {
      java.lang.Object ref = classId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        classId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *UUID
     * </pre>
     *
     * <code>string class_id = 1;</code>
     * @param value The classId to set.
     * @return This builder for chaining.
     */
    public Builder setClassId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      classId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *UUID
     * </pre>
     *
     * <code>string class_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearClassId() {
      
      classId_ = getDefaultInstance().getClassId();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *UUID
     * </pre>
     *
     * <code>string class_id = 1;</code>
     * @param value The bytes for classId to set.
     * @return This builder for chaining.
     */
    public Builder setClassIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      classId_ = value;
      onChanged();
      return this;
    }

    private long time_ ;
    /**
     * <pre>
     *注册时间
     * </pre>
     *
     * <code>int64 time = 2;</code>
     * @return The time.
     */
    public long getTime() {
      return time_;
    }
    /**
     * <pre>
     *注册时间
     * </pre>
     *
     * <code>int64 time = 2;</code>
     * @param value The time to set.
     * @return This builder for chaining.
     */
    public Builder setTime(long value) {
      
      time_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *注册时间
     * </pre>
     *
     * <code>int64 time = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTime() {
      
      time_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object equipmentNumber_ = "";
    /**
     * <pre>
     *设备ID  用于绑定硬件 教室内部唯一标识
     * </pre>
     *
     * <code>string equipment_number = 3;</code>
     * @return The equipmentNumber.
     */
    public java.lang.String getEquipmentNumber() {
      java.lang.Object ref = equipmentNumber_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        equipmentNumber_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *设备ID  用于绑定硬件 教室内部唯一标识
     * </pre>
     *
     * <code>string equipment_number = 3;</code>
     * @return The bytes for equipmentNumber.
     */
    public com.google.protobuf.ByteString
        getEquipmentNumberBytes() {
      java.lang.Object ref = equipmentNumber_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        equipmentNumber_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *设备ID  用于绑定硬件 教室内部唯一标识
     * </pre>
     *
     * <code>string equipment_number = 3;</code>
     * @param value The equipmentNumber to set.
     * @return This builder for chaining.
     */
    public Builder setEquipmentNumber(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      equipmentNumber_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *设备ID  用于绑定硬件 教室内部唯一标识
     * </pre>
     *
     * <code>string equipment_number = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearEquipmentNumber() {
      
      equipmentNumber_ = getDefaultInstance().getEquipmentNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *设备ID  用于绑定硬件 教室内部唯一标识
     * </pre>
     *
     * <code>string equipment_number = 3;</code>
     * @param value The bytes for equipmentNumber to set.
     * @return This builder for chaining.
     */
    public Builder setEquipmentNumberBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      equipmentNumber_ = value;
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


    // @@protoc_insertion_point(builder_scope:lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest)
  }

  // @@protoc_insertion_point(class_scope:lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest)
  private static final lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest();
  }

  public static lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<addEquipmentRequest>
      PARSER = new com.google.protobuf.AbstractParser<addEquipmentRequest>() {
    @java.lang.Override
    public addEquipmentRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new addEquipmentRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<addEquipmentRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<addEquipmentRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

