/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema3;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Usuario
 */
public class Ejemplo2 {
    public static void main(String[] args) throws InterruptedException {
        try {
            ProcessBuilder ps = new ProcessBuilder ("docker", "run", "--rm", "--memory=128m", "--cpus=0.5", "alpine", "echo", "Hola Mundo");
            Process proceso = ps.start();
            BufferedReader lector = new BufferedReader( new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                    System.out.println("Salida del comando: " + linea);
            }
            int codigo = proceso.waitFor();
            System.out.println("Código: " + codigo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        

    }
}
