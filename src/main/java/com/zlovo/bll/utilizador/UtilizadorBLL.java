package com.zlovo.bll.utilizador;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.utilizador.Utilizador;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UtilizadorBLL {

    // Atributo que permite estar a par que Utilizador está logado no programa
    private static Utilizador userLog;

    // Getters & Setters
    public static Utilizador getUserLog() {
        return userLog;
    }

    public static void setUserLog(Utilizador userLog) {
        UtilizadorBLL.userLog = userLog;
    }

    // Método que CRIA um utilizador
    public static void criarUtilizador(@NotNull Utilizador utilizador) {
        utilizador.setIdUtilizador(Repositorio.getRepositorio().getNumUtilizadores() + 1);
        Repositorio.getRepositorio().getUtilizadoresMap().put(utilizador.getIdUtilizador(), utilizador);
        Repositorio.getRepositorio().setNumUtilizadores(utilizador.getIdUtilizador());
    }

    // Método que verifica se existe um utilizador com given username já registado
    public static boolean checkUsername(String username) {
        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (username.equals(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getUsername()))
                return true;
        return false;
    }

    // Método que verifica given username e password com todos os utilizadores já registados
    public static boolean login(String username, String password) {
        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (username.equals(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getUsername()))
                if (password.equals(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getPassword())) {
                    userLog = Repositorio.getRepositorio().getUtilizadoresMap().get(key);
                    userLog.setLoginStatus(true);
                    return true;
                }
        return false;
    }

    // Método que remove utilizador com given username
    public static boolean removeUtilizador(String username) {
        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (username.equals(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getUsername())) {
                Repositorio.getRepositorio().getUtilizadoresMap().remove(key);
                return true;
            }
        return false;
    }

    // Método que lista todos os utilizadores
    public static void listarUtilizadores() {
        if (!Repositorio.getRepositorio().getUtilizadoresMap().isEmpty()) {
            ClienteBLL.listarClientes();
            EmpresarioBLL.listarEmpresarios();
            MotardBLL.listarMotards();
            AdministradorBLL.listarAdministradores();
        } else
            System.out.println("Sem utilizadores registados!");
    }

    // Método que verifica se existe um utilizador com given username e nome já registado e retorna a password
    public static @Nullable String checkUsernameNif(String username, String nif) {
        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (username.equals(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getUsername()))
                if (nif.equals(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getNif()))
                    return Repositorio.getRepositorio().getUtilizadoresMap().get(key).getPassword();
        return null;
    }

    // Método para resetar password
    public static void resetPassword(String username, String newPassword) {
        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (username.equals(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getUsername()))
                Repositorio.getRepositorio().getUtilizadoresMap().get(key).setPassword(newPassword);
    }
}