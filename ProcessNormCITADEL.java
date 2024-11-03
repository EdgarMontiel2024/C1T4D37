package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

 public class ProcessNormCITADEL {

   private static void filterFactX(Stream<Registro> registros,String factura ){    
        registros.filter(reg ->reg.factura.equals(factura))
                 .filter(reg -> Integer.parseInt( reg.cantidad )>0     )
                 .map(reg -> { reg._66="66";

                               reg.sucursal = reg.sucursal.equals("015") ? "022" : reg.sucursal;

                               if (reg.orden.trim().length()==6){
                                reg.orden= "2"+reg.orden.substring(1,reg.orden.length());
                               }
                               return reg;
                             } 
                     ) ;
                 
        

        System.out.println("________________________");
    }



    static List<Registro> filterFact(Stream<Registro> registros,String factura ){
        List <Registro> res= registros.filter(r ->r.factura.equals(factura)).toList();
       // System.out.println(res);                
        return res;
    }


    static List<Registro> filterQuantityGreatherThan0( List<Registro> registros ){  
        Stream<Registro> regstream= registros.stream();
        return regstream.filter(reg -> Integer.parseInt( reg.cantidad )>0     ).toList();
    } 

    static List<Registro> fijar66( List<Registro> registros ){       
        Stream<Registro> regstream= registros.stream();
        return regstream.map(r ->{ r._66="66"; return r; }).toList();
    }  

    static List<Registro> changeSucursal_015_022( List<Registro> registros ){       
        Stream<Registro> regstream= registros.stream();
        return regstream.map( r -> {
                                     r.sucursal = r.sucursal.equals("015") ? "022" : r.sucursal;
                                     return r;
                                   }   ).toList();
    }  

    static List<Registro> changePrefixOrdenIfLenghis6( List<Registro> registros ){       
        Stream<Registro> regstream= registros.stream();
        return regstream.map( r -> {
                                     if (r.orden.trim().length()==6){
                                         r.orden= "2"+r.orden.substring(1,r.orden.length());
                                        }
                                      return r;
                                   }   ).toList();
    }  

   
   

    static List<Registro> maxOne_MERCHANDISE( List<Registro> registros ){ 
        List<Registro> rregistros=new ArrayList <Registro>();
        
        Stream<Registro> stream_quitaMERCHANDISE= registros.stream();
        Stream<Registro> stream_quitatodomenosMERCHANDISE= registros.stream();
        
      // deja un MERCHAN...
       Optional<Registro> oneMerchandise = stream_quitatodomenosMERCHANDISE
                                             .filter( r-> r.toString().contains("MERCHANDISE") ).findFirst();
       Registro r=oneMerchandise.orElse(null);

       //----
       stream_quitaMERCHANDISE.filter( rs-> !rs.toString().contains("MERCHANDISE") )
                              .forEach(rs -> rregistros.add(rs)) ;
       if(r!=null){ rregistros.add(r);} 

        return rregistros;
    }



    static List<Registro> maxOne_CRV( List<Registro> registros ){ 
        List<Registro> rregistros=new ArrayList <Registro>();
        
        Stream<Registro> stream_quitaCRV= registros.stream();
        Stream<Registro> stream_quitatodomenosCRV= registros.stream();
        
      // deja un CRV
       Optional<Registro> oneCRV = stream_quitatodomenosCRV
                                             .filter( r-> r.toString().contains("CRV") ).findFirst();
       Registro r=oneCRV.orElse(null);

       //----
       stream_quitaCRV.filter( rs-> !rs.toString().contains("CRV") )
                              .forEach(rs -> rregistros.add(rs)) ;
       if(r!=null){ rregistros.add(r);} 

        return rregistros;
    }



    static List<Registro> unificarOSmasRecientea1Factura( List<Registro> registros ){ 
        Stream<Registro> regstream= registros.stream();
         
        String osMAx = regstream.map(r->r.orden).distinct().max((a,b)-> Integer.parseInt(a)- Integer.parseInt(b) ).get();
        
        Stream<Registro> regstreamSustit= registros.stream();


        registros=regstreamSustit.map(rr -> { rr.orden=osMAx; return rr; }).toList();



        return registros;
    }


}
