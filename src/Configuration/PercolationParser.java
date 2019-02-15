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
@author-Hyunjae Lee
Parser for Percolation
 */
public class PercolationParser extends Parser{
    double prob;
    int numPerc;
    /*
    @param-filename
    Method to parse xml document for percolation
     */
    public void percParse(String filename) throws BadFileInputException{

//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new File(filename + ".xml"));
//            document.getDocumentElement().normalize();

                NodeList myList = getDocument(filename).getElementsByTagName("probability");
                Node gameNode = myList.item(0);
                if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) gameNode;
                    prob = Double.parseDouble(eElement.getElementsByTagName("prob").item(0).getTextContent());
                    numPerc = Integer.parseInt(eElement.getElementsByTagName("numPerc").item(0).getTextContent());
                }

//            catch(BadFileInputException b) {
//                throw new BadFileInputException("Bad");
//            }


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
Returns number of percolated cells
 */
    public int getNumPerc(){
        return numPerc;
    }
    /*
    Returns probability to be filled
     */
    public double getProb(){
        return prob;
    }
}
