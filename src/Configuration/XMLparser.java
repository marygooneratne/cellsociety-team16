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
Class for parser that parses common elements for all xml documents like author, rows, columns, simulation type.
 */
public class XMLparser extends Parser{
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
    private NodeList list;

    /*
    @param-filename
    Method that parses any xml document for the rows, columns, author, simulation type.
     */
    public void parse(String filename) throws BadFileInputException{
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            try {
//                Document document = builder.parse(new File(filename + ".xml"));
//                document.getDocumentElement().normalize();
//
//                list = document.getDocumentElement().getElementsByTagName("Simulation");
//            }
//            catch(SAXException e){
//                throw new BadFileInputException("Bad File");
//
//            }
//            catch(IOException i){
//                i.printStackTrace();
//            }


            NodeList list= getDocument(filename).getDocumentElement().getElementsByTagName("Simulation");

    //public void parse(String filename) {
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            try {
//                Document document = builder.parse(new File(filename));
//                document.getDocumentElement().normalize();
//
//                list = document.getDocumentElement().getElementsByTagName("Simulation");
//            }
//            catch(SAXException e){
//                e.printStackTrace();
//
//            }
//            catch(IOException i){
//                i.printStackTrace();
//            }
 //
//>>>>>>> ffc6131b824598190019ef09dfbbfebb09a9046d
        System.out.println(filename);
        //if(!filename.substring(filename.indexOf("Percolation.xml")).equals("Percolation.xml")||!filename.substring(filename.indexOf("resources")).equals("SugarScape")||!filename.substring(filename.indexOf("resources")).equals("SpreadingFire")||!filename.substring(filename.indexOf("resources")).equals("WatorWorld")||!filename.substring(filename.indexOf("resources")).equals("Segregation")||!filename.substring(filename.indexOf("resources")).equals("GameOfLife")){
        if(!(filename.substring(filename.length()-4).equals(".xml"))){

            throw new BadFileInputException("Incorrect File input!!!");
        }
            Node gennode = list.item(0);
            if (gennode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) gennode;
                type = eElement.getElementsByTagName("type").item(0).getTextContent();
                title = eElement.getElementsByTagName("title").item(0).getTextContent();
                author = eElement.getElementsByTagName("author").item(0).getTextContent();
                rows = Integer.parseInt(eElement.getElementsByTagName("rows").item(0).getTextContent());
                columns = Integer.parseInt(eElement.getElementsByTagName("columns").item(0).getTextContent());
            }

        //}
//        catch(ParserConfigurationException e){
//            e.printStackTrace();
//        }
    }
    /*
    Gets rows of simulation
     */
    public int getRows(){
        return rows;
    }
    /*
    Gets columns of simulation
     */
    public int getCols() {
        return columns;
    }
    /*
    Gets author of xml file
     */
    public String getAuthor(){
        return author;
    }
    /*

    Gets type of simulation
     */
    public String getType(){
        return type;
    }
    /*
    Gets title of simulation
     */
    public String getTitle() {
        return title;
    }
    /*
    Gets node list of xml elements
     */
    public NodeList getList(){
        return list;
    }

}
