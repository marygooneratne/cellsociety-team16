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

public class SegregationParser extends XMLparser{
    double probEmpty;
    double probRed;
    double thresh;

    public void segParse(String filename){
        Node segNode = list.item(1);
        if(segNode.getNodeType()==Node.ELEMENT_NODE){
            Element myEl= (Element) segNode;
            probEmpty=Integer.parseInt(myEl.getElementsByTagName("probEmpty").item(0).getTextContent());
            probRed=Integer.parseInt(myEl.getElementsByTagName("probRed").item(0).getTextContent());
            thresh=Integer.parseInt(myEl.getElementsByTagName("threshold").item(0).getTextContent());
        }

    }
    public double getProbEmpty(){
        return probEmpty;
    }
    public double getProbRed(){
        return probRed;
    }
    public double getThresh(){
        return thresh;
    }
}
