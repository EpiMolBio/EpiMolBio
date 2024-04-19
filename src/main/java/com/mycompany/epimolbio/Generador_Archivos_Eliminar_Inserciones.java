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
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Generador_Archivos_Eliminar_Inserciones {
    
    /*Carga los archivos los cuales son usados en la funcionalidad de Eliminar Inserciones.
    También genera los archivos con las inserciones eliminadas valiendose de la clase Eliminar_Inserciones.*/
    
    public static void cargarGeneradorArchivosEliminarInserciones(String CarpetaEntradaAlineador, String salidaAlineador, String secuenciaComparadora){
        
        try{
        
            String directorioAlineador = CarpetaEntradaAlineador;
        
            final File carpetaAlineador = new File(directorioAlineador);

            String ficherosAlineador[] = new String[5000];

            for(int i = 0; i < ficherosAlineador.length; i++) {

                ficherosAlineador[i] = "";

            }

            int a = 0;

            String FicheroAlineador;

            for (File ficheroEntradaAlineador : carpetaAlineador.listFiles()) {

                ficherosAlineador[a] = (ficheroEntradaAlineador.getName());
                a++;

            }

            for (String ficherosAlineador1 : ficherosAlineador) {
                
                if (ficherosAlineador1.equals("")) {
                    
                    break;
                    
                }
                
                FicheroAlineador = ficherosAlineador1;
                
                Eliminar_Inserciones.cargarEliminarInserciones(CarpetaEntradaAlineador, salidaAlineador, FicheroAlineador, secuenciaComparadora);
                
            }
            
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);      
                    
            Logger.getLogger(Generador_Archivos_Eliminar_Inserciones.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
    
}
