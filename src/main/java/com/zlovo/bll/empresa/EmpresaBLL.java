package com.zlovo.bll.empresa;

import com.zlovo.bll.utilizador.EmpresarioBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EmpresaBLL {

    private static Empresa empresaLog;

    public static Empresa getEmpresaLog() {
        return empresaLog;
    }

    public static void setEmpresaLog(Empresa empresaLog) {
        EmpresaBLL.empresaLog = empresaLog;
    }
    // Método que cria empresa
    public static void criarEmpresa(@NotNull Empresa empresa) {
        empresa.setId(Repositorio.getRepositorio().getNumEmpresas() + 1);
        Repositorio.getRepositorio().setNumEmpresas(empresa.getId());
        if(!EmpresarioBLL.adicionarEmpresa(empresa))
            return;
        if (!Repositorio.getRepositorio().getLocalidadesEmpresasMap().containsKey(empresa.getMorada().getLocalidade())) {
            ArrayList<Empresa> empresas = new ArrayList<>();
            empresas.add(empresa);
            Repositorio.getRepositorio().getLocalidadesEmpresasMap().put(empresa.getMorada().getLocalidade(), empresas);
            return;
        }
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet()){
            Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key).add(empresa);
            return;
        }
    }
    //Método que verifica se o nome da empresa é repetido
    public static boolean checkEmpresaNome(String nome){
        boolean checker = false;
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            for (Empresa keyEmpresa : Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key))
                if (Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key).get(keyEmpresa.getId()).getNome().equals(nome))
                    checker = true;
        return checker;
    }
}