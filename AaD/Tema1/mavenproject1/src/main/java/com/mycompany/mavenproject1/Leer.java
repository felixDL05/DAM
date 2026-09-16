/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.FileWriter;

public class Leer {
    public static void main(String[] args) throws IOException {
        FileWriter escritor = new FileWriter("C:\\Users\\Usuario\\Desktop\\AaD\\archivo.txt", true);
        escritor.write("Hasta luego Mundo");
        escritor.close();
        
    }
}
