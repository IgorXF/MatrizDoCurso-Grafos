package com.mycompany.grafocursocc.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igorxf
 */
public class FilePersistence {

    public void saveToFile(String texto, String filePath) {
        try {
            FileWriter arq = new FileWriter(filePath);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.print(texto);
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(FilePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // MUDANÇA: O método agora propaga a exceção se o arquivo não for encontrado
    public String loadFromFile(String filePath) throws FileNotFoundException {
        String conteudoLido = "";
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file, "UTF-8"); // Adicionado "UTF-8" para compatibilidade

            // Lógica de leitura simplificada e mais segura
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }
            conteudoLido = sb.toString();
            scanner.close();

        } catch (FileNotFoundException ex) {
            // MUDANÇA CRÍTICA: Em vez de apenas logar, nós relançamos a exceção.
            // Isso força o programa a parar e informar que o arquivo não foi encontrado.
            throw ex;
        }
        return conteudoLido;
    }
}