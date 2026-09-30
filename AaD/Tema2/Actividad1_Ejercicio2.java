import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;


public class Actividad1_Ejercicio2 {
    public static void main(String[] args) {
        try{ 
            PipedOutputStream salida = new PipedOutputStream();
            PipedInputStream entrada = new PipedInputStream(salida);
            
            File origen = new File("origen.txt");
            File destino = new File("destino.txt");
            
            if(!origen.exists()) {
                origen.createNewFile();
            }
            
            if(!destino.exists()) {
                destino.createNewFile();
            }
            
            Thread productor = new Thread( ()->{
                try{ 
                    FileInputStream fichero = new FileInputStream(origen);
                    int dato;
                    while((dato = fichero.read()!= -1)) {
                        salida.write(dato);
                    }
                    fichero.close();
                    salida.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
    }
            });
            
            Thread consumidor = new Thread( ()-> {
                try{ 
                    FileOutputStream fichero = new FileOutputStream(destino);
                    int dato = entrada.read();
                    while((dato = entrada.read())!= -1) {
                        fichero.write(dato);
                    }
                    fichero.close();
                    entrada.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            
        } catch (Exception ex) {
        
    })
    }
}