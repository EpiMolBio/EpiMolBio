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

public class Filtrado_Secuencias_Parciales {
    
    //Genera archivos .fasta a partir de otros, eliminando las secuencias que están incompletas en un porcentaje dado.
    //Generates .fasta files from other files by removing sequences that are incomplete beyond a given percentage.
    
    public static void cargarFiltradoSecuenciasParciales(String archivo, String archivoGuardado, double porcentaje, int seleccionTipo, boolean seleccionarTipoArchivo){
      
        try{
            
            double porcentajeSalidaTotal;
            
            double porcentajeSalida;
            
            int contadorSecundario;
            
            String directorio = archivo;
	
            final File carpeta = new File(directorio);
	
            String ficheros[] = new String[5000];
            String ficherosSecundario[] = new String[5000];
            
            for(int i = 0; i < ficheros.length; i++) {
		
                ficheros[i] = "";
		ficherosSecundario[i] = "";
                
            }
	
            int a = 0;
            
            int contadorTotalesGeneral = 0;
            int contadorPerdidosGeneral = 0;
            
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
            
            DecimalFormat df = new DecimalFormat("0.000", symbols);
            df.setDecimalSeparatorAlwaysShown(true);
            
            int contadorTotales = 0;
            int contadorPerdidosSecundario = 0;
            
            String temp;
            String cadena;
            
            double porcentajePerdidos;
            
            int contadorPerdidos;
            
            String cadenaTemp;    
            
            FileWriter ficheroSalida  = null;
                 
            FileWriter ficheroSalida2 = null;
                        
            if(seleccionarTipoArchivo == true){
                
                ficheroSalida2 = new FileWriter(archivoGuardado);
                
                ficheroSalida2.write("<!DOCTYPE html>");
                ficheroSalida2.write("<html lang = 'es'>");
                ficheroSalida2.write("<head>");
                ficheroSalida2.write("<meta charset = 'UTF-8'/>");
                ficheroSalida2.write(Menu_Lateral.head());
                ficheroSalida2.write(Estilo_Tablas_html.cssTabla());
                ficheroSalida2.write("</head>\n");
                ficheroSalida2.write("<body>\n");
                
                if(idioma == 1){
                
                    ficheroSalida2.write(Menu_Lateral.body("Filtrado de Secuencias Parciales", true, true, true));
                    ficheroSalida2.write("<h3 style='text-align: center;'>Filtrado de Secuencias Parciales</h3>");
                    
            
                }else{
                    
                    ficheroSalida2.write(Menu_Lateral.body("Partial Sequence Filtering", true, true, true));
                    ficheroSalida2.write("<h3 style='text-align: center;'>Partial Sequence Filtering</h3>");
                    
                }
                
                if(idioma == 1){
                
                    ficheroSalida2.write("<table><tr><td>Archivo</td><td>Secuencias Totales</td><td>Secuencias Recuperadas</td><td>Secuencias Perdidas</td><td>Porcentaje Perdidas</td></tr>");
                
                }else{
                    
                    ficheroSalida2.write("<table><tr><td>File</td><td>Total Sequences</td><td>Recovered Sequences</td><td>Lost Sequences</td><td>Loss Percentage</td></tr>");
                    
                }
                
            }

            for (File ficheroEntrada : carpeta.listFiles()) {
	        
                ficheros[a] = (ficheroEntrada.getName());
                a++;
			
            }
            
            if(seleccionarTipoArchivo == true){
            
                Arrays.sort(ficheros);
                
                contadorSecundario = 0;
            
                for (String fichero : ficheros) {
                    
                    if (!fichero.equals("")) {
                        
                        ficherosSecundario[contadorSecundario] = fichero;
                        contadorSecundario++;
                        
                    }
                }
            
                ficheros = ficherosSecundario;
            
            }
                
            for (String fichero : ficheros) {
                
                if (fichero.equals("")) {
                    
                    break;
                    
                }
                
                if (seleccionarTipoArchivo == true) {
                    
                    ficheroSalida2.write("<tr><td> " + fichero + "</td>");
                    
                }
                
                if (seleccionarTipoArchivo == false) {
                    
                    if (idioma == 1) {
                        
                        ficheroSalida = new FileWriter(archivoGuardado + "Filtrado_Parciales_" + fichero);
                        
                    } else {
                        
                        ficheroSalida = new FileWriter(archivoGuardado + "Partial_Filter_" + fichero);
                        
                    }
                    
                }
                
                FileReader f = new FileReader(archivo + fichero);
                
                try (BufferedReader b = new BufferedReader(f)) {
                    
                    temp = "";
                    contadorPerdidos = 0;
                    cadenaTemp = "";
                    
                    while((cadena = b.readLine()) != null) {
                        
                        if(cadena.toCharArray()[0] == '>') {
                            
                            if(temp.length() != 0) {
                                
                                for(int i = 0; i < temp.length(); i++){
                                    
                                    if(temp.toCharArray()[i] == '?' || temp.toCharArray()[i] == 'X' || temp.toCharArray()[i] == 'x'){
                                        
                                        contadorPerdidos++;
                                        
                                    }
                                    
                                    if((temp.toCharArray()[i] == 'N' || temp.toCharArray()[i] == 'n') && seleccionTipo == 2){
                                        
                                        contadorPerdidos++;
                                        
                                    }
                                    
                                }
                                
                                porcentajePerdidos = (100.0 - (100.0 * (double)contadorPerdidos)/(double)temp.length());
                                
                                contadorTotales++;
                                contadorTotalesGeneral++;
                                
                                if(porcentajePerdidos >= porcentaje){
                                    
                                    if(seleccionarTipoArchivo == false){
                                        
                                        ficheroSalida.write(cadenaTemp + "\r\n");
                                        ficheroSalida.write(temp.toUpperCase() + "\r\n");
                                        
                                    }
                                    
                                }else{
                                    
                                    contadorPerdidosSecundario++;
                                    contadorPerdidosGeneral++;
                                    
                                }
                            
                                contadorPerdidos = 0;
                                
                            }
                            
                            cadenaTemp = cadena;
                            
                            if(temp.length() != 0) {
                                
                                temp = "";
                                
                            }else{
                                
                                temp = "";
                                
                            }
                            
                        }else {
                            
                            temp += cadena;
                            
                        }
                        
                    }
                    
                    contadorPerdidos = 0;
                    
                    for(int i = 0; i < temp.length(); i++){
                        
                        if(temp.toCharArray()[i] == '?' || temp.toCharArray()[i] == 'X' || temp.toCharArray()[i] == 'x'){
                            
                            contadorPerdidos++;
                            
                        }
                        
                        if((temp.toCharArray()[i] == 'N' || temp.toCharArray()[i] == 'n') && seleccionTipo == 2){
                            
                            contadorPerdidos++;
                            
                        }
                        
                    }
                    
                    porcentajePerdidos = (100.0 - (100.0 * (double)contadorPerdidos)/(double)temp.length());
                    contadorTotales++;
                    contadorTotalesGeneral++;
                    
                    if(porcentajePerdidos >= porcentaje){
                        
                        if(seleccionarTipoArchivo == false){
                            
                            ficheroSalida.write(cadenaTemp + "\r\n");
                            ficheroSalida.write(temp.toUpperCase());
                            
                        }
                        
                    }else{
                        
                        contadorPerdidosSecundario++;
                        contadorPerdidosGeneral++;
                        
                    }
                }
                
                if(seleccionarTipoArchivo == false){
                
                    ficheroSalida.close();
                    
                }else{
                    
                    porcentajeSalida = (double)(contadorPerdidosSecundario * 100.0)/contadorTotales;
                    
                    String color = "";
                    
                    if(porcentajeSalida == 100.0){
                    
                        color = "#834580";
                        
                    }else if(porcentajeSalida >= 90.0){
                                
                        color = "#BF3D27";
                                
                    }else if(porcentajeSalida < 10.0){
                                
                        color = "#3E8249";
                                
                    }else if(porcentajeSalida >= 10.0 && porcentajeSalida <= 50.0){
                                
                        color = "#2E75B6";
                                
                    }else if(porcentajeSalida < 90.0 && porcentajeSalida > 75.0){
                                
                        color = "#F3A031";
                                
                    }else if(porcentajeSalida <= 75 && porcentajeSalida > 50.0){
                                
                        color = "#E0D900";
                                
                    }
                    
                    ficheroSalida2.write("<td>" + contadorTotales + "</td><td>" + (contadorTotales - contadorPerdidosSecundario) + "</td><td>" + contadorPerdidosSecundario + "</td><td>" +                                        
                            "<a style = 'color: " + color + ";'>" + df.format(porcentajeSalida) + "%</a></td></tr>");
                     
                }
                
                contadorPerdidosSecundario = 0;
                contadorTotales = 0;
                
            }
            
            if(seleccionarTipoArchivo == true){
                
                porcentajeSalidaTotal = (double)(contadorPerdidosGeneral * 100.0)/contadorTotalesGeneral;
                    
                String color_total = "";
                  
                if(porcentajeSalidaTotal == 100.0){
                
                    color_total = "#834580";
                    
                }else if(porcentajeSalidaTotal >= 90.0){
                                
                    color_total = "#BF3D27";
                                
                }else if(porcentajeSalidaTotal < 10.0){
                                
                    color_total = "#3E8249";
                                
                }else if(porcentajeSalidaTotal >= 10.0 && porcentajeSalidaTotal <= 50.0){
                                
                    color_total = "#2E75B6";
                                
                }else if(porcentajeSalidaTotal < 90.0 && porcentajeSalidaTotal > 75.0){
                                
                    color_total = "#F3A031";
                                
                }else if(porcentajeSalidaTotal <= 75 && porcentajeSalidaTotal > 50.0){
                                
                    color_total = "#E0D900";
                                
                }
                
                ficheroSalida2.write("<tr><td>Total</td>");
                                
                ficheroSalida2.write("<td>" + contadorTotalesGeneral + "</td><td>" + (contadorTotalesGeneral - contadorPerdidosGeneral) + "</td><td>" + contadorPerdidosGeneral + "</td><td>" + 
                    "<a style = 'color: " + color_total + ";'>" + df.format(porcentajeSalidaTotal) + "%</a></td></tr>");
                    
                ficheroSalida2.write("</table></html>");
                ficheroSalida2.close();
                
            }
            
        }catch(IOException e){
            
            Logger.getLogger(Filtrado_Secuencias_Parciales.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
                
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
               
            Terminar_Hilos.cargarTerminarHilos();
                
        }
        
    }
    
}
