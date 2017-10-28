package association;

public class Association {
    private String name;
    private String establish_time;
    private ManagerInfo manager_info;
    private ContactWay contact_way;
    private String intro;
    private String members;
    private String activities;
    private int member_num;
    private int activity_num;

    public Association(String name, String establish_time, ManagerInfo manager_info, ContactWay contact_way, String intro, String members, String activities, int mn, int an) {
        this.name = name;
        this.establish_time = establish_time;
        this.manager_info = manager_info;
        this.contact_way = contact_way;
        this.intro = intro;
        this.members = members;
        this.activities = activities;
        this.member_num = mn;
        this.activity_num = an;
    }

    public int getMember_num() {
        return member_num;
    }

    public int getActivity_num() {
        return activity_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEstablish_time() {
        return establish_time;
    }

    public void setEstablish_time(String establish_time) {
        this.establish_time = establish_time;
    }

    public ManagerInfo getManager_info() {
        return manager_info;
    }

    public void setManager_info(ManagerInfo manager_info) {
        this.manager_info = manager_info;
    }

    public ContactWay getContact_way() {
        return contact_way;
    }

    public void setContact_way(ContactWay contact_way) {
        this.contact_way = contact_way;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }
}
