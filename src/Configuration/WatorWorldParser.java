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
//public class WatorWorldParser extends XMLparser{
//    double probFish;
//    double probEmpty;
//    int fishTime;
//    int sharkTime;
//    int starveTime;
//    public void parseWator(String filename,NodeList nlist) {
//        Node watorNode = nlist.item(1);
//        if(watorNode.getNodeType()==Node.ELEMENT_NODE){
//            Element myEl=(Element) watorNode;
//            probFish=Integer.parseInt(myEl.getElementsByTagName("probFish").item(0).getTextContent());
//            probEmpty=Integer.parseInt(myEl.getElementsByTagName("probEmpty").item(0).getTextContent());
//        }
//        Node watorNode1 = nlist.item(2);
//        if(watorNode1.getNodeType()==Node.ELEMENT_NODE){
//            Element elem = (Element) watorNode1;
//            fishTime=Integer.parseInt(elem.getElementsByTagName("fishTime").item(0).getTextContent());
//            sharkTime=Integer.parseInt(elem.getElementsByTagName("sharkTime").item(0).getTextContent());
//            starveTime=Integer.parseInt(elem.getElementsByTagName("starveTime").item(0).getTextContent());
//
//        }
//
//    }
//    public double getProbFish(){
//        return probFish;
//    }
//    public double getProbEmpty(){
//        return probEmpty;
//    }
//    public int getFishTime(){
//        return fishTime;
//    }
//    public int getSharkTime(){
//        return sharkTime;
//    }
//    public int getStarveTime(){
//        return starveTime;
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
@author - Hyunjae Lee
Class for parser for WatorWorld Simulation
 */
public class WatorWorldParser extends Parser{
    double probFish;
    double probEmpty;
    int fishTime;
    int sharkTime;
    int starveTime;
    /*
    Method for parsing xml file for watorworld simulation
     */
    public void parseWator(String filename,NodeList nlist) throws BadFileInputException{

//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new File(filename + ".xml"));
//            document.getDocumentElement().normalize();

            NodeList myList=getDocument(filename).getElementsByTagName("probability");
            Node gameNode=myList.item(0);
            if(gameNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement = (Element) gameNode;
                probFish =Double.parseDouble(eElement.getElementsByTagName("probFish").item(0).getTextContent());
                probEmpty=Double.parseDouble(eElement.getElementsByTagName("probEmpty").item(0).getTextContent());
            }
            NodeList myList1=getDocument(filename).getElementsByTagName("time");
            Node myNode=myList1.item(0);
            if(myNode.getNodeType()==Node.ELEMENT_NODE){
                Element myElement = (Element) myNode;
                fishTime=Integer.parseInt(myElement.getElementsByTagName("fishTime").item(0).getTextContent());
                sharkTime=Integer.parseInt(myElement.getElementsByTagName("sharkTime").item(0).getTextContent());
                starveTime=Integer.parseInt(myElement.getElementsByTagName("starveTime").item(0).getTextContent());
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
//        Node watorNode = nlist.item(1);
//        if(watorNode.getNodeType()==Node.ELEMENT_NODE){
//            Element myEl=(Element) watorNode;
//            probFish=Integer.parseInt(myEl.getElementsByTagName("probFish").item(0).getTextContent());
//            probEmpty=Integer.parseInt(myEl.getElementsByTagName("probEmpty").item(0).getTextContent());
//        }
//        Node watorNode1 = nlist.item(2);
//        if(watorNode1.getNodeType()==Node.ELEMENT_NODE){
//            Element elem = (Element) watorNode1;
//            fishTime=Integer.parseInt(elem.getElementsByTagName("fishTime").item(0).getTextContent());
//            sharkTime=Integer.parseInt(elem.getElementsByTagName("sharkTime").item(0).getTextContent());
//            starveTime=Integer.parseInt(elem.getElementsByTagName("starveTime").item(0).getTextContent());
//
//        }

    }
    /*
    Returns probability that cell is fish
     */
    public double getProbFish(){
        return probFish;
    }
    /*
    Returns probability cell is empty
     */
    public double getProbEmpty(){
        return probEmpty;
    }
    /*
    Returns time for fish cycle
     */
    public int getFishTime(){
        return fishTime;
    }
    /*
    Returns time for shark cycle
     */
    public int getSharkTime(){
        return sharkTime;
    }
    /*
    Returns time for starvation
     */
    public int getStarveTime(){
        return starveTime;
    }
}
