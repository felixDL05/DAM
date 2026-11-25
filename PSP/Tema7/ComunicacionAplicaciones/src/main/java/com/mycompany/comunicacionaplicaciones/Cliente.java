/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.comunicacionaplicaciones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Usuario
 */
public class Cliente {

    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PUERTO = 5002;
        DataInputStream in;
        DataOutputStream out;
        
        try {
            //Creo el socket para conectarse con el servidor
            Socket sc = new Socket(HOST, PUERTO);
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            
            //Envio un mensaje al servidor
            out.writeUTF("Hola soy el cliente");
            
            //Recibir mensaje del servidor
            String mensaje = in.readUTF();
            System.out.println(mensaje);
            
            sc.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
