/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import Procesos.Proceso;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Augusto
 */
public class AlgoritmoFifo {
    
    private ArrayList<Proceso> listadoProcesos;    
    private ArrayList<Proceso> listadoProcesosTerminados;
    private Proceso procesoPorAtender;

    
    public AlgoritmoFifo(ArrayList<Proceso> listadoP){
        Collections.sort(listadoP);
        this.setListadoProcesos(listadoP);
    }

    public ArrayList<Proceso> getListadoProcesos() {
        return listadoProcesos;
    }

    private void setListadoProcesos(ArrayList<Proceso> listadoProcesos) {
        this.listadoProcesos = listadoProcesos;
    }
    
    public void imprimirListado(ArrayList<Proceso> listadoP){
        for (Proceso proceso : listadoP) {
            System.out.println(proceso.getTiempoLlegada());
        }
    }
    
    public void ejecutarProceso(){
        
    }
}
