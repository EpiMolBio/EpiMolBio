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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Secuencias_Unicas {
    
    //Busca todas las secuencias que hay en uno o varios archivos .fasta y genera uno o varios archivos .fasta con las secuencias sin repeticiones.
    
    public static void cargarSecuenciasUnicas(String entrada, String salida, double frecuenciaMinima){
        
        try{
        
            String tag;
            
            if(idioma == 1){
                
                tag = "Únicas_";
                
            }else{
                
                tag = "Unique_";
                
            }
            
            int contadorSecuencias;
        
            int contadorSecuenciasUnicas = 0;
            int secuenciasTotales = 0;
        
            boolean mostrar = true;
        
            String ficheros[];
            String secuenciasUnicas[] = new String[1000000];
            int secuenciasUnicasEnteros[] = new int[1000000];
        
            for(int i = 0; i < secuenciasUnicas.length; i++){
            
                secuenciasUnicas[i] = "";
                secuenciasUnicasEnteros[i] = 0;
            
            }
        
            File carpetaEntrada = new File(entrada);
        
            ficheros = carpetaEntrada.list();
        
            FileReader f;
            BufferedReader b;
        
            String cadena;
            String temp = "";
        
            FileWriter salidaArchiv;
        
            for (String fichero : ficheros) {
                
                salidaArchiv = new FileWriter(salida + "/" + tag + fichero);
                
                f = new FileReader(entrada + "/" + fichero);
                b = new BufferedReader(f);
                
                while((cadena = b.readLine()) != null) {
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        if(temp.length() != 0) {
		                                    
                            for(int x = 0 ; x < secuenciasUnicas.length; x++){
                                
                                if(contadorSecuenciasUnicas == 0){
                                    
                                    secuenciasUnicas[0] = temp.toUpperCase();
                                    break;
                                    
                                }
                                
                                if(secuenciasUnicas[x].equals("")){
                                    
                                    break;
                                    
                                }
                                
                                if(secuenciasUnicas[x].equals(temp.toUpperCase())){
                                    
                                    secuenciasUnicasEnteros[x]++;
                                    mostrar = false;
                                    break;
                                    
                                }
                                
                            }
                            
                            if(mostrar == true){
                                
                                secuenciasUnicas[contadorSecuenciasUnicas] = temp.toUpperCase();
                                secuenciasUnicasEnteros[contadorSecuenciasUnicas]++;
                                contadorSecuenciasUnicas++;
                                
                            }
                            
                            secuenciasTotales++;
                            
                            mostrar = true;
                            
                            temp = "";
								
                        }else{
		                        
                            temp = "";
		                        
                        }
                        
                    }else {
		                    
                        temp += cadena;
		        	 
                    }

                }
                
                for(int x = 0 ; x < secuenciasUnicas.length; x++){
                                
                    if(contadorSecuenciasUnicas == 0){
                                    
                        secuenciasUnicasEnteros[0]++;
                        secuenciasUnicas[0] = temp.toUpperCase();
                        break;
                                    
                    }
                                
                    if(secuenciasUnicas[x].equals("")){
                                    
                        break;
                                    
                    }
                                
                    if(secuenciasUnicas[x].equals(temp.toUpperCase())){
                                    
                        secuenciasUnicasEnteros[x]++;
                        mostrar = false;
                        break;
                    }
                                
                }
                
                if(mostrar == true){
                                
                    secuenciasUnicas[contadorSecuenciasUnicas] = temp.toUpperCase();
                    secuenciasUnicasEnteros[contadorSecuenciasUnicas]++;
                    contadorSecuenciasUnicas++;
                                
                }
                
                secuenciasTotales++;
                mostrar = true;
                temp = "";
                contadorSecuencias = 0;
                
                for(int a = 0; a < secuenciasUnicas.length; a++){
            
                    if(secuenciasUnicas[a].equals("")){
                
                        break;
                
                    }
                    
                    if(((secuenciasUnicasEnteros[a]*100.0)/secuenciasTotales) >= frecuenciaMinima){
                        
                        if(idioma == 1){
                            
                            salidaArchiv.write(">secuencia " + (contadorSecuencias+1) + "\r\n" + secuenciasUnicas[a] + "\r\n");
                       
                        }else{
                            
                            salidaArchiv.write(">sequence " + (contadorSecuencias+1) + "\r\n" + secuenciasUnicas[a] + "\r\n");
                            
                        }
                        
                        contadorSecuencias++;
                
                    }
                }
                
                salidaArchiv.close();
                secuenciasTotales = 0;
                contadorSecuenciasUnicas = 0;
                
                for(int e = 0; e < secuenciasUnicas.length; e++){
            
                    if(secuenciasUnicas.equals("")){
                        
                        break;
                        
                    }
                    
                    secuenciasUnicas[e] = "";
                    secuenciasUnicasEnteros[e] = 0;
            
                }
            }
                    
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Secuencias_Unicas.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
           
        }   
    }
}
