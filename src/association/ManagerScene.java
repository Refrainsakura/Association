package association;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;


public class ManagerScene extends JFrame {
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
    private JPanel panel;
    private JButton button_check;
    private JButton button_sort;
    private JButton button_gather;
    private JLabel title;

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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("管理员");
        setSize(360, 300);
        setLocationRelativeTo(null);
        title = new JLabel("选择使用功能");
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        button_check = new JButton("查看成员");
        button_sort = new JButton("设置排序");
        button_gather = new JButton("信息汇总");

        button_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberCheck();
            }
        });
        button_sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssociationSort();
            }
        });
        button_gather.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoGather();
            }
        });
        add(panel);
        panel.add(title);
        panel.add(button_check);
        panel.add(button_sort);
        panel.add(button_gather);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void MemberCheck() {
        JFrame frame_check = new JFrame();
        frame_check.setSize(360, 640);
        frame_check.setLayout(new BorderLayout());
        frame_check.setLocationRelativeTo(null);
        JPanel panel_check = new JPanel();
        JLabel title_check = new JLabel("请选择想查看的社团");
        title_check.setHorizontalAlignment(SwingConstants.CENTER);
        panel_check.setLayout(new GridLayout(associations.size() + 1, 1));
        panel_check.add(title_check);
        JButton[] ass_name = new JButton[associations.size()];
        for (int i = 0; i < associations.size(); i++) {
            final int j = i;
            ass_name[i] = new JButton(associations.get(i).getName());
            panel_check.add(ass_name[i]);
            ass_name[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MemberShow(j);
                }
            });
        }
        frame_check.add(panel_check);
        frame_check.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_check.setVisible(true);
    }

    public void MemberShow(int id) {
        JFrame frame_member = new JFrame("成员详情");
        frame_member.setSize(360, 200);
        frame_member.setLayout(new BorderLayout());
        frame_member.setLocationRelativeTo(null);
        JPanel panel_member = new JPanel();
        JLabel title_member = new JLabel(associations.get(id).getName() + "的成员有");
        title_member.setHorizontalAlignment(SwingConstants.CENTER);
        JTextArea member = new JTextArea(associations.get(id).getMembers());
        member.setLineWrap(true);
        member.setWrapStyleWord(true);
        member.setEditable(false);
        member.setFont(new Font("黑体", Font.PLAIN, 20));
        member.setAlignmentY(0.5f);
        panel_member.setLayout(new BorderLayout());
        panel_member.add(title_member, BorderLayout.NORTH);
        panel_member.add(member, BorderLayout.CENTER);
        frame_member.add(panel_member);
        frame_member.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_member.setVisible(true);

    }

    public void AssociationSort() {
        JFrame frame_sort = new JFrame("设置排序");
        frame_sort.setSize(360, 240);
        frame_sort.setLayout(new BorderLayout());
        frame_sort.setLocationRelativeTo(null);
        JPanel panel_sort = new JPanel();
        JLabel title_sort = new JLabel("请选择排序方式：");
        title_sort.setHorizontalAlignment(SwingConstants.CENTER);
        JButton button_memnum = new JButton("按成员数量");
        JButton button_actnum = new JButton("按成员数量");
        panel_sort.setLayout(new GridLayout(3, 1));
        panel_sort.add(title_sort);
        panel_sort.add(button_memnum);
        panel_sort.add(button_actnum);
        frame_sort.add(panel_sort);
        frame_sort.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_sort.setVisible(true);
        button_actnum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssociationSortbyActnum();
            }
        });
        button_memnum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssociationSortbyMemnum();
            }
        });
    }

    public void AssociationSortbyMemnum() {
        Collections.sort(associations, new Comparator<Association>() {
            @Override
            public int compare(Association o1, Association o2) {
                return o2.getMember_num() - o1.getMember_num();
            }
        });

        JFrame frame_sbm = new JFrame("成员排序");
        frame_sbm.setSize(360, 320);
        frame_sbm.setLayout(new BorderLayout());
        frame_sbm.setLocationRelativeTo(null);
        JPanel panel_sbm = new JPanel();
        JLabel title_sbm = new JLabel("按成员排序为");
        title_sbm.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField[] assbm = new JTextField[associations.size()];
        panel_sbm.add(title_sbm);
        for (int i = 0; i < associations.size(); i++) {
            assbm[i] = new JTextField(i + 1 + "-" + associations.get(i).getName() + "   成员数量为" + associations.get(i).getMember_num());
            panel_sbm.add(assbm[i]);
        }
        panel_sbm.setLayout(new GridLayout(associations.size() + 1, 1));
        frame_sbm.add(panel_sbm);
        frame_sbm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_sbm.setVisible(true);
    }


    public void AssociationSortbyActnum() {
        Collections.sort(associations, new Comparator<Association>() {
            @Override
            public int compare(Association o1, Association o2) {
                return o2.getActivity_num() - o1.getActivity_num();
            }
        });

        JFrame frame_sba = new JFrame("活动排序");
        frame_sba.setSize(360, 320);
        frame_sba.setLayout(new BorderLayout());
        frame_sba.setLocationRelativeTo(null);
        JPanel panel_sba = new JPanel();
        JLabel title_sba = new JLabel("按活动排序为");
        title_sba.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField[] assba = new JTextField[associations.size()];
        panel_sba.add(title_sba);
        for (int i = 0; i < associations.size(); i++) {
            assba[i] = new JTextField(i + 1 + "-" + associations.get(i).getName() + "    活动数量为" + associations.get(i).getActivity_num());
            panel_sba.add(assba[i]);
        }
        panel_sba.setLayout(new GridLayout(associations.size() + 1, 1));
        frame_sba.add(panel_sba);
        frame_sba.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_sba.setVisible(true);
    }

    public void InfoGather() {
        JFrame frame_info = new JFrame("信息汇总");
        frame_info.setSize(360, 400);
        frame_info.setLayout(new GridLayout(2, 1));
        frame_info.setLocationRelativeTo(null);
        JPanel panel_info1 = new JPanel();
        JScrollPane jsp_info = new JScrollPane();
        JScrollPane jsp_info2 = new JScrollPane();
        JTextField title_info1 = new JTextField("活动信息汇总:");
        title_info1.setHorizontalAlignment(SwingConstants.LEFT);
        title_info1.setEditable(false);
        panel_info1.add(title_info1);
        JTextArea info[] = new JTextArea[activities.size()];
        for (int i = 0; i < activities.size(); i++) {
            info[i] = new JTextArea("活动名称: " + activities.get(i).getName() + "\n开始时间: " + activities.get(i).getHold_time() +
                    "\n地点: " + activities.get(i).getLocation() +
                    "\n简介: " + activities.get(i).getSlogan() + "\n主办社团: " + activities.get(i).getAssociations());
            info[i].setLineWrap(true);
            info[i].setWrapStyleWord(true);
            info[i].setEditable(false);
            info[i].setFont(new Font("黑体", Font.PLAIN, 16));
            info[i].setAlignmentY(0.5f);
            panel_info1.add(info[i]);
        }

        JPanel panel_info2 = new JPanel();
        JTextField title_info2 = new JTextField("其中需要备注的活动有:");
        title_info2.setEditable(false);
        panel_info2.add(title_info2);
        title_info2.setHorizontalAlignment(SwingConstants.LEFT);
        title_info1.setFont(new Font("宋体",Font.BOLD,18));
        title_info2.setFont(new Font("宋体",Font.BOLD,18));

        JTextArea info2[] = new JTextArea[activities.size()];
        for (int i = 0; i < activities.size(); i++) {
            if (!activities.get(i).getRemark().equals(" ")) {
                info2[i] = new JTextArea("活动名称: " + activities.get(i).getName() + "\n开始时间: " + activities.get(i).getHold_time() +
                        "\n地点: " + activities.get(i).getLocation() +
                        "\n简介: " + activities.get(i).getSlogan() + "\n备注: " + activities.get(i).getRemark() + "\n主办社团: " + activities.get(i).getAssociations());
                info2[i].setLineWrap(true);
                info2[i].setWrapStyleWord(true);
                info2[i].setEditable(false);
                info2[i].setFont(new Font("黑体", Font.PLAIN, 16));
                info2[i].setAlignmentY(0.5f);
                panel_info2.add(info2[i]);
            }
        }
        jsp_info.add(panel_info1);
        jsp_info2.add(panel_info2);
        jsp_info.setViewportView(panel_info1);
        jsp_info2.setViewportView(panel_info2);
        panel_info1.setLayout(new BoxLayout(panel_info1, BoxLayout.Y_AXIS));
        panel_info2.setLayout(new BoxLayout(panel_info2, BoxLayout.Y_AXIS));
        frame_info.add(jsp_info);
        frame_info.add(jsp_info2);
        frame_info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_info.setVisible(true);

    }
}

