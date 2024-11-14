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

public class Hilo_Similitud_Parcial implements Runnable {
    
    public static Thread t_similitud_parcial;
    
    public String archivoEntrada; 
    public String archivoSalida;
    public String consensoProblema;
    public int tipo;
    public int tamano; 
    public double similitud;
    public int alinear;
    public int traducir;
    
    //Gestiona el hilo de Similitud Parcial.
    //Manages the Partial Similarity thread.
    
    public Hilo_Similitud_Parcial(String archivoEntrada, String archivoSalida, String consensoProblema, int tipo, int tamano, double similitud, int alinear, int traducir){
        
        this.archivoEntrada = archivoEntrada; 
        this.archivoSalida = archivoSalida;
        this.consensoProblema = consensoProblema;
        this.tipo = tipo;
        this.tamano = tamano; 
        this.similitud = similitud;
        this.alinear = alinear;
        this.traducir = traducir;
       
        t_similitud_parcial = new Thread(this, "Hilo_Similitud_Parcial");
        t_similitud_parcial.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Similitud_Parcial.cargarSimilitudParcial(this.archivoEntrada, this.archivoSalida, this.consensoProblema, this.tipo, this.tamano, this.similitud, this.alinear, this.traducir);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Similitud_Parcial.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
