package model;

import controller.*;
import view.InterfaceView;

import java.sql.*;

public class TelaDeRemoverModel {

    /**
     * Método para remover um registro de usuário do banco de dados com base no ID
     * fornecido.
     * 
     * @param id O ID do usuário que será removido.
     */
    public static void removerModel(String id) {
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();

            // Cria a consulta SQL para excluir o registro com o ID especificado
            String strSqlRemoverId = "delete from `login`.`tbl_login` where `id` = " + id + ";";
            // Cria um statement para executar a consulta
            Statement stmSqlRemoverId = conexao.createStatement();
            // Adiciona a consulta ao batch de comandos
            stmSqlRemoverId.addBatch(strSqlRemoverId);
            // Executa o batch de comandos
            stmSqlRemoverId.executeBatch();

            // Notifica o usuário sobre o sucesso da remoção
            InterfaceView.notificarUsuario("O id " + id + " foi removido com sucesso.",
                    TelaDeRemoverController.lblNotificacoes);
        } catch (Exception e) {
            // Se ocorrer um erro, notifica o usuário
            InterfaceView.notificarUsuario("Ops! Problema no servidor, tente novamente mais tarde.",
                    TelaDeRemoverController.lblNotificacoes);
            // Imprime o erro no console para depuração
            System.err.println("Erro: " + e);
        }
    }

    /**
     * Método para popular o ComboBox com os IDs existentes na tabela `tbl_login` do
     * banco de dados.
     */
    public static void popularCbxIdModel() {
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();

            // Cria a consulta SQL para selecionar todos os registros da tabela `tbl_login`,
            // ordenados pelo ID
            String strSqlPopularCbxId = "select * from `login`.`tbl_login` order by `id` asc;";
            // Cria um statement para executar a consulta
            Statement stmSqlPopularCbxId = conexao.createStatement();
            // Executa a consulta e armazena os resultados
            ResultSet rstSqlPopularCbxId = stmSqlPopularCbxId.executeQuery(strSqlPopularCbxId);

            // Enquanto houverem registros na consulta, adiciona os IDs ao ComboBox
            while (rstSqlPopularCbxId.next()) {
                // Adiciona o ID ao ComboBox
                TelaDeRemoverController.adicionarItemCbxId(rstSqlPopularCbxId.getString("id"));
            }

            // Fecha o statement após a execução
            stmSqlPopularCbxId.close();
        } catch (Exception e) {
            // Se ocorrer um erro, notifica o usuário
            InterfaceView.notificarUsuario(
                    "Ops! Ocorreu um problema no servidor e não será possível carregar os ids neste momento. Por favor, retorne novamente mais tarde.",
                    TelaDeRemoverController.lblNotificacoes);
            // Imprime o erro no console para depuração
            System.err.println("Erro: " + e);
        }
    }

    /**
     * Método para atualizar os campos de exibição com os dados de um usuário com
     * base no ID fornecido.
     * 
     * @param strId O ID do usuário cujos dados serão carregados nos campos da
     *              interface.
     */
    public static void atualizarCamposModel(String strId) {
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();

            // Cria a consulta SQL para selecionar os dados do usuário com o ID especificado
            String strSqlAtualizarCampos = "select * from `login`.`tbl_login` where id = " + strId + ";";
            // Cria um statement para executar a consulta
            Statement stmSqlAtualizarCampos = conexao.createStatement();
            // Executa a consulta e armazena os resultados
            ResultSet rstSqlAtualizarCampos = stmSqlAtualizarCampos.executeQuery(strSqlAtualizarCampos);

            // Se o registro for encontrado, preenche os campos na interface
            if (rstSqlAtualizarCampos.next()) {
                // Preenche os campos com os dados do usuário
                TelaDeRemoverController.preencherCampos(rstSqlAtualizarCampos.getString("nome"),
                        rstSqlAtualizarCampos.getString("email"),
                        rstSqlAtualizarCampos.getString("img"));
            } else {
                // Caso não encontre o ID, notifica o usuário
                InterfaceView.notificarUsuario("Id não encontrado.", TelaDeRemoverController.lblNotificacoes);
            }
        } catch (Exception e) {
            // Se ocorrer um erro, notifica o usuário
            InterfaceView.notificarUsuario("Ops! Problema no servidor. Tente novamente mais tarde.",
                    TelaDeRemoverController.lblNotificacoes);
            // Imprime o erro no console para depuração
            System.err.println("Erro: " + e);
        }
    }
}
