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

public class PercolationParser extends Parser{
    double prob;
    int numPerc;
    public void percParse(String filename) {

//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new File(filename + ".xml"));
//            document.getDocumentElement().normalize();

            NodeList myList=getDocument(filename).getElementsByTagName("probability");
            Node gameNode=myList.item(0);
            if(gameNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement = (Element) gameNode;
                prob =Double.parseDouble(eElement.getElementsByTagName("prob").item(0).getTextContent());
                numPerc=Integer.parseInt(eElement.getElementsByTagName("numPerc").item(0).getTextContent());
            }

//        catch(ParserConfigurationException e){
//            e.printStackTrace();
//        }
//        catch(SAXException e){
//            e.printStackTrace();
//
//        }
//        catch(IOException i){
//            i.printStackTrace();
//        }
    }

    public int getNumPerc(){
        return numPerc;
    }
    public double getProb(){
        return prob;
    }
}
