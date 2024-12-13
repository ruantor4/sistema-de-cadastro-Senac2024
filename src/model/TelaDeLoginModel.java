package model;

import controller.*;
import view.InterfaceView;

import java.sql.*;
import java.util.*;

public class TelaDeLoginModel {

    // Método responsável por realizar o login do usuário
    public static ArrayList<String> logarModel(String login, String senha) {
        // Lista para armazenar os resultados da operação (usada como retorno)
        ArrayList<String> resultados = new ArrayList<String>();

        // Inicializa a lista com um valor de exemplo
        resultados.add("resultado1");

        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();

            // Cria a query SQL para verificar o login e senha do usuário
            String strSqlLogin = "select * from `login`.`tbl_login` where email = '" + login + "' and senha = '" + senha
                    + "';";

            // Cria a Statement para executar a query
            Statement stmSqlLogin = conexao.createStatement();

            // Executa a query e obtém os resultados
            ResultSet rstSqlLogin = stmSqlLogin.executeQuery(strSqlLogin);

            // Verifica se o usuário foi encontrado
            if (rstSqlLogin.next()) {
                // Caso o login seja bem-sucedido, notifica o usuário e abre a tela de menu
                InterfaceView.notificarUsuario("Login " + rstSqlLogin.getString("email") + " realizado com sucesso.",
                        TelaDeLoginController.lblNotificacoes);
                TelaDeLoginController.abrirTelaDeMenu();

                // Armazena o id do usuário logado
                InterfaceView.idLoginAtual = rstSqlLogin.getString("id");
            } else {
                // Caso não encontre o usuário, notifica o erro
                InterfaceView.notificarUsuario(
                        "Não foi possível encontrar o login e/ou senha digitados. Por favor, verifique e tente novamente.",
                        TelaDeLoginController.lblNotificacoes);
            }

            // Fecha a Statement após o uso
            stmSqlLogin.close();
        } catch (Exception e) {
            // Caso ocorra um erro durante a execução, notifica o usuário
            InterfaceView.notificarUsuario(
                    "Houve um problema e não será possível realizar o login neste momento. Por favor, tente novamente mais tarde.",
                    TelaDeLoginController.lblNotificacoes);
            System.err.println("Ops! Deu ruim, se liga no erro: " + e);
        }

        // Retorna a lista de resultados
        return resultados;
    }
}
