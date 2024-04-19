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
import static com.mycompany.epimolbio.Interfaz.jComboBox21;
import static com.mycompany.epimolbio.Interfaz.jTextField29;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Consensos {
    
    public static int longitud;
    public static String matrizDatos[][];
    public static int contadorConsensos = 0;
    public static int contadorConsensosAbsoluto; 
    public static int contadorConsensosSalidaTabla[] = new int[10000];
    
    //Genera consensos y consensos de consensos.
    
    public static void cargarConsensos(String rutaCarga, String rutaGuardado, int ronda, String longitud_) {
        
        try{
            
            String referencia = jTextField29.getText().toUpperCase();
            
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
            
            double valorDivision;
            
            ronda = ronda -1;
            
            for(int i = 0; i < contadorConsensosSalidaTabla.length; i++){
                
                contadorConsensosSalidaTabla[i] = -1;
                
            }
            
            String htmlSolapado[] = new String[50000];
            
            for(int i = 0; i < htmlSolapado.length; i++){
                
                htmlSolapado[i] = "";
                
            }
            
            FileWriter ficheroSalida2;
            
            longitud = Integer.parseInt(longitud_);
            
            matrizDatos = new String[10000][longitud];
            
            String consensoAuxiliar;
     
            int seleccionNivelConsenso = ronda; 
            
            double valorEncontradosSecundario = 0.0;
            String valorFinalCortadoSecundario[];
            int valorIndexSecundario;
            String numericoSalida;
 
            double valorEncontrados = 0.0;
            String valorFinalCortado[];
            int valorIndex;
            double valorConsensoTemp = 0.0;
            String consensoTemp;
            String valorTemp = "";
            
            String monomeroCortadoVertical[];
            String numericoCortadoVertical[];
            String valorFinal;

            String posicionNumerico;
            
            String consenso;
            
            for(int r = 0; r < 10000; r++){
            
                for(int i = 0; i < longitud; i++){
                
                    matrizDatos[r][i] = "";
                    
                }
            
            }
        
            String resultado[];
            
            String rutaGuardadoTxt;
            
            if(idioma == 1){
            
                rutaGuardadoTxt = rutaGuardado + "/Consensos.txt";
            
            
            }else{
                
                rutaGuardadoTxt = rutaGuardado + "/Consensus.txt";
            
                
            }
            
            FileWriter ficheroSalida1 = null;
            
            if(seleccionNivelConsenso == 1 && ronda == 1){
            
                ficheroSalida1  = new FileWriter(rutaGuardadoTxt);
            
            }
            
            final File carpeta = new File(rutaCarga);
            String ficheros[] = new String[5000];

            for(int i = 0; i < ficheros.length; i++) {

                ficheros[i] = "";

            }

            int a = 0;

            for (File ficheroEntrada : carpeta.listFiles()) {

                ficheros[a] = (ficheroEntrada.getName());
                a++;
           
            }
        
            int valorMatriz;
            
            if(seleccionNivelConsenso == 1){
                 
                for(int x = 0; x < ficheros.length; x++){
        
                    if(ficheros[x].equals("")){
                
                        break;
                
                    }
            
                    consenso = "";
                                
                    for(int i = 0; i < longitud; i++){
        
                        consensoAuxiliar = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(rutaCarga + ficheros[x], (i+1), 'ł', "↓", 20, 1, 0, "", 0, 0, 0, 2, 0, 'ł') + "|";
                                                        
                        if(consensoAuxiliar.equals("|")){
                            
                            consensoAuxiliar = "?,0.0|";
                                
                        }
                        
                        consenso += consensoAuxiliar;
                    
                    }
                    
                    resultado = consenso.split("\\|");
           
                    valorMatriz = x*2;
            
                    for(int n = 0; n < resultado.length; n++){
               
                        for(int t = 0; t < resultado[n].length(); t++){
                
                            if(Character.isLetter(resultado[n].toCharArray()[t]) || resultado[n].toCharArray()[t] == '?' || resultado[n].toCharArray()[t] == '*'){
                        
                                matrizDatos[valorMatriz][n] += resultado[n].toCharArray()[t];
                        
                            }else if(Character.isDigit(resultado[n].toCharArray()[t]) || resultado[n].toCharArray()[t] == '.' ||
                                resultado[n].toCharArray()[t] == '@'){
                              
                                matrizDatos[(valorMatriz + 1)][n] += resultado[n].toCharArray()[t];
                                
                            }
                
                        }
                 
                    }
                }
                
                for(int r = 0; r < 10000; r++){
            
                    if(matrizDatos[r][0].equals("")){
                    
                        break;
                    
                    }
                
                    for(int i = 0; i < longitud; i++){
                        
                        ficheroSalida1.write(matrizDatos[r][i] + "|");
                
                    }
                
                    ficheroSalida1.write("\r\n");
            
                }
        
                ficheroSalida1.close();
                
            }else{
                
                String ficheros2[] = new String[5000];

                for(int i = 0; i < ficheros2.length; i++){
                
                    ficheros2[i] = "";
                
                }
                
                int s = 0;
                
                for (File ficheroEntrada : carpeta.listFiles()) {
                    
                    ficheros2[s] = (ficheroEntrada.getName());
                    s++;
           
                }
                        
                String valorPorcentajesCortado[];        
                String encontradosCadena;
                String fichero;
                
                for (String ficheros21 : ficheros2) {
                    
                    if (ficheros21.equals("")) {
                        
                        break;
                        
                    }
                    
                    fichero = "";
                    
                    for (int i = 0; i < ficheros21.length(); i++) {
                        
                        if (ficheros21.toCharArray()[i] == '.' && ficheros21.toCharArray()[i+1] == 't' && ficheros21.toCharArray()[i+2] == 'x' && ficheros21.toCharArray()[i+3] == 't') {
                            
                            break;
                            
                        }
                        
                        fichero += ficheros21.toCharArray()[i];
                    }
                    
                    ficheroSalida2 = new FileWriter(rutaGuardado + "/" + fichero + ".html");
                    ficheroSalida1  = new FileWriter(rutaGuardado + "/" + fichero + ".txt");
                    lectorSecuencias(rutaCarga + ficheros21);
                    
                    char encontrados[] = new char[500];
                    
                    for(int i = 0; i < encontrados.length; i++){
            
                        encontrados[i] = 'ł';
            
                    }
                    
                    encontradosCadena = "";
                    valorFinal = "";
                    numericoSalida = "";
                    posicionNumerico = "";
                    
                    for(int i = 0; i < longitud; i++){
            
                        for(int x = 0; x < 10000; x+=2){
                
                            encontradosCadena += matrizDatos[x][i] + "|";
                            posicionNumerico += matrizDatos[x+1][i] + "|";
                             
                        }
            
                        monomeroCortadoVertical = encontradosCadena.split("\\|");
                        numericoCortadoVertical = posicionNumerico.split("\\|");
            
                        contadorConsensos = contadorConsensosAbsoluto;
                        
                        for (String monomero_cortado_vertical1 : monomeroCortadoVertical) {
                            
                            if (monomero_cortado_vertical1.equals("?")) {
                                
                                contadorConsensos--;
                                
                            }
                            
                        }
                        
                        contadorConsensosSalidaTabla[i] = contadorConsensos;
                        
                        for(int l = 0; l < encontrados.length; l++){
            
                            encontrados[l] = 'ł';
            
                        }
                                      
                        encontradosCadena = encontradosCadena.replace("|", "");
                        
                        for(int seleccionNoRepetidos = 0; seleccionNoRepetidos < encontradosCadena.length(); seleccionNoRepetidos++){
                                                
                            if(seleccionNoRepetidos == 0){
                        
                                encontrados[seleccionNoRepetidos] = encontradosCadena.toCharArray()[seleccionNoRepetidos];
                        
                            }else{
                        
                                for(int interno = 0; interno < encontrados.length; interno++){
                            
                                    if(encontrados[interno] == encontradosCadena.toCharArray()[seleccionNoRepetidos]){
                                        
                                        break;
                                
                                    }
                            
                                    else if(encontrados[interno] == 'ł'){
                                        
                                        encontrados[interno] = encontradosCadena.toCharArray()[seleccionNoRepetidos];
                                                                            
                                        break;
                                
                                    }
                                    
                                }
                            }
                    
                        }

                        for(int sx = 0; sx < encontrados.length; sx++){
                      
                            if(encontrados[sx] == 'ł'){
                          
                                break;
                      
                            }
                    
                            ficheroSalida1.write(encontrados[sx]);
                    
                        }
                    
                        ficheroSalida1.write("|");
                        
                        for(int o = 0; o < encontrados.length; o++){
                            
                            if(encontrados[o] == 'ł'){
                                
                                break;
                            
                            }
                    
                            for(int r = 0; r < monomeroCortadoVertical.length; r++){
                                
                                valorIndexSecundario = monomeroCortadoVertical[r].indexOf(encontrados[o]);
                                
                                if(valorIndexSecundario != -1){
                            
                                    valorFinalCortadoSecundario = numericoCortadoVertical[r].split("\\@");
                          
                                    valorEncontradosSecundario += Double.parseDouble(valorFinalCortadoSecundario[valorIndexSecundario]);

                                }
                     
                            }
                            
                            if(valorEncontradosSecundario != 0 && contadorConsensos != 0){
                            
                                numericoSalida += valorEncontradosSecundario/contadorConsensos + "@";
                                
                            }else{
                                
                                numericoSalida += 0.0 + "@";
                                
                            }
                            
                            valorEncontradosSecundario = 0.0;
                        }
                
                        numericoSalida += "|";
                
                        consensoTemp = "";
                        
                        for(int o = 0; o < encontrados.length; o++){
                            
                            if(encontrados[o] == 'ł'){
                                
                                break;
                            
                            }
                  
                            for(int r = 0; r < monomeroCortadoVertical.length; r++){
                                
                                valorIndex = monomeroCortadoVertical[r].indexOf(encontrados[o]);
                                
                                if(valorIndex != -1){
                            
                                    valorFinalCortado = numericoCortadoVertical[r].split("\\@");
                                
                                    valorEncontrados += Double.parseDouble(valorFinalCortado[valorIndex]);

                                }
                        
                            }
                                            
                            if(valorEncontrados == 0 && contadorConsensos == 0){
                                
                                valorDivision = 0.0;
                                
                            }else{
                                
                                valorDivision = valorEncontrados/contadorConsensos;
                                
                            }
   
                            if(valorDivision > 50.0){
                                
                                consensoTemp = String.valueOf(encontrados[o] + "|");
                        
                                valorTemp = String.valueOf(valorDivision);
                        
                                valorEncontrados = 0;
                                
                                htmlSolapado[i] = consensoTemp;
                        
                                break;
                        
                            }else{
                                
                                if(valorDivision > valorConsensoTemp){
                                    
                                    valorConsensoTemp = valorDivision;
                                    consensoTemp = String.valueOf(encontrados[o] + "|");
                                    valorTemp = String.valueOf(valorDivision);
                                    
                                    htmlSolapado[i] = consensoTemp;
                                    
                                }else if(valorDivision == valorConsensoTemp){
                                    
                                    consensoTemp += String.valueOf(encontrados[o]);
                                    
                                    valorTemp = String.valueOf(valorDivision);
                           
                                    htmlSolapado[i] = consensoTemp;
                                    
                                }
                        
                            }

                            valorEncontrados = 0;
                                                   
                        }
                
                        valorConsensoTemp = 0;
                        
                        
                        valorFinal += valorTemp + "|";
                                        
                        encontradosCadena = "";
                        posicionNumerico = "";
                        valorTemp = "";
            
                    }
                    
                    ficheroSalida1.write("\r\n");
                    ficheroSalida1.write(numericoSalida);
                    valorPorcentajesCortado = valorFinal.split("\\|");
                    DecimalFormat df = new DecimalFormat("0.000", symbols);
                    df.setDecimalSeparatorAlwaysShown(true);
                    String fondo = "";
                    
                    ficheroSalida2.write("<!DOCTYPE html>");
                    ficheroSalida2.write("<html lang = 'es'>");
                    ficheroSalida2.write("<head>");
                    ficheroSalida2.write("<meta charset = 'UTF-8'/>");
                    ficheroSalida2.write(Menu_Lateral.head());
                    ficheroSalida2.write(Estilo_Tablas_html.cssTabla());
                    ficheroSalida2.write("</head><body>");
                    
                    if(jComboBox21.getSelectedIndex() == 2){
                            
                        if(idioma == 1){
                            
                            ficheroSalida2.write(Menu_Lateral.body("Variabilidad Consensos", true, true, true));
                            ficheroSalida2.write("<h3 style='text-align: center;'>Variabilidad Consensos</h3>");
                            
                        }else{
                                
                            ficheroSalida2.write(Menu_Lateral.body("Variability Consensus", true, true, true));
                            ficheroSalida2.write("<h3 style='text-align: center;'>Variability Consensus</h3>");
                            
                        }
                        
                    }
                    
                    ficheroSalida2.write("<table><tr>");
                    
                    if(idioma == 1){
                            
                        ficheroSalida2.write("<td> Referencia </td>");
                            
                    }else{
                            
                        ficheroSalida2.write("<td> Reference </td>");
                        
                    }
                    
                    for(int i = 0; i < referencia.length(); i++){
                            
                        ficheroSalida2.write("<td>" + referencia.toCharArray()[i] + "</td>");
                        
                    }
                    
                    if(idioma == 1){
                      
                        ficheroSalida2.write("</tr><td> Posici&oacute;n </td>");
                
                    }else{
                            
                        ficheroSalida2.write("</tr><td> Position </td>");
                        
                    }
                    
                    for(int i = 1; i <= longitud; i++){
                        
                        ficheroSalida2.write("<td colspan = '1'>" + i + "</td>");
                        
                    }
                    
                    if(idioma == 1){
                        
                        ficheroSalida2.write("<tr/><tr><td> Residuo </td>");
                
                    }else{
                            
                        ficheroSalida2.write("<tr/><tr><td> Residue </td>");
                        
                    }
                    
                    for(int i = 0; i < valorPorcentajesCortado.length; i++){
                                                
                        if(Double.parseDouble(valorPorcentajesCortado[i]) == 100.0){
                                
                            fondo = "#834580";
                                
                        }else if(Double.parseDouble(valorPorcentajesCortado[i]) >= 90.0){
                        
                            fondo = "#BF3D27";
                       
                        }else if(Double.parseDouble(valorPorcentajesCortado[i]) < 10.0 && Double.parseDouble(valorPorcentajesCortado[i]) > 0.0){
                    
                            fondo = "#3E8249";
                    
                        }else if(Double.parseDouble(valorPorcentajesCortado[i]) <= 50.0 && Double.parseDouble(valorPorcentajesCortado[i]) >= 10.0){
                    
                            fondo = "#2E75B6";
                    
                        }else if(Double.parseDouble(valorPorcentajesCortado[i]) <= 75.0 && Double.parseDouble(valorPorcentajesCortado[i]) > 50.0 ){
                            
                            fondo = "#E0D900";
                            
                        }else if(Double.parseDouble(valorPorcentajesCortado[i]) < 90.0 && Double.parseDouble(valorPorcentajesCortado[i]) > 75.0){
                    
                            fondo = "#F3A031";
                    
                        }else if(Double.parseDouble(valorPorcentajesCortado[i]) <= 0.0){
                        
                            fondo = "";
                        
                        }
                                     
                        if(htmlSolapado[i].replace("|", "").length() > 1){
                                
                            htmlSolapado[i] = "(" + htmlSolapado[i] + ")";
                            
                        }
                        
                        ficheroSalida2.write("<td style = 'background-color:" + fondo +";' colspan = '1'><strong>" + htmlSolapado[i].replace("|", "") + "</strong></td>");
                    
                    }
                    
                    ficheroSalida2.write("</tr><tr>");
                    
                    if(idioma == 1){
                        
                        ficheroSalida2.write("<td> Conservaci&oacute;n </td>");
                                      
                    }else{
                            
                        ficheroSalida2.write("<td> Conservation </td>");
                        
                    }
                    
                    for (String valorPorcentajesCortado1 : valorPorcentajesCortado) {
                        
                        if (Double.parseDouble(valorPorcentajesCortado1) == 100.0) {
                            
                            fondo = "#834580";
                            
                        } else if (Double.parseDouble(valorPorcentajesCortado1) >= 90.0) {
                            
                            fondo = "#BF3D27";
                            
                        } else if (Double.parseDouble(valorPorcentajesCortado1) < 10.0 && Double.parseDouble(valorPorcentajesCortado1) > 0.0) {
                           
                            fondo = "#3E8249";
                            
                        } else if (Double.parseDouble(valorPorcentajesCortado1) <= 50.0 && Double.parseDouble(valorPorcentajesCortado1) >= 10.0) {
                            
                            fondo = "#2E75B6";
                            
                        } else if (Double.parseDouble(valorPorcentajesCortado1) <= 75.0 && Double.parseDouble(valorPorcentajesCortado1) > 50.0) {
                           
                            fondo = "#E0D900";
                            
                        } else if (Double.parseDouble(valorPorcentajesCortado1) < 90.0 && Double.parseDouble(valorPorcentajesCortado1) > 75.0) {
                            
                            fondo = "#F3A031";
                            
                        } else if (Double.parseDouble(valorPorcentajesCortado1) <= 0.0) {
                            
                            fondo = "";
                            
                        }
                        
                        ficheroSalida2.write("<td style = 'background-color:" + fondo +";' colspan = '1'><strong>" + df.format(Double.parseDouble(valorPorcentajesCortado1)) + "</strong></td>");
                    
                    }
                    
                    if(idioma == 1){
                        
                        ficheroSalida2.write("</tr><tr><td> N&uacute;mero de Secuencias </td>");
                
                    }else{
                            
                        ficheroSalida2.write("</tr><tr><td> Number of Sequences </td>");
                        
                    }
                    
                    for(int i = 0; i < longitud; i++){
                        
                        ficheroSalida2.write("<td colspan = '1'>" + contadorConsensosSalidaTabla[i] + "</td>");
                        
                    }
                    
                    ficheroSalida2.write("</tr></table>");
                    ficheroSalida2.write("</body></html>");
                    ficheroSalida2.close();
                    ficheroSalida2.close();
                    ficheroSalida1.close();
                    
                }
            }
            
        }catch(IOException | NumberFormatException e){
        
            Logger.getLogger(Consensos.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
        
    public static void lectorSecuencias(String archivo) {
		
        try{
        
            for(int i = 0; i < 10000; i++){
                
                for(int b = 0; b < longitud; b++){
                    
                    matrizDatos[i][b] = "";
                    
                }
            }
            
            FileReader f = new FileReader(archivo);
            int contadorInterno;
            
            try (BufferedReader b = new BufferedReader(f)) {
                
                String cadena;
                contadorInterno = 0;
                String linea[];
                
                while((cadena = b.readLine()) != null) {
                    
                    linea = cadena.split("\\|");
                    
                    for(int i = 0; i < longitud; i++){
                        
                        matrizDatos[contadorInterno][i] = linea[i] + "|";
                        
                    }
                    
                    contadorInterno++;
                    
                }
            }
   
            contadorConsensos = contadorInterno/2;
            
            contadorConsensosAbsoluto = contadorConsensos;
            
        }catch(IOException e){
            
            Logger.getLogger(Consensos.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
}
