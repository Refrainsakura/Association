package association;


public class Activity {
    private String name;
    private String hold_time;
    private String location;
    private String associations;
    private String slogan;
    private String remark;

    public Activity(String name, String hold_time, String location, String associations, String slogan, String remark) {
        this.name = name;
        this.hold_time = hold_time;
        this.location = location;
        this.associations = associations;
        this.slogan = slogan;
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHold_time() {
        return hold_time;
    }

    public void setHold_time(String hold_time) {
        this.hold_time = hold_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAssociations() {
        return associations;
    }

    public void setAssociations(String associations) {
        this.associations = associations;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
