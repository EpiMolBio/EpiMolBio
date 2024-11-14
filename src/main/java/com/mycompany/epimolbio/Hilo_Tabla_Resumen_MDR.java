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
import static com.mycompany.epimolbio.Interfaz.btn_calcular_simples;
import static com.mycompany.epimolbio.Interfaz.btn_presionado;
import static com.mycompany.epimolbio.Interfaz.color1;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Hilo_Tabla_Resumen_MDR implements Runnable{

    public static Thread t_tabla_resumen_mdr;
    
    public String entrada;
    public String salida;
    public int proteina;
    
    //Gestiona el hilo de la Tabla Resumen MDR.
    //Manages the DRM Summary Table thread.
    
    public Hilo_Tabla_Resumen_MDR(String entrada, String salida, int proteina){
        
        this.entrada = entrada;
        this.salida = salida;
        this.proteina = proteina;
        
        t_tabla_resumen_mdr = new Thread(this, "Hilo_Tabla_Resumen_MDR");
        t_tabla_resumen_mdr.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            Botones_Calcular.llamadaCalcular();
            
            btn_presionado = true;
            
            Tabla_Resumen_MDR.cargarTablaResumenMDR(this.entrada, this.salida, this.proteina);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            btn_calcular_simples.setBackground(color1);
            
            Logger.getLogger(Hilo_Tabla_Resumen_MDR.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
