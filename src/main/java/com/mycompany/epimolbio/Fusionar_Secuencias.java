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
import static com.mycompany.epimolbio.Interfaz.jComboBox12;
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

public class Fusionar_Secuencias {
    
    /*Genera uno o varios archivos fasta a partir de los .fasta de 2 carpetas de entrada. Se unen las secuencias que tienen el mismo encabezado de los .fasta con el mismo nombre.
    Primero se escribe la secuencia de la carpeta 1 y después la de la carpeta 2. Funcionalidad no disponible desde el interfaz.*/
    
    public static void cargarFusionarSecuencias(String carpetaEntrada, String salida){
        
        try{
        		
            String archivo = carpetaEntrada + "1/";
            String archivo2 = carpetaEntrada + "2/";
		
            final File carpeta = new File(archivo);
            final File carpeta2 = new File(archivo2);
		
            String ficheros[] = new String[5000];
            String ficheros2[] = new String[5000];
        
            for(int i = 0; i < ficheros.length; i++) {
	
                ficheros[i] = "";
                ficheros2[i] = "";
            }

            int a = 0;

            for (File ficheroEntrada : carpeta.listFiles()) {
        
                ficheros[a] = (ficheroEntrada.getName());
                a++;
		
            }
            
            ficheros = Comprobador_Archivos_Vacios.cargarComprobarArchivosVacios(archivo, ficheros);
            
            a = 0;
        
            for (File ficheroEntrada : carpeta2.listFiles()) {
            
                ficheros2[a] = (ficheroEntrada.getName());
                a++;
		
            }
		
            ficheros2 = Comprobador_Archivos_Vacios.cargarComprobarArchivosVacios(archivo2, ficheros);
            
            FileWriter ficheroSalida;
        
            String temp;
            String temp2;
        
            String cadena;
            String cadena2;
        
            String encabezado1;
            String encabezado2;
        
            String archivoActual = "";
        
            boolean salidaBucle;
            boolean salidaBuclePrincipal;
            
            for (String fichero : ficheros) {
                
                if (fichero.equals("")) {
                    
                    break;
                    
                }
                
                for (String ficheros21 : ficheros2) {
                    
                    if (ficheros21.equals("")) {
                        
                        break;
                        
                    }
                    
                    if (fichero.equals(ficheros21)) {
                        
                        archivoActual = fichero;
                        break;
                        
                    } else {
                        
                        archivoActual = "";
                        
                    }
                    
                }
                
                if (!fichero.equals(archivoActual)) {
                    
                    continue;
                    
                }
                
                if(jComboBox12.getSelectedIndex() == 13 || jComboBox12.getSelectedIndex() == 28){
                
                    ficheroSalida = new FileWriter(salida + fichero);
                
                }else{
                    
                    if (idioma == 1) {
                    
                        ficheroSalida = new FileWriter(salida + "Completado_" + fichero);
                    
                    } else {
                    
                        ficheroSalida = new FileWriter(salida + "Filled_" + fichero);
                    
                    }
                    
                }
                
                FileReader f = new FileReader(archivo + archivoActual);
                BufferedReader b2;
                
                try (BufferedReader b = new BufferedReader(f)) {
                    
                    FileReader f2 = new FileReader(archivo2 + archivoActual);
                    b2 = new BufferedReader(f2);
                    
                    salidaBuclePrincipal = true;
                    
                    temp = "";
                    temp2 = "";
                    
                    encabezado1 = "";
                    encabezado2 = "";
                    
                    while(salidaBuclePrincipal) {
                        
                        if((cadena = b.readLine()) == null){
                            
                            cadena = ">";
                            salidaBuclePrincipal = false;
                            
                        }
                        
                        if(cadena.toCharArray()[0] == '>') {
                            
                            if(temp.length() != 0) {
                                
                                f2 = new FileReader(archivo2 + archivoActual);
                                b2 = new BufferedReader(f2);
                                
                                salidaBucle = true;
                                
                                while(salidaBucle) {
                                    
                                    if((cadena2 = b2.readLine()) == null){
                                        
                                        cadena2 = ">";
                                        salidaBucle = false;
                                        
                                    }
                                    
                                    
                                    if(cadena2.toCharArray()[0] == '>') {
                                        
                                        if(temp2.length() != 0) {
                                            
                                            if(encabezado1.equals(encabezado2)) {
                                                
                                                ficheroSalida.write(encabezado1 + "\r\n");
                                                ficheroSalida.write(temp + temp2 + "\r\n");
                                                
                                            }
                                        }
                                        
                                        encabezado2 = cadena2;
                                        
                                        if(temp.length() != 0) {
                                            
                                            temp2 = "";
                                            
                                        }else{
                                            
                                            temp2 = "";
                                            
                                        }
                                        
                                    }else {
                                        
                                        temp2 += cadena2;
                                    }
                                    
                                }
                                
                            }
                            
                            encabezado1 = cadena;
                            
                            if(temp.length() != 0) {
                                
                                temp = "";
                                
                            }else{
                                
                                temp = "";
                                
                            }
                            
                        }else {
                            
                            temp += cadena;
                            
                        }
                        
                    }   
                    
                    ficheroSalida.close();
                }
                
                b2.close();
                
            }
         
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Fusionar_Secuencias.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
        
    }
    
}
