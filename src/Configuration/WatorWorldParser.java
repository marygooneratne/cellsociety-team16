package Configuration;
import org.w3c.dom.*;
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
public class WatorWorldParser extends XMLparser{
    double probFish;
    double probEmpty;
    int fishTime;
    int sharkTime;
    int starveTime;
    public void parseWator(String filename) {
        Node watorNode = list.item(1);
        if(watorNode.getNodeType()==Node.ELEMENT_NODE){
            Element myEl=(Element) watorNode;
            probFish=Integer.parseInt(myEl.getElementsByTagName("probFish").item(0).getTextContent());
            probEmpty=Integer.parseInt(myEl.getElementsByTagName("probEmpty").item(0).getTextContent());
        }
        Node watorNode1 = list.item(2);
        if(watorNode1.getNodeType()==Node.ELEMENT_NODE){
            Element elem = (Element) watorNode1;
            fishTime=Integer.parseInt(elem.getElementsByTagName("fishTime").item(0).getTextContent());
            sharkTime=Integer.parseInt(elem.getElementsByTagName("sharkTime").item(0).getTextContent());
            starveTime=Integer.parseInt(elem.getElementsByTagName("starveTime").item(0).getTextContent());

        }

    }
    public double getProbFish(){
        return probFish;
    }
    public double getProbEmpty(){
        return probEmpty;
    }
    public int getFishTime(){
        return fishTime;
    }
    public int getSharkTime(){
        return sharkTime;
    }
    public int getStarveTime(){
        return starveTime;
    }
}
