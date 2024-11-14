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
import static com.mycompany.epimolbio.Interfaz.jDialog40;
import static com.mycompany.epimolbio.Interfaz.jTextArea2;
import static com.mycompany.epimolbio.Interfaz.btn_presionado;
import static com.mycompany.epimolbio.Interfaz.entrada16;
import static com.mycompany.epimolbio.Interfaz.idioma;
import static com.mycompany.epimolbio.Interfaz.jCheckBox27;
import static com.mycompany.epimolbio.Interfaz.jCheckBox28;
import static com.mycompany.epimolbio.Interfaz.jComboBox12;
import static com.mycompany.epimolbio.Interfaz.jComboBox31;
import static com.mycompany.epimolbio.Interfaz.jLabel91;
import static com.mycompany.epimolbio.Interfaz.jTextField17;
import static com.mycompany.epimolbio.Interfaz.jTextField29;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Programar_Funciones {
    
    //Lanza cualquier funcionalidad del programa siguiendo las intrucciones de un archivo .csv. Este puede contener más de una instrucción para automatizar análisis completos.
    //Executes any functionality of the program following the instructions in a .csv file. This file can contain multiple instructions to automate complete analyses.
    
    public static void cargarProgramarFunciones(String archivoCarga){
     
        try{
                    
            String parametro4;
            String parametro5;
            String parametro6;
            String parametro7;
            String parametro8;
            
            String seleccionFiltroEncabezado[];
            int seleccionEnteroFiltroEncabezado[];
            
            int valorItem;
            int seleccionReemplazo;
            int checkTipo;
            int contadorMDRCodones;
            
            String codonesAnalizarMDR_[];
            String archivoGuardadoMDRCodones;
            String archivoCargaMDRCodones;
            String entradaMDRAdquiridas;
            String salidaMDRAdquiridas;
            
            int proteinaMDRAdquiridas;
            
            boolean parametroVIH1MDRAdquiridas;
            boolean parametroVIH2MDRAdquiridas;
            
            String entradaMDRTransmitidas;
            String salidaMDRTransmitidas;
            
            int proteinaMDRTransmitidas;
            
            String mutacionSeleccionadaListaMutaciones;
                
            int seleccionListaMutaciones;
                
            boolean ordenListaMutaciones;
            
            int selectorFiltroTraduccion[];
            int traducirFiltroTraduccion;
            int traducirNoFiltroTraduccion;
            int gapsNoFiltroTraduccion;
            int marcoTraduccion;
                
            String archivoEntradaTraduccion;
            String directorioSalidaTraduccion;
            String carpetaEntradaTraduccion;
            int seleccionFiltroTraduccion;
            
            String cargaListaMutaciones;
            String guardadoListaMutaciones;
            String wtListaMutaciones;
            String seleccionarCodonMDRCodones_;
                
            double valorCribaListaMutaciones;
                
            int seleccionFiltroMutadasListaMutaciones;
            
            String reporte = "";
            int contadorReporte = 0;
            
            String parametroFinal;
            
            String ficheroSecundario[] = new String[5000];
            
            String directorio = archivoCarga;
	
            final File carpeta = new File(directorio);
	
            String ficheros[] = new String[5000];
	
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
        
            String cadena;
            
            FileReader f;
            BufferedReader b;
            
            String parametros[];
            
            File directorioSalida;
            
            String salidaSeparada[];
            
            String salidaCarpeta;
                 
            for(int i = 0; i < ficheros.length; i++){
                
                if(ficheros[i].equals("")){
                    
                    break;
                    
                }
                
                f = new FileReader(archivoCarga + ficheros[i]);
                b = new BufferedReader(f);
                                
                while((cadena = b.readLine()) != null) {
                    
                    System.gc();
		
                    parametros = cadena.split(";");
                                    
                    if(idioma == 1){
                    
                        reporte += "INSTRUCCIÓN: " + (contadorReporte +1) + "  --->  ";
                    
                    }else{
                        
                        reporte += "INSTRUCTION: " + (contadorReporte +1) + "  --->  ";
                        
                    }
                    
                    for(int h = 0; h < parametros.length; h++){
                        
                        reporte += parametros[h];
                        
                        if(h < parametros.length -1){
                            
                            reporte += ";";
                            
                        }
                        
                    }
                    
                    reporte += "\n\n";
                    
                    jTextArea2.setText(reporte);
                    
                    if(contadorReporte == 0){
                    
                        jDialog40.setLocationRelativeTo(null);
                        jDialog40.setVisible(true);
                        
                    }else if(jDialog40.isVisible() == false){
                        
                        jDialog40.setLocationRelativeTo(null);
                        jDialog40.setVisible(true);
                        
                    }
                    
                    contadorReporte++;
                    
                    if(parametros[1].toCharArray()[parametros[1].length() -1] == '/'){
                        
                        directorioSalida = new File(parametros[1]);
                        directorioSalida.mkdirs();
                        
                    }else{
                        
                        salidaCarpeta = "";
                        
                        salidaSeparada = parametros[1].split("/");
                        
                        for(int x = 0; x < salidaSeparada.length -1; x++){
                            
                            salidaCarpeta += salidaSeparada[x];
                            
                            if(x < salidaSeparada.length-2){
                                
                                salidaCarpeta += "/";
                                
                            }
                            
                        }
                                                
                        directorioSalida = new File(salidaCarpeta + "/");
                        directorioSalida.mkdirs();
                      
                    }
                    
                    switch (parametros[parametros.length -1].toUpperCase()) {
                        
                        case "RASTREADOR ESPECIFICO":
                            
                            parametro4 = "0";
                            parametro5 = "0";
                            parametro6 = "ł";
                            parametro7 = "ł";
                            parametro8 = "0";
                            
                            if(parametros[2].toUpperCase().equals("AA")){
                                
                                parametros[2] = "1";
                                
                            }else if(parametros[2].toUpperCase().equals("NT")){
                                
                                parametros[2] = "2";
                            }
                            
                            if(parametros[3].equals("29")){
                                
                                parametro4 = String.valueOf(Integer.parseInt(parametros[4].toUpperCase()) - 46);
                                parametro5 = String.valueOf(Integer.parseInt(parametros[5].toUpperCase()) + 45);
                                parametro6 = parametros[6].toUpperCase();
                                parametro7 = parametros[7].toUpperCase();
                                parametro8 = parametros[8].toUpperCase();
                                
                            }   
                            
                            String entradaTemporal = entrada16;
                            
                            if(parametros[3].equals("28") || parametros[3].equals("13")){
                            
                                entrada16 = parametros[1];
                            
                            }
                            
                            if(!parametros[3].equals("28") && !parametros[3].equals("29")){
                                    
                                String proteinas[] = {"nsp1 (Leader)", "nsp2", "nsp3 (Incluye Papain Like Proteasa)", "Papain Like Proteasa",
                                    "nsp4", "nsp5 (3-C-Like Proteasa)", "nsp6", "nsp7", "nsp8", "nsp9", "nsp10", "nsp11", "nsp12 (RdRp)", "nsp13 (Helicasa)", "nsp14 (ExoN)", "nsp15 (EndoARNasa)",
                                    "nsp16 (2'-O-Metiltransferasa)", "S (Spike)", "ORF3a", "E (Envuelta)", "M (Membrana)", "ORF6", "ORF7a", "ORF7b", "ORF8", "N (Nucleocapside)", "ORF10"};
                                
                                Rastreador_Flanqueantes.cargarRastreadorFlanqueantes(parametros[0], parametros[1] + "/" + proteinas[Integer.parseInt(parametros[3])-1] + "_", Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]), Integer.parseInt(parametro4),
                                        Integer.parseInt(parametro5), parametro6, parametro7, Integer.parseInt(parametro8));
                                
                            }else if(parametros[3].equals("28")){
                                                                
                                String proteinas[] = {"nsp1 (Leader)", "nsp2", "nsp3 (Incluye Papain Like Proteasa)", "Papain Like Proteasa",
                                    "nsp4", "nsp5 (3-C-Like Proteasa)", "nsp6", "nsp7", "nsp8", "nsp9", "nsp10", "nsp11", "nsp12 (RdRp)", "nsp13 (Helicasa)", "nsp14 (ExoN)", "nsp15 (EndoARNasa)",
                                    "nsp16 (2'-O-Metiltransferasa)", "S (Spike)", "ORF3a", "E (Envuelta)", "M (Membrana)", "ORF6", "ORF7a", "ORF7b", "ORF8", "N (Nucleocapside)", "ORF10"};
                                
                                for(int x = 1; x < 28; x++){
                                    
                                    Rastreador_Flanqueantes.cargarRastreadorFlanqueantes(parametros[0], parametros[1] + "/" + proteinas[x-1] + "_", Integer.parseInt(parametros[2]), x, Integer.parseInt(parametro4), Integer.parseInt(parametro5), parametro6, parametro7, Integer.parseInt(parametro8));
                                    
                                }
                                
                            }else if(parametros[3].equals("29")){
                                
                                Rastreador_Flanqueantes.cargarRastreadorFlanqueantes(parametros[0], parametros[1] + "/", Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]), Integer.parseInt(parametro4),
                                        Integer.parseInt(parametro5), parametro6, parametro7, Integer.parseInt(parametro8));
                                
                            }
                            
                            if(parametros[3].equals("28") || parametros[3].equals("13")){
                            
                                entrada16 = entradaTemporal;
                            
                            }
                            
                            break;
                            
                        case "TABLA MUTACIONES":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            parametros[5] = parametros[5].toUpperCase();
                            parametros[6] = parametros[6].toUpperCase();
                            
                            if(parametros[4].toUpperCase().equals("TABLA")){
                                
                                parametros[4] = "2";
                                
                            }else if (parametros[4].toUpperCase().equals("LISTA")){
                                
                                parametros[4] = "1";
                                
                            }   if(parametros[5].toUpperCase().equals("NULO")){
                                
                                parametroFinal = "";
                                
                            }else{
                                
                                parametroFinal = parametros[5].toUpperCase();
                                
                            }   if(parametros[6].toUpperCase().equals("TODO")){
                                
                                parametros[6] = "1";
                                
                            }else if(parametros[6].toUpperCase().equals("MUTADAS")){
                                
                                parametros[6] = "2";
                                
                            }   Posiciones_Mutadas_Tabla_Mutaciones.cargarPosicionesMutadasTablaMutaciones(parametros[0], parametros[1], parametros[2], Double.parseDouble(parametros[3]), false, Integer.parseInt(parametros[4]), parametroFinal, false, Integer.parseInt(parametros[6]));
                            
                            break;
                            
                        case "TABLA MUTACIONES SC":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            parametros[5] = parametros[5].toUpperCase();
                            
                            if(parametros[4].toUpperCase().equals("NULO")){
                                
                                parametroFinal = "";
                                
                            }else{
                                
                                parametroFinal = parametros[4].toUpperCase();
                                
                            }   
                            
                            if(parametros[5].toUpperCase().equals("TODO")){
                                
                                parametros[5] = "1";
                                
                            }else if(parametros[5].toUpperCase().equals("MUTADAS")){
                                
                                parametros[5] = "2";
                                
                            }   
                            
                            Posiciones_Mutadas_Tabla_Mutaciones.lectorSubcarpetas(parametros[0], parametros[1], parametros[2], Double.parseDouble(parametros[3]), false, 2, parametroFinal, Integer.parseInt(parametros[5]));
                            break;
                            
                        case "FUSIONAR SECUENCIAS":
                            
                            jComboBox12.setSelectedIndex(0);
                            Fusionar_Secuencias.cargarFusionarSecuencias(parametros[0], parametros[1]);
                            break;
                            
                        case "FUSIONAR ARCHIVOS":
                            
                            Fusionar_Archivos.cargarFusionarArchivos(parametros[0], parametros[1]);
                            break;
                            
                        case "FILTRO":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            parametros[5] = parametros[5].toUpperCase();
                            parametros[6] = parametros[6].toUpperCase();
                            parametros[7] = parametros[7].toUpperCase();
                            parametros[8] = parametros[8].toUpperCase();
                            
                            seleccionEnteroFiltroEncabezado = new int[5];
                            seleccionFiltroEncabezado = parametros[2].split(",");
                            
                            for(int contador_seleccion = 0; contador_seleccion < seleccionEnteroFiltroEncabezado.length; contador_seleccion++){
                                
                                seleccionEnteroFiltroEncabezado[contador_seleccion] = Integer.parseInt(seleccionFiltroEncabezado[contador_seleccion]);
                                
                            }   
                            
                            Generador_Archivos_Traductor_Gaps.cargarGeneradorArchivosTraductorGaps(parametros[0], "", seleccionEnteroFiltroEncabezado, Integer.parseInt(parametros[3]), Integer.parseInt(parametros[4]),
                                    Integer.parseInt(parametros[5]), parametros[1], Integer.parseInt(parametros[6]), Integer.parseInt(parametros[7]), parametros[8]);
                            break;
                            
                        case "FILTRO UNICO":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            parametros[5] = parametros[5].toUpperCase();
                            
                            valorItem = 0;
                            String parametrosItem[] = parametros[2].split(",");
                            
                            for(int U = 0; U < parametrosItem.length; U++){
                                
                                if(parametrosItem[U].equals("1")){
                                    
                                    valorItem = U;
                                    
                                    break;
                                    
                                }
                                
                            }
                            
                            Filtrado_Encabezado_Unico.cargarFiltradoEncabezadoUnico(parametros[0], parametros[1], parametros[3], valorItem, 
                                    Integer.parseInt(parametros[4]), Integer.parseInt(parametros[5]));
                            
                            break;
                            
                        case "BUSCAR/REEMPLAZAR":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();      
                            
                            seleccionReemplazo = 0;
                            
                            if(parametros[4].toUpperCase().equals("SECUENCIA")){
                                
                                seleccionReemplazo = 1;
                                
                            }else if(parametros[4].toUpperCase().equals("TODO")){
                                
                                seleccionReemplazo = 2;
                                
                            }
                            
                            Buscar_Reemplazar.cargarBuscarReemplazar(parametros[0], parametros[1], parametros[2], parametros[3], seleccionReemplazo);
                            
                            break;
                            
                        case "FILTRO ESPECIFICO":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            
                            Filtro_Especifico.cargarFiltroEspecifico(parametros[0], parametros[1], parametros[2]);
                            break;
                            
                        case "SECUENCIAS UNICAS":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            
                            Secuencias_Unicas.cargarSecuenciasUnicas(parametros[0], parametros[1], Double.parseDouble(parametros[2]));
                            break;
                            
                        case "BUSQUEDA SECUENCIAS":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            if(parametros[2].equals("CSV")){
                                
                                parametros[2] = "1";
                                
                            }else if(parametros[2].equals("FASTA")){
                                
                                parametros[2] = "2";
                                
                            }   
                            
                            Busqueda_Secuencias.cargarBusquedaSecuencias(parametros[0], parametros[1], parametros[3], Integer.parseInt(parametros[2]));
                            
                            break;
                            
                        case "FILTRADO SECUENCIAS PARCIALES":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            if(parametros[2].equals("AA")){
                                
                                parametros[2] = "1";
                                
                            }else if(parametros[2].equals("NT")){
                                
                                parametros[2] = "2";
                                
                            }   
                            
                            Filtrado_Secuencias_Parciales.cargarFiltradoSecuenciasParciales(parametros[0], parametros[1], Double.parseDouble(parametros[3]), Integer.parseInt(parametros[2]), false);
                            
                            break;
                            
                        case "CONTAR SECUENCIAS MUTADAS":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            if(parametros[3].equals("AA")){
                                
                                parametros[3] = "1";
                                
                            }else if(parametros[3].equals("NT")){
                                
                                parametros[3] = "2";
                                
                            }   
                            
                            Contar_Secuencias_Mutadas.cargarContarSecuenciasMutadas(parametros[0], parametros[1], parametros[2], Integer.parseInt(parametros[3]));
                            break;
                            
                        case "CONTAR SECUENCIAS TABLA":
                            
                            Contar_Secuencias_Tabla.cargarContarSecuenciasTabla(parametros[0], parametros[1], 2);
                            break;
                            
                        case "ALINEAMIENTOS MULTIPLES":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            Alineamientos_Multiples alg_mult = new Alineamientos_Multiples();
                            alg_mult.cargadorAlineamientosMultiples(parametros[0], parametros[1], parametros[3], Integer.parseInt(parametros[2]));
                            break;
                            
                        case "ALINEAMIENTOS MULTIPLES A":
                            
                            Alineamientos_Multiples_Reales alg_mult_real = new Alineamientos_Multiples_Reales();
                            alg_mult_real.cargadorAlineamientosMultiplesReales(parametros[0], parametros[1]);
                            break;
                            
                        case "ALINEADOR":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            
                            Generador_Archivos_Eliminar_Inserciones.cargarGeneradorArchivosEliminarInserciones(parametros[0], parametros[1], parametros[2]);
                            break;
                            
                        case "RASTREADOR SIMILITUD":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            parametros[5] = parametros[5].toUpperCase();
                            parametros[6] = parametros[6].toUpperCase();
                            parametros[7] = parametros[7].toUpperCase();
                            
                            if(parametros[2].equals("AMINOACIDOS")){
                                
                                parametros[2] = "1";
                                
                            }else if(parametros[2].equals("NUCLEOTIDOS")){
                                
                                parametros[2] = "2";
                                
                            }   
                            
                            if(parametros[3].equals("RANGO COMPLETO")){
                                
                                parametros[4] = "0";
                                parametros[5] = "0";
                                
                            }   
                            
                            Rastreador_Similitud.cargarRastreadorSimilitud(parametros[0], parametros[1], Integer.parseInt(parametros[4]), Integer.parseInt(parametros[5]), parametros[7], Integer.parseInt(parametros[2]), Double.parseDouble(parametros[6]));
                            
                            break;
                            
                        case "SIMILITUD":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                                                        
                            if(parametros[3].equals("") && parametros[4].equals("")){
                                
                                parametros[3] = "0";
                                parametros[4] = "0";
                                
                            }   
                            
                            Similitud.cargarHomologiaSimilitud(parametros[0], parametros[1], parametros[2], Integer.parseInt(parametros[3])-1, Integer.parseInt(parametros[4])-1);
                            
                            break;
                            
                        case "SIMILITUD PARCIAL":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            parametros[5] = parametros[5].toUpperCase();
                            parametros[6] = parametros[6].toUpperCase();
                            
                            if(parametros[2].equals("TRADUCIR")){
                                
                                parametros[2] = "1";
                                
                            }else if(parametros[2].equals("NO TRADUCIR")){
                                
                                parametros[2] = "2";
                                
                            }   
                            
                            if(parametros[5].equals("ALINEAR")){
                                
                                parametros[5] = "1";
                                
                            }else if(parametros[5].equals("NO ALINEAR")){
                                
                                parametros[5] = "2";
                                
                            }   
                            
                            checkTipo = 0;
                            
                            if(parametros[2].equals("1")){
                                
                                checkTipo = 2;
                                
                            }else if(parametros[2].equals("2")){
                                
                                checkTipo = 1;
                                
                            }   
                            
                            Similitud_Parcial.cargarSimilitudParcial(parametros[0], parametros[1], parametros[6], Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]), Double.parseDouble(parametros[4]), Integer.parseInt(parametros[5]), checkTipo);
                            
                            break;
                            
                        case "DISENO SECUENCIAS CONSERVADAS":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            parametros[5] = parametros[5].toUpperCase();
                            parametros[6] = parametros[6].toUpperCase();
                            parametros[7] = parametros[7].toUpperCase();
                            parametros[8] = parametros[8].toUpperCase();
                            
                            if(parametros[2].equals("TODO EL RANGO")){
                                
                                parametros[3] = "-1";
                                parametros[4] = "-1";
                                
                            }   
                            
                            jTextField17.setText(parametros[8].toUpperCase());
                                                        
                            Generador_Archivos_Busqueda_Secuencias_Conservadas.cargarGeneradorArchivosBusquedaSecuenciasConservadas(parametros[0], parametros[1], Double.parseDouble(parametros[5]),
                                    Integer.parseInt(parametros[3]), Integer.parseInt(parametros[4]) -1, Integer.parseInt(parametros[6]), Integer.parseInt(parametros[7]));
                            
                            jTextField17.setText("");
                            
                            break;
                            
                        case "FRECUENCIA MUTACION":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            
                            if(parametros[3].equals("ALINEAR")){
                                
                                parametros[3] = "1";
                                
                            }else if(parametros[3].equals("NO ALINEAR")){
                                
                                parametros[3] = "2";
                                
                            }   
                            
                            if(parametros[2].equals("NUCLEOTIDOS")){
                                
                                parametros[2] = "1";
                                
                            }else if(parametros[2].equals("AMINOACIDOS")){
                                
                                parametros[2] = "2";
                                
                            }   
                            
                            Frecuencia_Mutacion.cargarFrecuenciaMutacion(parametros[0], parametros[1], parametros[4], Integer.parseInt(parametros[3]), Integer.parseInt(parametros[2]));
                            
                            break;
                            
                        case "INDICE WU-KABAT":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            
                            Wu_Kabat.cargarWuKabat(parametros[0], parametros[1], Integer.parseInt(parametros[2]));
                            break;
                            
                        case "CONSENSOS":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            
                            if(parametros[2].equals("RONDA 1") || parametros[2].equals("RONDAS SUCESIVAS")){
                                
                                if(parametros[2].equals("RONDA 1")){
                                    
                                    parametros[2] = "2";
                                    
                                }else if(parametros[2].equals("RONDAS SUCESIVAS")){
                                    
                                    parametros[2] = "3";
                                    
                                }
                                
                                if(parametros[3].equals("TRUE")){
                                    
                                    parametros[3] = "1";
                                    
                                }else if(parametros[3].equals("FALSE")){
                                    
                                    parametros[3] = "0";
                                    
                                }
                                
                                if(parametros[4].equals("TRUE")){
                                    
                                    parametros[4] = "1";
                                    
                                }else if(parametros[4].equals("FALSE")){
                                    
                                    parametros[4] = "0";
                                    
                                }
                                
                                if(parametros[2].equals("3")){
                                    
                                    jTextField29.setText(parametros[3].toUpperCase());
                                    
                                }
                                
                                Consensos.cargarConsensos(parametros[0], parametros[1], Integer.parseInt(parametros[2]), String.valueOf(parametros[3].length()));
                                
                                if(parametros[2].equals("3")){
                                    
                                    jTextField29.setText("");
                                    
                                }
                                
                            }  
                            
                            break;
                            
                        case "CONSERVACION INDIVIDUAL":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();

                            if(parametros[3].equals("LISTA")){
                                
                                parametros[3] = "1";
                                
                            }else if(parametros[3].equals("TABLA HORIZONTAL")){
                                
                                parametros[3] = "2";
                                
                            }   
                            
                            if(parametros[3].equals("1")){
                                
                                Conservacion_Individual_Lista.cargarConservacionLista(parametros[0], parametros[1], parametros[2]);
                                
                            }else if(parametros[3].equals("2")){
                                
                                Conservacion_Individual_Tabla.cargarConservacionIndividualTabla(parametros[0], parametros[1], parametros[2]);
                                
                            }  
                            
                            break;
                            
                        case "CONSERVACION CODONES":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            if(parametros[2].equals("MOSTRAR 100%")){
                                
                                parametros[2] = "1";
                                
                            }else if(parametros[2].equals("MOSTRAR > 75%")){
                                
                                parametros[2] = "2";
                                
                            }   
                            
                            Conservacion_Codones.cargarConservacionCodones(parametros[0], parametros[1], parametros[3], Integer.parseInt(parametros[2]));
                            
                            break;
                            
                        case "LISTA MUTACIONES":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            parametros[5] = parametros[5].toUpperCase();
                            
                            mutacionSeleccionadaListaMutaciones = parametros[4];
                            seleccionListaMutaciones = 1;
                            ordenListaMutaciones = false;
                            cargaListaMutaciones = parametros[0];
                            guardadoListaMutaciones = parametros[1];
                            wtListaMutaciones = parametros[2];
                            valorCribaListaMutaciones = Double.parseDouble(parametros[3]);
                            seleccionFiltroMutadasListaMutaciones = 0;
                            
                            if(parametros[5].equals("TODAS POSICIONES")){
                                
                                seleccionFiltroMutadasListaMutaciones = 1;
                                
                            }else if(parametros[5].equals("POSICIONES MUTADAS")){
                                
                                seleccionFiltroMutadasListaMutaciones = 2;
                                
                            }   
                            
                            Posiciones_Mutadas_Tabla_Mutaciones.cargarPosicionesMutadasTablaMutaciones(cargaListaMutaciones, guardadoListaMutaciones, wtListaMutaciones, valorCribaListaMutaciones, ordenListaMutaciones, seleccionListaMutaciones, mutacionSeleccionadaListaMutaciones, false, seleccionFiltroMutadasListaMutaciones);
                            
                            break;
                            
                        case "MARCADORES":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            if(parametros[3].equals("MOSTRAR > 75%")){
                                
                                jComboBox31.setSelectedIndex(1);
                                
                            }else if(parametros[3].equals("MOSTRAR >= 90%")){
                                
                                jComboBox31.setSelectedIndex(2);
                                
                            }   
                            
                            Marcadores.cargarMarcadores(parametros[0], parametros[1], parametros[2]);
                            jComboBox31.setSelectedIndex(0);
                            
                            break;
                            
                        case "MUTACIONES MULTIPLES":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            if(parametros[3].equals("FALSE")){
                                
                                Mutaciones_Multiples.cargarMutacionesMultiples(parametros[0], parametros[1], parametros[2].toUpperCase(), false);
                                
                            }else if(parametros[3].equals("TRUE")){
                                
                                Mutaciones_Multiples.lectorSubcarpetasMutacionesMultiples(parametros[0], parametros[1], parametros[2].toUpperCase());
                                
                            }   
                            
                            break;
                            
                        case "MUTACIONES POSICION":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            if(parametros[3].equals("FALSE")){
                                
                                Mutaciones_Posicion.cargarMutacionesPosicion(parametros[0], parametros[1], parametros[2], false);
                                
                            }else if(parametros[3].equals("TRUE")){
                                
                                Mutaciones_Posicion.lectorSubcarpetas(parametros[0], parametros[1], parametros[2]);
                                
                            }   
                            
                            break;
                            
                        case "POLIMORFISMOS CODONES LISTA":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            if(parametros[2].equals("MOSTRAR 100%")){
                                
                                parametros[2] = "1";
                                
                            }else if(parametros[2].equals("MOSTRAR > 75%")){
                                
                                parametros[2] = "2";
                                
                            }   
                            
                            Polimorfismos_Codones.cargarPolimorfismosCodones(parametros[0], parametros[1], parametros[3], Integer.parseInt(parametros[2]));
                            break;
                            
                        case "CONSERVACION POL":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            switch (parametros[2]) {
                            
                                case "PR":
                                    
                                    parametros[2] = "1";
                                    break;
                                    
                                case "RT":
                                    
                                    parametros[2] = "2";
                                    break;
                                    
                                case "IN":
                                
                                    parametros[2] = "3";
                                    break;
                                    
                            }
                            
                            if(parametros[3].equals("VIH-2")){
                                
                                parametros[3] = "2";
                                
                            }else if(parametros[3].equals("VIH-1")){
                                
                                parametros[3] = "1";
                                
                            }   
                            
                            Conservacion_Pol.cargarConservacionPol(parametros[0], parametros[1], Integer.parseInt(parametros[3]), Integer.parseInt(parametros[2]));
                            
                            break;

                        case "OTRAS MUTACIONES POL":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            parametros[5] = parametros[5].toUpperCase();
                            
                            switch (parametros[2]) {
                            
                                case "PR":
                    
                                    parametros[2] = "1";
                                    break;
                            
                                case "RT":
                                
                                    parametros[2] = "2";
                                    break;
                                
                                case "IN":
                                    
                                    parametros[2] = "3";
                                    break;
                                
                            }
                        
                            switch (parametros[5]) {
                            
                                case "100%":
                                
                                    parametros[5] = "1";
                                    break;
                                
                                case "> 75%":
                                    
                                    parametros[5] = "2";
                                    break;
                                
                                case ">= 90%":
                                
                                    parametros[5] = "3";
                                    break;
                                
                            }
                        
                            if(parametros[3].equals("VIH-2")){
                                
                                parametros[3] = "2";
                                
                            }else if(parametros[3].equals("VIH-1")){
                                
                                parametros[3] = "1";
                                
                            }
                        
                            switch (parametros[4]) {
                            
                                case "LISTA":
                                
                                    parametros[4] = "1";
                                    break;
                                
                                case "TABLA":
                                    
                                    parametros[4] = "2";
                                    break;
                                    
                                case "TABLA RESUMEN":
                                    
                                    parametros[4] = "3";
                                    break;

                            }
                        
                            switch (parametros[4]) {
                            
                                case "1":
                                
                                    Otras_Mutaciones_Pol_Lista.cargarOtrasMutacionesPolLista(parametros[0], parametros[1], Integer.parseInt(parametros[2]), Integer.parseInt(parametros[5]), Integer.parseInt(parametros[3]));
                                    break;
                                
                                case "2":
                                
                                    Otras_Mutaciones_Pol_Tabla.cargarOtrasMutacionesPolTabla(parametros[0], parametros[1], Integer.parseInt(parametros[3]), Integer.parseInt(parametros[2]));
                                    break;
                                
                                case "3":
                                    
                                    Otras_Mutaciones_Pol_Tabla_Resumen.cargarOtrasMutacionesPolTablaResumen(parametros[0], parametros[1], Integer.parseInt(parametros[3]), Integer.parseInt(parametros[2]));
                                    break;
                                
                            }
                        
                            break;

                        case "MDR TRIPLETES":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            
                            switch (parametros[2]) {
                                
                                case "MDR-IP":
                                    
                                    parametros[2] = "1";
                                    break;
                            
                                case "MDR-ITIAN":
                                    
                                    parametros[2] = "2";
                                    break;
                                
                                case "MDR-ITINAN":
                                
                                    parametros[2] = "3";
                                    break;
                                
                                case "MDR-INI":
                                    
                                    parametros[2] = "4";
                                    break;
                                    
                                case "MDR-ICA":
                                    
                                    parametros[2] = "12";
                                    break;
                               
                            }
                            
                            int tipoMDRCodones = Integer.parseInt(parametros[2]);
                             
                            codonesAnalizarMDR_ = new String[75];
                            
                            if(tipoMDRCodones != 0){
                                
                                archivoCargaMDRCodones = parametros[0];
                                archivoGuardadoMDRCodones = parametros[1];
                                
                                if(parametros[3].equals("FALSE")){
                                    
                                    seleccionarCodonMDRCodones_ = "";
                                    
                                }else{
                                    
                                    seleccionarCodonMDRCodones_ = parametros[4];
                                    
                                }
                                
                                for(int R = 0; R < codonesAnalizarMDR_.length; R++){
                                    
                                    codonesAnalizarMDR_[R] = "";
                                    
                                }
                                
                                if(!seleccionarCodonMDRCodones_.equals("")){
                                    
                                    contadorMDRCodones = 0;
                                    
                                    for(int A = 0; A < seleccionarCodonMDRCodones_.length(); A++){
                                        
                                        if(seleccionarCodonMDRCodones_.toCharArray()[A] != ','){
                                            
                                            codonesAnalizarMDR_[contadorMDRCodones] = codonesAnalizarMDR_[contadorMDRCodones] + seleccionarCodonMDRCodones_.toCharArray()[A];
                                            
                                        }else{
                                            
                                            contadorMDRCodones++;
                                            
                                        }
                                        
                                    }
                                    
                                }
                                
                                for (String CodonesAnalizarMDR : codonesAnalizarMDR_) {
                                                                        
                                    if (CodonesAnalizarMDR.equals("") && !codonesAnalizarMDR_[0].equals("")) {
                                                                                
                                        break;
                                
                                    }
                                                                
                                    if (parametros[3].equals("TRUE")) {
                                
                                        MDR_Codones.cargarMDRCodones(archivoCargaMDRCodones, archivoGuardadoMDRCodones + "/" + CodonesAnalizarMDR + ".html", tipoMDRCodones, CodonesAnalizarMDR);
                            
                                    }else {
                                
                                        MDR_Codones.cargarMDRCodones(archivoCargaMDRCodones, archivoGuardadoMDRCodones + "/", tipoMDRCodones, CodonesAnalizarMDR);
                                        break;
                            
                                    }
                                }
                                
                            } 
                            
                            break;
                            
                        case "MDR ADQUIRIDAS":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            
                            entradaMDRAdquiridas = parametros[0];
                            salidaMDRAdquiridas = parametros[1];
                            proteinaMDRAdquiridas = 0;
                            
                            switch (parametros[2]) {
                                
                                case "MDR-IP":
                                    
                                    parametros[2] = "1";
                                    break;
                                    
                                case "MDR-ITIAN":
                                    
                                    parametros[2] = "2";
                                    break;
                                    
                                case "MDR-ITINAN":
                                    
                                    parametros[2] = "3";
                                    break;
                                    
                                case "MDR-INI":
                                    
                                    parametros[2] = "4";
                                    break;
                            
                                case "MDR-ICA":
                                    
                                    parametros[2] = "12";
                                    break;
                                    
                            }
                            
                            switch (parametros[3]) {
                            
                                case "LISTA":
                                    
                                    parametros[3] = "1";
                                    break;
                                    
                                case "TABLA":
                                    
                                    parametros[3] = "2";
                                    break;
                                    
                                case "TABLA RESUMEN MDR":
                                
                                    parametros[3] = "3";
                                    break;
                                    
                            }
                            
                            parametroVIH1MDRAdquiridas = false;
                            parametroVIH2MDRAdquiridas = false;
                            
                            if(parametros[4].equals("VIH-1")){
                                
                                parametroVIH1MDRAdquiridas = true;
                                
                            }else if(parametros[4].equals("VIH-2")){
                                
                                parametroVIH2MDRAdquiridas = true;
                                
                            }  
                            
                            if(Integer.parseInt(parametros[3]) == 1 || Integer.parseInt(parametros[3]) == 3){
                                
                                if(Integer.parseInt(parametros[2])== 1 && parametroVIH1MDRAdquiridas && (Integer.parseInt(parametros[3]) == 1 || Integer.parseInt(parametros[3]) == 3)){
                                    
                                    proteinaMDRAdquiridas = 1;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 2 && parametroVIH1MDRAdquiridas && (Integer.parseInt(parametros[3]) == 1 || Integer.parseInt(parametros[3]) == 3)){
                                    
                                    proteinaMDRAdquiridas = 2;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 3 && parametroVIH1MDRAdquiridas && (Integer.parseInt(parametros[3]) == 1 || Integer.parseInt(parametros[3]) == 3)){
                                    
                                    proteinaMDRAdquiridas = 3;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 4 && parametroVIH1MDRAdquiridas && (Integer.parseInt(parametros[3]) == 1 || Integer.parseInt(parametros[3]) == 3)){
                                    
                                    proteinaMDRAdquiridas = 4;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 12 && parametroVIH1MDRAdquiridas && (Integer.parseInt(parametros[3]) == 1 || Integer.parseInt(parametros[3]) == 3)){
                                
                                    proteinaMDRAdquiridas = 12;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 1 && parametroVIH2MDRAdquiridas && (Integer.parseInt(parametros[3]) == 1 || Integer.parseInt(parametros[3]) == 3)){
                                    
                                    proteinaMDRAdquiridas = 5;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 2 && parametroVIH2MDRAdquiridas && (Integer.parseInt(parametros[3]) == 1 || Integer.parseInt(parametros[3]) == 3)){
                                    
                                    proteinaMDRAdquiridas = 6;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 4 && parametroVIH2MDRAdquiridas && (Integer.parseInt(parametros[3]) == 1 || Integer.parseInt(parametros[3]) == 3)){
                                    
                                    proteinaMDRAdquiridas = 7;
                                    
                                }
                                
                                if(Integer.parseInt(parametros[3]) == 1){
                                    
                                    Lista_MDR.cargarListaMDR(entradaMDRAdquiridas, salidaMDRAdquiridas, proteinaMDRAdquiridas);
                                    
                                }else if(Integer.parseInt(parametros[3]) == 3){
                                    
                                    Tabla_Resumen_MDR.cargarTablaResumenMDR(entradaMDRAdquiridas, salidaMDRAdquiridas, proteinaMDRAdquiridas);
                                    
                                }
                                
                            }else if(Integer.parseInt(parametros[3]) == 2){
                                
                                if(Integer.parseInt(parametros[2]) == 1 && parametroVIH1MDRAdquiridas && Integer.parseInt(parametros[3]) == 2){
                                    
                                    proteinaMDRAdquiridas = 1;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 2 && parametroVIH1MDRAdquiridas && Integer.parseInt(parametros[3]) == 2){
                                    
                                    proteinaMDRAdquiridas = 2;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 3 && parametroVIH1MDRAdquiridas && Integer.parseInt(parametros[3]) == 2){
                                    
                                    proteinaMDRAdquiridas = 3;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 4 && parametroVIH1MDRAdquiridas && Integer.parseInt(parametros[3]) == 2){
                                    
                                    proteinaMDRAdquiridas = 4;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 12 && parametroVIH1MDRAdquiridas && Integer.parseInt(parametros[3]) == 2){
                                
                                    proteinaMDRAdquiridas = 12;
                                
                                }else if(Integer.parseInt(parametros[2]) == 1 && parametroVIH2MDRAdquiridas && Integer.parseInt(parametros[3]) == 2){
                                    
                                    proteinaMDRAdquiridas = 5;
                                    
                                }else if(Integer.parseInt(parametros[2]) == 2 && parametroVIH2MDRAdquiridas && Integer.parseInt(parametros[3]) == 2){
                                    
                                    proteinaMDRAdquiridas = 6;
                                    
                                }
                                
                                Tabla_MDR.CargarTablaMDR(entradaMDRAdquiridas, salidaMDRAdquiridas, proteinaMDRAdquiridas);
                                
                            }   
                            
                            break;

                        case "MDR TRANSMITIDAS":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            entradaMDRTransmitidas = parametros[0];
                            salidaMDRTransmitidas = parametros[1];
                            proteinaMDRTransmitidas = 0;
                            
                            switch (parametros[2]) {
                            
                                case "TDR-IP":
                                    
                                    parametros[2] = "1";
                                    break;
                                    
                                case "TDR-ITIAN":
                                    
                                    parametros[2] = "2";
                                    break;
                                    
                                case "TDR-ITINAN":
                                
                                    parametros[2] = "3";
                                    break;
                                    
                                case "TDR-INI":
                                
                                    parametros[2] = "4";
                                    break;

                            }
                            
                            switch (parametros[3]) {
                                
                                case "LISTA":
                                    
                                    parametros[3] = "1";
                                    break;
                                    
                                case "TABLA":
                                    
                                    parametros[3] = "2";
                                    break;
                                    
                                case "TABLA RESUMEN TDR":
                                    
                                    parametros[3] = "3";
                                    break;
                                    
                            }
                            
                            if(Integer.parseInt(parametros[3]) == 1 || Integer.parseInt(parametros[3]) == 3){
                                
                                switch (Integer.parseInt(parametros[2])) {
                            
                                    case 1:
                                        
                                        proteinaMDRTransmitidas = 8;
                                        break;
                            
                                    case 2:
                                
                                        proteinaMDRTransmitidas = 9;
                                        break;
                                        
                                    case 3:
                                        
                                        proteinaMDRTransmitidas = 10;
                                        break;
                                        
                                    case 4:
                                
                                        proteinaMDRTransmitidas = 11;
                                        break;        
                            
                                }
                                
                                if(Integer.parseInt(parametros[3]) == 1){
                                    
                                    Lista_MDR.cargarListaMDR(entradaMDRTransmitidas, salidaMDRTransmitidas, proteinaMDRTransmitidas);
                                    
                                }else if(Integer.parseInt(parametros[3]) == 3){
                                    
                                    Tabla_Resumen_MDR.cargarTablaResumenMDR(entradaMDRTransmitidas, salidaMDRTransmitidas, proteinaMDRTransmitidas);
                                    
                                }
                                
                            }else if(Integer.parseInt(parametros[3]) == 2){
                                
                                switch (Integer.parseInt(parametros[2])) {

                                    case 1:

                                        proteinaMDRTransmitidas = 8;
                                        break;

                                    case 2:

                                        proteinaMDRTransmitidas = 9;
                                        break;

                                    case 3:

                                        proteinaMDRTransmitidas = 10;
                                        break;

                                    case 4:

                                        proteinaMDRTransmitidas = 11;
                                        break;

                                }
                                
                                Tabla_MDR.CargarTablaMDR(entradaMDRTransmitidas, salidaMDRTransmitidas, proteinaMDRTransmitidas);
                                
                            }   
                            
                            break;

                        case "TRADUCCION":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            parametros[4] = parametros[4].toUpperCase();
                            
                            selectorFiltroTraduccion = new int[5];
                            traducirFiltroTraduccion = 0;
                            traducirNoFiltroTraduccion = 0;
                            gapsNoFiltroTraduccion = 0;
                            marcoTraduccion = Integer.parseInt(parametros[4]);
                            
                            if(Integer.parseInt(parametros[2]) == 1){
                                
                                traducirNoFiltroTraduccion = 1;
                                jCheckBox28.setSelected(true);
                                
                            }else{
                                
                                jCheckBox28.setSelected(false);
                                
                            }   
                            
                            if(Integer.parseInt(parametros[3]) == 1){
                                
                                gapsNoFiltroTraduccion = 1;
                                jCheckBox27.setSelected(true);
                                
                            }else{
                                
                                jCheckBox27.setSelected(true);
                                
                            }   
                            
                            archivoEntradaTraduccion = "";
                            directorioSalidaTraduccion = parametros[1];
                            carpetaEntradaTraduccion = parametros[0];
                            seleccionFiltroTraduccion = 0;
                            
                            Generador_Archivos_Traductor_Gaps.cargarGeneradorArchivosTraductorGaps(carpetaEntradaTraduccion, archivoEntradaTraduccion, selectorFiltroTraduccion,
                                    traducirNoFiltroTraduccion, gapsNoFiltroTraduccion, marcoTraduccion, directorioSalidaTraduccion, seleccionFiltroTraduccion, traducirFiltroTraduccion, "");
                            
                            jCheckBox28.setSelected(false);
                            jCheckBox27.setSelected(false);
                            
                            break;
                            
                        case "CORTAR SECUENCIAS":
                            
                            parametros[2] = parametros[2].toUpperCase();
                            parametros[3] = parametros[3].toUpperCase();
                            
                            Cortar_Secuencias.cargarCortarSecuencias(parametros[0], parametros[1], Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]));
                            break;
                            
                        case "ELIMINAR STOPS":
                            
                            Eliminar_Stops.cargarEliminarStops(parametros[0], parametros[1]);
                            break;                          
                            
                    }
            
                }
                
            }
                        
        }catch(Exception e){
        
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                    
            Logger.getLogger(Programar_Funciones.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
}
