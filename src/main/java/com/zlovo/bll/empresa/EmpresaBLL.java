package com.zlovo.bll.empresa;

import com.zlovo.bll.utilizador.EmpresarioBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.utilizador.Empresario;
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
        empresa.setEmpresarioID(UtilizadorBLL.getUserLog().getUsername());
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
                if (keyEmpresa.getNome().equals(nome)) {
                    checker = true;
                    break;
                }
        return checker;
    }
    // Método que remove a empresa do mapa LocalidadeEmpresa e tambem do mapa CategoriaEmpresa e do mapa utilizadores
    public static void removerEmpresa(Empresa empresa, Empresario empresario){
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key).removeIf(keyEmpresa -> keyEmpresa.equals(empresa));
        for(String key : Repositorio.getRepositorio().getCategoriasEmpresasMap().keySet())
            Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key).removeIf(keyEmpresa -> keyEmpresa.equals(empresa));

        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Empresario)
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key).equals(empresario))
                    for (Empresa e : ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList())
                        if (e.equals(empresa))
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().remove(empresa);
    }
    // Método que remove a empresa do mapa LocalidadeEmpresa e tambem do mapa CategoriaEmpresa
    public static void removerEmpresaALL(String empresario){
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key).removeIf(keyEmpresa -> keyEmpresa.getEmpresarioID().equals(empresario));

        for(String key : Repositorio.getRepositorio().getCategoriasEmpresasMap().keySet())
            Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key).removeIf(keyEmpresa -> keyEmpresa.getEmpresarioID().equals(empresario));
    }
}