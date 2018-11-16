/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

/**
 *
 * @author Augusto
 */
public class Proceso implements Comparable<Proceso>, Runnable {
    
    private int id;
    private String nombre;
    private int tiempoLlegada;
    private int rafagaCpu;
    private boolean bloqueado;
    private boolean seleccionado;
    

    public Proceso() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getRafagaCpu() {
        return rafagaCpu;
    }

    public void setRafagaCpu(int rafagaCpu) {
        this.rafagaCpu = rafagaCpu;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    @Override
        public int compareTo(Proceso p) {
            if (this.getTiempoLlegada() < p.getTiempoLlegada()) {
                return -1;
            }
            if (this.getTiempoLlegada() > p.getTiempoLlegada()) {
                return 1;
            }
            return 0;
        }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
