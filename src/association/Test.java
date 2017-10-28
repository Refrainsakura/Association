package association;

import javax.swing.*;
import java.util.Scanner;
import java.awt.*;

public class Test {
//    private JPanel main_panel;
//    private JLabel label;
//    private JButton button_manager;
//    private JButton button_user;
//    public Test(){
//        this.setTitle("招新展板");
//        this.setLayout(new BoxLayout(this,1));
//        this.setSize(640,480);
//        main_panel = new JPanel();
//        label = new JLabel("请选择你的身份");
//        button_manager = new JButton("管理员");
//        button_user = new JButton("学生");
//        this.add(main_panel);
//        main_panel.add(label);
//        main_panel.add(button_manager);
//        main_panel.add(button_user);
////        this.pack();
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//    }
    private static String[] str = {};
    public static void main(String[] args) {
        new Test();
        Scanner re = new Scanner(System.in);
        System.out.println("请选择你的身份：");
        System.out.println("1-学生");
        System.out.println("2-管理员");
        switch (re.nextInt()) {
            case 1: {
                UserScene us = new UserScene();

                us.Manager();
                break;
            }
            case 2: {
                ManagerScene ms = new ManagerScene();
                ms.Manager();
                break;
            }
            default: {
                System.out.println("请重新输入");
                main(str);
                break;
            }
        }
    }
}
