/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import com.mycompany.mavenproject2.TemaTest;
import com.mycompany.mavenproject2.TiendaXML;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.w3c.dom.NodeList;

/**
 *
 * @author Usuario
 */
public class Tema3Test {
    
    public Tema3Test() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        tienda = new TiendaXML();
        document = tienda.cargaXML();
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void sumarTest() {
        TemaTest t = new TemaTest();
        assertEquals(5, t.sumar(2, 3));
    }
    
    @Test
    public void comprobarNumeroDeProductosTest() throws Exception {
        NodeList nombres = tienda.obtenerNombres(document);
        assertEquals(3, nombres.getLength());
    }
    
    @Test
    public void comprobarPrimerAlumno() throws Exception {
        
    }
    
    
    
}
