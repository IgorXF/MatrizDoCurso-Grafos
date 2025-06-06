/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafocursocc.classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igorxf
 */
public class Requistos {
    private int id;
    private List<Integer> dependencias;

    public Requistos() {
        this.id = 0;
        this.dependencias = new ArrayList();
    }
    
    

    public List<Integer> getDependencias() {
        return dependencias;
    }

    public void setDependencias(List<Integer> dependencias) {
        this.dependencias = dependencias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
