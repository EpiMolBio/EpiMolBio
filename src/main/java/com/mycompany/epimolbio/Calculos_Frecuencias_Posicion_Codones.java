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
import java.io.BufferedReader;
import java.io.FileReader;
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

public class Calculos_Frecuencias_Posicion_Codones {

    //Calcula los codones posibles y las traducciones de estos en una posición dada.
    //Calculates the possible codons and their translations at a given position.
    
    public static String cargarCalculosFrecuenciasPosicionCodones(String archivo, int posicion, String polimorfismos, int seleccion, int color, String MDR[]) {
       		 	
        try{
                  
            boolean Entrada;
            
            String retornoMDR = "";
	
            String temp2;
						
            int contadorSecuencias;
		            
            String cadena;
            
            int contadorPosicionArrEncontrados = 0;
            
            FileReader f = new FileReader(archivo);
            
            BufferedReader b = new BufferedReader(f);
        
            String temp = "";
            String codonesEncontrados[] = new String[1000];
            
            int contadorEncontrados[] = new int[1000];
            
            for(int i = 0; i < contadorEncontrados.length; i++){
                
                contadorEncontrados[i] = 0;
                codonesEncontrados[i] = "ł";
              
            }
        
            contadorSecuencias = 0;
			                        
            while((cadena = b.readLine()) != null) {
                
                cadena = cadena.toUpperCase();
        			
                if(cadena.toCharArray()[0] == '>') {				
                    
                    if(temp.length() >= (posicion + 2) && temp.length() != 0) {
                    	
                        contadorSecuencias++;
                        
                        Entrada = true;
                        
                        temp2 = "";
                    	
                        temp2 = temp2.concat(String.valueOf(temp.toCharArray()[posicion-1]) + String.valueOf(temp.toCharArray()[posicion]) + String.valueOf(temp.toCharArray()[posicion + 1])).toUpperCase();
                        
                        temp2 = temp2.replace("N", "?");
                        temp2 = temp2.replace("X", "?");
                    	
                        if(temp2.toCharArray()[0] == '-' || temp2.toCharArray()[0] == '?' || temp2.toCharArray()[1] == '-' || temp2.toCharArray()[1] == '?'
         			|| temp2.toCharArray()[2] == '-' || temp2.toCharArray()[2] == '?') {
                    			
                            if(contadorSecuencias != 0) {
                   		    	
                                contadorSecuencias--;
                    		    	
                            }
                    		
                            temp = "";
                    			
                            continue;
                    		
                        }
                    	
                        for (String codonesEncontrado : codonesEncontrados) {
                            
                            for (String codonesEncontrado1 : codonesEncontrados) {
                                
                                if (temp2.equals(codonesEncontrado1)) {
                                    
                                    Entrada = false;
                                    break;
                                    
                                }
                            }
                           
                            if(Entrada){
                                
                                codonesEncontrados[contadorPosicionArrEncontrados] = temp2;
                                                                
                                contadorPosicionArrEncontrados++;
                                Entrada = false;
                                
                            }
                        }
                        
                        for(int i = 0; i < contadorEncontrados.length; i++){
                            
                            if(temp2.equals(codonesEncontrados[i])){
                                                                
                                contadorEncontrados[i]++;
                                 
                            }
                            
                        }
							
                        temp = "";
						
                    }else{
                        
                        temp = "";
                        
                    }
                        
                }else {
                    
                    temp += cadena;
                    temp = temp.replace("N", "?");
                    
                }

            }
			            
            if(temp.length() >= (posicion + 2) && temp.length() != 0) {
            	            
                boolean EntradaAccesoria = true;
            	
                temp2 = "";
            	
                temp2 = temp2.concat(String.valueOf(temp.toCharArray()[posicion-1]) + String.valueOf(temp.toCharArray()[posicion]) + String.valueOf(temp.toCharArray()[posicion + 1])).toUpperCase();
            	
                temp2 = temp2.replace("N", "?");
                temp2 = temp2.replace("X", "?");
                
                if(temp2.toCharArray()[0] != '-' && temp2.toCharArray()[0] != '?' && temp2.toCharArray()[1] != '-' && temp2.toCharArray()[1] != '?'
                    && temp2.toCharArray()[2] != '-' && temp2.toCharArray()[2] != '?') {
            		
                    contadorSecuencias++;
            		
                    for (String codonesEncontrado : codonesEncontrados) {
                            
                        for (String codonesEncontrado1 : codonesEncontrados) {
                                    
                            if (temp2.equals(codonesEncontrado1)) {
                                        
                                EntradaAccesoria = false;
                                break;
                                        
                            }
                                    
                        }
                            
                        if(EntradaAccesoria){
                                
                            codonesEncontrados[contadorPosicionArrEncontrados] = temp2;
                                
                            contadorPosicionArrEncontrados++;
                            EntradaAccesoria = false;
                                
                        }
                    }
            
                    for(int i = 0; i < contadorEncontrados.length; i++){
                
                        if(temp2.equals(codonesEncontrados[i])){
                                        
                            contadorEncontrados[i]++;
                     
                        }
                
                    }
                }
            }
          			            
            String retorno = "";
            
            boolean asterisco = false;
            
            for(int i = 0; i < codonesEncontrados.length; i++){
                                
                if(codonesEncontrados[i].equals("ł")){
                        
                    break;
                }
                
                if(codonesEncontrados[i].equals(polimorfismos) && seleccion == 2){
                       
                    continue;
                   
                }
                               
                if((contadorEncontrados[i] * 100.0)/contadorSecuencias <= 75.0 && color == 2) {
                	                   
                    continue;
                	
                }
                
                if(seleccion == 1 || seleccion == 2) {
                	         
                    retorno += " " + "<a Style = 'color:black';> "+ traduccion(codonesEncontrados[i]) + "</a>"+ "[" + codonesEncontrados[i] + "(" +
                        calculoFrecuencia(contadorEncontrados[i], contadorSecuencias, asterisco, 1) + ")], "; 
                
                }else if(seleccion == 3){
                    
                    retorno += " " + "<a Style = 'color:black';> "+ traduccion(codonesEncontrados[i]) + "</a>"+ "[" + codonesEncontrados[i] + "(" + 
                        calculoFrecuencia(contadorEncontrados[i], contadorSecuencias, asterisco, 2) + ")], ";
                    
                }
                
            }
             
            if(seleccion == 15){
               
                String asteriscoCadena;
                String colorCodones = "";
                  
                for(int i = 0; i < codonesEncontrados.length; i++){
                    
                    asteriscoCadena = "";             
                    
                    if(codonesEncontrados[i].equals("ł")){
                        
                        break;
                        
                    }
                    
                    for (String MDR1 : MDR) {
                        
                        if (MDR1.equals(codonesEncontrados[i].toUpperCase())) {
                            
                            asteriscoCadena = "*";
                            break;
                        
                        }
                    }
                    
                    if(((contadorEncontrados[i] * 100.0)/contadorSecuencias) == 100.0){
                    
                        colorCodones = "#834580";
                        
                    }else if(((contadorEncontrados[i] * 100.0)/contadorSecuencias) < 10.0){
                        
                        colorCodones = "#3E8249";
                        
                    }else if(((contadorEncontrados[i] * 100.0)/contadorSecuencias) >= 90.0){
                        
                        colorCodones = "#BF3D27";
                        
                    }else if(((contadorEncontrados[i] * 100.0)/contadorSecuencias)< 90.0 && ((contadorEncontrados[i] * 100.0)/contadorSecuencias) > 75.0){
                        
                        colorCodones = "#F3A031";
                        
                    }else if(((contadorEncontrados[i] * 100.0)/contadorSecuencias) <=75.0 && ((contadorEncontrados[i] * 100.0)/contadorSecuencias)>50.0){
                        
                        colorCodones = "#E0D900";
                    
                    }else if(((contadorEncontrados[i] * 100.0)/contadorSecuencias)<= 50.0 && ((contadorEncontrados[i] * 100.0)/contadorSecuencias)>= 10.0){
                        
                        colorCodones = "#2E75B6";
                        
                    }
                    
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                    symbols.setDecimalSeparator('.');
                    
                    DecimalFormat df = new DecimalFormat("0.000", symbols);
                    df.setDecimalSeparatorAlwaysShown(true);
                       
                    retornoMDR = retornoMDR +("<a Style = 'color:black';>" + traduccion(codonesEncontrados[i]) + "<a>" + "[" + codonesEncontrados[i] + "<a Style = 'color:#BF3D27;'>" + asteriscoCadena + "</a>" + "<a>(</a>" + "<strong><a Style = 'color:" + colorCodones + ";'>" + df.format((contadorEncontrados[i] * 100.0)/contadorSecuencias) + "%</a></strong>)], ");
                    
                }
               
                retornoMDR = retornoMDR + ("<strong>" + contadorSecuencias + "</strong></br></br>");

            }
            
            b.close();
            
            if(seleccion != 15){
                    
                retorno += contadorSecuencias;
                    
            }
            	
            if(seleccion != 15){
            
                return retorno + " </br>";
                    
            }else{
                    
                return retornoMDR;
                
            }
            
        }catch(IOException e){
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Calculos_Frecuencias_Posicion_Codones.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
                
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                    
            Terminar_Hilos.cargarTerminarHilos();
                
            return "";
                
        }
    }
	
    //Traduce un codón dado.
    //Translates a given codon.
    
    public static String traduccion(String codon) {
            
        try{
            
            String aa;
		
            codon = codon.toUpperCase();
		
            switch (codon) {
                    
                case "TTT":
                case "TTC":
                        
                    aa = "F";
                    break;
                        
                case "TTA":
                case "TTG":
                case "CTT":
                case "CTC":
                case "CTA":
                case "CTG":
                        
                    aa = "L";
                    break;
                        
                case "ATT":
                case "ATC":
                case "ATA":
                        
                    aa = "I";
                    break;
                        
                case "ATG":
                        
                    aa = "M";
                    break;
                       
                case "GTT":
                case "GTC":
                case "GTA":
                case "GTG":
                        
                    aa = "V";
                    break;
                       
                case "TCT":
                case "TCC":
                case "TCA":
                case "TCG":
                        
                    aa = "S";
                    break;
                        
                case "CCT":
                case "CCC":
                case "CCA":
                case "CCG":
                        
                    aa = "P";
                    break;
                        
                case "ACT":
                case "ACC":
                case "ACA":
                case "ACG":
                        
                    aa = "T";
                    break;
                        
                case "GCT":
                case "GCC":
                case "GCA":
                case "GCG":
                       
                    aa = "A";
                    break;
                      
                case "TAT":
                case "TAC":
                        
                    aa = "Y";
                    break;
                        
                case "TAA":
                case "TAG":
                case "TGA":
                        
                    aa = "*";
                    break;
                        
                case "CAT":
                case "CAC":
                        
                    aa = "H";
                    break;
                        
                case "CAA":
                case "CAG":
                        
                    aa = "Q";
                    break;
                        
                case "AAT":
                case "AAC":
                        
                    aa = "N";
                    break;
                      
                case "AAA":
                case "AAG":
                       
                    aa = "K";
                    break;
                        
                case "GAT":
                case "GAC":
                        
                    aa = "D";
                    break;
                        
                case "GAA":
                case "GAG":
                        
                    aa = "E";
                    break;
                        
                case "TGT":
                case "TGC":
                        
                    aa = "C";
                    break;
                        
                case "TGG":
                        
                    aa = "W";
                    break;
                        
                case "CGT":
                case "CGC":
                case "CGA":
                case "CGG":
                        
                    aa = "R";
                    break;
                        
                case "AGT":
                case "AGC":
                        
                    aa = "S";
                    break;
                      
                case "AGA":
                case "AGG":
                        
                    aa = "R";
                    break;
                        
                case "GGT":
                case "GGC":
                case "GGA":
                case "GGG":
                        
                    aa = "G";
                    break;
                       
                case "___":
                        
                    aa = "_";
                    break;
                        
                default:
                        
                    aa = "?";
                    break;
                        
            }
		
            return aa;
                
        }catch(Exception e){
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Calculos_Frecuencias_Posicion_Codones.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
                
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Terminar_Hilos.cargarTerminarHilos();
                
            return "";
                
        }
    }
	    
    public static String calculoFrecuencia(double totalAciertos, double totalSecuencias, boolean asterisco, int formatoNumerico){
		
        try{
                
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
                
            DecimalFormat df;
		
            if(formatoNumerico == 1){
                    
                df = new DecimalFormat("0.000", symbols);
                df.setDecimalSeparatorAlwaysShown(true);
                    
            }else{
                    
                df = new DecimalFormat("0.00000000");
                    
            }
                
            String retorno = df.format((totalAciertos * 100.0)/totalSecuencias);
		
            String ast = "";
		
            if(asterisco == true) {
			
		ast = "*";
			
            }
		
            if(((totalAciertos * 100.0)/totalSecuencias) == 100.0){
                    
                return "<a Style = 'color:#834580';><strong>" + retorno  + "%" + "<a Style = 'color:black';>" + ast + "</a>" + "</strong></a>";
                
            }else if(((totalAciertos * 100.0)/totalSecuencias) < 10.0){
			
                return "<a Style = 'color:#3E8249';><strong>" + retorno  + "%" + "<a Style = 'color:black';>" + ast + "</a>" + "</strong></a>";
			
            }else if(((totalAciertos * 100.0)/totalSecuencias) >= 90.0) {
			
                return "<a Style = 'color:#BF3D27';><strong>" + retorno  + "%" + "<a Style = 'color:black';>" + ast + "</a>" + "</strong></a>";
			
            }else if(((totalAciertos * 100.0)/totalSecuencias) < 90.0 && ((totalAciertos * 100.0)/totalSecuencias) > 75.0) {
			
                return "<a Style = 'color:#F3A031';><strong>" + retorno  + "%" + "<a Style = 'color:black';>" + ast + "</a>" + "</strong></a>";
		
            }else if(((totalAciertos * 100.0)/totalSecuencias) <= 75.0 && ((totalAciertos * 100.0)/totalSecuencias) > 50.0) {
			
                return "<a Style = 'color:#E0D900';><strong>" + retorno  + "%" + "<a Style = 'color:black';>" + ast + "</a>" + "</strong></a>";
			
            }else if(((totalAciertos * 100.0)/totalSecuencias) <= 50.0 && ((totalAciertos * 100.0)/totalSecuencias) >= 10.0) {
			
                return "<a Style = 'color:#2E75B6';><strong>" + retorno  + "%" + "<a Style = 'color:black';>" + ast + "</a>" + "</strong></a>";
			
            }else {
                    
                return "";
                        
            }   
                
        }catch(Exception e){
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
              
            Logger.getLogger(Calculos_Frecuencias_Posicion_Codones.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
                
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Terminar_Hilos.cargarTerminarHilos();
                
            return "";
                
        }
		
    }
}
