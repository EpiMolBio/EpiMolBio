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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Buscar_Reemplazar {
    
    /*Busca y reemplaza una serie de caracteres en secuencias en formato .fasta y genera una o varias salidas .fasta con el resultado. 
    Esto puede realizarlo tanto en el encabezado como en la secuencia*/
    
    /*Search for and replace a series of characters in sequences in .fasta format and generate one or more .fasta output files with the result.
    This can be done in both the header and the sequence.*/
    
    public static void cargarBuscarReemplazar(String entrada, String salida, String buscar, String reemplazar, int modificacionArchivoEntero){
        
        try{
        
            buscar = buscar.toUpperCase();
            reemplazar = reemplazar.toUpperCase();
            
            String ficheros[];
            
            File entrada_ = new File(entrada);
            
            ficheros = entrada_.list();
            
            String archivo;
            
            FileReader f;
            BufferedReader b;
            
            FileWriter salidaArchivo; 
                
            String cadena;
                
            for (String fichero : ficheros) {
                
                archivo = entrada + "/" + fichero;
                
                if (idioma == 1) {
                    
                    salidaArchivo = new FileWriter(salida + "/Reemplazar_" + fichero);
                
                } else {
                    
                    salidaArchivo = new FileWriter(salida + "/Replace_" + fichero);
                
                }
                
                f = new FileReader(archivo);
                b = new BufferedReader(f);
                
                String temp = "";
                
                while((cadena = b.readLine()) != null) {
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        if(temp.length() != 0) {
            	
                            if(modificacionArchivoEntero == 1){
                        
                                temp = temp.toUpperCase();
                                salidaArchivo.write(temp.replace(buscar, reemplazar) + "\r\n");
            		
                            }else{
                                
                                temp = temp.toUpperCase();
                                salidaArchivo.write(temp + "\r\n");
                            
                            }
                            
                        }
                        
                        if(modificacionArchivoEntero == 2){
                    
                            cadena = cadena.toUpperCase();
                            salidaArchivo.write(cadena.replace(buscar, reemplazar) + "\r\n");
                        
                        }else{
                        
                            salidaArchivo.write(cadena + "\r\n");
                        
                        }
            	            	
                        if(temp.length() != 0) {
            			
                            temp = "";
					
                        }else{
                    
                            temp = "";
                    
                        }
                    
                    }else {
                
                        temp += cadena;
                        
                    }
                    
                }
                
                if(modificacionArchivoEntero == 1){
                        
                    temp = temp.toUpperCase();
                    salidaArchivo.write(temp.replace(buscar, reemplazar) + "\r\n");
            		
                }else{
                          
                    temp = temp.toUpperCase();
                    salidaArchivo.write(temp.toUpperCase() + "\r\n");
                            
                }
                
                salidaArchivo.close();
                b.close();
                
            }
            
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
              
            Logger.getLogger(Buscar_Reemplazar.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
    }
    
}
