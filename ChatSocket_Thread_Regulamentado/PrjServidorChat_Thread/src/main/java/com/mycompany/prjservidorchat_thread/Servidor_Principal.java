package com.mycompany.prjservidorchat_thread;

import com.mycompany.prjclientesocket_thread.util.EscreverArquivo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Pablo
 */
public class Servidor_Principal extends Thread {

    private static Vector clientes;
    private Socket conexao;
    private String pablo;
    private static List<String> palavras;
        

    
    public Servidor_Principal(Socket ss){
        conexao = ss;
    }
    
    
    public static void main(String[] args) {
        palavras = new ArrayList();
        Scanner scan = new Scanner (System.in);
        clientes = new Vector();
        
        try {
           System.out.println("Digite as palavras chaves para captação delas (Aperte apenas ENTER para prosseguir):");
           String palavra = scan.nextLine();
           while(palavra != null && !(palavra.trim().equals(""))){
               palavras.add(palavra);
               palavra = scan.nextLine();
           }
           
           
            ServerSocket ss = new ServerSocket(2222);
            while(true){
                System.out.println("Esperando um cliente realizara a coneão");
                
                Socket con = ss.accept();
                System.out.println("Conexão Realizada !!");
                
                Thread t = new Servidor_Principal(con);
                
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run(){
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            
            pablo= entrada.readLine();
            
            if(pablo == null){
                return;
            }
            clientes.add(saida);
            
            String linha = entrada.readLine();
            while(linha != null && !(linha.trim().equals(""))){
                for(String palavra: palavras){

                    if(linha.toLowerCase().contains(palavra)){
                        EscreverArquivo.escrever(pablo + " falou: " + linha);
                    }

                }
                enviarParaTodos(saida, " falou: ",linha);
                linha= entrada.readLine();
            }
            enviarParaTodos(saida, " saiu ", " do chat");
            clientes.remove(saida);
            conexao.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void enviarParaTodos(PrintStream saida, String acao, String linha){
        Enumeration e = clientes.elements();
        
        while(e.hasMoreElements()){
            
            PrintStream chat = (PrintStream) e.nextElement();
            
            if(chat != saida){
                chat.println(pablo+acao+linha);
            }
        }
    }
}
