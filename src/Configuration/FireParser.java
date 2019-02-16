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
/*
@author - Hyunjae Lee
This class is a parser for parsing the parameters of the spreading fire simulation
 */
public class FireParser extends Parser{
    private double prob;
    private int numTree;
    private int numBurn;
    /*
    @param - filename
    Method that parses and sets instance variable params for spreading fire.
     */
    public void fireParse(String filename) throws BadFileInputException{

//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new File(filename + ".xml"));
//            document.getDocumentElement().normalize();

            NodeList myList=getDocument(filename).getElementsByTagName("probability");
            Node gameNode=myList.item(0);
            if(gameNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement = (Element) gameNode;
                prob =Double.parseDouble(eElement.getElementsByTagName("prob").item(0).getTextContent());
                numTree=Integer.parseInt(eElement.getElementsByTagName("numTree").item(0).getTextContent());
                numBurn=Integer.parseInt(eElement.getElementsByTagName("numBurn").item(0).getTextContent());
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
    /*
    Gets probability of burning
     */
    public double getProb(){
        return prob;
    }
    /*
    Gets number of trees
     */
    public int getNumTree(){
        return numTree;
    }
    /*
    Gets number of burning cells
     */
    public int getNumBurn(){
        return numBurn;
    }
}
