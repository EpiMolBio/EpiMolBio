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
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Busqueda_Secuencias {
    
    //Busca secuencias con una serie de mutaciones concretas y las guarda en un archivo .fasta o .csv según se elija.
    
    public static void cargarBusquedaSecuencias(String entrada, String salida, String mutaciones, int formato){
        
        try{
                           
            String tag;
            
            if(idioma == 1){
                
                tag = "Búsqueda_";
                
            }else{
                
                tag = "Search_";
                
            }
            
            int contadorCoincidencias = 0;
                        
            String mutacionesSeparadas[] = mutaciones.split(","); 
            
            String mutacionesSeparadasNumerico[] = new String[mutacionesSeparadas.length];
            String mutacionesSeparadasAminoacido[] = new String[mutacionesSeparadas.length];
            
            for(int i = 0; i < mutacionesSeparadasAminoacido.length ; i++){
                
                mutacionesSeparadasAminoacido[i] = "" + String.valueOf(mutacionesSeparadas[i].toCharArray()[mutacionesSeparadas[i].length() -1]);
                
            }
            
            for(int i = 0; i < mutacionesSeparadasNumerico.length; i++){
                
                mutacionesSeparadasNumerico[i] = "";
                
                for(int b = 1; b < mutacionesSeparadas[i].length()-1 ; b++){
                    
                    mutacionesSeparadasNumerico[i] += mutacionesSeparadas[i].toCharArray()[b];
                    
                }
            }
            
            String encabezadoTemporal = "";
                                
            File carpeta = new File(entrada);
            
            String ficheros[] = carpeta.list();
            
            Arrays.sort(ficheros);
            
            FileWriter salidaArchivo = null;
            
            if(formato == 1){
            
                salidaArchivo = new FileWriter(salida);
            }
            
            FileReader f;
            BufferedReader b;
            
            String temp;
            String cadena;
            
            for (String fichero : ficheros) {
                
                if (formato == 1) {
                    
                    salidaArchivo.write(fichero + "\r\n");
                    
                } else if (formato == 2) {
                    
                    salidaArchivo = new FileWriter(salida + "/" + tag + fichero);
                    
                }
                
                f = new FileReader(entrada + fichero);
                b = new BufferedReader(f);
                temp = "";
                
                while((cadena = b.readLine()) != null) {
    			
                    contadorCoincidencias = 0;
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        if(temp.length() != 0) {
            	
                            temp = temp.toUpperCase();
                            
                            for(int s = 0; s < mutacionesSeparadas.length; s++){
                                
                                if(temp.toCharArray()[Integer.parseInt(mutacionesSeparadasNumerico[s])-1] == mutacionesSeparadasAminoacido[s].toCharArray()[0]){
                                    
                                    contadorCoincidencias++;
                                
                                }
                            
                            }
                            
                            if(contadorCoincidencias == mutacionesSeparadas.length){
                                
                                if(formato == 1){
                                    
                                    salidaArchivo.write(encabezadoTemporal + ";" + temp + "\r\n");
                                    
                                }else if(formato == 2){
                                    
                                    salidaArchivo.write(encabezadoTemporal + "\r\n" + temp + "\r\n");
                                    
                                }                    
                            }
                        
                            contadorCoincidencias = 0;
                        }
                        
                        encabezadoTemporal = cadena;
            	            	
                        if(temp.length() != 0) {
            			
                            temp = "";
					
                        }else{
                    
                            temp = "";
                    
                        }
                        
                    }else {
                
                        temp += cadena;
                        
                    }
                    
                }
                
                temp = temp.toUpperCase();
                
                for(int s = 0; s < mutacionesSeparadas.length; s++){
                         
                    if(temp.toCharArray()[Integer.parseInt(mutacionesSeparadasNumerico[s])-1] == mutacionesSeparadasAminoacido[s].toCharArray()[0]){
                                
                        contadorCoincidencias++;
                                
                    }
                            
                }
                
                if(contadorCoincidencias == mutacionesSeparadas.length){
                            
                    temp = temp.toUpperCase();
                    
                    if(formato == 1){
                                
                        salidaArchivo.write(encabezadoTemporal + ";" + temp + "\r\n");
                                    
                    }else if(formato == 2){
                                   
                        salidaArchivo.write(encabezadoTemporal + "\r\n" + temp + "\r\n");
                        
                    }                    
                }
                
                if(formato == 2){
                    
                    salidaArchivo.close();
                    
                }
                
            }
            
            if(formato == 1){
            
                salidaArchivo.close();
            
            }
            
            if(formato == 2){
            
                eliminarArchivosVacios(salida + "/" + tag, ficheros);
            
            }
            
        }catch(IOException | NumberFormatException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Busqueda_Secuencias.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
    
    //Elimina los archivos generados que no contienen secuencias.
    
    public static void eliminarArchivosVacios(String carpetaGuardado , String ficheros[]){
        
        try{
            
            String ficherosNoVacios[];
            
            ficherosNoVacios = Comprobador_Archivos_Vacios.cargarComprobarArchivosVacios(carpetaGuardado, ficheros);
            
            System.gc();
            
            File temporalBorrado;
            
            boolean vacio;
            
            for (String fichero : ficheros) {
                
                vacio = true;
                
                for (String ficherosNoVacio : ficherosNoVacios) {
                    
                    if (ficherosNoVacio.equals(fichero)) {
                        
                        vacio = false;
                        
                    }
                    
                }
                
                if (vacio) {
                    
                    temporalBorrado = new File(carpetaGuardado + fichero);
                    darPermisos(temporalBorrado);
                    temporalBorrado.delete();
                    
                }
            }
            
            
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Busqueda_Secuencias.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
        
        
    }
    
    //Da permisos a los archivos vacios para poder eliminarlos.
    
    public static void darPermisos(File file) throws IOException{
    
        try{
        
            Set<PosixFilePermission> perms = new HashSet<>();
        
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            perms.add(PosixFilePermission.OWNER_EXECUTE);

            perms.add(PosixFilePermission.OTHERS_READ);
            perms.add(PosixFilePermission.OTHERS_WRITE);
            perms.add(PosixFilePermission.OTHERS_EXECUTE);

            perms.add(PosixFilePermission.GROUP_READ);
            perms.add(PosixFilePermission.GROUP_WRITE);
            perms.add(PosixFilePermission.GROUP_EXECUTE);

            Files.setPosixFilePermissions(file.toPath(), perms);
            
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Busqueda_Secuencias.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
     }
    
}
