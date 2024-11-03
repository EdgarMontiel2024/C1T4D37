package com.example;


import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        
        String fact="63538";
        String path_file="C:/workspace_vscode/demo/files/IN291024.2235";

        FileToObjectRegistro filetobject = new FileToObjectRegistro();        
        Stream<Registro> registros= filetobject.convert(path_file);

        //System.out.println(registros.count());//556
        List<Registro> regs= null;
        regs = ProcessNormCITADEL.filterFact(registros, fact);
        regs = ProcessNormCITADEL.filterQuantityGreatherThan0(regs);
        regs = ProcessNormCITADEL.fijar66(regs);
        regs = ProcessNormCITADEL.changeSucursal_015_022(regs);
        regs = ProcessNormCITADEL.changePrefixOrdenIfLenghis6(regs);
        regs = ProcessNormCITADEL.maxOne_CRV(regs);
        regs = ProcessNormCITADEL.maxOne_MERCHANDISE(regs);        
        regs = ProcessNormCITADEL.unificarOSmasRecientea1Factura(regs);
        regs = ProcessNormCITADEL.unificarOSmasRecientea1Factura(regs);

        //System.out.println("Para la Factura: "+fact+" \n"
        //                   + regs);



        ObjectRegistroToFile  objetToFile = new ObjectRegistroToFile(); 
        objetToFile.convert(regs, fact);

        //objetToFile.getFileName_DateTimeFact(fact);
        




        
      

    }
}