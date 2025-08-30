package com.mycompany.prjclientesockets;

import com.mycompany.prjclientesockets.form.ClienteForm;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Pablo
 */
public class Cliente_Principal extends Thread {

    private Socket conexao;
    private BufferedReader entrada;
    private PrintStream saida;
    private static boolean done = false;
    private static ClienteForm clienteForm;

    public Cliente_Principal() {

    }

    public Cliente_Principal(Socket ss) {
        conexao = ss;
    }

    public void conectandoServidor(String ip, int porta, String nome, ClienteForm form) {
        try {
            this.conexao = new Socket(ip, porta);
            this.saida = new PrintStream(conexao.getOutputStream());
            this.entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            clienteForm = form;

            // envia nome do cliente ao servidor
            saida.println(nome);

            this.start();

        } catch (Exception e) {
            e.printStackTrace();
            if (form != null) {
                form.adicionarMensagem("Erro ao conectar no servidor!");
            }
        }
    }

    @Override
    public void run() {
        try {
            String linha;
            while (!done && (linha = entrada.readLine()) != null) {
                if (clienteForm != null) {
                    if (linha.startsWith("Servidor:")) {
                        clienteForm.adicionarMensagem(linha, Color.RED);  // mensagens do servidor em vermelho
                    } else {
                        clienteForm.adicionarMensagem(linha, Color.BLACK); // mensagens dos clientes em preto
                    }
                }
            }
        } catch (IOException e) {
            if (clienteForm != null) {
                clienteForm.adicionarMensagem("Conexão encerrada com o servidor.", Color.GRAY);
            }
        }
        done = true;
    }

    public void enviarMensagem(String msg) {
        if (saida != null) {
            saida.println(msg);
        }
    }

    public void fecharConexao() {
        try {
            done = true;
            if (conexao != null) {
                conexao.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
