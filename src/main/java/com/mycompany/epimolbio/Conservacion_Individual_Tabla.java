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

public class Conservacion_Individual_Tabla {
    
    //Genera la salida en formato tabla de conservación individual, valiendose de la clase Calculos_Frecuencias_Posicion.
    
    public static void cargarConservacionIndividualTabla(String entrada, String salida, String referencia){
        
        try{
            
            int longitudReferencia = referencia.length();                        
            
            boolean botonTabla = true;
                                                
            final File carpeta = new File(entrada);
			
            String ficheros[];
		
            ficheros = carpeta.list();
            Arrays.sort(ficheros);
                   																				                	                                                                                                
            String archivo;
				
            String arrPosicion[] = new String[longitudReferencia];
            String arrAa[] = new String[longitudReferencia];
            String arrConservacion[] = new String[longitudReferencia];
            String arrTotal[] = new String[longitudReferencia];
            String colorFondoHorizontal[] = new String[longitudReferencia];
			
            String total = " ";
            String retorno;

            boolean conser = false;
            boolean totales = false;
            
            try (FileWriter archivoSalida = new FileWriter(salida)) {
                
                archivoSalida.write("<!DOCTYPE html>");
                archivoSalida.write("<html lang = 'es'>");
                archivoSalida.write("<head>");
                archivoSalida.write("<meta charset = 'UTF-8'/>");
                archivoSalida.write(Menu_Lateral.head());
                archivoSalida.write(Estilo_Tablas_html.cssTabla());
                archivoSalida.write("</head>\n");
                archivoSalida.write("<body>\n");
                
                archivoSalida.write("<table style = 'border-collapse: collapse;'>");
                
                for (String fichero : ficheros) {
                    
                    if (fichero.equals("")) {
                        
                        break;
                        
                    }
                    
                    archivo = entrada + "/" + fichero;
                    
                    for(int x = 0; x < longitudReferencia; x++) {
                        
                        retorno = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, (x+1), 'ł', "↓", 1, 2, 0, "", 0, 0, 0, 0, 0, 'ł');
                        
                        for(int n = 0; n < retorno.length(); n++) {
                            
                            if(retorno.toCharArray()[n] == ')') {
                                
                                conser = true;
                                
                            }

                        }
                        
                        if(conser == true) {
                            
                            for(int b = 1; b < retorno.length(); b++) {
                                
                                if(retorno.toCharArray()[b] == '(') {
                                    
                                    continue;
                                    
                                }
                                
                                if(retorno.toCharArray()[b] == '%') {
                                    
                                    break;
										
                                }
                            }
								
                            for(int c = 3; c < retorno.length(); c++) {
                                
                                if(retorno.toCharArray()[c-2] == ')' || totales == true){
                                    
                                    if(retorno.toCharArray()[c] == '<') {
                                        
                                        break;
                                        
                                    }
                                    
                                    total += retorno.toCharArray()[c];
                                    totales = true;
                                    
                                }
                                
                            }
                            
                            conser = false;
                            totales = false;
                            
                        }else {
                            
                            total = retorno;
                            
                        }
                        
                        arrPosicion[x] = String.valueOf(x+1);
                        
                        colorFondoHorizontal[x] = "";
                        
                        arrAa[x] = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, (x+1), 'ł', "↓", 10, 1, 0, "", 0, 0, 0, 4, 0, 'ł');
                        
                        arrConservacion[x] = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, (x+1), 'ł', "↓", 10, 1, 0, "", 0, 0, 0, 5, 0, 'ł');
                        
                        if(Double.parseDouble(arrConservacion[x]) < 10.0){
                            
                            colorFondoHorizontal[x] = "background-color:#3E8249;";
                            
                        }else if(Double.parseDouble(arrConservacion[x]) >= 10.0 && Double.parseDouble(arrConservacion[x]) <= 50.0){
                            
                            colorFondoHorizontal[x] = "background-color:#2E75B6;";
                            
                        }else if(Double.parseDouble(arrConservacion[x]) > 50.0 && Double.parseDouble(arrConservacion[x]) <= 75){
                            
                            colorFondoHorizontal[x] = "background-color:#E0D900;";
                            
                        }else if(Double.parseDouble(arrConservacion[x]) > 75.0 && Double.parseDouble(arrConservacion[x]) < 90.0){
                            
                            colorFondoHorizontal[x] = "background-color:#F3A031;";
                            
                        }else if(Double.parseDouble(arrConservacion[x]) == 100.0){
                            
                            colorFondoHorizontal[x] = "background-color:#834580;";
                            
                        }else if(Double.parseDouble(arrConservacion[x]) >= 90.0){
                            
                            colorFondoHorizontal[x] = "background-color:#BF3D27;";
                            
                        }
                        
                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                        symbols.setDecimalSeparator('.');
                        
                        DecimalFormat df = new DecimalFormat("0.000" , symbols);
                        df.setDecimalSeparatorAlwaysShown(true);
                        
                        arrConservacion[x] = "<strong>" + df.format(Double.parseDouble(arrConservacion[x])) + "</strong>";
                        
                        arrTotal[x] = total;
                        
                        total = " ";
                        
                    }
                    
                    if(botonTabla == true){
                        
                        if(idioma == 1){
                            
                            archivoSalida.write("<h3 style = 'text-align: center;'>Variabilidad Tabla Conservaci&oacute;n Individual</h3>");
                            
                        }else{
                            
                            archivoSalida.write("<h3 style = 'text-align: center;'>Variability Conservation Individual Table</h3>");
                            
                        }
                        
                        archivoSalida.write("<table>");
                        
                    }
                    
                    archivoSalida.write("<tr>");
                    
                    if(botonTabla == true){
                        
                        if(idioma == 1){
                            
                            archivoSalida.write("<td rowspan='2' style = 'border-top: 1px solid black; border-left: 1px solid black; border-bottom: 1px solid black;'>Archivo</td><td style = 'border-top: 1px solid black; border-left: 1px solid black;'> Referencia </td>");
                            
                        }else{
                            
                            archivoSalida.write("<td rowspan='2' style = 'border-top: 1px solid black; border-left: 1px solid black; border-bottom: 1px solid black;'>File</td><td style = 'border-top: 1px solid black; border-left: 1px solid black;'> Reference </td>");
                            
                        }
                        
                        for(int recorrido_wt = 0; recorrido_wt < longitudReferencia; recorrido_wt++) {
                            
                            archivoSalida.write("<td style = 'border-top: 1px solid black;'>" + referencia.toCharArray()[recorrido_wt] +"</td>");
                            
                        }
                        
                    }
                    
                    archivoSalida.write("</tr>");
                    archivoSalida.write("<tr>");
                    
                    if(botonTabla == true){
                        
                        if(idioma == 1){
                            
                            archivoSalida.write("<td style = 'border-left: 1px solid black; border-bottom: 1px solid black;'> Posici&oacute;n </td>");
                            
                        }else{
                            
                            archivoSalida.write("<td style = 'border-left: 1px solid black; border-bottom: 1px solid black;';> Position </td>");
                            
                        }
                        
                        for(int recorrido_Variante = 0; recorrido_Variante < longitudReferencia; recorrido_Variante++) {
                            
                            archivoSalida.write("<td>"+ arrPosicion[recorrido_Variante] +"</td>");
                            
                        }
                        
                        botonTabla = false;
                        
                    }
                    
                    archivoSalida.write("<tr>");
                    archivoSalida.write("<td style = 'border-bottom: 1px solid black; border-top: 1px solid black; border-left: 1px solid black;'rowspan='4'>" + fichero + "</td>");
                    archivoSalida.write("</tr>");
                    archivoSalida.write("</tr>");
                    archivoSalida.write("<tr>");
                    
                    if(idioma == 1){
                        
                        archivoSalida.write("<td style = 'border-top: 1px solid black;'> Residuo </td>");
                        
                    }else{
                        
                        archivoSalida.write("<td style = 'border-top: 1px solid black;'> Residue </td>");
                        
                    }
                    for(int recorrido_Variante = 0; recorrido_Variante < longitudReferencia; recorrido_Variante++) {
                        
                        archivoSalida.write("<td style = '"+ colorFondoHorizontal[recorrido_Variante] + "; border-top: 1px solid black;'>" + arrAa[recorrido_Variante] +"</td>");
                        
                    }
                    
                    archivoSalida.write("</tr>");
                    archivoSalida.write("<tr>");
                    
                    if(idioma == 1){
                        
                        archivoSalida.write("<td> Conservaci&oacute;n </strong></td>");
                        
                    }else{
                        
                        archivoSalida.write("<td> Conservation </td>");
                        
                    }
                    for(int recorrido_Variante = 0; recorrido_Variante < longitudReferencia; recorrido_Variante++) {
                        
                        archivoSalida.write("<td style = '"+ colorFondoHorizontal[recorrido_Variante] + ";'> "+ arrConservacion[recorrido_Variante] +"</td>");
                        
                    }
                    
                    archivoSalida.write("</tr>");
                    archivoSalida.write("<tr>");
                    
                    if(idioma == 1){
                        
                        archivoSalida.write("<td style = 'border-bottom: 1px solid black;'> N&uacute;mero de Secuencias </td>");
                        
                    }else{
                        
                        archivoSalida.write("<td style = 'border-bottom: 1px solid black;'> Number of Sequences </td>");
                        
                    }
                    for(int recorrido_Variante = 0; recorrido_Variante < longitudReferencia; recorrido_Variante++) {
                        
                        archivoSalida.write("<td style = 'border-bottom: 1px solid black;'>"+ arrTotal[recorrido_Variante] +"</td>");
                        
                    }
                    
                    archivoSalida.write("</tr>");
                    
                }
                
                archivoSalida.write("</table>");
                
                if(idioma == 1){
                    
                    archivoSalida.write(Menu_Lateral.body("Variabilidad Tabla Conservaci&oacute;n Individual", true, true, true));
                    
                }else{
                    
                    archivoSalida.write(Menu_Lateral.body("Variability Conservation Individual Table", true, true, true));
                    
                }
                
                archivoSalida.write("</body>\n");
                archivoSalida.write("</html>\n");
            }
            
        }catch(IOException | NumberFormatException e) {
        
            Logger.getLogger(Conservacion_Individual_Tabla.class.getName()).log(Level.SEVERE, null, e);
                                    
            btn_presionado = false;
                                    
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                                    
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                                    
            Terminar_Hilos.cargarTerminarHilos();
                                    
        }
        
    }
    
}
