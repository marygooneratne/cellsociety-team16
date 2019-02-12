package Configuration;
import org.w3c.dom.*;
public class SugarParser extends Parser{
    private int numAgents;
    public void parseSugar(String filename) throws BadFileInputException{
        NodeList myList=getDocument(filename).getElementsByTagName("probability");
        Node gameNode=myList.item(0);
        if(gameNode.getNodeType()==Node.ELEMENT_NODE) {
            Element eElement = (Element) gameNode;
            numAgents = Integer.parseInt(eElement.getElementsByTagName("numAgents").item(0).getTextContent());
        }
    }
    public int getNumAgents(){
        return numAgents;
    }
}
