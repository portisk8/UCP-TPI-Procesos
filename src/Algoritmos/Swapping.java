/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

/**
 *
 * @author Agustin
 */
import Procesos.Proceso;
import java.lang.reflect.Array;
import java.util.*;
public class Swapping{
    private ArrayList<Proceso> procesosPrincipales;
    private ArrayList<Proceso> procesosSecundarios;
    private int tamanioPrincipal = 5;
    String cambios="";
    String cadePrin="";
    String cadeSecu = "";
    public Swapping(ArrayList<Proceso> p_procesoLista){
        
        this.setProcPrincipales(new ArrayList());
        this.setProcSecundarios(new ArrayList());
        
        this.agregarProceso(p_procesoLista);
    }
    
    private void setProcPrincipales(ArrayList pArray){
        this.procesosPrincipales = pArray;
    }
    public ArrayList<Proceso> getProcPrincipales(){
        return this.procesosPrincipales;
    }
    private void setProcSecundarios(ArrayList pArray){
        this.procesosSecundarios = pArray;
    }
    public ArrayList<Proceso> getProcSecundarios(){
        return this.procesosSecundarios;
    }
    public void agregarProceso(ArrayList<Proceso> p_procesoLista){
        for (Proceso proceso : p_procesoLista) {
            
            if (this.getProcPrincipales().size() < this.tamanioPrincipal){
                this.getProcPrincipales().add(proceso);
            } else {
                this.getProcSecundarios().add(proceso);
            }
        }
        
    }
    public /*Proceso[]*/ String mostrarProcPrinc(){
      //  System.out.println("Memoria Principal: "); //muestro mi cola de procesos en memoria real
        int i = 0;
        String cadena= "";
        for(Proceso p: this.getProcPrincipales()){
            cadena = cadena +  ("Partición " + i + ":"+"\n"+p.mostrar()+"\n-------------------------------------------------------\n");
            i++;
        }
        
       /* int i = 0;
        Proceso [] arrayPri = new Proceso [this.getProcPrincipales().size()];
        for(Proceso p: this.getProcPrincipales()){
            //System.out.println("Partición " + i + ":");
            arrayPri[i] = p;
        }
        
        return arrayPri;*/
       return cadena;
    }
    
    public /*Proceso[]*/String mostrarProcSecond(){
       // System.out.println("Memoria Secundaria: "); //muestro mi cola de procesos en memoria real
        int i = 0;
       
        String cadena = "";
        for(Proceso p: this.getProcSecundarios()){
            
            cadena = cadena +  ("Partición " + i + ":"+"\n"+p.mostrar()+"\n-------------------------------------------------------\n");
            i++;
        }
        
        
        /* Proceso [] arraySecu = new Proceso [this.getProcSecundarios().size()];
        for(Proceso p: this.getProcSecundarios()){
            //System.out.println("Partición " + i + ":");
            arraySecu[i] = p;
        }
        
            return arraySecu;*/
        return cadena;
       
    }
    
    public String mostrarPrin(){
        return cadePrin;
    }
    public String mostrarSecu(){
        return cadeSecu;
    }
    public String cambiosLugar(){
        
        return cambios;
    }
    public void swappear(){
        //System.out.println("Swapping con procesos bloqueados entre la memoria real y la virtual: ");
        int j=0,i=0;
        Proceso proAux;
        int cant2 = this.getProcSecundarios().size();
        for(Proceso p: this.getProcPrincipales()){ // el swpping consiste en recorrer mi cola de procesos e intercambiar procesos que este bloqueados con los procesos que se encuentran en memoria virtual
              
            if(p.isBloqueado() == true && j < cant2){ //solo intercambiará mientras halla procesos en estado listo en memoria virtual, eso lo controlamos con un auxi mientras sea menor a la cantidad2
               // System.out.println("Swap en Particion " + auxi + "."); // informo donde se realizó el intercambio
                cambios = cambios + "Swapping en partición " + i + "\n";
                
                cadePrin = cadePrin +"Particion "+ i + ":\n"+(this.getProcSecundarios().get(j).mostrar() +"\n-------------------------------------------------------\n");
                
                cadeSecu = cadeSecu + "Particion "+ j + ":\n"+(this.getProcPrincipales().get(i).mostrar() +"\n-------------------------------------------------------\n");
                /*proAux = p; // guardo mi proceso bloqueado en proAux   
                //this.getProcPrincipales().add(this.getProcPrincipales().indexOf(p), this.getProcSecundarios().);
                
                this.getProcPrincipales().remove(p);
                this.getProcPrincipales().add(i, this.getProcSecundarios().get(j)); // muevo mi proceso listo al lugar donde se encontraba el proceso bloqueado
                this.getProcSecundarios().remove(this.getProcSecundarios().get(j));
                this.getProcSecundarios().add(j, proAux);// mi proceso bloqueado lo mando a la posicion del proceso listo en memoria virtual*/
                
                j++;
                
            }else{
                cadePrin = cadePrin +"Particion "+ i + ":\n"+(this.getProcPrincipales().get(i).mostrar() +"\n-------------------------------------------------------\n");
                //cadeSecu = cadeSecu + "Particion"+ j + ":"+(this.getProcSecundarios().get(j).mostrar() +"\n-------------------------------------------------------\n");
            
            }
            i++; 
            
           
        }
        
        for (i=j ; i < cant2; i++){
            cadeSecu = cadeSecu + "Particion"+ i + ":\n"+(this.getProcSecundarios().get(i).mostrar() +"\n-------------------------------------------------------\n");
       }
    }
}
