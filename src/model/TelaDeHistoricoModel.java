package model;

import view.InterfaceView;

import java.util.*;
import java.sql.*;

public class TelaDeHistoricoModel {

    // Método responsável por capturar o histórico do usuário a partir do banco de
    // dados
    public static String[] capturarHistorico() {
        // Lista para armazenar os históricos do usuário
        ArrayList<String> strHistoricos = new ArrayList<String>();

        try {
            // Cria a query SQL para buscar os históricos do usuário logado (idLoginAtual)
            String strSqlCapturarHistorico = "select * from `login`.`tbl_historico` where `id_login` = "
                    + InterfaceView.idLoginAtual + ";";

            // Estabelece a conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();

            // Cria a Statement para executar a query
            Statement stmCapturarHistorico = conexao.createStatement();

            // Executa a query e obtém os resultados
            ResultSet rstCapturarHistorico = stmCapturarHistorico.executeQuery(strSqlCapturarHistorico);

            // Adiciona os resultados à lista
            while (rstCapturarHistorico.next()) {
                strHistoricos.add(rstCapturarHistorico.getString("txt_historico"));
            }
        } catch (Exception e) {
            // Caso ocorra um erro, imprime o erro no console
            System.err.println("Erro: " + e);
        }

        // Retorna o histórico como um array de strings
        return strHistoricos.toArray(new String[0]);
    }
}
