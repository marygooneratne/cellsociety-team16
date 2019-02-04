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

public class ProbabilityParser extends XMLparser{
    private double probPop;
    public void parseGame(String filename) {
        Node gameNode = list.item(1);
        if(gameNode.getNodeType()==Node.ELEMENT_NODE){
            Element eElement = (Element) gameNode;
            probPop=Integer.parseInt(eElement.getElementsByTagName("prob").item(0).getTextContent());

        }
    }
    public double getProbPop(){
        return probPop;
    }
}
