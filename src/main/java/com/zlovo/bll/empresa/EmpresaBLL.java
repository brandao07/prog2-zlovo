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
        Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(empresa.getMorada().getLocalidade()).add(empresa);
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
                        if (e.equals(empresa)) {
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().remove(empresa);
                            return;
                        }
    }
    // Método que remove a empresa do mapa LocalidadeEmpresa e tambem do mapa CategoriaEmpresa
    public static void removerEmpresaALL(String empresario){
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key).removeIf(keyEmpresa -> keyEmpresa.getEmpresarioID().equals(empresario));

        for(String key : Repositorio.getRepositorio().getCategoriasEmpresasMap().keySet())
            Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key).removeIf(keyEmpresa -> keyEmpresa.getEmpresarioID().equals(empresario));
    }
    // Método que altera os dados de uma empresa
    public static void alteraEmpresa(@NotNull Empresa empresa){
        alteraEmpresaCategoriaMap(empresa);
        alteraEmpresaLocalidadeMap(empresa);
        alteraEmpresaUtilizadoresMap(empresa);
        EmpresaBLL.setEmpresaLog(alteraHandler(empresa));
    }

    public static void alteraEmpresaCategoriaMap(@NotNull Empresa empresa){
        for (String key : Repositorio.getRepositorio().getCategoriasEmpresasMap().keySet())
            for (Empresa e : Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key))
                if(e.equals(EmpresaBLL.getEmpresaLog())) {
                    Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key).remove(e);
                    Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key).add(alteraHandler(empresa));
                    return;
                }
    }

    public static void alteraEmpresaLocalidadeMap(@NotNull Empresa empresa){
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            for (Empresa e : Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key))
                if (e.equals(EmpresaBLL.getEmpresaLog())) {
                    Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key).remove(e);
                    if (Repositorio.getRepositorio().getLocalidadesEmpresasMap().containsKey(alteraHandler(empresa).getMorada().getLocalidade())) {
                        Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(alteraHandler(empresa).getMorada().getLocalidade()).add(alteraHandler(empresa));
                        return;
                    }
                    ArrayList<Empresa> empresas = new ArrayList<>();
                    empresas.add(alteraHandler(empresa));
                    Repositorio.getRepositorio().getLocalidadesEmpresasMap().put(alteraHandler(empresa).getMorada().getLocalidade(), empresas);
                    return;
                }
    }

    public static void alteraEmpresaUtilizadoresMap(@NotNull Empresa empresa){
        for(int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Empresario)
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key).equals(UtilizadorBLL.getUserLog()))
                    for (Empresa e : ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList())
                        if (e.equals(EmpresaBLL.getEmpresaLog())) {
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().remove(e);
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().add(alteraHandler(empresa));
                            return;
                        }
    }

    public static @NotNull Empresa alteraHandler(@NotNull Empresa empresa){
        Empresa e = EmpresaBLL.getEmpresaLog();
        if (!empresa.getNome().isEmpty()) e.setNome(empresa.getNome());
        if (!empresa.getTelefone().isEmpty()) e.setTelefone(empresa.getTelefone());
        if (!empresa.getMorada().getRua().isEmpty()) e.getMorada().setRua(empresa.getMorada().getRua());
        e.getMorada().setnPorta(empresa.getMorada().getnPorta());
        if (empresa.getMorada().getLocalidade() == null) return e;
        e.getMorada().setLocalidade(empresa.getMorada().getLocalidade());
        return e;
    }

    // Método que adiciona um produto
    public static void adicionaProduto (@NotNull Produto produto){
        if (EmpresaBLL.getEmpresaLog().getProdutosMap().containsKey(produto.getCategoria())) {
            EmpresaBLL.getEmpresaLog().getProdutosMap().get(produto.getCategoria()).add(produto);
            atualizaListaProdutos(EmpresaBLL.getEmpresaLog(), produto.getCategoria());
        }

    }

    public static int quantidadeProdutosCategoria(String categoria){
        if (EmpresaBLL.getEmpresaLog().getProdutosMap().get(categoria) == null) return 0;
        return EmpresaBLL.getEmpresaLog().getProdutosMap().get(categoria).size();
    }

    public static void atualizaListaProdutos(Empresa empresa, String categoria){
        Repositorio.getRepositorio().getCategoriasEmpresasMap().get(categoria).removeIf(e -> e.getId() == empresa.getId());
        Repositorio.getRepositorio().getCategoriasEmpresasMap().get(categoria).add(empresa);
        Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(empresa.getMorada().getLocalidade()).removeIf(e -> e.getId() == empresa.getId());
        Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(empresa.getMorada().getLocalidade()).add(empresa);
        for(int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Empresario)
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key).equals(UtilizadorBLL.getUserLog()))
                    for (Empresa e : ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList())
                        if (e.getId() == empresa.getId()) {
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().remove(e);
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().add(empresa);
                            return;
                        }
    }
}