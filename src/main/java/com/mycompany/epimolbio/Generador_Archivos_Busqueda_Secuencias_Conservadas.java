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
import static com.mycompany.epimolbio.Interfaz.jTextField17;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Generador_Archivos_Busqueda_Secuencias_Conservadas {
    
    //Crea el archivo de Busqueda de Secuencias Conservadas, para esto se sirve de la clase Calculos_Frecuencias_Posicion.
    //Creates the Conserved Sequences Search file, using the Calculos_Frecuencias_Posicion class.
    
    public static void cargarGeneradorArchivosBusquedaSecuenciasConservadas(String rutaCarga, String rutaGuardado, double porcentajeHomologia,
                    int valorInferior, int valorSuperior, int rangoLongitudInferior, int rangoLongitudSuperior){
    
        try{
            
            int c;
            
            int contadorParadaConsenso;
                        
            String retornoInterno;
            
            String retorno;
            
            String cadenaEvaluar;
                    
            String retornoDividido[];
            
            String directorio = rutaCarga + "/";

            final File carpeta = new File(directorio);

            String ficheros[] = new String[5000];

            for(int n = 0; n < ficheros.length; n++) {

                ficheros[n] = "";

            }

            int a = 0;

            for (File ficheroEntrada : carpeta.listFiles()) {

                ficheros[a] = (ficheroEntrada.getName());
                a++;
            }
        
            String ficheroSalida1;

            ficheroSalida1 = rutaGuardado;

            try (FileWriter ficheroSalida2 = new FileWriter(ficheroSalida1)) {
                
                ficheroSalida2.write("<!DOCTYPE html>");
                ficheroSalida2.write("<html lang = 'es'>");
                ficheroSalida2.write("<head>");
                ficheroSalida2.write("<meta charset = 'UTF-8'/>");
                ficheroSalida2.write(Menu_Lateral.head());
                ficheroSalida2.write(Estilo_Tablas_html.cssTabla());
                ficheroSalida2.write("</head><body>");
                
                if(idioma == 1){
                    
                    ficheroSalida2.write("<h3 style='text-align: center;'>Homolog&iacute;a B&uacute;squeda de Secuencias Conservadas Longitud " + rangoLongitudInferior + " - " + rangoLongitudSuperior + " " + porcentajeHomologia +"%</h3>");
                    ficheroSalida2.write(Menu_Lateral.body("Homolog&iacute;a B&uacute;squeda de Secuencias Conservadas Longitud "+ rangoLongitudInferior + " - " + rangoLongitudSuperior + " "+ porcentajeHomologia + "%", true, true, true));
                    
                }else{
                    
                    ficheroSalida2.write("<h3 style='text-align: center;'>Homology Search for Conserved Sequences Length " + rangoLongitudInferior + " - " + rangoLongitudSuperior + " " + porcentajeHomologia + "%</h3>");
                    ficheroSalida2.write(Menu_Lateral.body("Homology Search for Conserved Sequences Length " + rangoLongitudInferior + " - " + rangoLongitudSuperior + " " + porcentajeHomologia + "%", true, true, true));
                    
                }
                
                ficheroSalida2.write("<table>");
                
                if(idioma == 1){
                    
                    ficheroSalida2.write("<tr><td>Archivo</td><td>Longitud</td><td>Regi&oacute;n</td><td>Fragmento</td><td>Frecuencia</td></tr>");
                    
                }else{
                    
                    ficheroSalida2.write("<tr><td>File</td><td>Length</td><td>Region</td><td>Fragment</td><td>Frequency</td></tr>");
                    
                }
                
                for (String fichero : ficheros) {
                    
                    if (fichero.equals("")) {
                        
                        break;
                        
                    }
                    
                    cadenaEvaluar = "";
                    
                    if (jTextField17.getText().equals("")) {
                        
                        c = 0;
                        
                        contadorParadaConsenso = 0;
                        
                        while (true) {
                            
                            if(contadorParadaConsenso == 20){
                                
                                break;
                                
                            }
                            
                            retornoInterno = (Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(rutaCarga + "/" + fichero, c+1, 'ł', "↓", 10, 1, 0, "", 0, 0, 0, 1, 0, 'ł'));
                            cadenaEvaluar += retornoInterno;
                            
                            if(retornoInterno.equals("ł")){
                                
                                contadorParadaConsenso++;
                                
                            }
                            
                            c++;
                            
                        }
                        
                        cadenaEvaluar = cadenaEvaluar.replace("łłłłłłłłłłłłłłłłłłłł", "");
                        
                    } else {
                        
                        cadenaEvaluar = jTextField17.getText().toUpperCase();
                        
                    }
                    for (int i = rangoLongitudInferior; i < rangoLongitudSuperior + 1; i++) {
                        
                        retorno = Busqueda_Secuencias_Conservadas.cargarBusquedaSecuenciasConservadas(directorio + fichero, i, cadenaEvaluar, porcentajeHomologia, valorInferior, valorSuperior);
                        retorno = retorno.replace("<p'font-size: 100%';> <strong>Regi&oacute;n: ", "");
                        retorno = retorno.replace("Regi&oacute;n: ", "");
                        retorno = retorno.replace("Region: ", "");
                        retorno = retorno.replace("</br>", "ł");
                        retorno = retorno.replace(" : ", "ł");
                        retorno = retorno.replace("</strong></p>", "");
                        
                        retornoDividido = retorno.split("ł");
                        
                        if(retorno.equals("")){
                            
                            continue;
                            
                        }
                        
                        for (int j = 0; j < retornoDividido.length; j += 3) {
                            
                            ficheroSalida2.write("<tr><td>" + fichero + "</td>");
                            ficheroSalida2.write("<td>" + i + "</td>");
                            ficheroSalida2.write("<td>" + retornoDividido[j] + "</td>");
                            ficheroSalida2.write("<td>" + retornoDividido[j+1] + "</td>");
                            ficheroSalida2.write("<td>" + retornoDividido[j+2] + "</td></tr>");
                            
                        }
                    }
                }
                
                ficheroSalida2.write("</table></html>");
            }

        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Generador_Archivos_Busqueda_Secuencias_Conservadas.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
}
