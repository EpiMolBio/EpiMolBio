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
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Similitud_Parcial {
    
    public static String secuencias2[] = new String[1000000];
    public static String encabezados2[] = new String[1000000];
    
    //Busca regiones de una secuencia que tenga la similitud dada con las secuencias de un archivo .fasta
    //Search for regions of a sequence that have the given similarity with the sequences in a .fasta file
    
    public static void cargarSimilitudParcial(String archivoEntrada, String archivoSalida, String consensoProblema, int tipo, int tamano, double similitud, int alinear, int traducir){
        
        try{
            
            String parAlineado[] = new String[2];
            
            int contadorSimilitud;
                             
            int alcanceConsensosMarcos;
                                
            String consensosMarcos[];
            String consensosListas[];
            
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
            
            DecimalFormat df = new DecimalFormat("0.000" , symbols);
            df.setDecimalSeparatorAlwaysShown(true);
        
            String archivoCarga = archivoEntrada;
        
            String ficheros[] = new String[5000];
       
            final File carpeta = new File(archivoCarga);
        
            for(int i = 0; i < ficheros.length; i++){
            
                ficheros[i] = "";
            
            }
        
            int a = 0;
			
            for(File ficherosEntrada: carpeta.listFiles()) {
				
                ficheros[a] = (ficherosEntrada.getName());
                a++;
            
            }
        
            String colorPorcentaje = "";
        
            double similaridadResultado;
        
            String consensoLista;
        
            boolean continuidadLista;
            boolean continuidadProblema;
        
            String consensoListaTrozeado;
            String consensoProblemaTrozeado;
      
            try (FileWriter ficheroSalida = new FileWriter(archivoSalida)) {
                
                ficheroSalida.write("<!DOCTYPE html>");
                ficheroSalida.write("<html lang = 'es'>");
                ficheroSalida.write("<head>");
                ficheroSalida.write("<meta charset = 'UTF-8'/>");
                ficheroSalida.write(Menu_Lateral.head());
                ficheroSalida.write(Estilo_Tablas_html.cssTabla());
                ficheroSalida.write("</head>\n");
                ficheroSalida.write("<body>\n");
                
                if(idioma == 1){
                    
                    ficheroSalida.write("<h3  style = 'text-align: center;'>Homolog&iacute;a Similitud Parcial Porcentaje Conservaci&oacute;n " + similitud + "%</h3>");
                    ficheroSalida.write(Menu_Lateral.body("Homolog&iacute;a Similitud Parcial Porcentaje Conservaci&oacute;n " + similitud + "%", true, true, true));
                    
                }else{
                    
                    ficheroSalida.write("<h3 style = 'text-align: center;'>Homology Partial Similarity " + similitud + "%</h3>");
                    ficheroSalida.write(Menu_Lateral.body("Homology Partial Similarity " + similitud + "%", true, true, true));
                    
                }
                
                ficheroSalida.write("<table>");
                
                if(idioma == 1){
                    
                    ficheroSalida.write("<tr><td>Archivo</td><td>Secuencia</td><td>Secuencia Introducida</td><td>Secuencia Encontrada</td><td>Similitud</td></tr>");
                    
                }else{
                    
                    ficheroSalida.write("<tr><td>File</td><td>Sequence</td><td>Input Sequence</td><td>Found Sequence</td><td>Similarity</td></tr>");
                    
                }
                
                
                for (String fichero : ficheros) {
                    
                    if (fichero.equals("")) {
                        
                        break;
                
                    }
                    
                    for(int i = 0; i < secuencias2.length; i++){
                        
                        secuencias2[i] = "";
                        encabezados2[i] = "";
                        
                    }
                    
                    lectorSecuencias(archivoEntrada + "/" + fichero);
                    
                    for (int recorridoLista = 0; recorridoLista < secuencias2.length; recorridoLista++) {
                        
                        if(secuencias2[recorridoLista].equals("")){
                            
                            break;
                    
                        }
                        
                        consensoLista = secuencias2[recorridoLista];
                        continuidadLista = false;
                        continuidadProblema = false;
                        consensoProblema = consensoProblema.toUpperCase();
                        
                        if(traducir == 1){
                            
                            alcanceConsensosMarcos = 1;
                            
                        }else{
                            
                            alcanceConsensosMarcos = 3;
                            
                        }
                        
                        consensosMarcos = new String[alcanceConsensosMarcos];
                        consensosListas = new String[alcanceConsensosMarcos];
                        
                        if(traducir == 1){
                            
                            consensosListas[0] = consensoLista;
                            consensosMarcos[0] = consensoProblema;
                            
                        }else{
                            
                            consensosListas[0] = Traductor.cargarTraductor(consensoLista, 0);
                            consensosListas[1] = Traductor.cargarTraductor(consensoLista, 1);
                            consensosListas[2] = Traductor.cargarTraductor(consensoLista, 2);
                            
                            consensosMarcos[0] = Traductor.cargarTraductor(consensoProblema, 0);
                            consensosMarcos[1] = Traductor.cargarTraductor(consensoProblema, 1);
                            consensosMarcos[2] = Traductor.cargarTraductor(consensoProblema, 2);
                            
                        }
                        
                        parAlineado[0] = "";
                        parAlineado[1] = "";
                                                
                        for (int consensos_lista = 0; consensos_lista < consensosMarcos.length; consensos_lista++) {
                            
                            if(consensosListas[consensos_lista].equals("")){
                                
                                break;
                                
                            }
                            
                            consensoLista = consensosListas[consensos_lista];
                            
                            for (String consensos_marco : consensosMarcos) {
                                
                                if (consensos_marco.equals("")) {
                                    
                                    break;
                                    
                                }
                                
                                consensoProblema = consensos_marco;
                                
                                for (int i = 0; i <= consensoLista.length() - tamano; i++) {
                                    
                                    consensoListaTrozeado = "";
                                    
                                    for(int region_lista = i; region_lista < tamano + i; region_lista++){
                                        
                                        if(consensoLista.toCharArray()[region_lista] == '*'){
                                            
                                            continuidadLista = true;
                                            break;
                
                                        }
                                        
                                        consensoListaTrozeado += consensoLista.toCharArray()[region_lista];
                                        
                                    }
                                    
                                    if(continuidadLista == true){
                                        
                                        continuidadLista = false;
                                        continue;
                    
                                    }
                                    
                                    for (int x = 0; x <= consensoProblema.length() - tamano; x++) {
                                        
                                        consensoProblemaTrozeado = "";
                                        
                                        for(int region_problema = x; region_problema < tamano + x; region_problema++){
                                            
                                            if(consensoProblema.toCharArray()[region_problema] == '*'){
                                                
                                                continuidadProblema = true;
                                                break;
                                                
                                            }
                                            
                                            consensoProblemaTrozeado += consensoProblema.toCharArray()[region_problema];
                                            
                                        }
                                        
                                        if(continuidadProblema == true){
                                            
                                            continuidadProblema = false;
                                            continue;
                                            
                                        }
                                        
                                        if(alinear == 1){
                                            
                                            if(tipo == 1){
                                                
                                                parAlineado = Calculos_Alineamientos.alineamientoNucleotidos(consensoListaTrozeado, consensoProblemaTrozeado, "", "");
                                                
                                                
                                            }else if(tipo == 2){
                                                
                                                parAlineado = Calculos_Alineamientos.alineamientoAminoacidos(consensoListaTrozeado, consensoProblemaTrozeado, "", "");
                                                
                                            }
                                            
                                        }else{
                                            
                                            parAlineado[0] = consensoListaTrozeado;
                                            parAlineado[1] = consensoProblemaTrozeado;
                                            
                                        }
                                        
                                        contadorSimilitud = 0;
                                        
                                        for(int n = 0; n < parAlineado[0].length(); n++){
                                            
                                            if(parAlineado[0].toCharArray()[n] == parAlineado[1].toCharArray()[n]){
                                                
                                                contadorSimilitud++;
                                                
                                            }
                                            
                                        }

                                        similaridadResultado = (contadorSimilitud * 100.0)/ parAlineado[0].length();
                                        
                                        if (similaridadResultado >= similitud) {
                                            
                                            ficheroSalida.write("<tr><td>" + fichero + "</td>");
                                            ficheroSalida.write("<td>" + encabezados2[recorridoLista] + "</td>");
                                            ficheroSalida.write("<td>" + parAlineado[0] + "</td>");
                                            ficheroSalida.write("<td>" + parAlineado[1] + "</td>");
                                            
                                            if(similaridadResultado == 100.0){
                                                
                                                colorPorcentaje = "#834580";
                                                
                                            }else if(similaridadResultado >= 90.0){
                                                
                                                colorPorcentaje = "#BF3D27";
                                                
                                            }else if(similaridadResultado < 10.0){
                                                
                                                colorPorcentaje = "#3E8249";
                                                
                                            }else if(similaridadResultado < 90.0 && similaridadResultado >75.0){
                                                
                                                colorPorcentaje = "#F3A031";
                                                
                                            }else if(similaridadResultado <= 75.0 && similaridadResultado > 50.0){
                                                
                                                colorPorcentaje = "#E0D900";
                                                
                                            }else if(similaridadResultado <= 50.0 && similaridadResultado >= 10.0){
                                                
                                                colorPorcentaje = "#2E75B6";
                                                
                                            }
                                            
                                            ficheroSalida.write("<td><a Style = 'color:" + colorPorcentaje + "';>" + df.format(similaridadResultado) + "%</a></td></tr>");
                                        
                                        }
                                    }
                                }
                            }
                        }
                    }                   
                }
                
                ficheroSalida.write("</table></html>");
            }
        
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
             
            Logger.getLogger(Similitud_Parcial.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
    public static void lectorSecuencias(String archivo) throws IOException {
	
        try{
        
            FileReader f = new FileReader(archivo);
            
            try (BufferedReader b = new BufferedReader(f)) {
                
                String temp = "";				
                
                String cadena;
                
                int contador = 0;
                int contador2 = 0;
                
                while((cadena = b.readLine()) != null) {
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        if(temp.length() != 0) {
                            
                            secuencias2[contador2] = temp.toUpperCase();
                            contador2++;
                            
                        }
                        
                        encabezados2[contador] = cadena;
                        
                        contador++;
                        
                        if(temp.length() != 0) {
                            
                            temp = "";
                            
                        }else{
                            
                            temp = "";
                            
                        }
                        
                    }else {
                        
                        temp += cadena;
                        
                    }

                }
                
                secuencias2[contador2] = temp.toUpperCase();
            }
        
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Similitud_Parcial.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
}
