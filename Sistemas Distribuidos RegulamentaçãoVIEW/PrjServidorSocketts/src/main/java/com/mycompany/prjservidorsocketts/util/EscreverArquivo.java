package com.mycompany.prjservidorsocketts.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Pablo
 */
public class EscreverArquivo {
    public static void escrever(String texto){
        try {
            File f = new File("C:\\Users\\IFTM\\Desktop\\Sistemas-Distribuidos\\ArquivoIdentificador.txt");
            
            FileWriter fw= new FileWriter(f,true);
            
            PrintWriter pw = new PrintWriter(fw);
            
            pw.println(texto);
            
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}