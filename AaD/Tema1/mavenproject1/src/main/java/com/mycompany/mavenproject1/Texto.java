/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.FileInputStream;

public class Texto {
    public static void main(String[] args) throws IOException {
        FileInputStream e = new FileInputStream("C:\\Users\\Usuario\\Desktop\\AaD\\archivo.txt");
        int dato = e.read();
        System.out.println("El primer byte es: " + dato);
        e.close();
    }
}
