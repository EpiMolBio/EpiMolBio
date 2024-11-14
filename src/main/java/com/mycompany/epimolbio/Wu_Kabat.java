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
import java.io.File;
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

public class Wu_Kabat {
    
    //Calcula el coeficiente de variabilidad de Wu-Kabat, para la funcionalidad Coeficiente de Variabilidad de Wu-Kabat.
    //Calculates the Wu-Kabat variability coefficient for the Wu-Kabat Variability Coefficient functionality.
    
    public static void cargarWuKabat(String entrada, String salida, int longitud){
        
        try{
        
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
            
            DecimalFormat df1 = new DecimalFormat("0.000", symbols);
            df1.setDecimalSeparatorAlwaysShown(true);
            
            String ficheros[];
            File carpetaEntrada = new File(entrada);
            ficheros = carpetaEntrada.list();
            Arrays.sort(ficheros);
            
            String separarRetorno[];
            
            String separarIndividual[];
            String datoNumerico[];
        
            String numerico;
            
            int k;
            int N;
            double n;
            
            double maximo;
            double maximoTemporal;
            
            try (FileWriter salida_ = new FileWriter(salida)) {
                
                if(idioma == 1){
                    
                    salida_.write("Archivo;Posición;Wu-Kabat;Número de Secuencias;Número de Aminoácidos;Frecuencia\r\n");
                    
                }else{
                    
                    salida_.write("File;Position;Wu-Kabat;Number of Sequences;Number of Amino Acids;Frequency\r\n");
                    
                }
                
                for (String fichero : ficheros) {
                    
                    for (int i = 0; i < longitud; i++) {
                        
                        numerico = "";
                        separarRetorno = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(entrada + "/" + fichero, i+1, 'ł', "↓", 20, 1, 0, "", 0, 0, 0, 2, 0, 'ł').split("@");
                        
                        for (String separarRetorno1 : separarRetorno) {
                            
                            separarIndividual = separarRetorno1.split(",");
                            numerico += separarIndividual[1] + "|";
                            
                        }
                        
                        datoNumerico = numerico.split("\\|");
                        maximo = 0.0;
                        
                        if(datoNumerico.length > 1){
                            
                            for (String datoNumerico1 : datoNumerico) {
                                
                                maximoTemporal = Double.parseDouble(datoNumerico1);
                                
                                if(maximo < maximoTemporal){
                                    
                                    maximo = maximoTemporal;
                                    
                                }
                            }
                            
                        }else{
                            
                            maximo = Double.parseDouble(datoNumerico[0]);
                            
                        }
                        
                        k = separarRetorno.length;
                        N = Integer.parseInt(Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(entrada + "/" + fichero, i+1, ' ', "", 50, 1, 0, "", 0, 0, 0, 0, 0, 'ł'));
                        maximo = (double)((maximo*N)/100.0);
                        n = maximo;
                        
                        salida_.write(fichero + ";" + (i+1) + ";" + df1.format(wu_kabat_expresion(N, k, n)) + ";" + N + ";" + k + ";" + df1.format(n) + "\r\n");
                    
                    }
                }
            }
            
        }catch(IOException | NumberFormatException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Wu_Kabat.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();

        }
    }
    
    private static double wu_kabat_expresion(int N, int k, double n){
        
        double w = 0.0;
        
        try{
        
            w = (double)(N*k)/n;
            
        }catch(Exception e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Wu_Kabat.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
        return w;
        
    }
    
}
