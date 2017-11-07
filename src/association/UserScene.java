package association;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Collator;
import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;

public class UserScene extends JFrame {
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
    private JButton button_find;
    private JLabel title_ass;
    private JLabel title_act;
    private JButton[] button_detail1;
    private JButton[] button_detail2;
    private JPanel[] panel_ass;
    private JPanel[] panel_act;
    private JPanel panel_title;
    private JScrollPane jsp_main;

    public UserScene() {
        ImageShow();
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

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("用户");
        setSize(360, 300);
        setLocationRelativeTo(null);
        jsp_main = new JScrollPane();
        title_ass = new JLabel("社团列表");
        title_act = new JLabel("活动列表");
        title_ass.setFont(new Font("宋体", Font.BOLD, 18));
        title_act.setFont(new Font("宋体", Font.BOLD, 18));
        title_ass.setVerticalAlignment(SwingConstants.CENTER);
        title_ass.setHorizontalAlignment(SwingConstants.CENTER);
        button_find = new JButton("查找");
        button_find.setHorizontalAlignment(SwingConstants.RIGHT);
        button_find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindAll();
            }
        });
        JTextField ass_name[] = new JTextField[associations.size()];
        panel_ass = new JPanel[associations.size()];
        button_detail1 = new JButton[associations.size()];
        panel_title = new JPanel();
        panel_title.setLayout(new GridLayout(1, 2));
        panel_title.add(title_ass);
        panel_title.add(button_find);
        panel = new JPanel();
        panel.add(panel_title);
        for (int i = 0; i < associations.size(); i++) {
            final int m = i;
            ass_name[i] = new JTextField((i + 1) + "-" + associations.get(i).getName());
            ass_name[i].setEditable(false);
            ass_name[i].setFont(new Font("黑体", Font.PLAIN, 16));
            button_detail1[i] = new JButton("查看详情");
            panel_ass[i] = new JPanel();
            panel_ass[i].setLayout(new GridLayout(1, 2));
            panel_ass[i].add(ass_name[i]);
            panel_ass[i].add(button_detail1[i]);
            panel.add(panel_ass[i]);
            button_detail1[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AssDetail_Show(m);
                }
            });
        }
        JTextField act_name[] = new JTextField[activities.size()];
        panel_act = new JPanel[activities.size()];
        button_detail2 = new JButton[activities.size()];
        panel.add(title_act);
        for (int j = 0; j < activities.size(); j++) {
            final int n = j;
            act_name[j] = new JTextField((j + 1) + "-" + activities.get(j).getName());
            act_name[j].setEditable(false);
            act_name[j].setFont(new Font("黑体", Font.PLAIN, 16));
            button_detail2[j] = new JButton("查看详情");
            panel_act[j] = new JPanel();
            panel_act[j].setLayout(new GridLayout(1, 2));
            panel_act[j].add(act_name[j]);
            panel_act[j].add(button_detail2[j]);
            panel.add(panel_act[j]);
            button_detail2[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ActDetail_Show(n);
                }
            });
        }
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        jsp_main.add(panel);
        jsp_main.setViewportView(panel);
        add(jsp_main);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    public void AssDetail_Show(int id) {
        JFrame frame_assdetail = new JFrame("社团详情");
        frame_assdetail.setSize(360, 400);
        frame_assdetail.setLayout(new BorderLayout());
        frame_assdetail.setLocationRelativeTo(null);
        JPanel panel_assdetail = new JPanel();
        JPanel panel_buts = new JPanel();
        JLabel label_act = new JLabel("点击查看举办活动：");
        JLabel title_assdetail = new JLabel(associations.get(id).getName() + "的详情为：");
        title_assdetail.setHorizontalAlignment(SwingConstants.CENTER);
        title_assdetail.setFont(new Font("宋体", Font.BOLD, 22));
        JTextArea assdetail = new JTextArea("社团名: " + associations.get(id).getName() + "\n创建时间: " + associations.get(id).getEstablish_time() +
                "\n负责人:" + "\n姓名: " + associations.get(id).getManager_info().getName() + "\n院系: " + associations.get(id).getManager_info().getCollege() +
                "\n班级: " + associations.get(id).getManager_info().getClass_num() + "\n联系方式:" + "\n联系电话: " + associations.get(id).getContact_way().getPhone_num() +
                "\n邮箱: " + associations.get(id).getContact_way().getEmail() + "\n简介: " + associations.get(id).getIntro() + "\n活动： " + associations.get(id).getActivities());
        assdetail.setLineWrap(true);
        assdetail.setWrapStyleWord(true);
        assdetail.setEditable(false);
        assdetail.setFont(new Font("黑体", Font.PLAIN, 20));
        assdetail.setAlignmentY(0.5f);
        String[] split = associations.get(id).getActivities().split("、");
        JButton[] button_asso = new JButton[split.length];
        panel_assdetail.setLayout(new BorderLayout());
        panel_buts.setLayout(new FlowLayout());
        panel_buts.add(label_act);
        for (int i = 0; i < associations.get(id).getActivity_num(); i++) {
            final int j = i;
            button_asso[i] = new JButton(split[i]);
            button_asso[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FindbyName(split[j]);
                }
            });
            panel_buts.add(button_asso[i]);
        }
        panel_assdetail.add(title_assdetail, BorderLayout.NORTH);
        panel_assdetail.add(assdetail, BorderLayout.CENTER);
        panel_assdetail.add(panel_buts, BorderLayout.SOUTH);
        frame_assdetail.add(panel_assdetail);
        frame_assdetail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_assdetail.setVisible(true);
    }

    public void ActDetail_Show(int id) {
        JFrame frame_actdetail = new JFrame("活动详情");
        frame_actdetail.setSize(360, 400);
        frame_actdetail.setLayout(new BorderLayout());
        frame_actdetail.setLocationRelativeTo(null);
        JPanel panel_actdetail = new JPanel();
        JPanel panel_buts = new JPanel();
        JLabel label_ass = new JLabel("点击查看举办社团：");
        JLabel title_actdetail = new JLabel(activities.get(id).getName() + "的详情为：");
        title_actdetail.setHorizontalAlignment(SwingConstants.CENTER);
        title_actdetail.setFont(new Font("宋体", Font.BOLD, 22));
        JTextArea actdetail = new JTextArea("活动名称: " + activities.get(id).getName() + "\n开始时间: " + activities.get(id).getHold_time() +
                "\n地点: " + activities.get(id).getLocation() +
                "\n简介: " + activities.get(id).getSlogan());
        actdetail.setLineWrap(true);
        actdetail.setWrapStyleWord(true);
        actdetail.setEditable(false);
        actdetail.setFont(new Font("黑体", Font.PLAIN, 20));
        actdetail.setAlignmentY(0.5f);
        String[] split = activities.get(id).getAssociations().split("、");
        JButton[] button_acts = new JButton[split.length];
        panel_actdetail.setLayout(new BorderLayout());
        panel_buts.setLayout(new FlowLayout());
        panel_buts.add(label_ass);
        for (int i = 0; i < split.length; i++) {
            final int j = i;
            button_acts[i] = new JButton(split[i]);
            button_acts[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FindbyName(split[j]);
                }
            });
            panel_buts.add(button_acts[i]);
        }
        panel_actdetail.setLayout(new BorderLayout());
        panel_actdetail.add(title_actdetail, BorderLayout.NORTH);
        panel_actdetail.add(actdetail, BorderLayout.CENTER);
        panel_actdetail.add(panel_buts, BorderLayout.SOUTH);
        frame_actdetail.add(panel_actdetail);
        frame_actdetail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_actdetail.setVisible(true);
    }

    public void FindAll() {
        JFrame frame_findall = new JFrame("查找");
        frame_findall.setSize(360, 300);
        frame_findall.setLocationRelativeTo(null);
        JLabel title_find = new JLabel("选择使用功能");
        JPanel panel_find = new JPanel();
        JPanel panel_fbn = new JPanel();
        JPanel panel_fbt = new JPanel();
        JPanel panel_fba = new JPanel();
        panel_find.setLayout(new GridLayout(4, 1));
        title_find.setVerticalAlignment(SwingConstants.CENTER);
        title_find.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel label_fbn = new JLabel("输入名称");
        JLabel label_fbt = new JLabel("输入时间");
        JLabel label_fba = new JLabel("输入社团");
        JButton button_fbn = new JButton("查询");
        JButton button_fbtb = new JButton("筛选之前");
        JButton button_fbta = new JButton("筛选之后");
        JButton button_fba = new JButton("筛选");
        JTextField text_fbn = new JTextField(16);
        JTextField text_fbt = new JTextField(16);
        JTextField text_fba = new JTextField(16);
        panel_fbn.setLayout(new FlowLayout());
        panel_fbn.add(label_fbn);
        panel_fbn.add(text_fbn);
        panel_fbn.add(button_fbn);
        panel_fbt.setLayout(new FlowLayout());
        panel_fbt.add(label_fbt);
        panel_fbt.add(text_fbt);
        panel_fbt.add(button_fbtb);
        panel_fbt.add(button_fbta);
        panel_fba.setLayout(new FlowLayout());
        panel_fba.add(label_fba);
        panel_fba.add(text_fba);
        panel_fba.add(button_fba);
        button_fbn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindbyName(text_fbn.getText().toString());
            }
        });
        button_fbtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindActicitybyTimeBefore(text_fbt.getText().toString());
            }
        });
        button_fbta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindActicitybyTimeAfter(text_fbt.getText().toString());
            }
        });
        button_fba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindActivitybyAssociation(text_fba.getText().toString());
            }
        });
        add(panel_find);
        panel_find.add(title_find);
        panel_find.add(panel_fbn);
        panel_find.add(panel_fbt);
        panel_find.add(panel_fba);
        frame_findall.add(panel_find);
        frame_findall.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_findall.setVisible(true);
    }

    public void FindbyName(String name) {
        int count = 0;
        for (int i = 0; i < associations.size(); i++) {
            if (associations.get(i).getName().equals(name)) {
                AssDetail_Show(i);
            } else count++;
        }
        for (int j = 0; j < activities.size(); j++) {
            if (activities.get(j).getName().equals(name)) {
                ActDetail_Show(j);
            } else count++;
        }
        if (count == activities.size() + associations.size()) {
            JDialog dia_fbn = new JDialog();
            dia_fbn.setLocationRelativeTo(null);
            JLabel title_dia = new JLabel("没有找到,请重新输入");
            title_dia.setHorizontalAlignment(SwingConstants.CENTER);
            dia_fbn.setSize(200, 120);
            dia_fbn.setVisible(true);
            dia_fbn.getContentPane().add(title_dia);

        }
    }

    public void FindActicitybyTimeBefore(String date) {
        int count = 0;
        JFrame frame_fbtb = new JFrame();
        frame_fbtb.setSize(360, 300);
        frame_fbtb.setLocationRelativeTo(null);
        JScrollPane jsp_fbtb = new JScrollPane();
        JLabel title_actf = new JLabel("活动列表");
        title_actf.setFont(new Font("宋体", Font.BOLD, 18));
        title_actf.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField act_namef[] = new JTextField[activities.size()];
        JPanel[] panel_actf = new JPanel[activities.size()];
        JButton[] button_detailf = new JButton[activities.size()];
        JPanel panelf = new JPanel();
        panelf.add(title_actf);
        for (int i = 0; i < activities.size(); i++) {
            if (compare_date(date, activities.get(i).getHold_time()) == 1) {
                final int m = i;
                act_namef[i] = new JTextField(activities.get(i).getName());
                act_namef[i].setEditable(false);
                act_namef[i].setFont(new Font("黑体", Font.PLAIN, 16));
                button_detailf[i] = new JButton("查看详情");
                panel_actf[i] = new JPanel();
                panel_actf[i].setLayout(new GridLayout(1, 2));
                panel_actf[i].add(act_namef[i]);
                panel_actf[i].add(button_detailf[i]);
                panelf.add(panel_actf[i]);
                button_detailf[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ActDetail_Show(m);
                    }
                });
            } else count++;
        }
        panelf.setLayout(new BoxLayout(panelf, BoxLayout.Y_AXIS));
        jsp_fbtb.add(panelf);
        jsp_fbtb.setViewportView(panelf);
        frame_fbtb.add(jsp_fbtb);
        frame_fbtb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_fbtb.setVisible(true);
        if (count == activities.size()) {
        	JDialog dia_fbn = new JDialog();
            dia_fbn.setLocationRelativeTo(null);
            JLabel title_dia = new JLabel("没有找到,请重新输入");
            title_dia.setHorizontalAlignment(SwingConstants.CENTER);
            dia_fbn.setSize(200, 120);
            dia_fbn.setVisible(true);
            dia_fbn.getContentPane().add(title_dia);

        }
    }

    public void FindActicitybyTimeAfter(String date) {
        int count = 0;
        JFrame frame_fbta = new JFrame();
        frame_fbta.setSize(360, 300);
        frame_fbta.setLocationRelativeTo(null);
        JScrollPane jsp_fbta = new JScrollPane();
        JLabel title_actf = new JLabel("活动列表");
        title_actf.setFont(new Font("宋体", Font.BOLD, 18));
        title_actf.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField act_namef[] = new JTextField[activities.size()];
        JPanel[] panel_actf = new JPanel[activities.size()];
        JButton[] button_detailf = new JButton[activities.size()];
        JPanel panelf = new JPanel();
        panelf.add(title_actf);
        for (int i = 0; i < activities.size(); i++) {
            if (compare_date(date, activities.get(i).getHold_time()) == -1) {
                final int m = i;
                act_namef[i] = new JTextField(activities.get(i).getName());
                act_namef[i].setEditable(false);
                act_namef[i].setFont(new Font("黑体", Font.PLAIN, 16));
                button_detailf[i] = new JButton("查看详情");
                panel_actf[i] = new JPanel();
                panel_actf[i].setLayout(new GridLayout(1, 2));
                panel_actf[i].add(act_namef[i]);
                panel_actf[i].add(button_detailf[i]);
                panelf.add(panel_actf[i]);
                button_detailf[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ActDetail_Show(m);
                    }
                });
            } else count++;
        }
        panelf.setLayout(new BoxLayout(panelf, BoxLayout.Y_AXIS));
        jsp_fbta.add(panelf);
        jsp_fbta.setViewportView(panelf);
        frame_fbta.add(jsp_fbta);
        frame_fbta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_fbta.setVisible(true);
        if (count == activities.size()) {
        	JDialog dia_fbn = new JDialog();
            dia_fbn.setLocationRelativeTo(null);
            JLabel title_dia = new JLabel("没有找到,请重新输入");
            title_dia.setHorizontalAlignment(SwingConstants.CENTER);
            dia_fbn.setSize(200, 120);
            dia_fbn.setVisible(true);
            dia_fbn.getContentPane().add(title_dia);

        }
    }

    public void FindActivitybyAssociation(String asname) {
        int count = 0;
        JFrame frame_fba = new JFrame();
        frame_fba.setSize(360, 200);
        frame_fba.setLocationRelativeTo(null);
        JScrollPane jsp_fba = new JScrollPane();
        JLabel title_actf = new JLabel("活动列表");
        title_actf.setFont(new Font("宋体", Font.BOLD, 18));
        title_actf.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField act_namef[] = new JTextField[activities.size()];
        JPanel[] panel_actf = new JPanel[activities.size()];
        JButton[] button_detailf = new JButton[activities.size()];
        JPanel panelf = new JPanel();
        panelf.add(title_actf);
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getAssociations().indexOf(asname) != -1) {
                final int m = i;
                act_namef[i] = new JTextField(activities.get(i).getName());
                act_namef[i].setEditable(false);
                act_namef[i].setFont(new Font("黑体", Font.PLAIN, 16));
                button_detailf[i] = new JButton("查看详情");
                panel_actf[i] = new JPanel();
                panel_actf[i].setLayout(new GridLayout(1, 2));
                panel_actf[i].add(act_namef[i]);
                panel_actf[i].add(button_detailf[i]);
                panelf.add(panel_actf[i]);
                button_detailf[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ActDetail_Show(m);
                    }
                });
            } else count++;
        }
        panelf.setLayout(new BoxLayout(panelf, BoxLayout.Y_AXIS));
        jsp_fba.add(panelf);
        jsp_fba.setViewportView(panelf);
        frame_fba.add(jsp_fba);
        frame_fba.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_fba.setVisible(true);
        if (count == activities.size()) {
        	JDialog dia_fbn = new JDialog();
            dia_fbn.setLocationRelativeTo(null);
            JLabel title_dia = new JLabel("没有找到,请重新输入");
            title_dia.setHorizontalAlignment(SwingConstants.CENTER);
            dia_fbn.setSize(200, 120);
            dia_fbn.setVisible(true);
            dia_fbn.getContentPane().add(title_dia);

        }
    }

    public void ImageShow() {
        int delay = 1000;
        Timer start_timer = new Timer();
        Comparator sort = Collator.getInstance(java.util.Locale.CHINA);
        Collections.sort(an, sort);
        TimerTask start_task = new TimerTask() {
            @Override
            public void run() {
                JFrame frame_show = new JFrame("图片展示");
                frame_show.setSize(360, 200);
                frame_show.setLocationRelativeTo(null);
                JPanel panel_show = new JPanel();
                panel_show.setLayout(new BoxLayout(panel_show,BoxLayout.Y_AXIS));
                JTextField text_show1 = new JTextField("HEI");
                JTextField text_show2 = new JTextField();
                for (int i = 0; i < associations.size(); i++) {
                    int num1 = new Random().nextInt(10) + 1;
                    int num2 = new Random().nextInt(10) + 1;
                    text_show1.setText("展示" + an.get(i) + "的第" + num1 + "张图片");
                    text_show2.setText("展示" + an.get(i) + "的第" + num2 + "张图片");
                    try {
                        Thread.sleep(5 * delay);//delay=1s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                panel_show.add(text_show1);
                panel_show.add(text_show2);
                frame_show.add(panel_show);
                frame_show.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frame_show.setVisible(true);
            }
        };
        start_timer.schedule(start_task, 60 * delay);
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
            JDialog dia_fbn = new JDialog();
            dia_fbn.setLocationRelativeTo(null);
            JLabel title_dia = new JLabel("输入不合法，请重新输入");
            title_dia.setHorizontalAlignment(SwingConstants.CENTER);
            dia_fbn.setSize(200, 120);
            dia_fbn.setVisible(true);
            dia_fbn.getContentPane().add(title_dia);
            dia_fbn.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        return 0;
    }
}

