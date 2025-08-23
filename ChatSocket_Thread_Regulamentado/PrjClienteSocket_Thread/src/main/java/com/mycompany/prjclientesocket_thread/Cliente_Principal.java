package com.mycompany.prjclientesocket_thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Pablo
 */
public class Cliente_Principal extends Thread{
     
    private static boolean done = false;
    private Socket conexao;

    public Cliente_Principal(Socket ss) {
        conexao = ss;
    }
    
    

    public static void main(String[] args) {
        try {
            Socket con = new Socket("127.0.0.1",2222);
            
            PrintStream saida = new PrintStream(con.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Digite seu nome:");
            
            String pablo = teclado.readLine();
            saida.println(pablo);
            
            Thread t = new Cliente_Principal(con);
            t.start();
            
            String linha;
            while(true){
                System.out.println("> ");
                linha = teclado.readLine();
                if(done){
                    break;
                }
                saida.println(linha);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        try {
            BufferedReader entrada = new BufferedReader( new InputStreamReader(conexao.getInputStream()));
            String linha;
            while(true){
                linha = entrada.readLine();
                if(linha == null){
                    System.out.println("Conexão Encerrada");
                    break;
                }
                System.out.println();
                System.out.println(linha);
                System.out.println("...> ");
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        done = true;
    }
}
