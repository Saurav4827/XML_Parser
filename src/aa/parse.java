package aa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class parse {
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException 
	{
	File fXmlFile = new File("xmlfile.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	NodeList node=doc.getElementsByTagName("testsuite");
	for(int i=0;i<node.getLength();i++)
	{
	       Node node1= node.item(i);
	       System.out.println(node1.getNodeName());
	       Element el= (Element) node1;
	
	       String fail= el.getAttribute("failures");
	       int fail_Test= Integer.parseInt(fail);
	       String total=el.getAttribute("tests");
	       int total_Test=Integer.parseInt(total);
	
	       int passed=total_Test-fail_Test;
	       String passed_Test= Integer.toString(passed);
	       System.out.println("total test " + total_Test);
	       System.out.println("passed test" + passed_Test);
	       System.out.println("failed test " + fail_Test);
	       
	       Properties p=new Properties();
	       FileWriter writer= new FileWriter("abc.properties");
	   	   p.setProperty("total_Test", total);
	   	   p.setProperty("passed_Test",passed_Test);
	   	   p.setProperty("failed_Test", fail);
	   	   p.store(writer, "Test_Summary");
	   	  
	}
	}
}

