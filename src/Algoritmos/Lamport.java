/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import Procesos.Proceso;
import Procesos.ProcesoTableModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Carlos
 */
public class Lamport implements Lock, Runnable  {
    private LamportView lamportView;
    private Proceso process;

    int n ;
boolean [] choosing; // inside doorway
int [] number;
public Lamport (int numProc) {
n=numProc;
choosing = new boolean[n];
number = new int[n];
for (int j = 0; j <n; j++) {
choosing[j] = false;
number[j]=0;
}
}
public Lamport (LamportView l_LamportView, Proceso l_Process) {
    this.setLamportView(l_LamportView);
    this.process = l_Process;

}

void CriticalSection() {
System.out.println("Proceso " + this.process.getNombre() + " ENTRO en la seccion critica");
}
void nonCriticalSection() {
System.out.println("Proceso " + this.process.getNombre() + " SALIO de la seccion critica\n");
}

@Override
public void accederSC() {
// paso 1: elegir un numero de atencion
// tomamos el ultimo numero disponible
/*choosing[i] = true;
for (int j=0; j<n; j++)   
if (number[j] > number[i])
number[i] = number[j];
number[i]++;
choosing[i] = false;
// paso 2: verificar si mi numero es el menor de todos
for(int j=0; j<n; j++) {
while (choosing[j]) ; // proceso j intentando tomar un numero
//Esta ocupado esperar..
while ( (number[j] !=0) && ( (number[j] < number[i]) || ((number[j] == number[i]) && j<i) ) ) ;
}
*/
boolean aux = false;
while(!aux){
    boolean b = LamportView.procesoTableModelEnCurso.esElMasTemprano(process);
    //boolean b = LamportView.listProcessTerminados.size() == LamportView.listProcess.indexOf(this.process);
    if(!LamportView.seccionCriticaOcupada ){
        LamportView.seccionCriticaOcupada = true;
        aux = true;
        LamportView.procesoTableModelEspera.removeRow(0);
        LamportView.procesoTableModelEnCurso.addRow(process);
        CriticalSection();
    }
}

}
@Override
public void liberarSC () {
    LamportView.procesoTableModelEnCurso.removeRow(0);
    LamportView.seccionCriticaOcupada = false;
    LamportView.listProcessTerminados.add(process);
    LamportView.procesoTableModelSalientes.addRow(process);
    nonCriticalSection();
}

    public LamportView getLamportView() {
        return lamportView;
    }

    public void setLamportView(LamportView lamportView) {
        this.lamportView = lamportView;
    }


    @Override
    public void run() {
            System.out.println("Proceso " + process.getNombre() + " DESEA entrar en la seccion Critica");
        this.accederSC();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Lamport.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.liberarSC();
    }

    
}