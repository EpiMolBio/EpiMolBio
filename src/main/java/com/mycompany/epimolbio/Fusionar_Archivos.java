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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Fusionar_Archivos {
    
    //Genera un archivo .fasta uniendo los .fasta que se encuentren en la carpeta de entrada.
    //Generates a .fasta file by merging the .fasta files found in the input folder.
    
    public static void cargarFusionarArchivos(String archivoCarga, String archivoGuardado){
    
        try{
            
            try (FileWriter ficheroSalida = new FileWriter(archivoGuardado)) {
                
                String directorio = archivoCarga;
                
                final File carpeta = new File(directorio);
                
                String ficheros[] = new String[5000];
                
                for(int i = 0; i < ficheros.length; i++) {
                    
                    ficheros[i] = "";
                    
                }
                
                int a = 0;
                
                for (File ficheroEntrada : carpeta.listFiles()) {
                    
                    ficheros[a] = (ficheroEntrada.getName());
                    a++;
                    
                }
                
                ficheros = Comprobador_Archivos_Vacios.cargarComprobarArchivosVacios(archivoCarga, ficheros);
                
                for (String fichero : ficheros) {
                    
                    if (fichero.equals("")) {
                        
                        break;
                        
                    }
                    
                    FileReader f = new FileReader(archivoCarga + fichero);
                    
                    try (BufferedReader b = new BufferedReader(f)) {
                        
                        String cadena;
                        
                        while((cadena = b.readLine()) != null) {
                            
                            ficheroSalida.write(cadena + "\r\n");
                            
                        }
                    }
                    
                }
            }
            
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Fusionar_Archivos.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
}
