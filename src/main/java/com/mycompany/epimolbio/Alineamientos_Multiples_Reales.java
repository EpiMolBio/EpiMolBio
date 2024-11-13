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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.mycompany.epimolbio.Interfaz.btn_presionado;
import static com.mycompany.epimolbio.Interfaz.idioma;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Alineamientos_Multiples_Reales {
    
    public static String entries0[];
    public static String entries1[];
    
    public static File directorioSalidaAlineado;
    public static File directorioSalidaLinea;
    public static File currentFile;
    public static File ejecutableCreado;
    
    public static Process p_reales;
    
    /*Crea una serie de carpetas temporales y pega MUSCLE en la ubicación de EpiMolBio para realizar alineamientos.
    Selecciona la versión de MUSCLE apropiada para el sistema operativo en el que se está trabajando.
    Esta versión solo realiza alineamientos multiples conservando las inserciones.*/
    
    /*Create a series of temporary folders and paste MUSCLE in the EpiMolBio location to perform alignments.
    Select the appropriate version of MUSCLE for the operating system being used.
    This version only performs multiple alignments while preserving insertions.*/

    
    public void cargadorAlineamientosMultiplesReales(String entrada, String salida) throws IOException{
        
        try{
                    
            String tagArchivo;
            
            if(idioma == 1){
                
                tagArchivo = "Alineado_";
                
            }else{
                
                tagArchivo = "Aligned_";
                
            }
            
            File directorioSalida = new File(salida);
            directorioSalida.mkdirs();
            
            String ficheros[];
                
            File entrada_ = new File(entrada);
            
            ficheros = entrada_.list();
                    
            String sistemaOperativo = System.getProperty("os.name");
        
            String carpetaArchivo = new File(Alineamientos_Multiples.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            String carpeta = "";
            
            int contadorSeparador = 0;
            int contadorConformador = 0;
          
            for(int i = 0; i < carpetaArchivo.length(); i++){
                            
                if(carpetaArchivo.toCharArray()[i] == '/' || carpetaArchivo.toCharArray()[i] == '\\'){
                
                    contadorSeparador++;
                
                }
                
            }
            
            for(int i = 0; i < carpetaArchivo.length(); i++){
                
                if(carpetaArchivo.toCharArray()[i] == '/' || carpetaArchivo.toCharArray()[i] == '\\'){
  
                    contadorConformador++;
                    
                    if(contadorSeparador == contadorConformador){
                        
                        break;
                        
                    }
                    
                }
                
                carpeta += carpetaArchivo.toCharArray()[i];
                
            }
        
            carpeta += "/";
            
            String comando[];
	
            ejecutableCreado = null;
        
            String archivoSo = "";
            String archivoTemporal = "";                 
            
            if(sistemaOperativo.toUpperCase().startsWith("LINUX")){
            
                archivoSo = "/com/mycompany/epimolbio/alineamientos_multiples/muscle3.8.31_i86linux64.elf";
                archivoTemporal = "/muscle.elf";
                
            }else if(sistemaOperativo.toUpperCase().startsWith("WINDOWS")){
            
                archivoSo = "/com/mycompany/epimolbio/alineamientos_multiples/muscle3.8.31_i86win32.exe";
                archivoTemporal = "/muscle.exe";
                
            }else if(sistemaOperativo.toUpperCase().startsWith("MAC")){
            
                archivoSo = "/com/mycompany/epimolbio/alineamientos_multiples/muscle3.8.31_i86darwin64.app";
                archivoTemporal = "/muscle.app";
                
            }
            
            InputStream muscle = Alineamientos_Multiples.class.getResourceAsStream(archivoSo);
            
            ejecutableCreado = new File(carpeta + archivoTemporal);
            directorioSalidaLinea = new File(carpeta + "/carpeta_temporal_linea/");
            directorioSalidaAlineado = new File(carpeta + "/carpeta_temporal_archivos_alineado/");
                        
            eliminarArchivosTemporales();
            
            copiarPegarArchivo(muscle, ejecutableCreado);
            directorioSalidaLinea.mkdirs();
            directorioSalidaAlineado.mkdirs();
            
            for (String fichero : ficheros) {
                
                elimiarSaltos(entrada + "/" + fichero, carpeta);
                
                String[] comandoMuscle = {carpeta + archivoTemporal, "-in", carpeta + "/carpeta_temporal_linea" + "/" + fichero, "-out", carpeta + "/carpeta_temporal_archivos_alineado/" + tagArchivo + fichero};
                comando = comandoMuscle;
                comando(comando);
                                                
            }
            
            File ficherosAlineamientos = new File(carpeta + "/carpeta_temporal_archivos_alineado/");
            String ficherosAlineados[] = ficherosAlineamientos.list();
            
            for(String ficheroAlineado : ficherosAlineados){
                
                sustituir(carpeta + "/carpeta_temporal_archivos_alineado/" + ficheroAlineado, salida);
                
            }
                        
            eliminarArchivosTemporales();
        
        }catch(URISyntaxException e){
      
            Logger.getLogger(Alineamientos_Multiples_Reales.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
           
            ejecutableCreado.delete();
            
            p_reales.destroy();
            
            eliminarArchivosTemporales();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
        
    }
    
    //Lanza el comando de MUSCLE.
    //Run the MUSCLE command.
    
    public static void comando(String[] comando) throws IOException{
        
        BufferedReader br = null;
        
        try {
            
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            p_reales = pb.start();
            br = new BufferedReader(new InputStreamReader(
            p_reales.getInputStream()));
            String out;
            
            while ((out = br.readLine()) != null){
                
                System.out.println(out);
                
            }
        
            br.close();
           
        } catch (IOException e) {
        
            Logger.getLogger(Alineamientos_Multiples_Reales.class.getName()).log(Level.SEVERE, null, e);
            
            try {
                
                if (br != null) {
                       
                    br.close();
                
                }
                
            } catch (IOException ex) {
        
                Logger.getLogger(Alineamientos_Multiples.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
           
            ejecutableCreado.delete();
                        
            p_reales.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
    public static void darPermisos(File file){
    
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
          
            Logger.getLogger(Alineamientos_Multiples_Reales.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
        
            ejecutableCreado.delete();
            
            p_reales.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
    //Copia y pega el programa MUSCLE en la carpeta donde se encuentra EpiMolBio.
    //Copy and paste the MUSCLE program into the folder where EpiMolBio is located.
    
    public static void copiarPegarArchivo(InputStream src, File dst){

	try {
                       
            try (OutputStream out = new FileOutputStream(dst)) {
                
                byte[] b = new byte[1024];
                
                int l;
                
                while((l = src.read(b)) > 0){
                    
                    out.write(b, 0, l);
                    
                }
            }
            
            String sistemaOperativo = System.getProperty("os.name");
            
            if(!sistemaOperativo.toUpperCase().startsWith("WINDOWS")){
            
                darPermisos(dst);
            
            }
                    
	} catch (IOException e) {

            Logger.getLogger(Alineamientos_Multiples_Reales.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
          
            ejecutableCreado.delete();
            
            p_reales.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
            
	}

    }
    
    //Elimina saltos de linea en la entrada y sustituye caracteres invalidos para MUSCLE por otros validos.
    //Remove line breaks from the input and replace invalid characters for MUSCLE with valid ones.
    
    public static void elimiarSaltos(String entrada, String carpeta) throws IOException{
               
        FileReader f = new FileReader(entrada);
        BufferedReader b = new BufferedReader(f);
        
        String entradaCortada[] = entrada.split("/");
         
        try{
            
            try (FileWriter salida_ = new FileWriter(carpeta + "/carpeta_temporal_linea/" + entradaCortada[entradaCortada.length-1])) {
                
                String cadena;
                
                boolean primerEncabezado = false;
                
                while((cadena = b.readLine()) != null) {
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        if(primerEncabezado) {
                            
                            salida_.write("\n");
                            
                        }
                        
                        salida_.write(cadena + "\r\n");
                        primerEncabezado = true;
                        
                    }else {
                        
                        salida_.write(cadena.toUpperCase().replace("X", "?").replace("*", "X").replace("?", "Z"));
                        
                    }

                }
            }
            
            b.close();
        
        }catch(IOException e){
            
            Logger.getLogger(Alineamientos_Multiples_Reales.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            eliminarArchivosTemporales();
            
            b.close();
            
            p_reales.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
     
    //Sustituye carecteres cambiados anteriormente para ser leidos pos MUSCLE por los originales.
    //Replace characters previously changed for MUSCLE to be read with the original ones.
    
    public static void sustituir(String carpeta, String salida){
        
        try{
                        
            FileReader f = new FileReader(carpeta);
            
            try (BufferedReader b = new BufferedReader(f)) {
                
                String entradaCortada[] = carpeta.split("/");
                
                System.out.println(salida + "/" + entradaCortada[entradaCortada.length-1]);
                
                try (FileWriter salida_ = new FileWriter(salida + "/" + entradaCortada[entradaCortada.length-1])) {
                    
                    String cadena;
                    
                    while((cadena = b.readLine()) != null) {
                        
                        if(cadena.toCharArray()[0] == '>') {
                            
                            salida_.write(cadena + "\r\n");
                            
                        }else{
                            
                            salida_.write(cadena.replace("X", "*").replace("Z", "?") + "\r\n");
                            
                        }
                        
                    }
                }
            }
            
        }catch(IOException e){
            
            Logger.getLogger(Alineamientos_Multiples_Reales.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            eliminarArchivosTemporales();
                        
            p_reales.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
    //Elimina las carpetas y archivos temporales.
    //Delete the temporary folders and files.
    
    public static void eliminarArchivosTemporales(){
        
        try{
        
            while(true){
                    
                System.gc();
                
                entries0 = directorioSalidaLinea.list();
                entries1 = directorioSalidaAlineado.list(); 
                    
                if(entries0 == null && entries1 == null){
                        
                    break;
                        
                }else{
                        
                    try{
        
                        entries0 = directorioSalidaLinea.list();
            
                        for(String s: entries0){
            
                            currentFile = new File(directorioSalidaLinea.getPath(),s);
                            currentFile.delete();
                    
                        }
            
                        directorioSalidaLinea.delete();
            
                    }catch(Exception e){}
        
                    try{
        
                        entries1 = directorioSalidaAlineado.list();
            
                        for(String s: entries1){
            
                            currentFile = new File(directorioSalidaAlineado.getPath(),s);
                            currentFile.delete();
                    
                        }
            
                        directorioSalidaAlineado.delete();
            
                    }catch(Exception e){}
        
                    try{
        
                        ejecutableCreado.delete();
            
                    }catch(Exception e){}
             
                }
                    
            }
        
        }catch(Exception e){
        
            Logger.getLogger(Alineamientos_Multiples.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                        
            Terminar_Hilos.cargarTerminarHilos();
        
        }
        
    }
            
}
