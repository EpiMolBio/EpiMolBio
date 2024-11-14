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

public class Hilo_Rastreador_Flanqueantes implements Runnable{
    
    public static Thread t_rastreador_flanqueantes;
    
    public String guardado;
    public String carga;
    public int tipoSecuencia;
    public int proteinaSeleccionada;
    public int rangoInf;
    public int rangoSup;
    public String delante;
    public String detras;
    public int tamanoSecuencia;
    
    //Gestiona el hilo del Rastreador por secuencias Flanqueantes.
    //Manages the Flanking Sequences Tracker thread.
    
    public Hilo_Rastreador_Flanqueantes(String carga, String guardado, int tipoSecuencia, int proteinaSeleccionada, int rangoInf, int rangoSup, String delante, String detras, int tamanoSecuencia){
    
        this.guardado = guardado;
        this.carga = carga;
        this.tipoSecuencia = tipoSecuencia;
        this.proteinaSeleccionada = proteinaSeleccionada;
        this.rangoInf = rangoInf;
        this.rangoSup = rangoSup;
        this.delante = delante;
        this.detras = detras;
        this.tamanoSecuencia = tamanoSecuencia;
        
        t_rastreador_flanqueantes = new Thread(this, "Hilo_Rastreador_Flanqueantes");
        t_rastreador_flanqueantes.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Rastreador_Flanqueantes.cargarRastreadorFlanqueantes(this.carga, this.guardado, this.tipoSecuencia, this.proteinaSeleccionada, this.rangoInf, this.rangoSup, this.delante, this.detras, this.tamanoSecuencia);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Rastreador_Flanqueantes.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
