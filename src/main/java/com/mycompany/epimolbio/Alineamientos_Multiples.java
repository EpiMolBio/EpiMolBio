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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import java.net.URISyntaxException;


/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Alineamientos_Multiples {
    
    public static String entries0[];
    public static String entries1[];
    public static String entries2[];
    public static String entries3[];
    
    public static File directorioSalida;
    public static File directorioSalidaLinea;
    public static File directorioSalidaSecundario;
    public static File directorioSalidaTerciario;
    public static File currentFile;
    public static File ejecutableCreado;
    
    public static Process p;
    
    /*Crea una serie de carpetas temporales y pega MUSCLE en la ubicación de EpiMolBio para realizar alineamientos.
    Selecciona la versión de Muscle apropiada para el sistema operativo en el que se está trabajando.*/
    
    /* Create a series of temporary folders and paste MUSCLE in the EpiMolBio location to perform alignments.
    Select the appropriate version of MUSCLE for the operating system being used.*/
    
    public void cargadorAlineamientosMultiples(String entrada, String salida, String referencia, int cantidadSecuencias){
        
        try{
        
            String tagArchivo;
            
            if(idioma == 1){
                
                tagArchivo = "Alineado_";
                
            }else{
                
                tagArchivo = "Aligned_";
                
            }
            
            String ficheros[];
            String ficherosSecundarios[];
                
            File entrada_ = new File(entrada);
            
            ficheros = entrada_.list();
            
            String ficherosAlineados[];
        
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
            
            directorioSalidaLinea = new File(carpeta + "/carpeta_temporal_linea/");
            directorioSalida = new File(carpeta + "/carpeta_temporal_archivos");
            directorioSalidaSecundario = new File(carpeta + "/carpeta_temporal_archivos_alineado/");
            directorioSalidaTerciario = new File(carpeta + "/carpeta_temporal_archivos_alineado_procesado/");
                        
            eliminarArchivosTemporales();
                        
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
        
            InputStream muscle;
            
            File secundarios_;
            File alineados_;
            
            for (String fichero : ficheros) {
                                
                directorioSalidaLinea.mkdirs();
                directorioSalida.mkdirs();
                directorioSalidaSecundario.mkdirs();
                directorioSalidaTerciario.mkdirs();
                muscle = Alineamientos_Multiples.class.getResourceAsStream(archivoSo);
                ejecutableCreado = new File(carpeta + archivoTemporal);
                copiarPegarArchivo(muscle, ejecutableCreado);
                
                elimiarSaltos(entrada + "/" + fichero, carpeta);
                dividirArchivos(carpeta + "/carpeta_temporal_linea/linea.fasta", carpeta + "/carpeta_temporal_archivos/", cantidadSecuencias, referencia);
                secundarios_ = new File(carpeta + "/carpeta_temporal_archivos");
                ficherosSecundarios = secundarios_.list();
                
                for (String ficheros_secundario : ficherosSecundarios) {
                    
                    String[] comandoMuscle = {carpeta + archivoTemporal, "-in", carpeta + "/carpeta_temporal_archivos/" + ficheros_secundario, "-out", carpeta + "/carpeta_temporal_archivos_alineado/Alineado" + ficheros_secundario};
                    comando = comandoMuscle;
                    comando(comando);
                    
                }
                
                alineados_ = new File(carpeta + "/carpeta_temporal_archivos_alineado/");
                ficherosAlineados = alineados_.list();
                alineadorReferencia(carpeta + "/carpeta_temporal_archivos_alineado/", ficherosAlineados, carpeta + "/carpeta_temporal_archivos_alineado_procesado/", salida + "/" + tagArchivo + fichero);
                                
                eliminarArchivosTemporales();
                
            }
 
        
        }catch(IOException | URISyntaxException e){
      
            Logger.getLogger(Alineamientos_Multiples.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            eliminarArchivosTemporales();
            
            p.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
        
    }
    
    //Lanza el comando de MUSCLE.
    //Execute the MUSCLE command.
    
    public static void comando(String[] comando){
        
        BufferedReader br = null;
        
        try {
            
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            p = pb.start();
            br = new BufferedReader(new InputStreamReader(
            p.getInputStream()));
            String out;
            
            while ((out = br.readLine()) != null){
                
                System.out.println(out);
                
            }
         
            br.close();
 
        } catch (IOException e) {
        
            Logger.getLogger(Alineamientos_Multiples.class.getName()).log(Level.SEVERE, null, e);
            
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
            
            eliminarArchivosTemporales();
            
            p.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
    //Copia y pega el programa MUSCLE en la carpeta donde se encuentra EpiMolBio.
    //Copy and paste the MUSCLE program into the folder where EpiMolBio is located.
    
    public void copiarPegarArchivo(InputStream src, File dst) throws IOException{

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

            Logger.getLogger(Alineamientos_Multiples.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            eliminarArchivosTemporales();
            
            p.destroy();
            
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
          
            Logger.getLogger(Alineamientos_Multiples.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            eliminarArchivosTemporales();
            
            p.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
        
    public static void elimiarSaltos(String entrada, String carpeta) throws IOException{
               
        FileReader f = new FileReader(entrada);
        BufferedReader b = new BufferedReader(f);
        
        try{
            
            try (FileWriter salida_ = new FileWriter(carpeta + "/carpeta_temporal_linea/linea.fasta")) {
                
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
                        
                        salida_.write(cadena);
                        
                    }

                }
            }
            
            b.close();
        
        }catch(IOException e){
            
            Logger.getLogger(Alineamientos_Multiples.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            eliminarArchivosTemporales();
            
            b.close();
            
            p.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
    //Divide la entrada en archivos más pequeños para ser alineados según se seleccione.
    //Split the input into smaller files to be aligned as selected.
    
    public static void dividirArchivos(String archivo, String salida, int tamanoDivision, String referencia){
        
        try{
                        
            FileReader f = new FileReader(archivo);
            
            try (BufferedReader b = new BufferedReader(f)) {
                
                int contadorArchivos = 1;
                
                FileWriter salida_ = new FileWriter(salida + contadorArchivos + ".fasta");
                
                contadorArchivos++;
                
                if(!referencia.equals("")){
                    
                    salida_.write(">REFERENCIA" + "\r\n");
                    salida_.write(referencia.replace("X", "?").replace("*", "X").replace("?", "Z") + "\r\n");
                    
                }
                
                String cadena;
                
                int contadorSecuencias = 0;
                int contadorSecuenciasSecundario = 0;
                
                while((cadena = b.readLine()) != null) {
                    
                    if(contadorSecuencias == 2){
                        
                        contadorSecuenciasSecundario++;
                        contadorSecuencias = 0;
                    }
                    
                    if(contadorSecuenciasSecundario == tamanoDivision){
                        
                        salida_.close();
                        
                        salida_ = new FileWriter(salida + contadorArchivos + ".fasta");
                        
                        if(!referencia.equals("")){
                            
                            salida_.write(">REFERENCIA" + "\r\n");
                            salida_.write(referencia + "\r\n");
                            
                        }
                        
                        contadorArchivos++;
                        contadorSecuenciasSecundario = 0;
                        
                    }
                    
                    if(cadena.toCharArray()[0] == '>'){
                        
                        salida_.write(cadena + "\r\n");
                        
                    }else{
                    
                        salida_.write(cadena.toUpperCase().replace("X", "?").replace("*", "X").replace("?", "Z") + "\r\n");
                    
                    }
                    
                    contadorSecuencias++;
                }
                
                salida_.close();
                
            }
                        
        }catch(IOException e){
        
            Logger.getLogger(Alineamientos_Multiples.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            eliminarArchivosTemporales();
            
            p.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
    //Elimina las inserciones respecto a la secuencia de referencia.
    //Remove insertions relative to the reference sequence.
    
    public static void alineadorReferencia(final String archivo, String ficheros[], String salida, String salidaTotal) throws IOException{
        
        FileReader f;
        BufferedReader b = null;
        
        try{
        
            String cadena;
            String archivoTemporal;
            
            String referencia = "";
            
            boolean entradaReferencia = false;
            
            for (String fichero : ficheros) {
                
                archivoTemporal = archivo + fichero;
                f = new FileReader(archivoTemporal);
                b = new BufferedReader(f);
                
                while((cadena = b.readLine()) != null) {
			
                    if(cadena.equals(">REFERENCIA")){
                        
                        entradaReferencia = true;
                        continue;
                        
                    }
                    
                    if(entradaReferencia == true && cadena.toCharArray()[0] != '>'){
                        
                        referencia += cadena;
                        
                    }else{
                        
                        entradaReferencia = false;
                        
                    }
                    
                }
                
                Eliminar_Inserciones.cargarEliminarInserciones(archivo, salida, fichero, referencia);
                referencia = "";
            }
                    
            File salida_ = new File(salida);
            
            ficheros = salida_.list();
            
            try (FileWriter salidaGeneral = new FileWriter(salidaTotal)) {
                
                for (String fichero : ficheros) {
                    
                    archivoTemporal = salida + fichero;
                    f = new FileReader(archivoTemporal);
                    b = new BufferedReader(f);
                    
                    while((cadena = b.readLine()) != null) {
                        
                        if(cadena.equals(">REFERENCIA")){
                            
                            b.readLine();
                            
                            continue;
                        }
                        
                        if(cadena.toCharArray()[0] == '>'){
                            
                            salidaGeneral.write(cadena + "\r\n");
                            
                        }else{
                        
                            salidaGeneral.write(cadena.replace("X", "*").replace("Z", "?") + "\r\n");
                        
                        }
                        
                    }
                }
                
            }
            
        }catch(IOException e){
            
            Logger.getLogger(Alineamientos_Multiples.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            eliminarArchivosTemporales();
            
            if (b != null) {
            
                b.close();
                
            }
            
            p.destroy();
            
            Terminar_Hilos.cargarTerminarHilos();
        }
        
        if (b != null) {
        
            b.close();
        
        }
        
    }
    
    //Elimina las carpetas y archivos temporales.
    //Delete the temporary folders and files.
    
    public static void eliminarArchivosTemporales(){
        
        try{
        
            while(true){
                    
                System.gc();
                
                entries0 = directorioSalidaLinea.list();
                entries1 = directorioSalida.list(); 
                entries2 = directorioSalidaSecundario.list();
                entries3 = directorioSalidaTerciario.list();
                    
                if(entries0 == null && entries1 == null && entries2 == null && entries3 == null){
                        
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
        
                        entries1 = directorioSalida.list();
            
                        for(String s: entries1){
            
                            currentFile = new File(directorioSalida.getPath(),s);
                            currentFile.delete();
                    
                        }
            
                        directorioSalida.delete();
            
                    }catch(Exception e){}
        
                    try{
        
                        entries2 = directorioSalidaSecundario.list();
            
                        for(String s: entries2){
            
                            currentFile = new File(directorioSalidaSecundario.getPath(),s);
                            currentFile.delete();
                    
                        }
            
                        directorioSalidaSecundario.delete();
            
                    }catch(Exception e){}
        
                    try{
        
                        entries3 = directorioSalidaTerciario.list();
           
                        for(String s: entries3){
            
                            currentFile = new File(directorioSalidaTerciario.getPath(),s);
                            currentFile.delete();
                    
                        }
            
                        directorioSalidaTerciario.delete();
            
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
