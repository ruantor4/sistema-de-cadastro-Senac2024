package model;

import controller.*;
import java.sql.*;
import java.util.ArrayList;

public class TelaDeLoginModel {
    public static ArrayList<String> logarModel(String login, String senha){
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("Resultado1");
        try {

            Connection conexao = MySQLConnection.conectar();
            String strSqlLogin = "select * from `login`.`tbl_login` where `email` = '" 
            + login + "' and `senha` = '" + senha + "';";
            Statement stmSqlLogin = conexao.createStatement();
            ResultSet rstSqlLogin = stmSqlLogin.executeQuery(strSqlLogin);
    
            if (rstSqlLogin.next()){
               TelaDeLoginController.notificarUsuario("Login realizado com sucesso");
            }else{
                TelaDeLoginController.notificarUsuario("Não foi possivel encontrar o login e/ou senha digitado. Por favor, verifique e tente novamente");
            }
            stmSqlLogin.close();
        }catch (Exception e) {
            TelaDeLoginController.notificarUsuario("Houve um problema e não será possivel realizar o login neste momento, por favor tente mais tarde.");
            System.err.println("Ops! Deu ruim, se liga no erro: "+ e);
    }    
        
        return resultados;
    }
}
