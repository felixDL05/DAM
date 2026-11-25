/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package segundo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Usuario
 */
public class Servidor {
    public static void main(String[] args) {
        final int PUERTO = 5002;
        byte[] buffer = new byte[1024];
        
        try {
            System.out.println("Iniciado servidor udp");
            //Creacion del socket
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);
            
            while(true) {
                //Preparo la respuesta
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                
                //Recibo el datagrama
                socketUDP.receive(peticion);
                System.out.println("Recibida la info del cliente");
                
                //Convertir lo recibido y mostrar el mensaje
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);
                
                //Obtengo el puerto y dirección de origen si no quieren responder mo es necesario
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
                
                mensaje = "Hola mundo desde servidor";
                buffer = mensaje.getBytes();
                
                //Crear el datagrama
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, PUERTO);
                
                //Envio la informacion
                socketUDP.send(respuesta);
            }
        } catch (Exception e) {
            
        }
    }
}
