package dsf.checkWord.xml;

import java.awt.Window;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlConfig {

    private static final String DEFAULT_XML = "F:\\idea\\checkWord\\测试文件\\xml\\createXML.xml";

    private static final String CONFIG_PATH = "F:\\idea\\checkWord\\测试文件\\xml\\";

    public static Map<String, Map<String, String >> getDefaultConfig() {
        //获取默认配置
        return XmlUtil.getXmlConfig(DEFAULT_XML);
    }

    public static  void setDefaultConfig(String fileName){
        //设置默认配置

        Map<String, Map<String, String >> map = XmlUtil.getXmlConfig(CONFIG_PATH+fileName);
        XmlConfig.addConfig(DEFAULT_XML,map);

    }


    public static Map<String, Map<String, Map<String, String>>> getAllConfig() {
        //获取所有配置文件中的所有内容
        File dir = new File(CONFIG_PATH);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".xml"));
        if(files == null || files.length == 0) {

            return null;
        }
        Map<String, Map<String, Map<String, String>>> map = new HashMap<>(files.length);
        for(File file : files) {

            map.put(file.getName(), XmlUtil.getXmlConfig(file.getAbsolutePath()));
        }
        return map;
    }

    /*public static Map<String, Map<String, String >> getXmlConfig(String file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        File f = new File(file);

        Map<String, Map<String, String >> map = new HashMap<String, Map<String,String>>();
        Map<String, String> config = new HashMap<String, String>();


        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(f);
            NodeList text = document.getElementsByTagName("text");
            for (int i = 0; i < text.getLength(); i++) {
                NodeList properties = text.item(i).getChildNodes();

                if (properties.item(4).getTextContent()!= "") {
                    config.put("字号", properties.item(4).getTextContent());
                    config.put("字体", properties.item(5).getTextContent());
                    config.put("颜色", properties.item(6).getTextContent());
                    map.put(properties.item(1).getTextContent(), config);
                    System.out.println(map.get(i+"级"));
                }

            }

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (SAXException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return map;

    }*/

    public static void addConfig(String file, Map<String, Map<String, String>> map) {
        XmlUtil.createXML(file);
        Set<Map.Entry<String, Map<String, String>>> entries = map.entrySet();
        for(Map.Entry<String, Map<String, String>> map2 : entries) {
            //此时map2.getValue()得到的map映射不存在“标题级别”这个键值对，
            // 但调用XmlUtil类中的modify方法必须用“标题级别”来判断是否要给改标题级别添加属性
            Map<String,String> subMap =map2.getValue();
            subMap.put("标题级别",map2.getKey());
            XmlUtil.modify(file, subMap);
        }
    }



    public static void addConfig(Map<String, Map<String, String>> map) {

        String defaultPath = "afas";
        addConfig(defaultPath, map);
    }


    public static void main(String[] args) {
        XmlConfig aConfig = new XmlConfig();
        //XmlUtil.createXML("C:\\Users\\507\\Desktop\\检测word文档项目\\checkWord\\测试文件\\xml\\createXML02.xml");
        //Map<String, Map<String, String >> q= XmlUtil.getXmlConfig("C:\\Users\\507\\Desktop\\检测word文档项目\\checkWord\\测试文件\\xml\\createXML.xml");
        //System.out.println(q);

//        Map<String, Map<String, String >> map = aConfig.getDefaultConfig();
//        System.out.println(map.get("0级"));

//        Map<String, Map<String, String>> defaultConfig = getDefaultConfig();
//        System.out.println(defaultConfig.get("1级"));


    }

}