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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class Tabla_MDR {

    public static int alcanceTablaMDR1 = 0;
    public static int alcanceTablaMDR2 = 0;
    public static int alcanceTablaMDR3 = 0;
    public static int alcanceTablaMDR4 = 0;
    
    /*Busca las mutaciones de resistencia de las proteinas PR, RT, IN y p24 del VIH-1 y VIH-2, del archivo o archivos .fasta cargados,
    valiendose de la clase Calculos_Frecuencias_Posicion. El resultado es una tabla .html.*/
    
    public static void CargarTablaMDR(String entrada, String salida, int proteina){
        
        try{
            
            alcanceTablaMDR1 = 0;
            alcanceTablaMDR2 = 0;
            alcanceTablaMDR3 = 0;
            alcanceTablaMDR4 = 0;
            
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
            
            String temporalTablaValor;
            
            try (FileWriter salidaArchivo = new FileWriter(salida)) {
                
                salidaArchivo.write("<!DOCTYPE html>\n");
                salidaArchivo.write("<html lang = 'es'>\n");
                salidaArchivo.write("<head>\n");
                salidaArchivo.write("<meta charset = 'UTF-8'/>\n");
                salidaArchivo.write(Menu_Lateral.head());
                salidaArchivo.write(Estilo_Tablas_html.cssTabla());
                salidaArchivo.write("</head>\n");
                salidaArchivo.write("<body>\n");
                
                String archivoDeseado = "com/mycompany/epimolbio/MDR/mdr.txt";

                ClassLoader classLoader = Lista_MDR.class.getClassLoader();

                InputStream inputStream = classLoader.getResourceAsStream(archivoDeseado);
                
                String composicion;
                
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    
                    String cadena;
                    composicion = "";
                    int contadorDivision = 0;
                    boolean proteinaSeleccionada = false;
                    String MDRComun = "";
                    boolean escribirAlcance1 = true;
                    boolean escribirAlcance2 = false;
                    boolean escribirAlcance3 = false;
                    
                    while ((cadena = reader.readLine()) != null) {
                        
                        if(proteinaSeleccionada == true) {
                            
                            if(cadena.contains("PROTEINA")){
                                
                                break;
                                
                            }
                            
                            if(!cadena.contains("DIVB")) {
                                
                                for(int i = 1; i < cadena.length(); i++){
                                    
                                    if (Character.isDigit(cadena.toCharArray()[i])) {
                                        
                                        MDRComun += cadena.toCharArray()[i];
                                        
                                        
                                    }else {
                                        
                                        composicion += cadena.toCharArray()[0] + MDRComun + cadena.toCharArray()[i] + ",";
                                        contadorDivision++;
                                        
                                    }
                                    
                                }
                                
                                MDRComun = "";
                                
                            }else {
                                
                                if(escribirAlcance1 == true){
                                    
                                    alcanceTablaMDR1 = contadorDivision;
                                    
                                    escribirAlcance1 = false;
                                    escribirAlcance2 = true;
                                    
                                    
                                }else if(escribirAlcance2 == true){
                                    
                                    alcanceTablaMDR2 = contadorDivision;
                                    escribirAlcance2 = false;
                                    escribirAlcance3 = true;
                                    
                                }else if(escribirAlcance3 == true){
                                    
                                    alcanceTablaMDR3 = contadorDivision;
                                    
                                }
                                
                                contadorDivision = 0;
                                
                            }
                            
                        }
                        
                        if(cadena.contains("PROTEINA " + proteina)) {
                            
                            proteinaSeleccionada = true;
                            
                        }
                        
                    }
                    
                    alcanceTablaMDR4 = contadorDivision;
                    
                }
                    
                switch (proteina) {
                    
                    case 1:
                        
                        //MDR IP VIH-1
                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Adquiridas MDR-IP VIH-1", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Acquired Resistance Mutations DRM-PI HIV-1", true, true, true));
                            
                        }
                        
                        break;
                        
                    case 2:
                        
                        //MDR ITIAN VIH-1
                                   
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-1", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Acquired Resistance Mutations DRM-NRTI HIV-1", true, true, true));
                            
                        }
                        
                        break;
                        
                    case 3:
                        
                        //MDR ITINAN VIH-1
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Adquiridas MDR-ITINAN VIH-1", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Acquired Resistance Mutations DRM-NNRTI HIV-1", true, true, true));
                            
                        }
                        
                        break;
                        
                    case 4:
                        
                        //MDR INI VIH-1
                          
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Adquiridas MDR-INI VIH-1", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Acquired Resistance Mutations DRM-INSTI HIV-1", true, true, true));
                            
                        }
                        
                        break;
                        
                    case 5:
                        
                        //MDR IP VIH-2
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Adquiridas MDR-IP VIH-2", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Acquired Resistance Mutations DRM-PI HIV-2", true, true, true));
                            
                        }
                        
                        break;
                        
                    case 6:
                        
                        //MDR ITIAN VIH-2
                               
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-2", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Acquired Resistance Mutations DRM-NRTI HIV-2", true, true, true));
                            
                        }
                        
                        break;
                        
                    case 7:
                        
                        //MDR INI VIH-2
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Adquiridas MDR-INI VIH-2", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Acquired Resistance Mutations DRM-INSTI HIV-2", true, true, true));
                            
                        }
                        
                        break;
                        
                    case 8:
                        
                        //SDRM IP VIH-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Transmitidas SDRM-IP", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Transmitted Resistance Mutations SDRM-PI", true, true, true));
                            
                        }
                        
                        break;
                        
                    case 9:
                        
                        //SDRM ITIAN VIH-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Transmitidas SDRM-ITIAN", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Transmitted Resistance Mutations SDRM-NRTI", true, true, true));
                            
                        }
                        
                        break;
                        
                    case 10:
                        
                        //SDRM ITINAN VIH-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Transmitidas SDRM-ITINAN", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Transmitted Resistance Mutations SDRM-NNRTI", true, true, true));
                            
                        }
                        
                        break;
                        
                    case 11:
                        
                        //SDRM INI VIH-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Transmitidas SDRM-INI", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Transmitted Resistance Mutations SDRM-INSTI", true, true, true));
                            
                        }
                        
                        break;
                       
                    case 12:
                        
                        //MDR ICA VIH-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Mutaciones de Resistencia Adquiridas MDR-ICA VIH-1", true, true, true));
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Table Acquired Resistance Mutations DRM-CAI HIV-1", true, true, true));
                                                        
                        }
                        
                        break;
                        
                    default:
                        
                        break;
                        
                }
                
                String division1[] = composicion.split(",");
                
                String wt[] = new String[division1.length];
                String posicion[] = new String[division1.length];
                String MDR[] = new String[division1.length];
                
                for(int i = 0; i < division1.length; i++){
                    
                    wt[i] = "";
                    posicion[i] = "";
                    MDR[i] = "";
                    
                }
                
                for(int i = 0; i < division1.length; i++){
                    
                    wt[i] = division1[i].toCharArray()[0] + "";
                    MDR[i] = division1[i].toCharArray()[division1[i].length()-1] + "";
                    
                }
                
                for(int i = 0; i < division1.length; i++){
                    
                    for(int x = 0; x < division1[i].length(); x++){
                        
                        if(Character.isDigit(division1[i].toCharArray()[x])){
                            
                            posicion[i] += division1[i].toCharArray()[x] + "";
                            
                        }
                        
                    }
                    
                }
                
                String ficheros[];
                
                File carpetaEntrada = new File(entrada);
                
                ficheros = carpetaEntrada.list();
                Arrays.sort(ficheros);
                
                String tablaMatriz[][] = new String[ficheros.length][division1.length];
                
                salidaArchivo.write("<table>\n");
                
                String rotulo = "";
                
                if(proteina != 0){
                    
                    rotulo = rotulos(proteina);
                    
                }
                
                if(!rotulo.equals("")){
                    
                    salidaArchivo.write(rotulo);
                    
                }
                
                for(int i = 0; i < ficheros.length; i++){
                    
                    for(int x = 0; x < division1.length; x++){
                        
                        tablaMatriz[i][x] = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(entrada + "/" + ficheros[i], Integer.parseInt(posicion[x]), wt[x].toCharArray()[0], MDR[x], 2, 1, 0, "", 0, 0, 0, 0, 0, 'ł');
                        
                    }
                    
                    if(i == 0){
                        
                        salidaArchivo.write("<td><strong>" + "" +"</strong></td>\n");
                        
                        for (String division11 : division1) {
                            
                            salidaArchivo.write("<td>" + division11 + "</td>\n");
                            
                        }
                        
                    }
                    
                    salidaArchivo.write("<tr>");
                    
                    salidaArchivo.write("<td>" + ficheros[i] +"</td>\n");
                    
                    for(int x = 0; x < division1.length; x++) {
                        
                        switch (tablaMatriz[i][x].toCharArray()[0]) {
                            
                            case 'p':    
                            {
                                
                                salidaArchivo.write("<td style = 'background-color: #834580;'>");
                                temporalTablaValor = "";
                                
                                for(int valor = 2; valor < tablaMatriz[i][x].length(); valor++){
                                    
                                    if(tablaMatriz[i][x].toCharArray()[valor-1] == '|'){
                                        
                                        for(int valorInterno = valor; valorInterno < tablaMatriz[i][x].length(); valorInterno++){
                                            
                                            temporalTablaValor += tablaMatriz[i][x].toCharArray()[valorInterno];
                                            
                                            if(tablaMatriz[i][x].toCharArray()[valorInterno] == ' '){
                                                
                                                break;
                                                
                                            }
                                            
                                        }
                                        
                                    }
                                    
                                }
                                
                                DecimalFormat df = new DecimalFormat("0.000", symbols);
                                df.setDecimalSeparatorAlwaysShown(true);
                                salidaArchivo.write("<strong>" + df.format(Double.parseDouble(temporalTablaValor)) + "%  " +"</strong></td>\n");
                                
                                break;
                                
                            }
                            
                            case 'r':
                            {
                                
                                salidaArchivo.write("<td style = 'background-color: #BF3D27;'>");
                                temporalTablaValor = "";
                                
                                for(int valor = 2; valor < tablaMatriz[i][x].length(); valor++){
                                    
                                    if(tablaMatriz[i][x].toCharArray()[valor-1] == '|'){
                                        
                                        for(int valorInterno = valor; valorInterno < tablaMatriz[i][x].length(); valorInterno++){
                                            
                                            temporalTablaValor += tablaMatriz[i][x].toCharArray()[valorInterno];
                                            
                                            if(tablaMatriz[i][x].toCharArray()[valorInterno] == ' '){
                                                
                                                break;
                                                
                                            }
                                            
                                        }
                                        
                                    }
                                    
                                }
                                
                                DecimalFormat df = new DecimalFormat("0.000", symbols);
                                df.setDecimalSeparatorAlwaysShown(true);
                                salidaArchivo.write("<strong>" + df.format(Double.parseDouble(temporalTablaValor)) + "%  " +"</strong></td>\n");
                                
                                break;
                                
                            }
                            
                            case 'n':
                            {
                                
                                salidaArchivo.write("<td style = 'background-color: #F3A031;'>");
                                temporalTablaValor = "";
                                
                                for(int valor = 2; valor < tablaMatriz[i][x].length(); valor++){
                                    
                                    if(tablaMatriz[i][x].toCharArray()[valor-1] == '|'){
                                        
                                        for(int valorInterno = valor; valorInterno < tablaMatriz[i][x].length(); valorInterno++){
                                            
                                            temporalTablaValor += tablaMatriz[i][x].toCharArray()[valorInterno];
                                            
                                            if(tablaMatriz[i][x].toCharArray()[valorInterno] == ' '){
                                                
                                                break;
                                                
                                            }
                                            
                                        }
                                        
                                    }
                                    
                                }
                                
                                DecimalFormat df = new DecimalFormat("0.000", symbols);
                                df.setDecimalSeparatorAlwaysShown(true);
                                salidaArchivo.write("<strong>" + df.format(Double.parseDouble(temporalTablaValor)) + "%  " +"</strong></td>\n");
                                
                                break;
                                
                            }
                            
                            case 'b':
                            {
                                
                                salidaArchivo.write("<td style = 'background-color: #2E75B6;'>");
                                temporalTablaValor = "";
                                
                                for(int valor = 2; valor < tablaMatriz[i][x].length(); valor++){
                                    
                                    if(tablaMatriz[i][x].toCharArray()[valor-1] == '|'){
                                        
                                        for(int valorInterno = valor; valorInterno < tablaMatriz[i][x].length(); valorInterno++){
                                            
                                            temporalTablaValor += tablaMatriz[i][x].toCharArray()[valorInterno];
                                            
                                            if(tablaMatriz[i][x].toCharArray()[valorInterno] == ' '){
                                                
                                                break;
                                                
                                            }
                                            
                                        }
                                        
                                    }
                                    
                                }
                                
                                DecimalFormat df = new DecimalFormat("0.000", symbols);
                                df.setDecimalSeparatorAlwaysShown(true);
                                salidaArchivo.write("<strong>" + df.format(Double.parseDouble(temporalTablaValor)) + "%  " +"</strong></td>\n");
                                
                                break;
                                
                            }
                            
                            case 'a':
                            {
                                
                                salidaArchivo.write("<td style = 'background-color: #E0D900;'>");
                                temporalTablaValor = "";
                                
                                for(int valor = 2; valor < tablaMatriz[i][x].length(); valor++){
                                    
                                    if(tablaMatriz[i][x].toCharArray()[valor-1] == '|'){
                                        
                                        for(int valorInterno = valor; valorInterno < tablaMatriz[i][x].length(); valorInterno++){
                                            
                                            temporalTablaValor += tablaMatriz[i][x].toCharArray()[valorInterno];
                                            
                                            if(tablaMatriz[i][x].toCharArray()[valorInterno] == ' '){
                                                
                                                break;
                                                
                                            }
                                            
                                        }
                                        
                                    }
                                    
                                }
                                
                                DecimalFormat df = new DecimalFormat("0.000", symbols);
                                df.setDecimalSeparatorAlwaysShown(true);
                                salidaArchivo.write("<strong>" + df.format(Double.parseDouble(temporalTablaValor)) + "%  " +"</strong></td>\n");
                                
                                break;
                                
                            }
                            
                            case 'v':
                            {
                                
                                salidaArchivo.write("<td style = 'background-color: #3E8249;'>");
                                temporalTablaValor = "";
                                
                                for(int valor = 2; valor < tablaMatriz[i][x].length(); valor++){
                                    
                                    if(tablaMatriz[i][x].toCharArray()[valor-1] == '|'){
                                        
                                        for(int valorInterno = valor; valorInterno < tablaMatriz[i][x].length(); valorInterno++){
                                            
                                            temporalTablaValor += tablaMatriz[i][x].toCharArray()[valorInterno];
                                            
                                            if(tablaMatriz[i][x].toCharArray()[valorInterno] == ' '){
                                                
                                                break;
                                                
                                            }
                                            
                                        }
                                        
                                    }
                                    
                                }
                                
                                DecimalFormat df = new DecimalFormat("0.000", symbols);
                                df.setDecimalSeparatorAlwaysShown(true);
                                salidaArchivo.write("<strong>" + df.format(Double.parseDouble(temporalTablaValor)) + "%  " +"</strong></td>\n");
                                
                                break;
                                
                            }
                            
                            default:
                                
                                salidaArchivo.write("<td>"+ "  " +"</td>\n");
                                break;
                                
                        }
                        
                    }
				
                    salidaArchivo.write("</tr>\n");
                    
                }
                
                salidaArchivo.write("</table>\n");
                
                salidaArchivo.write("</body>\n");
                
                salidaArchivo.write("</html>\n");
            }
            
        }catch(IOException | NumberFormatException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Tabla_MDR.class.getName()).log(Level.SEVERE, null, e);

            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
    public static String rotulos(int proteina){
        
        try{
            
            String rotulos = "";
        
            switch(proteina){
                
                case 1:
                    		
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Adquiridas MDR-IP VIH-1</td>\n";	
				
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Table Acquired Resistance Mutations DRM-PI HIV-1</td>\n";	
                        
                    }
                    
                    rotulos += "</tr>\n";
				
                    rotulos += "<tr>";
				
                    if(idioma == 1){
                    
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Principales</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR3 + "'>Accesorias</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Otras</td>\n";
                    
                    }else{
                        
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Major</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR3 + "'>Accessory</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Others</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
                    
                    break;
                
                case 2:
                    		
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){   
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-1</td>\n";
				
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR4) + "'>Table Acquired Resistance Mutations DRM-NRTI HIV-1</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
				
                    rotulos += "<tr>";
				
                    if(idioma == 1){
                    
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>ITIAN</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Otras</td>\n";

                    }else{
                        
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>NRTI</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Others</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
                    
                    break;
                    
                case 3:
				
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
     
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Adquiridas MDR-ITINAN VIH-1</td>\n";
				
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR4) + "'>Table Acquired Resistance Mutations DRM-NNRTI HIV-1</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
				
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                    
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>ITINAN</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'><strong>Otras</strong></td>\n";
				
                    }else{
                        
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>NNRTI</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Others</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
                    
                    break;
                    
                case 4:
                   				
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                  
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Adquiridas MDR-INI VIH-1</td>\n";
				
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Table Acquired Resistance Mutations DRM-INSTI HIV-1</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
				
                    rotulos += "<tr>";
			
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                    
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Principales</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR3 + "'>Accesorias</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Otras</td>\n";
				
                    }else{
                        
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Major</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR3 + "'>Accessory</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Others</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
                    
                    break;
                    
                case 5:
                    				
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Adquiridas MDR-IP VIH-2</td>\n";	
				
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Table Acquired Resistance Mutations DRM-PI HIV-2</td>\n";	
                        
                    }
                    
                    rotulos += "</tr>\n";
				
                    rotulos += "<tr>";
				
                    if(idioma == 1){
                    
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Principales</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR3 + "'>Accesorias</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Otras</td>\n";
                    
                    }else{
                        
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Major</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR3 + "'>Accessory</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Others</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
                    
                    break;
                    
                case 6:
				
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-2</td>\n";	
				
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Table Acquired Resistance Mutations DRM-NRTI HIV-2</td>\n";	
                        
                    }
                    
                    rotulos += "</tr>\n";
				
                    rotulos += "<tr>";
				
                    if(idioma == 1){
                    
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Principales</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR3 + "'>Accesorias</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Otras</td>\n";
                    
                    }else{
                        
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Major</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR3 + "'>Accessory</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Others</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
                    
                    break;
                    
                case 7:
				
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Adquiridas MDR-INI VIH-2</td>\n";	
				
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Table Acquired Resistance Mutations DRM-INSTI HIV-2</td>\n";	
                        
                    }
                    
                    rotulos += "</tr>\n";
				
                    rotulos += "<tr>";
				
                    if(idioma == 1){
                    
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Principales</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR3 + "'>Accesorias</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Otras</td>\n";
                    
                    }else{
                        
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Major</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR3 + "'>Accessory</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Others</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
                    
                    break;
                    
                case 8:
                    
                    rotulos += "<table>";
            		
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Transmitidas SDRM-IP</td>\n";
            
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR4) + "'>Table Transmitted Resistance Mutations SDRM-PI</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
                    
                    break;
                    
                case 9:
                    
                    rotulos += "<table>";
            
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    			
                    if(idioma == 1){
                    
                        rotulos += "<td colspan = '" + (alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Transmitidas SDRM-ITIAN</td>\n";
            
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR4) + "'>Table Transmitted Resistance Mutations SDRM-NRTI</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
            
                    rotulos += "<tr>";
                    
                    break;
                    
                case 10:
                    
                    rotulos += "<table>";
		
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Transmitidas SDRM-ITINAN</td>\n";
            
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR4) + "'>Table Transmitted Resistance Mutations SDRM-NNRTI</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
            
                    rotulos += "<tr>";
                    
                    break;
                    
                case 11:
                    
                    rotulos += "<table>";
				
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                        
                        rotulos +="<td colspan = '" + (alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Transmitidas SDRM-INI</td>\n";
            
                    }else{
                        
                        rotulos +="<td colspan = '" + (alcanceTablaMDR4) + "'>Table Transmitted Resistance Mutations SDRM-INSTI</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
            
                    rotulos += "<tr>";
                    
                    break;  
                    
                case 12:
                    
                    rotulos += "<tr>";
				
                    rotulos += "<td> </td>\n";
                    
                    if(idioma == 1){
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Tabla Mutaciones de Resistencia Adquiridas MDR-ICA VIH-1</td>\n";	
				
                    }else{
                        
                        rotulos += "<td colspan = '" + (alcanceTablaMDR2 + alcanceTablaMDR3 + alcanceTablaMDR4) + "'>Table Acquired Resistance Mutations DRM-CAI HIV-1</td>\n";	
                        
                    }
                    
                    rotulos += "</tr>\n";
				
                    rotulos += "<tr>";
				
                    if(idioma == 1){
                    
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Principales</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Accesorias</td>\n";
                    
                    }else{
                        
                        rotulos += "<td> </td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR2 + "'>Major</td>\n";
                        rotulos += "<td colspan = '" + alcanceTablaMDR4 + "'>Accessory</td>\n";
                        
                    }
                    
                    rotulos += "</tr>\n";
                    
                    break;
                 
            }

            return rotulos;
        
        }catch(Exception e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Tabla_MDR.class.getName()).log(Level.SEVERE, null, e);

            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return "";
            
        }
    }
}
