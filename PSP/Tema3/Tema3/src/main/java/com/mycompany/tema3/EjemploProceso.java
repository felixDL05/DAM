/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tema3;

import java.io.IOException;

/**
 *
 * @author Usuario
 */
public class EjemploProceso {

    public static void main(String[] args) throws InterruptedException {
        try {
            ProcessBuilder pb = new ProcessBuilder("notepad.exe");
            Process proceso = pb.start();
            ProcessHandle handle = proceso.toHandle();
            
            System.out.println("PID: " + handle.pid());
            System.out.println("¿Estás vivo?: " + handle.isAlive());
            
            Thread.sleep(3000);
            handle.destroy();
            System.out.println("¿Estás vivo?: " + handle.isAlive());
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
