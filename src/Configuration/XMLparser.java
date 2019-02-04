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

public class XMLparser {
    private int gridWidth;
    private int gridHeight;
    private String type;
    private String author;
    private String title;
    private int rows;
    private int columns;
    private int probDeath;
    private int probLife;
    private int numDead;
    private int numAlive;

    public void parse(String filename) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(filename+".xml"));
        document.getDocumentElement().normalize();

        NodeList list = document.getDocumentElement().getChildNodes();
            Node node = list.item(0);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element eElement = (Element) node;
                type=eElement.getElementsByTagName("type").item(0).getTextContent();
                title=eElement.getElementsByTagName("title").item(0).getTextContent();
                author=eElement.getElementsByTagName("author").item(0).getTextContent();
                rows=Integer.parseInt(eElement.getAttribute("rows"));
                columns=Integer.parseInt(eElement.getAttribute("columns"));
            }
    }
    public int getRows(){
        return rows;
    }
    public int getCols() {
        return columns;
    }
    public String getAuthor(){
        return author;
    }
    public String getType(){
        return type;
    }
    public String getTitle() {
        return title;
    }
}
