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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Dot_Plot {
       
    //Calcula los puntos para hacer un dot plot entre dos secuencias introducidas. Devuelve una matriz con la posición de dichos puntos.
    //Calculates the points for creating a dot plot between two input sequences. Returns a matrix with the positions of these points.
    
    public static int[][] cargarDotPlot(String secuencia1, String secuencia2){
           
        try{
        
            int sec1 = secuencia1.length();
            int sec2 = secuencia2.length();
		
            int matriz[][] = new int[sec1+1][sec2+1];
		
            matriz[0][0] = 0;
		
            int contadorNegativo = -1;
		
            for(int i = 1; i < sec2+1; i++) {
			
                matriz[0][i] = contadorNegativo;
			
                contadorNegativo--;
			
            }
		
            contadorNegativo = -1;
		
            for(int i = 1; i < sec1+1; i++) {
			
                matriz[i][0] = contadorNegativo;
                contadorNegativo--;
			
            }
		
            char vector_1[] = secuencia1.toCharArray();
            char vector_2[] = secuencia2.toCharArray();
		
            int valorCeldaSup;
            int valorCeldaIzq;
            int valorCeldaSupIzq;
            int valorMM;
		
            int vectorOrden[] = new int[3];
            int valorTemp1, valorTemp2;
				
            int matrizDot[][];
		
            matrizDot = matriz;
		
            for(int i = 1; i < sec2+1; i++) {
			
                for(int a = 1; a < sec1+1; a++) {
				
                    if(vector_1[a-1] == vector_2[i-1]) {
					
                        valorMM = 1;
					
                    }else {
					
                        valorMM = -1;
					
                    }
				
                    matrizDot[a-1][i-1] = valorMM;
				
                    valorCeldaSup = valorMM + matriz[a-1][i];
                    valorCeldaIzq = valorMM + matriz[a][i-1];
                    valorCeldaSupIzq = valorMM + matriz[a-1][i-1];
				
                    vectorOrden[0] = valorCeldaSup ;
                    vectorOrden[1] = valorCeldaIzq;
                    vectorOrden[2] = valorCeldaSupIzq;
				
                    for(int x = 0; x < 500; x++) {
					
                        for(int y = 0; y < 2 ; y++) {
						
                            valorTemp1 = vectorOrden[y];
                            valorTemp2 = vectorOrden[y+1];
						
                            if(vectorOrden[y] > vectorOrden[y+1]) {
							
                                vectorOrden[y] = valorTemp2;
                                vectorOrden[y+1] = valorTemp1;
							
                            }
						
                        }
					
                    }
					
                    matriz[a][i] = vectorOrden[2];
						
                }
			
            }
        	
            return matrizDot;
        
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Dot_Plot.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return null;
            
        }
        
    }
    
}
