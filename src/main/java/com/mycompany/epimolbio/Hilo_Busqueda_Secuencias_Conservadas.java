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

public class Hilo_Busqueda_Secuencias_Conservadas implements Runnable{
    
    public static Thread t_busqueda_secuencias_conservadas; 
    
    public String rutaCarga;
    public String rutaGuardado;
    public double porcentajeHomologia;
    public int valorInferior; 
    public int valorSuperior;
    public int rangoLongitudInferior;
    public int rangoLongitudSuperior;
    
    //Gestiona el hilo de Búsqueda de Secuencias Conservadas.
    //Manages the Conserved Sequences Search thread.
    
    public Hilo_Busqueda_Secuencias_Conservadas(String rutaCarga, String rutaGuardado, double porcentajeHomologia,
                    int valorInferior, int valorSuperior, int rangoLongitudInferior, int rangoLongitudSuperior){
        
        this.rutaCarga = rutaCarga;
        this.rutaGuardado = rutaGuardado;
        this.porcentajeHomologia = porcentajeHomologia;
        this.valorInferior = valorInferior; 
        this.valorSuperior = valorSuperior;
        this.rangoLongitudInferior = rangoLongitudInferior;
        this.rangoLongitudSuperior = rangoLongitudSuperior;
        
        t_busqueda_secuencias_conservadas = new Thread(this, "Hilo_Busqueda_Secuencias_Conservadas");
        t_busqueda_secuencias_conservadas.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Generador_Archivos_Busqueda_Secuencias_Conservadas.cargarGeneradorArchivosBusquedaSecuenciasConservadas(this.rutaCarga, this.rutaGuardado, this.porcentajeHomologia,
                    this.valorInferior, this.valorSuperior, this.rangoLongitudInferior, this.rangoLongitudSuperior);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Busqueda_Secuencias_Conservadas.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
        
    }
    
}
