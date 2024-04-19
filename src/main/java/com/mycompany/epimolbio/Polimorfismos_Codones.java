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

public class Polimorfismos_Codones {
    
    /*Encuentra los codones mutados respecto a una secuencia de referencia, valiendose de la clase Calculos_Frecuencias_Posicion_Codones.
    El restultado es un listado en html*/
    
    public static void cargarPolimorfismosCodones(String entrada, String salida, String referencia, int criba){
                       
        try{
        
            int cantidadCodones = (int) Math.floor(referencia.length()/3);
                
            String wtCodon;
                                                
            char codonesArr [];
            char CodonesArr_[];
        
            String numericoCodones;
        
            int contadorSaltos;
            String retornoCodonesTemporal;
        
            String retornoPosicionesCodonesSplit[];
                		
            String archivo;
		
            final File carpeta = new File(entrada);
		
            String ficheros[];
		
            ficheros = carpeta.list();
            Arrays.sort(ficheros);
        
            try (FileWriter ficheroSalida = new FileWriter(salida)) {
                
                String retornoPosicionesCodones;
                
                ficheroSalida.write("<!DOCTYPE html>");
                ficheroSalida.write("<html lang = 'es'>");
                ficheroSalida.write("<head>");
                ficheroSalida.write("<meta charset = 'UTF-8'/>");
                ficheroSalida.write(Menu_Lateral.head());
                ficheroSalida.write(Estilo_Tablas_html.cssTabla());
                ficheroSalida.write("</head>\n");
                ficheroSalida.write("<body>\n");
                
                if(criba == 1){
                    
                    if(idioma == 1){
                        
                        ficheroSalida.write(Menu_Lateral.body("Variabilidad Polimorfismos Codones 100%", true, true, true));
                        ficheroSalida.write("<h3 style = 'text-align: center;'>Variabilidad Polimorfismos Codones 100%</h3>");
                        
                    }else{
                        
                        ficheroSalida.write(Menu_Lateral.body("Variability Polymorphisms Codons 100%", true, true, true));
                        ficheroSalida.write("<h3 style = 'text-align: center;'>Variability Polymorphisms Codons 100%</h3>");
                        
                    }
                    
                }else if(criba == 2){
                    
                    if(idioma == 1){
                        
                        ficheroSalida.write(Menu_Lateral.body("Variabilidad Polimorfismos Codones > 75%", true, false, false));
                        ficheroSalida.write("<h3 style = 'text-align: center;'>Variabilidad Polimorfismos Codones > 75%</h3>");
                        
                    }else{
                        
                        ficheroSalida.write(Menu_Lateral.body("Variability Polymorphisms Codons > 75%", true, false, false));
                        ficheroSalida.write("<h3 style = 'text-align: center;'>Variability Polymorphisms Codons > 75%</h3>");
                        
                    }
                    
                }
                
                for (String fichero : ficheros) {
                    
                    if (fichero.equals("")) {
                        
                        break;
                        
                    }
                    
                    archivo = entrada + "/" + fichero;
                    ficheroSalida.write("<h4 style = 'text-align: center;'>" + fichero + "</h4>");
                    ficheroSalida.write("<table>");
                    
                    if(idioma == 1){
                        
                        ficheroSalida.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Posiciones Totales</td>");
                        
                    }else{
                        
                        ficheroSalida.write("<tr><td>Position</td><td>Residues</td><td>Total Positions</td>");
                        
                    }
                    
                    codonesArr = referencia.toCharArray();
                    CodonesArr_ = new char[3];
                    
                    for(int tripletes = 0; tripletes < (cantidadCodones * 3); tripletes += 3) {
                        
                        CodonesArr_[0] = codonesArr[tripletes];
                        CodonesArr_[1] = codonesArr[tripletes+ 1];
                        CodonesArr_[2] = codonesArr[tripletes + 2];
                        
                        wtCodon = String.valueOf(CodonesArr_);
                        
                        ficheroSalida.write("<tr>");
                        
                        retornoPosicionesCodones = Calculos_Frecuencias_Posicion_Codones.cargarCalculosFrecuenciasPosicionCodones(archivo, (tripletes + 1), wtCodon, 2, criba, null);
                        retornoPosicionesCodonesSplit = retornoPosicionesCodones.split(",");
                        
                        retornoPosicionesCodones = retornoPosicionesCodones.replace("," + retornoPosicionesCodonesSplit[retornoPosicionesCodonesSplit.length-1], "");
                        
                        contadorSaltos = 0;
                        retornoCodonesTemporal = "";
                        
                        if(!retornoPosicionesCodones.contains("color")){
                            
                            continue;
                            
                        }
                        
                        for(int g = 0; g < retornoPosicionesCodones.length(); g++){
                            
                            if(retornoPosicionesCodones.toCharArray()[g] == ','){
                                
                                contadorSaltos++;
                                
                            }
                            
                            if(g != 0 && contadorSaltos % 5 == 0 && retornoPosicionesCodones.toCharArray()[g] == ','){
                                
                                retornoCodonesTemporal += retornoPosicionesCodones.toCharArray()[g] + "<br>";
                                
                            }else{
                                
                                retornoCodonesTemporal += retornoPosicionesCodones.toCharArray()[g];
                                
                            }
                            
                        }
                                        
                        retornoPosicionesCodones = retornoCodonesTemporal;
                        
                        numericoCodones = retornoPosicionesCodonesSplit[retornoPosicionesCodonesSplit.length-1].replace(" ", "").replace("</br>", "");
                        
                        ficheroSalida.write("<td>" + ((tripletes/3)+1) + " " + Traductor.cargarTraductor(wtCodon, 0) + "(" + wtCodon + ")</td><td>" + retornoPosicionesCodones.replace(",", "") + "</td><td>" + numericoCodones + "</td>");
                        
                        ficheroSalida.write("</tr>");
                        
                    }
                    
                    ficheroSalida.write("</table><br><br>");
                }
            }
        
        }catch(IOException e){
            
            Logger.getLogger(Polimorfismos_Codones.class.getName()).log(Level.SEVERE, null, e);
                                    
            btn_presionado = false;
                                    
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                                    
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                                    
            Terminar_Hilos.cargarTerminarHilos();
                                    
        }
                 
    }
    
}
