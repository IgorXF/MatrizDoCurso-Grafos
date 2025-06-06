package com.mycompany.grafocursocc;

import com.mycompany.grafocursocc.classes.Grafo;
import com.mycompany.grafocursocc.classes.Materia;
import com.mycompany.grafocursocc.classes.Requistos;
import com.mycompany.grafocursocc.files.FilePersistence;
import com.mycompany.grafocursocc.files.SerializadorCSVMaterias;
import com.mycompany.grafocursocc.files.SerializadorCSVRequisitos;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igorxf
 */
public class GrafoCursoCC {

    // MUDANÇA: A assinatura do método agora especifica a exceção que pode lançar
    public static List<Materia> CarregarMaterias(String caminho) throws FileNotFoundException {
        FilePersistence file = new FilePersistence();
        String csvData = file.loadFromFile(caminho); // Isso pode lançar a exceção
        
        SerializadorCSVMaterias fp = new SerializadorCSVMaterias();
        List<Materia> materias = fp.fromCSV(csvData);
        return materias;
    }
    
    public static List<Requistos> CarregarRequisitos(String caminho) throws FileNotFoundException {
        FilePersistence file = new FilePersistence();
        String csvData = file.loadFromFile(caminho); // Isso pode lançar a exceção
        
        SerializadorCSVRequisitos fp = new SerializadorCSVRequisitos();
        List<Requistos> requisitos = fp.fromCSV(csvData);
        return requisitos;
    }

    public static void main(String[] args) {
        List<Materia> materias;
        List<Requistos> requistos;
        // MUDANÇA: Envolvemos a chamada em um try-catch para dar um feedback claro
        try {
            materias = CarregarMaterias("listaMaterias.csv");
            requistos = CarregarRequisitos("ListaPreRequisitos.csv");
            System.out.println("Arquivo carregado com sucesso!");
            System.out.println("Total de matérias na lista: " + materias.size());
            System.out.println("-----------------------------------");

            for (Materia m : materias) {
                //System.out.println("ID: " + m.getId() + ", Nome: " + m.getNome());
            }
            
            
            for (Requistos r : requistos) {
                //System.out.println("ID: " + r.getId() + ", Nome: " + r.getDependencias());
            }
            
             Grafo matrizCurso = new Grafo();
             matrizCurso.CriarGrafo(materias, requistos);
             matrizCurso.imprimirGrafo();
            

        } catch (FileNotFoundException e) {
            // Se o arquivo não for encontrado, esta mensagem será exibida
            System.err.println("ERRO: O arquivo 'listaMaterias.csv' não foi encontrado!");
            System.err.println("Por favor, verifique se o arquivo está na pasta raiz do projeto e tente novamente.");
            // Opcional: imprimir o stack trace para depuração
            // e.printStackTrace();
        }
    }
}