package model;

import java.sql.*;

public class MySQLConnector {

    // Método responsável por estabelecer e retornar uma conexão com o banco de
    // dados MySQL
    public static Connection conectar() {
        // Definição dos parâmetros de conexão com o banco de dados
        String mysqlHost = "127.0.0.1"; // Endereço IP do host do banco de dados
        String mysqlDb = "login"; // Nome do banco de dados
        String mysqlUser = "root"; // Nome de usuário do banco de dados
        String mysqlPassword = "torquatop5w"; // Senha do banco de dados
        String mysqlPort = "3306"; // Porta do MySQL
        String mysqlUrl = "jdbc:mysql://" + mysqlHost + ":" + mysqlPort + "/" + mysqlDb + "?user=" + mysqlUser
                + "&password=" + mysqlPassword; // URL de conexão

        Connection conn = null; // Declaração da variável que irá armazenar a conexão com o banco de dados

        try {
            // Carrega o driver JDBC para MySQL
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            // Estabelece a conexão com o banco de dados usando a URL e as credenciais
            // definidas acima
            conn = DriverManager.getConnection(mysqlUrl);

            // Caso queira ver a mensagem de sucesso de conexão, descomente a linha abaixo
            // System.out.println("Conexão realizada com sucesso!");
        } catch (Exception e) {
            // Em caso de erro ao conectar, imprime a mensagem de erro
            System.err.println(
                    "Ops! Algo de errado não está certo com a conexão com o banco de dados MySQL! Mensagem do servidor: "
                            + e);
        }

        // Retorna a conexão (pode ser null caso ocorra algum erro)
        return conn;
    }
}
