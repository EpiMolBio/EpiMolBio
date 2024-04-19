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

public class Filtrado_Encabezado_Unico {
    
    //Filtra de uno o varios archivos .fasta las secuencias por su encabezado. Previamente hay que marcar el item por el cual se debe filtrar.
    
    public static void cargarFiltradoEncabezadoUnico(String entrada, String salida, String separador, int posicionMarcado, int eliminarGaps, int traducir){
        
        try{
        
            if(!Character.isLetterOrDigit(separador.toCharArray()[0]) && !separador.equals("")) {
			
		separador = "\\" + separador;
			
            }
                        
            String ficheros[];
        
            File entrada_ = new File(entrada);
        
            ficheros = entrada_.list();
        
            for (String fichero : ficheros) {
                
                filtroUnico(entrada + "/" + fichero, salida, posicionMarcado, separador, fichero, eliminarGaps, traducir);
            
            }
        
        }catch(Exception e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Filtrado_Encabezado_Unico.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
        
    }
    
    public static void filtroUnico(String entrada, String salida, int posicionMarcado, String separador, String archivo, int eliminarGaps, int traducir){
        
        try{
            
            FileReader f = new FileReader(entrada);
            
            try (BufferedReader b = new BufferedReader(f)) {
                
                String cadena;
                
                String elementos[] = new String[5000];
                
                for(int i = 0; i < elementos.length; i++) {
                    
                    elementos[i] = "";
                    
                }
                
                String encabezadoTemporal[];
                int contadorArray = 0;
                
                boolean guardarElemento = true;
                
                while((cadena = b.readLine()) != null) {
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        encabezadoTemporal = cadena.toUpperCase().split(separador);
                        
                        if(contadorArray != 0) {
                            
                            for (String elemento : elementos) {
                                
                                if (elemento.equals("")) {
                                    
                                    break;
                                    
                                }
                                
                                if (elemento.equals(encabezadoTemporal[posicionMarcado])) {
                                    
                                    guardarElemento = false;
                                    break;
                                    
                                }
                                
                            }
                            
                            if(guardarElemento == true) {
                                
                                elementos[contadorArray] = encabezadoTemporal[posicionMarcado];
                                contadorArray++;
                                
                            }else {
                                
                                guardarElemento = true;
                                
                            }
                            
                        }else{
                            
                            elementos[contadorArray] = encabezadoTemporal[posicionMarcado];
                            contadorArray++;
                            
                        }

                    }
                
                }
             
                cribado(elementos, entrada, salida, posicionMarcado, separador, archivo, eliminarGaps, traducir);
            }
            
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Filtrado_Encabezado_Unico.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
         
    }
        
    public static void cribado(String encabezados[], String entrada, String salida, int posicionMarcado, String separador, String archivo, int eliminarGaps, int traducir){
		
        try{
            
            String tag;
            
            if(idioma == 1){
                
                tag = "Filtro_Encabezado_";
                
            }else{
                
                tag = "Header_Filter_";
                
            }
            
            String secuencia = "";
            
            FileReader f;
            BufferedReader b;
        
            FileWriter salidaArchivo;
		
            String cadena;
		
            boolean clasificador;
            
            String encabezadoTemporal[];
		
            for (String encabezado : encabezados) {
                
                if (encabezado.equals("")) {
                    
                    break;
                    
                }
                
                clasificador = false;
                
                f = new FileReader(entrada);
                b = new BufferedReader(f);
                
                salidaArchivo = new FileWriter(salida + "/" + tag + archivo.replace(".fasta", "") + "_" + encabezado.replace(">", "") + ".fasta");
                
                while ((cadena = b.readLine()) != null) {
                    
                    if (cadena.toCharArray()[0] == '>') {
                        
                        if(!secuencia.equals("")){
                            
                            if(clasificador == true){
                                
                                if(eliminarGaps == 2){
                            
                                    secuencia = secuencia.replace("-", "");
                                
                                }
                                
                                if(traducir == 1){
                                    
                                    secuencia = Traductor.cargarTraductor(secuencia, 0);
                                    
                                }
                                
                                salidaArchivo.write(secuencia + "\r\n");
                            
                            }
                        }
                        
                        secuencia = "";
                        encabezadoTemporal = cadena.toUpperCase().split(separador);
                        
                        if (encabezadoTemporal[posicionMarcado].equals(encabezado)) {
                            
                            salidaArchivo.write(cadena.toUpperCase() + "\r\n");
                            clasificador = true;
                            
                        } else {
                            
                            clasificador = false;
                            
                        }
                        
                    } else if(clasificador == true) {
                        
                        secuencia += cadena.toUpperCase();
                       
                    }
                }
                
                if(clasificador == true){
                    
                    if(eliminarGaps == 2){
                            
                        secuencia = secuencia.replace("-", "");
                                
                    }
                            
                    if(traducir == 1){
                                
                        secuencia = Traductor.cargarTraductor(secuencia, 0);
                                
                    }
                                                                                
                    salidaArchivo.write(secuencia + "\r\n");
                    
                }
                
                salidaArchivo.close();
            }
				
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Filtrado_Encabezado_Unico.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
                
    }
    
}