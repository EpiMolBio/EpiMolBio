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

import static com.mycompany.epimolbio.Interfaz.idioma;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Menu_Lateral {
    
    //Genera el head del menú lateral de los archivos en html.
    //Generates the head of the sidebar menu for files in html.
    
    public static String head(){
        
        String retornoHead = " <style type=\"text/css\">\n" +
        "      .menu {\n" +
        "\n" +
        "	  font-family: Verdana;\n" +
        "	  width: 1.2rem;\n" +
        "	  height: 2.7rem;\n" +
        "	  background: #225578;\n" +
        "	  border-radius: 0.5rem;\n" +
        "	  position: absolute;\n" +
        "	  top: 50%;\n" +
        "	  left: 0%;\n" +
        "	  font-family:sans-serif;\n" +
        "	  font-size: 2rem;\n" +
        "	  color:white;\n" +
        "	  text-align: center;\n" +
        "	  transition: left 0.4s;}\n" +
        "\n" +
        "      .checkbox:checked ~.menu{\n" +
        "\n" +
        "	  left:32%;\n" +
        "	  border-radius: 0 0.5rem 0.5rem 0;\n" +
        "	  \n" +
        "      }.left-panel{\n" +
        "\n" +
        "	  border-style: solid;\n" +
        "	  text-align: center;\n" +
        "	  color: black;\n" +
        "	  background-color: white;\n" +
        "	  width: 30%;\n" +
        "	  height: 100%;\n" +
        "	  position: absolute;\n" +
        "	  top: 0;\n" +
        "	  left: -200%;\n" +
        "	  transition: left 0.4s;\n" +
        "	  border-color: #86ba91;\n" +
        "	  padding-right: 1%;\n" +
        "	  padding-left: 1%;\n" +
        "	  \n" +
        "      }\n" +
        "\n" +
        "      .checkbox:checked ~ .left-panel{\n" +
        "\n" +
        "	  left:0;\n" +
        "	  \n" +
        "      }\n" +
        "\n" +
        "      .checkbox {\n" +
        "\n" +
        "	  display: none;\n" +
        "	  \n" +
        "      }\n" +
        "\n" +
        "    </style>";
        
        return retornoHead;
        
    }
    
    //Genera el body del menú lateral de los archivos en html según corresponda.
    //Generates the body of the sidebar menu for files in html as appropriate.
    
    public static String body(String funcion, boolean valoresAltos, boolean valoresMedios, boolean valoresBajos){
        
        String retornoBody;
        
        if(idioma == 1){
        
            retornoBody = " \n<input type = \"checkbox\" class=\"checkbox\" id=\"check\">\n" +
            "    <label class= \"menu\" for= \"check\">|</label>\n" +
            "    <div class=\"left-panel\">\n" +
            "\n" +
            "      <h2>EpiMolBio: Funci&oacute;n " + funcion + "</h2>\n" +
            "      \n" +
            "      <h2 Style=\" padding-top: 5%;\">C&oacute;digo de Colores</h2>\n" +
            "\n" +
            "      <h3><a Style = \"color:#834580;\">Morado</a> : x = 100%<br>\n"
                + "<a Style = \"color:#BF3D27;\">Rojo</a> : 100% < x >= 90%<br>\n";
                
        }else{
            
            retornoBody = " \n<input type = \"checkbox\" class=\"checkbox\" id=\"check\">\n" +
            "    <label class= \"menu\" for= \"check\">|</label>\n" +
            "    <div class=\"left-panel\">\n" +
            "\n" +
            "      <h2>EpiMolBio: Function " + funcion + "</h2>\n" +
            "      \n" +
            "      <h2 Style=\" padding-top: 5%;\">Color Code</h2>\n" +
            "\n" +
            "      <h3><a Style = \"color:#834580;\">Purple</a> : x = 100%</br>\n"
                + "<a Style = \"color:#BF3D27;\">Red</a> : 100% < x >= 90%</br>\n";
            
        }
        
        if(valoresAltos == true){
        
            if(idioma == 1){
            
                retornoBody += "	 <a Style = \"color:#F3A031;\"> Naranja</a> : 90% < x > 75%</br>\n";
        
            }else{
                
                retornoBody += "	 <a Style = \"color:#F3A031;\"> Orange</a> : 90% < x > 75%</br>\n";
                
            }
            
        }
        
        if(valoresMedios == true){
        
            if(idioma == 1){
            
                retornoBody += "	 <a Style = \"color:#E0D900;\"> Amarillo</a> : 75% <= x >50%</br>\n";
            
            }else{
                
                retornoBody += "	 <a Style = \"color:#E0D900;\"> Yellow</a> : 75% <= x >50%</br>\n";
                
            }
        }
        
        if(valoresBajos == true){
            
            if(idioma == 1){
            
            retornoBody +=  "<a Style = \"color:#2E75B6;\"> Azul</a> : 50% <= x >= 10%</br>\n"+
                "<a Style = \"color:#3E8249;\">Verde</a> : x < 10%</h3>";
            
            }else{
                
                retornoBody +=  "<a Style = \"color:#2E75B6;\"> Blue</a> : 50% <= x >= 10%</br>\n"+
                "<a Style = \"color:#3E8249;\">Green</a> : x < 10%</h3>";
                
            }
            
        }
                
        retornoBody += "	  <a><img Style=\" padding-top: 5%;\" src=\" data:image/png;base64,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\" ></a>					   \n" +
        "\n";

        retornoBody += "      </div>";

        return retornoBody;
        
    }    
}
