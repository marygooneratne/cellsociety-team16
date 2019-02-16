package Configuration;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
/*
@author-Hyunjae Lee
Parser for Game Of Life
 */
public class ProbabilityParser extends Parser{
    private double probPop;
    /*
    Method that parses game of life xml file
     */
    public void parseGame(String filename,NodeList nlist) throws BadFileInputException{

//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new File(filename + ".xml"));
//            document.getDocumentElement().normalize();

            NodeList myList=getDocument(filename).getElementsByTagName("probability");

            Node gameNode=myList.item(0);
            if(gameNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement = (Element) gameNode;
                probPop =Double.parseDouble(eElement.getElementsByTagName("prob").item(0).getTextContent());

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

//        document.getDocumentElement().normalize();
//        NodeList myList = document.getDocumentElement().getElementsByTagName("probability");
//        Node gameNode = myList.item(0);
//        if(gameNode.getNodeType()==Node.ELEMENT_NODE) {
//            Element eElement = (Element) gameNode;
//            probPop = Integer.parseInt(eElement.getElementsByTagName("prob").item(0).getTextContent());
//
//        }
    }
    /*
    Gets probability that cell is populated
     */
    public double getProbPop(){
        return probPop;
    }
}
