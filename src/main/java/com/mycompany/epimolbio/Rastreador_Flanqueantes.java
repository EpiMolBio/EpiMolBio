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

import static com.mycompany.epimolbio.Fusionar_Secuencias.cargarFusionarSecuencias;
import static com.mycompany.epimolbio.Interfaz.Error;
import static com.mycompany.epimolbio.Interfaz.btn_presionado;
import static com.mycompany.epimolbio.Interfaz.entrada16;
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

public class Rastreador_Flanqueantes {
 
    public static String encabezadosOrf[] = new String[1000000];
    public static String secuenciasOrf[] = new String[1000000];
    public static String entries1Flanqueantes[];
    public static String entries2Flanqueantes[];
    public static String entries3Flanqueantes[];
    public static File directorioSalida1;
    public static File directorioSalida2;
    public static File directorioSalidaFusionarSecuencias;
    public static String guardadoTemporal;
    
    /*Busca secuencias concretas sirviendose de una región delimitada por 2 secuencias flanqueantes, una longitud máxima y mínima
    y un rango de posiciones en la secuencia donde se realiza la búsqueda.*/
    
    /*Searches for specific sequences using a region defined by 2 flanking sequences, a maximum and minimum length,
    and a range of positions in the sequence where the search is performed.*/
    
    public static void cargarRastreadorFlanqueantes(String archivoCarga, String archivoGuardado, int tipoSecuencia, int proteinaSeleccionada, int rangoInf, int rangoSup, String delante, String detras, int tamanoSecuencia){
        
        try{
            
            guardadoTemporal = entrada16 + "/";
                       
            switch (proteinaSeleccionada) {
                
                case 1:
                    
                    //nsp1 (Leader)
                    
                    delante = "SSAHLGFVRV*PKGK";
                    detras = "AYTRYVDNNFCGPDG";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 1, 1300, (175-10), (185+10), 1300, delante, detras, 1);
                    
                    break;
                    
                case 2:
                    
                    //nsp2
                    
                    delante = "SSGVTRELMRELNGG";
                    detras = "APTKVTFGDDTVIEV";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 300, 3200, (633-5), (643+5), 3200, delante, detras, 1);
                    
                    break;
                    
                case 3:
                    
                    //nsp3 (Incluye Papain Like Proteasa)
                    
                    delante = "PNMMVTNNTFTLKGG";
                    detras = "KIVNNWLKQLIKVTL";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 1800, 9800, (1940-5), (1950+5), 9800, delante, detras, 1);
                    
                    break;   
                 
                case 4:
                    
                    //papain like proteasa
                    
                    delante = "EVITFDNLKTLLSLR";
                    detras = "KLDGVVCTEIDPKLD";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 4300, 6500, (313-5), (323+5), 6500, delante, detras, 1);
                    
                    break;
                
                case 5:
                    
                    //nsp4
                    
                    delante = "QVVNVVTTKIALKGG";
                    detras = "SGFRKMAFPSGKVEG";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 7500, 11000, (495-5), (505+5), 11000, delante, detras, 1);
                    
                    break;
                    
                case 6:
                    
                    //nsp5 (3-C-Like Proteasa)
                    
                    delante = "LYQPPQTSITSAVLQ";
                    detras = "SAVKRTIKGTHHWLL";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 9700, 11200, (301-5), (311+5), 11200, delante, detras, 1);
                    
                    break;
                    
                case 7:
                    
                    //nsp6
                    
                    delante = "TPFDVVRQCSGVTFQ";
                    detras = "SKMSDVKCTSVVLLS";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 10200, 12300, (285-5), (295+5), 12300, delante, detras, 1);
                    
                    break;
                    
                case 8:
                    
                    //nsp7
                    
                    delante = "LGVGGKPCIKVATVQ";
                    detras = "AIASEFSSLPSYAAF";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 11300, 12500, (78-5), (88+5), 12500, delante, detras, 1);
                    
                    break;
                    
                case 9:
                    
                    //nsp8
                    
                    delante = "NKLCEEMLDNRATLQ";
                    detras = "NNELSPVALRQMSCA";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 11000, 13500, (193-5), (203+5), 13500, delante, detras, 1);
                    
                    break;
                    
                case 10:
                    
                    //nsp9
                    
                    delante = "LIVTALRANSAVKLQ";
                    detras = "AGNATEVPANSTVLS";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 12000, 13500, (108-5), (118+5), 13500, delante, detras, 1);
                   
                    break;
                    
                case 11:
                    
                    //nsp10
                    
                    delante = "RGMVLGSLAATVRLQ";
                    detras = "SADAQSFLNGFAV*V";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 12000, 14500, (134-5), (144+5), 14500, delante, detras, 1);
                   
                    break;
                    
                case 12:
                    
                    //nsp11
                    
                    delante = "GYGCSCDQLREPMLQ";
                    detras = "VQPVLHRAAQALVLM";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 12500, 14500, 8, 18, 14500, delante, detras, 1);
                    
                    break;
                    
                case 13:
                    
                    //nsp12 (RdRp)
                                        
                    directorioSalida1 = new File(guardadoTemporal + "/fusionar secuencias/1/");
                    directorioSalida2 = new File(guardadoTemporal + "/fusionar secuencias/2/");
                    directorioSalidaFusionarSecuencias = new File(guardadoTemporal + "/fusionar secuencias/");
                    
                    entries1Flanqueantes = directorioSalida1.list(); 
                    entries2Flanqueantes = directorioSalida2.list();
                    entries3Flanqueantes = directorioSalidaFusionarSecuencias.list();
                    
                    eliminarArchivosTemporalesRastreador();
                   
                    directorioSalida1 = new File(guardadoTemporal + "/fusionar secuencias/1/");
                    directorioSalida2 = new File(guardadoTemporal + "/fusionar secuencias/2/");
                    directorioSalidaFusionarSecuencias = new File(guardadoTemporal + "/fusionar secuencias/");
                    
                    delante = "GYGCSCDQLREPMLQ";
                    detras = "GFAV*VQPVLHRAAQ";
                    
                    directorioSalida1.mkdirs();
                    directorioSalida2.mkdirs();                    
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, guardadoTemporal + "/fusionar secuencias/1/nsp12 (RdRp)_", 12800, 14000, 4, 14, 14000, delante, detras, 1);
                    
                    delante = "PRTHASVS*CTIVFK";
                    detras = "AVGACVLCNSQTSLR";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, guardadoTemporal + "/fusionar secuencias/2/nsp12 (RdRp)_", 12800, 17500, (918-5), (928+5), 17500, delante, detras, 1);
                    
                    cargarFusionarSecuencias(guardadoTemporal + "/fusionar secuencias/", guardadoTemporal);
                   
                    eliminarArchivosTemporalesRastreador();
                    
                    break;
                    
                case 14:
                    
                    //nsp13 (Helicasa)
                    
                    delante = "PEFYEAMYTPHTVLQ";
                    detras = "AENVTGLFKDCSKVI";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 15700, 18500, (596-5), (606+5), 18500, delante, detras, 1);
                    
                    break;
                    
                case 15:
                    
                    //nsp14 (ExoN)
                    
                    delante = "FTSLEIPRRNVATLQ";
                    detras = "SLENVAFNVVNKGHF";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 17500, 20100, (522-5), (532+5), 20100, delante, detras, 1);
                    
                    break;
                    
                case 16:
                    
                    //nsp15 (EndoARNasa)
                    
                    delante = "QFDTYNLWNTFTRLQ";
                    detras = "SSQAWQPGVAMPNLY";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 19000, 21400, (341-5), (351+5), 21400, delante, detras, 1);
                    
                    break;
                    
                case 17:
                    
                    //nsp16 (2'-O-metiltransferasa)
                    
                    delante = "WCKDGHVETFYPKLQ";
                    detras = "TNNVCFSCFIATSL*";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 20000, 22000, (293-5), (303+5), 22000, delante, detras, 1);
                    
                    break;
                    
                case 18:
                    
                    //S (Spike)
                    
                    delante = "ELLFLVMFLLTTKRT";
                    detras = "TNLWICL*ESSQLEL";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 20000, 26000, 1241, 1303, 26000, delante, detras, 1);
                    
                    break;
                    
                case 19:
                    
                    //ORF3a
                    
                    delante = "ASAQRSQITLHINEL";
                    detras = "AQADEYELMYSFVSE";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 24900, 27000, (270-5), (280+5), 27000, delante, detras, 1);
                    
                    break;
                    
                case 20:
                    
                    //E (Envuelta)
                    
                    delante = "TTSVPL*AQADEYEL";
                    detras = "TN*ILY*FFCLEL*F";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 25050, 27650, (70-5), (80+5), 27650, delante, detras, 1);
                    
                    break;
                    
                case 21:
                    
                    //M (Membrana)
                    
                    delante = "LNIILVFLFGTLILA";
                    detras = "VTTDVSSR*LSGYYS";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 26000, 27600, (217-5), (227+5), 27600, delante, detras, 1);
                    
                    break;
                    
                case 22:
                    
                    //ORF6
                    
                    delante = "VAVTILLCLYSK*QQ";
                    detras = "TNMKIILFLALITLA";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 26000, 28500, 56, 66, 28500, delante, detras, 1);
                    
                    break;
                    
                case 23:
                    
                    //ORF7a
                    
                    delante = "SQLDEEQPMEID*TN";
                    detras = "LNFH*LTSICAF*PF";
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 26700, 28400, (116-5), (126+5), 28400, delante, detras, 1);
                    break;
                    
                case 24:
                    
                    //ORF7b
                    
                    delante = "NSVYNTLLHTQKKDR";
                    detras = "TNMKFLVFLGIITTV";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 26500, 28500, 38, 48, 28500, delante, detras, 1);
                    
                    break;
                    
                case 25:
                    
                    //ORF8
                    
                    delante = "LELQDHNETCHA*TN";
                    detras = "TNKLKCLIMDPKISE";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 27000, 28800, (116-5), (126+5), 28800, delante, detras, 1);
                    
                    break;
                    
                case 26:
                    
                    //N (Nucleocapside)
                    
                    delante = "*RSCCFRFHLNEQTK";
                    detras = "THADHTRQMGYINVF";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 26950, 29990, (414), (424), 29990, delante, detras, 1);
                    
                    break;

                case 27:
                    
                    //ORF10
                    
                    delante = "ADSTQA*THADHTRQ";
                    detras = "QSLISV*H*GGLERA";
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, 28700, 29900, 38-15, 38+15, 29900, delante, detras, 1);
                    
                    break;

                case 29:
                    
                    //Seleccionado
                    
                    rastreadorSecuencias(tipoSecuencia, archivoCarga, archivoGuardado, rangoInf, rangoSup, tamanoSecuencia-15, tamanoSecuencia+15, rangoSup,
                        delante, detras, 2);
                    
                    break;
                    
            }
            
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Rastreador_Flanqueantes.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
    
    public static void lectorSecuencias(String archivo){
		
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
                                                        
                            for(int aumentarLongitud = 0; aumentarLongitud < 2000; aumentarLongitud++){
                                
                                temp += "X";
                                
                            }
                            
                            secuenciasOrf[contador2] = temp.toUpperCase();
                            contador2++;
                            
                        }
                        
                        encabezadosOrf[contador] = cadena;
                        
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
                
                for(int aumentarLongitud = 0; aumentarLongitud < 2000; aumentarLongitud++){
                                
                    temp += "X";
                                
                }
                
                secuenciasOrf[contador2] = temp.toUpperCase();
                
            }
   
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Rastreador_Flanqueantes.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
   
    public static void rastreadorSecuencias(int tipoSecuencia, String archivoCarga, String archivoGuardado, int rangoBusquedaInf, int rangoBusquedaSup,
            int valorInferior, int valorSuperior, int longitudMinima, String delante, String detras, int etiquetaFlanqueantes){
           
        try{
            
            int contadorDelante;
            int contadorDetras;
            
            boolean salidaInterna;
                   
            if(tipoSecuencia == 2){
                 
                valorInferior = valorInferior * 3;
                valorSuperior = valorSuperior * 3;    
            
            }
        
            String ficheros[] = new String[100000];
        
            int l;
        
            l = 0;
        
            for(int i = 0; i < ficheros.length; i++){
            
                ficheros[i] = "";
            
            }
        
            final File carpeta2 = new File(archivoCarga);
        
            for(File ficherosEntrada2: carpeta2.listFiles()) {
				
                ficheros[l] = (ficherosEntrada2.getName());
            
                l++;
	
            }
            
            ficheros = Comprobador_Archivos_Vacios.cargarComprobarArchivosVacios(archivoCarga, ficheros);
            
            String secuenciaSalidaTemp;
            String secuenciaEntrada;
            String marcos[] = new String[3];
            String ficheroSalida;
            String secuenciaSalida;
            String secuenciaEntradaTra;
            String secuenciaEntradaNt;
            
            for (String fichero : ficheros) {
                
                if (fichero.equals("")) {
                    
                    break;
                    
                }
                
                secuenciaSalidaTemp = "";
                
                for(int i = 0; i< encabezadosOrf.length; i++){
            
                    encabezadosOrf[i] = "";
                    secuenciasOrf[i] = "";
            
                }
                
                lectorSecuencias(archivoCarga + fichero);
                
                ficheroSalida = archivoGuardado;
                FileWriter ficheroSalida1;
                
                if (idioma == 1) {
                    
                    if (etiquetaFlanqueantes == 1) {
                        
                        ficheroSalida1 = new FileWriter(ficheroSalida + "Rastreado_" + fichero);
                        
                    } else {
                        
                        ficheroSalida1 = new FileWriter(ficheroSalida + "Rastreado_Flanqueantes_" + fichero);
                        
                    }
                    
                } else {
                    
                    if (etiquetaFlanqueantes == 1) {
                        
                        ficheroSalida1 = new FileWriter(ficheroSalida + "Tracked_" + fichero);
                        
                    } else {
                        
                        ficheroSalida1 = new FileWriter(ficheroSalida + "Tracked_Flanking_" + fichero);
                        
                    }
                    
                }
                
                for(int b = 0; b < secuenciasOrf.length; b++){
        
                    if(secuenciasOrf[b].equals("")){
            
                        break;
            
                    }
        
                    marcos[0] = "";
                    marcos[1] = "";
                    marcos[2] = "";
                    
                    if(secuenciasOrf[b].length() < longitudMinima){
                        
                        continue;
            
                    }
                            
                    for(int n = rangoBusquedaInf + 1; n < rangoBusquedaSup; n++){
            
                        marcos[0] += secuenciasOrf[b].toCharArray()[n];
            
                    }
                            
                    for(int n = rangoBusquedaInf; n < rangoBusquedaSup; n++){
            
                        marcos[1] += secuenciasOrf[b].toCharArray()[n];
                        
                    }
                    
                    for(int n = rangoBusquedaInf + 2; n < rangoBusquedaSup; n++){
            
                        marcos[2] += secuenciasOrf[b].toCharArray()[n];
            
                    }
                            
                    for(int marcos_contador = 0; marcos_contador < 3; marcos_contador++){
        
                        secuenciaEntrada = marcos[marcos_contador];
        
                        secuenciaEntrada = secuenciaEntrada.toUpperCase();
        
                        secuenciaSalida = "";
        
                        salidaInterna = false;
                    
                        secuenciaEntradaTra = Traductor.cargarTraductor(secuenciaEntrada, 0);
                   
                        secuenciaEntradaNt = "";
                        
                        if(tipoSecuencia == 2){
                            
                            secuenciaEntradaNt = secuenciaEntrada;
                       
                        }
                   
                        secuenciaEntrada = secuenciaEntradaTra;
                        
                        for(int i = 0; i < secuenciaEntrada.length() - delante.length(); i++){
                            
                            contadorDelante = 0;
                            
                            for(int flanqueantes = 0; flanqueantes < delante.length(); flanqueantes++){
                                
                                if(secuenciaEntrada.toCharArray()[i + flanqueantes] == delante.toCharArray()[flanqueantes]){
                                    
                                    contadorDelante++;
                                    
                                }
                            }
                           
                            if((100.0 * contadorDelante)/delante.length() >= 53.0){
                                                            
                                for(int x = i+15; x < secuenciaEntrada.length()- detras.length(); x++){
                                    
                                    secuenciaSalida += secuenciaEntrada.toCharArray()[x];
                                                                        
                                    contadorDetras = 0;
                            
                                    for(int flanqueantes = 0; flanqueantes < detras.length(); flanqueantes++){
                                
                                        if(secuenciaEntrada.toCharArray()[x + flanqueantes] == detras.toCharArray()[flanqueantes]){
                                    
                                            contadorDetras++;
                                    
                                        }
                                    }
                                    
                                    if((100.0 * contadorDetras)/delante.length() >= 53.0){                                        
                                        
                                        if(tipoSecuencia == 1){
                                            
                                            secuenciaSalida = secuenciaSalida.substring(0, secuenciaSalida.length()-1);
                                            
                                            if(secuenciaSalida.toCharArray()[secuenciaSalida.length()-1] == '*'){
                                                
                                                secuenciaSalidaTemp = secuenciaSalida.substring(0, secuenciaSalida.length()-1);
                                                
                                            }else{
                                            
                                                secuenciaSalidaTemp = secuenciaSalida;
                                                
                                            }
                                        
                                        }else if(tipoSecuencia == 2){
                                            
                                            secuenciaSalidaTemp = "";
                                            
                                            for(int a = (i+15)*3; a < (x+1)*3; a++){                                            
                                                
                                                
                                                secuenciaSalidaTemp += secuenciaEntradaNt.toCharArray()[a];
                                            
                                            }
                                            
                                            secuenciaSalidaTemp = secuenciaSalidaTemp.substring(0, secuenciaSalidaTemp.length()-3);
                                            
                                            secuenciaSalida = secuenciaSalidaTemp;
                                                                                        
                                        }
                                                                            
                                        if(secuenciaSalida.length() > valorInferior && secuenciaSalida.length() < valorSuperior){
                                          
                                            ficheroSalida1.write(encabezadosOrf[b]);
                                            ficheroSalida1.write("\r\n");
                                            ficheroSalida1.write(secuenciaSalidaTemp);
                                            ficheroSalida1.write("\r\n");
               
                                            secuenciaSalidaTemp = "";
                                            secuenciaSalida = "";
                                            salidaInterna = true;
                                            break;
                            
                                        }else{
                            
                                            i = x;
                                            secuenciaSalida = "";
                                            break;
                            
                                        }
                                       
                                    }
                    
                                }
                            }
                            
                            if(salidaInterna == true){
                
                                break;
                
                            }
                            
                            
                        }
        
                        if(salidaInterna == true){
                
                            break;
                
                        }
        
                    }
                }
                
                ficheroSalida1.close();
            }
        
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Rastreador_Flanqueantes.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }       
    }
    
    //Elimina las carpetas y archivos temporales.
    //Removes temporary folders and files.
    
    public static void eliminarArchivosTemporalesRastreador(){
        
        try{
        
            File currentFile;
                    
            while(true){
                    
                System.gc();
                
                directorioSalida1 = new File(guardadoTemporal + "/fusionar secuencias/1/");
                directorioSalida2 = new File(guardadoTemporal + "/fusionar secuencias/2/");
                directorioSalidaFusionarSecuencias = new File(guardadoTemporal + "/fusionar secuencias/");
                
                entries1Flanqueantes = directorioSalida1.list(); 
                entries2Flanqueantes = directorioSalida2.list();
                entries3Flanqueantes = directorioSalidaFusionarSecuencias.list();
                
                if(entries1Flanqueantes == null && entries2Flanqueantes == null && entries3Flanqueantes == null){
                        
                    break;
                       
                }else{
                        
                    try{
                    
                        for(String s: entries1Flanqueantes){
            
                            currentFile = new File(directorioSalida1.getPath(),s);
                            currentFile.delete();
                    
                        }
            
                        directorioSalida1.delete();
            
                    }catch(Exception e){}
                    
                    try{
                    
                        for(String s: entries2Flanqueantes){
            
                            currentFile = new File(directorioSalida2.getPath(),s);
                            currentFile.delete();
                    
                        }
            
                        directorioSalida2.delete();
            
                    }catch(Exception e){}
                    
                    try{
                    
                        for(String s: entries3Flanqueantes){
            
                            currentFile = new File(directorioSalidaFusionarSecuencias.getPath(),s);
                            currentFile.delete();
                    
                        }
            
                        directorioSalidaFusionarSecuencias.delete();
            
                    }catch(Exception e){}
                    
                }
                    
            }
                        
        }catch(Exception e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Rastreador_Flanqueantes.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
        
    }
    
}
