package association;

import java.text.Collator;
import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;

public class UserScene {
    private List<Activity> activities = new ArrayList<Activity>();
    private List<Association> associations = new ArrayList<>();
    private List<ManagerInfo> managerInfos = new ArrayList<>();
    private List<ContactWay> contactWays = new ArrayList<>();
    private List<String> an = new ArrayList<>();
    private List<String> at = new ArrayList<>();
    private List<String> mn = new ArrayList<>();
    private List<String> mc = new ArrayList<>();
    private List<String> mcn = new ArrayList<>();
    private List<String> cp = new ArrayList<>();
    private List<String> ce = new ArrayList<>();
    private List<String> ai = new ArrayList<>();
    private List<String> am = new ArrayList<>();
    private List<String> aac = new ArrayList<>();
    private List<String> ass = new ArrayList<>();
    private List<String> mi = new ArrayList<>();
    private List<String> ac = new ArrayList<>();
    private List<String> acn = new ArrayList<>();
    private List<String> act = new ArrayList<>();
    private List<String> acl = new ArrayList<>();
    private List<String> aas = new ArrayList<>();
    private List<String> acs = new ArrayList<>();
    private List<String> acr = new ArrayList<>();
    private List<String> amn = new ArrayList<>();
    private List<String> aacn = new ArrayList<>();
    private String[] member_num;
    private String[] activity_num;
    private Scanner reader;

    public UserScene() {
        this.ass = DataRead.CSVtoList("resource\\社团表.csv");//调动静态方法读取源数据
        this.mi = DataRead.CSVtoList("resource\\用户表.csv");
        this.ac = DataRead.CSVtoList("resource\\活动表.csv");
        for (int i = 1; i < 11; i++) {
            this.an.add(ass.get(8 * i + 1));
            this.at.add(ass.get(8 * i + 2));
            this.mn.add(mi.get(3 * i + 1));
            this.mc.add(mi.get(3 * i + 2));
            this.mcn.add(mi.get(3 * i + 3));
            this.cp.add(ass.get(8 * i + 4));
            this.ce.add(ass.get(8 * i + 5));
            this.am.add(ass.get(8 * i + 6));
            this.aac.add(ass.get(8 * i + 7));
            this.ai.add(ass.get(8 * i + 8));
            this.member_num = am.get(i - 1).split("、");
            this.activity_num = aac.get(i - 1).split("、");
            this.managerInfos.add(new ManagerInfo(mn.get(i - 1), mc.get(i - 1), mcn.get(i - 1)));
            this.contactWays.add(new ContactWay(cp.get(i - 1), ce.get(i - 1)));
            this.associations.add(new Association(an.get(i - 1), at.get(i - 1), managerInfos.get(i - 1), contactWays.get(i - 1), ai.get(i - 1), am.get(i - 1),
                    aac.get(i - 1), member_num.length, activity_num.length));//将源数据通过循环给每个社团赋值
        }
        for (int j = 1; j < 13; j++) {
            this.acn.add(ac.get(6 * j + 1));
            this.act.add(ac.get(6 * j + 2));
            this.acl.add(ac.get(6 * j + 3));
            this.aas.add(ac.get(6 * j + 4));
            this.acs.add(ac.get(6 * j + 5));
            this.acr.add(ac.get(6 * j + 6));
            this.activities.add(new Activity(acn.get(j - 1), act.get(j - 1), acl.get(j - 1), aas.get(j - 1), acs.get(j - 1), acr.get(j - 1)));
        }//将源数据通过循环给每个活动赋值
        this.reader = new Scanner(System.in);
    }

    public void Manager() {
        ShowAll();
    }

    public void ShowAll() {
        ImageShow();
        System.out.println("请选择数字查看感兴趣的社团或活动");
        System.out.println("请按下0进行搜索");
        System.out.println("\n社团列表如下：");
        for (int i = 0; i < associations.size(); i++) {
            System.out.println((i + 1) + "-" + associations.get(i).getName());
        }
        System.out.println("\n活动列表如下：");
        for (int i = 0; i < activities.size(); i++) {
            System.out.println((i + 1 + associations.size()) + "-" + activities.get(i).getName());
        }
        switch (reader.nextInt()) {
            case 0: {
                FindAll();
                break;
            }
            case 1: {
                System.out.println(associations.get(0).getName() + "的详情如下：");
                System.out.println("社团名: " + associations.get(0).getName() + "\n创建时间: " + associations.get(0).getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + associations.get(0).getManager_info().getName() + "\n院系: " + associations.get(0).getManager_info().getCollege() +
                        "\n班级: " + associations.get(0).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(0).getContact_way().getPhone_num() +
                        "\n邮箱: " + associations.get(0).getContact_way().getEmail() + "\n简介: " + associations.get(0).getIntro() + "\n举办的活动:");
                String[] acts = associations.get(0).getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关活动");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                    case 2:
                        FindbyName(acts[1]);
                        break;
                }
                break;
            }
            case 2: {
                System.out.println(associations.get(1).getName() + "的详情如下：");
                System.out.println("社团名: " + associations.get(1).getName() + "\n创建时间: " + associations.get(1).getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + associations.get(1).getManager_info().getName() + "\n院系: " + associations.get(1).getManager_info().getCollege() +
                        "\n班级: " + associations.get(1).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(1).getContact_way().getPhone_num() +
                        "\n邮箱: " + associations.get(1).getContact_way().getEmail() + "\n简介: " + associations.get(1).getIntro() + "\n举办的活动:");
                String[] acts = associations.get(1).getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关活动");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 3: {
                System.out.println(associations.get(2).getName() + "的详情如下：");
                System.out.println("社团名: " + associations.get(2).getName() + "\n创建时间: " + associations.get(2).getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + associations.get(2).getManager_info().getName() + "\n院系: " + associations.get(2).getManager_info().getCollege() +
                        "\n班级: " + associations.get(2).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(2).getContact_way().getPhone_num() +
                        "\n邮箱: " + associations.get(2).getContact_way().getEmail() + "\n简介: " + associations.get(2).getIntro() + "\n举办的活动:");
                String[] acts = associations.get(2).getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关活动");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                    case 2:
                        FindbyName(acts[1]);
                        break;
                }
                break;
            }
            case 4: {
                System.out.println(associations.get(3).getName() + "的详情如下：");
                System.out.println("社团名: " + associations.get(3).getName() + "\n创建时间: " + associations.get(3).getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + associations.get(3).getManager_info().getName() + "\n院系: " + associations.get(3).getManager_info().getCollege() +
                        "\n班级: " + associations.get(3).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(3).getContact_way().getPhone_num() +
                        "\n邮箱: " + associations.get(3).getContact_way().getEmail() + "\n简介: " + associations.get(3).getIntro() + "\n举办的活动:");
                String[] acts = associations.get(3).getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);

                }
                System.out.println("请选择数字查看相关活动");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 5: {
                System.out.println(associations.get(4).getName() + "的详情如下：");
                System.out.println("社团名: " + associations.get(4).getName() + "\n创建时间: " + associations.get(4).getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + associations.get(4).getManager_info().getName() + "\n院系: " + associations.get(4).getManager_info().getCollege() +
                        "\n班级: " + associations.get(4).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(4).getContact_way().getPhone_num() +
                        "\n邮箱: " + associations.get(4).getContact_way().getEmail() + "\n简介: " + associations.get(4).getIntro() + "\n举办的活动:");
                String[] acts = associations.get(4).getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);

                }
                System.out.println("请选择数字查看相关活动");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 6: {
                System.out.println(associations.get(5).getName() + "的详情如下：");
                System.out.println("社团名: " + associations.get(5).getName() + "\n创建时间: " + associations.get(5).getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + associations.get(5).getManager_info().getName() + "\n院系: " + associations.get(5).getManager_info().getCollege() +
                        "\n班级: " + associations.get(5).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(5).getContact_way().getPhone_num() +
                        "\n邮箱: " + associations.get(5).getContact_way().getEmail() + "\n简介: " + associations.get(5).getIntro() + "\n举办的活动:");
                String[] acts = associations.get(5).getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);

                }
                System.out.println("请选择数字查看相关活动");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 7: {
                System.out.println(associations.get(6).getName() + "的详情如下：");
                System.out.println("社团名: " + associations.get(6).getName() + "\n创建时间: " + associations.get(6).getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + associations.get(6).getManager_info().getName() + "\n院系: " + associations.get(6).getManager_info().getCollege() +
                        "\n班级: " + associations.get(6).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(6).getContact_way().getPhone_num() +
                        "\n邮箱: " + associations.get(6).getContact_way().getEmail() + "\n简介: " + associations.get(6).getIntro() + "\n举办的活动:");
                String[] acts = associations.get(6).getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);

                }
                System.out.println("请选择数字查看相关活动");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 8: {
                System.out.println(associations.get(7).getName() + "的详情如下：");
                System.out.println("社团名: " + associations.get(7).getName() + "\n创建时间: " + associations.get(7).getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + associations.get(7).getManager_info().getName() + "\n院系: " + associations.get(7).getManager_info().getCollege() +
                        "\n班级: " + associations.get(7).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(7).getContact_way().getPhone_num() +
                        "\n邮箱: " + associations.get(7).getContact_way().getEmail() + "\n简介: " + associations.get(7).getIntro() + "\n举办的活动:");
                String[] acts = associations.get(7).getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);

                }
                System.out.println("请选择数字查看相关活动");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 9: {
                System.out.println(associations.get(8).getName() + "的详情如下：");
                System.out.println("社团名: " + associations.get(8).getName() + "\n创建时间: " + associations.get(8).getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + associations.get(8).getManager_info().getName() + "\n院系: " + associations.get(8).getManager_info().getCollege() +
                        "\n班级: " + associations.get(8).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(8).getContact_way().getPhone_num() +
                        "\n邮箱: " + associations.get(8).getContact_way().getEmail() + "\n简介: " + associations.get(8).getIntro() + "\n举办的活动:");
                String[] acts = associations.get(8).getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);

                }
                System.out.println("请选择数字查看相关活动");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                    case 2:
                        FindbyName(acts[1]);
                        break;
                }
                break;
            }
            case 10: {
                System.out.println(associations.get(9).getName() + "的详情如下：");
                System.out.println("社团名: " + associations.get(9).getName() + "\n创建时间: " + associations.get(9).getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + associations.get(9).getManager_info().getName() + "\n院系: " + associations.get(9).getManager_info().getCollege() +
                        "\n班级: " + associations.get(9).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(9).getContact_way().getPhone_num() +
                        "\n邮箱: " + associations.get(9).getContact_way().getEmail() + "\n简介: " + associations.get(9).getIntro() + "\n举办的活动:");
                String[] acts = associations.get(9).getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);

                }
                System.out.println("请选择数字查看相关活动");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                    case 2:
                        FindbyName(acts[1]);
                        break;
                }
                break;
            }
            case 11: {
                System.out.println(activities.get(0).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(0).getName() + "\n开始时间: " + activities.get(0).getHold_time() +
                        "\n地点: " + activities.get(0).getLocation() +
                        "\n简介: " + activities.get(0).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(0).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                    case 2:
                        FindbyName(acts[1]);
                        break;
                }
                break;
            }
            case 12: {
                System.out.println(activities.get(1).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(1).getName() + "\n开始时间: " + activities.get(1).getHold_time() +
                        "\n地点: " + activities.get(1).getLocation() +
                        "\n简介: " + activities.get(1).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(1).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 13: {
                System.out.println(activities.get(2).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(2).getName() + "\n开始时间: " + activities.get(2).getHold_time() +
                        "\n地点: " + activities.get(2).getLocation() +
                        "\n简介: " + activities.get(2).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(2).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 14: {
                System.out.println(activities.get(3).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(3).getName() + "\n开始时间: " + activities.get(3).getHold_time() +
                        "\n地点: " + activities.get(3).getLocation() +
                        "\n简介: " + activities.get(3).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(3).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 15: {
                System.out.println(activities.get(4).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(4).getName() + "\n开始时间: " + activities.get(4).getHold_time() +
                        "\n地点: " + activities.get(4).getLocation() +
                        "\n简介: " + activities.get(4).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(4).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 16: {
                System.out.println(activities.get(5).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(5).getName() + "\n开始时间: " + activities.get(5).getHold_time() +
                        "\n地点: " + activities.get(5).getLocation() +
                        "\n简介: " + activities.get(5).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(5).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 17: {
                System.out.println(activities.get(6).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(6).getName() + "\n开始时间: " + activities.get(6).getHold_time() +
                        "\n地点: " + activities.get(6).getLocation() +
                        "\n简介: " + activities.get(6).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(6).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 18: {
                System.out.println(activities.get(7).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(7).getName() + "\n开始时间: " + activities.get(7).getHold_time() +
                        "\n地点: " + activities.get(7).getLocation() +
                        "\n简介: " + activities.get(7).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(7).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 19: {
                System.out.println(activities.get(8).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(8).getName() + "\n开始时间: " + activities.get(8).getHold_time() +
                        "\n地点: " + activities.get(8).getLocation() +
                        "\n简介: " + activities.get(8).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(8).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                    case 2:
                        FindbyName(acts[1]);
                        break;
                }
                break;
            }
            case 20: {
                System.out.println(activities.get(9).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(9).getName() + "\n开始时间: " + activities.get(9).getHold_time() +
                        "\n地点: " + activities.get(9).getLocation() +
                        "\n简介: " + activities.get(9).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(9).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 21: {
                System.out.println(activities.get(10).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(10).getName() + "\n开始时间: " + activities.get(10).getHold_time() +
                        "\n地点: " + activities.get(10).getLocation() +
                        "\n简介: " + activities.get(10).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(10).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
            case 22: {
                System.out.println(activities.get(11).getName() + "的详情如下：");
                System.out.println("活动名称: " + activities.get(11).getName() + "\n开始时间: " + activities.get(11).getHold_time() +
                        "\n地点: " + activities.get(11).getLocation() +
                        "\n简介: " + activities.get(11).getSlogan() + "\n主办社团:");
                String[] acts = activities.get(11).getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
                System.out.println("请选择数字查看相关社团");
                switch (reader.nextInt()) {
                    case 1:
                        FindbyName(acts[0]);
                        break;
                }
                break;
            }
        }
    }//展示详情

    public void FindAll() {
        System.out.println("请选择搜索方式:");
        System.out.println("1-按社团名或活动名搜索");
        System.out.println("2-按时间对活动进行筛选");
        System.out.println("3-按社团对活动进行筛选");
        switch (reader.nextInt()) {
            case 1: {
                System.out.println("请输入社团名或活动名:");
                FindbyName(reader.next());
                break;
            }
            case 2: {
                FindActivitybyTime();
                break;
            }
            case 3: {
                System.out.println("请输入社团名:");
                FindActivitybyAssociation(reader.next());
                break;
            }
        }
    }

    public void FindbyName(String name) {
        int count = 0;
        for (Association a : associations) {
            if (a.getName().equals(name)) {
                System.out.println("找到相关社团");
                System.out.println("社团名: " + a.getName() + "\n创建时间: " + a.getEstablish_time() +
                        "\n负责人:" + "\n姓名: " + a.getManager_info().getName() + "\n院系: " + a.getManager_info().getCollege() +
                        "\n班级: " + a.getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + a.getContact_way().getPhone_num() +
                        "\n邮箱: " + a.getContact_way().getEmail() + "\n简介: " + a.getIntro() + "\n举办的活动:");
                String[] acts = a.getActivities().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
            } else count++;
        }
        for (Activity a : activities) {
            if (a.getName().equals(name)) {
                System.out.println("找到相关活动");
                System.out.println("活动名称: " + a.getName() + "\n开始时间: " + a.getHold_time() +
                        "\n地点: " + a.getLocation() +
                        "\n简介: " + a.getSlogan() + "\n主办社团:");
                String[] acts = a.getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
            } else count++;
        }
        if (count == activities.size() + associations.size()) {
            System.out.println("没有找到相关内容，请重新输入");
            FindbyName(reader.next());
        }
    }

    public void FindActivitybyTime() {
        System.out.println("请输入查询日期（格式：年.月.日）");
        String date = reader.next();
        System.out.println("请输入选择查询方式");
        System.out.println("1-查询之前的活动");
        System.out.println("2-查询之后的活动");
        switch (reader.nextInt()) {
            case 1:
                FindActicitybyTimeBefore(date);
                break;
            case 2:
                FindActicitybyTimeAfter(date);
                break;
        }
    }

    public void FindActicitybyTimeBefore(String date) {
        System.out.println("");
        int count = 0;
        for (Activity a : activities) {
            if (compare_date(date, a.getHold_time()) == 1) {
                System.out.println("\n找到相关活动");
                System.out.println("活动名称: " + a.getName() + "\n开始时间: " + a.getHold_time() +
                        "\n地点: " + a.getLocation() +
                        "\n简介: " + a.getSlogan() + "\n主办社团:");
                String[] acts = a.getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
            } else count++;
        }
        if (count == activities.size()) {
            System.out.println("没有找到相关内容，请重新输入");
            FindActicitybyTimeBefore(reader.next());
        }
    }

    public void FindActicitybyTimeAfter(String date) {
        System.out.println("");
        int count = 0;
        for (Activity a : activities) {
            if (compare_date(date, a.getHold_time()) == -1) {
                System.out.println("\n找到相关活动");
                System.out.println("活动名称: " + a.getName() + "\n开始时间: " + a.getHold_time() +
                        "\n地点: " + a.getLocation() +
                        "\n简介: " + a.getSlogan() + "\n主办社团:");
                String[] acts = a.getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
            } else count++;
        }
        if (count == activities.size()) {
            System.out.println("没有找到相关内容，请重新输入");
            FindActicitybyTimeAfter(reader.next());
        }
    }

    public void FindActivitybyAssociation(String asname) {
        int count = 0;
        for (Activity a : activities) {
            if (a.getAssociations().indexOf(asname) != -1) {
                System.out.println("\n找到相关活动");
                System.out.println("活动名称: " + a.getName() + "\n开始时间: " + a.getHold_time() +
                        "\n地点: " + a.getLocation() +
                        "\n简介: " + a.getSlogan() + "\n主办社团:");
                String[] acts = a.getAssociations().split("、");
                for (int i = 0; i < acts.length; i++) {
                    System.out.println(i + 1 + "-" + acts[i]);
                }
            } else count++;
        }
        if (count == activities.size()) {
            System.out.println("没有找到相关内容，请重新输入");
            FindActivitybyAssociation(reader.next());
        }
    }

    public void ImageShow() {
        int delay = 1000;
        Timer start_timer = new Timer();
        Comparator sort= Collator.getInstance(java.util.Locale.CHINA);
        Collections.sort(an,sort);
        TimerTask start_task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n进入展示模式");
                for(int i=0;i<associations.size();i++){
                    int num1 = new Random().nextInt(10) + 1;
                    int num2 = new Random().nextInt(10) + 1;
                    System.out.println("展示"+an.get(i)+"的第"+num1+"张图片");
                    System.out.println("展示"+an.get(i)+"的第"+num2+"张图片");
                    try{
                        Thread.sleep(5*delay);//delay=1s
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        start_timer.schedule(start_task,60*delay);
    }

    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");//静态方法传参并比较日期
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}

