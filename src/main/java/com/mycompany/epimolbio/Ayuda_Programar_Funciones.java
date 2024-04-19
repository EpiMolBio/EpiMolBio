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

import static com.mycompany.epimolbio.Interfaz.jCheckBox1;
import static com.mycompany.epimolbio.Interfaz.jCheckBox10;
import static com.mycompany.epimolbio.Interfaz.jCheckBox11;
import static com.mycompany.epimolbio.Interfaz.jCheckBox12;
import static com.mycompany.epimolbio.Interfaz.jCheckBox13;
import static com.mycompany.epimolbio.Interfaz.jCheckBox16;
import static com.mycompany.epimolbio.Interfaz.jCheckBox17;
import static com.mycompany.epimolbio.Interfaz.jCheckBox19;
import static com.mycompany.epimolbio.Interfaz.jCheckBox2;
import static com.mycompany.epimolbio.Interfaz.jCheckBox20;
import static com.mycompany.epimolbio.Interfaz.jCheckBox21;
import static com.mycompany.epimolbio.Interfaz.jCheckBox22;
import static com.mycompany.epimolbio.Interfaz.jCheckBox23;
import static com.mycompany.epimolbio.Interfaz.jCheckBox24;
import static com.mycompany.epimolbio.Interfaz.jCheckBox25;
import static com.mycompany.epimolbio.Interfaz.jCheckBox26;
import static com.mycompany.epimolbio.Interfaz.jCheckBox27;
import static com.mycompany.epimolbio.Interfaz.jCheckBox28;
import static com.mycompany.epimolbio.Interfaz.jCheckBox29;
import static com.mycompany.epimolbio.Interfaz.jCheckBox31;
import static com.mycompany.epimolbio.Interfaz.jCheckBox32;
import static com.mycompany.epimolbio.Interfaz.jCheckBox35;
import static com.mycompany.epimolbio.Interfaz.jCheckBox36;
import static com.mycompany.epimolbio.Interfaz.jCheckBox38;
import static com.mycompany.epimolbio.Interfaz.jCheckBox39;
import static com.mycompany.epimolbio.Interfaz.jCheckBox4;
import static com.mycompany.epimolbio.Interfaz.jCheckBox40;
import static com.mycompany.epimolbio.Interfaz.jCheckBox41;
import static com.mycompany.epimolbio.Interfaz.jCheckBox5;
import static com.mycompany.epimolbio.Interfaz.jCheckBox7;
import static com.mycompany.epimolbio.Interfaz.jCheckBox8;
import static com.mycompany.epimolbio.Interfaz.jCheckBox9;
import static com.mycompany.epimolbio.Interfaz.jComboBox1;
import static com.mycompany.epimolbio.Interfaz.jComboBox10;
import static com.mycompany.epimolbio.Interfaz.jComboBox11;
import static com.mycompany.epimolbio.Interfaz.jComboBox12;
import static com.mycompany.epimolbio.Interfaz.jComboBox15;
import static com.mycompany.epimolbio.Interfaz.jComboBox17;
import static com.mycompany.epimolbio.Interfaz.jComboBox2;
import static com.mycompany.epimolbio.Interfaz.jComboBox21;
import static com.mycompany.epimolbio.Interfaz.jComboBox22;
import static com.mycompany.epimolbio.Interfaz.jComboBox23;
import static com.mycompany.epimolbio.Interfaz.jComboBox24;
import static com.mycompany.epimolbio.Interfaz.jComboBox27;
import static com.mycompany.epimolbio.Interfaz.jComboBox28;
import static com.mycompany.epimolbio.Interfaz.jComboBox29;
import static com.mycompany.epimolbio.Interfaz.jComboBox3;
import static com.mycompany.epimolbio.Interfaz.jComboBox31;
import static com.mycompany.epimolbio.Interfaz.jComboBox4;
import static com.mycompany.epimolbio.Interfaz.jComboBox6;
import static com.mycompany.epimolbio.Interfaz.jComboBox8;
import static com.mycompany.epimolbio.Interfaz.jComboBox9;
import static com.mycompany.epimolbio.Interfaz.jTextField10;
import static com.mycompany.epimolbio.Interfaz.jTextField11;
import static com.mycompany.epimolbio.Interfaz.jTextField13;
import static com.mycompany.epimolbio.Interfaz.jTextField14;
import static com.mycompany.epimolbio.Interfaz.jTextField15;
import static com.mycompany.epimolbio.Interfaz.jTextField16;
import static com.mycompany.epimolbio.Interfaz.jTextField17;
import static com.mycompany.epimolbio.Interfaz.jTextField18;
import static com.mycompany.epimolbio.Interfaz.jTextField19;
import static com.mycompany.epimolbio.Interfaz.jTextField2;
import static com.mycompany.epimolbio.Interfaz.jTextField20;
import static com.mycompany.epimolbio.Interfaz.jTextField21;
import static com.mycompany.epimolbio.Interfaz.jTextField22;
import static com.mycompany.epimolbio.Interfaz.jTextField24;
import static com.mycompany.epimolbio.Interfaz.jTextField25;
import static com.mycompany.epimolbio.Interfaz.jTextField26;
import static com.mycompany.epimolbio.Interfaz.jTextField27;
import static com.mycompany.epimolbio.Interfaz.jTextField29;
import static com.mycompany.epimolbio.Interfaz.jTextField31;
import static com.mycompany.epimolbio.Interfaz.jTextField33;
import static com.mycompany.epimolbio.Interfaz.jTextField36;
import static com.mycompany.epimolbio.Interfaz.jTextField38;
import static com.mycompany.epimolbio.Interfaz.jTextField39;
import static com.mycompany.epimolbio.Interfaz.jTextField40;
import static com.mycompany.epimolbio.Interfaz.jTextField41;
import static com.mycompany.epimolbio.Interfaz.jTextField42;
import static com.mycompany.epimolbio.Interfaz.jTextField43;
import static com.mycompany.epimolbio.Interfaz.jTextField44;
import static com.mycompany.epimolbio.Interfaz.jTextField45;
import static com.mycompany.epimolbio.Interfaz.jTextField46;
import static com.mycompany.epimolbio.Interfaz.jTextField47;
import static com.mycompany.epimolbio.Interfaz.jTextField48;
import static com.mycompany.epimolbio.Interfaz.jTextField49;
import static com.mycompany.epimolbio.Interfaz.jTextField5;
import static com.mycompany.epimolbio.Interfaz.jTextField50;
import static com.mycompany.epimolbio.Interfaz.jTextField52;
import static com.mycompany.epimolbio.Interfaz.jTextField53;
import static com.mycompany.epimolbio.Interfaz.jTextField54;
import static com.mycompany.epimolbio.Interfaz.jTextField6;
import static com.mycompany.epimolbio.Interfaz.jTextField7;
import static com.mycompany.epimolbio.Interfaz.jTextField8;
import static com.mycompany.epimolbio.Interfaz.jTextField9;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Ayuda_Programar_Funciones {
    
    /*Genera una cadena de texto que sirve como ayuda para usar la función de programar funciones.
    Está información se muestra pulsando el botón de programar funciones.*/
    
    public static String cargarAyudaProgramarFunciones(String funcion){
        
        String retorno = "";
            
        switch (funcion) {
            
            //Se muestra una información generica en caso de no seleccionar ninguna opción válida.
            
            case "":
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);;;;;;";
                break;
                
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            el rastreador específico del SARS-CoV-2.*/
                
            case "RASTREADOR ESPECIFICO_A":
                
            {
                    
                int tipoSecuencia = 0;
                int proteinaElegidaInterfaz = jComboBox12.getSelectedIndex();
                    
                tipoSecuencia = proteinaElegidaInterfaz;
                    
                int tipoSecuenciasInt = 0;
                    
                if(jCheckBox29.isSelected()){
                        
                    tipoSecuenciasInt = 1;
                        
                }else if(jCheckBox38.isSelected()){
                        
                    tipoSecuenciasInt = 2;
                        
                }       
                
                String tipoSecuenciasStr = "";
                
                if(tipoSecuenciasInt == 1){
                        
                    tipoSecuenciasStr = "AA";
                        
                }else if(tipoSecuenciasInt == 2){
                        
                    tipoSecuenciasStr = "NT";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + tipoSecuenciasStr + ";" + tipoSecuencia + ";RASTREADOR ESPECIFICO";
                
                break;
                    
            }
             
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            el rastreador específico general.*/
            
            case "RASTREADOR ESPECIFICO_B":
               
            {
                int tipoSecuencia = 29;
                int tipoSecuenciasInt = jComboBox17.getSelectedIndex();
                String tipoSecuenciasStr = "";
                    
                if(tipoSecuenciasInt == 1){
                        
                    tipoSecuenciasStr = "AA";
                        
                }else if(tipoSecuenciasInt == 2){
                        
                    tipoSecuenciasStr = "NT";
                        
                }       
                    
                String parametro_4 = jTextField25.getText();
                String parametro_5 = jTextField24.getText();
                String parametro_6 = jTextField27.getText().toUpperCase();
                String parametro_7 = jTextField26.getText().toUpperCase();
                String parametro_8 = jTextField19.getText();
                    
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + tipoSecuenciasStr + ";" + tipoSecuencia + ";" + parametro_4 + ";" + parametro_5 + ";" + parametro_6 + ";" + parametro_7 + ";" + parametro_8 + ";RASTREADOR ESPECIFICO";
                    
                break;
                    
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            la Tabla Mutaciones.*/
            
            case "TABLA MUTACIONES":
            
            {
                
                String parametro_2 = jTextField53.getText().toUpperCase();
                String parametro_3 = jTextField54.getText();
                String parametro_4 = jTextField44.getText();
                String parametro_5 = "";
                
                if(parametro_4.equals("")){
                        
                    parametro_4 = "NULO";
                        
                }
                
                if(jCheckBox40.isSelected()){
                        
                    parametro_5 = "TODO";
                        
                }else if(jCheckBox39.isSelected()){
                        
                    parametro_5 = "MUTADAS";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO CSV)/OUTPUT (CSV FILE);" + parametro_2 + ";" + parametro_3 + ";TABLA;" + parametro_4 + ";" + parametro_5 + ";TABLA MUTACIONES";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones en cso de usar 
            la opción de subcarpetas de Tabla de Mutaciones.*/
            
            case "TABLA MUTACIONES SC":
                
            {
                
                String parametro_2 = jTextField53.getText().toUpperCase();
                String parametro_3 = jTextField54.getText();
                String parametro_4 = jTextField44.getText();
                String parametro_5 = "";
                
                if(parametro_4.equals("")){
                        
                    parametro_4 = "NULO";
                        
                } 
                
                if(jCheckBox40.isSelected()){
                        
                    parametro_5 = "TODO";
                        
                }else if(jCheckBox39.isSelected()){
                        
                    parametro_5 = "MUTADAS";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO CSV)/OUTPUT (CSV FILE);" + parametro_2 + ";" + parametro_3 + ";" + parametro_4 + ";" + parametro_5 + ";TABLA MUTACIONES SC";
                  
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Fusionar Secuencias.*/
            
            case "FUSIONAR SECUENCIAS":
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);FUSIONAR SECUENCIAS";
                break;
                
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Fusionar Archivos.*/
                
            case "FUSIONAR ARCHIVOS":
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO FASTA)/OUTPUT (FASTA FILE);FUSIONAR ARCHIVOS";
                break;
                
            /*Muestra la información necesaría para la funcionalidad de programar funciones en caso de usar 
            Filtro Múltiple (pertenece a Filtrado por Encabezado), no accesible desde el interfaz.*/
                
            case "FILTRO":
            
            {
                
                String parametro_2 = "";
                
                if(jCheckBox26.isSelected()){
                        
                    parametro_2 += "1,";
                        
                }else{
                        
                    parametro_2 += "0,";
                        
                }
                
                if(jCheckBox23.isSelected()){
                        
                    parametro_2 += "1,";
                        
                }else{
                        
                    parametro_2 += "0,";
                        
                }
                
                if(jCheckBox20.isSelected()){
                        
                    parametro_2 += "1,";
                        
                }else{
                        
                    parametro_2 += "0,";
                        
                }       
                
                if(jCheckBox24.isSelected()){
                        
                    parametro_2 += "1,";
                        
                }else{
                        
                    parametro_2 += "0,";
                        
                }
                
                if(jCheckBox22.isSelected()){
                        
                    parametro_2 += "1";
                        
                }else{
                        
                    parametro_2 += "0";
                        
                }
                
                String parametro_3 = jTextField39.getText();
                String parametro_4;
                
                if(jCheckBox21.isSelected()){
                        
                    parametro_4 = "2";
                        
                }else{
                        
                    parametro_4 = "1";
                        
                }
                
                String parametro_5;
                
                if(jCheckBox25.isSelected()){
                        
                    parametro_5 = "1";
                        
                }else{
                        
                    parametro_5 = "0";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + parametro_2 + ";0;0;1;" + parametro_4 + ";" + parametro_5 + ";" + parametro_3  + ";FILTRO";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Filtrado por Encabezado.*/
            
            case "FILTRO UNICO":
                
            {
                
                String parametro_2 = "";
                
                if(jCheckBox26.isSelected()){
                        
                    parametro_2 += "1,";
                        
                }else{
                        
                    parametro_2 += "0,";
                        
                }
                
                if(jCheckBox23.isSelected()){
                        
                    parametro_2 += "1,";
                        
                }else{
                        
                    parametro_2 += "0,";
                        
                }
                
                if(jCheckBox20.isSelected()){
                        
                    parametro_2 += "1,";
                        
                }else{
                        
                    parametro_2 += "0,";
                        
                }
                
                if(jCheckBox24.isSelected()){
                        
                    parametro_2 += "1,";
                        
                }else{
                        
                    parametro_2 += "0,";
                        
                }
                
                if(jCheckBox22.isSelected()){
                        
                    parametro_2 += "1";
                        
                }else{
                        
                    parametro_2 += "0";
                        
                }
                
                String parametro_4;
                
                if(jCheckBox21.isSelected()){
                        
                    parametro_4 = "2";
                        
                }else{
                        
                    parametro_4 = "1";
                        
                }
                
                String parametro_3 = jTextField39.getText();
               
                String parametro_5;
                
                if(jCheckBox25.isSelected()){
                        
                    parametro_5 = "1";
                        
                }else{
                        
                    parametro_5 = "0";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + parametro_2 + ";" + parametro_3 + ";" + parametro_4 + ";" + parametro_5 +";FILTRO UNICO";
                    
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Traducción.*/
            
            case "TRADUCCTOR":
                
            {
                    
                String parametro_2;
                    
                if(jCheckBox28.isSelected()){
                        
                    parametro_2 = "1";
                        
                }else{
                        
                    parametro_2 = "0";
                        
                }
                
                String parametro_3;
                
                if(jCheckBox27.isSelected()){
                        
                    parametro_3 = "1";
                        
                }else{
                        
                    parametro_3 = "0";
                        
                }
                
                int parametro_4 = jComboBox22.getSelectedIndex()-1;
                    
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + parametro_2 + ";" + parametro_3 + ";" + parametro_4 + ";TRADUCCION";
                    
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Buscar y Reemplazar.*/
            
            case "BUSCAR/REEMPLAZAR":
                
            {
                    
                String parametro_2 = jTextField41.getText().toUpperCase();
                String parametro_3 = jTextField42.getText().toUpperCase();
                String parametro_4 = "";
                
                if(jCheckBox32.isSelected()){
                        
                    parametro_4 = "SECUENCIA";
                        
                }else if(jCheckBox31.isSelected()){
                        
                    parametro_4 = "TODO";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + parametro_2 + ";" + parametro_3 + ";" + parametro_4 + ";BUSCAR/REEMPLAZAR";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Filtro Específico.*/
            
            case "FILTRO ESPECIFICO":
            
            {
                
                String parametro_2 = jTextField40.getText();
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + parametro_2 + ";FILTRO ESPECIFICO";
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Secuencias Únicas.*/
            
            case "SECUENCIAS UNICAS":
                
            {
                String parametro_2 = jTextField48.getText();
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + parametro_2 + ";SECUENCIAS UNICAS";
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Búsqueda de Secuencias.*/
            
            case "BUSQUEDA SECUENCIAS":
                
            {
                    
                int parametro_2_Entero = jComboBox23.getSelectedIndex();
                String parametro_2 = "";
                
                if(parametro_2_Entero == 1){
                                            
                    parametro_2 = "SALIDA (ARCHIVO CSV)/OUTPUT (CSV FILE);CSV";
                       
                }else if(parametro_2_Entero == 2){
                        
                    parametro_2 = "SALIDA (CARPETA)/OUTPUT (FOLDER);FASTA";
                        
                }
                
                String parametro_3 = jTextField43.getText().toUpperCase();
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);" + parametro_2 + ";" + parametro_3 + ";BUSQUEDA SECUENCIAS";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Filtrado de Secuencias Parciales.*/
            
            case "FILTRADO SECUENCIAS PARCIALES":
                
            {
                
                int parametro_2_Entero = jComboBox24.getSelectedIndex();
                String parametro_2 = "";
                
                if(parametro_2_Entero == 1){
                        
                    parametro_2 = "AA";
                        
                }else if(parametro_2_Entero == 2){
                        
                    parametro_2 = "NT";
                        
                }
                
                String parametro_3 = jTextField45.getText();
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (FOLDER)/OUTPUT (FOLDER);" + parametro_2 + ";" + parametro_3 + ";FILTRADO SECUENCIAS PARCIALES";
                
                break;
                    
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Contar Secuencias Mutadas.*/
            
            case "CONTAR SECUENCIAS MUTADAS":
                
            {
                
                String parametro_2 = jTextField47.getText().toUpperCase();
                boolean parametro_3_BoolAa = jCheckBox36.isSelected();
                boolean parametro_3_BoolNt = jCheckBox35.isSelected();
                String parametro_3 = "";
                
                if(parametro_3_BoolAa == true){
                        
                    parametro_3 = "AA";
                        
                }else if(parametro_3_BoolNt == true){
                        
                    parametro_3 = "NT";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO CSV)/OUTPUT (CSV FILE);" + parametro_2 + ";" + parametro_3 + ";CONTAR SECUENCIAS MUTADAS";
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            la tabla de Contar Secuencias.*/
            
            case "CONTAR SECUENCIAS TABLA":
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO CSV)/OUTPUT (CSV FILE);CONTAR SECUENCIAS TABLA";
                break;
                
            /*Muestra la información necesaría para la funcionalidad de programar funciones en caso de usar 
            Alineamientos Multiples, no conservando las inserciones.*/
                
            case "ALINEAMIENTOS MULTIPLES":
                
            {
                
                String parametro_3 = jTextField33.getText();
                String parametro_4 = jTextField31.getText().toUpperCase();
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + parametro_3 + ";" + parametro_4 +";ALINEAMIENTOS MULTIPLES";
                
                break;
                
            }
                
            /*Muestra la información necesaría para la funcionalidad de programar funciones en caso de usar 
            Alineamientos Multiples conservando las inserciones.*/
            
            case "ALINEAMIENTOS MULTIPLES A":
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);ALINEAMIENTOS MULTIPLES A";
                break;
                
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Eliminar Inserciones.*/
                
            case "ALINEADOR":
                
                String parametros_2 = jTextField46.getText().toUpperCase();
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + parametros_2 +";ALINEADOR";
                break;
                
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Rastreador Similitud.*/
                
            case "RASTREADOR SIMILITUD":
                
            {
                
                int parametro_2_entero = 0;
                
                if(jCheckBox12.isSelected()){
                        
                    parametro_2_entero = 1;
                        
                }else if(jCheckBox13.isSelected()){
                        
                    parametro_2_entero = 2;
                        
                }
                
                String parametro_2 = "";
                
                if(parametro_2_entero == 1){
                        
                    parametro_2 = "AMINOACIDOS";
                        
                }else if(parametro_2_entero == 2){
                        
                    parametro_2 = "NUCLEOTIDOS";
                        
                }       
                
                boolean parametro_3_Completo = jCheckBox11.isSelected();
                boolean parametro_3_Parcial = jCheckBox10.isSelected();
                String parametro_3 = "";
                String parametro_4 = "";
                String parametro_5 = "";
                
                if(parametro_3_Parcial == true){
                        
                    parametro_3 = "SELECCIONAR RANGO";
                    
                    if(jCheckBox13.isSelected()){
                        
                        parametro_4 = String.valueOf(Integer.parseInt(jTextField22.getText()) -1);
                        parametro_5 = jTextField21.getText();
                        
                    }else if(jCheckBox12.isSelected()){
                        
                        parametro_4 = jTextField22.getText();
                        parametro_5 = String.valueOf(Integer.parseInt(jTextField21.getText()) +3);
                        
                    }else{
                    
                        parametro_4 = jTextField22.getText();
                        parametro_5 = jTextField21.getText();
                        
                    }
                    
                }else if(parametro_3_Completo == true){
                        
                    parametro_3 = "RANGO COMPLETO";
                        
                }
                
                String parametro_6 = jTextField18.getText();
                String parametro_7 = jTextField20.getText().toUpperCase();
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + parametro_2 + ";" +parametro_3 + ";" + parametro_4 + ";" + parametro_5 + ";" + parametro_6 + ";" + parametro_7 +";RASTREADOR SIMILITUD";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones en caso de usar 
            la funcionalidad de Similitud de la sección de Homología.*/
            
            case "SIMILITUD":
            {
                String parametro_2 = jTextField5.getText().toUpperCase();
                String parametro_3 = jTextField7.getText();
                String parametro_4 = jTextField6.getText();
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" +parametro_3 + ";" + parametro_4 +";SIMILITUD";
                break;
                    
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Similitud Parcial.*/
            
            case "SIMILITUD PARCIAL":
                
            {
                
                String parametro_5 = "";
                
                if(jCheckBox9.isSelected()){
                        
                    parametro_5 = "ALINEAR";
                        
                }else if(jCheckBox8.isSelected()){
                        
                    parametro_5 = "NO ALINEAR";
                        
                }
                
                String parametro_3 = jTextField10.getText();
                String parametro_4 = jTextField8.getText();
                String parametro_2 = "2";
                String parametro_6 = jTextField9.getText().toUpperCase();
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" +parametro_3 + ";" + parametro_4 + ";" + parametro_5 + ";" + parametro_6 + ";SIMILITUD PARCIAL";
                    
                break;
                    
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Búsqueda de Secuencias Conservadas.*/
            
            case "DISENO SECUENCIAS CONSERVADAS":
                
            {

                int parametro_2_Entero = jComboBox15.getSelectedIndex();
                String parametro_2 = "";
                
                if(parametro_2_Entero == 1){
                        
                    parametro_2 = "TODO EL RANGO";
                        
                }else if(parametro_2_Entero == 2){
                        
                    parametro_2 = "SELECCIONAR RANGO";
                       
                }
                
                String parametro_3 = jTextField13.getText();
                
                if(!parametro_3.equals("")){
                    
                    parametro_3 = String.valueOf(Integer.parseInt(jTextField13.getText())-1);
                    
                }
                
                String parametro_4 = jTextField11.getText();
                String parametro_5 = jTextField14.getText();
                String parametro_6 = jTextField16.getText();
                String parametro_7 = jTextField15.getText();
                String parametro_8 = jTextField17.getText().toUpperCase();
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" +parametro_3 + ";" + parametro_4 + ";" + parametro_5 + ";" + parametro_6 + ";" + parametro_7 + ";" + parametro_8
                            + ";DISENO SECUENCIAS CONSERVADAS";
                                
                break;
                                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Frecuencia de Mutación.*/
            
            case "FRECUENCIA MUTACION":
                
            {
                
                String parametro_2 = "";
                
                if(jCheckBox17.isSelected()){
                        
                    parametro_2 = "NUCLEOTIDOS";
                        
                }else if(jCheckBox16.isSelected()){
                        
                    parametro_2 = "AMINOACIDOS";
                        
                }
                
                String parametro_3;
                
                if(jCheckBox19.isSelected()){
                        
                    parametro_3 = "ALINEAR";
                        
                }else{
                        
                    parametro_3 = "NO ALINEAR";
                        
                }
                
                String parametro_4 = jTextField36.getText().toUpperCase();
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO CSV)/OUTPUT (CSV FILE);" + parametro_2 + ";" +parametro_3 + ";" + parametro_4 +";FRECUENCIA MUTACION";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            el Coeficiente de variabilidad de Wu-Kabat.*/
            
            case "INDICE WU-KABAT":
            {
                String parametro_2 = jTextField38.getText();
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO CSV)/OUTPUT (CSV FILE);" + parametro_2 + ";INDICE WU-KABAT";
                break;
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            las funcionalidades de Consensos, tanto Ronda 1 como Rondas Sucesivas.*/
            
            case "RONDAS":
                
            {
                int parametro_2_Entero = jComboBox21.getSelectedIndex();
                String parametro_2 = "";
                
                if(parametro_2_Entero == 1){
                        
                    parametro_2 = "RONDA 1";
                        
                }else if(parametro_2_Entero == 2){
                        
                    parametro_2 = "RONDAS SUCESIVAS";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" + jTextField29.getText().toUpperCase() + ";CONSENSOS";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Conservación Individual.*/
            
            case "CONSERVACION INDIVIDUAL":
                
            {
                int parametro_4_Entero = jComboBox27.getSelectedIndex();
                String parametro_4 = "";
                
                if(parametro_4_Entero == 1){
                        
                    parametro_4 = "LISTA";
                        
                }else if(parametro_4_Entero == 2){
                        
                    parametro_4 = "TABLA HORIZONTAL";
                        
                }
                
                String parametro_3 = jTextField49.getText().toUpperCase();
                
                if(parametro_4.equals("LISTA")){
                        
                    retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_3 + ";" + parametro_4 + ";CONSERVACION INDIVIDUAL";
                        
                }else if(parametro_4.equals("TABLA HORIZONTAL")){
                        
                    retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_3 + ";" + parametro_4 + ";CONSERVACION INDIVIDUAL";
                        
                }
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Conservación Codones.*/
            
            case "CONSERVACION CODONES":
                
            {
                    
                int parametro_2_Entero = jComboBox28.getSelectedIndex();
                String parametro_2 = "";
                
                if(parametro_2_Entero == 1){
                        
                    parametro_2 = "MOSTRAR 100%";
                        
                }else if(parametro_2_Entero == 2){
                        
                    parametro_2 = "MOSTRAR > 75%";
                        
                }
                
                String parametro_3 = jTextField50.getText().toUpperCase();
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" + parametro_3 + ";CONSERVACION CODONES";
                 
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Posiciones Mutadas.*/
            
            case "LISTA MUTACIONES":
                
            {
                    
                String parametro_2 = jTextField53.getText().toUpperCase();
                String parametro_3 = jTextField54.getText();
                String parametro_4 = jTextField44.getText();
                String parametro_5 = "";
            
                if(jCheckBox40.isSelected()){
                        
                    parametro_5 = "TODAS POSICIONES";
                        
                }else if(jCheckBox39.isSelected()){
                        
                    parametro_5 = "POSICIONES MUTADAS";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" + parametro_3 + ";" + parametro_4 + ";" + parametro_5 + ";LISTA MUTACIONES";
                    
                break;
                
            }
                    
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Marcadores.*/
            
            case "MARCADORES":
                
            {
            
                String parametro_2 = jTextField53.getText().toUpperCase();
                int parametro_3_Entero = jComboBox31.getSelectedIndex();
                String parametro_3 = "";
                
                if(parametro_3_Entero == 1){
                        
                    parametro_3 = "MOSTRAR > 75%";
                        
                }else if(parametro_3_Entero == 2){
                        
                    parametro_3 = "MOSTRAR >= 90%";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" + parametro_3 + ";MARCADORES";
                    
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Mutaciones Múltiples.*/
            
            case "MUTACIONES MULTIPLES":
                
            {
                
                String parametro_2 = jTextField44.getText();
                String parametro_3 = "FALSE";
                
                if(jCheckBox41.isSelected()){
                        
                    parametro_3 = "TRUE";
                        
                
                }
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO CSV)/OUTPUT (CSV FILE);" + parametro_2 + ";" + parametro_3 +";MUTACIONES MULTIPLES";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Mutaciones por Posición.*/
            
            case "MUTACIONES POSICION":
                
            {
            
                String parametro_2 = jTextField44.getText();
                String parametro_3 = "FALSE";
                
                if(jCheckBox41.isSelected()){
                        
                    parametro_3 = "TRUE";
                        
            
                }
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO CSV)/OUTPUT (CSV FILE);" + parametro_2 + ";" + parametro_3 + ";MUTACIONES POSICION";
                    
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para 
            Polimorfismos Codones.*/
            
            case "POLIMORFISMOS CODONES LISTA":
                
            {
            
                int parametro_2_Entero = jComboBox29.getSelectedIndex();
                String parametro_2 = "";
                
                if(parametro_2_Entero == 1){
                        
                    parametro_2 = "MOSTRAR 100%";
                        
                }else if(parametro_2_Entero == 2){
                        
                    parametro_2 = "MOSTRAR > 75%";
                        
                }
                
                String parametro_3 = jTextField52.getText().toUpperCase();
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" + parametro_3 + ";POLIMORFISMOS CODONES LISTA";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Conservación Pol.*/
            
            case "CONSERVACION POL":
                
            {
                int parametro_2_Entero = jComboBox11.getSelectedIndex();
                String parametro_2 = "";
            
                switch (parametro_2_Entero) {
                
                    case 1:
                    
                        parametro_2 = "PR";
                        break;
                        
                    case 2:
                    
                        parametro_2 = "RT";
                        break;
                    
                    case 3:
                        
                        parametro_2 = "IN";
                        break;
                        
                    default:
                        
                        break;
                        
                }
            
                String parametro_3 = "VIH-1";
                
                if(jCheckBox7.isSelected()){
                        
                    parametro_3 = "VIH-2";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" + parametro_3 + ";CONSERVACION POL";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            Otras Mutaciones Pol.*/
            
            case "OTRAS MUTACIONES POL":
            
            {
                    
                int parametro_2_Entero = jComboBox8.getSelectedIndex();
                String parametro_2 = "";
                
                switch (parametro_2_Entero) {
                
                    case 1:
                        
                        parametro_2 = "PR";
                        break;
                        
                    case 2:
                        
                        parametro_2 = "RT";
                        break;
                        
                    case 3:
                        
                        parametro_2 = "IN";
                        break;
                        
                    default:
                        
                        break;
                        
                }
                
                String parametro_3 = "VIH-1";
                    
                if(jCheckBox5.isSelected()){
                        
                    parametro_3 = "VIH-2";
                        
                }
                
                int parametro_4_Entero = jComboBox10.getSelectedIndex();
                String parametro_4 = "";
                
                switch (parametro_4_Entero) {
                    
                    case 1:
                        
                        parametro_4 = "LISTA";
                        break;
                        
                    case 2:
                        
                        parametro_4 = "TABLA";
                        break;
                        
                    case 3:
                        
                        parametro_4 = "TABLA RESUMEN";
                        break;
                        
                    case 4:
                        
                        parametro_4 = "LISTA FORMATO TABLA";
                        break;
                        
                    default:
                        
                        break;
                        
                }
                
                int parametro_5_Entero = jComboBox9.getSelectedIndex();
                String parametro_5 = "";
                
                switch (parametro_5_Entero) {
                    
                    case 1:
                        
                        parametro_5 = "100%";
                        break;
                        
                    case 2:
                        
                        parametro_5 = "> 75%";
                        break;
                        
                    case 3:
                        
                        parametro_5 = ">= 90%";
                        break;
                        
                    default:
                        
                        break;
                    
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" + parametro_3 + ";" + parametro_4 + ";" + parametro_5 + ";OTRAS MUTACIONES POL";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            MDR Codones dentro de Virus/VIH.*/
            
            case "MDR TRIPLETES":
                
            {
                int parametro_2_Entero = jComboBox6.getSelectedIndex();
                String parametro_2 = "";
            
                switch (parametro_2_Entero) {
                
                    case 1:
                        
                        parametro_2 = "MDR-IP";
                        break;
                        
                    case 2:
                        
                        parametro_2 = "MDR-ITIAN";
                        break;
                        
                    case 3:
                        
                        parametro_2 = "MDR-ITINAN";
                        break;
                
                    case 4:
                        
                        parametro_2 = "MDR-INI";
                        break;
                        
                    case 5:
                        
                        parametro_2 = "MDR-ICA";
                        break;
                        
                }
                
                String parametro_3 = "FALSE";
                    
                if(jCheckBox4.isSelected()){
                        
                    parametro_3 = "TRUE";
                        
                }
                
                String parametro_4 = jTextField2.getText().toUpperCase();
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FOLDER);" + parametro_2 + ";" + parametro_3 + ";" + parametro_4 + ";MDR TRIPLETES";
                 
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            MDR Adquiridas dentro de Virus/VIH.*/
            
            case "MDR ADQUIRIDAS":
                
            {
                
                int parametro_2_Entrada = jComboBox1.getSelectedIndex();
                String parametro_2 = "";
                
                switch (parametro_2_Entrada) {
                    
                    case 1:
                        
                        parametro_2 = "MDR-IP";
                        break;
                        
                    case 2:
                    
                        parametro_2 = "MDR-ITIAN";
                        break;
                        
                    case 3:
                        
                        parametro_2 = "MDR-ITINAN";
                        break;
                        
                    case 4:
                        
                        parametro_2 = "MDR-INI";
                        break;
                        
                    case 5:
                        
                        parametro_2 = "MDR-ICA";
                        break;
                 
                }
                
                int parametro_3_Entero = jComboBox2.getSelectedIndex();
                String parametro_3 = "";
            
                switch (parametro_3_Entero) {
                
                    case 1:
                    
                        parametro_3 = "LISTA";
                        break;
                        
                    case 2:
                        
                        parametro_3 = "TABLA";
                        break;
                        
                    case 3:
                        
                        parametro_3 = "TABLA RESUMEN MDR";
                        break;
                        
                    default:
                        
                        break;
                        
                }
                
                String parametro_4 = "";
                    
                if(jCheckBox1.isSelected()){
                        
                    parametro_4 = "VIH-1";
                        
                }else if(jCheckBox2.isSelected()){
                        
                    parametro_4 = "VIH-2";
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" + parametro_3 + ";" + parametro_4 + ";MDR ADQUIRIDAS";
                
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones para usar 
            SDRM dentro de Virus/VIH.*/
            
            case "MDR TRANSMITIDAS":
            
            {
                    
                int parametro_2_Entero = jComboBox3.getSelectedIndex();
                String parametro_2 = "";
                
                switch (parametro_2_Entero) {
                
                    case 1:
                        
                        parametro_2 = "TDR-IP";
                        break;
                        
                    case 2:
                    
                        parametro_2 = "TDR-ITIAN";
                        break;
                        
                    case 3:
                    
                        parametro_2 = "TDR-ITINAN";
                        break;
                
                    case 4:
                        
                        parametro_2 = "TDR-INI";
                        break;
                        
                    default:
                        
                        break;
                }
                
                int parametro_3_Entero = jComboBox4.getSelectedIndex();
                    
                String parametro_3 = "";
            
                switch (parametro_3_Entero) {
                
                    case 1:
                        
                        parametro_3 = "LISTA";
                        break;
                
                    case 2:
                    
                        parametro_3 = "TABLA";
                        break;
                
                    case 3:
                    
                        parametro_3 = "TABLA RESUMEN TDR";
                        break;
                        
                    default:
                        
                        break;
                        
                }
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (ARCHIVO HTML)/OUTPUT (HTML FILE);" + parametro_2 + ";" + parametro_3 + ";MDR TRANSMITIDAS";
                  
                break;
                
            }
            
            /*Muestra la información necesaría para la funcionalidad de programar funciones en caso de usar 
            Cortar Secuencias, funcionalidad no disponible desde el interfaz.*/
            
            case "CORTAR SECUENCIAS":
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);" + "RANGO INFERIOR" + ";" + "RANGO SUPERIOR" + ";CORTAR SECUENCIAS";
                break;
                
            /*Muestra la información necesaría para la funcionalidad de programar funciones en caso de usar 
            Eliminar Stops, funcionalidad no disponible desde el interfaz.*/
                
            case "ELIMINAR STOPS":
                
                retorno = "ENTRADA (CARPETA)/INPUT (FOLDER);SALIDA (CARPETA)/OUTPUT (FOLDER);ELIMINAR STOPS";
                break;
                
        }
          
        return retorno;
        
    }
    
}