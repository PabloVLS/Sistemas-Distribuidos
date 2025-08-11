package com.mycompany.prjservidorsocketts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class Principal {

    public static void main(String[] args) {
        try{
            ServerSocket s = new ServerSocket(2222);
            while(true){
                Socket con = s.accept();
                System.out.println("Conectou!");
                BufferedReader entrada = new BufferedReader(new InputStreamReader(con.getInputStream()));
                PrintStream saida = new PrintStream(con.getOutputStream());
                String linha = entrada.readLine();
                while(linha != null && !(linha.trim().equals(""))){
                    System.out.println("Msg do cliente: "+linha);
                    saida.println("Saida: "+linha);
                    linha = entrada.readLine();
                }
                con.close();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
