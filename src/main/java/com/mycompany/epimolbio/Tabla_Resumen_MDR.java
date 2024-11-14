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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Tabla_Resumen_MDR {
    
    public static String titulo;
    
    public static int alcanceTablaResumenMDR1 = 0;
    public static int alcanceTablaResumenMDR2 = 0;
    public static int alcanceTablaResumenMDR3 = 0;
    
    /*Busca las mutaciones de resistencia de las proteinas PR, RT, IN y p24 del VIH-1 y VIH-2, del archivo o archivos .fasta cargados,
    valiendose de la clase Calculos_Frecuencias_Posicion. El resultado es una tabla resumen .html.*/
    
    /*Search for resistance mutations in the PR, RT, IN, and p24 proteins of HIV-1 and HIV-2 from the loaded .fasta file(s),
    using the Calculos_Frecuencias_Posicion class. The result is a summary .html table.*/
    
    public static void cargarTablaResumenMDR(String entrada, String salida, int proteina){
    
        try{
       
            File carpetaEntrada = new File(entrada);
            
            try (FileWriter salidaArchivo = new FileWriter(salida)) {
                
                alcanceTablaResumenMDR1 = 0;
                alcanceTablaResumenMDR2 = 0;
                alcanceTablaResumenMDR3 = 0;
                
                salidaArchivo.write("<!DOCTYPE html>\n");
                salidaArchivo.write("<html lang = 'es'>\n");
                salidaArchivo.write("<head>\n");
                salidaArchivo.write("<meta charset = 'UTF-8'/>\n");
                salidaArchivo.write(Menu_Lateral.head());
                salidaArchivo.write(Estilo_Tablas_html.cssTabla());
                salidaArchivo.write("</head>\n");
                salidaArchivo.write("<body>\n");
                
                String ficheros[];
                
                ficheros = carpetaEntrada.list();
                Arrays.sort(ficheros);
                
                String composicion = "";
                
                String archivoDeseado = "com/mycompany/epimolbio/MDR/mdr.txt";

                ClassLoader classLoader = Lista_MDR.class.getClassLoader();

                InputStream inputStream = classLoader.getResourceAsStream(archivoDeseado);
                    
                boolean escribirAlcance1 = true;
                boolean escribirAlcance2 = false;
                boolean escribirAlcance3 = false;
                
                String cadena;
                     
                int contadorDivision = 0;
                
                boolean proteinaSeleccionada = false;
                    
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    
                while ((cadena = reader.readLine()) != null) {
                        
                    if(proteinaSeleccionada == true) {
                            
                        if(cadena.contains("PROTEINA")){
                                
                            break;
                                
                        }
                            
                        if(!cadena.contains("DIVB")) {
                                
                            composicion += cadena + ",";
                            contadorDivision++;    
                            
                        }else {
                                
                            if(escribirAlcance1 == true){
                                
                                alcanceTablaResumenMDR1 = contadorDivision;
                                    
                                escribirAlcance1 = false;
                                escribirAlcance2 = true;
                                    
                                    
                            }else if(escribirAlcance2 == true){
                                    
                                alcanceTablaResumenMDR2 = contadorDivision;
                                escribirAlcance2 = false;
                                escribirAlcance3 = true;
                                    
                            }else if(escribirAlcance3 == true){
                                    
                                alcanceTablaResumenMDR3 = contadorDivision;
                                    
                            }
                            
                        }
                            
                    }
                        
                    if(cadena.contains("PROTEINA " + proteina)) {
                            
                        proteinaSeleccionada = true;
                            
                    }
                        
                }       
                                
                switch (proteina) {
                    
                    case 1:
                        
                        //MDR IP VIH-1
                        //DRM PI HIV-1
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-IP VIH-1", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-IP VIH-1";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Acquired Resistance Mutations DRM-PI HIV-1", true, true, true));
                            titulo = "Summary Table Acquired Resistance Mutations DRM-PI HIV-1";
                            
                        }
                        
                        break;
                        
                    case 2:
                        
                        //MDR ITIAN VIH-1
                        //DRM NRTI HIV-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-1", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-1";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Acquired Resistance Mutations DRM-NRTI HIV-1", true, true, true));
                            titulo = "Summary Table Acquired Resistance Mutations DRM-NRTI HIV-1";
                            
                        }
                        
                        break;
                        
                    case 3:
                        
                        //MDR ITINAN VIH-1
                        //DRM NNRTI HIV-1
                                 
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-ITINAN VIH-1", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-ITINAN VIH-1";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Acquired Resistance Mutations DRM-NNRTI HIV-1", true, true, true));
                            titulo = "Summary Table Acquired Resistance Mutations DRM-NNRTI HIV-1";
                            
                        }
                        
                        break;
                        
                    case 4:
                        
                        //MDR INI VIH-1
                        //DRM INSTI HIV-1
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-INI VIH-1", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-INI VIH-1";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Acquired Resistance Mutations DRM-INSTI HIV-1", true, true, true));
                            titulo = "Summary Table Acquired Resistance Mutations DRM-INSTI HIV-1";
                            
                        }
                        
                        break;
                        
                    case 5:
                        
                        //MDR IP VIH-2
                        //DRM PI HIV-2
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-IP VIH-2", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-IP VIH-2";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Acquired Resistance Mutations DRM-PI HIV-2", true, true, true));
                            titulo = "Summary Table Acquired Resistance Mutations DRM-PI HIV-2";
                            
                        }
                        
                        break;
                        
                    case 6:
                        
                        //MDR ITIAN VIH-2
                        //DRM NRTI HIV-2              
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-2", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-2";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Acquired Resistance Mutations DRM-NRTI HIV-2", true, true, true));
                            titulo = "Summary Table Acquired Resistance Mutations DRM-NRTI HIV-2";
                            
                        }
                        
                        break;
                        
                    case 7:
                        
                        //MDR INI VIH-2
                        //DRM INSTI HIV-2                
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-INI VIH-2", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-INI VIH-2";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Acquired Resistance Mutations DRM-INSTI HIV-2", true, true, true));
                            titulo = "Summary Table Acquired Resistance Mutations DRM-INSTI HIV-2";
                            
                        }
                        
                        break;
                        
                    case 8:
                        
                        //SDRM IP VIH-1
                        //SDRM PI HIV-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Transmitidas SDRM-IP", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Transmitidas SDRM-IP";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Transmited Resistance Mutations SDRM-PI", true, true, true));
                            titulo = "Summary Table Transmited Resistance Mutations SDRM-PI";
                            
                        }
                        
                        break;
                        
                    case 9:
                        
                        //SDRM ITIAN VIH-1
                        //SDRM NRTI HIV-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Transmitidas SDRM-ITIAN", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Transmitidas SDRM-ITIAN";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Transmited Resistance Mutations SDRM-NRTI", true, true, true));
                            titulo = "Summary Table Transmited Resistance Mutations SDRM-NRTI";
                            
                        }
                        
                        break;
                        
                    case 10:
                        
                        //SDRM ITINAN VIH-1
                        //SDRM NNRTI HIV-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Transmitidas SDRM-ITINAN", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Transmitidas SDRM-ITINAN";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Transmited Resistance Mutations SDRM-NNRTI", true, true, true));
                            titulo = "Summary Table Transmited Resistance Mutations SDRM-NNRTI";
                            
                        }
                        
                        break;
                        
                    case 11:
                        
                        //SDRM INI VIH-1
                        //SDRM INSTI HIV-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Transmitidas SDRM-INI", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Transmitidas SDRM-INI";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Transmited Resistance Mutations SDRM-INSTI", true, true, true));
                            titulo = "Summary Table Transmited Resistance Mutations SDRM-INSTI";
                            
                        }
                        
                        break;
                        
                    case 12:
                        
                        //MDR ICA VIH-1
                        //DRM CAI HIV-1
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-ICA VIH-1", true, true, true));
                            titulo = "Tabla Resumen Mutaciones de Resistencia Adquiridas MDR-ICA VIH-1";
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("Summary Table Acquired Resistance Mutations DRM-CAI HIV-1", true, true, true));
                            titulo = "Summary Table Acquired Resistance Mutations DRM-CAI HIV-1";
                            
                        }
                        
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
                    
                }
                
                for(int i = 0; i < division1.length; i++){
                    
                    for(int x = 0; x < division1[i].length(); x++){
                        
                        if(Character.isDigit(division1[i].toCharArray()[x])){
                            
                            posicion[i] += division1[i].toCharArray()[x] + "";
                            
                        }
                        
                    }
                    
                }
                
                for(int i = 0; i < division1.length; i++){
                    
                    for(int x = 2; x < division1[i].length(); x++){
                        
                        if(!Character.isDigit(division1[i].toCharArray()[x])){
                            
                            MDR[i] += division1[i].toCharArray()[x] + "";
                            
                        }
                    }
                    
                }
                
                String rotulo = "";
                String retorno;
                
                retorno = rotulos(proteina);
                
                salidaArchivo.write(retorno);
                
                String retornoDivisionTabla;
                
                boolean pintarComa;
                String coma;
                
                String br;
                
                int contadorBr;
                int valorTemp = 0;
                
                for (String fichero : ficheros) {
                    
                    pintarComa = false;
                    
                    salidaArchivo.write("<tr>\n");
                    salidaArchivo.write("<td colspan = '1'>" + fichero + "</td>\n");
                    salidaArchivo.write("<td colspan = '1'>\n");
                    
                    contadorBr = 1;
                    
                    for (int i = 0; i < division1.length; i++) {
                        
                        if(proteina != 0){
                            
                            retornoDivisionTabla = divisionTabla(proteina, i);
                            salidaArchivo.write(retornoDivisionTabla);
                            
                            if(!retornoDivisionTabla.equals("")){
                                
                                pintarComa = false;
                                
                            }
                            
                        }
                        
                        if(!rotulo.equals("")){
                            
                            salidaArchivo.write(rotulo);
                            
                        }
                        
                        retorno = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(entrada + "/" + fichero, Integer.parseInt(posicion[i]), wt[i].toCharArray()[0], MDR[i], 3, 1, 0, "", 0, 0, 0, 0, 0, 'ł');
                        
                        if(!retorno.equals("")) {
                            
                            if(pintarComa == true){
                                
                                coma = ",";
                                
                            }else{
                                
                                coma = "";
                                
                            }
                            
                            if(contadorBr % 4 == 0){
                                
                                br = "<br>";
                                
                            }else{
                                
                                br = "";
                                
                            }
                            
                            salidaArchivo.write(coma + br + " " + wt[i] + posicion[i] + retorno);
                            
                            pintarComa = true;
                            
                            if(valorTemp > Integer.parseInt(posicion[i])){
                                
                                contadorBr = 1;
                                
                            }
                            
                            valorTemp = Integer.parseInt(posicion[i]);
                            contadorBr++;
                            
                        }
                    }
                    
                    salidaArchivo.write("</td></tr>\n");
                    
                }
                
                salidaArchivo.write("</table>\n");
                
                salidaArchivo.write("</body>\n");
                salidaArchivo.write("</html>\n");
            }
            
            
        }catch(IOException | NumberFormatException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Tabla_Resumen_MDR.class.getName()).log(Level.SEVERE, null, e);

            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
           
        }
        
    }
    
    public static String rotulos(int proteina){
        
        try{
        
            String retorno = "";
        
            retorno += "<table>\n";
					
            retorno += "<tr>\n";
	
            if(proteina <= 7){
                        
                if(proteina != 2 && proteina != 3 || (proteina == 5 || proteina == 6 || proteina == 7)){

                    
                    retorno += "<td colspan = '4'>" + titulo + "</td>\n";
			
                }else{
                        
                    retorno += "<td colspan = '3'>" + titulo + "</td>\n";
			   
                }
        
            }else{
            
                retorno += "<td colspan = '4'>" + titulo + "</td>\n";
			
            }
        
            retorno += "</tr>\n";
					
            retorno += "<tr>\n";
		
            switch(proteina){
            
                case 0: // GENERAL
                
                    if(idioma == 1){
                
                        retorno += "<td  colspan = '1'; rowspan = '1'>Archivo</td>\n";
                        retorno += "<td colspan = '2'>MDR</td>\n";
				
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '1'>File</td>\n";
                        retorno += "<td colspan = '2'>DRM</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
                
                    break;
                
                case 1: // MDR IP VIH-1 / DRM PI HIV-1
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '2'>Archivo</td>\n";
                        retorno += "<td colspan = '3'>MDR IP</td>\n";
				
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '2'>File</td>\n";
                        retorno += "<td colspan = '3'>DRM PI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
						
                    retorno += "<tr>\n";
					
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'>Principales</td>\n";
                        retorno += "<td colspan = '1'>Accesorias</td>\n";
                        retorno += "<td colspan = '1'>Otras</td>\n";

                    }else{
                    
                        retorno += "<td colspan = '1'>Major</td>\n";
                        retorno += "<td colspan = '1'>Accessory</td>\n";
                        retorno += "<td colspan = '1'>Other</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
            
                    break;
                
                case 2: // MDR ITIAN VIH-1 / DRM NRTI HIV-1
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '2'>Archivo</td>\n";
                        retorno += "<td colspan = '2'>MDR ITIAN</td>\n";
				
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '2'>File</td>\n";
                        retorno += "<td colspan = '2'>DRM NRTI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
						
                    retorno += "<tr>\n";
						
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'>ITIAN</td>\n";
                        retorno += "<td colspan = '1'>Otras</td>\n";

                    }else{
                    
                        retorno += "<td colspan = '1'>NRTI</td>\n";
                        retorno += "<td colspan = '1'>Other</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
                
                    break;
                
                case 3: // MDR ITINAN VIH-1 / DRM NNRTI HIV-1
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '2'>Archivo</td>\n";
                        retorno += "<td colspan = '2'>MDR ITINAN</td>\n";
				
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '2'>File</td>\n";
                        retorno += "<td colspan = '2'>DRM NNRTI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
						
                    retorno += "<tr>\n";
					
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'>ITINAN</td>\n";
                        retorno += "<td colspan = '1'>Otras</td>\n";

                    }else{
                    
                        retorno += "<td colspan = '1'>NNRTI</td>\n";
                        retorno += "<td colspan = '1'>Other</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
                
                    break;
                
                case 4: // MDR INI VIH-1 / DRM INSTI HIV-1
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '2'>Archivo</td>\n";
                        retorno += "<td colspan = '3'>MDR INI</td>\n";
				
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '2'>File</td>\n";
                        retorno += "<td colspan = '3'>DRM INSTI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
						
                    retorno += "<tr>\n";
						
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'>Principales</td>\n";
                        retorno += "<td colspan = '1'>Accesorias</td>\n";
                        retorno += "<td colspan = '1'>Otras</td>\n";

                    }else{
                    
                        retorno += "<td colspan = '1'>Major</td>\n";
                        retorno += "<td colspan = '1'>Accessory</td>\n";
                        retorno += "<td colspan = '1'>Other</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
                
                    break;
         
                case 5: //MDR IP VIH-2 / DRM PI HIV-2
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '2'>Archivo</td>\n";
                        retorno += "<td colspan = '3'>MDR IP</td>\n";
				
                    }else{
                        
                        retorno += "<td colspan = '1'; rowspan = '2'>File</td>\n";
                        retorno += "<td colspan = '3'>DRM PI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
						
                    retorno += "<tr>\n";
					
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'>Principales</td>\n";
                        retorno += "<td colspan = '1'>Accesorias</td>\n";
                        retorno += "<td colspan = '1'>Otras</td>\n";

                    }else{
                    
                        retorno += "<td colspan = '1'>Major</td>\n";
                        retorno += "<td colspan = '1'>Accessory</td>\n";
                        retorno += "<td colspan = '1'>Other</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
            
                    break;
                
                case 6: // MDR ITIAN VIH-2 / DRM NRTI HIV-2
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '2'>Archivo</td>\n";
                        retorno += "<td colspan = '3'>MDR ITIAN</td>\n";
				
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '2'>File</td>\n";
                        retorno += "<td colspan = '3'>DRM NRTI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
						
                    retorno += "<tr>\n";
					
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'>Principales</td>\n";
                        retorno += "<td colspan = '1'>Accesorias</td>\n";
                        retorno += "<td colspan = '1'>Otras</td>\n";

                    }else{
                    
                        retorno += "<td colspan = '1'>Major</td>\n";
                        retorno += "<td colspan = '1'>Accessory</td>\n";
                        retorno += "<td colspan = '1'>Other</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
            
                    break;
                
                case 7: //MDR INI VIH-2 / DRM INSTI HIV-2
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '2'>Archivo</td>\n";
                        retorno += "<td colspan = '3'>MDR INI</td>\n";
				
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '2'>File</td>\n";
                        retorno += "<td colspan = '3'>DRM INSTI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
						
                    retorno += "<tr>\n";
						
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'>Principales</td>\n";
                        retorno += "<td colspan = '1'>Accesorias</td>\n";
                        retorno += "<td colspan = '1'>Otras</td>\n";

                    }else{
                    
                        retorno += "<td colspan = '1'>Major</td>\n";
                        retorno += "<td colspan = '1'>Accessory</td>\n";
                        retorno += "<td colspan = '1'>Other</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
                
                    break;
                
                case 8: // SDRM IP VIH-1 / SDRM PI HIV-1
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '1'>Archivo</td>\n";
                        retorno += "<td colspan = '3'>SDRM IP</td>\n";
				
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '1'>File</td>\n";
                        retorno += "<td colspan = '3'>SDRM PI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
                
                    break;
                
                case 9: // SDRM ITIAN VIH-1 / SDRM NRTI HIV-1
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '1'>Archivo</td>\n";
                        retorno += "<td colspan = '3'>SDRM ITIAN</td>\n";
				
                    }else{
                        
                        retorno += "<td colspan = '1'; rowspan = '1'>File</td>\n";
                        retorno += "<td colspan = '3'>SDRM NRTI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
                
                    break;
             
                case 10: // SDRM ITINAN VIH-1 / SDRM NNRTI HIV-1
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '1'>Archivo</td>\n";
                        retorno += "<td colspan = '3'>SDRM ITINAN</td>\n";
				
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '1'>File</td>\n";
                        retorno += "<td colspan = '3'>SDRM NNRTI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
                
                    break;
                
                case 11: // SDRM INI VIH-1 / SDRM INSTI HIV-1
                
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '1'>Archivo</td>\n";
                        retorno += "<td colspan = '3'>SDRM INI</td>\n";
		
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '1'>File</td>\n";
                        retorno += "<td colspan = '3'>SDRM INSTI</td>\n";
                    
                    }
                    
                    retorno += "</tr>\n";
                
                    break;
                
                case 12: //MDR ICA VIH-1 / SDRM CAI HIV-1
                    
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'; rowspan = '2'>Archivo</td>\n";
                        retorno += "<td colspan = '2'>MDR ICA</td>\n";
				
                    }else{
                    
                        retorno += "<td colspan = '1'; rowspan = '2'>File</td>\n";
                        retorno += "<td colspan = '2'>DRM CAI</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
						
                    retorno += "<tr>\n";
						
                    if(idioma == 1){
                
                        retorno += "<td colspan = '1'>Principales</td>\n";
                        retorno += "<td colspan = '1'>Accesorias</td>\n";

                    }else{
                    
                        retorno += "<td colspan = '1'>Major</td>\n";
                        retorno += "<td colspan = '1'>Accessory</td>\n";
                    
                    }
                
                    retorno += "</tr>\n";
                
                    break;
                 
                    
            }
        
            return retorno;
        
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Tabla_Resumen_MDR.class.getName()).log(Level.SEVERE, null, e);

            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return "";
        
        }
        
    }
    
    public static String divisionTabla(int proteina, int i){
    
        try{
            
            String retorno = "";
            
           if(proteina == 1 && i == alcanceTablaResumenMDR2){
                
                retorno = "</td><td colspan = '1'>";
                
            }else if(proteina == 1 && i == alcanceTablaResumenMDR3){
                
                retorno = "</td><td colspan = '1'>";
                
            }else if(proteina == 2 && i == alcanceTablaResumenMDR2){
            
                retorno = "</td><td colspan = '1'>";
            
            }else if(proteina == 3 && i == alcanceTablaResumenMDR2){
            
                retorno = "</td><td colspan = '1'>";
            
            }else if(proteina == 4 && i == alcanceTablaResumenMDR2){
            
                retorno = "</td><td colspan = '1'>";
            
            }else if(proteina == 4 && i == alcanceTablaResumenMDR3){
            
                retorno = "</td><td colspan = '1'>";
            
            }else if(proteina == 5 && i == alcanceTablaResumenMDR2){
                
                retorno = "</td><td colspan = '1'>";
                
            }else if(proteina == 5 && i == alcanceTablaResumenMDR3){
                
                retorno = "</td><td colspan = '1'>";
                
            }else if(proteina == 6 && i == alcanceTablaResumenMDR2){
                
                retorno = "</td><td colspan = '1'>";
                
            }else if(proteina == 6 && i == alcanceTablaResumenMDR3){
                
                retorno = "</td><td colspan = '1'>";
                
            }else if(proteina == 7 && i == alcanceTablaResumenMDR2){
                
                retorno = "</td><td colspan = '1'>";
                
            }else if(proteina == 7 && i == alcanceTablaResumenMDR3){
                
                retorno = "</td><td colspan = '1'>";
                
            }else if(proteina == 12 && i == alcanceTablaResumenMDR2){
            
                retorno = "</td><td colspan = '1'>";
            
            }
                        
            return retorno;
        
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Tabla_Resumen_MDR.class.getName()).log(Level.SEVERE, null, e);

            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return "";
        
        }
    }
}
