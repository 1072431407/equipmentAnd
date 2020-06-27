package lab.jlhgxu520.equipment.po;

public class AdminClassRoomBean {
    private String class_id;
    private String school;
    private String class_number;//教室号
    private String equipment_number;

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setEquipment_number(String equipment_number) {
        this.equipment_number = equipment_number;
    }

    public String getEquipment_number() {
        return equipment_number;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setClass_number(String class_number) {
        this.class_number = class_number;
    }

    public String getSchool() {
        return school;
    }

    public String getClass_number() {
        return class_number;
    }

}
