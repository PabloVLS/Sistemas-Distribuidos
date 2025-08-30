package com.mycompany.prjservidorsocketts;

import com.mycompany.prjservidorsocketts.form.ServidorForm;
import com.mycompany.prjservidorsocketts.util.EscreverArquivo;
import java.awt.Color;
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
import javax.net.ssl.SSLEngine;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pablo
 */
public class Principal extends Thread {

    private static Vector<PrintStream> clientes = new Vector<>();
    private Socket conexao;
    private String meuNome;
    private static List<String> palavrasFiltro = new ArrayList<>();
    ;
    private static ServidorForm servidorForm;
    private static boolean rodando = false;
    private ServerSocket ss;

    public Principal() {

    }

    public Principal(Socket socket) {
        this.conexao = socket;
    }

    public static void setServidorForm(ServidorForm form) {
        servidorForm = form;
    }

    public void iniciandoServidor(int porta) {

        try {
            ss = new ServerSocket(porta);
            rodando = true;
            System.out.println("Servidor iniciado na porta " + porta);
            while (rodando) {

                Socket con = ss.accept();

                System.out.println("Conexão Realizada !!");
                if (servidorForm != null) {
                    SwingUtilities.invokeLater(() -> servidorForm.atualizarQuantidadeClientes(clientes.size() + 1));
                }
                Thread t = new Principal(con);

                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parandoServidor() {
        try {
            rodando = false;
            if (ss != null && !ss.isClosed()) {
                ss.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());

            meuNome = entrada.readLine();

            if (meuNome == null) {
                return;
            }
            clientes.add(saida);

            String linha = entrada.readLine();
            while (linha != null && !(linha.trim().equals(""))) {
                for (String palavra : palavrasFiltro) {

                    if (linha.toLowerCase().contains(palavra.toLowerCase())) {
                        EscreverArquivo.escrever(meuNome + " falou: " + linha);
                    }

                }
                enviarParaTodos(saida, " falou: ", linha);
                linha = entrada.readLine();
            }
            enviarParaTodos(saida, " saiu ", " do chat");
            clientes.remove(saida);
            if (servidorForm != null) {
                SwingUtilities.invokeLater(() -> servidorForm.atualizarQuantidadeClientes(clientes.size()));
            }
            conexao.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void enviarParaTodos(PrintStream saida, String acao, String linha) {
        Enumeration e = clientes.elements();

        String mensagem = meuNome + acao + linha;
        if (servidorForm != null) {
            SwingUtilities.invokeLater(() -> servidorForm.adicionarMensagem(mensagem, Color.BLACK));
        }

        while (e.hasMoreElements()) {
            PrintStream chat = (PrintStream) e.nextElement();
            chat.println(mensagem);
        }
    }

    public static void enviarMensagemServidor(String mensagem) {
        if (servidorForm != null) {
            SwingUtilities.invokeLater(() -> servidorForm.adicionarMensagem(mensagem, Color.RED));
        }

        Enumeration e = clientes.elements();
        while (e.hasMoreElements()) {
            PrintStream chat = (PrintStream) e.nextElement();
            chat.println(mensagem);
        }
    }

    public static void adicionarPalavra(String palavra) {
        palavrasFiltro.add(palavra);
    }
}
