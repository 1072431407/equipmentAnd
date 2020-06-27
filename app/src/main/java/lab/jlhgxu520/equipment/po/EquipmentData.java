package lab.jlhgxu520.equipment.po;
/**
 * 设备的状态参数
 */
public class EquipmentData implements Comparable<EquipmentData>{
    private long time;//系统时间
    private double core_temper;//内温度
    private double exter_temper;//外温度
    private double rotate;//转速
    private String equipment_id;
    private long start_time;

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getStart_time() {
        return start_time;
    }
    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }


    public void setCore_temper(double core_temper) {
        this.core_temper = core_temper;
    }

    public void setExter_temper(double exter_temper) {
        this.exter_temper = exter_temper;
    }

    public double getCore_temper() {
        return core_temper;
    }

    public double getExter_temper() {
        return exter_temper;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public double getRotate() {
        return rotate;
    }

    @Override
    public int compareTo(EquipmentData a) {
        if (a.getTime()-this.time<0)
            return  -1;
        if (a.getTime()-this.time>0)
            return  1;
        return 0; //升序
    }
}
