package com.mycompany.mavenproject2;

import org.w3c.dom.Document;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.NodeList;

public class TiendaXML {
    public Document cargaXML() throws Exception {
        //Creamos el puntero al archivo xml
        File archivo = new File("alumnos.xml");
        //Creamos la factoria del lector de DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(archivo);
        return document;
    }
    
    public NodeList obtenerNombres(Document document) throws Exception {
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expresion = "/alumnos.xml/alumno/nombre";
        NodeList nombres = (NodeList) xpath.evaluate(
                expresion,
                document,
                XPathConstants.NODESET
        );
                return nombres;
    }
}