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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Similitud {
    
    /*Calcula el porcentaje de conservación de una secuencia completa problema en un pool de secuencias en archivos .fasta. 
    El resultado es un archivo .html. Esto lo hace valiendose de la clase Calculos_Frecuencias_Posicion.*/
    
    public static void cargarHomologiaSimilitud(String entrada, String salida, String secuenciaProblema, int homologiaInferior, int homologiaSuperior){
        
        try{
            
            String retornoSimilitud;
            String retornoSimilitudSplit[];
            String retornoNumericoSimilitud;
                
            String archivo;
			
            final File carpeta = new File(entrada);
			
            String ficheros[];
		
            ficheros = carpeta.list();
            Arrays.sort(ficheros);
                                            			
            try (FileWriter archivoSalida = new FileWriter(salida)) {
                
                archivoSalida.write("<!DOCTYPE html>");
                archivoSalida.write("<html lang = 'es'>");
                archivoSalida.write("<head>");
                archivoSalida.write("<meta charset = 'UTF-8'/>");
                archivoSalida.write(Menu_Lateral.head());
                archivoSalida.write(Estilo_Tablas_html.cssTabla());
                archivoSalida.write("</head><body>");
                
                if(idioma == 1){
                    
                    if(homologiaInferior == -1 && homologiaSuperior == -1){
                        
                        archivoSalida.write("<h3 style='text-align: center;'>Homolog&iacute;a Similitud</h3>");
                        archivoSalida.write(Menu_Lateral.body("Homolog&iacute;a Similitud", true, true, true));
                        
                    }else{
                        
                        archivoSalida.write("<h3 style='text-align: center;'>Homolog&iacute;a Similitud Rango " + (homologiaInferior + 1) + " - " + (homologiaSuperior + 1) + "</h3>");
                        archivoSalida.write(Menu_Lateral.body("Homolog&iacute;a Similitud Rango " + (homologiaInferior + 1) + " - " + (homologiaSuperior + 1), true, true, true));
                        
                    }
                    
                    archivoSalida.write("<h4 style='text-align: center;'>Secuencia Problema: " + secuenciaProblema + "</h4>");
                    
                }else{
                    
                    if(homologiaInferior == -1 && homologiaSuperior == -1){
                        
                        archivoSalida.write("<h3 style='text-align: center;'>Homology Similarity</h3>");
                        archivoSalida.write(Menu_Lateral.body("Homology Similarity", true, true, true));
                        
                    }else{
                        
                        archivoSalida.write("<h3 style='text-align: center;'>Homology Similarity Range " + (homologiaInferior + 1) + " - " + (homologiaSuperior + 1) + "</h3>");
                        archivoSalida.write(Menu_Lateral.body("Homology Similarity Range " + (homologiaInferior + 1) + " - " + (homologiaSuperior + 1), true, true, true));
                        
                    }
                    
                    archivoSalida.write("<h4 style='text-align: center;'>Problem Sequence: " + secuenciaProblema + "</h4>");
                    
                }
                
                archivoSalida.write("<table>");
                
                if(idioma == 1){
                    
                    archivoSalida.write("<tr><td>Archivo</td><td>Frecuencia</td><td>Secuencias Totales</td></tr>");
                    
                }else{
                    
                    archivoSalida.write("<tr><td>File</td><td>Frequency</td><td>Total Sequences</td></tr>");
                    
                }
                
                for (String fichero : ficheros) {
                    
                    archivo = entrada + "/" + fichero;
                    
                    retornoSimilitud = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, 1, 'ł', "ł", 6, 0, 0, secuenciaProblema/*.toUpperCase()*/, 0, homologiaInferior, homologiaSuperior, 0, 0, 'ł');
                    retornoSimilitudSplit = retornoSimilitud.split("</a><a><strong> ");
                    retornoNumericoSimilitud = retornoSimilitudSplit[1];
                    retornoSimilitud = retornoSimilitud.replace("</a><a><strong> " + retornoSimilitudSplit[1] , "");
                    retornoNumericoSimilitud = retornoNumericoSimilitud.replace("</strong></a>", "");
                    
                    archivoSalida.write("<tr><td><a style = 'font-size: 100%;'>" + fichero + "</td><td></strong></a>" + "<strong>" + retornoSimilitud + "</td><td>" + retornoNumericoSimilitud + "</td></strong></tr>");
                
                }
                
                archivoSalida.write("</table></body></html>");
            }
        
        }catch(IOException e){
        
            Logger.getLogger(Similitud.class.getName()).log(Level.SEVERE, null, e);
                                    
            btn_presionado = false;
                                    
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                                    
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                                    
            Terminar_Hilos.cargarTerminarHilos();
        
        }
        
    }
    
}
