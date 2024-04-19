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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Contar_Secuencias_Tabla {
    
    //Cuenta las secuencias que hay en uno o varios archivos .fasta y genera una tabla.
    
    public static void cargarContarSecuenciasTabla(String archivoEntrada, String archivoSalida, int seleccion){
        
        try{
            
            if(seleccion == 2){
                
                tablaValoresSeparado(archivoEntrada, archivoSalida);
                
            }else if(seleccion == 3){
            
                tablaValoresCelda(archivoEntrada, archivoSalida);
            
            }
            
        }catch(Exception e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Contar_Secuencias_Tabla.class.getName()).log(Level.SEVERE, null, e);
                   
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
            
    }
    
    //Genera una tabla en .csv donde se muestra la cantidad de secuencias que hay en uno o varios archivos .fasta además del total de estas.
    
    public static void tablaValoresSeparado(String archivoEntrada, String archivoSalida){
        
        try{
        
            try (FileWriter ficheroSalida = new FileWriter(archivoSalida)) {
                
                if(idioma == 1){
                    
                    ficheroSalida.write("Archivo;Número de Secuencias\r\n");
                    
                }else{
                    
                    ficheroSalida.write("File;Number of Sequences\r\n");
                    
                }
                
                int contadorSecuencias;
                int contadorSecuenciasTotales = 0;
                
                FileReader f;
                BufferedReader c;
                
                String cadena;
                
                final File carpeta = new File(archivoEntrada);
                
                String ficheros[] = new String[5000];
                
                String ficherosTemporal[] = new String[5000];
                
                for(int i = 0; i < ficheros.length; i++) {
                    
                    ficheros[i] = "";
                    ficherosTemporal[i] = "";
                    
                }
                
                int a = 0;
                
                for (File ficheroEntrada : carpeta.listFiles()) {
                    
                    ficheros[a] = (ficheroEntrada.getName());
                    a++;
                    
                }
                
                Arrays.sort(ficheros);
                
                int contadorFicheros = 0;
                
                for (String fichero : ficheros) {
                    
                    if (!fichero.equals("")) {
                        
                        ficherosTemporal[contadorFicheros] = fichero;
                        contadorFicheros++;
                        
                    }
                }
                
                ficheros = ficherosTemporal;
                
                for (String fichero : ficheros) {
                    
                    if (fichero.equals("")) {
                        
                        break;
                        
                    }
                    
                    f = new FileReader(archivoEntrada + fichero);
                    c = new BufferedReader(f);
                    contadorSecuencias = 0;
                    
                    while((cadena = c.readLine()) != null) {
                        
                        if(cadena.toCharArray()[0] == '>') {
                            
                            contadorSecuencias++;
                            contadorSecuenciasTotales++;
                            
                        }
                    }
                    
                    ficheroSalida.write(fichero + ";" + String.valueOf(contadorSecuencias) + "\r\n");
                }
                
                ficheroSalida.write("Total;" + String.valueOf(contadorSecuenciasTotales));
            }
            
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Contar_Secuencias_Tabla.class.getName()).log(Level.SEVERE, null, e);
                   
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
                    
    }
    
    /*Función en deshuso, donde se genera un archivo .csv con la cantidad de secuencias que hay en varias carpetas. 
    En cada celda aparece la cantidad de secuencias de los archivos relacionados.*/
    
    public static void tablaValoresCelda(String archivoEntrada, String archivoSalida){
            
        try{
            
            boolean encontrado;
            
            int contadoresTotales[] = new int[5000];
            int contadorRecorrerTotales = 0;
            
            for(int i = 0; i < contadoresTotales.length; i++){
                
                contadoresTotales[i] = 0;
                
            }
            
            try (FileWriter ficheroSalida = new FileWriter(archivoSalida)) {
                
                String ficherosTotales[][] = new String[5000][5000];
                String valorFicherosTotales[][] = new String[5000][5000];
                String ficherosSinRepetir[] = new String[50000];
                String ficheroSecundario[] = new String[50000];
                
                String ficherosTemporales[];
                
                String salidaTotal;
                String salidaTotalCortada = "";
                
                int contadorSinRepetir = 0;
                
                for(int i = 0; i < ficherosSinRepetir.length;i++){
                    
                    ficherosSinRepetir[i] = "";
                    ficheroSecundario[i] = "";
                    
                }
                
                for(int i = 0; i< 5000; i++){
                    
                    for(int x = 0; x < 5000; x++){
                        
                        ficherosTotales[i][x] = "";
                        valorFicherosTotales[i][x] = "";
                        
                    }
                    
                }
                
                String directorio = archivoEntrada;
                
                final File carpetaPrincipal = new File(directorio);
                
                String ficheros[] = new String[5000];
                String ficherosTemporal[] = new String[5000];
                
                for(int i = 0; i < ficheros.length; i++) {
                    
                    ficheros[i] = "";
                    ficherosTemporal[i] = "";
                    
                }
                
                int a = 0;
                
                for (File ficheroEntrada : carpetaPrincipal.listFiles()) {
                    
                    ficheros[a] = (ficheroEntrada.getName());
                    a++;
                    
                }
                
                Arrays.sort(ficheros);
                
                int contadorFicheros = 0;
                
                for (String fichero : ficheros) {
                    
                    if (!fichero.equals("")) {
                        
                        ficherosTemporal[contadorFicheros] = fichero;
                        contadorFicheros++;
                        
                    }
                    
                }
                
                ficheros = ficherosTemporal;
                
                for (String fichero : ficheros) {
                    
                    ficheroSalida.write(";" + fichero);
                    
                    if (fichero.equals("")) {
                        
                        break;
                        
                    }
                }
                
                ficheroSalida.write("\r\n");
                
                int b;
                
                FileReader f;
                BufferedReader c;
                
                String cadena;
                
                int contadorSecuencias;
                
                boolean salida;
                boolean anadirNuevoFichero;
                
                int contadorSecundario;
                
                
                for(int carpetaExterna = 0; carpetaExterna < ficheros.length; carpetaExterna++){
                    
                    if(ficheros[carpetaExterna].equals("")){
                        
                        break;
                        
                    }
                    
                    final File carpetaSecundaria = new File(directorio + ficheros[carpetaExterna]);
                    
                    String ficherosInternos[] = new String[5000];
                    
                    for(int i = 0; i < ficherosInternos.length; i++) {
                        
                        ficherosInternos[i] = "";
                        
                    }
                    
                    b = 0;
                    
                    for (File ficheroEntrada : carpetaSecundaria.listFiles()) {
                        
                        ficherosInternos[b] = (ficheroEntrada.getName());
                        b++;
                        
                    }
                    
                    for(int contadorFicherosInternos = 0; contadorFicherosInternos < ficherosInternos.length; contadorFicherosInternos++){
                        
                        if(ficherosInternos[contadorFicherosInternos].equals("")){
                            
                            break;
                            
                        }
                        
                        f = new FileReader(directorio + ficheros[carpetaExterna] + "/" + ficherosInternos[contadorFicherosInternos]);
                        c = new BufferedReader(f);
                        
                        contadorSecuencias = 0;
                        
                        while((cadena = c.readLine()) != null) {
                            
                            if(cadena.toCharArray()[0] == '>') {
                                
                                contadorSecuencias++;
                                
                            }
                        }
                        
                        ficherosTemporales = ficherosInternos[contadorFicherosInternos].split("_");
                        
                        ficherosTotales[carpetaExterna][contadorFicherosInternos] = ficherosTemporales[ficherosTemporales.length-1];
                        valorFicherosTotales[carpetaExterna][contadorFicherosInternos] = String.valueOf(contadorSecuencias);
                        
                    }
                    
                }
                
                salida = false;
                anadirNuevoFichero = true;
                
                for(int i = 0; i< 5000; i++){
                    
                    for(int x = 0; x < 5000; x++){
                        
                        if(x == 0 && ficherosTotales[i][x].equals("")){
                            
                            salida = true;
                            break;
                        }
                        
                        if(ficherosTotales[i][x].equals("")){
                            
                            break;
                            
                        }
                        
                        for (String ficherosSinRepetir1 : ficherosSinRepetir) {
                            
                            if (ficherosSinRepetir1.equals(ficherosTotales[i][x])) {
                                
                                anadirNuevoFichero = false;
                                break;
                                
                            }
                            
                        }
                        
                        if(anadirNuevoFichero == true){
                            
                            ficherosSinRepetir[contadorSinRepetir] = ficherosTotales[i][x];
                            contadorSinRepetir++;
                            
                        }else{
                            
                            anadirNuevoFichero = true;
                            
                        }
                        
                    }
                    
                    if(salida == true){
                        
                        break;
                        
                    }
                    
                }
                
                Arrays.sort(ficherosSinRepetir);
                
                contadorSecundario = 0;
                
                for (String ficherosSinRepetir1 : ficherosSinRepetir) {
                    
                    if (!ficherosSinRepetir1.equals("")) {
                        
                        ficheroSecundario[contadorSecundario] = ficherosSinRepetir1;
                        contadorSecundario++;
                        
                    }
                }
                
                ficherosSinRepetir = ficheroSecundario;
                
                for (String ficherosSinRepetir1 : ficherosSinRepetir) {
                    
                    if (ficherosSinRepetir1.equals("")) {
                        
                        break;
                        
                    }
                    
                    salidaTotal = "";
                    salida = false;
                    salidaTotal += ficherosSinRepetir1 + ";";
                    
                    for (int i = 0; i< 5000; i++) {
                        
                        encontrado = false;
                        
                        for (int x = 0; x < 5000; x++) {
                            
                            if(x == 0 && ficherosTotales[i][x].equals("")){
                                
                                salida = true;
                                break;
                                
                            }
                            
                            if(ficherosTotales[i][x].equals("")){
                                
                                break;
                                
                            }
                            
                            if (ficherosTotales[i][x].equals(ficherosSinRepetir1)) {
                                
                                encontrado = true;
                                
                                salidaTotal += valorFicherosTotales[i][x] + ";";
                                
                                contadoresTotales[contadorRecorrerTotales] += Integer.parseInt(valorFicherosTotales[i][x]);
                                contadorRecorrerTotales++;
                                
                            }
                            
                        }
                        
                        if(encontrado == false && !ficherosTotales[i][0].equals("")){
                            
                            salidaTotal += "-;";
                            contadorRecorrerTotales++;
                            
                        }
                        
                        if(salida == true){
                            
                            contadorRecorrerTotales = 0;
                            
                            for(int d = 0; d < salidaTotal.length()-1; d++){
                                
                                salidaTotalCortada += salidaTotal.toCharArray()[d];
                                
                            }
                            
                            ficheroSalida.write(salidaTotalCortada + "\r\n");
                            
                            salidaTotalCortada = "";
                            break;
                            
                        }
                    }
                }
                
                ficheroSalida.write("Total");
                
                for(int i = 0; i < contadoresTotales.length; i++){
                    
                    if(ficheros[i].equals("")){
                        
                        break;
                        
                    }
                    
                    ficheroSalida.write(";" + String.valueOf(contadoresTotales[i]));
                    
                }
            }
            
        }catch(IOException | NumberFormatException e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Contar_Secuencias_Tabla.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
            
    }
    
}
