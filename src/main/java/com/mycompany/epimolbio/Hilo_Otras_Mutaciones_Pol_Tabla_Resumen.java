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

public class Hilo_Otras_Mutaciones_Pol_Tabla_Resumen implements Runnable{
    
    public static Thread t_otras_mutaciones_pol_tabla_resumen;
    
    public String entrada;
    public String salida;
    public int virus;
    public int proteina;
    
    //Gestiona el hilo de Otras Mutaciones Pol Tabla Resumen.
    //Manages the Other Pol Mutations Summary Table thread.
    
    Hilo_Otras_Mutaciones_Pol_Tabla_Resumen(String entrada, String salida, int virus, int proteina){
        
        this.entrada = entrada;
        this.salida = salida;
        this.virus = virus;
        this.proteina = proteina;
        
        t_otras_mutaciones_pol_tabla_resumen = new Thread(this, "Hilo_Otras_Mutaciones_Pol_Tabla_Resumen");
        t_otras_mutaciones_pol_tabla_resumen.start();
        
    }
    
    @Override
    public void run(){
        
        try {
        
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Otras_Mutaciones_Pol_Tabla_Resumen.cargarOtrasMutacionesPolTablaResumen(this.entrada, this.salida, this.virus, this.proteina);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        }catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Otras_Mutaciones_Pol_Tabla_Resumen.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
        
    }
        
}
