/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema3;
import java.io.IOException;
/**
 *
 * @author Usuario
 */
public class EjercicioHilo {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(()=> {
           try {
            
           } catch(IOException e) {
            System.out.println(e.getMessage());
        } 
        });
    }
}
