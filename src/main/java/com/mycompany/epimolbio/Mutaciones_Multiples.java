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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Mutaciones_Multiples {
    
    public static String retornador2 = "";
    
    //Calcula la frecuencia de aparición entre varias mutaciones de uno o varios archivos .fasta, donde el resultado es guardado en un archivo.csv
    //Calculates the frequency of occurrence among various mutations from one or more .fasta files, with the result saved in a .csv file.
    
    public static void cargarMutacionesMultiples(String archivoCarga, String archivoGuardado, String mutaciones, boolean modo){
        
        try{
            
            try (FileWriter salidaArchivo = new FileWriter(archivoGuardado)) {
                
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                symbols.setDecimalSeparator('.');
                
                DecimalFormat df = new DecimalFormat("0.000", symbols);
                df.setDecimalSeparatorAlwaysShown(true);
                
                String mutacionesSeparadas[] = mutaciones.split(",");
                
                double porcentajeSalida;
                
                final File carpeta = new File(archivoCarga);
                
                String ficheros[] = new String[5000];
                String ficheroSecundario[] = new String[5000];
                
                for(int i = 0; i < ficheros.length; i++) {
                    
                    ficheros[i] = "";
                    ficheroSecundario[i] = "";
                    
                }
                
                int a = 0;
                
                for (File ficheroEntrada : carpeta.listFiles()) {
                    
                    ficheros[a] = (ficheroEntrada.getName());
                    a++;
                    
                }
                
                Arrays.sort(ficheros);
                
                int contadorSecundario = 0;
                
                for (String fichero : ficheros) {
                    
                    if (!fichero.equals("")) {
                        
                        ficheroSecundario[contadorSecundario] = fichero;
                        contadorSecundario++;
                        
                    }
                    
                }
                
                ficheros = ficheroSecundario;
                
                String secuencia;
                
                String valorMutacionCadena[] = new String[mutacionesSeparadas.length];
                int valorMutacion[] = new int[mutacionesSeparadas.length];
                
                char mut[] = new char[mutacionesSeparadas.length];
                
                int contadorDemostrador;
                int contadorTotales;
                int contadorValidos;
                
                String temp;
                String cadena;
                
                if(modo == false){
                    
                    if(idioma == 1){
                        
                        salidaArchivo.write("Archivo;" + "Número Secuencias;" + mutaciones.replace(',', '/') + ";" + "Frecuencia;" + "\r\n");
                        
                    }else{
                        
                        salidaArchivo.write("File;" + "Number of Sequences;" + mutaciones.replace(',', '/') + ";" + "Frequency;" + "\r\n");
                        
                    }
                    
                }
                
                for (String fichero : ficheros) {
                    
                    if (fichero.equals("")) {
                        break;
                    }
                    
                    for(int i = 0; i < valorMutacionCadena.length; i++){
                        
                        valorMutacionCadena[i] = "";
                        
                    }
                    
                    for(int i = 0; i < mut.length; i++){
                        
                        mut[i] = mutacionesSeparadas[i].toCharArray()[mutacionesSeparadas[i].toCharArray().length-1];
                        
                    }
                    
                    for(int i = 0; i < valorMutacionCadena.length; i++){
                        
                        for(int n = 1; n < mutacionesSeparadas[i].toCharArray().length - 1; n++){
                            
                            valorMutacionCadena[i] += String.valueOf(mutacionesSeparadas[i].toCharArray()[n]);
                            
                        }
                        
                        valorMutacion[i] = Integer.parseInt(valorMutacionCadena[i]);
                        
                    }
                    
                    contadorDemostrador = 0;
                    contadorTotales = 0;
                    contadorValidos = 0;
                    
                    FileReader f = new FileReader(archivoCarga + fichero);
                    
                    try (BufferedReader b = new BufferedReader(f)) {
                        
                        temp = "";
                        
                        while((cadena = b.readLine()) != null) {
                            
                            if(cadena.toCharArray()[0] == '>') {
                                
                                if(temp.length() != 0) {
                                    
                                    secuencia = temp.toUpperCase();
                                    
                                    contadorValidos++;
                                    
                                    for(int n = 0; n < valorMutacion.length; n++){
                                        
                                        if(secuencia.toCharArray()[valorMutacion[n]-1] == '?' || secuencia.toCharArray()[valorMutacion[n]-1] == '-'){
                                            
                                            contadorValidos--;
                                            contadorDemostrador = 0;
                                            break;
                                            
                                        }
                                        
                                        if(secuencia.toCharArray()[valorMutacion[n]-1] == mut[n]){
                                            
                                            contadorDemostrador++;
                                            
                                        }
                                        
                                    }
                                    
                                    if(contadorDemostrador == valorMutacion.length){
                                        
                                        contadorTotales++;
                                        
                                    }
                                    
                                    contadorDemostrador = 0;
                                    
                                }
                                
                                if(temp.length() != 0) {
                                    
                                    temp = "";
                                    
                                }else{
                                    
                                    temp = "";
                                    
                                }
                                
                            }else{
                                
                                temp += cadena;
                                
                            }
                            
                        }
                        
                        secuencia = temp.toUpperCase();
                        contadorValidos++;
                        
                        for(int n = 0; n < valorMutacion.length; n++){
                            
                            if(secuencia.toCharArray()[valorMutacion[n]-1] == '?' || secuencia.toCharArray()[valorMutacion[n]-1] == '-'){
                                
                                contadorValidos--;
                                contadorDemostrador = 0;
                                break;
                                
                            }
                            
                            if(secuencia.toCharArray()[valorMutacion[n]-1] == mut[n]){
                                
                                contadorDemostrador++;
                                
                            }
                            
                        }
                        
                        if(contadorDemostrador == valorMutacion.length){
                            
                            contadorTotales++;
                            
                        }
                        
                    }
                    
                    porcentajeSalida = (double)(contadorTotales * 100.0)/contadorValidos;
                    
                    if (modo == false) {
                        
                        salidaArchivo.write(fichero + ";" + contadorValidos + ";" + contadorTotales + ";" + String.valueOf(df.format(porcentajeSalida)) + "\r\n");
                    
                    } else if (modo == true) {
                        
                        retornador2 += fichero + ";" + contadorValidos + ";" + contadorTotales + ";" + String.valueOf(df.format(porcentajeSalida)) + "\r\n";
                        
                    }
                    
                }
            }
            
        }catch(IOException | NumberFormatException e){
            
            Logger.getLogger(Mutaciones_Multiples.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);

            Terminar_Hilos.cargarTerminarHilos();
            
        }
          
    }
    
    //Sirve para poder leer archivos .fasta en subcarpetas de la funcionalidad de Mutaciones Múltiples.
    //Is used to read .fasta files in subfolders of the Multiple Mutations functionality.
    
    public static void lectorSubcarpetasMutacionesMultiples(String archivoCarga, String archivoGuardado, String mutaciones) throws Exception{
        
        try{
            
            retornador2 = "";
            
            String FicheroSalidaGeneral;
            
            FileWriter ficheroSalidaGeneral1;
            
            String directorio = archivoCarga;
            
            final File carpeta = new File(directorio);
            
            String ficheros[] = new String[5000];
            String ficheroSecundario[] = new String[5000];
            
            for(int i = 0; i < ficheros.length; i++) {
		
                ficheros[i] = ""; 
                ficheroSecundario[i] = "";
                    
            }
            
            int a = 0;
	
            for (File ficheroEntrada : carpeta.listFiles()) {
	        
                ficheros[a] = (ficheroEntrada.getName());
                a++;
			
            }
            
            Arrays.sort(ficheros);
                
            int contadorSecundario = 0;
            
            for (String fichero : ficheros) {
                
                if (!fichero.equals("")) {
                    
                    ficheroSecundario[contadorSecundario] = fichero;
                    contadorSecundario++;
                    
                }
            }
            
            ficheros = ficheroSecundario;
            
            FicheroSalidaGeneral = archivoGuardado;
            
            ficheroSalidaGeneral1 =  new FileWriter(FicheroSalidaGeneral);
              
            if(idioma == 1){
            
                ficheroSalidaGeneral1.write("Archivo;" + "Número Secuencias;" + mutaciones.replace(',', '/') + ";" + "Frecuencia;" + "\r\n");
                
            }else{
                
                ficheroSalidaGeneral1.write("File;" + "Sequence Number;" + mutaciones.replace(',', '/') + ";" + "Frequency;" + "\r\n");
                
            }
            
            for (String fichero : ficheros) {
                
                if (fichero.equals("")) {
                    
                    break;
                    
                }
                
                cargarMutacionesMultiples(archivoCarga + fichero + "/", archivoGuardado, mutaciones, true);
                
            }
            
            ficheroSalidaGeneral1.write(retornador2);
            
            ficheroSalidaGeneral1.close();
              
            retornador2 = "";
            
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Mutaciones_Multiples.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
    }
}
