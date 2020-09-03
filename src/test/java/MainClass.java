public class MainClass {
    private String class_string = "Hello, world";
    private int class_number = 20;

    public static int getLocalNumber() {
        int x = 14;
        return x;
    }

    public int getClassNumber() {
        return class_number;
    }

    public String getClassString() {
        return class_string;
    }
}
