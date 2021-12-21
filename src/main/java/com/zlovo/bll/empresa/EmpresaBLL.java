package com.zlovo.bll.empresa;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
//TODO Adicionar empresa ao empresario
public class EmpresaBLL {

    private static Empresa empresaLog;

    public static Empresa getEmpresaLog() {
        return empresaLog;
    }

    public static void setEmpresaLog(Empresa empresaLog) {
        EmpresaBLL.empresaLog = empresaLog;
    }

    public static void criarEmpresa(@NotNull Empresa empresa) {
        System.out.println("teste");
        System.out.println(empresa.getNome());
        empresa.setId(Repositorio.getRepositorio().getNumEmpresas() + 1);
        Repositorio.getRepositorio().setNumEmpresas(empresa.getId());
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
}