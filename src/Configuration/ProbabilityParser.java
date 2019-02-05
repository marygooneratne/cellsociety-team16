package Configuration;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ProbabilityParser extends XMLparser{
    private double probPop;
    public void parseGame(String filename,NodeList nlist) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filename + ".xml"));
            document.getDocumentElement().normalize();
            NodeList myList=document.getElementsByTagName("probability");
            Node gameNode=myList.item(0);
            if(gameNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement = (Element) gameNode;
                probPop =Double.parseDouble(eElement.getElementsByTagName("prob").item(0).getTextContent());

            }
        }
        catch(ParserConfigurationException e){
            e.printStackTrace();
        }
        catch(SAXException e){
            e.printStackTrace();

        }
        catch(IOException i){
            i.printStackTrace();
        }

//        document.getDocumentElement().normalize();
//        NodeList myList = document.getDocumentElement().getElementsByTagName("probability");
//        Node gameNode = myList.item(0);
//        if(gameNode.getNodeType()==Node.ELEMENT_NODE) {
//            Element eElement = (Element) gameNode;
//            probPop = Integer.parseInt(eElement.getElementsByTagName("prob").item(0).getTextContent());
//
//        }
    }
    public double getProbPop(){
        return probPop;
    }
}
