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

public class Posiciones_Mutadas_Tabla_Mutaciones {
    
    public static String retornador = "";
   
    public static void cargarPosicionesMutadasTablaMutaciones(String archivoCarga, String archivoGuardado, String wt, double valorMinimo, boolean orden, int seleccion, String mutacionSeleccionada, boolean modo, int tipoTabla) {
        
        try{
                                
            String rotuloTodasMut;
            
            if(tipoTabla == 1){
                
                if(idioma == 1){
                
                    rotuloTodasMut = " Todas las Posiciones";
                
                }else{
                    
                    rotuloTodasMut = " All Positions";
                    
                }
                
            }else{
                
                if(idioma == 1){
                
                    rotuloTodasMut = " Posiciones Mutadas";
                
                }else{
                    
                    rotuloTodasMut = " Mutated Positions";
                    
                }
                
            }
            
            mutacionSeleccionada = mutacionSeleccionada.replace(" ", "");
            
            if(!mutacionSeleccionada.equals("") && seleccion == 1){
            
                String mutacionSeleccionadaSustituida = "";
            
                for(int i = 0; i < mutacionSeleccionada.length(); i++){
                
                    if(!Character.isDigit(mutacionSeleccionada.toCharArray()[i]) && mutacionSeleccionada.toCharArray()[i] != ','){
                    
                        mutacionSeleccionadaSustituida += "0";
                        
                    }else{
                        
                        mutacionSeleccionadaSustituida += mutacionSeleccionada.toCharArray()[i];
                        
                    }
                
                }
            
                String valoresSeleccionadaString[] = mutacionSeleccionadaSustituida.split(",");
                int valoresSeleccionadaNumericos[] = new int[valoresSeleccionadaString.length];
            
                for(int i = 0; i < valoresSeleccionadaString.length; i++){
                
                    valoresSeleccionadaNumericos[i] = Integer.parseInt(valoresSeleccionadaString[i]);
                
                }
                
                boolean comprobarRepetidos = false;
                
                String seleccionadaSplit2[] = mutacionSeleccionada.split(",");
                
                mutacionSeleccionada = "";
                
                for(int i = 0; i < valoresSeleccionadaNumericos.length; i++){
                    
                    for(int x = 0; x < i; x++){
                    
                        if(valoresSeleccionadaNumericos[i] == valoresSeleccionadaNumericos[x] ){
                            
                            comprobarRepetidos = true;
                            break;
                        }
                        
                    }
                    
                    if(comprobarRepetidos == false){
                        
                        mutacionSeleccionada += seleccionadaSplit2[i] + ",";
                        
                    }
                    
                    comprobarRepetidos = false;
                     
                }
                
            }
                        
            boolean comaBool = true;
            int numeroSecuenciasTemporal = -1;
            int contadorProtTemporal;
            int contadorMutaciones = 0;
            boolean detectorTr;
            boolean trSeleccionada = false;
            
            int secuenciasTotalesSplit;
            
            String retornoSplit[];
            
            String mutacionSeleccionadaSplit[] = null;
            
            int contadorMutacionSeleccionada = 0;
            
            if(!mutacionSeleccionada.equals("")){
                
                mutacionSeleccionadaSplit = mutacionSeleccionada.split("\\,");
                trSeleccionada = true;
                
            }
            
            char mut = 'ł';
            String numericoMutacionSeleccionada;
            
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
            
            DecimalFormat df = new DecimalFormat("0.000", symbols);
            df.setDecimalSeparatorAlwaysShown(true);
            
            DecimalFormat df2 = new DecimalFormat("0");
            
            String ficherosSalida;
            String directorio = archivoCarga;
	
            final File carpeta = new File(directorio);
	
            String ficheros[] = new String[5000];
            String ficherosTemporal[] = new String[5000];
            String ficheroSecundario[] = new String[5000];
            
            if(orden == true){
                
                for(int i = 0; i < ficheros.length; i++) {
		
                    ficheros[i] = "";
                    ficherosTemporal[i] = ""; 
                    
                }
                
            }else{
                
                for(int i = 0; i < ficheros.length; i++) {
		
                    ficheros[i] = "";
                    ficheroSecundario[i] = "";
                    
                }
                
            }
            
            int a = 0;
	
            for (File ficheroEntrada : carpeta.listFiles()) {
	        
                ficheros[a] = (ficheroEntrada.getName());
                a++;
			
            }
            
            if(orden == false){
            
                Arrays.sort(ficheros);
                
                int contadorSecundario = 0;
            
                for (String fichero : ficheros) {
                    
                    if (!fichero.equals("")) {
                        
                        ficheroSecundario[contadorSecundario] = fichero;
                        contadorSecundario++;
                        
                    }
                    
                }
            
                ficheros = ficheroSecundario;
                
            }
            
            if(orden == true){
            
                String posicionTemp = "";
            
                for (String fichero : ficheros) {
                    
                    if (fichero.equals("")) {
                        
                        break;
                        
                    }
                    
                    for (int x = 0; x < fichero.toCharArray().length; x++) {
                        
                        if (fichero.toCharArray()[x] == '.') {
                            
                            break;
                            
                        }
                        
                        posicionTemp += fichero.toCharArray()[x];
                        
                    }
                    
                    ficherosTemporal[Integer.parseInt(posicionTemp)-1] = fichero;
                    posicionTemp = "";
                    
                }
                
                for(int i = 0; i < ficherosTemporal.length; i++){
                
                    if(ficherosTemporal[i].equals("")){
                    
                        break;
                    
                    }
                
                    ficheros[i] = ficherosTemporal[i];
                
                }
            
            }
            
            String archivo;
            String informacionTemporal;
            String numericoTemporal;
            String retorno;
            int numeroSecuencias;
            String color = "";
        
            ficherosSalida = archivoGuardado;
        
            try (FileWriter ficheroSalida1 = new FileWriter(ficherosSalida)) {
                
                if(seleccion == 1){
                    
                    ficheroSalida1.write("<!DOCTYPE html>");
                    ficheroSalida1.write("<html lang = 'es'>");
                    ficheroSalida1.write("<head>");
                    ficheroSalida1.write("<meta charset = 'UTF-8'/>");
                    ficheroSalida1.write(Menu_Lateral.head());
                    ficheroSalida1.write(Estilo_Tablas_html.cssTabla());
                    ficheroSalida1.write("</head>\n");
                    ficheroSalida1.write("<body>\n");
                    
                    if(idioma == 1){
                        
                        ficheroSalida1.write(Menu_Lateral.body("Variabilidad Polimorfismos Individual" + rotuloTodasMut, true, true, true));
                        ficheroSalida1.write("<h3 style = 'text-align: center;'>Variabilidad Polimorfismos Individual" + rotuloTodasMut + "</h3>");
                        
                    }else{
                        
                        ficheroSalida1.write(Menu_Lateral.body("Variability Polymorphisms Individual" + rotuloTodasMut, true, true, true));
                        ficheroSalida1.write("<h3 style = 'text-align: center;'>Variability Polymorphisms Individual" + rotuloTodasMut + "</h3>");
                        
                    }
                    
                }else if(seleccion == 2 && modo == false){
                    
                    if(idioma == 1){
                        
                        ficheroSalida1.write("Archivo;Número Secuencias;Referencia;Posición;Cambio;Porcentaje;Secuencias Mutadas" + "\r\n");
                        
                    }else{
                        
                        ficheroSalida1.write("File;Number of Sequences;Reference;Position;Change;Percentage;Mutated Sequences" + "\r\n");
                        
                    }
                    
                }
                
                ficheros = Comprobador_Archivos_Vacios.cargarComprobarArchivosVacios(archivoCarga, ficheros);
                
                for (String fichero : ficheros) {
                    
                    if (fichero.equals("")) {
                        
                        break;
                        
                    }
                    
                    contadorProtTemporal = -1;
                    
                    if (seleccion == 1) {
                        
                        ficheroSalida1.write("<h4 style = 'text-align: center;'>" + fichero + "</h4>\n");
                        ficheroSalida1.write("<table>");
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Posiciones Totales</td>");
                            
                        }else{
                            
                            ficheroSalida1.write("<tr><td>Position</td><td>Residues</td><td>Total Positions</td>");
                            
                        }
                        
                    }
                    
                    archivo = archivoCarga + fichero;
                    informacionTemporal = "";
                    numericoTemporal = "";
                    
                    for (int contador_Prot = 0; contador_Prot < wt.length(); contador_Prot++) {
                        
                        if(!mutacionSeleccionada.equals("")){
                            
                            mut = mutacionSeleccionadaSplit[contadorMutacionSeleccionada].toCharArray()[mutacionSeleccionadaSplit[contadorMutacionSeleccionada].length()-1];
                            
                            numericoMutacionSeleccionada = "";
                            
                            for(int i = 0; i < mutacionSeleccionadaSplit[contadorMutacionSeleccionada].length(); i++){
                                
                                if(Character.isDigit(mutacionSeleccionadaSplit[contadorMutacionSeleccionada].toCharArray()[i])){
                                    
                                    numericoMutacionSeleccionada += mutacionSeleccionadaSplit[contadorMutacionSeleccionada].toCharArray()[i];
                                    
                                }
                            }
                            
                            contador_Prot = Integer.parseInt(numericoMutacionSeleccionada) -1;
                            
                        }
                        
                        retorno = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, contador_Prot+1, ' ', "", 40, 1, 0, "", 0, 0, 0, 0, 0, 'ł');
                        retornoSplit = retorno.split("\\|");
                        secuenciasTotalesSplit = Integer.parseInt(retornoSplit[retornoSplit.length-1]);
                        retorno = "";
                        
                        for(int contador_split = 0; contador_split < retornoSplit.length-1; contador_split++){
                            
                            retorno += retornoSplit[contador_split];
                            
                            if(contador_split != retornoSplit.length-2){
                                
                                retorno += "|";
                                
                            }
                            
                        }
                        
                        detectorTr = false;
                        retorno += " </br>";
                        
                        if(seleccion == 1){
                            
                            comaBool = true;
                            
                        }
                        for (int i = 0; i < retorno.length() - 6; i++) {
                            
                            if (retorno.toCharArray()[i+2] == '|' && i > 1) {
                                
                                for(int x = 3; x < informacionTemporal.length(); x++){
                                    
                                    numericoTemporal += informacionTemporal.toCharArray()[x];
                                    
                                }
                                
                                if(numericoTemporal.equals("")){
                                    
                                    numericoTemporal = "-1.0";
                                    
                                }
                                
                                if (Double.parseDouble(numericoTemporal) >= valorMinimo) {
                                    
                                    if (tipoTabla == 1 || wt.toCharArray()[contador_Prot] != informacionTemporal.toCharArray()[1]) {
                                        
                                        numeroSecuencias = secuenciasTotalesSplit;
                                        
                                        if (seleccion == 1) {
                                            
                                            numeroSecuenciasTemporal = numeroSecuencias;
                                            
                                            if(Double.parseDouble(numericoTemporal) >= 90.0){
                                                
                                                color = "#BF3D27";
                                                
                                            }else if(Double.parseDouble(numericoTemporal) < 10.0){
                                                
                                                color = "#3E8249";
                                                
                                            }else if(Double.parseDouble(numericoTemporal) >= 10.0 && Double.parseDouble(numericoTemporal) <= 50.0){
                                                
                                                color = "#2E75B6";
                                                
                                            }else if(Double.parseDouble(numericoTemporal) < 90.0 && Double.parseDouble(numericoTemporal) > 75.0){
                                                
                                                color = "#F3A031";
                                                
                                            }else if(Double.parseDouble(numericoTemporal) <= 75 && Double.parseDouble(numericoTemporal) > 50.0){
                                                
                                                color = "#E0D900";
                                                
                                            }
                                            
                                            if(contador_Prot != contadorProtTemporal){
                                                
                                                detectorTr = true;
                                                
                                                ficheroSalida1.write("<tr>");
                                                
                                                ficheroSalida1.write("<td>" + wt.toCharArray()[contador_Prot] + "" + (contador_Prot +1) + "</td><td>");
                                                contadorProtTemporal = contador_Prot;
                                                contadorMutaciones = 0;
                                                comaBool = false;
                                                
                                            }
                                            
                                            if(contadorMutaciones != 0){
                                                
                                                ficheroSalida1.write(" ");
                                                
                                            }
                                            
                                            if(contadorMutaciones % 4 == 0 && contadorMutaciones != 0){
                                                
                                                ficheroSalida1.write("<br>");
                                                
                                            }
                                            
                                            ficheroSalida1.write(informacionTemporal.toCharArray()[1] + "(<a style = 'color: " + color + ";'><strong>" +df.format(Double.parseDouble(numericoTemporal)) + "%</strong></a>)");
                                            
                                            contadorMutaciones ++;
                                            
                                        } else if (seleccion == 2) {
                                            
                                            if (!mutacionSeleccionada.equals("") && mut == informacionTemporal.toCharArray()[1]) {
                                                
                                                if (modo == false) {
                                                    
                                                    ficheroSalida1.write(fichero + ";" + numeroSecuencias + ";" + wt.toCharArray()[contador_Prot] + ";" + (contador_Prot +1) + ";" + informacionTemporal.toCharArray()[1] + ";" + df.format(Double.parseDouble(numericoTemporal)) + "%" + ";" + df2.format((Double.parseDouble(numericoTemporal)/100.0) * numeroSecuencias) + "\r\n");
                                                    
                                                } else if (modo == true) {
                                                    
                                                    retornador += fichero + ";" + numeroSecuencias + ";" + wt.toCharArray()[contador_Prot] + ";" + (contador_Prot +1) + ";" + informacionTemporal.toCharArray()[1] + ";" + df.format(Double.parseDouble(numericoTemporal)) + "%" + ";" + df2.format((Double.parseDouble(numericoTemporal)/100.0) * numeroSecuencias) + "\r\n";
                                                    
                                                }
                                                
                                            } else if (mutacionSeleccionada.equals("") && mut == 'ł') {
                                                
                                                if (modo == false) {
                                                    
                                                    ficheroSalida1.write(fichero + ";" + numeroSecuencias + ";" + wt.toCharArray()[contador_Prot] + ";" + (contador_Prot +1) + ";" + informacionTemporal.toCharArray()[1] + ";" + df.format(Double.parseDouble(numericoTemporal)) + "%" + ";" + df2.format((Double.parseDouble(numericoTemporal)/100.0) * numeroSecuencias) + "\r\n");
                                                    
                                                } else if (modo == true) {
                                                    
                                                    retornador += fichero + ";" + numeroSecuencias + ";" + wt.toCharArray()[contador_Prot] + ";" + (contador_Prot +1) + ";" + informacionTemporal.toCharArray()[1] + ";" + df.format(Double.parseDouble(numericoTemporal)) + "%" + ";" + df2.format((Double.parseDouble(numericoTemporal)/100.0) * numeroSecuencias) + "\r\n";
                                                    
                                                }
                                            }
                                        }
                                    }
                                }
                                
                                numericoTemporal = "";
                                informacionTemporal = "";
                                
                            }
                            
                            informacionTemporal += retorno.toCharArray()[i];
                            
                        }
                        
                        for(int x = 3; x < informacionTemporal.length(); x++){
                            
                            numericoTemporal += informacionTemporal.toCharArray()[x];
                            
                        }
                        
                        if(numericoTemporal.equals("")){
                            
                            numericoTemporal = "-1.0";
                            
                        }
                        
                        if (Double.parseDouble(numericoTemporal) >= valorMinimo) {
                            
                            if (tipoTabla == 1 || wt.toCharArray()[contador_Prot] != informacionTemporal.toCharArray()[1]) {
                                
                                numeroSecuencias = secuenciasTotalesSplit;
                                
                                if (seleccion == 1) {
                                    
                                    if(Double.parseDouble(numericoTemporal) == 100.0){
                                        
                                        color = "#834580";
                                        
                                    }else if(Double.parseDouble(numericoTemporal) >= 90.0){
                                        
                                        color = "#BF3D27";
                                        
                                    }else if(Double.parseDouble(numericoTemporal) < 10.0){
                                        
                                        color = "#3E8249";
                                        
                                    }else if(Double.parseDouble(numericoTemporal) >= 10.0 && Double.parseDouble(numericoTemporal) <= 50.0){
                                        
                                        color = "#2E75B6";
                                        
                                    }else if(Double.parseDouble(numericoTemporal) < 90.0 && Double.parseDouble(numericoTemporal) > 75.0){
                                        
                                        color = "#F3A031";
                                        
                                    }else if(Double.parseDouble(numericoTemporal) <= 75 && Double.parseDouble(numericoTemporal) > 50.0){
                                        
                                        color = "#E0D900";
                                        
                                    }
                                    
                                    if(detectorTr == false){
                                        
                                        ficheroSalida1.write("<tr>");
                                        ficheroSalida1.write("<td>" + wt.toCharArray()[contador_Prot] + "" + (contador_Prot +1) + "</td><td>");
                                        
                                    }
                                    
                                    if(comaBool == false){
                                        
                                        ficheroSalida1.write(" ");
                                        
                                    }
                                    
                                    ficheroSalida1.write(informacionTemporal.toCharArray()[1] + "(<a style = 'color: " + color + ";'><strong>" +df.format(Double.parseDouble(numericoTemporal)) + "%</strong></a>)</td>");
                                    
                                    if(detectorTr == false){
                                        
                                        ficheroSalida1.write("<td>"  + numeroSecuencias + "</td></tr>");
                                        
                                    }
                                    
                                } else if (seleccion == 2) {
                                    
                                    if (!mutacionSeleccionada.equals("") && mut == informacionTemporal.toCharArray()[1]) {
                                        
                                        if (modo == false) {
                                            
                                            ficheroSalida1.write(fichero + ";" + numeroSecuencias + ";" + wt.toCharArray()[contador_Prot] + ";" + (contador_Prot +1) + ";" + informacionTemporal.toCharArray()[1] + ";" + df.format(Double.parseDouble(numericoTemporal)) + "%" + ";" + df2.format((Double.parseDouble(numericoTemporal)/100.0) * numeroSecuencias) + "\r\n");
                                            
                                        } else if (modo == true) {
                                            
                                            retornador += fichero + ";" + numeroSecuencias + ";" + wt.toCharArray()[contador_Prot] + ";" + (contador_Prot +1) + ";" + informacionTemporal.toCharArray()[1] + ";" + df.format(Double.parseDouble(numericoTemporal)) + "%" + ";" + df2.format((Double.parseDouble(numericoTemporal)/100.0) * numeroSecuencias) + "\r\n";
                                            
                                        }
                                        
                                    } else if (mutacionSeleccionada.equals("") && mut == 'ł') {
                                        
                                        if (modo == false) {
                                            
                                            ficheroSalida1.write(fichero + ";" + numeroSecuencias + ";" + wt.toCharArray()[contador_Prot] + ";" + (contador_Prot +1) + ";" + informacionTemporal.toCharArray()[1] + ";" + df.format(Double.parseDouble(numericoTemporal)) + "%" + ";" + df2.format((Double.parseDouble(numericoTemporal)/100.0) * numeroSecuencias) + "\r\n");
                                            
                                        } else if (modo == true) {
                                            
                                            retornador += fichero + ";" + numeroSecuencias + ";" + wt.toCharArray()[contador_Prot] + ";" + (contador_Prot +1) + ";" + informacionTemporal.toCharArray()[1] + ";" + df.format(Double.parseDouble(numericoTemporal)) + "%" + ";" + df2.format((Double.parseDouble(numericoTemporal)/100.0) * numeroSecuencias) + "\r\n";
                                            
                                        }
                                    }
                                }
                            }    
                        }
                        
                        numericoTemporal = "";
                        informacionTemporal = "";
                        
                        if(!mutacionSeleccionada.equals("")){
                            
                            if(numeroSecuenciasTemporal != -1 && seleccion == 1){
                                
                                ficheroSalida1.write("<td>"  + numeroSecuenciasTemporal + "</td></tr>");
                                numeroSecuenciasTemporal = -1;
                                
                            }
                            
                            if(contadorMutacionSeleccionada == mutacionSeleccionadaSplit.length -1){
                                
                                contadorMutacionSeleccionada = 0;
                                
                                break;
                                
                            }
                            
                            contadorMutacionSeleccionada++;
                            
                        }
                        
                        if(numeroSecuenciasTemporal != -1 && seleccion == 1 && trSeleccionada == false){
                            
                            ficheroSalida1.write("<td>"  + numeroSecuenciasTemporal + "</td></tr>");
                            numeroSecuenciasTemporal = -1;
                            
                        }
                        
                    }
                    
                    if(seleccion == 1){
                        
                        ficheroSalida1.write("</table><br><br>");
                        
                    }
                }
                
                if(seleccion == 1){
                    
                    ficheroSalida1.write("</body>");
                    ficheroSalida1.write("</html>");
                    
                }
            }
        
        }catch(IOException | NumberFormatException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                        
            Logger.getLogger(Posiciones_Mutadas_Tabla_Mutaciones.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();

        }
    }
    
    public static void lectorSubcarpetas(String archivoCarga, String archivoGuardado, String wt, double valorMinimo, boolean orden, int seleccion, String mutacionSeleccionada, int tipoTabla){
        
        try{
            
            retornador = "";
            
            String ficheroSalidaGeneral;
            
            FileWriter ficheroSalidaGeneral1;
            
            String directorio = archivoCarga;
            
            final File carpeta = new File(directorio);
            
            String ficheros[] = new String[5000];
            String ficheroSecundario[] = new String[5000];
            
            for(int i = 0; i < ficheros.length; i++) {
		
                ficheros[i] = ""; 
                ficheroSecundario[i] = "";
                    
            }
            
            int a = 0;
	
            for (File ficheroEntrada : carpeta.listFiles()) {
	        
                ficheros[a] = (ficheroEntrada.getName());
                a++;
			
            }
            
            Arrays.sort(ficheros);
                
            int contadorSecundario = 0;
            
            for (String fichero : ficheros) {
                
                if (!fichero.equals("")) {
                    
                    ficheroSecundario[contadorSecundario] = fichero;
                    contadorSecundario++;
                    
                }
            }
            
            ficheros = ficheroSecundario;
            
            ficheroSalidaGeneral = archivoGuardado;
            
            ficheroSalidaGeneral1 =  new FileWriter(ficheroSalidaGeneral);
              
            if(idioma == 1){
            
                ficheroSalidaGeneral1.write("Archivo;Número Secuencias;Referencia;Posición;Cambio;Porcentaje;Secuencias Mutadas" + "\r\n");
                
            }else{
                
                ficheroSalidaGeneral1.write("File;Number of Sequences;Reference;Position;Change;Percentage;Mutated Sequences" + "\r\n");
                
            }
            
            for (String fichero : ficheros) {
                
                if (fichero.equals("")) {
                    
                    break;
                    
                }
                
                cargarPosicionesMutadasTablaMutaciones(archivoCarga + fichero + "/", archivoGuardado, wt, valorMinimo, orden, seleccion, mutacionSeleccionada, true, tipoTabla);
            
            }
            
            ficheroSalidaGeneral1.write(retornador);
            
            ficheroSalidaGeneral1.close();
              
            retornador = "";
            
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Posiciones_Mutadas_Tabla_Mutaciones.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }   
    }
}
