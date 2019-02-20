package dsf.checkWord.xml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ModifyXML {

    private String file;

    public ModifyXML(String file) {

        if(!new File(file).exists()) {

            CreateXML.createXML(file);
        }
        this.file = file;
    }

    public  void addProperty(Map<String, String> map) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(file));


        try {
            //把元素节点名字叫"text"的所有元素全都保存在叫text的节点列表中
            NodeList text = document.getElementsByTagName("text");

            for (int i = 0; i < text.getLength(); i++) {
                //每循环一次就把把名字叫"text"的节点元素的所有子节点都更新保存在叫properties的节点列表中
                NodeList properties = text.item(i).getChildNodes();
                // System.out.println(properties.item(0).getTextContent());


                if(properties.item(0).getTextContent().equals(map.get("标题级数"))) {

                    properties.item(3).setTextContent(map.get("字号"));
                    properties.item(4).setTextContent(map.get("字体"));
                    properties.item(5).setTextContent(map.get("颜色"));

                    TransformerFactory tFactory = TransformerFactory.newInstance();
                    Transformer t = tFactory.newTransformer();
                    DOMSource source = new DOMSource(document);
                    File file = new File(this.file);
                    StreamResult result = new StreamResult(file);
                    t.transform(source, result);
                    break;
                }
            }
        }catch (Exception e) {
            //System.out.println("1");
            e.printStackTrace();
        }
    }

    public void deleteProperty(Map<String, String> map) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(this.file));

        try {

            NodeList text = document.getElementsByTagName("text");

            for (int i = 0; i < text.getLength(); i++) {
                NodeList properties = text.item(i).getChildNodes();
                System.out.println(properties.item(0).getTextContent());


                if(properties.item(0).getTextContent().equals(map.get("标题级数"))) {

                    properties.item(3).setTextContent("");
                    properties.item(4).setTextContent(map.get(""));
                    properties.item(5).setTextContent(map.get(""));

                    TransformerFactory tFactory = TransformerFactory.newInstance();
                    Transformer t = tFactory.newTransformer();
                    DOMSource source = new DOMSource(document);
                    File file = new File(this.file);
                    StreamResult result = new StreamResult(file);
                    t.transform(source, result);
                    break;
                }
            }
        }catch (Exception e) {
            System.out.println("1");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException  {

        ModifyXML modify = new ModifyXML("F:\\idea\\checkWord\\测试文件\\xml\\createXML.xml");
        Map<String, String> map = new HashMap<String, String>();
        map.put("标题级数", "0级");
        map.put("字号", "四号");
        map.put("字体", "微软雅黑");
        map.put("颜色", "黑色");

        modify.addProperty(map);
        //modify.deleteProperty(map);

    }
}
