package com.example;

public class Registro {
   public String factura="";
   public String _66="";
   public String sucursal="";//015 a 022;
   public String segA="";
   public String cantidad="";
   public String segB="";
   public String orden="";
   public String segC="";
//test

    public Registro(String linea){
        this.factura = linea.substring(0,5);
        this._66     = linea.substring(5,7);
        this.sucursal= linea.substring(7,10);
        this.segA    = linea.substring(10,100);    //<<<<------------
        this.cantidad= linea.substring(100,102);
        this.segB    = linea.substring(102,150);   //<--------------
        this.orden   = linea.substring(150,156);
        this.segC    = linea.substring(156,linea.length());   //<--------------

    }


     @Override
     public String toString() {
        return factura+_66+sucursal+segA+cantidad+segB+orden+segC;

     }


}
