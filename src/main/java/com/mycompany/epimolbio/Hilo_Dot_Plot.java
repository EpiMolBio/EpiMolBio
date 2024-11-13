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

public class Hilo_Dot_Plot implements Runnable {
    
    public static Thread t_dot_plot;
    
    public String secuenciaA;
    public String secuenciaB;
    public String salida;
    
    //Gestiona el hilo del Dot Plot.
    //Manages the Dot Plot thread.
    
    public Hilo_Dot_Plot(String salida, String secuenciaA, String secuenciaB){
        
        this.secuenciaA = secuenciaA;
        this.secuenciaB = secuenciaB;
        this.salida = salida;
        
        t_dot_plot = new Thread(this, "Hilo_Dot_Plot");
        t_dot_plot.start();
    }
    
    @Override
    public void run(){
        
        try{
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Generador_Archivos_Dot_Plot.cargarGeneradorArchivosDotPlot(this.salida, this.secuenciaA, this.secuenciaB);
        
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Dot_Plot.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
