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
import static com.mycompany.epimolbio.Interfaz.idioma;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Traductor_Gaps {
    
    /*Gestiona la traducción de secuencias y la eliminación de gaps en el programa.
    Esto lo hace valiendose de las clases Traductor y Eliminacion_Gaps*/
    
    /*Manages the sequence translation and gap removal in the program.
    This is done using the Translator and Gap_Removal classes.*/
    
    public static void cargarTraductorGaps(String archivo, int traducir, int eliminarGaps, int marco, String salidaArchivo, String Fichero) throws FileNotFoundException, IOException{
            
        try{
            
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
        
            String temp = "";
            String cadena;
            
            salidaArchivo += "/";
            
            for(int i = 0; i < Fichero.length(); i++){
                
                if(Fichero.toCharArray()[i] != '.'){
                    
                   if(i == 0){
                       
                        if(traducir == 1){
                
                            if(idioma == 1){
                
                                salidaArchivo = salidaArchivo + "Traducido_";
                
                            }else{
                    
                                salidaArchivo = salidaArchivo + "Translated_" ;
                    
                            }
                
                        }
            
                        if(eliminarGaps == 1){
                
                            if(idioma == 1){
                
                                salidaArchivo = salidaArchivo + "Sin_Gaps_";
                
                            }else{
                    
                                salidaArchivo = salidaArchivo + "No_Gaps_";
                    
                            }
                        }
                       
                   }
                    
                   salidaArchivo += (Fichero.toCharArray()[i]);
                   
                }
                
                else{
                    
                    break;
                    
                }
            }
            
            boolean mostrarPrimerEncabezado = true;
            
            salidaArchivo = salidaArchivo + ".fasta";
                        
            try (FileWriter salidaArchiv = new FileWriter(salidaArchivo)) {
                        
                while((cadena = b.readLine()) != null) {
                		
                    if(mostrarPrimerEncabezado == true){
                    
                        salidaArchiv.write(cadena);
                        salidaArchiv.write('\n');
                    
                        mostrarPrimerEncabezado = false;
                    }
                
                    if(cadena.toCharArray()[0] == '>') {				
                    
                        if(temp.length() != 0) {
                            
                            temp = temp.toUpperCase();
                            
                            if(eliminarGaps == 1){
                             
                                temp = Eliminacion_Gaps.cargarEliminacionGaps(temp);
                            }
                        
                            if(traducir == 1){
                            
                                temp = Traductor.cargarTraductor(temp, marco);
                            }
                         
                            salidaArchiv.write(temp);
                            salidaArchiv.write("\r\n");
                            salidaArchiv.write(cadena);
                            salidaArchiv.write("\r\n");
                        		
                            temp = "";
						
                        }else{
                        
                            temp = "";
                        
                        }
                    }else {
                    
                        temp += cadena;
                    }

                }
            
                temp = temp.toUpperCase();
                
                if(eliminarGaps == 1){
                             
                    temp = Eliminacion_Gaps.cargarEliminacionGaps(temp);
                
                }
                        
                if(traducir == 1){
                            
                    temp = Traductor.cargarTraductor(temp, marco);
                
                }
            
                salidaArchiv.write(temp);
            
                salidaArchiv.close();
            
            }catch(Exception e){
                
                Error.setLocationRelativeTo(null);
                Error.setVisible(true);
                
                Logger.getLogger(Traductor_Gaps.class.getName()).log(Level.SEVERE, null, e);
                
                btn_presionado = false;
                
                Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
                Terminar_Hilos.cargarTerminarHilos();
                
            }
            
            b.close();
            
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
              
            Logger.getLogger(Traductor_Gaps.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
}
