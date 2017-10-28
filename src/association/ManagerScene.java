package association;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;


public class ManagerScene {
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

    public ManagerScene() {
        this.ass = DataRead.CSVtoList("resource\\社团表.csv");
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
            this.associations.add(new Association(an.get(i - 1), at.get(i - 1), managerInfos.get(i - 1), contactWays.get(i - 1), ai.get(i - 1), am.get(i - 1), aac.get(i - 1), member_num.length, activity_num.length));
        }
        for (int j = 1; j < 13; j++) {
            this.acn.add(ac.get(6 * j + 1));
            this.act.add(ac.get(6 * j + 2));
            this.acl.add(ac.get(6 * j + 3));
            this.aas.add(ac.get(6 * j + 4));
            this.acs.add(ac.get(6 * j + 5));
            this.acr.add(ac.get(6 * j + 6));
            this.activities.add(new Activity(acn.get(j - 1), act.get(j - 1), acl.get(j - 1), aas.get(j - 1), acs.get(j - 1), acr.get(j - 1)));
        }
        this.reader = new Scanner(System.in);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void Manager() {
        System.out.println("请选择你想使用的功能");
        System.out.println("1-查看每个社团的各个成员列表");
        System.out.println("2-设置规则并对社团排序");
        System.out.println("3-进行活动信息汇总");

        switch (reader.nextInt()) {
            case 1:
                MemberCheck();
                break;
            case 2:
                AssociationSort();
                break;
            case 3:
                InfoGather();
                break;
            default: {
                System.out.println("请重新输入");
                Manager();
                break;
            }
        }
    }

    public void MemberCheck() {
        System.out.println("请选择你想查看的社团");
        for (int i = 0; i < associations.size(); i++) {
            System.out.println(i + 1 + "-" + associations.get(i).getName());
        }

        switch (reader.nextInt()) {
            case 1:
                System.out.println(associations.get(0).getName() + "的成员有：\n" + associations.get(0).getMembers());
                break;
            case 2:
                System.out.println(associations.get(1).getName() + "的成员有：\n" + associations.get(1).getMembers());
                break;
            case 3:
                System.out.println(associations.get(2).getName() + "的成员有：\n" + associations.get(2).getMembers());
                break;
            case 4:
                System.out.println(associations.get(3).getName() + "的成员有：\n" + associations.get(3).getMembers());
                break;
            case 5:
                System.out.println(associations.get(4).getName() + "的成员有：\n" + associations.get(4).getMembers());
                break;
            case 6:
                System.out.println(associations.get(5).getName() + "的成员有：\n" + associations.get(5).getMembers());
                break;
            case 7:
                System.out.println(associations.get(6).getName() + "的成员有：\n" + associations.get(6).getMembers());
                break;
            case 8:
                System.out.println(associations.get(7).getName() + "的成员有：\n" + associations.get(7).getMembers());
                break;
            case 9:
                System.out.println(associations.get(8).getName() + "的成员有：\n" + associations.get(8).getMembers());
                break;
            case 10:
                System.out.println(associations.get(9).getName() + "的成员有：\n" + associations.get(9).getMembers());
                break;
        }
    }

    public void AssociationSort() {
        System.out.println("请选择排序方式");
        System.out.println("1-按成员数量排序");
        System.out.println("2-按活动数量排序");
        switch (reader.nextInt()) {
            case 1: {
                Collections.sort(associations, new Comparator<Association>() {
                    @Override
                    public int compare(Association o1, Association o2) {
                        return o2.getMember_num() - o1.getMember_num();
                    }
                });
                System.out.println("按成员数量排序为：");
                for (Association a : associations) {
                    System.out.println(a.getName() + "   成员数量为：" + a.getMember_num());
                }
                break;
            }

            case 2: {
                Collections.sort(associations, new Comparator<Association>() {
                    @Override
                    public int compare(Association o1, Association o2) {
                        return o2.getActivity_num() - o1.getActivity_num();
                    }
                });
                System.out.println("按活动数量排序为：");
                for (Association a : associations) {
                    System.out.println(a.getName() + "   活动数量为：" + a.getActivity_num());
                }
                break;
            }
        }
    }

    public void InfoGather() {
        System.out.println("活动信息汇总：");
        for (Activity a : activities) {
            System.out.println("活动名称: " + a.getName() + "\n开始时间: " + a.getHold_time() +
                    "\n地点: " + a.getLocation() +
                    "\n简介: " + a.getSlogan() + "\n主办社团:");
            String[] acts = a.getAssociations().split("、");
            for (int i = 0; i < acts.length; i++) {
                System.out.println(i + 1 + "-" + acts[i]);
            }
            System.out.println("\n");
        }
        System.out.println("\n其中需要备注的活动有");
        for (Activity b : activities) {
            if (!b.getRemark().equals(" ")) {
                System.out.println("活动名称: " + b.getName() + "\n开始时间: " + b.getHold_time() +
                        "\n地点: " + b.getLocation() +
                        "\n简介: " + b.getSlogan() + "\n备注: " + b.getRemark() + "\n主办社团:");
                String[] bcts = b.getAssociations().split("、");
                for (int i = 0; i < bcts.length; i++) {
                    System.out.println(i + 1 + "-" + bcts[i]);
                }
                System.out.println("\n");
            }

        }
    }
}

