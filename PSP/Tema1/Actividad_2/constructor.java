import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class constructor {
    public static void main(String[] args) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "dir");
            Process proceso = builder.start();
            
            BufferedReader f = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = f.readLine()) !=null ) {
                System.out.println(linea);
            }
            int estadoSalida = proceso.waitFor();
            System.out.println("Código de salida: " + estadoSalida);
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }
}