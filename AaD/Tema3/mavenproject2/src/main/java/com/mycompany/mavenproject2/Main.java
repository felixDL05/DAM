/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
/**
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) {
        try{
            TiendaXML tienda = new TiendaXML();
            Document document = tienda.cargaXML();
            NodeList nombres = tienda.obtenerNombres(document);
            for (int i=0; i < nombres.getLength(); i++) {
                System.out.println(nombres.item(i).getTextContent());
            }
            System.out.println(nombres.item(0).getTextContent());
        } catch(Exception e) {
            
        }
    }
}
