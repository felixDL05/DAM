import java.io.IOException;

public class runtime {
    public static void main(String[] args) {
        try{
                Runtime runtime = Runtime.getRuntime();
                Process proceso = runtime.exec("ping www.google.es");
                
                int estadoSalida = proceso.waitFor();
                System.out.println("El proceso a salido con: " + estadoSalida);
        }
        catch(IOException | InterruptedException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }
}