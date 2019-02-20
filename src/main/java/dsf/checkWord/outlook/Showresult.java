package dsf.checkWord.outlook;

import java.awt.Container;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class Showresult extends JFrame
{
    public Showresult(String text)
    {
        this.setTitle("检测结果");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//窗体关闭时的操作 退出程序
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.NORMAL);//最小化
        this.setResizable(false);//禁用最大化按钮
        this.setVisible(true);
        Init(text);
    }
    public void Init(String text)
    {
        JPanel jPanel=new JPanel();

        JTextArea jTextArea=new JTextArea();
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jTextArea.setText(text);
        jTextArea.setLineWrap(true);
        jScrollPane.setPreferredSize(new Dimension(350, 350));
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        jScrollPane.setAlignmentY(TOP_ALIGNMENT );

        jPanel.add(jScrollPane);
        Container con = getContentPane();
        con.add(jPanel);
        con.setVisible(true);
    }
}
