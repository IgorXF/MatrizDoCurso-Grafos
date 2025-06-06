package com.mycompany.grafocursocc.files;

import com.mycompany.grafocursocc.classes.Materia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igorxf
 */
public class SerializadorCSVMaterias {
    public String toCSV(List<Materia> materias) {
        String csv = "Id;Nome;\n";
        for (Materia mat : materias) {
            csv += mat.getId() + ";"
                    + mat.getNome() + ";\n";
        }
        return csv;
    }

    public List<Materia> fromCSV(String data) {
        List<Materia> materias = new ArrayList<>();

        String[] linhas = data.split("\n");
        // Começa em i = 1 para pular o cabeçalho "Id;Nome;"
        for (int i = 1; i < linhas.length; i++) {
            String linha = linhas[i].trim(); // Remove espaços em branco extras

            if (linha.isEmpty()) {
                continue; // Pula linhas em branco no meio do arquivo
            }
            
            String[] partes = linha.split(";");
            
            // MUDANÇA: Garante que a linha tem PELO MENOS 2 partes (ID e Nome)
            if (partes.length >= 2) {
                try {
                    Materia mat = new Materia();
                    mat.setId(Integer.parseInt(partes[0].trim())); // .trim() para limpar o número
                    mat.setNome(partes[1].trim()); // .trim() para limpar o nome
                    
                    materias.add(mat);
                } catch (NumberFormatException e) {
                    System.err.println("Aviso: Linha mal formatada (ID não é um número): " + linha);
                }
            }
        }
        return materias;
    }
}