package lab.jlhgxu520.equipment.po;

public class AdminEquipmentBean {
    private String equipment_id;//设备ID
    private String equipment_number;//设备号
    private long register_time;
    private String class_id;

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setRegister_time(long register_time) {
        this.register_time = register_time;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public void setEquipment_number(String equipment_number) {
        this.equipment_number = equipment_number;
    }

    public long getRegister_time() {
        return register_time;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public String getEquipment_number() {
        return equipment_number;
    }
}
