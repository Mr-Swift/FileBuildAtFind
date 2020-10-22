package com.apple.developer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

@SuppressWarnings("all")
public class UserJFrame extends JFrame implements Runnable{
    private ComputerType type;
    private JLabel jLabelWorning=null;

    public UserJFrame() {
        this.setSize(600, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int x=(int)(this.getToolkit().getScreenSize().getWidth()-this.getWidth())/2;
        int y=(int)(this.getToolkit().getScreenSize().getHeight()-this.getHeight())/2;
        this.setTitle("一键生成综测文件");
        this.setLocation(x,y);

        JPanel jPanel = new JPanel();
        this.add(jPanel, BorderLayout.CENTER);
        CardLayout cardLayout = new CardLayout();
        jPanel.setLayout(cardLayout);

        JPanel jPanel01 = new JPanel();
        jPanel.add(jPanel01);

        JPanel jPanel02 = new JPanel();
        jPanel.add(jPanel02);

        jPanel01.setLayout(null);

        if ("/".equals(File.separator) && ":".equals(File.pathSeparator)) {//判断机器类型是否是麦金托什机
            type = ComputerType.MACINTOSH;
        } else if ("\\".equals(File.separator) && ";".equals(File.pathSeparator)) {
            type = ComputerType.WINDOWS;
        }

        JLabel jLabelAddress = new JLabel();
        JLabel jLabelName = new JLabel();
        if (type == ComputerType.MACINTOSH) {
            JLabel jLabelHide = new JLabel("尊贵的Mac用户");
            jLabelHide.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 20));
            jLabelHide.setBounds(200, 30, 300, 20);
            jPanel01.add(jLabelHide);
            jLabelAddress.setText("请您输入或粘贴存放新建文件夹的地址：");
            jLabelName.setText("请您输入新建文件夹的文件名：");
        } else if (type == ComputerType.WINDOWS) {
            jLabelAddress.setText("输入或粘贴存放新建文件夹的地址：");
            jLabelName.setText("输入新建文件夹的文件名：");
        }
        jLabelAddress.setBounds(145, 80, 400, 20);
        jLabelAddress.setFont(new Font("华文新魏", Font.BOLD, 15));
        jPanel01.add(jLabelAddress);

        jLabelName.setBounds(145,165,400,20);
        jLabelName.setFont(new Font("华文新魏", Font.BOLD, 15));
        jPanel01.add(jLabelName);

        jLabelWorning = new JLabel();
        jLabelWorning.setBounds(140,250,300,15);
        jPanel01.add(jLabelWorning);

        JTextField jTextField01 = new JTextField();
        jTextField01.setBounds(140, 110, 300, 35);
        jPanel01.add(jTextField01);

        JTextField jTextField02 = new JTextField();
        jTextField02.setBounds(140, 200, 300, 35);
        jPanel01.add(jTextField02);

        JButton jButtonMkdir = new JButton("新建");
        jButtonMkdir.setBounds(220,280,100,50);
        jButtonMkdir.setFont(new Font("黑体",Font.BOLD,17));
        jPanel01.add(jButtonMkdir);



        jButtonMkdir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLabelWorning.setText(new FileMkdir().Mkdirs(jTextField01.getText(),jTextField02.getText()));
            }
        });

        jTextField01.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    jLabelWorning.setText(new FileMkdir().Mkdirs(jTextField01.getText(),jTextField02.getText()));
                }
            }
        });

        jTextField02.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    jLabelWorning.setText(new FileMkdir().Mkdirs(jTextField01.getText(),jTextField02.getText()));
                }
            }
        });
        new Thread(this).start();
        this.setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jLabelWorning.setText("");
        }
    }
}
