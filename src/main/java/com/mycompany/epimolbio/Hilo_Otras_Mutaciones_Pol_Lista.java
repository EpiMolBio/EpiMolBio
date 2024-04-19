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

public class Hilo_Otras_Mutaciones_Pol_Lista implements Runnable {
    
    public static Thread t_otras_mutaciones_pol_lista;
    
    public String entrada;
    public String salida;
    public int seleccionProteina; 
    public int cribado;
    public int virus;
    
    //Gestiona el hilo de Otras Mutaciones Pol Lista.
    
    public Hilo_Otras_Mutaciones_Pol_Lista(String entrada, String salida, int seleccionProteina, int cribado, int virus){
        
        this.entrada = entrada;
        this.salida = salida;
        this.seleccionProteina = seleccionProteina;
        this.cribado = cribado;
        this.virus = virus;
        
        t_otras_mutaciones_pol_lista = new Thread(this, "Hilo_Otras_Mutaciones_Pol_Lista");
        t_otras_mutaciones_pol_lista.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Otras_Mutaciones_Pol_Lista.cargarOtrasMutacionesPolLista(this.entrada, this.salida, this.seleccionProteina, this.cribado, this.virus);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Otras_Mutaciones_Pol_Lista.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
        
    }
    
}
