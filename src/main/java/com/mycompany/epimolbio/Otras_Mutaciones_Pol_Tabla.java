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

public class Otras_Mutaciones_Pol_Tabla {
    
    /*Calcula la frecuencia de aparición de mutaciones en el pool de secuencias introducidas del gen Pol, valiendose de la clase Calculos_Frecuencias_Posicion.
    El resultado es una tabla en formato .html*/
    
    /*Calculates the frequency of mutations in the input sequence pool for the Pol gene, using the Calculos_Frecuencias_Posicion class. 
    The result is a table in .html format.*/
    
    public static void cargarOtrasMutacionesPolTabla(String entrada, String salida, int virus, int proteina){
        	
        try{
            
            String hxb2PRVIH1 = "PQVTLWQRPLVTIKIGGQLKEALLDTGADDTVLEEMSLPGRWKPKMIGGIGGFIKVRQYDQILIEICGHKAIGTVLVGPTPVNIIGRNLLTQIGCTLNF";
            String hxb2RTVIH1 = "PISPIETVPVKLKPGMDGPKVKQWPLTEEKIKALVEICTEMEKEGKISKIGPENPYNTPVFAIKKKDSTKWRKLVDFRELNKRTQDFWEVQLGIPHPAGLKKKKSVTVLDVGDAYFSVPLDEDFRKYTAFTIPSINNETPGIRYQYNVLPQGWKGSPAIFQSSMTKILEPFRKQNPDIVIYQYMDDLYVGSDLEIGQHRTKIEELRQHLLRWGLTTPDKKHQKEPPFLWMGYELHPDKWTVQPIVLPEKDSWTVNDIQKLVGKLNWASQIYPGIKVRQLCKLLRGTKALTEVIPLTEEAELELAENREILKEPVHGVYYDPSKDLIAEIQKQGQGQWTYQIYQEPFKNLKTGKYARMRGAHTNDVKQLTEAVQKITTESIVIWGKTPKFKLPIQKETWETWWTEYWQATWIPEWEFVNTPPLVKLWYQLEKEPIVGAETF";
            String hxb2INVIH1 = "FLDGIDKAQDEHEKYHSNWRAMASDFNLPPVVAKEIVASCDKCQLKGEAMHGQVDCSPGIWQLDCTHLEGKVILVAVHVASGYIEAEVIPAETGQETAYFLLKLAGRWPVKTIHTDNGSNFTGATVRAACWWAGIKQEFGIPYNPQSQGVVESMNKELKKIIGQVRDQAEHLKTAVQMAVFIHNFKRKGGIGGYSAGERIVDIIATDIQTKELQKQITKIQNFRVYYRDSRNPLWKGPAKLLWKGEGAVVIQDNSDIKVVPRRKAKIIRDYGKQMAGDDCVASRQDED*";
		
            String aliPRVIH2 = "PQFSLWKRPVVTAYIEGQPVEVLLDTGADDSIVAGIELGSNYTPKIVGGIGGFINTKEYEDVEIKVLNKRVKATIMTGDTPINIFGRNILTALGMSLNL";
            String aliRTVIH2 = "PVAKIEPIEVRLKPGKDGPKLRQWPLTKEKIEALKEICEKTEREGQLEEAPPTNPYNTPTFAIKKKDKNKWRMLIDFRELNKVTQDFTEIQLGIPHPAGLAKKRRITVLDVGDAYFSIPLHESFRQYTAFTLPSVNNAEPGKRYIYKVLPQGWKGSPAIFQHTMRQILEPFRKANQDVILIQYMDDILIASDRTDLEHDKVVLQLKELLNGLGFSTPDEKFQKDPPYKWMGYGLWPTKWKLQKIQLPQKEVWTVNDIQKHVGVLNWAAQIYPGIKTKHLCRLIRGKMTLTEGVQWTELAEAELEENRIILSQEQEGHYYQEEKELEATVQKDQDNQWTYKIHQGEKILKVEKYAKMKNTHTNGVRLLAQVVQKIGKEALVIWGRIPRFHLPVERETWEQWWDDYWQVTWIPDWDFVSTPPLVRLAFNLVKDPILGAETF";
            String aliINVIH2 = "FLEKIEPAQEEHEKYHSNVKELSHKFGLPNLVARQIVNTCAQCQQKGEAIHGQVNAELGTWQMDCTHLEGKVIIIAVHVASGFIEAEVIPQESGRQTALFLLKLASRWPITHLHTDSGVNFTSQEVKMVAWWVGIEQSFGVPYNPQSQGVVEAMNHHLKNQISRIREQANTVETIVLMAVHCMNFKRRGGIGDMTPAERLINMISTEQEIQFLQTKNLKFKNFPVYYREGRDQLWKGPGELLWKGDGAVIVKVGTDIKVVPRRKAKIIRDYGGRQELDSGPHLEGAREDGEVA*";
            
            entrada += "/";
            String archivo;
            String retorno;
            
            final File carpeta = new File(entrada);
	
            String ficheros[];
	
            ficheros = carpeta.list();
            Arrays.sort(ficheros);
            
            boolean botonSalida = false;
            
            String tituloProt = null;
            String protSeleccionable = null;
            
            int tamanoProt = 0;
            
            String ficheroSalida = salida;
                        
            try (FileWriter ficheroSalida1 = new FileWriter(ficheroSalida)) {
                
                ficheroSalida1.write("<!DOCTYPE html>");
                ficheroSalida1.write("<html lang = 'es'>");
                ficheroSalida1.write("<head>");
                ficheroSalida1.write("<meta charset = 'UTF-8'/>");
                ficheroSalida1.write(Menu_Lateral.head());
                ficheroSalida1.write(Estilo_Tablas_html.cssTabla());
                ficheroSalida1.write("</head>\n");
                ficheroSalida1.write("<body>\n");
                
                if(virus == 1) {
                    
                    switch (proteina) {
                        case 1:
                            
                            tamanoProt = hxb2PRVIH1.length();
                            
                            if(idioma == 1){
                                
                                tituloProt = "Tabla Otras Mutaciones Pol Proteasa VIH-1 > 75%";
                                
                            }else{
                                
                                tituloProt = "Table Other Pol Mutations Protease HIV-1 > 75%";
                                
                            }
                            
                            protSeleccionable = hxb2PRVIH1;
                            break;
                            
                        case 2:
                            
                            tamanoProt = hxb2RTVIH1.length();
                            
                            if(idioma == 1){
                                
                                tituloProt = "Tabla Otras Mutaciones Pol Retrotranscriptasa VIH-1 > 75%";
                                
                            }else{
                                
                                tituloProt = "Table Other Pol Mutations Retrotranscriptase HIV-1 > 75%";
                                
                            }
                            
                            protSeleccionable = hxb2RTVIH1;
                            break;
                            
                        case 3:
                            
                            tamanoProt = hxb2INVIH1.length();
                            
                            if(idioma == 1){
                                
                                tituloProt = "Tabla Otras Mutaciones Pol Integrasa VIH-1 > 75%";
                                
                            }else{
                                
                                tituloProt = "Table Other Pol Mutations Integrase HIV-1 > 75%";
                                
                            }
                            
                            protSeleccionable = hxb2INVIH1;
                            break;
                            
                    }
                    
                }else if(virus == 2) {
                    
                    switch (proteina) {
                        
                        case 1:
                            
                            tamanoProt = aliPRVIH2.length();
                            
                            if(idioma == 1){
                                
                                tituloProt = "Tabla Otras Mutaciones Pol Proteasa VIH-2 > 75%";
                                
                            }else{
                                
                                tituloProt = "Table Other Pol Mutations Protease HIV-2 > 75%";
                                
                            }
                            
                            protSeleccionable = aliPRVIH2;
                            break;
                            
                        case 2:
                            
                            tamanoProt = aliRTVIH2.length();
                            
                            if(idioma == 1){
                                
                                tituloProt = "Tabla Otras Mutaciones Pol Retrotranscriptasa VIH-2 > 75%";
                                
                            }else{
                                
                                tituloProt = "Table Other Pol Mutations Retrotranscriptase HIV-2 > 75%";
                                
                            }
                            
                            protSeleccionable = aliRTVIH2;
                            break;
                            
                        case 3:
                            
                            tamanoProt = aliINVIH2.length();
                            
                            if(idioma == 1){
                                
                                tituloProt = "Tabla Otras Mutaciones Pol Integrasa VIH-2 > 75%";
                                
                            }else{
                                
                                tituloProt = "Table Other Pol Mutations Integrase HIV-2 > 75%";
                                
                            }
                            
                            protSeleccionable = aliINVIH2;
                            break;
                            
                    }
                    
                }
                
                for(int i = 0; i< ficheros.length; i++) {
                    
                    if(ficheros[i].equals("")) {
                        
                        break;
                        
                    }
                    
                    archivo = entrada + ficheros[i];
                    
                    if(botonSalida == false) {
                        
                        ficheroSalida1.write("<table>");
                        
                        ficheroSalida1.write("<tr>");
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write("<td rowspan = '2'>Archivo</td>");
                            
                        }else{
                            
                            ficheroSalida1.write("<td rowspan = '2'>File</td>");
                            
                        }
                        
                        ficheroSalida1.write("<td colspan = '" + tamanoProt +"'>" + tituloProt + "</td>");
                        
                        ficheroSalida1.write("</tr>");
                        
                        ficheroSalida1.write("<tr>");
                        
                        for(int c = 0; c < tamanoProt; c++) {
                            
                            ficheroSalida1.write("<td colspan = '1'>"+ protSeleccionable.toCharArray()[c] + (c+1) +"</td>");
                            
                        }
                        
                        ficheroSalida1.write("</tr>");
                        
                        botonSalida = true;
                        
                    }
                    
                    ficheroSalida1.write("<tr>");
                    
                    ficheroSalida1.write("<td>" + ficheros[i] +"</td>");
                    
                    for(int x = 0; x < tamanoProt; x++) {
                        
                        retorno = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(archivo, (x + 1), protSeleccionable.toCharArray()[x], "←", 4, 2, 0, "", 0, 0, 0, 0, 1, 'ł');
                        
                        if(retorno.toCharArray()[0] == 'p' && retorno.toCharArray()[0] != protSeleccionable.toCharArray()[x]){
                            
                            ficheroSalida1.write("<td style = 'background-color: #834580;'><strong>"+
                                    retorno.toCharArray()[1] +"</strong></td>");
                            
                        }else if(retorno.toCharArray()[0] == 'r' && retorno.toCharArray()[0] != protSeleccionable.toCharArray()[x]) {
                            
                            ficheroSalida1.write("<td style = 'background-color: #BF3D27;'><strong>"+
                                    retorno.toCharArray()[1] +"</strong></td>");
                            
                        }else if(retorno.toCharArray()[0] == 'n' && retorno.toCharArray()[0] != protSeleccionable.toCharArray()[x]) {
                            
                            ficheroSalida1.write("<td style = 'background-color: #F3A031;'><strong>"+
                                    retorno.toCharArray()[1] +"</strong></td>");
                            
                        }else if(retorno.toCharArray()[0] == 'b' && retorno.toCharArray()[0] != protSeleccionable.toCharArray()[x]) {
                            
                            ficheroSalida1.write("<td style = 'background-color: #2E75B6;'><strong>"+
                                    retorno.toCharArray()[1] +"</strong></td>");
                            
                        }else if(retorno.toCharArray()[0] == 'a' && retorno.toCharArray()[0] != protSeleccionable.toCharArray()[x]) {
                            
                            ficheroSalida1.write("<td style = 'background-color: #E0D900;'><strong>"+
                                    retorno.toCharArray()[1] +"</strong></td>");
                            
                        }else if(retorno.toCharArray()[0] == 'v' && retorno.toCharArray()[0] != protSeleccionable.toCharArray()[x]) {
                            
                            ficheroSalida1.write("<td style = 'background-color: #3E8249;'><strong>"+
                                    retorno.toCharArray()[1] +"</strong></td>");
                            
                        }else {
                            
                            ficheroSalida1.write("<td>  </td>");
                            
                        }
                        
                    }
                    
                    ficheroSalida1.write("</tr>");
                    
                    if(i == 0){
                        
                        if(idioma == 1){
                            
                            ficheroSalida1.write(Menu_Lateral.body(tituloProt, true, false, false));
                            
                        }else{
                            
                            ficheroSalida1.write(Menu_Lateral.body(tituloProt, true, false, false));
                            
                        }
                    }
                }
            }
            
        }catch(IOException e){
        
            Logger.getLogger(Otras_Mutaciones_Pol_Tabla.class.getName()).log(Level.SEVERE, null, e);
                    
            btn_presionado = false;
                    
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                    
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                   
            Terminar_Hilos.cargarTerminarHilos();
                
        }
            
    }
}
