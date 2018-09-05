import java.io.Serializable;

public class Student implements Serializable {
    private long timeHash;
    private String name;

    public Student(long timeHash, String name) {
        this.timeHash = timeHash;
        this.name = name;
    }

    public String info() {
        return (timeHash + " " + name);
    }
}
