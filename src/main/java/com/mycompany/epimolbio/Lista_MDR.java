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

public class Lista_MDR {
    
    public static int alcanceListaMDR1 = 0;
    public static int alcanceListaMDR2 = 0;
    public static int alcanceListaMDR3 = 0;
        
    /*Genera un archivo .html con las posiciones en las que se encuentran MDR de PR, RT, IN y p24. Las mutaciones aparecen con un asterísco.
    Se utiliza la clase Calculos_Frecuencias_Posicion para realizar los calculos.*/
    
    /*Generates an .html file with the positions of DRM in PR, RT, IN, and p24. Mutations are marked with an asterisk.
    The Calculos_Frecuencias_Posicion class is used to perform the calculations.*/
    
    public static void cargarListaMDR(String entrada, String salida, int proteina){
        
        try{
          
            alcanceListaMDR1 = 0;
            alcanceListaMDR2 = 0;
            alcanceListaMDR3 = 0;
            
            String secuenciasTotales;
            String secuenciaSalida;
            
            File carpetaEntrada = new File(entrada);
            
            try (FileWriter salidaArchivo = new FileWriter(salida)) {
                
                salidaArchivo.write("<!DOCTYPE html>\n");
                salidaArchivo.write("<html lang = 'es'>\n");
                salidaArchivo.write("<head>\n");
                salidaArchivo.write("<meta charset = 'UTF-8'/>\n");
                salidaArchivo.write(Menu_Lateral.head());
                salidaArchivo.write(Estilo_Tablas_html.cssTabla());
                salidaArchivo.write("</head>\n");
                salidaArchivo.write("<body>\n");
                
                String ficheros[];
                String retornoTemporal[];
                String retorno;
                
                ficheros = carpetaEntrada.list();
                Arrays.sort(ficheros);
                
                String composicion = "";
                
                String archivoEntrada = "com/mycompany/epimolbio/MDR/mdr.txt";

                ClassLoader classLoader = Lista_MDR.class.getClassLoader();

                InputStream inputStream = classLoader.getResourceAsStream(archivoEntrada);

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
                                
                                alcanceListaMDR1 = contadorDivision;
                                    
                                escribirAlcance1 = false;
                                escribirAlcance2 = true;
                                    
                                    
                            }else if(escribirAlcance2 == true){
                                    
                                alcanceListaMDR2 = contadorDivision;
                                escribirAlcance2 = false;
                                escribirAlcance3 = true;
                                    
                            }else if(escribirAlcance3 == true){
                                    
                                alcanceListaMDR3 = contadorDivision;
                                    
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
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Adquiridas MDR-IP VIH-1", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Adquiridas MDR-IP VIH-1</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Acquired Resistance Mutations DRM-PI HIV-1", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Acquired Resistance Mutations DRM-PI HIV-1</h3>");
                            
                        }
                        
                        break;
                        
                    case 2:
                        
                        //MDR ITIAN VIH-1
                        //DRM NRTI HIV-1
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-1", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-1</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Acquired Resistance Mutations DRM-NRTI HIV-1", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Acquired Resistance Mutations DRM-NRTI HIV-1</h3>");
                            
                        }
                        
                        break;
                        
                    case 3:
                        
                        //MDR ITINAN VIH-1
                        //DRM NNRTI HIV-1
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Adquiridas MDR-ITINAN VIH-1", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Adquiridas MDR-ITINAN VIH-1</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Acquired Resistance Mutations DRM-NNRTI HIV-1", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Acquired Resistance Mutations DRM-NNRTI HIV-1</h3>");
                            
                        }
                        
                        break;
                        
                    case 4:
                        
                        //MDR INI VIH-1
                        //DRM INSTI HIV-1
                                             
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Adquiridas MDR-INI VIH-1", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Adquiridas MDR-INI VIH-1</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Acquired Resistance Mutations DRM-INSTI HIV-1", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Acquired Resistance Mutations DRM-INSTI HIV-1</h3>");
                            
                        }
                        
                        break;
                        
                    case 5:
                        
                        //MDR IP VIH-2
                        //DRM PI HIV-2     
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Adquiridas MDR-IP VIH-2", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Adquiridas MDR-IP VIH-2</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Acquired Resistance Mutations DRM-PI HIV-2", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Acquired Resistance Mutations DRM-PI HIV-2</h3>");
                            
                        }
                        
                        break;
                        
                    case 6:
                        
                        //MDR ITIAN VIH-2
                        //DRM NRTI HIV-2
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-2", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Adquiridas MDR-ITIAN VIH-2</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Acquired Resistance Mutations DRM-NRTI HIV-2", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Acquired Resistance Mutations DRM-NRTI HIV-2</h3>");
                            
                        }
                        
                        break;
                        
                    case 7:
                        
                        //MDR INI VIH-2
                        //DRM INSTI HIV-2
                                                
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Adquiridas MDR-INI VIH-2", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Adquiridas MDR-INI VIH-2</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Acquired Resistance Mutations DRM-INSTI HIV-2", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Acquired Resistance Mutations DRM-INSTI HIV-2</h3>");
                            
                        }
                        
                        break;
                        
                    case 8:
                        
                        //SDRM IP VIH-1
                        //SDRM PI HIV-1                    
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Transmitidas SDRM-IP", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Transmitidas SDRM-IP</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Transmitted Resistance Mutations SDRM-PI", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Transmitted Resistance Mutations SDRM-PI</h3>");
                            
                        }
                        
                        break;
                        
                    case 9:
                        
                        //SDRM ITIAN VIH-1
                        //SDRM NRTI HIV-1
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Transmitidas SDRM-ITIAN", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Transmitidas SDRM-ITIAN</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Transmitted Resistance Mutations SDRM-NRTI", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Transmitted Resistance Mutations SDRM-NRTI</h3>");
                            
                        }
                                                
                        break;
                        
                    case 10:
                        
                        //SDRM ITINAN VIH-1
                        //SDRM NNRTI HIV-1
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Transmitidas SDRM-ITINAN", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Transmitidas SDRM-ITINAN</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Transmitted Resistance Mutations SDRM-NNRTI", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Transmitted Resistance Mutations SDRM-NNRTI</h3>");
                            
                        }
                                                
                        break;
                     
                    case 11:
                        
                        //SDRM INI VIH-1
                        //SDRM INSTI HIV-1

                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Transmitidas SDRM-INI", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Transmitidas SDRM-INI</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Transmitted Resistance Mutations SDRM-INSTI", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Transmitted Resistance Mutations SDRM-INSTI</h3>");
                            
                        }
                                                
                        break;
                        
                        
                    case 12:
                        
                        //MDR ICA VIH-1
                        //DRM CAI HIV-1
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write(Menu_Lateral.body("Lista Mutaciones de Resistencia Adquiridas MDR-ICA VIH-1", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>Lista Mutaciones de Resistencia Adquiridas MDR-ICA VIH-1</h3>");
                            
                        }else{
                            
                            salidaArchivo.write(Menu_Lateral.body("List Acquired Resistance Mutations DRM-CAI HIV-1", true, true, true));
                            salidaArchivo.write("<h3 style = 'text-align: center;'>List Acquired Resistance Mutations DRM-CAI HIV-1</h3>");
                            
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
                
                for(int a = 0; a < ficheros.length; a++){
                    
                    if(a == 0){
                        
                        salidaArchivo.write("<h4 style = 'text-align: center;'>" + ficheros[a] + "</h4>\n");
                        
                    }else{
                        
                        salidaArchivo.write("</table><br><br><h4 style = 'text-align: center;'>" + ficheros[a] + "</h4>\n");
                        
                    }
                    
                    salidaArchivo.write("<table>");
                    
                    if(proteina >= 8 && proteina != 12){
                        
                        if(idioma == 1){
                            
                            salidaArchivo.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Posiciones Totales</td>");

                        }else{
                            
                            salidaArchivo.write("<tr><td>Position</td><td>Residues</td><td>Total Positions</td>");
                            
                        }
                        
                    }
                    
                    for(int i = 0; i < division1.length; i++){
                        
                        if(proteina != 0){
                            
                            rotulo = rotulos(proteina, i);
                            
                        }
                        
                        if(!rotulo.equals("")){
                            
                            salidaArchivo.write(rotulo);
                            
                            if(idioma == 1){
                                
                                salidaArchivo.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Posiciones Totales</td>");
                                
                            }else{
                                
                                salidaArchivo.write("<tr><td>Position</td><td>Residues</td><td>Total Positions</td>");
                                
                            }
                            
                        }
                        
                        salidaArchivo.write("<tr>");
                        
                        retorno = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(entrada + "/" + ficheros[a], Integer.parseInt(posicion[i]), wt[i].toCharArray()[0], MDR[i], 1, 1, 0, "", 0, 0, 0, 0, 0, 'ł');
                        
                        retornoTemporal = retorno.split("\\)");
                        
                        secuenciasTotales = retornoTemporal[retornoTemporal.length-1];
                        
                        secuenciaSalida = "";
                        
                        retorno = retorno.replace(" ", "");
                        retorno = retorno.replace("</a></strong></a>)", "</a></strong></a>) ");
                        retornoTemporal = retorno.split(" ");
                        
                        for(int l = 0; l < retornoTemporal.length-1; l++){
                            
                            retornoTemporal[l] = retornoTemporal[l].replace("aStyle", "a Style");
                            secuenciaSalida += retornoTemporal[l] + " ";
                            
                            if((l+1) % 4 == 0){
                                
                                secuenciaSalida += "<br>";
                                
                            }
                            
                        }
                        
                        secuenciaSalida = secuenciaSalida.replace("%<a Style='color:black';>*</a></strong></a>)", "%<a Style='color:#BF3D27';>*</a></strong></a>)");
                        
                        salidaArchivo.write("<td>" + wt[i] + posicion[i] + "</td><td>" +  secuenciaSalida + "</td><td>" + secuenciasTotales + "</td></tr>\n");
                        
                        
                        salidaArchivo.write("</tr>");
                        
                    }
                    
                    salidaArchivo.write("</html>");
                    
                    
                }
                
                salidaArchivo.write("</body>\n");
                salidaArchivo.write("</html>\n");
            }
            
        }catch(IOException | NumberFormatException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Lista_MDR.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
    
    //Selecciona los títulos que correspondan según la posición y la proteína.
    //Select the titles that correspond to the position and protein.
    
    public static String rotulos(int proteina, int i){
        
        try{
        
            String composicion = "";
        
            if(proteina == 1 && i == alcanceListaMDR1){
                        
                if(idioma == 1){
                
                    composicion = "<caption align=\"top\"><strong>MDR-IP PRINCIPALES</strong></caption>\n";
                        
                }else{
                    
                    composicion = "<caption align=\"top\"><strong>MAJOR DRM-PI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 1 && i == alcanceListaMDR2){
                        
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-IP ACCESORIAS</strong></caption>\n";
                        
                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>ACCESSORY DRM-PI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 1 && i == alcanceListaMDR3){
                        
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-IP OTRAS</strong></caption>\n";

                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>DRM-PI OTHERS</strong></caption>\n";
                    
                }
                
            }else if(proteina == 2 && i == alcanceListaMDR1){
                
                if(idioma == 1){
                
                    composicion = "<caption align=\"top\"><strong>MDR-ITIAN</strong></caption>\n";
            
                }else{
                    
                    composicion = "<caption align=\"top\"><strong>DRM-NRTI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 2 && i == alcanceListaMDR2){
            
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-ITIAN OTRAS</strong></caption>\n";
            
                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>DRM-NRTI OTHERS</strong></caption>\n";
                    
                }
                
            }else if(proteina == 3 && i == alcanceListaMDR1){
            
                if(idioma == 1){
                
                    composicion = "<caption align=\"top\"><strong>MDR-ITINAN</strong></caption>\n";
                
                }else{
                    
                    composicion = "<caption align=\"top\"><strong>DRM-NNRTI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 3 && i == alcanceListaMDR2){
            
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-ITINAN OTRAS</strong></caption>\n";
            
                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>DRM-NNRTI OTHERS</strong></caption>\n";
                    
                }
                
            }else if(proteina == 4 && i == alcanceListaMDR1){
            
                if(idioma == 1){
                
                    composicion = "<caption align=\"top\"><strong>MDR-INI PRINCIPALES</strong></caption>\n";
            
                }else{
                    
                    composicion = "<caption align=\"top\"><strong>MAJOR DRM-INSTI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 4 && i == alcanceListaMDR2){
                
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-INI ACCESORIAS</strong></caption>\n";
            
                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>ACCESSORY DRM-INSTI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 4 && i == alcanceListaMDR3){
            
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-INI OTRAS</strong></caption>\n";
            
                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>DRM-INSTI OTHERS</strong></caption>\n";
                    
                }
                
            }else if(proteina == 5 && i == alcanceListaMDR1){
            
                if(idioma == 1){
                
                    composicion = "<caption align=\"top\"><strong>MDR-IP PRINCIPALES</strong></caption>\n";
                        
                }else{
                    
                    composicion = "<caption align=\"top\"><strong>MAJOR DRM-PI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 5 && i == alcanceListaMDR2){
            
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-IP ACCESORIAS</strong></caption>\n";
                        
                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>ACCESSORY DRM-PI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 5 && i == alcanceListaMDR3){
            
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-IP OTRAS</strong></caption>\n";

                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>DRM-PI OTHERS</strong></caption>\n";
                    
                }
                
            }else if(proteina == 6 && i == alcanceListaMDR1){
                
                if(idioma == 1){
                
                    composicion = "<caption align=\"top\"><strong>MDR-ITIAN PRINCIPALES</strong></caption>\n";
                        
                }else{
                    
                    composicion = "<caption align=\"top\"><strong>MAJOR DRM-NRTI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 6 && i == alcanceListaMDR2){
                
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-ITIAN ACCESORIAS</strong></caption>\n";
                        
                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>ACCESSORY DRM-NRTI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 6 && i == alcanceListaMDR3){
                
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-ITIAN OTRAS</strong></caption>\n";

                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>DRM-NRTI OTHERS</strong></caption>\n";
                    
                }
                
            }else if(proteina == 7 && i == alcanceListaMDR1){
            
                 if(idioma == 1){
                
                    composicion = "<caption align=\"top\"><strong>MDR-INI PRINCIPALES</strong></caption>\n";
                        
                }else{
                    
                    composicion = "<caption align=\"top\"><strong>MAJOR DRM-INSTI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 7 && i == alcanceListaMDR2){
            
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-INI ACCESORIAS</strong></caption>\n";
                        
                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>ACCESSORY DRM-INSTI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 7 && i == alcanceListaMDR3){
            
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-INI OTRAS</strong></caption>\n";

                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>DRM-INSTI OTHERS</strong></caption>\n";
                    
                }
                
            }else if(proteina == 12 && i == alcanceListaMDR1){
                        
                if(idioma == 1){
                
                    composicion = "<caption align=\"top\"><strong>MDR-ICA PRINCIPALES</strong></caption>\n";
                        
                }else{
                    
                    composicion = "<caption align=\"top\"><strong>MAJOR DRM-CAI</strong></caption>\n";
                    
                }
                
            }else if(proteina == 12 && i == alcanceListaMDR2){
                        
                if(idioma == 1){
                
                    composicion = "</table></br><table><caption align=\"top\"><strong>MDR-ICA ACCESORIAS</strong></caption>\n";
                        
                }else{
                    
                    composicion = "</table></br><table><caption align=\"top\"><strong>ACCESSORY DRM-CAI</strong></caption>\n";
                    
                }
                
            }
                    
            return composicion;
        
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
 
            Logger.getLogger(Lista_MDR.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;

            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return "";
            
        }
    } 
}
