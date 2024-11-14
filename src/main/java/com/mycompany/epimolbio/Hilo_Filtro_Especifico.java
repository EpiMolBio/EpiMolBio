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

public class Hilo_Filtro_Especifico implements Runnable{

    public static Thread t_filtro_especifico;
    
    public String entrada;
    public String salida;
    public String composicion;
    
    //Gestiona el hilo de Filtro Específico.
    //Manages the Specific Filter thread.
    
    public Hilo_Filtro_Especifico(String entrada, String salida, String composicion){
        
        this.entrada = entrada;
        this.salida = salida;
        this.composicion = composicion;
        
        t_filtro_especifico = new Thread(this, "Hilo_Filtro_Especifico");
        t_filtro_especifico.start();
        
    }
    
    @Override
    public void run(){
 
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Filtro_Especifico.cargarFiltroEspecifico(this.entrada, this.salida, this.composicion);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Filtro_Especifico.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
