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

public class Hilo_Busqueda_Secuencias implements Runnable{
    
    public static Thread t_busqueda_secuencias;
    
    public String entrada;
    public String salida;
    public String mutaciones;
    public int formato;
    
    //Gestiona el hilo de Busqueda de Secuencias.
    //Manages the Sequence Search thread.
    
    public Hilo_Busqueda_Secuencias(String entrada, String salida, String mutaciones, int formato){
        
        this.entrada = entrada;
        this.salida = salida;
        this.mutaciones = mutaciones;
        this.formato = formato;
        
        t_busqueda_secuencias = new Thread(this, "Hilo_Busqueda_Secuencias");
        t_busqueda_secuencias.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Busqueda_Secuencias.cargarBusquedaSecuencias(this.entrada, this.salida, this.mutaciones, this.formato);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Busqueda_Secuencias.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
