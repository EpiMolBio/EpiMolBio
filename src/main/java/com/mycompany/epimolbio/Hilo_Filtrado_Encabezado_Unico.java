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

public class Hilo_Filtrado_Encabezado_Unico implements Runnable{
    
    public static Thread t_filtrado_encabezado_unico;
    
    public String entrada;
    public String salida;
    public String separador;
    public int posicionMarcado;
    public int eliminarGaps;
    public int traducir;
    
    //Gestiona el hilo de Filtrado Encabezado Único.
    //Manages the Unique Header Filtering thread.
    
    public Hilo_Filtrado_Encabezado_Unico(String entrada, String salida, String separador, int posicionMarcado, int eliminarGaps, int traducir){
        
        this.entrada = entrada;
        this.salida = salida;
        this.separador = separador;
        this.posicionMarcado = posicionMarcado;
        this.eliminarGaps = eliminarGaps;
        this.traducir = traducir;
        
        t_filtrado_encabezado_unico = new Thread(this, "Hilo_Filtrado_Encabezado_Unico");
        t_filtrado_encabezado_unico.start();
        
    }
    
    @Override
    public void run(){
        
        try{
        
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Filtrado_Encabezado_Unico.cargarFiltradoEncabezadoUnico(this.entrada, this.salida, this.separador, this.posicionMarcado, this.eliminarGaps, this.traducir);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Filtrado_Encabezado_Unico.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
    
}
