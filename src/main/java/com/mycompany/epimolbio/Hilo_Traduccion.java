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

public class Hilo_Traduccion implements Runnable{

    public static Thread t_traduccion;
    
    public String CarpetaEntrada;
    public String archivoEntrada;
    public int selectorFiltro[];
    public int traducirNoFiltro;
    public int gapsNoFiltro;
    public int marco;
    public String directorioSalida;
    public int seleccionFiltro;
    public int traducirFiltro;
    public String separador;
    
    //Gestiona el hilo de Traducción.
    
    public Hilo_Traduccion(String CarpetaEntrada, String archivoEntrada, int selectorFiltro[], int traducirNoFiltro, int gapsNoFiltro, int marco, String directorioSalida, 
            int seleccionFiltro, int traducirFiltro, String separador){
        
        this.CarpetaEntrada = CarpetaEntrada;
        this.archivoEntrada = archivoEntrada;
        this.selectorFiltro = selectorFiltro;
        this.traducirNoFiltro = traducirNoFiltro;
        this.gapsNoFiltro = gapsNoFiltro;
        this.marco = marco;
        this.directorioSalida = directorioSalida;
        this.seleccionFiltro = seleccionFiltro;
        this.traducirFiltro = traducirFiltro;
        this.separador = separador;
        
        t_traduccion = new Thread(this, "Hilo_Traduccion");
        t_traduccion.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            Generador_Archivos_Traductor_Gaps.cargarGeneradorArchivosTraductorGaps(this.CarpetaEntrada, this.archivoEntrada, this.selectorFiltro, this.traducirNoFiltro, this.gapsNoFiltro, this.marco, this.directorioSalida,
                    this.seleccionFiltro, this.traducirFiltro, this.separador);
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        }catch (Exception e) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Traduccion.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }   
    }
}
