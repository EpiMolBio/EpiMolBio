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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Busqueda_Secuencias_Conservadas {
    
    //Busca regiones conservadas de una longitud a elegir en un pool de secuencias de mayor tamaño.
    //Search for conserved regions of a chosen length within a larger pool of sequences.
    
    public static String cargarBusquedaSecuenciasConservadas(String archivo, int paso, String cadena, double porcentaje,
             int valorInferior, int valorSuperior){
            
        try{
            
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
            
            String aptamero = "";
            double valor;
            
            String valorFormato;
            
            String retorno = "";
            
            String cadenaEvaluar = cadena;
                        
            for(int a = 0; a <= cadenaEvaluar.length()-paso; a++){
                
                for(int i = a; i < a+paso; i++){
                    
                    aptamero += cadenaEvaluar.toCharArray()[i];
                    
                }
                
                valor = Double.parseDouble(Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, 1, 'ł', "ł", 6, 0, 0, aptamero, 1, valorInferior, valorSuperior, 0, 0, 'ł'));
                 
                if(valor >= porcentaje){
                    
                    DecimalFormat df1 = new DecimalFormat("0.000", symbols);
                    df1.setDecimalSeparatorAlwaysShown(true);
 
                    valorFormato = df1.format(valor);
                    
                    String color = "";
                    
                    if(valor == 100.0){
                    
                        color = "#834580";
                        
                    }else if(valor >= 90.0){
                        
                        color = "#BF3D27";
                        
                    }else if(valor < 90.0 && valor > 75.0){
                        
                        color = "#F3A031";
                        
                    }else if(valor <= 75.0 && valor > 50.0){
                        
                        color = "#E0D900";
                        
                    }else if(valor <= 50.0 && valor >= 10.0){
                        
                        color = "#2E75B6";
                        
                    }else if(valor < 10.0){
                        
                        color = "#3E8249";
                        
                    }
                    
                    if(idioma == 1){
                    
                        retorno += "<p'font-size: 100%';>" + "Regi&oacute;n: " + (a + 1) + " - " + (paso + a) +"</br>" + aptamero + " : " + "<a style = 'color:" + color +"'>" + valorFormato + "%</a>" + "</p></br>";
                    
                    }else{
                        
                        retorno += "<p'font-size: 100%';>" + "Region: " + (a + 1) + " - " + (paso + a) +"</br>" + aptamero + " : " + "<a style = 'color:" + color +"'>" + valorFormato + "%</a>" + "</p></br>";
                        
                    }
                }
                
                aptamero = "";
                
            }
            
            return retorno;
            
        }catch(NumberFormatException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                 
            Logger.getLogger(Busqueda_Secuencias_Conservadas.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return "";
            
        }
    }                   		
}
