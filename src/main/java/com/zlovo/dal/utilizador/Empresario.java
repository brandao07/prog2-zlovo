package com.zlovo.dal.utilizador;

import com.zlovo.dal.empresa.Empresa;

import java.io.Serializable;
import java.util.ArrayList;

public class Empresario extends Utilizador implements Serializable {

    private final ArrayList<Empresa> empresasList = new ArrayList<>();

    //      Construtor
    public Empresario() {
    }

    public ArrayList<Empresa> getEmpresasList() {
        return empresasList;
    }
}
