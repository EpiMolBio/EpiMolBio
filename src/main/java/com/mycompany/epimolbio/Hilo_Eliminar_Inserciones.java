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

public class Hilo_Eliminar_Inserciones implements Runnable {
    
    public static Thread t_eliminar_inserciones;
    
    public String entrada;
    public String salida;
    public String secuenciaComparadora;
    
    //Gestiona el hilo de Eliminar Inserciones.
    //Manages the Remove Insertions thread.
    
    public Hilo_Eliminar_Inserciones(String entrada, String salida, String secuenciaComparadora){
        
        this.entrada = entrada; 
        this.salida = salida;
        this.secuenciaComparadora = secuenciaComparadora;
        
        t_eliminar_inserciones = new Thread(this, "Hilo_Eliminar_Inserciones");
        t_eliminar_inserciones.start();
        
        
    }
    
    @Override
    public void run(){
        
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Generador_Archivos_Eliminar_Inserciones.cargarGeneradorArchivosEliminarInserciones(this.entrada, this.salida, this.secuenciaComparadora);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Eliminar_Inserciones.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
        
    }
    
}
