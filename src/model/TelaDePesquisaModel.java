package model;

import controller.*;
import view.InterfaceView;

import java.sql.*;

public class TelaDePesquisaModel {

    /**
     * Método responsável por registrar a pesquisa do usuário no histórico de
     * pesquisas.
     * Além disso, atualiza as cláusulas SQL para filtrar os registros com base na
     * pesquisa.
     */
    public static void registrarPesquisaModel(String pesquisa) {
        // Armazena a pesquisa realizada no controlador
        TelaDePesquisaController.registroDePesquisa = pesquisa;

        // Atualiza as cláusulas de pesquisa para o SQL
        if (TelaDePesquisaController.registroDePesquisa.length() > 0) {
            TelaDePesquisaController.clausulasDePesquisaComWhere = " where `nome` like '%"
                    + TelaDePesquisaController.registroDePesquisa + "%' or `email` like '%"
                    + TelaDePesquisaController.registroDePesquisa + "%'";
            TelaDePesquisaController.clausulasDePesquisaSemWhere = " and (`nome` like '%"
                    + TelaDePesquisaController.registroDePesquisa + "%' or `email` like '%"
                    + TelaDePesquisaController.registroDePesquisa + "%')";
        }

        // Registra a pesquisa no histórico do usuário
        try {
            String strSqlRegistrarHistorico = "insert into `login`.`tbl_historico` (`id_login`, `txt_historico`) values ("
                    + InterfaceView.idLoginAtual + ", '" + pesquisa + "')";
            Connection conexao = MySQLConnector.conectar();
            Statement stmSqlRegistrarHistorico = conexao.createStatement();
            stmSqlRegistrarHistorico.addBatch(strSqlRegistrarHistorico);
            stmSqlRegistrarHistorico.executeBatch();
        } catch (Exception e) {
            System.err.println("Erro: " + e);
        }

        // Chama o método para mostrar o primeiro registro
        TelaDePesquisaController.vaParaPrimeiroRegistro();
    }

    /**
     * Inicializa a pesquisa, movendo para o primeiro registro disponível.
     */
    public static void inicializacaoDeRegistrosModel() {
        // Chama o controlador para iniciar o processo de exibição do primeiro registro
        TelaDePesquisaController.vaParaPrimeiroRegistro();
    }

    /**
     * Exibe o primeiro registro que corresponde à pesquisa, incluindo a contagem de
     * registros encontrados.
     */
    public static void vaParaPrimeiroRegistroModel() {
        try {
            // Consulta SQL para pegar os registros filtrados pela pesquisa
            String strSqlInicializacao = "select * from `login`.`tbl_login` "
                    + TelaDePesquisaController.clausulasDePesquisaComWhere + " order by `id` asc;";
            Connection conexao = MySQLConnector.conectar();
            Statement stmSqlInicializacao = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rstSqlInicializacao = stmSqlInicializacao.executeQuery(strSqlInicializacao);

            int qtdResultados = 0;
            while (rstSqlInicializacao.next()) {
                qtdResultados++;
            }

            // Verifica se encontrou algum registro e atualiza a interface
            if (rstSqlInicializacao.first()) {
                // Atualiza os campos com os dados do primeiro registro
                TelaDePesquisaController.atualizarCampos(rstSqlInicializacao.getString("id"),
                        rstSqlInicializacao.getString("nome"), rstSqlInicializacao.getString("email"),
                        rstSqlInicializacao.getString("img"));
                // Notifica o usuário sobre o sucesso
                InterfaceView.notificarUsuario(
                        "Foram encontrados \"" + qtdResultados
                                + "\" registros. Primeiro registro posicionado com sucesso!",
                        TelaDePesquisaController.lblNotificacoes);
                // Habilita a navegação para o próximo registro
                TelaDePesquisaController.habilitarAvancar();
            } else {
                // Caso não haja registros, limpa os campos e desabilita botões
                TelaDePesquisaController.limparCampos();
                TelaDePesquisaController.desabilitarTodos();
                InterfaceView.notificarUsuario("Não foram encontrados registros.",
                        TelaDePesquisaController.lblNotificacoes);
            }
            stmSqlInicializacao.close();
        } catch (Exception e) {
            // Em caso de erro, exibe mensagem de erro
            InterfaceView.notificarUsuario(
                    "Ops! Houve um problema no servidor e não será possível inicializar os registros no momento. Por favor, retorne novamente mais tarde.",
                    TelaDePesquisaController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
    }

    /**
     * Exibe o próximo registro da pesquisa.
     */
    public static void vaParaProximoRegistroModel(String id) {
        try {
            // Consulta SQL para pegar o próximo registro baseado no id
            String strSqlProximoRegistro = "select * from `login`.`tbl_login` where `id` > " + id
                    + TelaDePesquisaController.clausulasDePesquisaSemWhere + " order by `id` asc;";
            Connection conexao = MySQLConnector.conectar();
            Statement stmSqlProximoRegistro = conexao.createStatement();
            ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);

            // Verifica se há um próximo registro
            if (rstSqlProximoRegistro.next()) {
                // Atualiza os campos com os dados do próximo registro
                TelaDePesquisaController.atualizarCampos(rstSqlProximoRegistro.getString("id"),
                        rstSqlProximoRegistro.getString("nome"), rstSqlProximoRegistro.getString("email"),
                        rstSqlProximoRegistro.getString("img"));
                // Notifica o usuário sobre o sucesso
                InterfaceView.notificarUsuario("Próximo registro posicionado com sucesso!",
                        TelaDePesquisaController.lblNotificacoes);
                // Habilita a navegação para todos os registros
                TelaDePesquisaController.habilitarTodos();
            } else {
                // Caso não haja mais registros, habilita o botão de voltar
                TelaDePesquisaController.habilitarVoltar();
                InterfaceView.notificarUsuario("Não foram encontrados registros adiante.",
                        TelaDePesquisaController.lblNotificacoes);
            }
            stmSqlProximoRegistro.close();
        } catch (Exception e) {
            // Exibe mensagem de erro caso ocorra um problema
            InterfaceView.notificarUsuario(
                    "Ops! Houve um problema no servidor e não será possível inicializar os registros no momento. Por favor, retorne novamente mais tarde.",
                    TelaDePesquisaController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
    }

    /**
     * Exibe o último registro da pesquisa.
     */
    public static void vaParaUltimoRegistroModel() {
        try {
            // Consulta SQL para pegar o último registro baseado na pesquisa
            String strSqlUltimoRegistro = "select * from `login`.`tbl_login` "
                    + TelaDePesquisaController.clausulasDePesquisaComWhere + " order by `id` desc;";
            Connection conexao = MySQLConnector.conectar();
            Statement stmSqlUltimoRegistro = conexao.createStatement();
            ResultSet rstSqlUltimoRegistro = stmSqlUltimoRegistro.executeQuery(strSqlUltimoRegistro);

            // Verifica se encontrou algum registro
            if (rstSqlUltimoRegistro.next()) {
                // Atualiza os campos com os dados do último registro
                TelaDePesquisaController.atualizarCampos(rstSqlUltimoRegistro.getString("id"),
                        rstSqlUltimoRegistro.getString("nome"), rstSqlUltimoRegistro.getString("email"),
                        rstSqlUltimoRegistro.getString("img"));
                // Notifica o usuário sobre o sucesso
                InterfaceView.notificarUsuario("Último registro posicionado com sucesso!",
                        TelaDePesquisaController.lblNotificacoes);
                // Habilita o botão de voltar
                TelaDePesquisaController.habilitarVoltar();
            } else {
                InterfaceView.notificarUsuario("Não foram encontrados registros.",
                        TelaDePesquisaController.lblNotificacoes);
            }
            stmSqlUltimoRegistro.close();
        } catch (Exception e) {
            // Exibe mensagem de erro caso ocorra um problema
            InterfaceView.notificarUsuario(
                    "Ops! Houve um problema no servidor e não será possível inicializar os registros no momento. Por favor, retorne novamente mais tarde.",
                    TelaDePesquisaController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
    }

    /**
     * Exibe o registro anterior à pesquisa atual.
     */
    public static void vaParaRegistroAnteriorModel(String id) {
        try {
            // Consulta SQL para pegar o registro anterior baseado no id
            String strSqlRegistroAnterior = "select * from `login`.`tbl_login` where `id` < " + id
                    + TelaDePesquisaController.clausulasDePesquisaSemWhere + " order by `id` desc;";
            Connection conexao = MySQLConnector.conectar();
            Statement stmSqlRegistroAnterior = conexao.createStatement();
            ResultSet rstSqlRegistroAnterior = stmSqlRegistroAnterior.executeQuery(strSqlRegistroAnterior);

            // Verifica se há um registro anterior
            if (rstSqlRegistroAnterior.next()) {
                // Atualiza os campos com os dados do registro anterior
                TelaDePesquisaController.atualizarCampos(rstSqlRegistroAnterior.getString("id"),
                        rstSqlRegistroAnterior.getString("nome"), rstSqlRegistroAnterior.getString("email"),
                        rstSqlRegistroAnterior.getString("img"));
                // Notifica o usuário sobre o sucesso
                InterfaceView.notificarUsuario("Registro anterior posicionado com sucesso!",
                        TelaDePesquisaController.lblNotificacoes);
                // Habilita a navegação para todos os registros
                TelaDePesquisaController.habilitarTodos();
            } else {
                // Caso não haja mais registros anteriores, habilita o botão de avançar
                TelaDePesquisaController.habilitarAvancar();
                InterfaceView.notificarUsuario("Não foram encontrados registros anteriores.",
                        TelaDePesquisaController.lblNotificacoes);
            }
            stmSqlRegistroAnterior.close();
        } catch (Exception e) {
            // Exibe mensagem de erro caso ocorra um problema
            InterfaceView.notificarUsuario(
                    "Ops! Houve um problema no servidor e não será possível inicializar os registros no momento. Por favor, retorne novamente mais tarde.",
                    TelaDePesquisaController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
    }
}
