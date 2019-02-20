package dsf.checkWord.outlook;

import dsf.checkWord.entity.AbstractWord;
import dsf.checkWord.entity.WordFactory;
import dsf.checkWord.entity.WordParagraph;
import dsf.checkWord.entity.WordRun;
import dsf.checkWord.service.WordAnalyzer;
import dsf.checkWord.xml.XmlConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;


@SuppressWarnings("serial")
public class Homepage extends JFrame implements ActionListener {
    private JPanel topPanel = new JPanel();
    private JPanel bottompPanel = new JPanel();
    private JButton choosefileButton = new JButton("选择文件");
    private JButton actionbutton = new JButton("检  测");
    private JButton closeButton = new JButton("退  出");
    private JButton choose = new JButton("选择配置");

    private DefaultListModel<String> model=new DefaultListModel<String>();
    private JList<String> list=new JList<String>(model);

    List<File> selectedFiles = null;
    File selectedFile = null;
    List<AbstractWord> words;

    public Map<String, Map<String, String>> getRules() {
        return rules;
    }

    public void setRules(Map<String, Map<String, String>> rules) {
        this.rules = rules;
    }

    Map<String, Map<String, String>> rules = XmlConfig.getDefaultConfig();

    public Homepage() {
        this.setTitle("Word检测");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//窗体关闭时的操作 退出程序
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.NORMAL);//最小化
        this.setResizable(false);//禁用最大化按钮
        this.setVisible(true);
        Init();
    }

    public void top() {

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setVisibleRowCount(5);

        BoxLayout boxLayout = new BoxLayout(topPanel, BoxLayout.X_AXIS);
        topPanel.setLayout(boxLayout);
        JLabel showJLabel = new JLabel("默认配置:");
        showJLabel.setBorder(BorderFactory.createEmptyBorder(4, 5, 0, 5));
        showJLabel.setAlignmentY(TOP_ALIGNMENT);


        if(rules != null) {

            List<String> show=Split.mapToList(rules);
            if(show!=null) {
                for (String a : show) {
                    model.addElement(a);//显示默认配置
                }
            }
        }




        JScrollPane jScrollPane = new JScrollPane(list);
        jScrollPane.setPreferredSize(new Dimension(140, 100));
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setAlignmentY(TOP_ALIGNMENT);

        choose.setAlignmentY(TOP_ALIGNMENT);
        choose.addActionListener(this);

        topPanel.add(showJLabel);
        topPanel.add(jScrollPane);
        topPanel.add(Box.createVerticalStrut(1));
        topPanel.add(choose);
        topPanel.setBorder(BorderFactory.createEmptyBorder(80, 10, 80, 10));

        topPanel.setAlignmentY(TOP_ALIGNMENT);

    }

    public void bootom() {
        BoxLayout boxLayout = new BoxLayout(bottompPanel, BoxLayout.X_AXIS);
        bottompPanel.setLayout(boxLayout);

        bottompPanel.add(Box.createHorizontalGlue());
        bottompPanel.add(choosefileButton);
        bottompPanel.add(Box.createHorizontalGlue());
        bottompPanel.add(actionbutton);
        bottompPanel.add(Box.createHorizontalGlue());
        bottompPanel.add(closeButton);
        bottompPanel.add(Box.createHorizontalGlue());

        choosefileButton.addActionListener(this);
        actionbutton.addActionListener(this);
        closeButton.addActionListener(this);
    }

    public void Init() {
        top();
        bootom();

        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        topPanel.setAlignmentX(LEFT_ALIGNMENT);
        bottompPanel.setAlignmentX(LEFT_ALIGNMENT);
        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(topPanel);
        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(bottompPanel);
        jPanel.add(Box.createVerticalStrut(10));

        Container con = getContentPane();
        con.add(jPanel);
        con.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choose) {
            dispose();
            Uploadfile uploadfile = new Uploadfile();
            uploadfile.setVisible(true);
        }


        // TODO Auto-generated method stub
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        if (e.getSource() == choosefileButton)//点击选择文件按钮
        {
            fileChooser.setMultiSelectionEnabled(true);
            int value = fileChooser.showOpenDialog(this);

            selectedFiles = new LinkedList<File>(Arrays.asList(fileChooser.getSelectedFiles()));
            //selectedFile = fileChooser.getSelectedFile();//取出文件路径

            if (value == JFileChooser.APPROVE_OPTION) {
                System.out.println(selectedFiles);
                //将文件路径传送到某函数；
                words = new LinkedList<>();
                for (File file : selectedFiles) {

                    words.add(WordFactory.getWord(file.getAbsolutePath()));
                }
            } else {
                dispose();
            }
        }

        if (e.getSource() == actionbutton) {
            //进行文档匹配

            if (selectedFiles == null) {
                JOptionPane.showMessageDialog(null, "请选择文件", "出错啦", JOptionPane.ERROR_MESSAGE);
            } else {

                if (rules != null) {

                    Map<String, Map<String, Map<String, Map<String, String>>>> results = new HashMap<>(rules.size());
                    for (AbstractWord word : words) {

                        Map<Map<String, String>, Map<WordParagraph, Map<WordRun, String>>> result = WordAnalyzer.check(word, rules);
                        results.put(word.getName(), WordAnalyzer.getCheckResult(result));
                    }
                    WordAnalyzer.showResults(results);
//                    Date date = new Date();
//                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//                    String resultFile = dateFormat.format(date) + "_result.txt";
//                    File file = new File(resultFile);
//                    System.out.println(file.getAbsolutePath());
//                    try {
//                        file.createNewFile();
//                    } catch (IOException e1) {
//                        System.out.println("create result file exception");
//                        e1.printStackTrace();
//                    }
//                    try(PrintStream printStream = new PrintStream(file)) {
//                        for(Map.Entry<String, Map<WordParagraph, Map<WordRun, String>>> word : results.entrySet()) {
//
//                            printStream.println(word.getKey() + ":");
//                            for(Map.Entry<WordParagraph, Map<WordRun, String>> paragraph : word.getValue().entrySet()) {
//
//                                printStream.println("   " + paragraph.getKey().text() + ":");
//                                for(Map.Entry<WordRun, String> run : paragraph.getValue().entrySet()) {
//
//                                    printStream.println("       " + run.getKey().text() + ":   " + run.getValue());
//                                }
//                            }
//                        }
//                    } catch (FileNotFoundException e1) {
//                        e1.printStackTrace();
//                    }
//                    try(FileInputStream fileInputStream = new FileInputStream(file)) {
//                        byte[] bytes = new byte[Math.toIntExact(file.length())];
//                        fileInputStream.read(bytes);
//                        Showresult showresult = new Showresult(new String(bytes));
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
                } else {

                    JOptionPane.showMessageDialog(null, "请选择配置", "出错啦", JOptionPane.ERROR_MESSAGE);
                }
                //检测界面
            }
        }
        if (e.getSource() == closeButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {


            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                try
                {
//                    UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessLookAndFeel");//灰色
                    UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");//浅灰色
                    //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");//蓝色
                    //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceAutumnLookAndFeel");//暖黄色
                    //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceCremeLookAndFeel");//米色
                    //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel");//灰色，椭圆按钮
                    //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel");//深灰色，椭圆按钮


                }
                catch (Exception e)
                {}

                new Homepage().setVisible(true);

            }
        });


    }
}

