package dsf.checkWord.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXWriter;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author 董少飞
 * @date 2018/11/2
 */
public class XmlUtil {


    public static void createXML(String fileName) {
        //创建一个新的（只有配置文件结构，没有具体的参数）的配置文件
        //主动创建document对象
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("word_doc");

        for(int i=0; i<10; i++) {

            Element text = root.addElement("text");
            Element title = text.addElement("title");
            title.addText(i+"级");

            Element titleId = text.addElement("title_id");
            titleId.addText(i+"");

            text.addElement("parent");
            text.addElement("font_size");
            text.addElement("font_family");
            text.addElement("color");
            text.addElement("bold");
            text.addElement("italic");
            text.addElement("line_spacing");
            text.addElement("text_center");
            text.addElement("text_indent");
            text.addElement("alignment");
        }

        try {
            writeXml(fileName, document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Map<String, String>> getXmlConfig(String fileName) {
        //获取一个xml配置文件的所有信息
        Map<String, Map<String, String >> map = new HashMap<>(8);
        try {
            //通过readXml方法读取一个文件，将其转换成Document对象
            Document document = XmlUtil.readXml(fileName);
            Element rootElement = document.getRootElement();

            Iterator<Element> iterator = rootElement.elementIterator();
            while(iterator.hasNext()) {//遍历根节点下的所有text节点

                Element element = iterator.next();
                Iterator<Element> subIterator = element.elementIterator();
                while(subIterator.hasNext()){//遍历text节点下的所有子节点
                    //返回迭代器中下一个元素，让while循环有一个终止条件
                    subIterator.next();
                    String fontFamily = element.element("font_family").getText();
                    if(fontFamily == null || "".equals(fontFamily.trim())) {
                        break;//如果某一个text节点下fontFamily节点中没有内容，说明这一整个text节点在文档节点中没有被赋予内容，然后跳出这一整个text节点的循环
                    }
                    Map<String, String> detail = new HashMap<>(8);

                    String title = element.element("title").getText();
                    String fontSize = element.element("font_size").getText();
                    String color = element.element("color").getText();

                    /*String parent = element.element("parent").getText();
                    String blod = element.element("bold").getText();
                    String italic = element.element("italic").getText();
                    String lineSpacing = element.element("line_spacing").getText();
                    String textCenter = element.element("text_center").getText();
                    String alignment = element.element("alignment").getText();*/

                    detail.put("字号", fontSize);
                    detail.put("字体", fontFamily);
                    detail.put("颜色", color);

                    /*detail.put("父标题级数", parent);
                    detail.put("是否加粗", blod);
                    detail.put("是否斜体", italic);
                    detail.put("行间距", lineSpacing);
                    detail.put("是否居中", textCenter);
                    detail.put("对齐方式", alignment);*/

                    map.put(title, detail);//用Map存储数据不像数组一样（存储是有序的），它是无序的
                }


            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void modify(String fileName, Map<String, String> map) {
        try {
            //通过readXml方法读取一个文件，将其转换成Document对象
            Document document = XmlUtil.readXml(fileName);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for(Element element : elements) {

                if(element.element("title").getText().equals(map.get("标题级别"))) {

                    element.element("font_size").setText(map.get("字号"));
                    element.element("font_family").setText(map.get("字体"));
                    element.element("color").setText(map.get("颜色"));

                    /*element.element("parent").setText(map.get("父标题级数"));
                    element.element("bold").setText(map.get("是否加粗"));
                    element.element("italic").setText(map.get("是否斜体"));
                    element.element("line_spacing").setText(map.get("行间距"));
                    element.element("text_center").setText(map.get("是否居中"));
                    element.element("alignment").setText(map.get("对齐方式"));*/

                    break;
                }
            }
            writeXml(fileName, document);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPorperty(String file, Map<String, String> map) {
        //添加一个<text></text>中的所有属性
        modify(file, map);
    }

    public static Document readXml(String fileName) throws DocumentException {
        //通过read方法读取一个文件，将其转换成Document对象
        SAXReader reader = new SAXReader();
        File file = new File(fileName);
        Document document = reader.read(file);
        if(!file.exists()) {

            createXML(fileName);
        }
        return document;
    }

    public static void writeXml(String fileName, Document document) throws Exception {
        //将文档写入xml文件
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        //排版缩进的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //创建XMLWriter对象，指定了写出文件及编码格式
        XMLWriter xmlWriter = new XMLWriter(fileOutputStream, format);
        //写入
        xmlWriter.write(document);

    }

    public static void swap(String defaultXml, String file) {
        //把两个配置文件中的所有内容互换，主要作用是把后来创建的配置文件更改成默认配置文件
        Map<String, Map<String, String >> map1 = XmlUtil.getXmlConfig(defaultXml);
        Map<String, Map<String, String >> map2 = XmlUtil.getXmlConfig(file);
        XmlConfig.addConfig(defaultXml,map2);
        XmlConfig.addConfig(file,map1);


    }
    public static void main(String[] args) {
//        创建一个新的xml配置文件
//        XmlUtil.createXML("C:\\Users\\507\\Desktop\\检测word文档项目\\checkWord\\测试文件\\xml\\createXML02.xml");

//        获取一个xml配置文件的所有信息
        //Map<String,Map<String,String>> q = XmlUtil.getXmlConfig("C:\\Users\\507\\Desktop\\检测word文档项目\\checkWord\\测试文件\\xml\\createXML.xml");
        //System.out.println(q);

        Map<String,String> map = new HashMap<>();
        map.put("标题级别","3级");
        map.put("字体","微软雅黑");
        map.put("颜色","红色");
        map.put("字号","小四");
        XmlUtil.modify("C:\\Users\\507\\Desktop\\检测word文档项目\\checkWord\\测试文件\\xml\\createXML.xml",map);

        //  XmlUtil.addPorperty("C:\\Users\\507\\Desktop\\检测word文档项目\\checkWord\\测试文件\\xml\\createXML.xml",map);

        //       Map<String, Map<String, String >> map = XmlConfig.getDefaultConfig();
        //       System.out.println(map);

        XmlUtil.swap("C:\\Users\\507\\Desktop\\检测word文档项目\\checkWord\\测试文件\\xml\\createXML.xml","C:\\Users\\507\\Desktop\\检测word文档项目\\checkWord\\测试文件\\xml\\createXML02.xml");

//        Map<String, Map<String, String>> defaultConfig = getDefaultConfig();
//        System.out.println(defaultConfig.get("1级"));


    }
}
