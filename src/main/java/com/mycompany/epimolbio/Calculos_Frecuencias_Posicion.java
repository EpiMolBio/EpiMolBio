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

public class Calculos_Frecuencias_Posicion {
    
    //Calcula la frecuencia de los aminoácidos o nucleótidos en una posición dada en el pool de secuencias introducidas.
    
    public static String cargarCalculosFrecuenciasPosicion(String archivo, int posicion, char evaluador, String polimorfismos, int seleccion, 
        int color, int proteina, String secuenciaProblemaSimilitud, int seleccionAdicional, int limiteInfSecuenciaProblemaSimilitud, int limiteSupSecuenciaProblemaSimilitud, 
        int coloresConsenso, int valor_100, char excepcion){
       		 	
        try{
                
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
                
            boolean Entrada;
                
            char temp2;
            
            int contadorEncontrados3[] = new int[100];
            char encontrados3[] = new char[100];
			
            int contadorSimilitud = 0;
            int contadorTotalesSimilitud = 0;
            boolean retornoSimilitud;
			
            for(int i = 0; i < polimorfismos.length(); i++) {
				
                encontrados3[i] = 'ł';
                    
            }
			
            int contadorOrdenar = 0;
			
            int contadorSecuencias;
		            
            String cadena;
            
            int contadorPosicionArrEncontrados = 0;
            
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
        
            String temp = "";
            char aminoacidosEncontrados[] = new char[100];
            
            int contadorEncontrados[] = new int[100];
            
            for(int i = 0; i < contadorEncontrados.length; i++){
                
                contadorEncontrados[i] = 0;
                aminoacidosEncontrados[i] = 'ł';
                
            }
        
            contadorSecuencias = 0;
			                        
            while((cadena = b.readLine()) != null) {
        			
                cadena = cadena.toUpperCase();
                
                if(cadena.toCharArray()[0] == '>') {				
                    
                    if(temp.length() >= posicion && temp.length() != 0) {
                    	                    	
                        if(seleccion == 6) {
                    		
                            retornoSimilitud = similitud(temp.toUpperCase(), secuenciaProblemaSimilitud, limiteInfSecuenciaProblemaSimilitud, limiteSupSecuenciaProblemaSimilitud);
                    		
                            if(retornoSimilitud == true) {
                    		
                    		contadorSimilitud++;
                    			
                            }
                            
                            contadorTotalesSimilitud++;
                    	
                        }
                    					
                        contadorSecuencias++;
                        
                        Entrada = true;
                                            	
                    	temp2 = temp.toCharArray()[posicion-1];
                              	
                    	if(temp2 == '-' || temp2 == '?' || temp2 == excepcion) {
                    			
                            if(contadorSecuencias != 0) {
                    		    	
                    		contadorSecuencias--;
                    		    	
                            }
                    		
                            temp = "";
                    			
                            continue;
                    		
                    	}
                                                               
                        for(int x = 0; x < aminoacidosEncontrados.length; x++){
                                
                            if(temp.toCharArray()[posicion-1] == aminoacidosEncontrados[x]){
                                    
                                Entrada = false;
                                break;
                                    
                            }
                            
                        }
                            
                        if(Entrada){
                                
                            aminoacidosEncontrados[contadorPosicionArrEncontrados] = temp.toCharArray()[posicion-1];
                                                                
                            contadorPosicionArrEncontrados++;
                                
                        }
                            
                        for(int i = 0; i < contadorEncontrados.length; i++){
                            
                            if(temp.toCharArray()[posicion-1] == aminoacidosEncontrados[i]){
                                                                
                                contadorEncontrados[i]++;
                                 
                            }
                            
                        }
							
                        temp = "";
						
                    }else{
                        
                        temp = "";
                        
                    }
                    
                }else {
                    
                    temp += cadena;
                
                }

            }
			            
            if(temp.length() >= posicion && temp.length() != 0) {
            	            	
            	if(seleccion == 6) {
            		
                    retornoSimilitud = similitud(temp.toUpperCase(), secuenciaProblemaSimilitud, limiteInfSecuenciaProblemaSimilitud, limiteSupSecuenciaProblemaSimilitud);
            		
                    if(retornoSimilitud == true) {
            		
                        contadorSimilitud++;
            			
                    }
                    
                    contadorTotalesSimilitud++;
                    
            	}
            	                        
            	boolean entradaAccesoria = true;
            	
            	temp2 = temp.toCharArray()[posicion-1];
                        	
            	if(temp2 != '-' && temp2 != '?' && temp2 != excepcion) {
            		
                    contadorSecuencias++;
                            
            		for(int x = 0; x < aminoacidosEncontrados.length; x++){
                	
                            if(temp.toCharArray()[posicion-1] == aminoacidosEncontrados[x]){
                        
                                entradaAccesoria = false;
            			break;
                        
                            }
            		}
                
                        if(entradaAccesoria){
                    
                            aminoacidosEncontrados[contadorPosicionArrEncontrados] = temp.toCharArray()[posicion-1];
                                        
                            contadorPosicionArrEncontrados++;
                            
                        }
                 
                        for(int i = 0; i < contadorEncontrados.length; i++){
                
                            if(temp.toCharArray()[posicion-1] == aminoacidosEncontrados[i]){
                                        
                            contadorEncontrados[i]++;
                     
                        }
              
                    }
                }
            }
			            
            String retorno = "";
            
            boolean asterisco;
            
            double calculo_;
            
            for(int i = 0; i < aminoacidosEncontrados.length; i++){
                
                asterisco = false;
                
                if(aminoacidosEncontrados[i] == 'ł'){
                        
                    break;
                
                }
                
                for(int a = 0; a < polimorfismos.length(); a++) {
                	
                    if(polimorfismos.toCharArray()[a] == aminoacidosEncontrados[i]) {
                	
                        asterisco = true;
                        break;
                		
                    }
                	
                }
                
                if((((contadorEncontrados[i] * 100.0)/contadorSecuencias <= 75.0 || aminoacidosEncontrados[i] == evaluador) && color == 2)
                    || ((contadorEncontrados[i] * 100.0)/contadorSecuencias <= 50.0 || aminoacidosEncontrados[i] == evaluador) && color == 3) {
                	
                    continue;
                	
                }
                
                if(seleccion == 1 || seleccion == 7 || seleccion == 101) {
                    
                    if(seleccion == 1 || seleccion == 7){
                    
                        retorno += aminoacidosEncontrados[i] + "(" + calculoFrecuencia(contadorEncontrados[i],
                            contadorSecuencias, asterisco, 1) + ") "; 
                    
                    }else if(seleccion == 101){
                        
                        retorno += aminoacidosEncontrados[i] + "(" + calculoFrecuencia(contadorEncontrados[i], 
                            contadorSecuencias, asterisco, 2) + ") "; 
                        
                    }
                    
                }else if(seleccion == 2 && aminoacidosEncontrados[i] == polimorfismos.toCharArray()[0]) {
                    
                    retorno += calculoFrecuenciaTabla(contadorEncontrados[i], contadorSecuencias, evaluador, aminoacidosEncontrados[i], valor_100);
                		
                }else if(seleccion == 3) {
                        
                    if(i == 0 && color != 2) {
                		
                        for(int y = 0; y < polimorfismos.length(); y++) {
                			
                            for(int z = 0; z < aminoacidosEncontrados.length; z++) {
                				
                                if(polimorfismos.toCharArray()[y] == aminoacidosEncontrados[z]) {
                					
                                    encontrados3[contadorOrdenar] = aminoacidosEncontrados[z];
                                    contadorEncontrados3[contadorOrdenar] = contadorEncontrados[z];
                					
                                    contadorOrdenar++;
                					
                                }
                            }		
                        }	
                    }
                	
                    if((encontrados3[i] == 'ł' && color != 2) || aminoacidosEncontrados[i] == 'ł' && color == 2) {
                		
                        break;
                		
                    }
                        
                    if(color != 2) {
                	
                        retorno += calculoTablaMDR(contadorEncontrados3[i], contadorSecuencias, encontrados3[i]);
                		
                    }else{
                		
                        retorno += calculoTablaMDR(contadorEncontrados[i], contadorSecuencias, aminoacidosEncontrados[i]);

                    }
                	
                }else if(seleccion == 4) {
                	
                    retorno += calculoFrecuenciaTabla(contadorEncontrados[i], contadorSecuencias, evaluador, aminoacidosEncontrados[i], valor_100);
                	
                }else if(seleccion == 40){
                        
                    retorno += calculoFrecuenciaTabla(contadorEncontrados[i], contadorSecuencias, evaluador, aminoacidosEncontrados[i], valor_100);
                    
                }else if(seleccion == 6) {
                	
                    DecimalFormat df1 = new DecimalFormat("0.000", symbols);
                    df1.setDecimalSeparatorAlwaysShown(true);
                	
                    calculo_ = (contadorSimilitud * 100.0)/contadorTotalesSimilitud;
                        
                    if(seleccionAdicional == 1){
                            
                        retorno = String.valueOf(calculo_);
                            
                    }else {
                    	
                        if(calculo_ == 100.0){
                            
                            retorno = "<a Style = 'color:#834580';><strong>" + String.valueOf(df1.format(calculo_)) + "%</strong></a>" + "<a><strong> " + contadorTotalesSimilitud +
                                "</strong></a>";
                            
                        }else if(calculo_ < 10.0){
            			
                            retorno = "<a Style = 'color:#3E8249';><strong>" + String.valueOf(df1.format(calculo_)) + "%</strong></a>" + "<a><strong> " + contadorTotalesSimilitud +
                                "</strong></a>";
            			
                        }else if(calculo_ >= 90.0) {
            			
                            retorno = "<a Style = 'color:#BF3D27';><strong>" + String.valueOf(df1.format(calculo_)) +"%</strong></a>" + "<a><strong> " + contadorTotalesSimilitud +
                                "</strong></a>";
            			
                        }else if(calculo_ < 90.0 && calculo_ > 75.0) {
            			
                            retorno = "<a Style = 'color:#F3A031';><strong>" + String.valueOf(df1.format(calculo_)) + "%</strong></a>" + "<a><strong> " + contadorTotalesSimilitud +
                                "</strong></a>";
            		
                        }else if(calculo_ <= 75.0 && calculo_ > 50.0) {
            			
                            retorno = "<a Style = 'color:#E0D900';><strong>" + String.valueOf(df1.format(calculo_)) + "%</strong></a>" + "<a><strong> " + contadorTotalesSimilitud +
                                "</strong></a>";
            			
                        }else if(calculo_ <= 50.0 && calculo_ >= 10.0) {
            			
                            retorno = "<a Style = 'color:#2E75B6';><strong>" + String.valueOf(df1.format(calculo_)) + "%</strong></a>" + "<a><strong> " + contadorTotalesSimilitud +
                                "</strong></a>";
                                
                        }
                    }
                }
            }
            
            if(seleccion == 10){
                
                char temporalesAA[];
                temporalesAA = aminoacidosEncontrados;
                                        
                double temp1;
                double tempo2;
                    
                char aa1_;
                char aa2_;
                    
                double porcentajes[] = new double[100];
                                        
                for(int p = 0; p < 100; p++){
                        
                    porcentajes[p] = -1.0;
                        
                }
           
                for(int s = 0; s < 100; s++){
                        
                    porcentajes[s] = (contadorEncontrados[s] * 100.0) / contadorSecuencias;
                        
                    if(contadorEncontrados[s] == 0.0){
                            
                        break;
                        
                    }
                        
                }
                                        
                boolean salidaSort;
                   
                while(true){
                        
                    salidaSort = true;
                        
                    for(int ba = 0; ba < 100-1; ba++){
                        
                        if(porcentajes[ba] < porcentajes[ba+1]){
                            
                            temp1 = porcentajes[ba];
                            tempo2 = porcentajes[ba+1];
                            
                            porcentajes[ba+1] = temp1;
                            porcentajes[ba] = tempo2;
                            
                            aa1_ = temporalesAA[ba];
                            aa2_ = temporalesAA[ba+1];
                          
                            temporalesAA[ba] = aa2_;
                            temporalesAA[ba + 1] = aa1_;
                             
                            break;
                                
                        }
                                         
                    }
                                                
                    for(int i = 0; i < 99; i++){
                            
                        if(porcentajes[i] < porcentajes[i+1]){
                                
                            salidaSort = false;
                            break;
                                
                        }
                            
                    }
                        
                    if(salidaSort == true){
                            
                        break;
                            
                    }        
                
                }
                    
                char consenso_ = temporalesAA[0];
                    
                String consensoAmpliado = "";
                    
                if(coloresConsenso == 2 || coloresConsenso == 4){
                    
                    if(porcentajes[0] == porcentajes[1]){
                        
                        consensoAmpliado += "(";
                        
                    }
                    
                    consensoAmpliado += temporalesAA[0];
                    
                    for(int contador_consenso = 1; contador_consenso < temporalesAA.length - 1; contador_consenso++){
                        
                        if(porcentajes[0] == porcentajes[contador_consenso]){
                            
                            consensoAmpliado += temporalesAA[contador_consenso];
                            
                        }else{
                            
                            if(contador_consenso != 1){
                                
                                consensoAmpliado += ")";
                                
                            }

                            break;
                            
                        }
                        
                    }
                        
                }
                    
                switch (coloresConsenso) {
                        
                    case 1:
                        
                        retorno = String.valueOf(consenso_);
                        break;
                            
                    case 2:
                            
                        String colorConsenso = "";
                            
                        if(porcentajes[0] == 100.0){
                                
                            colorConsenso = "#834580";
                                
                        }else if(porcentajes[0] < 10.0){
                                
                            colorConsenso = "#3E8249";
                                
                        }else if(porcentajes[0] >= 10.0 && porcentajes[0] <= 50.0){
                                
                            colorConsenso = "#2E75B6";
                                
                        }else if(porcentajes[0] > 50.0 && porcentajes[0] <= 75){
                                
                            colorConsenso = "#E0D900";
                                
                        }else if(porcentajes[0] > 75.0 && porcentajes[0] < 90.0){
                                
                            colorConsenso = "#F3A031";
                                
                        }else if(porcentajes[0] >= 90.0){
                                
                            colorConsenso = "#BF3D27";
                                
                        }       
                            
                        retorno = "<a Style = 'color:" + colorConsenso + "';><strong>" + consensoAmpliado + "</strong></a>";
                            
                        break;
                            
                    case 3:
                            
                        DecimalFormat df = new DecimalFormat("0.000", symbols);
                        df.setDecimalSeparatorAlwaysShown(true);
                        String porcentajeRetorno = df.format(porcentajes[0]);
                        String colorConsensoNumerico = "";
                            
                        if(porcentajes[0] == 100.0){
                                
                            colorConsensoNumerico = "#834580";
                                
                        }else if(porcentajes[0] < 10.0){
                                
                            colorConsensoNumerico = "#3E8249";
                                
                        }else if(porcentajes[0] >= 10.0 && porcentajes[0] <= 50.0){
                                
                            colorConsensoNumerico = "#2E75B6";
                                
                        }else if(porcentajes[0] > 50.0 && porcentajes[0] <= 75){
                                
                            colorConsensoNumerico = "#E0D900";
                                
                        }else if(porcentajes[0] > 75.0 && porcentajes[0] < 90.0){
                                
                            colorConsensoNumerico = "#F3A031";
                                
                        }else if(porcentajes[0] >= 90.0){
                                
                            colorConsensoNumerico = "#BF3D27";
                                
                        }       
                            
                        retorno = "<a Style = 'color:" + colorConsensoNumerico + "';><strong>" + porcentajeRetorno + "</strong></a>";
                        break;
                            
                    case 4:
                            
                        retorno = "<a Style = 'color:black';><strong>" + consensoAmpliado + "</strong></a>";
                        break;
                            
                    case 5:
                            
                        retorno = String.valueOf(porcentajes[0]);
                        break;
                            
                    default:
                            
                        break;
                            
                }
            }
            
            b.close();
            
            if(seleccion == 7 || seleccion == 101) {
            	
                String totalGapsGuion = "";
                String totalGapsInterrogacion = "";
                int totalGaps =  0;
                String totalGaps_ = "";
            	
                for(int i = 0; i < aminoacidosEncontrados.length; i++) {
            		
                    if(aminoacidosEncontrados[i] == 'ł') {
            			
                        break;
            			
                    }
            		
                    if(aminoacidosEncontrados[i] == '-') {
            			
                        totalGapsGuion = "; " + String.valueOf(contadorEncontrados[i]) + aminoacidosEncontrados[i];
            			
                        totalGaps += contadorEncontrados[i];
            			
                    }
                        
                    if(aminoacidosEncontrados[i] == '?') {
            			
                        totalGapsInterrogacion =  "; " + String.valueOf(contadorEncontrados[i]) + aminoacidosEncontrados[i];
            			
                        totalGaps += contadorEncontrados[i];
            			
                    }
                    
                    if(totalGaps != 0) {
            			
                        totalGaps_ = "; " + String.valueOf(totalGaps) + " gap";
            			
                    }
            		
                }
            	
                return retorno += contadorSecuencias + totalGaps_ + totalGapsGuion + totalGapsInterrogacion;
            	
            }
            
            if(seleccion == 20){
                
                String vueltaAminoacidosEncontrados = "";
                
                for(int i = 0; i < aminoacidosEncontrados.length; i++){
                    
                    if(aminoacidosEncontrados[i] == 'ł'){
                        
                        break;
                        
                    }
                    
                    vueltaAminoacidosEncontrados += aminoacidosEncontrados[i] +","+  ((contadorEncontrados[i] * 100.0)/contadorSecuencias) + "@";
                
                }
                
                return vueltaAminoacidosEncontrados.substring(0, vueltaAminoacidosEncontrados.length()-1);
                
            }
            
            if(seleccion == 50){
                
                return String.valueOf(contadorSecuencias);
                
            }
            
            if(seleccion == 40){
                
                retorno += "|" + String.valueOf(contadorSecuencias);
                
                return retorno;
                
            }
            
            if(seleccion == 1) {
            	
                retorno += contadorSecuencias;
            	
            }
            
            if(seleccion == 3 || seleccion == 6 || seleccion == 10) {
            	
                return retorno;
            	
            }
                
            if(seleccion == 5) {
            	
                return retorno + "<!---->";
            	
            }else if(seleccionAdicional == 1){
            
                return retorno;
            
            }else {
            	
                return retorno + " </br>";
            	
            }
            
        }catch(IOException e){
                
            Logger.getLogger(Calculos_Frecuencias_Posicion.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);            
              
            Terminar_Hilos.cargarTerminarHilos();
            
            return "";
                
        }
            
    }
    
    public static String calculoFrecuencia(double totalAciertos, double totalSecuencias, boolean asterisco, int decimales){
		
        try{
                
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
                
            DecimalFormat df;
                
            if(decimales == 1){
                
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
            
            Logger.getLogger(Calculos_Frecuencias_Posicion.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
                
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Terminar_Hilos.cargarTerminarHilos();
                
            return "";
                
        }
                
    }
	
    //Devuelve el resultado para construir salidas con los valores relacionados con los residuos por posición.
    
    public static String calculoFrecuenciaTabla(double totalAciertos, double totalSecuencias, char wt, char residuo, int valor_100){
		
        try{
            
            if(((totalAciertos * 100.0)/totalSecuencias) == 100.0){
                                    
                return "p" + residuo + "|" + String.valueOf((totalAciertos * 100.0)/totalSecuencias);
                    
            }else if(((totalAciertos * 100.0)/totalSecuencias) < 10.0 && ((totalAciertos * 100.0)/totalSecuencias) > 0.0 && residuo != wt){
			
                return "v" + residuo + "|" + String.valueOf((totalAciertos * 100.0)/totalSecuencias);
			
            }else if(((totalAciertos * 100.0)/totalSecuencias) >= 90.0 && residuo != wt) {
			
                return "r" + residuo + "|" + String.valueOf((totalAciertos * 100.0)/totalSecuencias);
			
            }else if(((totalAciertos * 100.0)/totalSecuencias) < 90.0 && ((totalAciertos * 100.0)/totalSecuencias) > 75.0 && residuo != wt) {
			
		return "n" + residuo + "|" + String.valueOf((totalAciertos * 100.0)/totalSecuencias);
		
            }else if(((totalAciertos * 100.0)/totalSecuencias) <= 75.0 && ((totalAciertos * 100.0)/totalSecuencias) > 50.0 && residuo != wt) {
			
		return "a" + residuo + "|" + String.valueOf((totalAciertos * 100.0)/totalSecuencias);
			
            }else if(((totalAciertos * 100.0)/totalSecuencias) <= 50.0 && ((totalAciertos * 100.0)/totalSecuencias) >= 10.0 && residuo != wt) {
			
		return "b" + residuo + "|" + String.valueOf((totalAciertos * 100.0)/totalSecuencias);
			
            }
            
            else {
                
                return "  ";
		
            }
                
        }catch(Exception e){
                
            Logger.getLogger(Calculos_Frecuencias_Posicion.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
              
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
              
            Terminar_Hilos.cargarTerminarHilos();
                
            return "";
                
        }
		
    }
        
    public static String calculoTablaMDR(double totalAciertos, double totalSecuencias, char residuo) {
		
        try{
            
            if(((totalAciertos * 100.0)/totalSecuencias) == 100.0){
                
                return "<a Style = 'color:#834580';><strong>" + residuo + "<a Style = 'color:black';>" + "</a>" + "</strong></a>";
                    
            }else if(((totalAciertos * 100.0)/totalSecuencias) < 10.0){
			
                return "<a Style = 'color:#3E8249';><strong>" + residuo + "<a Style = 'color:black';>" + "</a>" + "</strong></a>";
			
            }else if(((totalAciertos * 100.0)/totalSecuencias) >= 90.0) {
			
		return "<a Style = 'color:#BF3D27';><strong>" + residuo + "<a Style = 'color:black';>" + "</a>" + "</strong></a>";
			
            }else if(((totalAciertos * 100.0)/totalSecuencias) < 90.0 && ((totalAciertos * 100.0)/totalSecuencias) > 75.0) {
			
		return "<a Style = 'color:#F3A031';><strong>" + residuo + "<a Style = 'color:black';>" + "</a>" + "</strong></a>";
		
            }else if(((totalAciertos * 100.0)/totalSecuencias) <= 75.0 && ((totalAciertos * 100.0)/totalSecuencias) > 50.0) {
			
		return "<a Style = 'color:#E0D900';><strong>" + residuo + "<a Style = 'color:black';>" + "</a>" + "</strong></a>";
			
            }else if(((totalAciertos * 100.0)/totalSecuencias) <= 50.0 && ((totalAciertos * 100.0)/totalSecuencias) >= 10.0) {
			
                return "<a Style = 'color:#2E75B6';><strong>" + residuo + "<a Style = 'color:black';>" + "</a>" + "</strong></a>";
			
            }else{
                
                return "";
		
            }
                
        }catch(Exception e){
                
            Logger.getLogger(Calculos_Frecuencias_Posicion.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
                
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Terminar_Hilos.cargarTerminarHilos();
                
            return "";
        }
		
    }
	
    //Hace los cálculos de la funcionalidad de Similitud y de Búsqueda de secuencias conservadas.
    
    public static boolean similitud(String temp, String secuenciaProblema, int limiteInf, int limiteSup) {
                
        try{
                           
            char tempArr[] = temp.toCharArray();
            char secuenciaProblemaArr[] = secuenciaProblema.toCharArray();
		
            boolean salida = true;

            boolean retorno = false;
		
            if(limiteInf == -1 || limiteSup == -1){
                    
                limiteInf = 0;
                limiteSup = temp.length();
                    
            }
                
            int contadorTranslocacion = limiteInf;
                
            while(salida) {
			
                if(((tempArr.length - contadorTranslocacion) < secuenciaProblemaArr.length) || contadorTranslocacion + secuenciaProblema.length() > limiteSup + 1) {
				
                    retorno = false;
                    break;
                    
                }
			
                for(int i = 0; i < secuenciaProblemaArr.length; i++) {
				
                    if(secuenciaProblemaArr[i] != tempArr[contadorTranslocacion + i]) {
					
                        retorno = false;
                        break;
					
                    }else{
					
                        retorno = true;
					
                    }
					
                }
			
                if(retorno == true) {
				
                    break;
				
                }
			
                contadorTranslocacion++;
                        
            }
		
            return retorno;
                
        }catch(Exception e){
                
            Logger.getLogger(Calculos_Frecuencias_Posicion.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
                
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);       
              
            Terminar_Hilos.cargarTerminarHilos();
                
            return false;
            
        }
    }
}