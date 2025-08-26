package com.mycompany.prjservidorsocketts;

import com.mycompany.prjservidorsocketts.form.ServidorForm;
import com.mycompany.prjservidorsocketts.util.EscreverArquivo;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class Principal extends Thread {

    private static Vector clientes;
    private Socket conexao;
    private String meuNome;
    private static List<String> palavras;
    private List<String> palavrasFiltro;

        
    public Principal() {
        
    }
    
    public Principal(Socket socket, List<String> palavras) {
        this.conexao = socket;
        this.palavrasFiltro = palavras;
        ServidorView();
    }


    
    
    public void ServidorView(){
        new ServidorForm().setVisible(true);
    }
    
    public void iniciandoServidor(int porta) {
 
        try {
            ServerSocket ss = new ServerSocket(porta);
            while(true){
                
                Socket con = ss.accept();s
                
                System.out.println("Conexão Realizada !!");
                
                Thread t = new  Principal(con,palavrasFiltro);
                
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            
            meuNome= entrada.readLine();
            
            if(meuNome == null){
                return;
            }
            clientes.add(saida);
            
            String linha = entrada.readLine();
            while(linha != null && !(linha.trim().equals(""))){
                for(String palavra: palavras){

                    if(linha.toLowerCase().contains(palavra)){
                        EscreverArquivo.escrever(meuNome + " falou: " + linha);
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
                chat.println(meuNome+acao+linha);
            }
        }
    }
}