package com.zlovo.dal.utilizador;

import com.zlovo.dal.empresa.Empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Empresario extends Utilizador implements Serializable {

    private final List<Empresa> empresasList = new ArrayList<>();
    //      Construtor
    public Empresario(){}

    public List<Empresa> getEmpresasList() {
        return empresasList;
    }
}
