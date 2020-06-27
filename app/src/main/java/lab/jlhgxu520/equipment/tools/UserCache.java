package lab.jlhgxu520.equipment.tools;

/**
 * 只为了方便
 */
public class UserCache {
    public static String UUID;
    public static int power;
    public static String school;
    public static String name;//张三
    public static String number;//学号

    public static void clear() {
        UUID = null;
        power = 0;
        name = null;
        number = null;
        school = null;
    }
}
