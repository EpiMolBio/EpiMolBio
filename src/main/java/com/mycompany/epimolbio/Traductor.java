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

import static com.mycompany.epimolbio.Interfaz.Error;
import static com.mycompany.epimolbio.Interfaz.btn_presionado;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Traductor {
    
    //Gestiona la traducción de secuencias por parte de EpimolBio valiendose de la clase Calculos_Frecuencias_Posicion_Codones.
    //Manages the sequence translation by EpimolBio using the Calculos_Frecuencias_Posicion_Codones class.
    
    public static String cargarTraductor(String secuencia, int marco){
        
        try{
        
            String secuenciaMontada = "";
            String secuenciaTemporal = "";
       
            for(int i = marco; i < secuencia.length()-2; i+=3){
            
                secuenciaTemporal = secuenciaTemporal + secuencia.toCharArray()[i] + secuencia.toCharArray()[i+1] + secuencia.toCharArray()[i+2];
            
                secuenciaTemporal = Calculos_Frecuencias_Posicion_Codones.traduccion(secuenciaTemporal);
            
                if(secuenciaTemporal.equals("STOP")){
             
                    secuenciaTemporal = "*";
                
                }
            
                if(secuenciaTemporal.equals("*") && i == secuencia.length()-3){
             
                    secuenciaTemporal = "";
                
                }
            
                secuenciaMontada += secuenciaTemporal ;
                secuenciaTemporal = "";
                
            }
        
            return secuenciaMontada;
        
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
              
            Logger.getLogger(Traductor.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return "";
            
        }
    } 
}
