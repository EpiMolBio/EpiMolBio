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

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Estilo_Tablas_html {
    
    //Devuelve el estilo de las tablas de las salidas .html.
    
    public static String cssTabla(){
        
        String cssTabla = " <style>\n" +
            " \n" +    
            "   html {\n" +
            "		font-family: Arial, sans-serif;\n" +	
            "	}\n" +
            " 	table {\n" +
            "		border-collapse: collapse;\n" +
            "		width: 100%;\n" +
            "		max-width: 800px;\n" +
            "		margin: 0 auto;\n" +
            "		font-size: 14px; /* Modificamos el tamaño de letra a 14px */\n" +
            "		line-height: 1.5;\n" +
            "		text-align: left;\n" +
            "	}\n" +
            "	th, td {\n" +
            "		padding: 10px;\n" +
            "		border: 1px solid #ccc;\n" +
            "		text-align: center;\n" +
            "		white-space: nowrap;\n" +
            "		background-color: transparent;\n" +
            "	}\n" +
            "	th {\n" +
            "		font-weight: bold;\n" +
            "		\n" +
            "	}\n" +
            "	@media (max-width: 767px) {\n" +
            "		table {\n" +
            "			font-size: 12px; /* Modificamos el tamaño de letra a 12px */\n" +
            "		}\n" +
            "		th, td {\n" +
            "			padding: 5px;\n" +
            "		}\n" +
            "	}\n" +
            "  \n" +
            " </style>";
        
        return cssTabla;
        
    }
    
}
