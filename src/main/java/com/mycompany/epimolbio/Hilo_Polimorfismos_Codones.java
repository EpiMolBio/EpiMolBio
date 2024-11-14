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

public class Hilo_Polimorfismos_Codones implements Runnable{
    
    public static Thread t_polimorfismos_codones;
    
    public String entrada;
    public String salida;
    public String referencia;
    public int criba;
    
    //Gestiona el hilo de Polimorfismos Codones.
    //Manages the Codon Polymorphisms thread.
    
    public Hilo_Polimorfismos_Codones(String entrada, String salida, String referencia, int criba){
        
        this.entrada = entrada;
        this.salida = salida;
        this.referencia = referencia;
        this.criba = criba;
        
        t_polimorfismos_codones = new Thread(this, "Hilo_Polimorfismos_Codones");
        t_polimorfismos_codones.start();
        
    }
    
    @Override
    public void run(){
        
        try {
        
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Polimorfismos_Codones.cargarPolimorfismosCodones(this.entrada, this.salida, this.referencia, this.criba);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        }catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Polimorfismos_Codones.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
        
    }
    
}
