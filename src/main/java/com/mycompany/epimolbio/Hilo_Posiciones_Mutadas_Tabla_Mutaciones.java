/**

    Authors: Roberto Reinosa Fernández, África Holguín Fernández, Paloma Troyano Hernáez

    This software is licensed under Creative Commons Attribution-NonCommercial-NoDerivatives 4.0

    This license enables reusers to copy and distribute the material in any medium or format in unadapted form only, 
    for noncommercial purposes only, and only so long as attribution is given to the creator. 
     
    CC BY-NC-ND includes the following elements:
    
        BY: credit must be given to the creator.
        NC: Only noncommercial uses of the work are permitted.
        ND: No derivatives or adaptations of the work are permitted.

*/

package com.mycompany.epimolbio;

import static com.mycompany.epimolbio.Interfaz.Calculos_Finalizados;
import static com.mycompany.epimolbio.Interfaz.Error;
import static com.mycompany.epimolbio.Interfaz.btn_presionado;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Hilo_Posiciones_Mutadas_Tabla_Mutaciones implements Runnable{
    
    public static Thread t_posiciones_mutadas_tabla_mutaciones;
    
    public String archivoCarga;
    public String archivoGuardado;
    public String wt;
    public double valorMinimo;
    public boolean orden;
    public int seleccion;
    public String mutacionSeleccionada;
    public int tipoTabla;
    
    //Gestiona el hilo de Posiciones Mutadas Tabla Mutaciones.
    
    public Hilo_Posiciones_Mutadas_Tabla_Mutaciones(String archivoCarga, String archivoGuardado, String wt, double valorMinimo, boolean orden, int seleccion, String mutacionSeleccionada, int tipoTabla) throws Exception{
        
        this.archivoCarga = archivoCarga;
        this.archivoGuardado = archivoGuardado;
        this.wt = wt;
        this.valorMinimo = valorMinimo;
        this.orden = orden;
        this.seleccion = seleccion;
        this.mutacionSeleccionada = mutacionSeleccionada;
        this.tipoTabla = tipoTabla;
        
        t_posiciones_mutadas_tabla_mutaciones = new Thread(this, "Hilo_Posiciones_Mutadas_Tabla_Mutaciones");
        t_posiciones_mutadas_tabla_mutaciones.start();
        
    }
    
    @Override
    public void run(){
 
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Posiciones_Mutadas_Tabla_Mutaciones.cargarPosicionesMutadasTablaMutaciones(this.archivoCarga, this.archivoGuardado, this.wt, this.valorMinimo, this.orden, this.seleccion, this.mutacionSeleccionada, false, this.tipoTabla);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Posiciones_Mutadas_Tabla_Mutaciones.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
