package zhiman.ustc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class LogPrinter {
	private static Element root;

	public static void printXmlLog(String logFileName,String elememtName, String logInfo) {
		String p = LogPrinter.class.getClassLoader().getResource("").getPath();
		String str = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(str+"*****************"+p);
		String absolutePath = str + logFileName;
		File file = new File(absolutePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String path = file.getAbsolutePath();
		System.out.println(path);
		writeLogToXml(file,elememtName, logInfo);
	}

	private static void writeLogToXml(File file, String elememtName, String logInfo) {

		Document document = null;
		// flag = false ˵���ļ���������
		boolean flag = false;
		try {
			document = new SAXReader().read(file);
		} catch (DocumentException e1) {
			// �����쳣����Ϊlog.xmlû��element
			if (document == null) {
				// 1.�������ڵ�<log></log>
				root = DocumentHelper.createElement("log");
				document = DocumentHelper.createDocument(root);
				// Ϊtrue��˵�����´����ļ�
				flag = true;
			}
		}

		if (flag) {
			creatNewDocument(document, elememtName,file, logInfo);
			System.out.println("creatNewDocument");
		} else {
			root = document.getRootElement();
			Element element = root.element("action");
			appendElement(document, element, elememtName, file, logInfo);
		}
	}

	private static void creatNewDocument(Document document, String elementNmae,File file, String logInfo) {
		// 2.���ڵ�<log></log>�����<action></action>�ڵ�
		Element actionElement = root.addElement("action");
		Element currentElement = actionElement.addElement(elementNmae);
		currentElement.addText(logInfo);
		writeXml(document, file);
	}

	private static void appendElement(Document document, Element parentElement, 
								String elementNmae, File file,String logInfo) {
		Element currentElement = parentElement.addElement(elementNmae);
		currentElement.setText(logInfo);
		writeXml(document, file);
	}

	private static void writeXml(Document document, File file) {
		// 3���������ʽ ���в�����tab����
//		OutputFormat format = new OutputFormat();
//		format.setIndent(true);
//		format.setNewlines(true);
		OutputFormat format = OutputFormat.createPrettyPrint();// ��ʽ  
	    format.setEncoding(document.getXMLEncoding());  
	    //��������¿�������
	    format.setNewLineAfterDeclaration(false);  
		// 4.���ñ����ʽ
		format.setEncoding("utf-8");
		XMLWriter xmlWriter = null;
		try {
			xmlWriter = new XMLWriter(new FileOutputStream(file), format);
			xmlWriter.write(document);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (xmlWriter != null) {
				try {
					xmlWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
