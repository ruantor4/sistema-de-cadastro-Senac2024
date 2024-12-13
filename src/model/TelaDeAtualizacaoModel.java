package model;

import controller.*;
import view.InterfaceView;

import java.sql.*;
import java.util.*;

public class TelaDeAtualizacaoModel {

    // Método para popular a ComboBox com os IDs do banco de dados
    public static ArrayList<String> popularCbxIdModel() {
        // Cria uma lista para armazenar os IDs
        ArrayList<String> ids = new ArrayList<String>();
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();

            // Define a query SQL para obter todos os IDs da tabela 'tbl_login'
            String strSqlPopularCbxId = "select * from `login`.`tbl_login` order by `id` asc;";
            Statement stmSqlPopularCbxId = conexao.createStatement();
            ResultSet rstSqlPopularCbxId = stmSqlPopularCbxId.executeQuery(strSqlPopularCbxId);

            // Itera sobre o resultado e adiciona os IDs à lista
            while (rstSqlPopularCbxId.next()) {
                ids.add(rstSqlPopularCbxId.getString("id"));
            }

            // Fecha a Statement após o uso
            stmSqlPopularCbxId.close();
        } catch (Exception e) {
            // Caso ocorra erro, notifica o usuário
            InterfaceView.notificarUsuario(
                    "Ops! Ocorreu um problema no servidor e não será possível carregar os ids neste momento. Por favor, retorne novamente mais tarde.",
                    TelaDeAtualizacaoController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
        // Retorna a lista com os IDs
        return ids;
    }

    // Método para atualizar os campos da interface com os dados do usuário com base
    // no ID
    public static ArrayList<String> atualizarCamposModel(String strId) {
        ArrayList<String> dados = new ArrayList<String>(); // Lista para armazenar os dados
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();

            // Define a query SQL para buscar os dados de um usuário com base no ID
            String strSqlAtualizarCampos = "select * from `login`.`tbl_login` where id = " + strId + ";";
            Statement stmSqlAtualizarCampos = conexao.createStatement();
            ResultSet rstSqlAtualizarCampos = stmSqlAtualizarCampos.executeQuery(strSqlAtualizarCampos);

            // Se um resultado for encontrado, preenche os dados
            if (rstSqlAtualizarCampos.next()) {
                dados.add(rstSqlAtualizarCampos.getString("nome"));
                TelaDeAtualizacaoController.txtNomeCarregado = rstSqlAtualizarCampos.getString("nome");
                dados.add(rstSqlAtualizarCampos.getString("email"));
                dados.add(rstSqlAtualizarCampos.getString("img"));
                TelaDeAtualizacaoController.txtEmailCarregado = rstSqlAtualizarCampos.getString("email");
            } else {
                // Caso o ID não seja encontrado, notifica o usuário
                InterfaceView.notificarUsuario("Id não encontrado.", TelaDeAtualizacaoController.lblNotificacoes);
            }
        } catch (Exception e) {
            // Caso ocorra erro, notifica o usuário
            InterfaceView.notificarUsuario("Ops! Problema no servidor. Tente novamente mais tarde.",
                    TelaDeAtualizacaoController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
        // Retorna a lista com os dados obtidos
        return dados;
    }

    // Método para atualizar os dados de um usuário no banco de dados
    public static boolean atualizarModel(String id, String nome, String email, String senha, String img) {
        boolean atualizou = false; // Flag que indica se a atualização foi bem-sucedida
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();

            // Prepara a parte do SQL para atualização da senha, caso tenha sido fornecida
            String atualizarSenha = "";
            if (senha.length() > 0) {
                atualizarSenha = ", `senha` = '" + senha + "'";
            }

            // Define a query SQL para atualizar os dados do usuário
            String strSqlAtualizarId = "update `login`.`tbl_login` set `nome` = '" + nome + "', `email` = '" + email
                    + "'" + atualizarSenha + ", `img` = '" + img + "' where `id` = " + id + ";";
            Statement stmSqlAtualizarId = conexao.createStatement();
            stmSqlAtualizarId.addBatch(strSqlAtualizarId); // Adiciona o comando à batch
            stmSqlAtualizarId.executeBatch(); // Executa a atualização em lote

            // Marca a atualização como bem-sucedida
            atualizou = true;

            // Notifica o usuário sobre o sucesso da atualização
            InterfaceView.notificarUsuario("O id " + id + " foi atualizado com sucesso.",
                    TelaDeAtualizacaoController.lblNotificacoes);
        } catch (Exception e) {
            // Caso ocorra erro, notifica o usuário
            InterfaceView.notificarUsuario("Ops! Problema no servidor, tente novamente mais tarde.",
                    TelaDeAtualizacaoController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
        // Retorna se a atualização foi bem-sucedida ou não
        return atualizou;
    }
}
