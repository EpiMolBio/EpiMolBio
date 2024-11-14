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
import static com.mycompany.epimolbio.Interfaz.jComboBox31;
import static com.mycompany.epimolbio.Interfaz.jTextField53;
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

public class Otras_Mutaciones_Pol_Tabla_Resumen {
    
    /*Calcula la frecuencia de aparición de mutaciones en el pool de secuencias introducidas del gen Pol, valiendose de la clase Calculos_Frecuencias_Posicion.
    El resultado es una tabla resumen en formato .html*/
    
    /*Calculates the frequency of mutations in the input sequence pool for the Pol gene, using the Calculos_Frecuencias_Posicion class. 
    The result is a summary table in .html format.*/
    
    public static void cargarOtrasMutacionesPolTablaResumen(String entrada, String salida, int virus, int seleccionProteina){
        
        try{
      
            String hxb2PRVIH1 = "PQVTLWQRPLVTIKIGGQLKEALLDTGADDTVLEEMSLPGRWKPKMIGGIGGFIKVRQYDQILIEICGHKAIGTVLVGPTPVNIIGRNLLTQIGCTLNF";
            String hxb2RTVIH1 = "PISPIETVPVKLKPGMDGPKVKQWPLTEEKIKALVEICTEMEKEGKISKIGPENPYNTPVFAIKKKDSTKWRKLVDFRELNKRTQDFWEVQLGIPHPAGLKKKKSVTVLDVGDAYFSVPLDEDFRKYTAFTIPSINNETPGIRYQYNVLPQGWKGSPAIFQSSMTKILEPFRKQNPDIVIYQYMDDLYVGSDLEIGQHRTKIEELRQHLLRWGLTTPDKKHQKEPPFLWMGYELHPDKWTVQPIVLPEKDSWTVNDIQKLVGKLNWASQIYPGIKVRQLCKLLRGTKALTEVIPLTEEAELELAENREILKEPVHGVYYDPSKDLIAEIQKQGQGQWTYQIYQEPFKNLKTGKYARMRGAHTNDVKQLTEAVQKITTESIVIWGKTPKFKLPIQKETWETWWTEYWQATWIPEWEFVNTPPLVKLWYQLEKEPIVGAETF";
            String hxb2INVIH1 = "FLDGIDKAQDEHEKYHSNWRAMASDFNLPPVVAKEIVASCDKCQLKGEAMHGQVDCSPGIWQLDCTHLEGKVILVAVHVASGYIEAEVIPAETGQETAYFLLKLAGRWPVKTIHTDNGSNFTGATVRAACWWAGIKQEFGIPYNPQSQGVVESMNKELKKIIGQVRDQAEHLKTAVQMAVFIHNFKRKGGIGGYSAGERIVDIIATDIQTKELQKQITKIQNFRVYYRDSRNPLWKGPAKLLWKGEGAVVIQDNSDIKVVPRRKAKIIRDYGKQMAGDDCVASRQDED*";
		
            String aliPRVIH2 = "PQFSLWKRPVVTAYIEGQPVEVLLDTGADDSIVAGIELGSNYTPKIVGGIGGFINTKEYEDVEIKVLNKRVKATIMTGDTPINIFGRNILTALGMSLNL";
            String aliRTVIH2 = "PVAKIEPIEVRLKPGKDGPKLRQWPLTKEKIEALKEICEKTEREGQLEEAPPTNPYNTPTFAIKKKDKNKWRMLIDFRELNKVTQDFTEIQLGIPHPAGLAKKRRITVLDVGDAYFSIPLHESFRQYTAFTLPSVNNAEPGKRYIYKVLPQGWKGSPAIFQHTMRQILEPFRKANQDVILIQYMDDILIASDRTDLEHDKVVLQLKELLNGLGFSTPDEKFQKDPPYKWMGYGLWPTKWKLQKIQLPQKEVWTVNDIQKHVGVLNWAAQIYPGIKTKHLCRLIRGKMTLTEGVQWTELAEAELEENRIILSQEQEGHYYQEEKELEATVQKDQDNQWTYKIHQGEKILKVEKYAKMKNTHTNGVRLLAQVVQKIGKEALVIWGRIPRFHLPVERETWEQWWDDYWQVTWIPDWDFVSTPPLVRLAFNLVKDPILGAETF";
            String aliINVIH2 = "FLEKIEPAQEEHEKYHSNVKELSHKFGLPNLVARQIVNTCAQCQQKGEAIHGQVNAELGTWQMDCTHLEGKVIIIAVHVASGFIEAEVIPQESGRQTALFLLKLASRWPITHLHTDSGVNFTSQEVKMVAWWVGIEQSFGVPYNPQSQGVVEAMNHHLKNQISRIREQANTVETIVLMAVHCMNFKRRGGIGDMTPAERLINMISTEQEIQFLQTKNLKFKNFPVYYREGRDQLWKGPGELLWKGDGAVIVKVGTDIKVVPRRKAKIIRDYGGRQELDSGPHLEGAREDGEVA*";
                        
            boolean encabezadosTabla = true;
        
            String retornoPolimorf;
            boolean comaPolimorf_;
            String comaPolimorf;
        
            String archivo;
        
            String ficheroSalida = salida;
            String br;
                        
            String tituloReal = null;
            int contadorBr;

            final File carpeta = new File(entrada);
		
            String ficheros[];
		
            ficheros = carpeta.list();
            Arrays.sort(ficheros);
        
            try (FileWriter ficheroSalida1 = new FileWriter(ficheroSalida)) {
                
                ficheroSalida1.write("<!DOCTYPE html>");
                ficheroSalida1.write("<html lang = 'es'>");
                ficheroSalida1.write("<head>");
                ficheroSalida1.write("<meta charset = 'UTF-8'/>");
                ficheroSalida1.write(Menu_Lateral.head());
                ficheroSalida1.write(Estilo_Tablas_html.cssTabla());
                ficheroSalida1.write("</head>\n");
                ficheroSalida1.write("<body>\n");
                
                String wt;
                
                String retornoPolimorfNumerico;
                
                for(int i = 0; i< ficheros.length; i++) {
                    
                    if(ficheros[i].equals("")) {
                        
                        break;
                        
                    }
                    
                    archivo = entrada + "/" + ficheros[i];
                    
                    wt = "";
                    
                    contadorBr = 1;
                    
                    if(virus == 1) {
                        
                        switch (seleccionProteina) {
                            
                            case 1:
                                
                                if(idioma == 1){
                                    
                                    tituloReal = "Tabla Resumen Otras Mutaciones Pol Proteasa VIH-1 > 75%";
                                    
                                }else{
                                    
                                    tituloReal = "Summary Table Other Pol Mutations Protease HIV-1 > 75%";
                                    
                                }
                                
                                wt = hxb2PRVIH1;
                                break;
                                
                            case 2:
                                
                                if(idioma == 1){
                                    
                                    tituloReal = "Tabla Resumen Otras Mutaciones Pol Retrotranscriptasa VIH-1 > 75%";
                                    
                                }else{
                                    
                                    tituloReal = "Summary Table Other Pol Mutations Retrotranscriptase HIV-1 > 75%";
                                    
                                }
                                
                                wt = hxb2RTVIH1;
                                break;
                                
                            case 3:
                                
                                if(idioma == 1){
                                    
                                    tituloReal = "Tabla Resumen Otras Mutaciones Pol Integrasa VIH-1 > 75%";
                                    
                                }else{
                                    
                                    tituloReal = "Summary Table Other Pol Mutations Integrase HIV-1 > 75%";
                                    
                                }
                                
                                wt = hxb2INVIH1;   
                                break;
                                
                        }
                        
                    }else if(virus == 2) {
                        
                        switch (seleccionProteina) {
                            
                            case 1:
                                
                                if(idioma == 1){
                                    
                                    tituloReal = "Tabla Resumen Otras Mutaciones Pol Proteasa VIH-2 > 75%";
                                    
                                }else{
                                    
                                    tituloReal = "Summary Table Other Pol Mutations Protease HIV-2 > 75%";
                                    
                                }
                                
                                wt = aliPRVIH2;
                                break;
                                
                            case 2:
                                
                                if(idioma == 1){   
                                    
                                    tituloReal = "Tabla Resumen Otras Mutaciones Pol Retrotranscriptasa VIH-2 > 75%";
                                    
                                }else{
                                    
                                    tituloReal = "Summary Table Other Pol Mutations Retrotranscriptase HIV-2 > 75%";
                                    
                                }
                                
                                wt = aliRTVIH2;
                                break;
                                
                            case 3:
                                
                                if(idioma == 1){
                                    
                                    tituloReal = "Tabla Resumen Otras Mutaciones Pol Integrasa VIH-2 > 75%";
                                    
                                }else{
                                    
                                    tituloReal = "Summary Table Other Pol Mutations Integrase HIV-2 > 75%";
                                    
                                }
                                
                                wt = aliINVIH2;
                                break;
                                
                        }
                        
                    }
                    
                    if(!jTextField53.getText().equals("")){
                        
                        wt = jTextField53.getText();
                        
                    }
                    
                    comaPolimorf_ = false;
                    
                    if(encabezadosTabla == true) {
                        
                        ficheroSalida1.write("<table style = 'border-collapse: collapse;'>");
                        
                        ficheroSalida1.write("<tr>");
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write("<td colspan = '3'>" + tituloReal + "</td>");
                            
                        }else{
                            
                            ficheroSalida1.write("<td colspan = '3'>" + tituloReal + "</td>");
                            
                        }
                        
                        ficheroSalida1.write("</tr>");
                        
                        ficheroSalida1.write("<tr>");
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write("<td colspan = '1'; rowspan = '1'>Archivo</td>");
                            ficheroSalida1.write("<td colspan = '1'>Residuos</td>");
                            ficheroSalida1.write("<td colspan = '1'>Secuencias Totales</td>");   
                            
                        }else{
                            
                            ficheroSalida1.write("<td colspan = '1'; rowspan = '1'>File</td>");
                            ficheroSalida1.write("<td colspan = '1'>Residues</td>");
                            ficheroSalida1.write("<td colspan = '1'>Total Sequences</td>");
                            
                        }
                        
                        ficheroSalida1.write("</tr>");
                        
                        ficheroSalida1.write("<tr>");
                        
                        ficheroSalida1.write("</tr>");
                        
                        encabezadosTabla = false;   
                        
                    }
                    
                    ficheroSalida1.write("<tr>");
                    
                    ficheroSalida1.write("<td colspan = '1'>"+ ficheros[i] + "</td>");
                    ficheroSalida1.write("<td colspan = '1'>");
                    
                    retornoPolimorfNumerico = "";
                    
                    for(int contadorProt = 0; contadorProt < wt.length() ; contadorProt++) {
                        
                        retornoPolimorf = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, (contadorProt +1), wt.toCharArray()[contadorProt], "←", 3, 2, 0, "", 0, 0, 0, 0, 0, 'ł');
                        
                        retornoPolimorfNumerico = String.valueOf(Conteo_Interno.cargarConteoInterno(archivo));
                        
                        if(jComboBox31.getSelectedIndex() == 3 && retornoPolimorf.contains("#F3A031")){
                            
                            continue;
                            
                        }
                        
                        if(!retornoPolimorf.equals("")) {
                            
                            if(comaPolimorf_ == true){
                                
                                comaPolimorf = ",";
                                
                            }else{
                                
                                comaPolimorf = "";
                                
                            }   
                            
                            if(contadorBr % 4 == 0){
                                
                                br = "<br>";
                                
                            }else{
                                
                                br = "";
                                
                            }
                            
                            ficheroSalida1.write(comaPolimorf + br + " " + wt.toCharArray()[contadorProt] + (contadorProt+1) + retornoPolimorf);
                            
                            comaPolimorf_ = true;
                            
                            contadorBr++;
                            
                        }
                        
                    }
                    
                    ficheroSalida1.write("<td colspan = '1'>");
                    
                    ficheroSalida1.write(retornoPolimorfNumerico);
                    
                    ficheroSalida1.write("</td>");
                    
                    ficheroSalida1.write("</tr>");
                    
                    if(i == 0){
                                
                        if(idioma == 1){
                                
                            ficheroSalida1.write(Menu_Lateral.body(tituloReal, true, false, false));
                                
                        }else{   
                                
                            ficheroSalida1.write(Menu_Lateral.body(tituloReal, true, false, false));
                                
                        }
                            
                    }
                                  
                }
            }
        
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Otras_Mutaciones_Pol_Tabla_Resumen.class.getName()).log(Level.SEVERE, null, e);

            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
        
        }
        
    }
    
}
