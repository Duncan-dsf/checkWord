package dsf.checkWord.outlook;

import dsf.checkWord.xml.XmlConfig;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.*;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener
{

    private JPanel toppPanel=new JPanel();
    private JPanel bottompPanel=new JPanel();
    private JPanel leftPanel=new JPanel();
    private JPanel middlePanel=new JPanel();
    private JPanel rightPanel=new JPanel();
    private JComboBox<String> comseries=new JComboBox<String>();//标题级别
    private JComboBox<String> comfont=new JComboBox<String>();//字体
    private JComboBox<String> comfontsize=new JComboBox<String>();//字号
    private JComboBox<String> comcolor=new JComboBox<String>();//颜色
    private JButton addButton= new JButton("添  加");
    private JButton deleteButton = new JButton("删  除");

    private DefaultListModel<String> model=new DefaultListModel<String>();
    private JList<String> list=new JList<String>(model);
    private JButton actionbutton=new JButton("确认属性");
    private JButton closeButton=new JButton("退出");

    //
    Map<String,Map<String,String>> map=new HashMap<>(10);
    List<Map<String, String>> mapList=new LinkedList<Map<String,String>>();
    //
    public Interface()
    {
        super();
        this.setTitle("选择属性");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//窗体关闭时的操作 退出程序
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.NORMAL);//最小化
        this.setResizable(false);//禁用最大化按钮
        this.setVisible(true);
        Init();
    }
    public void left()
    {
        BoxLayout boxLayout=new BoxLayout(leftPanel,BoxLayout.Y_AXIS);
        leftPanel.setLayout(boxLayout);//设置left为垂直布局
        //级别
        JPanel left1=new JPanel();
        boxLayout=new BoxLayout(left1,BoxLayout.X_AXIS);
        left1.setLayout(boxLayout);
        JLabel series=new JLabel("标题级别：");
        comseries.addItem("正文");
        comseries.addItem("1级");
        comseries.addItem("2级");
        comseries.addItem("3级");
        comseries.addItem("4级");
        comseries.addItem("5级");
        comseries.addItem("6级");
        comseries.addItem("7级");
        comseries.addItem("8级");
        comseries.addItem("9级");


        series.setAlignmentY(TOP_ALIGNMENT);
        comseries.setAlignmentY(TOP_ALIGNMENT);
        left1.add(series);
        left1.add(comseries);
        left1.setAlignmentX(LEFT_ALIGNMENT);
        leftPanel.add(left1);
        leftPanel.add(Box.createVerticalStrut (20));
        //字体
        JPanel left2=new JPanel();
        boxLayout=new BoxLayout(left2,BoxLayout.X_AXIS);
        left2.setLayout(boxLayout);
        JLabel font=new JLabel("字      体：");
        comfont.addItem("宋体");
        comfont.addItem("楷体");
        comfont.addItem("黑体");
        comfont.addItem("微软雅黑");
        comfont.addItem("幼圆体");


        font.setAlignmentY(TOP_ALIGNMENT);
        comfont.setAlignmentY(TOP_ALIGNMENT);
        left2.add(font);
        left2.add(comfont);
        left2.setAlignmentX(LEFT_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut (20));
        leftPanel.add(left2);
        leftPanel.add(Box.createVerticalStrut (20));
        //字号
        JPanel left3=new JPanel();
        boxLayout=new BoxLayout(left3,BoxLayout.X_AXIS);
        left3.setLayout(boxLayout);
        JLabel fontsize=new JLabel("字      号：");
        comfontsize.addItem("一号");
        comfontsize.addItem("二号");
        comfontsize.addItem("三号");
        comfontsize.addItem("四号");
        comfontsize.addItem("五号");


        fontsize.setAlignmentY(TOP_ALIGNMENT);
        comfontsize.setAlignmentY(TOP_ALIGNMENT);
        left3.add(fontsize);
        left3.add(comfontsize);
        left3.setAlignmentX(LEFT_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut (20));
        leftPanel.add(left3);
        leftPanel.add(Box.createVerticalStrut (20));
        //颜色
        JPanel left4=new JPanel();
        JLabel color=new JLabel("颜      色：");
        boxLayout=new BoxLayout(left4,BoxLayout.X_AXIS);
        left4.setLayout(boxLayout);
        comcolor.addItem("黑色");
        comcolor.addItem("红色");
        comcolor.addItem("蓝色");
        comcolor.addItem("黄色");
        comcolor.addItem("绿色");


        color.setAlignmentY(TOP_ALIGNMENT);
        comcolor.setAlignmentY(TOP_ALIGNMENT);
        left4.add(color);
        left4.add(comcolor);
        left4.setAlignmentX(LEFT_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut (20));
        leftPanel.add(left4);
        leftPanel.add(Box.createVerticalStrut (40));
        leftPanel.setAlignmentY(TOP_ALIGNMENT);
        leftPanel.setBorder(BorderFactory.createEmptyBorder (10,10,10,10));
    }
    public void middle()
    {
        BoxLayout boxLayout=new BoxLayout(middlePanel,BoxLayout.Y_AXIS);

        middlePanel.setLayout(boxLayout);
        middlePanel.add(addButton);
        middlePanel.add(Box.createRigidArea (new Dimension(15, 60)));
        middlePanel.add(deleteButton);
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        middlePanel.setBorder(BorderFactory.createEmptyBorder (100, 5, 15, 5));
        middlePanel.setAlignmentY(TOP_ALIGNMENT);
    }
    public void right()
    {
        BoxLayout boxLayout=new BoxLayout(rightPanel,BoxLayout.X_AXIS);
        rightPanel.setLayout(boxLayout);
        JLabel showJLabel=new JLabel("所选内容:");
        showJLabel.setBorder(BorderFactory.createEmptyBorder (4, 5, 0, 5));
        showJLabel.setAlignmentY(TOP_ALIGNMENT);

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setVisibleRowCount(5);


        JScrollPane scrollPane=new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(120, 120));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollPane.setAlignmentY(TOP_ALIGNMENT );




        rightPanel.add(showJLabel);
        rightPanel.add(scrollPane);
        rightPanel.setBorder(BorderFactory.createEmptyBorder (80,10,80, 10));

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
        bottompPanel.add(actionbutton);
        bottompPanel.add(Box.createHorizontalGlue ());
        bottompPanel.add(closeButton);
        bottompPanel.add(Box.createHorizontalGlue ());
        actionbutton.addActionListener(this);
        closeButton.addActionListener(this);
    }
    public void Init()
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
        String serString=new String();//标题级别
        String fontString=new String();//字体
        String sizesString=new String();//字号
        String colorString=new String();//颜色
        Map<String, String> styleMap=new HashMap<String, String>();



        if(e.getSource()==addButton)//点击添加按钮，添加属性并显示到右边的列表中
        {
            serString=(String) comseries.getSelectedItem();
            fontString=(String)comfont.getSelectedItem();
            sizesString=(String)comfontsize.getSelectedItem();
            colorString=(String)comcolor.getSelectedItem();
            if(serString==null)
            {
                serString=comseries.getItemAt(0);
                styleMap.put("标题级别", serString);
            }
            if(fontString==null)
            {
                fontString=comfont.getItemAt(0);
                styleMap.put("字体", fontString);
            }
            if(sizesString==null)
            {
                sizesString=comfontsize.getItemAt(0);
                styleMap.put("字号", sizesString);
            }
            if(colorString==null)
            {
                colorString=comcolor.getItemAt(0);
                styleMap.put("颜色", colorString);
            }


            styleMap.put("标题级别",serString );
            styleMap.put("字体", fontString);
            styleMap.put("字号", sizesString);
            styleMap.put("颜色",colorString );
            mapList.add(styleMap);
            //把map添加进xml中的代码-----,然后从xml中读出map,或者直接从页面读取
            model.addElement(serString+","+fontString+","+sizesString+","+colorString);

        }

        if(e.getSource()==deleteButton)//点击删除按钮，先提取删除内容，然后将String解析成map类型并删除xml
        {
            int i=list.getSelectedIndex();//获取选中项下标
            model.removeElementAt(list.getSelectedIndex());//从列表中删除选中项
            mapList.remove(i);//从maplist中删除选中的map
        }
        if(e.getSource()==actionbutton)
        {
            if(mapList!=null) {
                Map<String,String> style=new HashMap<>(4);
                for (Map<String, String> a : mapList)
                {
                    String S = a.get("标题级别");
                    if(S.equals("正文"))
                    {
                        S="0级";
                    }

                    style = a;
                    style.remove("标题级别");
                    map.put(S, style);
                }
                //String inputValue = JOptionPane.showInputDialog("Please input a value");
                //System.out.println(inputValue);
                XmlConfig.addConfig(map);
                //将所选择的属性提交到xml,并显示到主界面上，后退出当前窗口
                dispose();
                new Uploadfile().setVisible(true);

            }
        }
        if(e.getSource()==closeButton)
        {
            dispose();
            new Uploadfile().setVisible(true);
        }


    }


}