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

public class Frecuencia_Mutacion {
    
    //Hace el calculo de la frecuencia de mutación de uno o varios archivos .fasta y genera una salida en html.
    
    public static void cargarFrecuenciaMutacion(String archivoCarga, String archivoGuardado, String consenso, int alinear, int tipoSecuencia){
        
        try{
        
            int contadorMonomeros = 0;
            
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
            
            DecimalFormat df1 = new DecimalFormat("0.000", symbols);
            df1.setDecimalSeparatorAlwaysShown(true);
            
            DecimalFormat df2 = new DecimalFormat("0.00000", symbols);
            df2.setDecimalSeparatorAlwaysShown(true);
        
            try (FileWriter salida = new FileWriter(archivoGuardado)) {
                
                if(idioma == 1){
                    
                    salida.write("Archivo;Frecuencia Mut.;Frecuencia Mut. %;% Conservación;Mut. Media Sec.;Sec. Totales" + "\r\n");
                    
                }else{
                    
                    salida.write("File;Mut. Frequency;Mut. Frequency %;Conservation %;Average Mut. Sec.;Total Sec." + "\r\n");
                    
                }
                
                int monomerosTotales;
                
                String ficheros[];
                
                String fichero;
                
                final File carpeta = new File(archivoCarga);
                
                ficheros = carpeta.list();
                Arrays.sort(ficheros);
                
                String parAlineado[] = new String[2];
                
                int contadorFrecuencia;
                int contadorSecuencias;
                
                double frecuenciaMutacionTotal;
                
                String temp;
                String cadena;
                String secuencia;
                
                FileReader f;
                BufferedReader b;
                
                for (String fichero1 : ficheros) {
                    
                    if (fichero1.equals("")) {
                        
                        break;
                        
                    }
                    
                    monomerosTotales = 0;
                    salida.write(fichero1);
                    contadorFrecuencia = 0;
                    contadorSecuencias = 0;
                    
                    fichero = archivoCarga + fichero1;
                    
                    f = new FileReader(fichero);
                    b = new BufferedReader(f);
                    
                    temp = "";
                    
                    while((cadena = b.readLine()) != null) {
                        
                        if(cadena.toCharArray()[0] == '>') {
                            
                            if(temp.length() != 0) {
                                
                                secuencia = temp.toUpperCase();
                                
                                if(alinear == 1){
                                    
                                    if(tipoSecuencia == 1){
                                        
                                        parAlineado = Calculos_Alineamientos.alineamientoNucleotidos(consenso, secuencia, "", "");
                                        
                                    }else if(tipoSecuencia == 2){
                                        
                                        parAlineado = Calculos_Alineamientos.alineamientoAminoacidos(consenso, secuencia, "", "");
                                        
                                    }
                            
                                }else if(alinear == 2){
                                    
                                    parAlineado[0] = consenso;
                                    parAlineado[1] = secuencia;
                                    
                                }
                                
                                monomerosTotales += secuencia.length();
                                
                                if(tipoSecuencia == 1){
                                    
                                    for(int i = 0; i < parAlineado[0].length(); i++){
                                        
                                        if(parAlineado[0].toCharArray()[i] != parAlineado[1].toCharArray()[i] && parAlineado[0].toCharArray()[i] != '?' && parAlineado[0].toCharArray()[i] != 'N' && parAlineado[0].toCharArray()[i] != '-' && parAlineado[1].toCharArray()[i] != '?' && parAlineado[1].toCharArray()[i] != 'N' && parAlineado[1].toCharArray()[i] != '-'){
                                            
                                            contadorFrecuencia++;
                                            
                                        }
                                        
                                    }
                                    
                                }else if(tipoSecuencia == 2){
                                    
                                    for(int i = 0; i < parAlineado[0].length(); i++){
                                        
                                        if(parAlineado[0].toCharArray()[i] != parAlineado[1].toCharArray()[i] && parAlineado[0].toCharArray()[i] != '?' && parAlineado[0].toCharArray()[i] != '*' && parAlineado[0].toCharArray()[i] != '-' && parAlineado[1].toCharArray()[i] != '?' && parAlineado[1].toCharArray()[i] != '*' && parAlineado[1].toCharArray()[i] != '-'){
                                            
                                            contadorFrecuencia++;
                                            
                                        }
                                        
                                    }
                    
                                }
                                
                                contadorSecuencias++;
                                
                                if(tipoSecuencia == 1){
                                    
                                    for(int i = 0; i < parAlineado[1].length(); i++){
                                        
                                        if(parAlineado[1].toCharArray()[i] != 'N' && parAlineado[1].toCharArray()[i] != '?' && parAlineado[1].toCharArray()[i] != '-'){
                                            
                                            contadorMonomeros++;
                                            
                                        }
                                        
                                    }
                    
                                }else if(tipoSecuencia == 2){
                                    
                                    for(int i = 0; i < parAlineado[1].length(); i++){
                                        
                                        if(parAlineado[1].toCharArray()[i] != '*' && parAlineado[1].toCharArray()[i] != '?' && parAlineado[1].toCharArray()[i] != '-'){
                                            
                                            contadorMonomeros++;
                                            
                                        }
                                        
                                    }
                                    
                                }
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
                    
                    secuencia = temp.toUpperCase();
                    
                    if(alinear == 1){
                        
                        if(tipoSecuencia == 1){
                            
                            parAlineado = Calculos_Alineamientos.alineamientoNucleotidos(consenso, secuencia, "", "");
                            
                        }else if(tipoSecuencia == 2){
                            
                            parAlineado = Calculos_Alineamientos.alineamientoAminoacidos(consenso, secuencia, "", "");
                            
                        }
                        
                    }else if(alinear == 2){
                        
                        parAlineado[0] = consenso;
                        parAlineado[1] = secuencia;
                        
                    }
                    
                    if(tipoSecuencia == 1){
                        
                        for(int i = 0; i < parAlineado[0].length(); i++){
                            
                            if(parAlineado[0].toCharArray()[i] != parAlineado[1].toCharArray()[i] && parAlineado[0].toCharArray()[i] != '?' && parAlineado[0].toCharArray()[i] != 'N' && parAlineado[0].toCharArray()[i] != '-' && parAlineado[1].toCharArray()[i] != '?' && parAlineado[1].toCharArray()[i] != 'N' && parAlineado[1].toCharArray()[i] != '-'){
                                
                                contadorFrecuencia++;
                                
                            }
                            
                        }
                        
                    }else if(tipoSecuencia == 2){
                        
                        for(int i = 0; i < parAlineado[0].length(); i++){
                            
                            if(parAlineado[0].toCharArray()[i] != parAlineado[1].toCharArray()[i] && parAlineado[0].toCharArray()[i] != '?' && parAlineado[0].toCharArray()[i] != '*' && parAlineado[0].toCharArray()[i] != '-' && parAlineado[1].toCharArray()[i] != '?' && parAlineado[1].toCharArray()[i] != '*' && parAlineado[1].toCharArray()[i] != '-'){
                                
                                contadorFrecuencia++;
                                
                            }
                            
                        }
                        
                    }
                    
                    if(tipoSecuencia == 1){
                        
                        for(int i = 0; i < parAlineado[1].length(); i++){
                            
                            if(parAlineado[1].toCharArray()[i] != 'N' && parAlineado[1].toCharArray()[i] != '?' && parAlineado[1].toCharArray()[i] != '-'){
                                
                                contadorMonomeros++;
                                
                            }
                            
                        }
                        
                    }else if(tipoSecuencia == 2){
                        
                        for(int i = 0; i < parAlineado[1].length(); i++){
                            
                            if(parAlineado[1].toCharArray()[i] != '*' && parAlineado[1].toCharArray()[i] != '?' && parAlineado[1].toCharArray()[i] != '-'){
                                
                                contadorMonomeros++;
                                
                            }
                            
                        }
                    
                    }
                    
                    monomerosTotales = contadorMonomeros;
                    contadorMonomeros = 0;
                    contadorSecuencias++;
                    
                    frecuenciaMutacionTotal = (double)contadorFrecuencia/(double)contadorSecuencias;
                    
                    salida.write(";" + df2.format((double)contadorFrecuencia/(double)monomerosTotales));
                    salida.write(";" + df1.format(((double)contadorFrecuencia * 100.0)/(double)monomerosTotales) + "%");
                    salida.write(";" + df1.format(100.0 - ((double)contadorFrecuencia * 100.0)/(double)monomerosTotales) + "%");
                    salida.write(";" + df1.format(frecuenciaMutacionTotal));
                    salida.write(";"  + contadorSecuencias + "\r\n");
                    
                }
            }
        
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Frecuencia_Mutacion.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
}
