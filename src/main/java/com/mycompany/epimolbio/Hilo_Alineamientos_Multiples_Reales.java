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

public class Hilo_Alineamientos_Multiples_Reales implements Runnable {
    
    public static Thread t_alineamientos_multiples_real;
    public String entrada;
    public String salida;
    
    //Gestiona el hilo de Alineamientos Múltiples Reales, es decir, de la opción que mantiene las inserciones.
    //Manages the Real Multiple Alignments thread, i.e., the option that preserves insertions.
    
    public Hilo_Alineamientos_Multiples_Reales(String entrada, String salida){
            
        this.entrada = entrada;
        this.salida = salida;
        
        t_alineamientos_multiples_real = new Thread(this, "Hilo_Alineamientos_Multiples_Reales");
        t_alineamientos_multiples_real.start();
        
    }
    
    @Override
    public void run(){
        
        try{
        
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Alineamientos_Multiples_Reales alg_mult_real = new Alineamientos_Multiples_Reales();
            alg_mult_real.cargadorAlineamientosMultiplesReales(this.entrada, this.salida);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
        
        }catch(Exception e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Alineamientos_Multiples_Reales.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
        
        }
        
    }
    
}
