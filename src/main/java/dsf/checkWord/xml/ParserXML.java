package dsf.checkWord.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ParserXML {

    private String file;

    public ParserXML(String file) {

        this.file = file;
    }

    public String getFont_size(String string) {//形参string表示标题的级数
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String str = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File f = new File(file);
            Document document = builder.parse(f);
            NodeList title = document.getElementsByTagName("title");

            for (int i = 0; i < title.getLength(); i++) {
                if (title.item(i).getTextContent().equals(string)) {
                    str = title.item(i).getParentNode().getChildNodes().item(3).getTextContent();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    public String getFont_family(String string) {//获取标题级数对应的字体，形参string表示标题的级数
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String str = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File f = new File(file);
            Document document = builder.parse(f);
            NodeList title = document.getElementsByTagName("title");

            for (int i = 0; i < title.getLength(); i++) {
                if (title.item(i).getTextContent().equals(string)) {
                    str = title.item(i).getParentNode().getChildNodes().item(4).getTextContent();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getColor(String string) {//获取标题级数对应的字体，形参string表示标题的级数
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String str = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File f = new File(file);
            Document document = builder.parse(f);
            NodeList title = document.getElementsByTagName("title");

            for (int i = 0; i < title.getLength(); i++) {
                if (title.item(i).getTextContent().equals(string)) {
                    str = title.item(i).getParentNode().getChildNodes().item(5).getTextContent();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    Map<String, Map<String, String>> getConfig() {

        return null;
    }

    Map<String, String> getRule(String level) {

        Map<String, String> map = new HashMap<>();
        return null;
    }

    public static void main(String[] args) {
        ParserXML parserXML = new ParserXML("");
        System.out.println(parserXML.getFont_size("0级"));//返回字号
        System.out.println(parserXML.getFont_family("0级"));
        System.out.println(parserXML.getColor("0级"));

    }

}
