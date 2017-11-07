package association;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


public class Test extends JFrame{
        private JPanel main_panel;
    private JLabel label;
    private JButton button_manager;
    private JButton button_user;

    public Test(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLocationRelativeTo(null);
        this.setTitle("招新展板");
        this.setLayout(new BorderLayout());
        this.setSize(320,200);
        main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(3,1));
        label = new JLabel("请选择你的身份");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        button_manager = new JButton("管理员");
        button_user = new JButton("学生");
        this.add(main_panel);
        main_panel.add(label);
        main_panel.add(button_manager);
        main_panel.add(button_user);
        button_manager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerScene();
            }
        });
        button_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserScene();
            }
        });
//        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Test();
//        new Test();
//        Scanner re = new Scanner(System.in);
//        System.out.println("请选择你的身份：");
//        System.out.println("1-学生");
//        System.out.println("2-管理员");
//        switch (re.nextInt()) {
//            case 1: {
//                UserScene us = new UserScene();
////                us.Manager();
//                break;
//            }
//            case 2: {
//                ManagerScene ms = new ManagerScene();
//                ms.Manager();
//                break;
//            }
//            default: {
//                System.out.println("请重新输入");
//                main(str);
//                break;
//            }
//        }
    }
}
