package com.mycompany.prjservidorsocketts.form;

import com.mycompany.prjservidorsocketts.Principal;
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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Pablo
 */
public class ServidorForm extends javax.swing.JFrame {

    private Principal principal = new Principal();

    public ServidorForm() {
        initComponents();
        Principal.setServidorForm(this);
    }

    public void regrasIniciandoServidor() {
        if (txtPorta.getText() == null || txtPorta.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Porta Desconhecida!");
            return;
        }
        int porta = Integer.parseInt(txtPorta.getText());

        try {
            new Thread(() -> principal.iniciandoServidor(porta)).start();
            JOptionPane.showMessageDialog(null, "Servidor iniciado na porta " + porta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void adicionarMensagem(String mensagem) {
        txaChat.append(mensagem + "\n");
    }*/
    public void adicionarMensagem(String mensagem, Color cor) {
        StyledDocument doc = txaChat.getStyledDocument();
        Style estilo = txaChat.addStyle("estilo", null);
        StyleConstants.setForeground(estilo, cor);

        try {
            doc.insertString(doc.getLength(), mensagem + "\n", estilo);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnIniciarServidor = new javax.swing.JButton();
        txtPararServidor = new javax.swing.JButton();
        txtPorta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtQuantidadeClientes = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtFiltroPalavras = new javax.swing.JTextArea();
        btnSalvarPalavras = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaChat = new javax.swing.JTextPane();
        btnEnviarMsgServidor = new javax.swing.JButton();
        txtEnviarMensagemServidor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("SERVIDOR CHAT");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(128, 128, 128))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel2.setText("Porta:");

        btnIniciarServidor.setText("Iniciar Servidor");
        btnIniciarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarServidorActionPerformed(evt);
            }
        });

        txtPararServidor.setText("Parar Servidor");
        txtPararServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPararServidorActionPerformed(evt);
            }
        });

        jLabel3.setText("Aguardando Conexão!!!");

        jLabel4.setText("Clientes Conectados:");

        txtQuantidadeClientes.setEditable(false);

        jLabel5.setText("Lista de palavras filtradas: Separar as palavras por (;). Ex: Palavra1; Palavra2");

        txtFiltroPalavras.setColumns(20);
        txtFiltroPalavras.setRows(5);
        jScrollPane1.setViewportView(txtFiltroPalavras);

        btnSalvarPalavras.setText("Salvar Palavras Restritas");
        btnSalvarPalavras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPalavrasActionPerformed(evt);
            }
        });

        jLabel6.setText("Mensagem");

        jScrollPane3.setViewportView(txaChat);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidadeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPorta))
                        .addGap(18, 18, 18)
                        .addComponent(btnIniciarServidor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPararServidor)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnSalvarPalavras))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnIniciarServidor)
                    .addComponent(txtPararServidor)
                    .addComponent(txtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtQuantidadeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvarPalavras)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnEnviarMsgServidor.setText("Enviar Mensagem (Como Servidor)");
        btnEnviarMsgServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarMsgServidorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEnviarMsgServidor)
                .addGap(105, 105, 105))
            .addComponent(txtEnviarMensagemServidor, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(txtEnviarMensagemServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEnviarMsgServidor)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarPalavrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPalavrasActionPerformed
        String texto = txtFiltroPalavras.getText();
        String[] palavras = texto.split(";");

        for (String palavra : palavras) {
            if (palavra != null && !palavra.trim().equals("")) {
                principal.adicionarPalavra(palavra.trim());
            }
        }
        JOptionPane.showMessageDialog(null, "Palavras Chaves Adicionadas !");
    }//GEN-LAST:event_btnSalvarPalavrasActionPerformed

    private void btnIniciarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarServidorActionPerformed
        regrasIniciandoServidor();
        btnIniciarServidor.setEnabled(false);
    }//GEN-LAST:event_btnIniciarServidorActionPerformed

    private void txtPararServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPararServidorActionPerformed
        principal.parandoServidor();
        JOptionPane.showMessageDialog(null, "Servidor Parado");
        btnIniciarServidor.setEnabled(true);
    }//GEN-LAST:event_txtPararServidorActionPerformed

    private void btnEnviarMsgServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarMsgServidorActionPerformed
        String msg = txtEnviarMensagemServidor.getText().trim();
        if (!msg.isEmpty()) {
            Principal.enviarMensagemServidor("Servidor: " + msg);
            txtEnviarMensagemServidor.setText("");
        }
    }//GEN-LAST:event_btnEnviarMsgServidorActionPerformed

    public void atualizarQuantidadeClientes(int quantidade) {
        SwingUtilities.invokeLater(() -> {
            txtQuantidadeClientes.setText(String.valueOf(quantidade));
        });
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarMsgServidor;
    private javax.swing.JButton btnIniciarServidor;
    private javax.swing.JButton btnSalvarPalavras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane txaChat;
    private javax.swing.JTextField txtEnviarMensagemServidor;
    private javax.swing.JTextArea txtFiltroPalavras;
    private javax.swing.JButton txtPararServidor;
    private javax.swing.JTextField txtPorta;
    private javax.swing.JTextField txtQuantidadeClientes;
    // End of variables declaration//GEN-END:variables
}
