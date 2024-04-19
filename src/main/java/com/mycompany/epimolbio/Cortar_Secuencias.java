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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Cortar_Secuencias {
    
    /*Corta las secuencias de uno o varios archivos .fasta y genera una salida en formato .fasta con el resultado. Funcionalidad no disponible desde el interfaz.
    Este corte se hace entre las posiciones indicadas*/
    
    public static void cargarCortarSecuencias(String carpetaRuta, String carpetaSalida, int rangoInferior, int rangoSuperior) {
	
        try{
        
            String ficheros[];
            File carpetaEntrada = new File(carpetaRuta);
            ficheros = carpetaEntrada.list();
            Arrays.sort(ficheros);
		
            for (String fichero : ficheros) {
                
                lectorSecuencias(carpetaRuta + "/" + fichero, carpetaSalida + "/" + fichero, rangoInferior, rangoSuperior);
            
            }
        
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Cortar_Secuencias.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
	
    //Corta la secuencia entre los valores de las posiciones introducidas y devuelve el encabezado con la secuencia cortada.
    
    public static String cortarSecuencias(String secuencia, String encabezado, int valorMinimo, int valorMaximo) {
        
        try{
        
            if(secuencia.length() > valorMaximo) {
		
                secuencia = secuencia.substring(valorMinimo, valorMaximo);
                return encabezado + "\r\n" + secuencia + "\r\n";
		
            }else{
                
                return "";
                
            }
        
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Cortar_Secuencias.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return "";
            
        }
	
    } 
	    
    public static void lectorSecuencias(String archivo, String carpetaSalida, int rangoInferior, int rangoSuperior) throws IOException {
	
        try{
        
            FileReader f = new FileReader(archivo);
            
            try (BufferedReader b = new BufferedReader(f); FileWriter salida_ = new FileWriter(carpetaSalida)) {
                                
                String respuestaTemporal;
                
                String temp = "";
                
                String cadena;
                
                String encabezado = "";
                String secuencia;
                
                while((cadena = b.readLine()) != null) {
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        if(temp.length() != 0) {
                            
                            secuencia = temp.toUpperCase();
                            
                            respuestaTemporal = cortarSecuencias(secuencia, encabezado, rangoInferior, rangoSuperior);
                            
                            if(!respuestaTemporal.equals("")){
                                
                                salida_.write(respuestaTemporal);
                                
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
                
                secuencia = temp;
                
                respuestaTemporal = cortarSecuencias(secuencia, encabezado, rangoInferior, rangoSuperior);
                
                if(!respuestaTemporal.equals("")){
                    
                    salida_.write(respuestaTemporal);
                    
                }
                
            }
            
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Cortar_Secuencias.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
}
