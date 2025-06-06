package com.mycompany.grafocursocc.files;

import com.mycompany.grafocursocc.classes.Requistos;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author igorxf
 */
public class SerializadorCSVRequisitos {

    /**
     * Converte uma lista de Requisitos para o formato CSV.
     * Exemplo de saída: "22;14;17;\n"
     */
    public String toCSV(List<Requistos> requisitos) {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("Id;PreRequisitos\n"); // Cabeçalho do CSV

        for (Requistos req : requisitos) {
            csvBuilder.append(req.getId()).append(";");
            
            // MUDANÇA: Itera pela lista de dependências para formatar a string corretamente
            if (req.getDependencias() != null && !req.getDependencias().isEmpty()) {
                String dependenciasStr = req.getDependencias()
                                            .stream()
                                            .map(String::valueOf)
                                            .collect(Collectors.joining(";"));
                csvBuilder.append(dependenciasStr).append(";");
            }
            
            csvBuilder.append("\n");
        }
        return csvBuilder.toString();
    }

    /**
     * Converte uma string no formato CSV para uma Lista de Requisitos.
     */
    public List<Requistos> fromCSV(String data) {
        List<Requistos> listaDeRequisitos = new ArrayList<>();

        if (data == null || data.trim().isEmpty()) {
            return listaDeRequisitos;
        }

        String[] linhas = data.split("\n");
        // Começa em i = 1 para pular o cabeçalho
        for (int i = 1; i < linhas.length; i++) {
            String linha = linhas[i].trim();

            if (linha.isEmpty()) {
                continue;
            }

            String[] partes = linha.split(";");

            if (partes.length >= 1) {
                try {
                    Requistos req = new Requistos();
                    // Define o ID da matéria (primeira parte)
                    req.setId(Integer.parseInt(partes[0]));

                    // MUDANÇA: Itera pelo resto das partes para preencher a lista de dependências
                    List<Integer> dependenciasDaLinha = new ArrayList<>();
                    for (int j = 1; j < partes.length; j++) {
                        String dependenciaStr = partes[j].trim();
                        if (!dependenciaStr.isEmpty()) {
                            dependenciasDaLinha.add(Integer.parseInt(dependenciaStr));
                        }
                    }
                    req.setDependencias(dependenciasDaLinha);

                    listaDeRequisitos.add(req);

                } catch (NumberFormatException e) {
                    System.err.println("Aviso: Linha mal formatada no CSV de requisitos: " + linha);
                }
            }
        }
        return listaDeRequisitos;
    }
}