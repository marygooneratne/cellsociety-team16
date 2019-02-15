//package Configuration;
//import org.w3c.dom.*;
//import javax.xml.parsers.*;
//import java.io.*;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.File;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//public class SegregationParser extends XMLparser{
//    double probEmpty;
//    double probRed;
//    double thresh;
//
//    public void segParse(String filename, NodeList nlist){
//        Node segNode = nlist.item(1);
//        if(segNode.getNodeType()==Node.ELEMENT_NODE){
//            Element myEl= (Element) segNode;
//            probEmpty=Integer.parseInt(myEl.getElementsByTagName("probEmpty").item(0).getTextContent());
//            probRed=Integer.parseInt(myEl.getElementsByTagName("probRed").item(0).getTextContent());
//            thresh=Integer.parseInt(myEl.getElementsByTagName("threshold").item(0).getTextContent());
//        }
//
//    }
//    public double getProbEmpty(){
//        return probEmpty;
//    }
//    public double getProbRed(){
//        return probRed;
//    }
//    public double getThresh(){
//        return thresh;
//    }
//}
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
Parser for Segregation simulation
 */
public class SegregationParser extends Parser{
    double probEmpty;
    double probRed;
    double thresh;
    /*
    @param-filename
    @param-nlist
    Method that parses xml file for segregation
     */
    public void segParse(String filename, NodeList nlist) throws BadFileInputException{

//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new File(filename + ".xml"));
//            document.getDocumentElement().normalize();

            NodeList myList=getDocument(filename).getElementsByTagName("probability");
            Node gameNode=myList.item(0);
            if(gameNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement = (Element) gameNode;
                probRed = Double.parseDouble(eElement.getElementsByTagName("probRed").item(0).getTextContent());
                probEmpty = Double.parseDouble(eElement.getElementsByTagName("probEmpty").item(0).getTextContent());
                thresh=Double.parseDouble(eElement.getElementsByTagName("threshold").item(0).getTextContent());
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
//        Node segNode = nlist.item(1);
//        if(segNode.getNodeType()==Node.ELEMENT_NODE){
//            Element myEl= (Element) segNode;
//            probEmpty=Integer.parseInt(myEl.getElementsByTagName("probEmpty").item(0).getTextContent());
//            probRed=Integer.parseInt(myEl.getElementsByTagName("probRed").item(0).getTextContent());
//            thresh=Integer.parseInt(myEl.getElementsByTagName("threshold").item(0).getTextContent());
//        }

    }
    /*
    Returns probability cell is empty
     */
    public double getProbEmpty(){
        return probEmpty;
    }
    /*
    Returns probability that cell is red
     */
    public double getProbRed(){
        return probRed;
    }
    /*
    Returns threshold for segregation
     */
    public double getThresh(){
        return thresh;
    }
}
