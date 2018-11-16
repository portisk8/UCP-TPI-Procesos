/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import Procesos.Proceso;
import Procesos.ProcesoTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Augusto
 */
public class Fifo implements Runnable{
    
    private FifoView fifoView;
    private ProcesoTableModel procesoTableModelPendiente;
    private ProcesoTableModel procesoTableModelEjecucion;
    private ProcesoTableModel procesoTableModelTerminado;

    
    public Fifo(FifoView f_fifoView, ArrayList<Proceso> listProcess) {
        this.fifoView = f_fifoView;
        Collections.sort(listProcess);
        this.setProcesoTableModelPendiente(new ProcesoTableModel(listProcess));
        this.setProcesoTableModelEjecucion(new ProcesoTableModel(new ArrayList<Proceso>()));
        this.setProcesoTableModelTerminado(new ProcesoTableModel(new ArrayList<Proceso>()));
        
        this.fifoView.getjTableProcesosEspera().setModel(this.getProcesoTableModelPendiente());
        this.fifoView.getjTableProcesoEnCurso().setModel(this.getProcesoTableModelEjecucion());
        this.fifoView.getjTableProcesosTerminados().setModel(this.getProcesoTableModelTerminado());
    }
    @Override
        public void run() {
            Proceso proceso;
            this.fifoView.getTimerCpu().setText("0");
            int timer = 0;
            while(this.getProcesoTableModelPendiente().getRowCount()>0){
                proceso = this.getProcesoTableModelPendiente().getProceso(0);
                
                this.getProcesoTableModelPendiente().removeRow(0);
                this.getProcesoTableModelEjecucion().addRow(proceso);
                for (int i = 0; i < proceso.getRafagaCpu(); i++) {
                    timer += 1;
                    this.fifoView.getTimerCpu().setText(Integer.toString(timer));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FifoView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.getProcesoTableModelEjecucion().removeRow(0);
                this.getProcesoTableModelTerminado().addRow(proceso);
            }
        }
        
    public FifoView getFifoView() {
        return fifoView;
    }

    private void setFifoView(FifoView fifoView) {
        this.fifoView = fifoView;
    }

    public ProcesoTableModel getProcesoTableModelPendiente() {
        return procesoTableModelPendiente;
    }

    private void setProcesoTableModelPendiente(ProcesoTableModel procesoTableModelPendiente) {
        this.procesoTableModelPendiente = procesoTableModelPendiente;
    }

    public ProcesoTableModel getProcesoTableModelEjecucion() {
        return procesoTableModelEjecucion;
    }

    private void setProcesoTableModelEjecucion(ProcesoTableModel procesoTableModelEjecucion) {
        this.procesoTableModelEjecucion = procesoTableModelEjecucion;
    }

    public ProcesoTableModel getProcesoTableModelTerminado() {
        return procesoTableModelTerminado;
    }

    private void setProcesoTableModelTerminado(ProcesoTableModel procesoTableModelTerminado) {
        this.procesoTableModelTerminado = procesoTableModelTerminado;
    }
    
}
