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
import static com.mycompany.epimolbio.Interfaz.idioma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Hilo_Filtrado_Secuencias_Parciales implements Runnable {
    
    public static Thread t_filtrado_secuencias_parciales;
    
    public String archivo;
    public String archivoGuardado;
    public double porcentaje;
    public int seleccionTipo;
    
    //Gestiona el hilo de Filtrado de Secuencias Parciales.
    //Manages the Partial Sequence Filtering thread.
    
    public Hilo_Filtrado_Secuencias_Parciales(String archivo, String archivoGuardado, double porcentaje, int seleccionTipo){
        
        this.archivo = archivo;
        this.archivoGuardado = archivoGuardado;
        this.porcentaje = porcentaje;
        this.seleccionTipo = seleccionTipo;
        
        t_filtrado_secuencias_parciales = new Thread(this, "Hilo_Filtrado_Secuencias_Parciales");
        t_filtrado_secuencias_parciales.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            String fichero_salida;
            
            if(idioma == 1){
                
                fichero_salida = "/Secuencias_Perdidas.html";
                
            }else{
                
                fichero_salida = "/Lost_Sequences.html";
                
            }
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Filtrado_Secuencias_Parciales.cargarFiltradoSecuenciasParciales(this.archivo, this.archivoGuardado, this.porcentaje, this.seleccionTipo,
            false);
            
            Filtrado_Secuencias_Parciales.cargarFiltradoSecuenciasParciales(this.archivo, this.archivoGuardado + fichero_salida, this.porcentaje, this.seleccionTipo,
            true);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Filtrado_Secuencias_Parciales.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
