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
import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.Alignments.PairwiseSequenceAlignerType;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.alignment.template.GapPenalty;
import org.biojava.nbio.core.alignment.matrices.SimpleSubstitutionMatrix;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.core.alignment.template.SequencePair;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AmbiguityDNACompoundSet;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Calculos_Alineamientos {
    
    /*Hace alineamiento global por pares entre dos secuencias de aminoácidos usando la libreria BioJava.
    EpiMolBio usa una referencia y una secuencia problema.*/
    
    public static String [] alineamientoAminoacidos(String query, String target, String openPenalty, String extensionPenalty) {   
        
        try{
        
            GapPenalty penalty = new SimpleGapPenalty();
            
            if(!openPenalty.equals("") || !extensionPenalty.equals("")){
                
                penalty.setOpenPenalty(Integer.parseInt(openPenalty));
                penalty.setExtensionPenalty(Integer.parseInt(extensionPenalty));
                
            }else{
                
                penalty = new SimpleGapPenalty();
                
            }
            
            query = query.replace("?","X");
            target = target.replace("?", "X");
        
            ProteinSequence s1 = new ProteinSequence(query);        
            ProteinSequence s2 = new ProteinSequence(target);
            
            SubstitutionMatrix<AminoAcidCompound> matrix = SimpleSubstitutionMatrix.getBlosum62();
    
            SequencePair<ProteinSequence, AminoAcidCompound> pair = Alignments.getPairwiseAlignment(s1, s2,
                Alignments.PairwiseSequenceAlignerType.GLOBAL, penalty, matrix);
            
            String parAlineado[] = new String[2];
        
            parAlineado[0] = pair.getAlignedSequence(1).toString().replace("X", "?");
            parAlineado[1] = pair.getAlignedSequence(2).toString().replace("X", "?");
    
            return parAlineado;
        
        }catch(NumberFormatException | CompoundNotFoundException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
                
            Logger.getLogger(Calculos_Alineamientos.class.getName()).log(Level.SEVERE, null, e);
                
            btn_presionado = false;
                
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
                
            Terminar_Hilos.cargarTerminarHilos();
                
            return null;
            
        } 
    
    }
    
    //Hace alineamiento local por pares entre dos secuencias de aminoácidos usando la libreria BioJava.
    
    public static String [] alineamientoAminoacidosLocal(String query, String target){   
        
        try{
        
            query = query.replace("?","X");
            target = target.replace("?", "X");
        
            ProteinSequence s1 = new ProteinSequence(query);        
            ProteinSequence s2 = new ProteinSequence(target);
            
            SubstitutionMatrix<AminoAcidCompound> matrix = SimpleSubstitutionMatrix.getBlosum62();
    
            SequencePair<ProteinSequence, AminoAcidCompound> pair = Alignments.getPairwiseAlignment(s1, s2,
                Alignments.PairwiseSequenceAlignerType.LOCAL, new SimpleGapPenalty(), matrix);
        
            String parAlineado[] = new String[2];
        
            parAlineado[0] = pair.getAlignedSequence(1).toString().replace("X", "?");
            parAlineado[1] = pair.getAlignedSequence(2).toString().replace("X", "?");
    
            return parAlineado;
       
        }catch(CompoundNotFoundException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Calculos_Alineamientos.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return null;
        }
    }
    
    /*Hace alineamiento global por pares entre dos secuencias de nucleótidos usando la libreria BioJava.
    EpiMolBio usa una referencia y una secuencia problema.*/
    
    public static String[] alineamientoNucleotidos(String querySeq, String targetSeq, String openPenalty, String extensionPenalty){
        
        try{
        
            GapPenalty gapP = new SimpleGapPenalty();
            
            if(!openPenalty.equals("") || !extensionPenalty.equals("")){
                
                gapP.setOpenPenalty(Integer.parseInt(openPenalty));
                gapP.setExtensionPenalty(Integer.parseInt(extensionPenalty));
                
            }else{
                
                gapP.setOpenPenalty((short)5);  
                gapP.setExtensionPenalty((short)2); 
                
            }
        
            DNASequence target = new DNASequence(targetSeq,  
            AmbiguityDNACompoundSet.getDNACompoundSet());  
  
            DNASequence query = new DNASequence(querySeq,  
            AmbiguityDNACompoundSet.getDNACompoundSet());

            SubstitutionMatrix<NucleotideCompound> matrix = SubstitutionMatrixHelper.getNuc4_4();  
   
            SequencePair<DNASequence, NucleotideCompound> psa =  
                Alignments.getPairwiseAlignment(query, target,  
                PairwiseSequenceAlignerType.GLOBAL, gapP, matrix);
       
            String retorno[] = new String[2];
     
            retorno[0] = psa.getAlignedSequence(1).toString();
            retorno[1] = psa.getAlignedSequence(2).toString();
     
            return retorno;
        
        }catch(NumberFormatException | CompoundNotFoundException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Calculos_Alineamientos.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return null;
            
        } 
     
    }
    
    //Hace alineamiento local por pares entre dos secuencias de nucleótidos usando la libreria BioJava.
   
    public static String[] alineamientoNucleotidosLocal(String querySeq, String targetSeq, String openPenalty, String extensionPenalty){
        
        try{
        
            GapPenalty gapP = new SimpleGapPenalty();
            
            if(!openPenalty.equals("") || !extensionPenalty.equals("")){
                
                gapP.setOpenPenalty(Integer.parseInt(openPenalty));
                gapP.setExtensionPenalty(Integer.parseInt(extensionPenalty));
                
            }else{
                
                gapP.setOpenPenalty((short)5);  
                gapP.setExtensionPenalty((short)2); 
                
            }
        
            DNASequence target = new DNASequence(targetSeq,  
            AmbiguityDNACompoundSet.getDNACompoundSet());  
  
            DNASequence query = new DNASequence(querySeq,  
            AmbiguityDNACompoundSet.getDNACompoundSet());

            SubstitutionMatrix<NucleotideCompound> matrix = SubstitutionMatrixHelper.getNuc4_4();  
   
            SequencePair<DNASequence, NucleotideCompound> psa =  
                Alignments.getPairwiseAlignment(query, target,  
                PairwiseSequenceAlignerType.LOCAL, gapP, matrix);
       
            String retorno[] = new String[2];
     
            retorno[0] = psa.getAlignedSequence(1).toString();
            retorno[1] = psa.getAlignedSequence(2).toString();
     
            return retorno;
        
        }catch(NumberFormatException | CompoundNotFoundException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Calculos_Alineamientos.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();           
            
            Terminar_Hilos.cargarTerminarHilos();
            
            return null;
            
        } 
     
    }
    
}
