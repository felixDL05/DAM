/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simuladoreditorvideo;

/**
 *
 * @author felix
 */
public class SimuladorEditorVideo {

    private static int contadorCompartido = 0;
    
    private static final Object candado = new Object();

    public static void main(String[] args) {
        System.out.println("---INICIO DE LA SIMULACIÓN DE HILOS (EDITOR DE VÍDEO)---");

        Thread hiloA = new Thread(new TareaVistaPrevia(), "Hilo-A-VistaPrevia");
        Thread hiloB = new Thread(new TareaCalculoTamano(), "Hilo-B-Tamano");
        Thread hiloC = new Thread(new TareaAutoGuardado(), "Hilo-C-AutoGuardado");

        hiloA.start();
        hiloB.start();
        hiloC.start();

        try {
            hiloA.join();
            hiloB.join();
            hiloC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---FIN DE LA SIMULACIÓN DE HILOS---\n");
        
        probarCondicionDeCarreraSincronizada();
    }

    static class TareaVistaPrevia implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("[Hilo A - Vista Previa] Reproduciendo fotograma " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class TareaCalculoTamano implements Runnable {
        @Override
        public void run() {
            System.out.println("[Hilo B - Tamaño] Calculando tamaño final del archivo...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[Hilo B - Tamaño] Cálculo completado: 450 MB.");
        }
    }

    static class TareaAutoGuardado implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(700);
                System.out.println("[Hilo C - AutoGuardado] Guardando cambios del proyecto automáticamente...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void probarCondicionDeCarreraSincronizada() {
        System.out.println("---Demostración de Condición de Carrera Protegida---");
        contadorCompartido = 0;
        
        Thread t1 = new Thread(() -> {
            synchronized (candado) {
                int valorActual = contadorCompartido;
                contadorCompartido = valorActual + 1;
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (candado) {
                int valorActual = contadorCompartido;
                contadorCompartido = valorActual + 1;
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final protegido con synchronized: " + contadorCompartido + " (Resultado correcto: 2)");
    }
}
