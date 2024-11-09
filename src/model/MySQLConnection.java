package model;

import java.sql.*;

public class MySQLConnection {
    public static Connection conectar(){
        String mysqlHost = "localhost";
        String mysqlDb = "login";
        String mysqlUser = "root";
        String mysqlPassword = "torquatop5w";
        String mysqlPort = "3306";
        String mysqlUrl = "jdbc:mysql://" + mysqlHost + ":" + mysqlPort + "/" + mysqlDb 
        + "?user=" + mysqlUser + "&password=" + mysqlPassword;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(mysqlUrl);
            System.out.println("Conecxão realizada com sucesso!");
        } catch (Exception e) {
            System.out.println
            ("Ops! ALgo de errado não está certo com sua conexao de banco de dados MYSQL! Mensagem do servidor:  "+ e );
        }
        
        
        return conn;
    }
}