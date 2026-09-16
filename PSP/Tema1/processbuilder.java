import java.io.IOException;

public class processbuilder {
    public static void main(String[] args) {
        try {
            ProcessBuilder builder = new ProcessBuilder("ping", "www.google.es");
            Process proceso = builder.start();
            
            int estadoSalida = proceso.waitFor();
            System.out.println("El proceso ha salido con: " + estadoSalida);
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }
}