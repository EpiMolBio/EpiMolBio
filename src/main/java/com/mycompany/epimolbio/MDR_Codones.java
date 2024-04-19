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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class MDR_Codones {
    
    public static ArrayList<String> codones = new ArrayList<>();
    
    public static int alcanceCodonesMDR1 = 0;
    public static int alcanceCodonesMDR2 = 0;
    public static int alcanceCodonesMDR3 = 0;
    
    /*Genera un archivo .html con las posiciones en las que se encuentran MDR de PR, RT, IN y p24 en codones. Las mutaciones aparecen con un asterísco.
    Se utiliza la clase Calculos_Frecuencias_Posicion_Codones para realizar los calculos.*/
    
    public static void cargarMDRCodones(String archivoCarga, String archivoGuardado, int tipoMDR, String seleccionCodon) {

        try{
                   
            String composicionDividida[];
            String mutacion;
            String posicionMDR;
            
            alcanceCodonesMDR1 = 0;
            alcanceCodonesMDR2 = 0;
            alcanceCodonesMDR3 = 0;
            
            boolean escribirAlcance1 = true;
            boolean escribirAlcance2 = false;
            boolean escribirAlcance3 = false;
            
            String tipoMDREncabezado;
            boolean codonSeleccionado;
            
            String retorno;
        
            codonSeleccionado = !seleccionCodon.equals("");
            
            boolean IP = false;
            boolean ITIAN = false;
            boolean ITINAN = false;
            boolean INI = false;
            boolean ICA = false;
        
            switch (tipoMDR) {
                
                case 1:
                    
                    IP = true;
                    break;
                    
                case 2:
                    
                    ITIAN = true;
                    break;
                    
                case 3:
                    
                    ITINAN = true;
                    break;
                    
                case 4:
                    
                    INI = true;
                    break;
                    
                case 12:
                    
                    ICA = true;
                    break;
                    
            }
            		
            String archivoEntrada = "com/mycompany/epimolbio/MDR/mdr.txt";

            ClassLoader classLoader = Lista_MDR.class.getClassLoader();

            InputStream inputStream = classLoader.getResourceAsStream(archivoEntrada);
            
            String codonesStringArr[];
        
            String cadena;
                        
            String composicion = "";
        
            int contadorDivision = 0;
       
            boolean proteinaSeleccionada = false;
        
            String MDRComun = "";
            
            String ficheroSalida1 = archivoGuardado;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        
            try (FileWriter ficheroSalida2 = new FileWriter(ficheroSalida1)) {
                
                ficheroSalida2.write("<!DOCTYPE html>\n");
                ficheroSalida2.write("<html lang = 'es'>\n");
                ficheroSalida2.write("<head>\n");
                ficheroSalida2.write("<meta charset = 'UTF-8'/>\n");
                ficheroSalida2.write(Menu_Lateral.head());
                ficheroSalida2.write(Estilo_Tablas_html.cssTabla());
                ficheroSalida2.write("</head>\n");
                ficheroSalida2.write("<body>\n");
                
                if(IP){
                    
                    if(idioma == 1){
                        
                        ficheroSalida2.write(Menu_Lateral.body("Mutaciones de Resistencia Codones MDR-IP", true, true, true));
                        ficheroSalida2.write("<h3 style = 'text-align: center;'>Mutaciones de Resistencia Codones MDR-IP</h3>");
                        
                    }else{
                        
                        ficheroSalida2.write(Menu_Lateral.body("Codon Resistance Mutations DRM-PI", true, true, true));
                        ficheroSalida2.write("<h3 style = 'text-align: center;'>Codon Resistance Mutations DRM-PI</h3>");
                        
                    }
                    
                }else if(ITIAN){
                    
                    if(idioma == 1){
                        
                        ficheroSalida2.write(Menu_Lateral.body("Mutaciones de Resistencia Codones MDR-ITIAN", true, true, true));
                        ficheroSalida2.write("<h3 style = 'text-align: center;'>Mutaciones de Resistencia Codones MDR-ITIAN</h3>");
                        
                    }else{
                        
                        ficheroSalida2.write(Menu_Lateral.body("Codon Resistance Mutations DRM-NRTI", true, true, true));
                        ficheroSalida2.write("<h3 style = 'text-align: center;'>Codon Resistance Mutations DRM-NRTI</h3>");
                        
                    }
                    
                }else if(ITINAN){
                    
                    if(idioma == 1){
                        
                        ficheroSalida2.write(Menu_Lateral.body("Mutaciones de Resistencia Codones MDR-ITINAN", true, true, true));
                        ficheroSalida2.write("<h3 style = 'text-align: center;'>Mutaciones de Resistencia Codones MDR-ITINAN</h3>");
                        
                    }else{
                        
                        ficheroSalida2.write(Menu_Lateral.body("Codon Resistance Mutations DRM-NNRTI", true, true, true));
                        ficheroSalida2.write("<h3 style = 'text-align: center;'>Codon Resistance Mutations DRM-NNRTI</h3>");
                        
                    }
                    
                }else if(INI){
                    
                    if(idioma == 1){
                        
                        ficheroSalida2.write(Menu_Lateral.body("Mutaciones de Resistencia Codones MDR-INI", true, true, true));
                        ficheroSalida2.write("<h3 style = 'text-align: center;'>Mutaciones de Resistencia Codones MDR-INI</h3>");
                        
                    }else{
                        
                        ficheroSalida2.write(Menu_Lateral.body("Codon Resistance Mutations DRM-INSTI", true, true, true));
                        ficheroSalida2.write("<h3 style = 'text-align: center;'>Codon Resistance Mutations DRM-INSTI</h3>");
                        
                    }
                    
                }else if(ICA){
                    
                    if(idioma == 1){
                        
                        ficheroSalida2.write(Menu_Lateral.body("Mutaciones de Resistencia Codones MDR-ICA", true, true, true));
                        ficheroSalida2.write("<h3 style = 'text-align: center;'>Mutaciones de Resistencia Codones MDR-ICA</h3>");
                        
                    }else{
                        
                        ficheroSalida2.write(Menu_Lateral.body("Codon Resistance Mutations DRM-CAI", true, true, true));
                        ficheroSalida2.write("<h3 style = 'text-align: center;'>Codon Resistance Mutations DRM-CAI</h3>");
                        
                    }
                    
                }
                
                String directorio = archivoCarga;
                
                String archivo;
                
                final File carpeta = new File(directorio);
                
                String ficheros[];
                ficheros = carpeta.list();
                Arrays.sort(ficheros);
                
                if(codonSeleccionado == false){
                
                    while((cadena = reader.readLine()) != null) {
        	        	
                        if(proteinaSeleccionada == true) {
        		
                            if(cadena.contains("PROTEINA")){
        			
                                break;
        			
                            }
        		
                            if(!cadena.contains("DIVB")) {
        		    		        			
                                for(int r = 1; r < cadena.length(); r++){
        				
                                    if (Character.isDigit(cadena.toCharArray()[r])) {
        					
                                        MDRComun += cadena.toCharArray()[r];			
        			
                                    }else {
        					
                                        composicion += cadena.toCharArray()[0] + MDRComun + cadena.toCharArray()[r] + ",";
                                        contadorDivision++;
                                                					
                                    }
        				
                                }
                                
                                MDRComun = "";

                            }else {
        			
                                if(escribirAlcance1 == true){
                                
                                    alcanceCodonesMDR1 = contadorDivision;
                                    
                                    escribirAlcance1 = false;
                                    escribirAlcance2 = true;
                                    
                                    
                                }else if(escribirAlcance2 == true){
                                    
                                    alcanceCodonesMDR2 = contadorDivision;
                                    escribirAlcance2 = false;
                                    escribirAlcance3 = true;
                                    
                                }else if(escribirAlcance3 == true){
                                    
                                    alcanceCodonesMDR3 = contadorDivision;
                                    
                                }
                                
                                contadorDivision = 0;
        		        		
                            }
        		        		
                        }
        	
                        if(cadena.contains("PROTEINA " + tipoMDR)) {
        		
                            proteinaSeleccionada = true;
        		
                        }
        	
                    }
                    
                    reader.close();
                    
                }else{
                    
                    composicion = seleccionCodon;
                    
                }
                
                int contadorPosicion;
                
                for(int i = 0; i < ficheros.length; i++) {
                 
                    contadorPosicion = 0;
                    
                    archivo = directorio + ficheros[i];

                    //f(x) = 3x-2
                    
                    tipoMDREncabezado = "";
                    
                    switch (tipoMDR) {
                        
                        case 1:
                            
                            if(idioma == 1){
                                
                                tipoMDREncabezado = "IP";
                                
                            }else{
                                
                                tipoMDREncabezado = "PI";
                                
                            }
                            
                            break;
                            
                        case 2:
                            
                            if(idioma == 1){
                                
                                tipoMDREncabezado = "ITIAN";
                                
                            }else{
                                
                                tipoMDREncabezado = "NRTI";
                                
                            }
                            
                            break;
                            
                        case 3:
                            
                            if(idioma == 1){
                                
                                tipoMDREncabezado = "ITINAN";
                                
                            }else{
                                
                                tipoMDREncabezado = "NNRTI";
                                
                            }
                            
                            break;
                            
                        case 4:
                            
                            if(idioma == 1){
                                
                                tipoMDREncabezado = "INI";
                                
                            }else{
                                
                                tipoMDREncabezado = "INSTI";
                                
                            }
                            
                            break;
                            
                        case 12:
                            
                            if(idioma == 1){
                                
                                tipoMDREncabezado = "ICA";
                                
                            }else{
                                
                                tipoMDREncabezado = "CAI";
                                
                            }
                            
                            break;
                        
                            
                    }
                    
                    if(i == 0){
                        
                        ficheroSalida2.write("<h4 style = 'text-align: center;'>" + ficheros[i] + "</h4>\n");
                        
                    }else{
                        
                        ficheroSalida2.write("<br><br><h4 style = 'text-align: center;'>" + ficheros[i] + "</h4>\n");
                        
                    }
                    
                    ficheroSalida2.write("<table>");
                    
                    if(idioma == 1 && tipoMDR == 1 && codonSeleccionado == false){
                        
                        ficheroSalida2.write("<caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" Principales</strong></caption>\n");
                        
                    }else if(idioma != 1 && tipoMDR == 1 && codonSeleccionado == false){
                        
                        ficheroSalida2.write("<caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" Major</strong></caption>\n");
                        
                    }
                    
                    if(idioma == 1 && tipoMDR == 1){
                        
                        ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");
                        
                    }else if(idioma != 1 && tipoMDR == 1){
                        
                        ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                        
                    }

                    composicionDividida = composicion.split(",");
                            
                    for (String composicionDividida1 : composicionDividida) {
                    
                        mutacion = String.valueOf(composicionDividida1.toCharArray()[composicionDividida1.length() - 1]);
            
                        retornoCodones(mutacion);
                        codonesStringArr = codones.toArray(new String[0]);
                                                
                        posicionMDR = "";
                        
                        for(int r = 1; r < composicionDividida[contadorPosicion].toCharArray().length; r++){
        				
                            if (Character.isDigit(composicionDividida[contadorPosicion].toCharArray()[r])) {
        					
                                posicionMDR += composicionDividida[contadorPosicion].toCharArray()[r];
        					
        					
                            }
                        
                        }
                        
                        //IP ACCESORIAS
                    
                        if(tipoMDR == 1 && contadorPosicion == alcanceCodonesMDR2){
                        
                            if(seleccionCodon.equals("")){
                            
                                ficheroSalida2.write("</table><table>");
                            
                            }
                        }
                        
                        if(idioma == 1 && tipoMDR == 1 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" Accesorias</strong></caption>\n");
                        
                        }else if(idioma != 1 && tipoMDR == 1 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" Accessory</strong></caption>\n");
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 1 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");
                        
                        }else if(idioma != 1 && tipoMDR == 1 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                        
                        }
                        
                        //IP OTRAS
            
                        if(tipoMDR == 1 && contadorPosicion == alcanceCodonesMDR2 + alcanceCodonesMDR3){
                
                            if(seleccionCodon.equals("")){
                    
                                ficheroSalida2.write("</table><table>");
                
                            }
                
                        }
                
                        if(idioma == 1 && tipoMDR == 1 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2 + alcanceCodonesMDR3){
            
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" Otras</strong></caption>\n");
            
                        }else if(idioma != 1 && tipoMDR == 1 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2 + alcanceCodonesMDR3){
                
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" Others</strong></caption>\n");
                
                        }
            
                        if(idioma == 1 && tipoMDR == 1 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2 + alcanceCodonesMDR3){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");

                        }else if(idioma != 1 && tipoMDR == 1 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2 + alcanceCodonesMDR3){
                            
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                            
                        }
                        
                        //ITIAN
                        
                        if(idioma == 1 && tipoMDR == 2 && codonSeleccionado == false && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +"</strong></caption>\n");
                        
                        }else if(idioma != 1 && tipoMDR == 2 && codonSeleccionado == false && contadorPosicion == 0){
                            
                            ficheroSalida2.write("<caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +"</strong></caption>\n");
                            
                        }
                    
                        if(idioma == 1 && tipoMDR == 2 && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");
                        
                        }else if(idioma != 1 && tipoMDR == 2 && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                        
                        }
                    
                        //ITIAN Otras
                    
                        if(tipoMDR == 2 && contadorPosicion == alcanceCodonesMDR2){
                        
                            if(seleccionCodon.equals("")){
                            
                                ficheroSalida2.write("</table><table>");
                            
                            }
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 2 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" Otras</strong></caption>\n");
                        
                        }else if(idioma != 1 && tipoMDR == 2 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" Others</strong></caption>\n");
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 2 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");
                        
                        }else if(idioma != 1 && tipoMDR == 2 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                        
                        }
                        
                        //ITINAN
            
                        if(idioma == 1 && tipoMDR == 3 && codonSeleccionado == false && contadorPosicion == 0){
            
                            ficheroSalida2.write("<caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" </strong></caption>\n");
            
                        }else if(idioma != 1 && tipoMDR == 3 && codonSeleccionado == false && contadorPosicion == 0){
                            
                            ficheroSalida2.write("<caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" </strong></caption>\n");
                            
                        }
            
                        if(idioma == 1 && tipoMDR == 3 && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");

                        }else if(idioma != 1 && tipoMDR == 3 && contadorPosicion == 0){
                            
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codones</td>");
                            
                        }
                
                        //ITINAN Otras
                    
                        if(tipoMDR == 3 && contadorPosicion == alcanceCodonesMDR2){
                        
                            if(seleccionCodon.equals("")){
                            
                                ficheroSalida2.write("</table><table>");
                            
                            }
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 3 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" Otras</strong></caption>\n");
                        
                        }else if(idioma != 1 && tipoMDR == 3 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" Others</strong></caption>\n");
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 3 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");
                        
                        }else if(idioma != 1 && tipoMDR == 3 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                        
                        }
                        
                        //INI Principales
                    
                        if(idioma == 1 && tipoMDR == 4 && codonSeleccionado == false && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" Principales</strong></caption>\n");
                        
                        }else if(idioma != 1 && tipoMDR == 4 && codonSeleccionado == false && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" Major</strong></caption>\n");
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 4 && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");
                        
                        }else if(idioma != 1 && tipoMDR == 4 && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                        
                        }
                    
                        //INI Accesorias
                    
                        if(tipoMDR == 4 && contadorPosicion == alcanceCodonesMDR2){
                        
                            if(seleccionCodon.equals("")){
                            
                                ficheroSalida2.write("</table><table>");
                            
                            }
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 4 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" Accesorias</strong></caption>\n");
                        
                        }else if(idioma != 1 && tipoMDR == 4 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" Accessory</strong></caption>\n");
                        
                        }
                    
                        if(idioma == 1 &&  tipoMDR == 4 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");
                        
                        }else if(idioma != 1 && tipoMDR == 4 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                        
                        }
                    
                        //INI Otras
                    
                        if(tipoMDR == 4 && contadorPosicion == alcanceCodonesMDR2 + alcanceCodonesMDR3){
                        
                            if(seleccionCodon.equals("")){
                            
                                ficheroSalida2.write("</table><table>");
                            
                            }
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 4 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2 + alcanceCodonesMDR3){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" Otras</strong></caption>\n");
                        
                        }else if(idioma != 1 && tipoMDR == 4 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2 + alcanceCodonesMDR3){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" Others</strong></caption>\n");
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 4 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2 + alcanceCodonesMDR3){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");
                        
                        }else if(idioma != 1 && tipoMDR == 4 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2 + alcanceCodonesMDR3){
                        
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                        
                        }
                        
                        //ICA Principales
                    
                        if(idioma == 1 && tipoMDR == 12 && codonSeleccionado == false && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" Principales</strong></caption>\n");
                        
                        }else if(idioma != 1 && tipoMDR == 12 && codonSeleccionado == false && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" Major</strong></caption>\n");
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 12 && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");
                        
                        }else if(idioma != 1 && tipoMDR == 12 && contadorPosicion == 0){
                        
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                        
                        }
                   
                        //ICA Accesorias
                    
                        if(tipoMDR == 12 && contadorPosicion == alcanceCodonesMDR2){
                        
                            if(seleccionCodon.equals("")){
                            
                                ficheroSalida2.write("</table><table>");
                            
                            }
                        
                        }
                        
                        if(idioma == 1 && tipoMDR == 12 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>MDR-"+ tipoMDREncabezado +" Accesorias</strong></caption>\n");
                        
                        }else if(idioma != 1 && tipoMDR == 12 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<br><caption align=\"top\"><strong>DRM-"+ tipoMDREncabezado +" Accessory</strong></caption>\n");
                        
                        }
                    
                        if(idioma == 1 && tipoMDR == 12 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Codones Totales</td>");
                        
                        }else if(idioma != 1 && tipoMDR == 12 && codonSeleccionado == false && contadorPosicion == alcanceCodonesMDR2){
                        
                            ficheroSalida2.write("<tr><td>Position</td><td>Residues</td><td>Total Codons</td>");
                        
                        }
                        
                        
                        retorno = Calculos_Frecuencias_Posicion_Codones.cargarCalculosFrecuenciasPosicionCodones(archivo, (3*Integer.parseInt(posicionMDR))-2, "", 15, 1, codonesStringArr);
                        
                        retorno = retornoTablaHtml(retorno, composicionDividida[contadorPosicion]);
                                                    
                        ficheroSalida2.write(retorno);
  
                        contadorPosicion++;
                                        
                    }
                    
                    ficheroSalida2.write("</table>");
                   
                }
                
                ficheroSalida2.write("</table>");
                
                ficheroSalida2.write("<br><br></body>\n");
                
                ficheroSalida2.write("</html>\n");
                
                ficheroSalida2.close();
                        
            }
        
        }catch(IOException | NumberFormatException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(MDR_Codones.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }   
    }
    
    public static String retornoTablaHtml(String retorno, String posicion){
        
                                            
        String retornoTemporal[];
        String secuenciasTotales;
        String secuenciaSalida;
        
        String retornoFuncion;
        
        retorno = retorno.replace("<br>", "");
                    
        retornoTemporal = retorno.split(","); 
                    
        secuenciasTotales = retornoTemporal[retornoTemporal.length-1];
        secuenciasTotales = secuenciasTotales.replace("<strong>", "");
        secuenciasTotales = secuenciasTotales.replace("</strong>", "");
        secuenciasTotales = secuenciasTotales.replace("</br></br>", "");
                 
        secuenciaSalida = "";
                                                
        for(int l = 0; l < retornoTemporal.length-1; l++){
                            
            secuenciaSalida += retornoTemporal[l];
                                                        
            if((l+1) % 4 == 0){
                                
                secuenciaSalida += "<br>";
                                
            }
                            
        }
                                                    
        retornoFuncion = "<tr><td>" + posicion + " </td><td>" + secuenciaSalida + "</td><td>"+ secuenciasTotales + "</td></tr>";
        
        return retornoFuncion;
        
    }
    
    //Devuelve los codones posibles para un aminoácido dado.
    
    public static void retornoCodones(String mutacion) {
	
        try{
        
            codones = new ArrayList<>();
	
            mutacion = mutacion.toUpperCase();
	
            switch(mutacion){
			
                case "F": 
				
                    codones.add("TTT");
                    codones.add("TTC");
                    break;
			
                case "L":
				
                    codones.add("TTA");
                    codones.add("TTG");
                    codones.add("CTT");
                    codones.add("CTC");
                    codones.add("CTA");
                    codones.add("CTG");
                    break;
				
                case "I":
				
                    codones.add("ATT");
                    codones.add("ATC");
                    codones.add("ATA");
                    break;
				
                case "M":
			
                    codones.add("ATG");
                    break;
				
                case "V":
				
                    codones.add("GTT");
                    codones.add("GTC");
                    codones.add("GTA");
                    codones.add("GTG");
                    break;
				
                case "S":
				
                    codones.add("TCT");
                    codones.add("TCC");
                    codones.add("TCA");
                    codones.add("TCG");
                    codones.add("AGT");
                    codones.add("AGC");
                    break;
			
                case "P":
				
                    codones.add("CCT");
                    codones.add("CCC");
                    codones.add("CCA");
                    codones.add("CCG");
                    break;
				
                case "T":
				
                    codones.add("ACT");
                    codones.add("ACC");
                    codones.add("ACA");
                    codones.add("ACG");
                    break;
				
                case "A":
				
                    codones.add("GCT");
                    codones.add("GCC");
                    codones.add("GCA");
                    codones.add("GCG");
                    break;
				
                case "Y":
				
                    codones.add("TAT");
                    codones.add("TAC");
                    break;
				
                case "*":
				
                    codones.add("TAA");
                    codones.add("TAG");
                    codones.add("TGA");
                    break;
				
                case "H":
				
                    codones.add("CAT");
                    codones.add("CAC");
                    break;
				
                case "Q":
				
                    codones.add("CAA");
                    codones.add("CAG");
                    break;
			
                case "N":
				
                    codones.add("AAT");
                    codones.add("AAC");
                    break;
				
                case "K":
		
                    codones.add("AAA");
                    codones.add("AAG");
                    break;
			
                case "D":
				
                    codones.add("GAT");
                    codones.add("GAC");
                    break;
				
                case "E":
				
                    codones.add("GAA");
                    codones.add("GAG");
                    break;
				
                case "C":
				
                    codones.add("TGT");
                    codones.add("TGC");
                    break;
			
                case "W":
				
                    codones.add("TGG");
                    break;
				
                case "R":
				
                    codones.add("CGT");
                    codones.add("CGC");
                    codones.add("CGA");
                    codones.add("CGG");
                    codones.add("AGA");
                    codones.add("AGG");
                    break;
				
                case "G":
				
                    codones.add("GGT");
                    codones.add("GGC");
                    codones.add("GGA");
                    codones.add("GGG");
                    break;
				
                case "_":
				
                    codones.add("___");
			
                default:
				
                    codones.add("???");
				
            }
	
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(MDR_Codones.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
}
