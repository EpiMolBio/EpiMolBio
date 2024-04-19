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

public class Hilo_Buscar_Reemplazar implements Runnable{
    
    public static Thread t_buscar_reemplazar;
    
    public String entrada;
    public String salida;
    public String buscar;
    public String reemplazar;
    public int modificacionArchivoEntero;
    
    //Gestiona el hilo de Buscar y Reemplazar.
    
    public Hilo_Buscar_Reemplazar(String entrada, String salida, String buscar, String reemplazar, int modificacionArchivoEntero){
        
        this.entrada = entrada;
        this.salida = salida;
        this.reemplazar = reemplazar;
        this.buscar = buscar;
        this.modificacionArchivoEntero = modificacionArchivoEntero;
        
        t_buscar_reemplazar = new Thread(this, "Hilo_Buscar_Reemplazar");
        t_buscar_reemplazar.start();
        
    }
    
    @Override
    public void run(){
       
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Buscar_Reemplazar.cargarBuscarReemplazar(this.entrada, this.salida, this.buscar, this.reemplazar, this.modificacionArchivoEntero);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Buscar_Reemplazar.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
       
    }
    
}
