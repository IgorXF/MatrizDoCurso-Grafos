/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafocursocc.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author igorxf
 */
public class Grafo {

    List<Vertice> vertices;

    public Grafo() {
        this.vertices = new ArrayList<>();
    }

    // VERSÃO COM CORREÇÃO SIMPLES
    public void CriarGrafo(List<Materia> materias, List<Requistos> requistos) {
        int tamanho = materias.size();

        // MUDANÇA: O loop agora vai de 0 até tamanho-1, que são os índices válidos.
        for (int i = 0; i < tamanho; i++) {
            Vertice v = new Vertice();

            // Pega a matéria e o requisito no mesmo índice 'i'
            Materia materiaAtual = materias.get(i);
            Requistos requisitoAtual = requistos.get(i);

            // Validação opcional para garantir que os IDs correspondem
            if (materiaAtual.getId() == requisitoAtual.getId()) {
                v.setMateria(materiaAtual);
                v.setDependencias(requisitoAtual.getDependencias());
                vertices.add(v);
            } else {
                System.err.println("Erro de alinhamento: ID da matéria " + materiaAtual.getId() + " não corresponde ao ID do requisito " + requisitoAtual.getId());
            }
        }
    }

    public void imprimirGrafo() {
        if (this.vertices == null || this.vertices.isEmpty()) {
            System.out.println("O grafo está vazio. Crie o grafo antes de imprimir.");
            return;
        }

        System.out.println("==================================================");
        System.out.println("           GRAFO DE DEPENDÊNCIAS DO CURSO         ");
        System.out.println("==================================================");

        // 1. Criar um mapa de ID para Nome da Matéria para facilitar a busca
        Map<Integer, String> mapaDeNomes = new HashMap<>();
        for (Vertice v : this.vertices) {
            mapaDeNomes.put(v.getMateria().getId(), v.getMateria().getNome());
        }

        // 2. Iterar por cada vértice para imprimir suas informações
        for (Vertice vertice : this.vertices) {
            Materia materiaAtual = vertice.getMateria();
            List<Integer> dependenciasIds = vertice.getDependencias();

            System.out.println("\nMatéria: " + materiaAtual.getId() + " - " + materiaAtual.getNome());

            if (dependenciasIds.isEmpty()) {
                System.out.println("  └── Pré-requisitos: Nenhum");
            } else {
                // Monta a string com os nomes dos pré-requisitos
                // Bloco novo sem Stream (compatível com Java 7)
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < dependenciasIds.size(); i++) {
                    Integer id = dependenciasIds.get(i);
                    String nome = mapaDeNomes.getOrDefault(id, "ID " + id + " desconhecido");
                    builder.append(nome);
                    // Adiciona a vírgula apenas se não for o último elemento
                    if (i < dependenciasIds.size() - 1) {
                        builder.append(", ");
                    }
                }
                String nomesRequisitos = builder.toString();

                System.out.println("  └── Pré-requisitos: " + nomesRequisitos);
            }
        }
        System.out.println("\n==================================================");
    }
}
