package association;

public class ManagerInfo {
    private String name;
    private String college;
    private String class_num;

    public ManagerInfo(String name, String college, String class_num) {
        this.name = name;
        this.college = college;
        this.class_num = class_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getClass_num() {
        return class_num;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }
}
