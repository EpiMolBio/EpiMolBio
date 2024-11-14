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

public class Hilo_Fusionar_Archivos implements Runnable{
  
    public static Thread t_fusionar_archivos;
    
    public String carga;
    public String guardado;
    
    //Gestiona el hilo de Fusionar Archivos.
    //Manages the Merge Files thread.
    
    public Hilo_Fusionar_Archivos(String carga, String guardado){
        
        this.carga = carga;
        this.guardado = guardado;
        
        t_fusionar_archivos = new Thread(this, "Hilo_Fusionar_Archivos");
        t_fusionar_archivos.start();
        
    }
    
    @Override
    public void run(){
             
        try{
        
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Fusionar_Archivos.cargarFusionarArchivos(this.carga, this.guardado);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        }catch(Exception e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);

            Logger.getLogger(Hilo_Fusionar_Archivos.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
        
        }
    }
}
