package lab.jlhgxu520.equipment.po;

public class AdminTeacherBean {
    private String teacher_id;
    private String number;
    private String class_number;

    private String school;
    private String name;

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setClass_number(String class_number) {
        this.class_number = class_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public String getClass_number() {
        return class_number;
    }

    public String getNumber() {
        return number;
    }

    public String getSchool() {
        return school;
    }
}
