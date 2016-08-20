package dom_parsing;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Dom_Parsing {

    public static void tratarNodo(Node nodo) {

        switch (nodo.getNodeType()) {
            case Node.DOCUMENT_NODE:
                System.out.println("<xml version=\"1.0\">");
                Document doc = (Document) nodo;
                tratarNodo(doc.getDocumentElement());
                break;
            case Node.ELEMENT_NODE:
                String nombre = nodo.getNodeName();
                System.out.print("<" + nombre);
                NamedNodeMap ats = nodo.getAttributes();
                for (int i = 0; i < ats.getLength(); i++) {
                    tratarNodo(ats.item(i));
                }
                System.out.println(">");
                NodeList hijos = nodo.getChildNodes();
                
                for (int i = 0; i < hijos.getLength(); i++) {
                    tratarNodo(hijos.item(i));
                }
                // tratar los hijos recursivamente
                break;
            case Node.ATTRIBUTE_NODE:
                System.out.print(" " + nodo.getNodeName()
                        + "=\"" + nodo.getNodeValue() + "\"");
                break;
            case Node.TEXT_NODE:
                String texto = nodo.getNodeValue().trim();
                if (!texto.equals("")) {
                    System.out.println(texto); //esto iba (ind + texto)
                }
                break;
            case Node.CDATA_SECTION_NODE:
                System.out.println(nodo.getNodeValue());
                break;
            case Node.PROCESSING_INSTRUCTION_NODE:
                break;
            case Node.ENTITY_REFERENCE_NODE:
                break;
            case Node.DOCUMENT_TYPE_NODE:
                break;
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        
        DocumentBuilderFactory fact
                = DocumentBuilderFactory.newInstance();
        // Crear nueva instancia de DOMBuilder a través factoria
        DocumentBuilder parser = fact.newDocumentBuilder();
        parser.parse("/home/darkaliensky/Escritorio/ejemploDOM.xml");
        Document doc = parser.parse(new File("/home/darkaliensky/Escritorio/ejemploDOM.xml"));
        
        Dom_Parsing.tratarNodo(doc);
        NodeList peliNodes = doc.getElementsByTagName("Pelicula");
        
        System.out.println(peliNodes.getLength());
        
    }
}
