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
import static com.mycompany.epimolbio.Interfaz.jComboBox31;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Marcadores {
    
    /*Entre varios archivos .fasta selecciona las mutaciones que son exclusivas de cada archivo.
    Utiliza la clase Calculos_Frecuencias_Posicion para realizar los calculos.*/
    
    /*Among several .fasta files, selects the mutations that are exclusive to each file.
    The Calculos_Frecuencias_Posicion class is used to perform the calculations.*/
    
    public static void cargarMarcadores(String entrada, String salida, String referencia){
        
        try{
            
            String cribado = "";
            
            boolean aaValido;
            
            String eliminarComa;
            
            if(jComboBox31.getSelectedIndex() == 1){
                
                cribado = " > 75%";
                
            }else if(jComboBox31.getSelectedIndex() == 2){
                
                cribado = " >= 90%";
                
            }
            
            String retornoCortado[];
            int contadorCantidadMarcadores;
            
            try (FileWriter ficheroSalida = new FileWriter(salida)) {
                
                ficheroSalida.write("<!DOCTYPE html>");
                ficheroSalida.write("<html lang = 'es'>");
                ficheroSalida.write("<head>");
                ficheroSalida.write("<meta charset = 'UTF-8'/>");
                ficheroSalida.write(Menu_Lateral.head());
                ficheroSalida.write(Estilo_Tablas_html.cssTabla());
                ficheroSalida.write("</head>\n");
                ficheroSalida.write("<body>\n");
                
                if(jComboBox31.getSelectedIndex() == 2){
                    
                    if(idioma == 1){
                        
                        ficheroSalida.write(Menu_Lateral.body("Variabilidad Polimorfismos Individual Marcadores" + cribado, false, false, false));
                        
                    }else{
                        
                        ficheroSalida.write(Menu_Lateral.body("Variability Polymorphisms Individual Markers" + cribado, false, false, false));
                        
                    }
                    
                }else{
                    
                    if(idioma == 1){
                        
                        ficheroSalida.write(Menu_Lateral.body("Variabilidad Polimorfismos Individual Marcadores" + cribado, true, false, false));
                        
                    }else{
                        
                        ficheroSalida.write(Menu_Lateral.body("Variability Polymorphisms Individual Markers" + cribado, true, false, false));
                        
                    }
                }
                
                ficheroSalida.write("<table>");
                
                ficheroSalida.write("<tr>");
                
                if(idioma == 1){
                    
                    ficheroSalida.write("<td colspan = '3'>Variabilidad Polimorfismos Individual Marcadores" + cribado + "</td>");
                    
                }else{
                    
                    ficheroSalida.write("<td colspan = '3'>Variability Polymorphisms Individual Markers" + cribado + "</td>");
                    
                }
                
                ficheroSalida.write("</tr>");
                
                ficheroSalida.write("<tr>");
                
                if(idioma == 1){
                    
                    ficheroSalida.write("<td colspan = '1'; rowspan = '1'>Archivo</td>");
                    ficheroSalida.write("<td colspan = '1'>Marcadores</td>");
                    ficheroSalida.write("<td colspan = '1'>Secuencias Totales</td>");
                    
                }else{
                    
                    ficheroSalida.write("<td colspan = '1'; rowspan = '1'>File</td>");
                    ficheroSalida.write("<td colspan = '1'>Markers</td>");
                    ficheroSalida.write("<td colspan = '1'>Total Sequences</td>");
                    
                }
                
                ficheroSalida.write("</tr>");
                
                ficheroSalida.write("<tr>");
                
                ficheroSalida.write("</tr>");
                
                String marcadores[];
                
                String ficheros[];
                
                File archivos = new File(entrada);
                
                ficheros = archivos.list();
                Arrays.sort(ficheros);
                
                marcadores = new String[ficheros.length];
                
                for(int i = 0; i < marcadores.length; i++){
                    
                    marcadores[i] = "";
                    
                }
                
                String matrizBruta[][] = new String[ficheros.length][referencia.length()];
                String matrizProcesada[][] = new String[ficheros.length][referencia.length()];
                
                String salidaProcesada;
                
                String archivo;
                
                String retornoPolimorf;
                
                for(int i = 0; i < ficheros.length; i++){
                    
                    archivo = entrada + "/" + ficheros[i];
                    
                    for(int x = 0; x < referencia.length(); x++){
                        
                        retornoPolimorf = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, (x+1), referencia.toCharArray()[x], "←", 3, 2, 0, "", 0, 0, 0, 0, 0, 'ł');
                        
                        if(jComboBox31.getSelectedIndex() == 2 && retornoPolimorf.contains("#F3A031")){
                            
                            retornoPolimorf = "";
                            
                        }
                        
                        salidaProcesada = retornoPolimorf.replace("<a Style = 'color:black';></a></a>", "");
                        salidaProcesada = salidaProcesada.replace("<a Style = 'color:#BF3D27';>", "");
                        salidaProcesada = salidaProcesada.replace("<a Style = 'color:#F3A031';>", "");
                        salidaProcesada = salidaProcesada.replace("<a Style = 'color:#834580';>", "");
                        
                        retornoPolimorf = referencia.toCharArray()[x] + "" + (x+1) + retornoPolimorf;
                        
                        matrizBruta[i][x] = retornoPolimorf;
                        matrizProcesada[i][x] = salidaProcesada;
                        
                    }
                }
                
                aaValido = true;
                
                for(int x = 0; x < referencia.length(); x++){
                    
                    for(int i = 0; i < ficheros.length; i++){
                        
                        for(int r = 0; r < ficheros.length; r++){
                            
                            if(matrizProcesada[i][x].equals("")){
                                
                                matrizBruta[i][x] = "";
                                
                            }
                            
                            if(matrizProcesada[i][x].equals(matrizProcesada[r][x]) && i != r){
                                
                                aaValido = false;
                                break;
                                
                            }
                            
                        }
                        
                        if(aaValido == true){
                            
                            marcadores[i] += matrizBruta[i][x];
                            
                            if(!matrizBruta[i][x].equals("")){
                                
                                marcadores[i] += ", ";
                                
                            }
                            
                        }else{
                            
                            aaValido = true;
                            
                        }
                        
                    }
                    
                }
                
                for(int i = 0; i<ficheros.length; i++){
                    
                    if(marcadores[i].equals("")){
                        
                        continue;
                        
                    }
                    
                    ficheroSalida.write("<tr>");
                    
                    ficheroSalida.write("<td colspan = '1'>"+ ficheros[i] + "</td>");
                    ficheroSalida.write("<td colspan = '1'>");
                    
                    eliminarComa = "";
                    
                    for(int coma = 0; coma < marcadores[i].length()-2; coma++){
                        
                        eliminarComa += marcadores[i].toCharArray()[coma];
                        
                    }
                    
                    marcadores[i] = eliminarComa;
                    
                    retornoCortado = marcadores[i].split(",");
                    
                    contadorCantidadMarcadores = 0;
                    
                    for(int marcadores_s = 0; marcadores_s < retornoCortado.length; marcadores_s++){
                        
                        ficheroSalida.write(retornoCortado[marcadores_s]);
                        
                        if(marcadores_s != retornoCortado.length -1){
                            
                            ficheroSalida.write(",");
                            
                        }
                        
                        if(contadorCantidadMarcadores != 0 && contadorCantidadMarcadores % 4 == 0){
                            
                            ficheroSalida.write("<br>");
                            
                        }
                        
                        contadorCantidadMarcadores ++;
                        
                    }
                    
                    contadorCantidadMarcadores++;
                    
                    ficheroSalida.write("<td colspan = '1'>");
                    
                    String retorno_polimorf_numerico = String.valueOf(Conteo_Interno.cargarConteoInterno(entrada + "/" + ficheros[i]));
                    
                    ficheroSalida.write(retorno_polimorf_numerico);
                    
                    ficheroSalida.write("</td>");
                    
                    ficheroSalida.write("</tr>");
                    
                }
                
                ficheroSalida.write("<table>");
                ficheroSalida.write("</html");
            }
            
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Marcadores.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
    }
}
