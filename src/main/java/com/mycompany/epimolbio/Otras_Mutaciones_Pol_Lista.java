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

public class Otras_Mutaciones_Pol_Lista {
    
    /*Calcula la frecuencia de aparición de mutaciones en el pool de secuencias introducidas del gen Pol, valiendose de la clase Calculos_Frecuencias_Posicion.
    El resultado es un listado en formato .html*/
    
    /*Calculates the frequency of mutations in the input sequence pool for the Pol gene, using the Calculos_Frecuencias_Posicion class.
    The result is a list in .html format.*/
    
    public static void cargarOtrasMutacionesPolLista(String entrada, String salida, int seleccionProteina, int cribado, int virus){
        
        try{
        
            String hxb2PRVIH1 = "PQVTLWQRPLVTIKIGGQLKEALLDTGADDTVLEEMSLPGRWKPKMIGGIGGFIKVRQYDQILIEICGHKAIGTVLVGPTPVNIIGRNLLTQIGCTLNF";
            String hxb2RTVIH1 = "PISPIETVPVKLKPGMDGPKVKQWPLTEEKIKALVEICTEMEKEGKISKIGPENPYNTPVFAIKKKDSTKWRKLVDFRELNKRTQDFWEVQLGIPHPAGLKKKKSVTVLDVGDAYFSVPLDEDFRKYTAFTIPSINNETPGIRYQYNVLPQGWKGSPAIFQSSMTKILEPFRKQNPDIVIYQYMDDLYVGSDLEIGQHRTKIEELRQHLLRWGLTTPDKKHQKEPPFLWMGYELHPDKWTVQPIVLPEKDSWTVNDIQKLVGKLNWASQIYPGIKVRQLCKLLRGTKALTEVIPLTEEAELELAENREILKEPVHGVYYDPSKDLIAEIQKQGQGQWTYQIYQEPFKNLKTGKYARMRGAHTNDVKQLTEAVQKITTESIVIWGKTPKFKLPIQKETWETWWTEYWQATWIPEWEFVNTPPLVKLWYQLEKEPIVGAETF";
            String hxb2INVIH1 = "FLDGIDKAQDEHEKYHSNWRAMASDFNLPPVVAKEIVASCDKCQLKGEAMHGQVDCSPGIWQLDCTHLEGKVILVAVHVASGYIEAEVIPAETGQETAYFLLKLAGRWPVKTIHTDNGSNFTGATVRAACWWAGIKQEFGIPYNPQSQGVVESMNKELKKIIGQVRDQAEHLKTAVQMAVFIHNFKRKGGIGGYSAGERIVDIIATDIQTKELQKQITKIQNFRVYYRDSRNPLWKGPAKLLWKGEGAVVIQDNSDIKVVPRRKAKIIRDYGKQMAGDDCVASRQDED*";
		
            String aliPRVIH2 = "PQFSLWKRPVVTAYIEGQPVEVLLDTGADDSIVAGIELGSNYTPKIVGGIGGFINTKEYEDVEIKVLNKRVKATIMTGDTPINIFGRNILTALGMSLNL";
            String aliRTVIH2 = "PVAKIEPIEVRLKPGKDGPKLRQWPLTKEKIEALKEICEKTEREGQLEEAPPTNPYNTPTFAIKKKDKNKWRMLIDFRELNKVTQDFTEIQLGIPHPAGLAKKRRITVLDVGDAYFSIPLHESFRQYTAFTLPSVNNAEPGKRYIYKVLPQGWKGSPAIFQHTMRQILEPFRKANQDVILIQYMDDILIASDRTDLEHDKVVLQLKELLNGLGFSTPDEKFQKDPPYKWMGYGLWPTKWKLQKIQLPQKEVWTVNDIQKHVGVLNWAAQIYPGIKTKHLCRLIRGKMTLTEGVQWTELAEAELEENRIILSQEQEGHYYQEEKELEATVQKDQDNQWTYKIHQGEKILKVEKYAKMKNTHTNGVRLLAQVVQKIGKEALVIWGRIPRFHLPVERETWEQWWDDYWQVTWIPDWDFVSTPPLVRLAFNLVKDPILGAETF";
            String aliINVIH2 = "FLEKIEPAQEEHEKYHSNVKELSHKFGLPNLVARQIVNTCAQCQQKGEAIHGQVNAELGTWQMDCTHLEGKVIIIAVHVASGFIEAEVIPQESGRQTALFLLKLASRWPITHLHTDSGVNFTSQEVKMVAWWVGIEQSFGVPYNPQSQGVVEAMNHHLKNQISRIREQANTVETIVLMAVHCMNFKRRGGIGDMTPAERLINMISTEQEIQFLQTKNLKFKNFPVYYREGRDQLWKGPGELLWKGDGAVIVKVGTDIKVVPRRKAKIIRDYGGRQELDSGPHLEGAREDGEVA*";

            boolean virusTitulo;
            
            String archivo;
            String wtSeleccionado = null;
        
            String temporalPosicion;
            boolean mostrar;
        
            int longWt = 0;
                
            String retornoTemporal[];
            String secuenciaSalida;
            String secuenciasTotales;
                
            String ficheroSalida = salida;
        
            String directorio = entrada;
	
            final File carpeta = new File(directorio);
		
            String ficheros[];
		
            ficheros = carpeta.list();
            Arrays.sort(ficheros);
                        
            try (FileWriter ficheroSalida1 = new FileWriter(ficheroSalida)) {
                
                String virusTag;
                
                if(virus == 1){
                    
                    virusTitulo = true;
                    
                    if(idioma == 1){
                        
                        virusTag = "VIH-1";
                        
                    }else{
                        
                        virusTag = "HIV-1";
                        
                    }
                    
                }else{
                    
                    virusTitulo = false;
                    
                    if(idioma == 1){
                        
                        virusTag = "VIH-2";
                        
                    }else{
                        
                        virusTag = "HIV-2";
                        
                    }
                    
                }
                
                ficheroSalida1.write("<!DOCTYPE html>");
                ficheroSalida1.write("<html lang = 'es'>");
                ficheroSalida1.write("<head>");
                ficheroSalida1.write("<meta charset = 'UTF-8'/>");
                ficheroSalida1.write(Menu_Lateral.head());
                ficheroSalida1.write(Estilo_Tablas_html.cssTabla());
                
                for(int i = 0; i< ficheros.length; i++) {
                    
                    if(ficheros[i].equals("")) {
                        
                        break;
                        
                    }
                    
                    archivo = directorio + "/" + ficheros[i];
                    
                    if(i == 0){
                        
                        if(idioma == 1){
                            
                            if(seleccionProteina == 1 && cribado == 1 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Proteasa VIH-1 100%</h3>");
                                
                            }else if(seleccionProteina == 1 && cribado == 1 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Proteasa VIH-2 100%</h3>");
                                
                            }else if(seleccionProteina == 1 && cribado == 2 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Proteasa VIH-1 > 75%</h3>");
                                
                            }else if(seleccionProteina == 1 && cribado == 2 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Proteasa VIH-2 > 75%</h3>");
                                
                            }else if(seleccionProteina == 2 && cribado == 1 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Retrotranscriptasa VIH-1 100%</h3>");
                                
                            }else if(seleccionProteina == 2 && cribado == 1 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Retrotranscriptasa VIH-2 100%</h3>");
                                
                            }else if(seleccionProteina == 2 && cribado == 2 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Retrotranscriptasa VIH-1 > 75%</h3>");
                                
                            }else if(seleccionProteina == 2 && cribado == 2 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Retrotranscriptasa VIH-2 > 75%</h3>");
                                
                            }else if(seleccionProteina == 3 && cribado == 1 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Integrasa VIH-1 100%</h3>");
                                
                            }else if(seleccionProteina == 3 && cribado == 1 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Integrasa VIH-2 100%</h3>");
                                
                            }else if(seleccionProteina == 3 && cribado == 2 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Integrasa VIH-1 > 75%</h3>");
                                
                            }else if(seleccionProteina == 3 && cribado == 2 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>Lista Otras Mutaciones Pol Integrasa VIH-2 > 75%</h3>");
                                
                            }
                            
                        }else{
                            
                            if(seleccionProteina == 1 && cribado == 1 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Protease HIV-1 100%</h3>");
                                
                            }else if(seleccionProteina == 1 && cribado == 1 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Protease HIV-2 100%</h3>");
                                
                            }else if(seleccionProteina == 1 && cribado == 2 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Protease HIV-1 > 75%</h3>");
                                
                            }else if(seleccionProteina == 1 && cribado == 2 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Protease HIV-2 > 75%</h3>");
                                
                            }else if(seleccionProteina == 2 && cribado == 1 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Retrotranscriptase HIV-1 100%</h3>");
                                
                            }else if(seleccionProteina == 2 && cribado == 1 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Retrotranscriptase HIV-2 100%</h3>");
                                
                            }else if(seleccionProteina == 2 && cribado == 2 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Retrotranscriptase HIV-1 > 75%</h3>");
                                
                            }else if(seleccionProteina == 2 && cribado == 2 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Retrotranscriptase HIV-2 > 75%</h3>");
                                
                            }else if(seleccionProteina == 3 && cribado == 1 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Integrase HIV-1 100%</h3>");
                                
                            }else if(seleccionProteina == 3 && cribado == 1 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Integrase HIV-2 100%</h3>");
                                
                            }else if(seleccionProteina == 3 && cribado == 2 && virusTitulo == true){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Integrase HIV-1 > 75%</h3>");
                                
                            }else if(seleccionProteina == 3 && cribado == 2 && virusTitulo == false){
                                
                                ficheroSalida1.write("<h3 style = 'text-align: center;'>List Other Pol Mutations Integrase HIV-2 > 75%</h3>");
                                
                            }
                            
                        }
                        
                    }
                    
                    ficheroSalida1.write("<h4 style = 'text-align: center;'>" + ficheros[i] + "</h4>");
                    
                    if(virus == 1) {
                        
                        switch (seleccionProteina) {
                            
                            case 1:
                                
                                longWt = 99;
                                wtSeleccionado = hxb2PRVIH1;
                                break;
                                
                            case 2:
                                
                                longWt = 440;
                                wtSeleccionado = hxb2RTVIH1;
                                break;
                                
                            case 3:
                                
                                longWt = 289;
                                wtSeleccionado = hxb2INVIH1;
                                break;
                                
                            default:
                                
                                break;
                                
                        }
                        
                    }else if(virus == 2) {
                        
                        switch (seleccionProteina) {
                            
                            case 1:
                                
                                longWt = 99;
                                wtSeleccionado = aliPRVIH2;
                                break;
                                
                            case 2:
                                
                                longWt = 439;
                                wtSeleccionado = aliRTVIH2;
                                break;
                                
                            case 3:
                                
                                longWt = 294;
                                wtSeleccionado = aliINVIH2;
                                break;
                                
                            default:
                                
                                break;
                                
                        }
                        
                    }
                    
                    mostrar = false;
                    
                    ficheroSalida1.write("<table>");
                    
                    if(idioma == 1){
                        
                        ficheroSalida1.write("<tr><td>Posici&oacuten</td><td>Residuos</td><td>Posiciones Totales</td>");
                        
                    }else{
                        
                        ficheroSalida1.write("<tr><td>Position</td><td>Residues</td><td>Total Positions</td>");
                        
                    }
                    
                    for(int x = 0; x < longWt; x++) {
                        
                        ficheroSalida1.write("<tr>");
                        
                        secuenciaSalida = "";
                        
                        temporalPosicion = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, (x+1), wtSeleccionado.toCharArray()[x], "↓", 1, cribado, 0, "", 0, 0, 0, 0, 0, 'ł');
                        
                        retornoTemporal = temporalPosicion.split("\\)");
                        
                        secuenciasTotales = retornoTemporal[retornoTemporal.length-1];
                        
                        temporalPosicion = temporalPosicion.replace(" ", "");
                        temporalPosicion = temporalPosicion.replace("</a></strong></a>)", "</a></strong></a>) ");
                        retornoTemporal = temporalPosicion.split(" ");
                        
                        for(int l = 0; l < retornoTemporal.length-1; l++){
                            
                            retornoTemporal[l] = retornoTemporal[l].replace("aStyle", "a Style");
                            secuenciaSalida += retornoTemporal[l] + " ";
                            
                            if((l+1) % 4 == 0){
                                
                                secuenciaSalida += "<br>";
                                
                            }
                            
                        }
                        
                        if(cribado == 2) {
                            
                            for(int s = 0; s < temporalPosicion.toCharArray().length; s++) {
                                
                                if(temporalPosicion.toCharArray()[s] == ')') {
                                    
                                    mostrar = true;
                                    break;
                                    
                                }
                                
                            }
                            
                        }else {
                            
                            mostrar = true;
                            
                        }
                        
                        if(mostrar == true) {
                            
                            ficheroSalida1.write("<td>" + wtSeleccionado.toCharArray()[x] + (x+1) + "</td><td>" + secuenciaSalida + "</td><td>" + secuenciasTotales + "</td>");
                            
                        }
                        
                        mostrar = false;
                        
                        ficheroSalida1.write("</tr>");
                        
                    }
                    
                    ficheroSalida1.write("</table><br><br>");
                    
                    if(cribado == 1 && i == 0 && seleccionProteina == 1){
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write(Menu_Lateral.body("Lista Otras Mutaciones Pol Proteasa " + virusTag + " 100%", true, true, true));
                            
                        }else{
                            
                            ficheroSalida1.write(Menu_Lateral.body("List Other Pol Mutations Protease " + virusTag + " 100%", true, true, true));
                            
                        }
                        
                    }else if(cribado == 2 && i == 0 && seleccionProteina == 1){
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write(Menu_Lateral.body("Lista Otras Mutaciones Pol Proteasa " + virusTag + " > 75%",  true, false, false));
                            
                        }else{
                            
                            ficheroSalida1.write(Menu_Lateral.body("List Other Pol Mutations Protease " + virusTag + " > 75%", true, false, false));
                            
                        }
                        
                    }else if(cribado == 1 && i == 0 && seleccionProteina == 2){
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write(Menu_Lateral.body("Lista Otras Mutaciones Pol Retrotranscriptasa " + virusTag + " 100%", true, true, true));
                            
                        }else{
                            
                            ficheroSalida1.write(Menu_Lateral.body("List Other Pol Mutations Retrotranscriptase " + virusTag + " 100%", true, true, true));
                            
                        }
                        
                    }else if(cribado == 2 && i == 0 && seleccionProteina == 2){
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write(Menu_Lateral.body("Lista Otras Mutaciones Pol Retrotranscriptasa " + virusTag + " > 75%",  true, false, false));
                            
                        }else{
                            
                            ficheroSalida1.write(Menu_Lateral.body("List Other Pol Mutations Retrotranscriptase " + virusTag + " > 75%", true, false, false));
                            
                        }
                        
                    }else if(cribado == 1 && i == 0 && seleccionProteina == 3){
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write(Menu_Lateral.body("Lista Otras Mutaciones Pol Integrasa " + virusTag + " 100%", true, true, true));
                            
                        }else{
                            
                            ficheroSalida1.write(Menu_Lateral.body("List Other Pol Mutations Integrase " + virusTag + " 100%", true, true, true));
                            
                        }
                        
                    }else if(cribado == 2 && i == 0 && seleccionProteina == 3){
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write(Menu_Lateral.body("Lista Otras Mutaciones Pol Integrasa " + virusTag + " > 75%", true, true, true));
                            
                        }else{
                            
                            ficheroSalida1.write(Menu_Lateral.body("List Other Pol Mutations Integrase " + virusTag + " > 75%", true, true, true));
                            
                        }
                        
                    }
                }
            }
        
        }catch(IOException e){
        
            Logger.getLogger(Otras_Mutaciones_Pol_Lista.class.getName()).log(Level.SEVERE, null, e);
                                    
            btn_presionado = false;
                                    
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                                    
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                                    
            Terminar_Hilos.cargarTerminarHilos();
        
        }
        
    }
    
}
