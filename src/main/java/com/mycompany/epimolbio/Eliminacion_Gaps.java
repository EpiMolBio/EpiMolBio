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

public class Eliminacion_Gaps {
    
    //Elimina los gaps de una secuencia introducida.
    
    public static String cargarEliminacionGaps(String secuencia){
        
        try{
        
            String secuenciaProblema = secuencia;
 							
            char secuenciaProblemaArr[];
 							
            secuenciaProblemaArr = secuenciaProblema.toCharArray();
 							
            String secuenciaSalida = "";
 							
            for(int x = 0; x < secuenciaProblema.length(); x++) {
 								
                if(secuenciaProblemaArr[x] != '-') {
 								
                    secuenciaSalida += secuenciaProblemaArr[x];
                
                }
 							
            }
 	
            return secuenciaSalida;
         							
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Eliminacion_Gaps.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return "";
            
        }
    }
    
}
