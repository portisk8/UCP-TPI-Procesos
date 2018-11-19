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
    //Creamos instancias hacia los componentes de la vista.
    private FifoView fifoView;
    private ProcesoTableModel procesoTableModelPendiente;
    private ProcesoTableModel procesoTableModelEjecucion;
    private ProcesoTableModel procesoTableModelTerminado;

    
    public Fifo(FifoView f_fifoView, ArrayList<Proceso> listProcess) {//Creamos constructor FIFO el cual recible por variables distancias al componente FifoView y una lista del tipo proceso llamada listProcess
        this.fifoView = f_fifoView;
        Collections.sort(listProcess);//este metodo ordena la lista de procesos
        this.setProcesoTableModelPendiente(new ProcesoTableModel(listProcess));
        this.setProcesoTableModelEjecucion(new ProcesoTableModel(new ArrayList<Proceso>()));
        this.setProcesoTableModelTerminado(new ProcesoTableModel(new ArrayList<Proceso>()));
        
        this.fifoView.getjTableProcesosEspera().setModel(this.getProcesoTableModelPendiente());
        this.fifoView.getjTableProcesoEnCurso().setModel(this.getProcesoTableModelEjecucion());
        this.fifoView.getjTableProcesosTerminados().setModel(this.getProcesoTableModelTerminado());
    }
    @Override
    //Implementamos el metodo run para poder trabajar con hilos
        public void run() {
            Proceso proceso;//creamos un proceso de la clase proceso
            this.fifoView.getTimerCpu().setText("0");//seteamos el valor de la vista fifo con valor cero (0)
            int timer = 0;//creamos una variable timer con inicializacion cero (0)
            while(this.getProcesoTableModelPendiente().getRowCount()>0){//mientras haya procesos en la tabla de pendientes por atender
                proceso = this.getProcesoTableModelPendiente().getProceso(0);//carga proceso de la tabla pendiente a la variable proceso
                
                this.getProcesoTableModelPendiente().removeRow(0);//una vez cargado el proceso se remueva de la tabla pendiente
                this.getProcesoTableModelEjecucion().addRow(proceso);//se envia el proceso a la tabla de procesos en ejecucion
                for (int i = 0; i < proceso.getRafagaCpu(); i++) {//para i iniciando en 0 hasta i menor al tiempo de rafaga de cpu, i aumenta en 1
                    timer += 1;//variable timer aumenta en uno
                    this.fifoView.getTimerCpu().setText(Integer.toString(timer));//setea el valor del componente de la vist fifo para mostrar en la rafaga de cpu el valor del tiempo
                    try {
                        Thread.sleep(1000);//el hilo queda en modo espera por 1 segundo
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FifoView.class.getName()).log(Level.SEVERE, null, ex);//en caso de ocurrir un error en la ejecucion se enviara un mensaje de aviso informando del error
                    }
                }
                this.getProcesoTableModelEjecucion().removeRow(0);//sacamos el proceso de la tabla procesos en ejecucion
                this.getProcesoTableModelTerminado().addRow(proceso);//cargamos el proceso en la tabla procesos terminados
            }
        }
    /*Seters y geters*/    
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
