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

public class Hilo_Contar_Secuencias_Tabla implements Runnable{
    
    public static Thread t_contar_secuencias_tabla;
    
    public String entrada;
    public String salida;
    public int seleccion;
    
    //Gestiona el hilo de Contar Secuencias Tabla.
    //Manages the Sequence Count Table thread.
    
    public Hilo_Contar_Secuencias_Tabla(String entrada, String salida, int seleccion){
        
        this.entrada = entrada;
        this.salida = salida;
        this.seleccion = seleccion;
        
        t_contar_secuencias_tabla = new Thread(this, "Hilo_Contar_Secuencias_Tabla");
        t_contar_secuencias_tabla.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Contar_Secuencias_Tabla.cargarContarSecuenciasTabla(this.entrada, this.salida, this.seleccion);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Contar_Secuencias_Tabla.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }

    }
    
}
