package com.zlovo.dal.empresa;

import java.util.ArrayList;

public class Bundle extends Produto{
    private final ArrayList<Produto> produtosBundle = new ArrayList<>();

    public Bundle() {}

    public ArrayList<Produto> getProdutosBundle() {
        return produtosBundle;
    }
}
