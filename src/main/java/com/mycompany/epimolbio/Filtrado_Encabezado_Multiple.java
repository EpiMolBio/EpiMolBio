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
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Filtrado_Encabezado_Multiple {
        
    public static String encabezados[] = new String[10000000];
    public static char separador;
        
    //Filtra de uno o varios archivos .fasta las secuencias por su encabezado. Este puede coger más de un item para filtrar. Funcionalidad no disponible desde el interfaz.
    
    public static void cargarFiltroEncabezadoMultiple(int valorFiltro[], String Fichero, String salidaArchivoA, int seleccionFiltro, String refiltrado, int traducirFiltro, String caracterSeparador) {
            
        if(caracterSeparador.equals("")){
            
            separador = '.';
            
        }else{
            
            separador = caracterSeparador.toCharArray()[0];
            
        }
        
        String varianteIndex[] = new String[10000000];
        String paisIndex[] = new String[10000000];
        String anoIndex[] = new String[10000000];
        String nombreIndex[] = new String[10000000];
        String accesoIndex[] = new String[10000000];
        String encabezadosOcultosVariante[] = new String[10000000];
        String encabezadosOcultosPais[] = new String[10000000];
        String encabezadosOcultosAno[] = new String[10000000];
        String encabezadosOcultosNombre[] = new String[10000000];
        String encabezadosOcultosAcceso[] = new String[10000000];
        
        String fichero2 = Fichero;
        
        String temp_ = "";
        String temp4_ = "";
        String temp2_ = "";
        String temp3_ = "";
        String cadena_ = "";
        
        String secuencias[] = new String[10000000];
        String variante_ = "";
        
        boolean crearArchivo = false;
        
        String variante;
        String pais_;
        String ano_;
        String nombre_;
        String acceso_;
        String salidaArchivo;
        
        int alcance = 0;
        int alcance_a = 0;
        int alcance_i = 0;
        int alcance_c = 0;
        int alcance_d = 0;
        int alcance_e = 0;
        int contadorVariantes;
        
        for(int i = 0; i < varianteIndex.length; i++) {
            
            varianteIndex[i] = "ł";
            paisIndex[i] = "ł";
            anoIndex[i] = "ł";
            nombreIndex[i] = "ł";
            accesoIndex[i] = "ł";
            
            encabezadosOcultosVariante[i] = "ł";
            encabezadosOcultosPais[i] = "ł";
            encabezadosOcultosAno[i] = "ł";
            encabezadosOcultosNombre[i] = "ł";
            encabezadosOcultosAcceso[i] = "ł";
            
        }
        
        if(valorFiltro[0] == 1){
            
            varianteIndex = filtrosSucesivos(varianteIndex, Fichero, 0);
            
        }
        
        if(valorFiltro[1] == 1){
            
            paisIndex = filtrosSucesivos(paisIndex, Fichero, 1);
            
        }
        
        if(valorFiltro[2] == 1){
            
            anoIndex = filtrosSucesivos(anoIndex, Fichero, 2);
            
        }
        
        if(valorFiltro[3] == 1){
            
            nombreIndex = filtrosSucesivos(nombreIndex, Fichero, 3);
            
        }
        
        if(valorFiltro[4] == 1){
            
            accesoIndex = filtrosSucesivos(accesoIndex, Fichero, 4);
            
        }
        
        for(int i = 0; i < 10000000; i++) {
            
            encabezados[i] = "ł";
            secuencias[i] = "ł";
            
        }
        
        contadorVariantes = 0;
        
        if(valorFiltro[0] == 1){
            
            encabezadosOcultosVariante = montadorFinal(cadena_, temp4_, temp3_, temp2_, temp_, 0, secuencias, contadorVariantes, variante_,
                    encabezados, encabezadosOcultosVariante, fichero2);
            
        }
        
        if(valorFiltro[1] == 1){
            
            encabezadosOcultosPais = montadorFinal(cadena_, temp4_, temp3_, temp2_, temp_, 1, secuencias, contadorVariantes, variante_,
                    encabezados, encabezadosOcultosPais, fichero2);
            
        }
        
        if(valorFiltro[2] == 1){
            
            encabezadosOcultosAno = montadorFinal(cadena_, temp4_, temp3_, temp2_, temp_, 2, secuencias, contadorVariantes, variante_,
                    encabezados, encabezadosOcultosAno, fichero2);
            
        }
        
        if(valorFiltro[3] == 1){
            
            encabezadosOcultosNombre = montadorFinal(cadena_, temp4_, temp3_, temp2_, temp_, 3, secuencias, contadorVariantes, variante_,
                    encabezados, encabezadosOcultosNombre, fichero2);
            
        }
        
        if(valorFiltro[4] == 1){
            
            encabezadosOcultosAcceso = montadorFinal(cadena_, temp4_, temp3_, temp2_, temp_, 4, secuencias, contadorVariantes, variante_,
                    encabezados, encabezadosOcultosAcceso, fichero2);
            
        }
        
        secuencias[contadorVariantes] = temp_;
        
        for(int i = 0; i < 1000000; i++){
            
            if(encabezados[i].equals("ł")){
                
                break;
                
            }
            
            alcance++;
            
        }
        
        for(int i = 0; i < 1000000; i++){
            
            if(encabezadosOcultosVariante[i].equals("ł")){
                
                break;
                
            }
            
            alcance_a++;
            
        }
        
        for(int i = 0; i < 1000000; i++){
            
            if(encabezadosOcultosPais[i].equals("ł")){
                
                break;
                
            }
            
            alcance_i++;
            
        }
        
        for(int i = 0; i < 1000000; i++){
            
            if(encabezadosOcultosVariante[i].equals("ł")){
                
                break;
                
            }
            
            alcance_a++;
            
        }
        
        for(int i = 0; i < 1000000; i++){
            
            if(encabezadosOcultosAno[i].equals("ł")){           
                
                break;
                
            }
            
            alcance_c++;
            
        }
        
        for(int i = 0; i < 1000000; i++){
            
            if(encabezadosOcultosNombre[i].equals("ł")){
                
                break;
                
            }
            
            alcance_d++;
            
        }
        
        for(int i = 0; i < 1000000; i++){
            
            if(encabezadosOcultosAcceso[i].equals("ł")){
                
                break;
                
            }
            
            alcance_e++;
            
        }
        
        for(int a = 0; a <= alcance_a; a++){
            
            if(varianteIndex[a].equals("ł") && !varianteIndex[0].equals("ł")){
                
                break;
                
            }
            
            for(int i = 0; i <= alcance_i; i++){
                
                if(paisIndex[i].equals("ł") && !paisIndex[0].equals("ł")){
                    
                    break;
                    
                }
                
                for(int c = 0; c <= alcance_c; c++){
                    
                    if(anoIndex[c].equals("ł") && !anoIndex[0].equals("ł")){
                        
                        break;
                        
                    }
                    
                    for(int d = 0; d <= alcance_d; d++){
                        
                        if(nombreIndex[d].equals("ł") && !nombreIndex[0].equals("ł")){
                            
                            break;
             
                        }
                        
                        for(int e = 0; e <= alcance_e; e++){
                            
                            if(accesoIndex[e].equals("ł") && !accesoIndex[0].equals("ł")){
                                
                                break;
                
                            }
                            
                            for(int b = 0; b < alcance; b++){
                                
                                if(encabezadosOcultosVariante[b].equals(varianteIndex[a]) && encabezadosOcultosPais[b].equals(paisIndex[i]) && encabezadosOcultosAno[b].equals(anoIndex[c]) && encabezadosOcultosNombre[b].equals(nombreIndex[d]) && encabezadosOcultosAcceso[b].equals(accesoIndex[e])){
                                    
                                    crearArchivo = true;
                                    
                                }
                                
                            }
                            
                            if(crearArchivo == false){
                                
                                continue;
                                
                            }
                            
                            if(varianteIndex[0].equals("ł")){
                                
                                variante = "";
                                
                            }else{
                                
                                variante = "_" + varianteIndex[a];
                                
                            }
                            
                            if(paisIndex[0].equals("ł")){
                                
                                pais_ = "";
                                
                            }else{
                                
                                pais_ = "_" + paisIndex[i];
                                
                            }
                            
                            if(anoIndex[0].equals("ł")){
                                
                                ano_ = "";
                                
                            }else{
                                
                                ano_ = "_" + anoIndex[c];
                                
                            }
                            
                            if(nombreIndex[0].equals("ł")){
                                
                                nombre_ = "";
                                
                            }else{
                                
                                nombre_ = "_" + nombreIndex[d];
                                
                            }
                            
                            if(accesoIndex[0].equals("ł")){
                                
                                acceso_ = "";
                                
                            }else{
                                
                                acceso_ = "_" + accesoIndex[e];
                                
                            }
                            
                            salidaArchivo = salidaArchivoA + refiltrado + variante + pais_ + ano_ + nombre_ + acceso_+  ".fasta";        
                            
                            try (FileWriter salidaArchv = new FileWriter(salidaArchivo)) {
                                
                                for(int x = 0; x < alcance; x++){
                                    
                                    if(encabezados[x].equals("ł")){
                                        
                                        break;
                                        
                                    }
                                    
                                    if(encabezadosOcultosVariante[x].equals(varianteIndex[a]) && encabezadosOcultosPais[x].equals(paisIndex[i]) && encabezadosOcultosAno[x].equals(anoIndex[c]) && encabezadosOcultosNombre[x].equals(nombreIndex[d]) && encabezadosOcultosAcceso[x].equals(accesoIndex[e])){
                                        
                                        salidaArchv.write(encabezados[x]);
                                        salidaArchv.write("\r\n");
                                        
                                        if(seleccionFiltro == 2){
                                            
                                            secuencias[x+1] = Eliminacion_Gaps.cargarEliminacionGaps(secuencias[x+1]);
                                            
                                        }
                                        
                                        if(traducirFiltro == 1){
                                            
                                            secuencias[x+1] = Traductor.cargarTraductor(secuencias[x+1], 0);
                                            
                                        }
                                        
                                        salidaArchv.write(secuencias[x+1]);
                                        salidaArchv.write("\r\n");
                                        
                                    }
                                    
                                }
                                
                                salidaArchv.close();
                                crearArchivo = false;
                                
                            }catch(Exception exe){
                                
                                Error.setLocationRelativeTo(null);
                                Error.setVisible(true);
                                
                                Logger.getLogger(Filtrado_Encabezado_Multiple.class.getName()).log(Level.SEVERE, null, exe);
                                
                                btn_presionado = false;
                                
                                Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                                
                                Terminar_Hilos.cargarTerminarHilos();
                                
                            }
                        }
                    }
                }    
            }
        }
    }
        
    public static String[] filtrosSucesivos(String varianteIndex[], String fichero, int valorFiltro){
            
        try{
            
            String cadena;
                 
            FileReader f = new FileReader(fichero);
            
            try (BufferedReader b = new BufferedReader(f)) {
                
                boolean nuevaSecuencia;
                
                char aminoacidosEncontrados[] = new char[100];
                
                int contadorEncontrados[] = new int[100];
                
                for(int i = 0; i < contadorEncontrados.length; i++){
                    
                    contadorEncontrados[i] = 0;
                    aminoacidosEncontrados[i] = 'ł';
                    
                }
                
                int valorBusqueda_ = valorFiltro;
                
                int contadorVariante = 0;
                
                String variante = "";
                
                int contadorEncabezados_;
                
                int x;
                
                while((cadena = b.readLine()) != null) {
                    
                    contadorEncabezados_ = 0;
                    
                    if(cadena.toCharArray()[0] == '>') {
                        
                        x = 0;
                        
                        while(true) {
                            
                            if(cadena.length() == x) {
                                
                                break;
									
                            }else if(cadena.toCharArray()[x] == separador) {
                                
                                contadorEncabezados_++;
                                
                                if(contadorEncabezados_ > valorBusqueda_) {
                                    
                                    break;
                                    
                                }
                            }
                            
                            if(cadena.toCharArray()[x] != '>' && contadorEncabezados_ == valorBusqueda_ && cadena.toCharArray()[x] != separador) {
                                
                                variante += cadena.toCharArray()[x];
                                
                            }
                            
                            x++;
                            
                        }
                        
                        nuevaSecuencia = true;
                        
                        for(int i = 0; i < 500; i++) {
                            
                            if(variante.equals(varianteIndex[i])) {
                                
                                nuevaSecuencia = false;
                                break;
                                
                            }
                        }
                        
                        if(nuevaSecuencia == true) {
                            
                            varianteIndex[contadorVariante] = variante;
                            contadorVariante++;
                            
                        }
                        
                    }
                    
                    if(cadena.toCharArray()[0] != '>') {
                        
                        variante = "";
                        
                    }
                	
                }
            }
            
            return varianteIndex;
            
        }catch(IOException e){
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Filtrado_Encabezado_Multiple.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
                
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Terminar_Hilos.cargarTerminarHilos();
                
            return null;
                
        }
    }
        
    public static String[] montadorFinal(String cadena_, String temp4_, String temp3_, String temp2_, String temp_, int valorFiltro,
            String secuencias[], int contadorVariantes, String variante_, String encabezados[], String encabezadosOcultos[], String fichero2){
            
        try{
            
            FileReader f_ = new FileReader(fichero2);
            
            try (BufferedReader b_ = new BufferedReader(f_)) {
                
                int contadorEncabezados;
                int valorBusqueda;
                
                int x_;
                
                while((cadena_ = b_.readLine()) != null) {
                    
                    contadorEncabezados = 0;
                    valorBusqueda = valorFiltro;
                    
                    if(cadena_.toCharArray()[0] == '>') {
                        
                        temp4_ = cadena_;
                        temp3_ = temp_;
                        temp2_ = "";
                        
                        secuencias[contadorVariantes] = temp_;
                        
                        x_ = 0;
                        
                        variante_ = "";
                        
                        while(true) {
                            
                            if(cadena_.length() == x_) {
                                
                                break;
 									
                            }
                            
                            if(cadena_.toCharArray()[x_] == separador) {
                                
                                contadorEncabezados++;
                                
                                if(contadorEncabezados > valorBusqueda) {
                                    
                                    break;
                                    
                                }
                            }
                            
                            if(cadena_.toCharArray()[x_] != '>' && contadorEncabezados == valorBusqueda && cadena_.toCharArray()[x_] != separador) {
                                
                                variante_ += cadena_.toCharArray()[x_];
                                
                            }
                            
                            x_++;
                            
                        }
                        
                        encabezados[contadorVariantes] = temp4_;
                        encabezadosOcultos[contadorVariantes] = variante_;
                        
                        contadorVariantes++;
                        temp_ = "";
                        
                    }
                    
                    if(cadena_.toCharArray()[0] != '>') {
                        
                        temp_ += cadena_;
                        
                    }
                    
                }
            }
                        
            secuencias[contadorVariantes] = temp_;
            
            return encabezadosOcultos;
            
        }catch(IOException e){
                
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Filtrado_Encabezado_Multiple.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
                
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Terminar_Hilos.cargarTerminarHilos();
                
            return null;
            
        }
            
    }  
}