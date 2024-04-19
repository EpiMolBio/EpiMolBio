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

public class Conservacion_Individual_Lista {
    
    //Genera la salida en formato lista de conservación individual, valiendose de la clase Calculos_Frecuencias_Posicion.
    
    public static void cargarConservacionLista(String entrada, String salida, String referencia){
        
        try{
                 
            String directorio = entrada;
                        
            final File carpeta = new File(directorio);
			
            String ficheros[];
			
            ficheros = carpeta.list();
            Arrays.sort(ficheros);
                                        
            String archivo;
            String retornoTemporal[];
            String secuenciaSalida;
            String secuenciasTotales;
            String retorno;
            String consenso;
				                
            int longitudProteina = referencia.length();
			                                
            try (FileWriter archivoSalida = new FileWriter(salida)) {
                
                archivoSalida.write("<!DOCTYPE html>");
                archivoSalida.write("<html lang = 'es'>");
                archivoSalida.write("<head>");
                archivoSalida.write("<meta charset = 'UTF-8'/>");
                archivoSalida.write(Menu_Lateral.head());
                archivoSalida.write(Estilo_Tablas_html.cssTabla());
                archivoSalida.write("</head>\n");
                archivoSalida.write("<body>\n");
                
                if(idioma == 1){
                    
                    archivoSalida.write("<h3 style = 'text-align: center;'>Variabilidad Lista Consevaci&oacute;n Individual</h3>");
                    
                }else{
                    
                    archivoSalida.write("<h3 style = 'text-align: center;'>Variability Conservation Individual List</h3>");
                    
                }
                
                for (String fichero : ficheros) {
                    
                    archivo = directorio + fichero;
                    archivoSalida.write("<h4 style = 'text-align: center;'>" + fichero + "</h4>");
                    consenso = "";
                    
                    for(int c = 0; c < longitudProteina; c++){
                        
                        consenso += (Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, (c+1), 'ł', "↓", 10, 1, 0, "", 0, 0, 0, 2, 0, 'ł'));
                        
                    }
                    
                    archivoSalida.write("<table>");
                    
                    if(idioma == 1){
                        
                        archivoSalida.write("<tr><td>CONSENSO</td><td>" + consenso + "</td></tr>");
                        
                    }else{
                        
                        archivoSalida.write("<tr><td>CONSENSUS</td><td>" + consenso + "</td></tr>");
                        
                    }
                    
                    archivoSalida.write("</table></br></br><table>");
                    
                    if(idioma == 1){
                        
                        archivoSalida.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Posiciones Totales</td>");
                        
                    }else{
                        
                        archivoSalida.write("<tr><td>Position</td><td>Residues</td><td>Total Positions</td>");
                        
                    }
                    
                    for(int b = 0; b < longitudProteina; b++) {
                        
                        retorno = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, (b+1), 'ł', "↓", 1, 2, 0, "", 0, 0, 0, 0, 0, 'ł');
                        
                        retornoTemporal = retorno.split("\\)");
                        
                        secuenciasTotales = retornoTemporal[retornoTemporal.length-1];
                        
                        retorno = retorno.replace(" ", "");
                        retorno = retorno.replace("</a></strong></a>)", "</a></strong></a>) ");
                        retornoTemporal = retorno.split(" ");
                        
                        secuenciaSalida = "";
                        
                        for(int l = 0; l < retornoTemporal.length-1; l++){
                            
                            retornoTemporal[l] = retornoTemporal[l].replace("aStyle", "a Style");
                            secuenciaSalida += retornoTemporal[l] + " ";
                            
                        }
                        
                        if(secuenciaSalida.equals("")){
                            
                            continue;
                            
                        }
                        
                        archivoSalida.write("<tr><td>" + referencia.toCharArray()[b] + (b+1) + "</td><td>"+ secuenciaSalida + "</td><td>" + secuenciasTotales + "</td></tr>");
                        
                    }
                    
                    archivoSalida.write("</table><br><br>");
                }
                
                if(idioma == 1){
                    
                    archivoSalida.write(Menu_Lateral.body("Variabilidad Lista Consevaci&oacute;n Individual", true, true, true));
                    
                }else{
                    
                    archivoSalida.write(Menu_Lateral.body("Variability Conservation Individual List", true, true, true));
                    
                }
                
                archivoSalida.write("</head>");
                archivoSalida.write("</html>");
            }
                
        }catch(IOException e){
        
            Logger.getLogger(Conservacion_Individual_Lista.class.getName()).log(Level.SEVERE, null, e);
                                    
            btn_presionado = false;
                                    
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                                    
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                                    
            Terminar_Hilos.cargarTerminarHilos();
                                    
        }
    }
   
}
