package dsf.checkWord.outlook;

import dsf.checkWord.xml.XmlConfig;
import dsf.checkWord.xml.XmlUtil;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;


@SuppressWarnings("serial")
public class Uploadfile extends JFrame implements ActionListener
{
    private JPanel toppPanel=new JPanel();
    private JPanel bottompPanel=new JPanel();
    private JPanel leftPanel=new JPanel();
    private JPanel middlePanel=new JPanel();
    private JPanel rightPanel=new JPanel();
    private DefaultListModel<String> model=new DefaultListModel<String>();
    private JList<String> list=new JList<String>(model);
    private JTextArea jTextArea=new JTextArea();

    private JButton chooseproperButton = new JButton("选择属性");

    private JButton deleteButton = new JButton("退  出");
    private JButton OKButton = new JButton("确定");
    private JButton showButton = new JButton("显示详情");

    private Map<String, Map<String, Map<String, String>>> configs = XmlConfig.getAllConfig();

    public Uploadfile()
    {
        super();
        this.setTitle("属性");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//窗体关闭时的操作 退出程序
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.NORMAL);//最小化
        this.setResizable(false);//禁用最大化按钮
        this.setVisible(true);
        init();
    }

    public void left()
    {
        BoxLayout boxLayout=new BoxLayout(leftPanel,BoxLayout.X_AXIS);
        leftPanel.setLayout(boxLayout);
        JLabel choosexml=new JLabel("选择历史配置:");
        choosexml.setAlignmentY(TOP_ALIGNMENT);


        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setVisibleRowCount(5);


        //List<String> show=Split.xmlToList(configs);//从xml中获取所有的配置的第一条信息
        List<String> show=Split.xmlname(configs);//读取xml的名字
        if(show!=null) {
            for (String a : show) {
                model.addElement(a);//显示历史配置
            }
        }


        JScrollPane scrollPane=new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(120, 80));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollPane.setAlignmentY(TOP_ALIGNMENT );

        leftPanel.add(choosexml);
        leftPanel.add(Box.createHorizontalGlue ());
        leftPanel.add(scrollPane);
        leftPanel.setBorder(BorderFactory.createEmptyBorder (80,10,80, 10));
    }

    public void middle()
    {
        BoxLayout boxLayout=new BoxLayout(middlePanel,BoxLayout.Y_AXIS);
        middlePanel.setLayout(boxLayout);
        middlePanel.add(Box.createRigidArea (new Dimension(15, 60)));
        middlePanel.add(showButton);
        middlePanel.add(Box.createRigidArea (new Dimension(15, 60)));
        showButton.addActionListener(this);
        middlePanel.setBorder(BorderFactory.createEmptyBorder (100, 5, 15, 5));
        middlePanel.setAlignmentY(TOP_ALIGNMENT);
    }

    public void right()
    {
        BoxLayout boxLayout=new BoxLayout(rightPanel,BoxLayout.X_AXIS);
        rightPanel.setLayout(boxLayout);
        JLabel showJLabel=new JLabel("详  情:");
        showJLabel.setBorder(BorderFactory.createEmptyBorder (4, 5, 0, 5));
        showJLabel.setAlignmentY(TOP_ALIGNMENT);

        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jTextArea.setText("");
        jTextArea.setLineWrap(true);
        jScrollPane.setPreferredSize(new Dimension(100, 100));


        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        jScrollPane.setAlignmentY(TOP_ALIGNMENT );

        rightPanel.add(showJLabel);
        rightPanel.add(jScrollPane);
        rightPanel.setBorder(BorderFactory.createEmptyBorder (80,10,80, 10));

        rightPanel.setAlignmentY(TOP_ALIGNMENT);
    }

    public void top()
    {
        left();
        middle();
        right();
        BoxLayout boxLayout=new BoxLayout(toppPanel, BoxLayout.X_AXIS);

        toppPanel.setLayout(boxLayout);
        leftPanel.setAlignmentY(TOP_ALIGNMENT);
        middlePanel.setAlignmentY(TOP_ALIGNMENT);
        rightPanel.setAlignmentY(TOP_ALIGNMENT);
        toppPanel.add(leftPanel);
        toppPanel.add(middlePanel);
        toppPanel.add(rightPanel);


    }
    public void bottom()
    {
        BoxLayout boxLayout=new BoxLayout(bottompPanel, BoxLayout.X_AXIS);
        bottompPanel.setLayout(boxLayout);

        bottompPanel.add(Box.createHorizontalGlue ());
        bottompPanel.add(OKButton);
        bottompPanel.add(Box.createHorizontalGlue ());
        bottompPanel.add(chooseproperButton);
        bottompPanel.add(Box.createHorizontalGlue ());
        bottompPanel.add(deleteButton);
        bottompPanel.add(Box.createHorizontalGlue ());

        OKButton.addActionListener(this);
        deleteButton.addActionListener(this);
        chooseproperButton.addActionListener(this);
    }

    public void  init()
    {
        top();
        bottom();

        JPanel jPanel=new JPanel();
        BoxLayout boxLayout=new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        toppPanel.setAlignmentX(LEFT_ALIGNMENT);
        bottompPanel.setAlignmentX(LEFT_ALIGNMENT);
        jPanel.add(Box.createVerticalStrut (10));
        jPanel.add(toppPanel);
        jPanel.add(Box.createVerticalStrut (10));
        jPanel.add(bottompPanel);
        jPanel.add(Box.createVerticalStrut (10));

        Container con = getContentPane();
        con.add(jPanel);
        con.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource()==OKButton)
        {
            //确认当前选择的配置
            XmlConfig.setDefaultConfig(list.getSelectedValue());
            dispose();
            new Homepage().setVisible(true);
        }
        if(e.getSource()==chooseproperButton)//跳转到选择属性
        {
            dispose();
            Interface interface1=new Interface();
            interface1.setVisible(true);

        }

        if(e.getSource()==deleteButton)//退出界面
        {
            dispose();
            new Homepage().setVisible(true);
        }
        if(e.getSource()==showButton)//显示详情
        {
            //int s=list.getSelectedIndex();
            String s=list.getSelectedValue();
            if(s!=null) {
                List<String> show = Split.xmlshow(s, configs);
                jTextArea.setText(" ");
                for (String a : show) {
                    jTextArea.append(a);//显示默认配置
                    jTextArea.append("\n");
                }
            }
            /*
            if(s!=-1)
            {
                List<String> show=Split.xmlshow(s,configs);
                jTextArea.setText(" ");
                for (String a : show) {
                    jTextArea.append(a);//显示默认配置
                    jTextArea.append("\n");
            }
            }
*/
        }
    }


}
