
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.io.IOException;

/**
 *
 * @author Jesús Guillén Encinas
 */
public class Tema1_Actividad1 {

   public static void main(String[] args) throws IOException {

        /*
         * ============================================================
         * TEMA 1 - INTRODUCCIÓN AL MANEJO DE FICHEROS
         * ACCESO A DATOS - EJERCICIOS
         * ============================================================
         *
         * INSTRUCCIONES:
         * - Completa cada apartado donde aparece TODO.
         * - No borres los enunciados.
         * - Puedes crear variables y código auxiliar dentro de cada apartado.
         *
         * CONTENIDOS:
         * 1. Clase File
         * 2. Información de un fichero
         * 3. Crear directorios y mover ficheros
         * 4. FileWriter
         * 5. FileReader
         * 6. Acceso secuencial o aleatorio
         * 7. FileInputStream / FileOutputStream
         * 8. RandomAccessFile
         * 9. Ejercicio Final
         */

        // ============================================================
        // EJERCICIO 1 - CREAR UN FICHERO
        // ============================================================

        /*
         * Crea un programa que:
         *
         * 1. Cree un objeto File asociado al fichero "datos.txt".
         * 2. Compruebe si el fichero existe.
         * 3. Si no existe, créalo.
         * 4. Muestra por pantalla el nombre del fichero.
         */

        // TODO: EJERCICIO 1
        // Escribe aquí tu solución.
        File f = new File("C:\\Users\\Usuario\\Desktop\\AaD\\datos.txt");
        if(!f.exists()) {
            f.createNewFile();
        }
        
        System.out.println("El nombre del fichero es: " + f.getName());

        // ============================================================
        // EJERCICIO 2 - INFORMACIÓN DEL FICHERO
        // ============================================================

        /*
         * Utilizando un objeto File asociado a "datos.txt",
         * muestra por pantalla:
         *
         * - Nombre del fichero.
         * - Ruta.
         * - Ruta absoluta.
         * - Directorio padre.
         * - Si existe.
         * - Si se puede leer.
         * - Si se puede escribir.
         */

        // TODO: EJERCICIO 2
        // Escribe aquí tu solución.
        System.out.println("El nombre del fichero es: " + f.getName());
        System.out.println("La ruta del fichero es: " + f.getPath());
        System.out.println("La ruta absoluta del fichero es: " + f.getAbsolutePath());
        System.out.println("El directorio padre del fichero es: " + f.getParent());
        System.out.println("¿El fichero existe?: " + f.exists());
        System.out.println("¿El fichero se puede leer?: " + f.canRead());
        System.out.println("¿El fichero se puede escribir?: " + f.canWrite());
        // ============================================================
        // EJERCICIO 3 - CREAR DIRECTORIO Y MOVER UN FICHERO
        // ============================================================

        /*
         * Tenemos un fichero llamado "datos.txt".
         *
         * El programa debe:
         *
         * 1. Crear un directorio llamado "backup".
         * 2. Comprobar que el directorio existe.
         * 3. Mover "datos.txt" dentro de "backup".
         *
         * Resultado final:
         *
         * backup/
         *     datos.txt
         */

        // TODO: EJERCICIO 3
        // Escribe aquí tu solución.
        File carpeta = new File("backup");
        carpeta.mkdir();

        if (carpeta.exists()) {
            System.out.println("El directorio existe.");
        } else {
            System.out.println("El directorio no existe.");
        }
        
        File destino = new File(carpeta, "datos.txt");

        if (f.renameTo(destino)) {
            System.out.println("El archivo se ha movido correctamente.");
        } else {
            System.out.println("No se ha podido mover el archivo.");
        }
        
        // ============================================================
        // EJERCICIO 4 - ESCRIBIR TEXTO CON FileWriter
        // ============================================================

        /*
         * Crea un fichero llamado "alumnos.txt" y escribe dentro:
         *
         * Juan
         * María
         * Pedro
         * Ana
         *
         * Debes utilizar FileWriter.
         *
         * Recuerda:
         * - Instanciar FileWriter.
         * - Utilizar write().
         * - Cerrar el flujo.
         */

        // TODO: EJERCICIO 4
        // Escribe aquí tu solución.
        FileWriter escritor = new FileWriter("alumnos.txt");

        escritor.write("Juan\n");
        escritor.write("María\n");
        escritor.write("Pedro\n");
        escritor.write("Ana\n");

        escritor.close();


        // ============================================================
        // EJERCICIO 5 - LEER UN CARÁCTER CON FileReader
        // ============================================================

        /*
         * Crea previamente un fichero llamado "mensaje.txt" que contenga:
         *
         * Hola
         *
         * Utiliza FileReader para:
         *
         * 1. Abrir el fichero.
         * 2. Leer el primer carácter mediante read().pero
         * 3. Mostrarlo por pantalla.
         * 4. Cerrar el lector.
         *
         * Resultado esperado:
         * H
         */

        // TODO: EJERCICIO 5
        // Escribe aquí tu solución.
        FileReader lector = new FileReader("mensaje.txt");

        int caracter = lector.read();

        System.out.println((char) caracter);

        lector.close();

        // ============================================================
        // EJERCICIO 6 - ¿ACCESO SECUENCIAL O ALEATORIO?
        // ============================================================

        /*
         * Para cada situación indica si utilizarías:
         *
         *     ACCESO SECUENCIAL
         *     o
         *     ACCESO ALEATORIO / DIRECTO
         *
         * y explica brevemente por qué.
         *
         * A) Tenemos un fichero con 500 nombres y queremos mostrar
         *    todos los nombres desde el primero hasta el último.
         *
         * B) Tenemos un fichero enorme y queremos acceder directamente
         *    a una posición concreta.
         *
         * C) Queremos recorrer un fichero de texto completo y copiar
         *    su contenido a otro fichero.
         *
         * D) Queremos modificar información situada en una posición
         *    concreta del fichero.
         *
         * E) Queremos leer un fichero de texto completo.
         *
         * Recuerda:
         *
         * - Secuencial: recorremos la información en orden.
         * - Aleatorio/directo: podemos posicionarnos directamente
         *   en una posición concreta.
         */

        // TODO: EJERCICIO 6
        // Escribe tus respuestas aquí mediante comentarios.
        //
        // A)El secuencial porque desde el primero va en orden uno por uno hasta el último ya que queremos mostrar los 500.
        // B)El aleatorio/directo ya que este nos permite ir directamente al dato concreto.
        // C)El secuencial ya que queremos recorrer todo el contenido.
        // D)El aleatorio/directo porque queremos coger solo la información de una posición concreta.
        // E)El secuencial porque al querer leer el texto completo lo lee desde el primero hasta el final.


        // ============================================================
        // EJERCICIO 7 - LECTURA DE BYTES
        // ============================================================

        /*
         * Crea un fichero llamado "bytes.txt" que contenga algún texto.
         *
         * Utiliza FileInputStream para:
         *
         * 1. Abrir el fichero.
         * 2. Leer un byte utilizando read().
         * 3. Mostrar por pantalla el valor obtenido.
         * 4. Cerrar el flujo.
         *
         */

        // TODO: EJERCICIO 7
        // Escribe aquí tu solución.
        FileInputStream b = new FileInputStream("bytes.txt");
        
        int dato = b.read();
        
        System.out.println("El byte leído es: " + dato);
        
        b.close();

        // ============================================================
        // EJERCICIO 8 - ESCRITURA DE BYTES
        // ============================================================

        /*
         * Utiliza FileOutputStream para crear/escribir el fichero:
         *
         * "salidaBytes.txt"
         *
         * Escribe mediante write() el valor 68.
         *
         * Recuerda que 68 es la letra 'D' en ASCII.
         *
         * Después cierra el flujo.
         */

        // TODO: EJERCICIO 8
        // Escribe aquí tu solución.
        FileOutputStream s = new FileOutputStream("salidaBytes.txt");
        
        s.write(68);
        
        s.close();
        
        System.out.println("El 68 se escribió en salidaBytes.txt");

        // ============================================================
        // EJERCICIO 9 - RandomAccessFile
        // ============================================================

        /*
         * Crea un fichero llamado "letras.txt" que contenga:
         *
         * ABCDEFGHIJ
         *
         * Utiliza RandomAccessFile para:
         *
         * 1. Abrir el fichero en modo "rw".
         * 2. Situarte en la posición 5 mediante seek().
         * 3. Leer el carácter que hay en esa posición.
         * 4. Mostrarlo por pantalla.
         * 5. Mostrar también la posición actual del puntero.
         *
         * Resultado esperado:
         *
         * F 6
         *
         */

        // TODO: EJERCICIO 9
        // Escribe aquí tu solución.
        FileWriter fichero2 = new FileWriter("letras.txt");
        fichero2.write("ABCDEFGHIJ");
        fichero2.close();
        RandomAccessFile fi = new RandomAccessFile("letras.txt", "rw");
        fi.seek(5);
        int letra = fi.read();
        System.out.println("El carácter en la posición 5 es: "+ (char)letra + "y la posición actual del puntero es: " + fi.getFilePointer());
        
        

        // ============================================================
        // EJERCICIO 10 - ESCRIBIR CON RandomAccessFile
        // ============================================================

        /*
         * Utiliza el fichero "letras.txt" del ejercicio anterior.
         *
         * 1. Abre el fichero con RandomAccessFile en modo "rw".
         * 2. Coloca el puntero en la posición 5.
         * 3. Escribe el byte correspondiente a la letra 'D'
         *    utilizando write().
         * 4. Cierra el fichero.
         *
         * Comprueba después cómo ha quedado el contenido del fichero.
         *
         */

        // TODO: EJERCICIO 10
        // Escribe aquí tu solución.
        
        
        // ============================================================
        // EJERCICIO 11 - LEER VARIOS BYTES
        // ============================================================

        /*
         * Utiliza RandomAccessFile con un fichero que contenga texto.
         *
         * Crea un array de bytes de tamaño 10.
         *
         * Utiliza read(byte[], inicio, cantidad) para leer varios
         * bytes del fichero y almacenarlos en el array.
         *
         * Finalmente muestra cuántos bytes se han leído.
         *
         */

        // TODO: EJERCICIO 11
        // Escribe aquí tu solución.

        // ============================================================
        // EJERCICIO 12 - Ejercicio FINAL: GESTOR DE FICHEROS
        // ============================================================

        /*
         * Crea un pequeño gestor de ficheros.
         *
         * El programa debe gestionar un fichero llamado:
         *
         * alumnos.txt
         *
         * Debe realizar las siguientes operaciones:
         *
         * 1. Crear el fichero si no existe.
         *
         * 2. Mostrar:
         *    - Nombre.
         *    - Ruta.
         *    - Ruta absoluta.
         *    - Si existe.
         *    - Si se puede leer.
         *    - Si se puede escribir.
         *
         * 3. Escribir en el fichero:
         *
         *    Juan
         *    Ana
         *    Pedro
         *    Laura
         *
         *    Utiliza FileWriter.
         *
         * 4. Utilizar FileReader para leer el primer carácter
         *    y mostrarlo por pantalla.
         *
         * 5. Crear un directorio llamado "backup".
         *
         * 6. Mover el fichero a:
         *
         *    backup/alumnos.txt
         *
         * 7. Mostrar la fecha de última modificación utilizando
         *    lastModified().
         *
         * IMPORTANTE:
         * Recuerda cerrar los flujos de lectura y escritura
         * utilizando close().
         *
         */

        // TODO: Ejercicio FINAL
        // Escribe aquí tu solución completa.


    }
}
