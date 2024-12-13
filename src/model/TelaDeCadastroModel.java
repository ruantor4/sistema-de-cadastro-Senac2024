package model;

import controller.*;
import view.InterfaceView;

import java.sql.*;

public class TelaDeCadastroModel {

    // Método responsável por cadastrar um novo usuário no banco de dados
    public static void cadastrarModel(String nome, String email, String senha, String img) {
        try {
            // Monta a query SQL para inserir os dados do novo usuário na tabela 'tbl_login'
            String strSqlCadastrar = "insert into `login`.`tbl_login` (`nome`, `email`, `senha`, `img`) values ('"
                    + nome + "','" + email + "','" + senha + "','" + img + "');";

            // Estabelece a conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();

            // Cria a Statement para executar a query
            Statement stmSqlCadastrar = conexao.createStatement();

            // Adiciona a query à batch
            stmSqlCadastrar.addBatch(strSqlCadastrar);

            // Executa a batch para realizar a inserção no banco de dados
            stmSqlCadastrar.executeBatch();

            // Notifica o usuário sobre o sucesso do cadastro
            InterfaceView.notificarUsuario("Cadastro realizado com sucesso!", TelaDeCadastroController.lblNotificacoes);
        } catch (Exception e) {
            // Caso ocorra um erro, notifica o usuário e imprime o erro no console
            InterfaceView.notificarUsuario(
                    "Ops! Ocorreu um problema e não será possível cadastrar nesse momento. Por favor, tente novamente mais tarde.",
                    TelaDeCadastroController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
    }
}
