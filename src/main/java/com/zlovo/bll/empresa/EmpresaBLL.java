package com.zlovo.bll.empresa;

import com.zlovo.bll.utilizador.EmpresarioBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Bundle;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.utilizador.Empresario;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;

public class EmpresaBLL {

    private static Empresa empresaLog;

    public static Empresa getEmpresaLog() {
        return empresaLog;
    }

    public static void setEmpresaLog(Empresa empresaLog) {
        EmpresaBLL.empresaLog = empresaLog;
    }

    // Método que cria empresa
    public static void criarEmpresa(@NotNull Empresa empresa, String empresario) {
        empresa.setId(Repositorio.getRepositorio().getNumEmpresas() + 1);
        Repositorio.getRepositorio().setNumEmpresas(empresa.getId());
        empresa.setEmpresarioID(empresario);
        Repositorio.getRepositorio().setNumEmpresasChart(Repositorio.getRepositorio().getNumEmpresasChart() + 1);
        if (!EmpresarioBLL.adicionarEmpresa(empresa, empresario))
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
    public static boolean checkEmpresaNome(String nome) {
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
    public static void removerEmpresa(Empresa empresa, Empresario empresario) {
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key).removeIf(keyEmpresa -> keyEmpresa.equals(empresa));
        for (String key : Repositorio.getRepositorio().getCategoriasEmpresasMap().keySet())
            Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key).removeIf(keyEmpresa -> keyEmpresa.equals(empresa));

        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Empresario)
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key).equals(empresario))
                    for (Empresa e : ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList())
                        if (e.equals(empresa)) {
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().remove(empresa);
                            Repositorio.getRepositorio().setNumEmpresasChart(Repositorio.getRepositorio().getNumEmpresasChart() - 1);
                            return;
                        }
    }

    // Método que remove a empresa do mapa LocalidadeEmpresa e tambem do mapa CategoriaEmpresa
    public static void removerEmpresaALL(String empresario) {
//        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
//            Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key).removeIf(keyEmpresa -> keyEmpresa.getEmpresarioID().equals(empresario));

        for (String key : Repositorio.getRepositorio().getCategoriasEmpresasMap().keySet())
            Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key).removeIf(keyEmpresa -> keyEmpresa.getEmpresarioID().equals(empresario));

        for(String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            try {
                for (Empresa e : Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key))
                    if (e.getEmpresarioID().equals(empresario)){
                        Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key).remove(e);
                        Repositorio.getRepositorio().setNumEmpresasChart(Repositorio.getRepositorio().getNumEmpresasChart() - 1);
                    }
            } catch (Exception e){
                e.printStackTrace();
            }

    }

    // Método que altera os dados de uma empresa
    public static void alteraEmpresa(@NotNull Empresa empresa) {
        alteraEmpresaCategoriaMap(empresa);
        alteraEmpresaLocalidadeMap(empresa);
        alteraEmpresaUtilizadoresMap(empresa);
        EmpresaBLL.setEmpresaLog(alteraHandler(empresa));
    }

    // Método que altera uma Empresa no mapa Categoria
    public static void alteraEmpresaCategoriaMap(@NotNull Empresa empresa) {
        for (String key : Repositorio.getRepositorio().getCategoriasEmpresasMap().keySet())
            for (Empresa e : Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key))
                if (e.equals(EmpresaBLL.getEmpresaLog())) {
                    Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key).remove(e);
                    Repositorio.getRepositorio().getCategoriasEmpresasMap().get(key).add(alteraHandler(empresa));
                    return;
                }
    }

    // Método que altera uma Empresa no mapa Localidade
    public static void alteraEmpresaLocalidadeMap(@NotNull Empresa empresa) {
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

    // Método que altera uma Empresa no mapa Utilizadores
    public static void alteraEmpresaUtilizadoresMap(@NotNull Empresa empresa) {
        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Empresario)
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key).equals(UtilizadorBLL.getUserLog()))
                    for (Empresa e : ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList())
                        if (e.equals(EmpresaBLL.getEmpresaLog())) {
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().remove(e);
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().add(alteraHandler(empresa));
                            return;
                        }
    }

    // Método que altera uma Empresa
    public static @NotNull Empresa alteraHandler(@NotNull Empresa empresa) {
        Empresa e = EmpresaBLL.getEmpresaLog();
        if (!empresa.getNome().isEmpty()) e.setNome(empresa.getNome());
        if (!empresa.getTelefone().isEmpty()) e.setTelefone(empresa.getTelefone());
        if (!empresa.getMorada().getRua().isEmpty()) e.getMorada().setRua(empresa.getMorada().getRua());
        e.getMorada().setnPorta(empresa.getMorada().getnPorta());
        if (empresa.getMorada().getLocalidade() == null) return e;
        e.getMorada().setLocalidade(empresa.getMorada().getLocalidade());
        return e;
    }

    // Método que devolve a quantidade de produtos de uam Categoria
    public static int quantidadeProdutosCategoria(@NotNull Empresa empresa, String categoria) {
        int result = 0;
        if (empresa.getProdutosMap().get(categoria) == null) return result;
        for (Produto p : empresa.getProdutosMap().get(categoria))
            if (!(p instanceof Bundle))
                result++;
        return result;
    }

    // Método que adiciona um produto
    public static void adicionaProduto(@NotNull Produto produto) {
        if (EmpresaBLL.getEmpresaLog().getProdutosMap().containsKey(produto.getCategoria())) {
            EmpresaBLL.getEmpresaLog().getProdutosMap().get(produto.getCategoria()).add(produto);
            atualizaListaProdutos(EmpresaBLL.getEmpresaLog(), produto.getCategoria());
            return;
        }
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        EmpresaBLL.getEmpresaLog().getProdutosMap().put(produto.getCategoria(), produtos);
        atualizaListaProdutos(EmpresaBLL.getEmpresaLog(), produto.getCategoria());
    }

    // Método que atualiza a lista de produtos
    public static void atualizaListaProdutos(Empresa empresa, String categoria) {
        if (Repositorio.getRepositorio().getCategoriasEmpresasMap().get(categoria) != null) {
            Repositorio.getRepositorio().getCategoriasEmpresasMap().get(categoria).removeIf(e -> e.getId() == empresa.getId());
            Repositorio.getRepositorio().getCategoriasEmpresasMap().get(categoria).add(empresa);
        } else {
            ArrayList<Empresa> empresas = new ArrayList<>();
            empresas.add(empresa);
            Repositorio.getRepositorio().getCategoriasEmpresasMap().put(categoria, empresas);
        }
        Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(empresa.getMorada().getLocalidade()).removeIf(e -> e.getId() == empresa.getId());
        Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(empresa.getMorada().getLocalidade()).add(empresa);
        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Empresario)
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key).equals(UtilizadorBLL.getUserLog()))
                    for (Empresa e : ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList())
                        if (e.getId() == empresa.getId()) {
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().remove(e);
                            ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().add(empresa);
                            return;
                        }
    }

    public static void removeProduto(@NotNull Produto produto) {
        EmpresaBLL.getEmpresaLog().getProdutosMap().get(produto.getCategoria()).remove(produto);
        atualizaListaProdutos(EmpresaBLL.getEmpresaLog(), produto.getCategoria());
    }

    public static @NotNull ArrayList<Empresa> getEmpresas() {
        ArrayList<Empresa> empresas = new ArrayList<>();
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            empresas.addAll(Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key));
        return empresas;
    }

    public static @NotNull HashSet<String> showCategorias(@NotNull Empresa empresa) {
        HashSet<String> categorias = new HashSet<>();
        for (String key : empresa.getProdutosMap().keySet())
            if (EmpresaBLL.quantidadeProdutosCategoria(empresa, key) > 0)
                categorias.add(key);
        return categorias;
    }

    public static @Nullable Empresa getEmpresa(int id) {
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            for (Empresa e : Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key))
                if (e.getId() == id)
                    return e;
        return null;
    }
}