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

public class Filtro_Especifico {
    
    //Genera archivos .fasta a partir de otros, dejando solo aquellas secuencias que tengan en su encabezado una seríe de caracteres concretos.
    
    public static void cargarFiltroEspecifico(String archivo_, String archivoSalida, String busqueda){

        try{
                             
            String tag;
            
            if(idioma == 1){
                
                tag = "/Filtro_Específico_";
                
            }else{
                
                tag = "/Specific_Filter_";
                
            }
                                  
            FileWriter salida;
           
            String archivo;
            
            String ficheros[];
            
            File entrada = new File(archivo_);
            
            ficheros = entrada.list();
            
            String composicion[] = busqueda.split(",");
            
            String composicionProcesada = "";
            
            for(int i = 0; i < composicion.length; i++){
                
                composicionProcesada += composicion[i];
                
                if(i < composicion.length-1){
                    
                    composicionProcesada += "|";
                    
                }
                
            }
            
            boolean pintarSecuencia;
            
            FileReader f;
            BufferedReader b;
            
            String encabezado;
            String secuencia;
            
            String temp;
            String cadena;
            
            for (String fichero : ficheros) {
                
                salida = new FileWriter(archivoSalida + tag + fichero);
                pintarSecuencia = false;
                
                archivo = archivo_ + "/" + fichero;
                f = new FileReader(archivo);
                b = new BufferedReader(f);
                
                temp = "";
                
                while((cadena = b.readLine()) != null) {
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        if(temp.length() != 0) {
            	
                            secuencia = temp;
                            
                            if(pintarSecuencia == true){
                                
                                salida.write(secuencia + "\r\n");
                                pintarSecuencia = false;
                            
                            }
                        }
                        
                        encabezado = cadena;
                        
                        if(encabezado.toUpperCase().contains(composicionProcesada)){
                            
                            salida.write(encabezado.toUpperCase() + "\r\n");
                            pintarSecuencia = true;
                        
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
                
                secuencia = temp;
                
                if(pintarSecuencia == true){
                
                    salida.write(secuencia);
                
                }
                
                b.close();
                salida.close();
                
            }
            
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Filtro_Especifico.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();

        }
        
    }
    
}
