package com.zlovo.dal;

import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.utilizador.Utilizador;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Repositorio implements Serializable {
    private static Repositorio _repo = null;
    private final Map<Integer, Utilizador> utilizadoresMap = new HashMap<>();
    private final Map<String, ArrayList<Empresa>> localidadesEmpresasMap = new HashMap<>();
    private final Map<String, ArrayList<Empresa>> categoriasEmpresasMap = new HashMap<>();
    private final Map<Integer, Encomenda> encomendasMap = new HashMap<>();
    private final Set<String> localidadeSet = new HashSet<>();
    private final Set<String> categoriaSet = new HashSet<>();
    private int numEmpresasChart = 0;
    private int numProdutosChart = 0;
    private int numEncomendasChart = 0;
    private int numUtilizadoresChart = 0;
    private int numEmpresas = 0;
    private int numProdutos = 0;
    private int numUtilizadores = 0;
    private int numEncomendas = 0;

    private Repositorio() {
    }

    // Método que apenas permite 1 repositório
    public static Repositorio getRepositorio() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        if (_repo == null) _repo = new Repositorio();
        lock.unlock();
        return _repo;
    }

    public int getNumEmpresasChart() {
        return numEmpresasChart;
    }

    public void setNumEmpresasChart(int numEmpresasChart) {
        this.numEmpresasChart = numEmpresasChart;
    }

    public int getNumProdutosChart() {
        return numProdutosChart;
    }

    public void setNumProdutosChart(int numProdutosChart) {
        this.numProdutosChart = numProdutosChart;
    }

    public int getNumEncomendasChart() {
        return numEncomendasChart;
    }

    public void setNumEncomendasChart(int numEncomendasChart) {
        this.numEncomendasChart = numEncomendasChart;
    }

    public int getNumUtilizadoresChart() {
        return numUtilizadoresChart;
    }

    public void setNumUtilizadoresChart(int numUtilizadoresChart) {
        this.numUtilizadoresChart = numUtilizadoresChart;
    }

    // Ler o ficheiro
    public static void desserializar(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            _repo = (Repositorio) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Repositorio class not found. " + ex.getMessage());
        }
    }

    public int getNumProdutos() {
        return numProdutos;
    }

    public void setNumProdutos(int numProdutos) {
        this.numProdutos = numProdutos;
    }

    public Map<Integer, Encomenda> getEncomendasMap() {
        return encomendasMap;
    }

    public int getNumUtilizadores() {
        return numUtilizadores;
    }

    public void setNumUtilizadores(int numUtilizadores) {
        this.numUtilizadores = numUtilizadores;
    }

    public int getNumEmpresas() {
        return numEmpresas;
    }

    public void setNumEmpresas(int numEmpresas) {
        this.numEmpresas = numEmpresas;
    }

    public int getNumEncomendas() {
        return numEncomendas;
    }

    public void setNumEncomendas(int numEncomendas) {
        this.numEncomendas = numEncomendas;
    }

    public Set<String> getCategoriaSet() {
        return categoriaSet;
    }

    public Map<Integer, Utilizador> getUtilizadoresMap() {
        return utilizadoresMap;
    }

    public Map<String, ArrayList<Empresa>> getLocalidadesEmpresasMap() {
        return localidadesEmpresasMap;
    }

    public Map<String, ArrayList<Empresa>> getCategoriasEmpresasMap() {
        return categoriasEmpresasMap;
    }

    public Set<String> getLocalidadeSet() {
        return localidadeSet;
    }

    public String getRepoPath() {
        return "src/main/resources/com/zlovo/dal/BD.dat";
    }

    public String getLocalidadesPath() {
        return "src/main/resources/com/zlovo/dal/localidades.txt";
    }

    // Escrever para o ficheiro
    public void serializar(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filename);
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public void adicionaLocalidades() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Repositorio.getRepositorio().getLocalidadesPath()));
        String line;
        while ((line = reader.readLine()) != null) {
            Repositorio.getRepositorio().getLocalidadeSet().add(line);
        }
        reader.close();
    }
}
