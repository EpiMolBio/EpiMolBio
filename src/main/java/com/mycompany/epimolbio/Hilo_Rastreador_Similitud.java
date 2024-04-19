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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Hilo_Rastreador_Similitud implements Runnable{
    
    public static Thread t_rastreador_similitud;
    
    public final String archivoCarga;
    public String archivoGuardado;
    public int rangoInferior;
    public int rangoSuperior;
    public String ejemplo;
    public int tipoSecuencia;
    public double homologia;
    
    //Gestiona el hilo del Rastreador por Similitud.
    
    public Hilo_Rastreador_Similitud(final String archivoCarga, String archivoGuardado, int rangoInferior, int rangoSuperior, String ejemplo, int tipoSecuencia, double homologia){
        
        this.archivoCarga = archivoCarga;
        this.archivoGuardado = archivoGuardado;
        this.rangoInferior = rangoInferior;
        this.rangoSuperior = rangoSuperior;
        this.ejemplo = ejemplo;
        this.tipoSecuencia = tipoSecuencia;
        this.homologia = homologia;
       
        t_rastreador_similitud = new Thread(this, "Hilo_Rastreador_Similitud");
        t_rastreador_similitud.start();
    }
    
    @Override
    public void run(){
        
        try {
        
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Rastreador_Similitud.cargarRastreadorSimilitud(this.archivoCarga, this.archivoGuardado, this.rangoInferior, this.rangoSuperior, this.ejemplo, this.tipoSecuencia, this.homologia);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (IOException ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Rastreador_Similitud.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
