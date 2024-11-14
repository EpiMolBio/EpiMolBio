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

public class Rastreador_Similitud {

    public static String sec_marco[] = new String[3];
    public static String ficheros[];
	
    public static String encabezados[] = new String[1000000];
    public static String secuencias[] = new String[1000000];
    
    //Busca secuencias concretas por similitud en un pool de secuencias de longitud superior.
    //Searches for specific sequences by similarity in a larger pool of sequences.
    
    public static void cargarRastreadorSimilitud(final String archivoCarga, String archivoGuardado, int rangoInferior, int rangoSuperior, String ejemplo, int tipoSecuencia, double homologia) throws IOException {
        
        try{
                       
            String anadido = "";
            
            String secuenciaTemp1;
            String secuenciaTemp2;
            String secuenciaTemp3;
            
            String secuenciaCalculada;
            
            int contadorHomologiaParcial;		
            boolean secEncontrada;
            
            int tamanoEjemplo;
            
            if(tipoSecuencia == 1){
                
                tamanoEjemplo = ejemplo.length()*3;
                
            }else{
                
                tamanoEjemplo = ejemplo.length();
                
            }
           
            for(int i = 0; i < tamanoEjemplo; i++){
                
                anadido += "ł";
                
            }
            
            anadido += "łłłłł";
                                    
            if(rangoInferior != 0 && rangoSuperior != 0){
                                
                rangoInferior += anadido.length();
                rangoSuperior += anadido.length();
            
            }
                        
            String ficheros[] = new String[100000];
        
            int n;
        
            n = 0;
        
            for(int i = 0; i < ficheros.length; i++){
            
                ficheros[i] = "";
            
            }
        
            final File carpeta2 = new File(archivoCarga);
        
            for(File ficherosEntrada2: carpeta2.listFiles()) {
				
                ficheros[n] = (ficherosEntrada2.getName());
            
                n++;
	
            }
        
            for (String fichero : ficheros) {
                
                if (fichero.equals("")) {
                    
                    break;
                    
                }
                
                for(int i = 0; i < encabezados.length; i++){
            
                    encabezados[i] = "";
                    secuencias[i] = "";
            
                }
                
                String ficheroSalida = archivoGuardado;
                FileWriter ficheroSalida1;
                
                if (idioma == 1) {
                    
                    ficheroSalida1 = new FileWriter(ficheroSalida + "Rastreado_Similitud_" + fichero);
                    
                } else {
                    
                    ficheroSalida1 = new FileWriter(ficheroSalida + "Tracked_Similarity_" + fichero);
                    
                }
                
                String secuenciaEjemplo;
                secuenciaEjemplo = ejemplo;
                String secuencia;
                String entrada_;
                
                sec_marco[0] = "";
                sec_marco[1] = "";
                sec_marco[2] = "";
                
                entrada_ = archivoCarga + fichero;
                lectorSecuencias(entrada_);
                
                for(int i = 0; i < secuencias.length; i++){
                    
                    if(secuencias[i].equals("")){
                       
                        break;
                        
                    }
                    
                    secuencias[i] = anadido + secuencias[i] + anadido;
                    
                }
                
                int rangoSuperiorSecundario = rangoSuperior;
                
                for(int s = 0; s < secuencias.length; s++) {
			
                    if(secuencias[s].equals("") && encabezados[s].equals("")){
                
                        break;
                
                    }
            
                    secuencia = secuencias[s].toUpperCase();
			
                    sec_marco[0] = "";
                    sec_marco[1] = "";
                    sec_marco[2] = "";
                    
                    for(int i = 1; i < secuencia.length(); i++) {
                        
                        sec_marco[1] += secuencia.toCharArray()[i];
                        
                    }
                    
                    for(int i = 2; i < secuencia.length(); i++) {
                        
                        sec_marco[2] += secuencia.toCharArray()[i];
                        
                    }
			
                    sec_marco[0] = secuencia;
           
                    if(rangoInferior != 0 && rangoSuperior != 0){
                
                        secuenciaTemp1 = "";
                        secuenciaTemp2 = "";
                        secuenciaTemp3 = "";
                                        
                        for(int i = rangoInferior -1; i < rangoSuperior; i++){
                                                
                            if(rangoSuperior > sec_marco[2].length()){
                        
                                break;
                        
                            }
                    
                            secuenciaTemp1 += sec_marco[0].toCharArray()[i];
                            secuenciaTemp2 += sec_marco[1].toCharArray()[i];
                            secuenciaTemp3 += sec_marco[2].toCharArray()[i];
                    
                        }
                
                        sec_marco[0] = secuenciaTemp1;
                        sec_marco[1] = secuenciaTemp2;
                        sec_marco[2] = secuenciaTemp3;
      
                    }
	
                    if(tipoSecuencia == 1){
                        
                        sec_marco[0] = Traductor.cargarTraductor(sec_marco[0], 0);
                        sec_marco[1] = Traductor.cargarTraductor(sec_marco[1], 0);
                        sec_marco[2] = Traductor.cargarTraductor(sec_marco[2], 0);
                
                    }
            
                    contadorHomologiaParcial = 0;
			
                    secEncontrada = false;
			
                    for (String sec_marco1 : sec_marco) {
                        
                        if(secEncontrada == true) {
					
                            break;
                            
                        }
                        
                        if (rangoSuperiorSecundario == 0) {
                            
                            rangoSuperior = sec_marco1.length();
                            
                        }
                        
                        if (sec_marco1.length() >= (rangoSuperior - rangoInferior)/3) {
                            
                            for (int i = 0; i < sec_marco1.length() - secuenciaEjemplo.length(); i++) {
                                
                                for (int a = 0; a < secuenciaEjemplo.length(); a++) {
                                    
                                    if (sec_marco1.toCharArray()[i+a] == secuenciaEjemplo.toCharArray()[a]) {
                                        
                                        contadorHomologiaParcial++;
                                        
                                    }
                                    
                                }
                                
                                if ((contadorHomologiaParcial * 100.0)/(double)secuenciaEjemplo.length() >= homologia) {
                                    
                                    secuenciaCalculada = "";
                                    
                                    for (int p = i; p < (secuenciaEjemplo.length() + i); p++) {
                                        
                                        secuenciaCalculada += (sec_marco1.toCharArray()[p]);
                                        
                                    }
                                    
                                    ficheroSalida1.write(encabezados[s]);
                                    ficheroSalida1.write("\r\n");
                                    ficheroSalida1.write(secuenciaCalculada.replace("ł", "?"));
                                    ficheroSalida1.write("\r\n");
                                    secEncontrada = true;
                                    
                                }
                                
                                contadorHomologiaParcial = 0;
                                
                            }	
                        }
                    }
			
                }
                
                ficheroSalida1.close();
                
            } 
        
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Rastreador_Similitud.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Hilo_Rastreador_Similitud.t_rastreador_similitud.stop();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
    public static void lectorSecuencias(String archivo) {
		
        try{
        
            FileReader f = new FileReader(archivo);
            
            try (BufferedReader b = new BufferedReader(f)) {
                
                String temp = "";				
                
                String cadena;
                
                int contador = 0;
                int contador2 = 0;
                
                while((cadena = b.readLine()) != null) {
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        if(temp.length() != 0) {
                            
                            secuencias[contador2] = temp.toUpperCase();
                            contador2++;
                            
                        }
                        
                        encabezados[contador] = cadena;
                        
                        contador++;
                        
                        if(temp.length() != 0) {
                            
                            temp = "";
                            
                        }else{
                            
                            temp = "";
                            
                        }
                        
                    }else {
                        
                        temp += cadena;
                        
                    }
                    
                }
                
                secuencias[contador2] = temp.toUpperCase();
            }
   
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                      
            Logger.getLogger(Rastreador_Similitud.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Hilo_Rastreador_Similitud.t_rastreador_similitud.stop();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
   
}
