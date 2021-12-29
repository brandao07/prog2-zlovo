package com.zlovo.bll;

import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.encomenda.Encomenda;
import org.jetbrains.annotations.NotNull;

public class EncomendaBLL {

    public static boolean checkProduto(@NotNull Encomenda encomenda, Produto produto){
        return encomenda.getProdutosList().contains(produto);
    }
}
