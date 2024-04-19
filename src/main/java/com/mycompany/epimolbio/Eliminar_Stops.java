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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.mycompany.epimolbio.Interfaz.Error;
import static com.mycompany.epimolbio.Interfaz.btn_presionado;
import static com.mycompany.epimolbio.Interfaz.idioma;
import java.io.IOException;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Eliminar_Stops {
    
    //Elimina las secuencias que contienen stops de uno o varios archivos .fasta. Funcionalidad no disponible desde el interfaz.
    
    public static void cargarEliminarStops(String carpeta, String carpetaSalida){
        
        try{
        
            String ficheros[];
            File carpetaEntrada = new File(carpeta);
            ficheros = carpetaEntrada.list();
            Arrays.sort(ficheros);
        
            String etiqueta = "";
            
            if(idioma == 1){
                
                etiqueta = "/stops filtrados_";
                
            }else if(idioma == 2){
                
                etiqueta = "/stops filtered_";
                
            }
            
            for (String fichero : ficheros) {
                
                filtrarStopsArchivo(carpeta + "/" + fichero, carpetaSalida + etiqueta + fichero);
                
            }
        
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Eliminar_Stops.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();

        }
        
    }
    
    //Genera uno o varios archivos .fasta filtrando las secuencias con stops que contienen el o los .fasta de entrada.
    
    public static void filtrarStopsArchivo(String archivo, String carpetaSalida){
        
        try{
            
            FileReader f = new FileReader(archivo);
            
            try (BufferedReader b = new BufferedReader(f); FileWriter salida_ = new FileWriter(carpetaSalida)) {
                                
                String temp = "";
                
                String cadena;
                
                String encabezado = "";
                String secuencia;
                
                while((cadena = b.readLine()) != null) {
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        if(temp.length() != 0) {
                            
                            secuencia = temp.toUpperCase();
                            
                            if(!secuencia.contains("*")){
                                
                                salida_.write(encabezado + "\r\n" + secuencia + "\r\n");
                                
                            }
                            
                        }
                        
                        encabezado = cadena;
                        
                        if(temp.length() != 0) {
                            
                            temp = "";
                            
                        }else{
                            
                            temp = "";
                            
                        }
                        
                    }else {
                        
                        temp += cadena;
                        
                    }
                    
                }
                
                secuencia = temp.toUpperCase();
                
                if(!secuencia.contains("*")){
                    
                    salida_.write(encabezado + "\r\n" + secuencia + "\r\n");
                    
                }
                
            }
            
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Eliminar_Stops.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
            
    }
    
}
