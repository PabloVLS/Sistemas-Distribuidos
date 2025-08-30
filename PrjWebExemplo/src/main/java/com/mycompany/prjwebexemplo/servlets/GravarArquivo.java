package com.mycompany.prjwebexemplo.servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class GravarArquivo {

    public static void escreverArq(Pet pet) {
        try {
            File f = new File("c:\\Objetos2.txt");
            Gson gson = new Gson();

            List<Pet> lstPets = new BuscarArquivo().lerArq();
            lstPets.add(pet);

            try (FileWriter fw = new FileWriter(f, false); BufferedWriter bw = new BufferedWriter(fw)) {
                for (Pet p : lstPets) {
                    bw.write(gson.toJson(p));
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reescreverArq(int index, Pet petAtualizado) {
        try {
            // Lê todos os pets do arquivo
            List<Pet> lstPets = new BuscarArquivo().lerArq();

            if (lstPets == null || lstPets.isEmpty()) {
                System.out.println("Lista de pets vazia!");
                return;
            }

            // Verifica se o índice é válido
            if (index < 0 || index >= lstPets.size()) {
                System.out.println("Índice inválido!");
                return;
            }

            // Atualiza o pet na lista
            lstPets.set(index, petAtualizado);

            // Sobrescreve o arquivo com a lista completa
            try (FileWriter fw = new FileWriter("c:\\Objetos2.txt", false); BufferedWriter bw = new BufferedWriter(fw)) {

                Gson gson = new Gson();
                for (Pet pet : lstPets) {
                    bw.write(gson.toJson(pet));
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualizarPetNoArquivo(int index, Pet pet) throws IOException {
        List<Pet> lstPets = new BuscarArquivo().lerArq();
        lstPets.set(index, pet);

        File f = new File("c:\\Objetos2.txt");
        try (FileWriter fw = new FileWriter(f, false); PrintWriter pw = new PrintWriter(fw)) {
            Gson gson = new Gson();
            for (Pet p : lstPets) {
                pw.println(gson.toJson(p));
            }
        }
    }

    public void excluirPet(List<Pet> pets) {
        File f = new File("c:\\Objetos2.txt");
        Gson gson = new Gson();
        try (FileWriter fw = new FileWriter(f, false); // false = sobrescreve
                 PrintWriter pw = new PrintWriter(fw)) {

            for (Pet p : pets) {
                pw.println(gson.toJson(p));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
