// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: admin.proto

package lab.jlhgxy520.equipment.rpc.proto.admin;

public interface addClassResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:lab.jlhgxy520.equipment.rpc.proto.admin.addClassResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *状态
   * </pre>
   *
   * <code>.lab.jlhgxy520.equipment.rpc.proto.admin.addClassResponse.State state = 1;</code>
   * @return The enum numeric value on the wire for state.
   */
  int getStateValue();
  /**
   * <pre>
   *状态
   * </pre>
   *
   * <code>.lab.jlhgxy520.equipment.rpc.proto.admin.addClassResponse.State state = 1;</code>
   * @return The state.
   */
  lab.jlhgxy520.equipment.rpc.proto.admin.addClassResponse.State getState();

  /**
   * <pre>
   *教室ID
   * </pre>
   *
   * <code>string class_id = 2;</code>
   * @return The classId.
   */
  java.lang.String getClassId();
  /**
   * <pre>
   *教室ID
   * </pre>
   *
   * <code>string class_id = 2;</code>
   * @return The bytes for classId.
   */
  com.google.protobuf.ByteString
      getClassIdBytes();

  /**
   * <code>string school = 3;</code>
   * @return The school.
   */
  java.lang.String getSchool();
  /**
   * <code>string school = 3;</code>
   * @return The bytes for school.
   */
  com.google.protobuf.ByteString
      getSchoolBytes();

  /**
   * <pre>
   *教室号 520
   * </pre>
   *
   * <code>string class_number = 4;</code>
   * @return The classNumber.
   */
  java.lang.String getClassNumber();
  /**
   * <pre>
   *教室号 520
   * </pre>
   *
   * <code>string class_number = 4;</code>
   * @return The bytes for classNumber.
   */
  com.google.protobuf.ByteString
      getClassNumberBytes();

  /**
   * <pre>
   *设备数量
   * </pre>
   *
   * <code>string equipment_number = 5;</code>
   * @return The equipmentNumber.
   */
  java.lang.String getEquipmentNumber();
  /**
   * <pre>
   *设备数量
   * </pre>
   *
   * <code>string equipment_number = 5;</code>
   * @return The bytes for equipmentNumber.
   */
  com.google.protobuf.ByteString
      getEquipmentNumberBytes();
}
