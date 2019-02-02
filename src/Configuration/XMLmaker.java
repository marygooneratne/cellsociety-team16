package Configuration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class XMLmaker {
    public FileMaker(String simType, String simName, String author, int width) {
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            //Elements for Simulation type and name, author
            Element Simulationtype = doc.createElement("Simulationtype");
            Simulationtype.appendChild(doc.createTextNode(simType));
            doc.appendChild(Simulationtype);
            Element Simtitle = doc.createElement("Simulationtitle");
            Simtitle.appendChild(doc.createTextNode(simName));
            Simulationtype.appendChild(Simtitle);
            Element Author = doc.createElement("Author");
            Author.appendChild(doc.createTextNode(author));
            Simtitle.appendChild(Author);

            //Elements for Grid and cell initial states
            Element Width = doc.createElement("Width");
            Width.appendChild(doc.createTextNode("%d",width))
            doc.appendChild(Width);




            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\file.xml"));

            transformer.transform(source,result);
            //Elements for

        }
        catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch(TransformerException tfe){
            tfe.printStackTrace();
        }
    }
}
