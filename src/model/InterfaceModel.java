package model;

import controller.*;

import java.nio.file.*;
import java.sql.*;
import java.util.*;

public class InterfaceModel {

    // Método responsável por validar as imagens e excluir aquelas que não estão
    // mais associadas a registros
    public static void validarImagens(ArrayList<String> strImagens) {
        // Loop para percorrer todas as imagens fornecidas
        for (int i = 0; i < strImagens.size(); i++) {
            try {
                // Obtém o nome da imagem atual da lista
                String imgAtual = strImagens.get(i);

                // Cria a consulta SQL para verificar se a imagem está associada a um registro
                // na tabela 'tbl_login'
                String strSqlValidarImagem = "select * from `login`.`tbl_login` where `img` = '" + imgAtual + "';";

                // Estabelece uma conexão com o banco de dados MySQL
                Connection conexao = MySQLConnector.conectar();

                // Cria um Statement para executar a consulta SQL
                Statement stmSqlValidarImagem = conexao.createStatement();

                // Executa a consulta SQL e obtém o resultado
                ResultSet rstSqlValidarImagem = stmSqlValidarImagem.executeQuery(strSqlValidarImagem);

                // Se o resultado não contiver nenhum registro (a imagem não está associada a
                // nenhum usuário)
                if (!rstSqlValidarImagem.next()) {
                    // Cria um caminho para o arquivo de imagem a ser excluído
                    Path pathOrigin = Paths.get(InterfaceController.localViewImgFolder + "/" + imgAtual);

                    // Exclui o arquivo da imagem do diretório
                    Files.delete(pathOrigin);

                    // Imprime uma mensagem de sucesso na exclusão da imagem
                    System.out.println("Arquivo " + imgAtual + " apagado com sucesso!");
                }
            } catch (Exception e) {
                // Se ocorrer algum erro, exibe a mensagem de erro no console
                System.err.println("Erro: " + e);
            }
        }
    }
}
