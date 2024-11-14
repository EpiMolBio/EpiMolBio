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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Mutaciones_Posicion {
    
    public static String retornador3 = "";
    
    //Prueba las combinaciones posibles entre los residuos de las posiciones indicadas y calcula la frecuencia de aparición conjunta.
    //Tests possible combinations between residues at the specified positions and calculates their joint occurrence frequency.
    
    public static void cargarMutacionesPosicion(String archivoCarga, String archivoGuardado, String mutaciones, boolean modo){
        
        try{
            
            try (FileWriter salidaArchivo = new FileWriter(archivoGuardado)) {
                
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                symbols.setDecimalSeparator('.');
                
                DecimalFormat df = new DecimalFormat("0.000", symbols);
                df.setDecimalSeparatorAlwaysShown(true);
                
                final File carpeta = new File(archivoCarga);
                
                String ficheros[] = new String[5000];
                String ficheroSecundario[] = new String[5000];
                
                for(int i = 0; i < ficheros.length; i++) {
                    
                    ficheros[i] = "";
                    ficheroSecundario[i] = "";
                    
                }
                
                int a = 0;
                
                for (File ficheroEntrada : carpeta.listFiles()) {
                    
                    ficheros[a] = (ficheroEntrada.getName());
                    a++;
                    
                }
                
                Arrays.sort(ficheros);
                
                int contadorSecundario = 0;
                
                for (String fichero : ficheros) {
                    
                    if (!fichero.equals("")) {
                        
                        ficheroSecundario[contadorSecundario] = fichero;
                        contadorSecundario++;
                        
                    }
                    
                }
                
                ficheros = ficheroSecundario;
                
                String secuencia;
                
                int contadorDemostrador;
                int contadorTotales;
                int contadorValidos;
                
                String temp;
                String cadena;
                
                if(modo == false){
                    
                    if(idioma == 1){
                        
                        salidaArchivo.write("Archivo;" + "Residuos ("+ mutaciones + ");" + "Número Mutaciones;" + "Frecuencia;" + "Número Secuencias" + "\r\n");
                        
                    }else{
                        
                        salidaArchivo.write("File;" + "Residues (" + mutaciones + ");" + "Number of Mutations;" + "Frequency;" + "Number of Sequences" + "\r\n");
                        
                    }
                }
                
                String valorMutacionCadena[];
                int valorMutacion[];
                
                String setMutaciones;
                
                String mutacionesSeparadas[];
                String mutacionesSeparadasTotal[];
                
                char mut[];
                
                FileReader f;
                BufferedReader b;
                
                double porcentajeSalida;
                
                for (String fichero : ficheros) {
                    
                    if (fichero.equals("")) {
                        
                        break;
                        
                    }
                    
                    mutacionesSeparadasTotal = rastreadorCombinaciones(archivoCarga + fichero, mutaciones);
                    
                    for (String mutacionesSeparadasTotal1 : mutacionesSeparadasTotal) {
                        
                        if (mutacionesSeparadasTotal1.equals("")) {
                            
                            break;
                            
                        }
                        
                        mutacionesSeparadas = mutacionesSeparadasTotal1.split(",");
                        setMutaciones = "";
                        
                        for (String mutacionesSeparada : mutacionesSeparadas) {
                            
                            setMutaciones += mutacionesSeparada.toCharArray()[mutacionesSeparada.length() - 1];
                            
                        }
                        
                        valorMutacionCadena = new String[mutacionesSeparadas.length];
                        valorMutacion = new int[mutacionesSeparadas.length];
                        mut = new char[mutacionesSeparadas.length];
                        
                        for(int i = 0; i < valorMutacionCadena.length; i++){
                            
                            valorMutacionCadena[i] = "";
                            
                        }
                        
                        for(int i = 0; i < mut.length; i++){
                            
                            mut[i] = mutacionesSeparadas[i].toCharArray()[mutacionesSeparadas[i].toCharArray().length-1];
                            
                        }
                        
                        for(int i = 0; i < valorMutacionCadena.length; i++){
                            
                            for(int n = 1; n < mutacionesSeparadas[i].toCharArray().length - 1; n++){
                                
                                valorMutacionCadena[i] += String.valueOf(mutacionesSeparadas[i].toCharArray()[n]);
                                
                            }
                            
                            valorMutacion[i] = Integer.parseInt(valorMutacionCadena[i]);
                            
                        }
                        
                        contadorDemostrador = 0;
                        contadorTotales = 0;
                        contadorValidos = 0;
                        
                        f = new FileReader(archivoCarga + fichero);
                        b = new BufferedReader(f);
                        temp = "";
                        
                        while((cadena = b.readLine()) != null) {
                            
                            if(cadena.toCharArray()[0] == '>') {
                                
                                if(temp.length() != 0) {
                                    
                                    secuencia = temp.toUpperCase();
                                    
                                    contadorValidos++;
                                    
                                    for(int n = 0; n < valorMutacion.length; n++){
                                        
                                        if(secuencia.toCharArray()[valorMutacion[n]-1] == '?' || secuencia.toCharArray()[valorMutacion[n]-1] == '-'){
                                            
                                            contadorValidos--;
                                            contadorDemostrador = 0;
                                            break;
                                            
                                        }
                                        
                                        if(secuencia.toCharArray()[valorMutacion[n]-1] == mut[n]){
                                            
                                            contadorDemostrador++;
                                            
                                        }
                                        
                                    }
                                    
                                    if(contadorDemostrador == valorMutacion.length){
                                        
                                        contadorTotales++;
                                        
                                    }
                                    
                                    contadorDemostrador = 0;
                                    
                                }
                                
                                if(temp.length() != 0) {
                                    
                                    temp = "";
                                    
                                }else{
                                    
                                    temp = "";
                                    
                                }
                                
                            }else {
                                
                                temp += cadena;
                            }
                            
                        }
                        
                        secuencia = temp.toUpperCase();
                        contadorValidos++;
                        
                        for(int n = 0; n < valorMutacion.length; n++){
                            
                            if(secuencia.toCharArray()[valorMutacion[n]-1] == '?' || secuencia.toCharArray()[valorMutacion[n]-1] == '-'){
                                
                                contadorValidos--;
                                contadorDemostrador = 0;
                                break;
                                
                            }
                            
                            if(secuencia.toCharArray()[valorMutacion[n]-1] == mut[n]){
                                
                                contadorDemostrador++;
                                
                            }
                            
                        }
                        
                        if(contadorDemostrador == valorMutacion.length){
                            
                            contadorTotales++;
                            
                        }
                        
                        b.close();
                        porcentajeSalida = (double)(contadorTotales * 100.0)/contadorValidos;
                        
                        if(contadorTotales == 0){
                            
                            continue;
                            
                        }
                        
                        if (modo == false) {
                            
                            salidaArchivo.write(fichero + ";" + setMutaciones + ";" + contadorTotales + ";" + String.valueOf(df.format(porcentajeSalida)) + ";" + contadorValidos + "\r\n");
                            
                        } else if (modo == true) {
                            
                            retornador3 += fichero + ";" + setMutaciones + ";" + contadorTotales + ";" + String.valueOf(df.format(porcentajeSalida)) + ";" + contadorValidos + "\r\n";
                            
                        }
                    }
                }
            }
            
        }catch(IOException | NumberFormatException e){
            
            Logger.getLogger(Mutaciones_Posicion.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Terminar_Hilos.cargarTerminarHilos();
                        
        }
      
    }
    
    //Gestiona la opción de cargar archivos desde subcarpetas de la funcionalidad Mutaciones por Posición.
    //Manages the option to load files from subfolders in the Mutations by Position functionality.
    
    public static void lectorSubcarpetas(String archivoCarga, String archivoGuardado, String mutaciones) throws Exception{
        
        try{
            
            retornador3 = "";
            
            String ficheroSalidaGeneral;
            
            FileWriter ficheroSalidaGeneral1;
            
            String directorio = archivoCarga;
            
            final File carpeta = new File(directorio);
            
            String ficheros[] = new String[5000];
            String ficheroSecundario[] = new String[5000];
            
            for(int i = 0; i < ficheros.length; i++) {
		
                ficheros[i] = ""; 
                ficheroSecundario[i] = "";
                    
            }
            
            int a = 0;
	
            for (File ficheroEntrada : carpeta.listFiles()) {
	        
                ficheros[a] = (ficheroEntrada.getName());
                a++;
			
            }
            
            Arrays.sort(ficheros);
                
            int contadorSecundario = 0;
            
            for (String fichero : ficheros) {
                
                if (!fichero.equals("")) {
                    
                    ficheroSecundario[contadorSecundario] = fichero;
                    contadorSecundario++;
                    
                }
                
            }
            
            ficheros = ficheroSecundario;
            
            ficheroSalidaGeneral = archivoGuardado;
            
            ficheroSalidaGeneral1 =  new FileWriter(ficheroSalidaGeneral);
              
            if(idioma == 1){
                        
                ficheroSalidaGeneral1.write("Archivo;" + "Residuos ("+ mutaciones + ");" + "Número Mutaciones;" + "Frecuencia;" + "Número Secuencias" + "\r\n");
                        
            }else{
                        
                ficheroSalidaGeneral1.write("File;" + "Residues (" + mutaciones + ");" + "Number of Mutations;" + "Frequency;" + "Number of Sequences" + "\r\n");
                        
            }
                
            for (String fichero : ficheros) {
                
                if (fichero.equals("")) {
                    
                    break;
                    
                }
                
                cargarMutacionesPosicion(archivoCarga + fichero + "/", archivoGuardado, mutaciones, true);
                
            }
            
            ficheroSalidaGeneral1.write(retornador3);
            
            ficheroSalidaGeneral1.close();
              
            retornador3 = "";
            
        }catch(IOException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                  
            Logger.getLogger(Mutaciones_Posicion.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
    
    public static String []rastreadorCombinaciones(String entrada, String posiciones){
            
        String combinacionesFiltradas[] = null;
               
        try{
            
            String posicionesCortadas[] = posiciones.split(",");
            
            String retorno;
            String setMutaciones[] = new String[posicionesCortadas.length];
            
            for(int i = 0; i < setMutaciones.length; i++){
                
                setMutaciones[i] = "";
                
            }
            
            for (int x = 0; x < posicionesCortadas.length; x++) {
               
                retorno = Calculos_Frecuencias_Posicion.cargarCalculosFrecuenciasPosicion(entrada, Integer.parseInt(posicionesCortadas[x]), 'ł', "ł", 1, 1, 0, "", 0, 0, 0, 0, 0, 'ł');
                
                for(int i = 0; i < retorno.length()-1; i++){
                
                    if(retorno.toCharArray()[i+1] == '('){
                    
                        setMutaciones[x] += "ł" + Integer.parseInt(posicionesCortadas[x]) + retorno.toCharArray()[i] + ";";
                    
                    }
                
                }
                
            }
                            
            ArrayList<String[]> conjuntos = new ArrayList<>();
            
            for (String setMutacione : setMutaciones) {
                
                conjuntos.add(setMutacione.split(";"));
                
            }
            
            ArrayList<String[]> combinaciones = generarCombinaciones(conjuntos, 0, new String[conjuntos.size()]);
            
            String combinacionTemporal = "";
            
            for(int i = 0; i < combinaciones.size(); i++){
                
                combinacionTemporal += Arrays.toString(combinaciones.get(i)).replace(" ", "").replace("[", "").replace("]", "") + ";";
                
            }
                        
            combinacionesFiltradas = combinacionTemporal.split(";");
            
        }catch(NumberFormatException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);      
                
            Logger.getLogger(Mutaciones_Posicion.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
        return combinacionesFiltradas;
        
    }
    
    //Realiza las combinaciones.
    //Performs the combinations.
    
    private static ArrayList<String[]> generarCombinaciones(ArrayList<String[]> conjuntos, int indice, String[] combinacionActual) {
       
        ArrayList<String[]> combinaciones = new ArrayList<>();

        if (indice == conjuntos.size()) {
            
            combinaciones.add(combinacionActual.clone());
            
        } else {
            
            String[] conjuntoActual = conjuntos.get(indice);

            for (String mutacion : conjuntoActual) {
                
                combinacionActual[indice] = mutacion;
                combinaciones.addAll(generarCombinaciones(conjuntos, indice + 1, combinacionActual));
                
            }
            
        }

        return combinaciones;
    }
    
}
