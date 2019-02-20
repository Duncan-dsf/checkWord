package dsf.checkWord.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;



public class CreateXML {




	public static String createXML(String fileName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String xmlStr = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			document.setXmlVersion("1.0");

			Element word_doc = document.createElement("word_doc");
			document.appendChild(word_doc);

			for(int i=0;i<10;i++) {
				Element text =document.createElement("text");
				word_doc.appendChild(text);

				Element title =document.createElement("title");
				title.setTextContent(i+"级");
				//System.out.println(title.getNodeName().getClass());
				text.appendChild(title);

				Element title_id = document.createElement("title_id");
				title_id.setTextContent(""+i);
				text.appendChild(title_id);

				Element parent = document.createElement("parent");
				text.appendChild(parent);

				Element font_size = document.createElement("font_size");
				text.appendChild(font_size);

				Element font_family = document.createElement("font_family");
				text.appendChild(font_family);

				Element color = document.createElement("color");
				text.appendChild(color);

				Element bold = document.createElement("bold");
				text.appendChild(bold);

				Element italic = document.createElement("italic");
				text.appendChild(italic);

				Element line_spacing = document.createElement("line_spacing");
				text.appendChild(line_spacing);

				Element text_center = document.createElement("text_center");
				text.appendChild(text_center);

				Element text_indent = document.createElement("text_indent");
				text.appendChild(text_indent);

				Element alignment = document.createElement("alignment");
				text.appendChild(alignment);


			}
			//更新xml文件
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //构建输入源
            DOMSource domSource = new DOMSource(document);

			File file = new File(fileName);
			if(!file.exists()) {
				file.createNewFile();
			}


			 ByteArrayOutputStream bos = new ByteArrayOutputStream();
             transformer.transform(domSource, new StreamResult(bos));
             xmlStr = bos.toString();

             //FileOutputStream  out = new FileOutputStream(file);
             //构建输入源
            // StreamResult xmlresult= new StreamResult(out);
             StreamResult xmlresult= new StreamResult(file);
             transformer.transform(domSource, xmlresult);

			//System.out.println(xmlStr);


		    }catch (ParserConfigurationException e) {
		    	System.out.println("4");
				e.printStackTrace();
			}catch (TransformerConfigurationException e) {
				System.out.println("5");
				e.printStackTrace();
			}catch (TransformerException e) {
				System.out.println("6");
				e.printStackTrace();

			}catch (IOException e) {
				System.out.println("7");
				e.printStackTrace();
			}



		return xmlStr;

	}

	/*public void parserXML(String strXML){
		//得到解析器
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			StringReader sr = new StringReader(strXML);
			InputSource is = new  InputSource(sr);
			//通过DocumentBuilder对象的parse()方法，把之前创建的xml文件加载到当前项目下
			//通过解析器可以得到代表整个内存中xml的Document对象
			Document doc = builder.parse(is);
			//获取xml文件的根节点
			Element rootelement = doc.getDocumentElement();
			//获取所有text节点的集合
			NodeList text = rootelement.getElementsByTagName("text");
			//遍历每一个text节点
			for(int i=0;i<text.getLength();i++) {
				Node textSep = text.item(i);
				NodeList properties = textSep.getChildNodes();
				//遍历properties中的每一个节点
				for(int j=0;j<properties.getLength();j++) {
					Node property = properties.item(j);
					String str =property.getNodeName();
					 System.out.println(str);
					if(str.equals("title")) {
					 String	title = property.getTextContent();
					 System.out.println(title);
					}

				}


			}


		}catch (ParserConfigurationException e) {
			System.out.println("1");
			e.printStackTrace();

		}catch (SAXException e) {
			System.out.println("2");
			e.printStackTrace();

		}catch (IOException e) {
			System.out.println("3");
			e.printStackTrace();

		}

	}
	*/
	public static void main(String[] args) {
//		CreateXML handler = new CreateXML();
//		String xml = handler.createXML("F:\\idea\\checkWord\\测试文件\\xml\\createXML.xml");
//		System.out.println(xml);
		//handler.parserXML(xml);

	}
}
