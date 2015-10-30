import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class TutorialText {
	public static String getTutorialText(String gameName){
		String result = "";
		try{
			File fXmlFile = new File("tutorial/tutorialData.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = (Document) dBuilder.parse(fXmlFile);
			
			String temp = doc.getElementsByTagName(gameName).item(0).getTextContent();
			String[] parts = temp.split("\n");
			for(String p:parts){
				result += p.trim();
				result += "<br/>";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "<html><div align=\"center\">" + result + "</div></html>";
	}
}
