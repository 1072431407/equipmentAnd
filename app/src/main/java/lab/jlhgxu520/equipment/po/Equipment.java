package lab.jlhgxu520.equipment.po;

public class Equipment {
    private String equipment_id;//设备UUID
    private String number;//设备号
    private int state;//硬件连接状态
    private String student_id;//学生UUID
    private String student_name;
    private double core_future;
    private double rotate_future;//转速 255210
    private double exter_future;//外温

    public void setCore_future(double core_future) {
        this.core_future = core_future;
    }

    public void setExter_future(double exter_future) {
        this.exter_future = exter_future;
    }

    public void setRotate_future(double rotate_future) {
        this.rotate_future = rotate_future;
    }

    public double getCore_future() {
        return core_future;
    }

    public double getExter_future() {
        return exter_future;
    }

    public double getRotate_future() {
        return rotate_future;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getNumber() {
        return number;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public int getState() {
        return state;
    }

}

