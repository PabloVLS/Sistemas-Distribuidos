package com.mycompany.prjservidorchat_thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Pablo
 */
public class Servidor_Principal extends Thread {

    private static Vector clientes;
    private Socket conexao;
    private String pablo;
    
    public Servidor_Principal(Socket ss){
        conexao = ss;
    }
    
    
    public static void main(String[] args) {
        clientes = new Vector();
        
        try {
            ServerSocket ss = new ServerSocket(2222);
            while(true){
                System.out.println("Eseprando um cliente realizara a coneão");
                
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
