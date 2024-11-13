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

public class Contar_Secuencias_Mutadas {
    
    //Calcula la cantidad de secuencias mutadas que hay en uno o varios archivos .fasta
    //Calculates the number of mutated sequences in one or more .fasta files.
    
    public static void cargarContarSecuenciasMutadas(String entrada, String salida, String referencia, int ntAa){
     
        try{
            
            referencia = referencia.toUpperCase();
            
            try (FileWriter salida_ = new FileWriter(salida)) {
                
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                symbols.setDecimalSeparator('.');
                
                DecimalFormat df = new DecimalFormat("0.000", symbols);
                df.setDecimalSeparatorAlwaysShown(true);
                
                boolean salidaBuclePrincipal;
                
                String secuencia;
                String archivo;
                String temp;
                String cadena;
                
                int contadorSecuenciasTotales;
                int contadorTotal;
                
                String ficheros[];
                File carpetaEntrada = new File(entrada);
                ficheros = carpetaEntrada.list();
                Arrays.sort(ficheros);
                
                FileReader f;
                BufferedReader b;
                
                if(idioma == 1){
                    
                    salida_.write("Archivo;Mutadas;Número de Secuencias;Porcentaje\r\n");
                    
                }else{
                    
                    salida_.write("File;Mutated;Number of Sequences;Percentage\r\n");
                    
                }
                
                for (String fichero : ficheros) {
                    
                    salida_.write(fichero + ";");
                    archivo = entrada + "/" + fichero;
                    
                    f = new FileReader(archivo);
                    b = new BufferedReader(f);
                    
                    temp = "";
                    contadorSecuenciasTotales = 0;
                    contadorTotal = 0;
                    salidaBuclePrincipal = true;
                    
                    while(salidaBuclePrincipal) {
                        
                        if((cadena = b.readLine()) == null){
                            
                            cadena = ">";
                            salidaBuclePrincipal = false;
                            
                        }
                        
                        if(cadena.toCharArray()[0] == '>') {
                            
                            if(temp.length() != 0) {
                                
                                secuencia = temp.toUpperCase();
                                
                                contadorTotal++;
                                
                                for(int i = 0; i < referencia.length(); i++){
                                    
                                    if(referencia.toCharArray()[i] != secuencia.toCharArray()[i] && secuencia.toCharArray()[i] != '?' && ntAa == 1){
                                        
                                        contadorSecuenciasTotales++;
                                        break;
                                        
                                    }else if(referencia.toCharArray()[i] != secuencia.toCharArray()[i] && secuencia.toCharArray()[i] != '?' && secuencia.toCharArray()[i] != 'N' && ntAa == 2){
                                        
                                        contadorSecuenciasTotales++;
                                        break;
                                        
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
                    
                    b.close();
                    salida_.write(contadorSecuenciasTotales + ";" + contadorTotal + ";" + df.format((double)((contadorSecuenciasTotales * 100.0)/contadorTotal)) + "%\r\n");
                    
                }
            }
            
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Contar_Secuencias_Mutadas.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
    }
}
