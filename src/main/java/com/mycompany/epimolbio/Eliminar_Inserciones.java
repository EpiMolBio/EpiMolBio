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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Eliminar_Inserciones {

    //Genera los archivos de la funcionalidad de Eliminar Inserciones. Elimina las inserciones respecto a una secuencia de referencia.
    //Generates the files for the Remove Insertions functionality. Removes insertions relative to a reference sequence.
    
    public static void cargarEliminarInserciones(String entrada, String salida, String fichero, String secuenciaComparadora) {
        
        try{
        
            String comparador = secuenciaComparadora; 
            String archivo = entrada + fichero;
            String salidaArchivo = salida;
                
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
		        
            String temp = "";
            String cadena;
        
            String ficheroSinExtension = "";
        
            for(int i = 0; i < fichero.length(); i++){
            
                if(fichero.toCharArray()[i] == '.'){
                
                    break;
                
                }
            
                ficheroSinExtension += fichero.toCharArray()[i]; 
            
            }
		            
            if(idioma == 1){
            
                salidaArchivo = salidaArchivo + "Inserciones_Eliminadas_" + ficheroSinExtension;
		    
            }else{
                
                salidaArchivo = salidaArchivo + "Insertions_Deleted_" + ficheroSinExtension;
                
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
                            
                            temp = eliminarInserciones(comparador, temp);
		                            
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
                
                salidaArchiv.write(eliminarInserciones(comparador, temp));
		            
                salidaArchiv.close();
                b.close();
		                
            }catch(Exception e){
                
                Error.setLocationRelativeTo(null);
                Error.setVisible(true);
                 
                Logger.getLogger(Eliminar_Inserciones.class.getName()).log(Level.SEVERE, null, e);
                
                btn_presionado = false;
                
                Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
                Terminar_Hilos.cargarTerminarHilos();
                
            }
        
        }catch(FileNotFoundException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Eliminar_Inserciones.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }  
    
    //Elimina las inserciones respecto a una secuencia de referencia y devuelve el resultado.
    //Removes insertions relative to a reference sequence and returns the result.
    
    private static String eliminarInserciones(String patron, String secuenciaComprobacion) {
	
        try{
        
            String secuenciaProcesada = "";
		
            for(int x = 0; x < secuenciaComprobacion.length(); x++) {
				
                if(patron.toCharArray()[x] != '-') {
					
                    secuenciaProcesada += secuenciaComprobacion.toCharArray()[x];
					
                }
						
            }
		
            return secuenciaProcesada;
            
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);

            Logger.getLogger(Eliminar_Inserciones.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return "";
            
        }	
    }
}
