
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.IOException;

public class Mavenproject1 {

    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\Usuario\\Desktop\\AaD\\archivo.txt");
        if(!f.exists()) {
            f.createNewFile();
        }
        System.out.println("El archivo se llama " + f.getName());
        System.out.println("El archivo está alojado en " + f.getPath());
        System.out.println("El archivo existe " + f.exists());
        System.out.println("El archivo puede ser escrito" + f.canWrite());
    }
}
