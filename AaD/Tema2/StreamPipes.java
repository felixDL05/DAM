import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class StreamPipes {

    public static void main(String[] args) {
        try {
            
            PipedOutputStream salida = new PipedOutputStream();
            PipedInputStream entrada = new PipedInputStream(salida);

            Thread productor = new Thread(() -> {
                try {
                    salida.write("Mensaje 1\n".getBytes());
                    salida.write("Mensaje 2\n".getBytes());
                    salida.write("Mensaje 3\n".getBytes());
                    
                    salida.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            Thread consumidor = new Thread(() -> {
                try {
                    int dato;
                    int caracteres = 0;
                    int mensajes = 0;

                    while ((dato = entrada.read()) != -1) {
                        System.out.print((char) dato);
                        caracteres++;

                        if (dato == '\n') {
                            mensajes++;
                        }
                    }

                    System.out.println("El número de caracteres es: " + caracteres);
                    System.out.println("El número de mensajes es: " + mensajes);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            productor.start();
            consumidor.start();

            productor.join();
            consumidor.join();

            entrada.close();

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}