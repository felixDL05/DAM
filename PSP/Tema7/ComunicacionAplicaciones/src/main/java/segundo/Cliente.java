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
public class Cliente {
    public static void main(String[] args) {
         final int PUERTO_SERVIDOR = 5003;
        byte[] buffer = new byte[1024];

        try {
            // Dirección del servidor (mi propio PC)
            InetAddress direccionServidor = InetAddress.getByName("127.0.0.1");

            // Creo el socket sin puerto, el sistema le da uno libre
            DatagramSocket socketUDP = new DatagramSocket();

            // Preparo el mensaje
            String mensaje = "Hola servidor, soy el cliente";
            buffer = mensaje.getBytes();

            // Creo el datagrama con el mensaje, la IP y el puerto del servidor
            DatagramPacket peticion = new DatagramPacket(
                    buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);

            // Envío el datagrama
            socketUDP.send(peticion);

            // Preparo el datagrama para recibir la respuesta
            buffer = new byte[1024];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);

            // Recibo la respuesta del servidor
            socketUDP.receive(respuesta);

            // Saco el mensaje
            mensaje = new String(respuesta.getData());
            System.out.println("Respuesta del servidor: " + mensaje);

            // Cierro el socket
            socketUDP.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
