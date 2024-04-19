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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Comprobador_Archivos_Vacios {
    
    //Comprueba si hay archivos vacíos y devuelve cuales son.
    
    public static String[] cargarComprobarArchivosVacios(String archivo, String ficheros[]){
        
        try{
        
            String ficherosFiltrado[] = new String[ficheros.length];
        
            for(int i = 0; i < ficherosFiltrado.length; i++){
            
                ficherosFiltrado[i] = "";
            
            }
                       
            int contadorFicherosFiltrado = 0;
            
            FileReader f;
            BufferedReader b;
            
            for (String fichero : ficheros) {
                
                if (fichero.equals("")) {
                    
                    break;
                    
                }
                
                f = new FileReader(archivo + fichero);
                b = new BufferedReader(f);
                
                if ((b.readLine()) != null) {
                    
                    ficherosFiltrado[contadorFicherosFiltrado] = fichero;              
                    contadorFicherosFiltrado++;
                    
                }
            }
                                
            return ficherosFiltrado;
        
        }catch(IOException e){
           
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Comprobador_Archivos_Vacios.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
       
        return null;
        
    }
}
