package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileToObjectRegistro {

    public Stream <Registro> convert(String path_file){
        ArrayList<Registro> al=new ArrayList<Registro>() ;
         try (
               FileReader fr = new FileReader(path_file) 
              ) {
                 BufferedReader br = new BufferedReader(fr);
                    // Lectura del fichero
                    String linea;
                    while((linea=br.readLine())!=null)
                    //System.out.println(linea);
                    al.add(new Registro(linea));
                }
         catch(Exception e){
            e.printStackTrace();
         }




        return al.stream();
    }

}
