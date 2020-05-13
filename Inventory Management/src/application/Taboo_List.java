package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Taboo_List {

	
	private ArrayList<String> taboo_word = new ArrayList<String>();
	
	
	public Taboo_List () {
		try {
			File fXmlFile = new File("@../../src/Database/Taboo_Lists.xml");
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
	        doc.getDocumentElement().normalize();
	        NodeList nList=  doc.getElementsByTagName("word");
	        for (int i = 0; i < nList.getLength(); i++) {	    	
	        	taboo_word.add(nList.item(i).getTextContent());	
	    		
	    		}
	      
	       
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public ArrayList<String> getTaboo_word() {
		return taboo_word;
	}


	public void setTaboo_word(ArrayList<String> taboo_word) {
		this.taboo_word = taboo_word;
	}
	
	public String taboo_check(String text) {
		String bad = text;
		
		for(int i1 = 0; i1 < taboo_word.size(); i1++) {
				
    			if(bad.contains(taboo_word.get(i1)))
    			{
    				bad = (bad.replaceAll(taboo_word.get(i1), "***"));
    			}
		}
		
		return bad;
	}
}
