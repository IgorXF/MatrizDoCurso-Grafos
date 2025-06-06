/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafocursocc.classes;

import java.util.List;

/**
 *
 * @author igorxf
 */
public class Vertice {
   private Materia materia;
   private List<Integer> dependencias;

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public List<Integer> getDependencias() {
        return dependencias;
    }

    public void setDependencias(List<Integer> dependencias) {
        this.dependencias = dependencias;
    }
    
   
}
