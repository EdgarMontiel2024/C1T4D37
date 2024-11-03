package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ObjectRegistroToFile {
    String root_path="C:/workspace_vscode/demo/files/process/P";

     public void convert(List<Registro> registros , String factura){
       String dirnewfile=root_path+getFileName_DateTimeFact(factura);
      
        try(FileWriter out = new FileWriter(dirnewfile,true);
            BufferedWriter bw= new BufferedWriter(out);   ){
                registros.forEach(r-> { try {
                                             bw.write(r.toString());
                                             bw.newLine();
                                            } catch (IOException e) { e.printStackTrace(); }
                                       
                                     });



            } catch (Exception ex) {
             ex.printStackTrace();
            }



     }



     public String getFileName_DateTimeFact(String fact){
       //LocalDate today    = LocalDate.now();
       // LocalTime time_now = LocalTime.now();

        // Creating a SimpleDateFormat object with a custom date pattern
       // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm");

        // Formatting the current date and time using the specified pattern
        String formattedDate = dateFormat.format(new Date());
      
         System.out.println("----------:"+formattedDate);

      //return "20241102_1758_"+fact+".txt";
       return "Pfact_"+formattedDate+"_"+fact+".txt";
     }

}
