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

public class Hilo_Frecuencia_Mutacion implements Runnable{

    public static Thread t_frecuencia_mutacion;
    
    public String archivoCarga;
    public String archivoGuardado;
    public String consenso;
    public int alinear;
    public int tipoSecuencia;
    
    //Gestiona el hilo de Frecuencia de Mutación.
    //Manages the Mutation Frequency thread.
    
    public Hilo_Frecuencia_Mutacion(String archivoCarga, String archivoGuardado, String consenso, int alinear, int tipoSecuencia){
        
        this.archivoCarga = archivoCarga;
        this.archivoGuardado = archivoGuardado;
        this.consenso = consenso;
        this.alinear = alinear;
        this.tipoSecuencia = tipoSecuencia;
        
        t_frecuencia_mutacion = new Thread(this, "Hilo_Frecuencia_Mutacion");
        t_frecuencia_mutacion.start();
        
    }
    
    @Override
    public void run(){
       
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Frecuencia_Mutacion.cargarFrecuenciaMutacion(this.archivoCarga, this.archivoGuardado, this.consenso, this.alinear, this.tipoSecuencia);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Frecuencia_Mutacion.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
