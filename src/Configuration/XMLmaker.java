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
    public static void main(String argv[]) {
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            //Elements for Simulation type and name, author
            Element Simulationtype = doc.createElement("Simulationtype");
            Simulationtype.appendChild(doc.createTextNode("Fire"));
            doc.appendChild(Simulationtype);
            Element Simtitle = doc.createElement("Simulationtitle");
            Simtitle.appendChild(doc.createTextNode("Spreading Fire"));
            Simulationtype.appendChild(Simtitle);
            Element Author = doc.createElement("Author");
            Author.appendChild("Hyunjae Lee");
            Simtitle.appendChild(Author);
            
            //Elements for

        }
    }
}
