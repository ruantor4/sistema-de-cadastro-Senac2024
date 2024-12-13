package controller;

import view.*;
import model.*;

import java.util.*;

public class TelaDeLoginController extends TelaDeLoginView {

    // Método para realizar o login, chamando o modelo para autenticação
    public static void logarController() {
        // Chama o método do modelo para realizar o login, passando o login e a senha do
        // usuário
        // O resultado é armazenado em uma lista de Strings
        ArrayList<String> resultados = new ArrayList<String>(
                TelaDeLoginModel.logarModel(txtLogin.getText(), String.valueOf(txtSenha.getPassword())));

        // Obtém o tamanho da lista de resultados (aqui, o valor não é utilizado)
        resultados.size();
    }

    // Método para abrir a tela de menu e fechar a tela de login
    public static void abrirTelaDeMenu() {
        // Cria uma nova instância da tela de menu
        TelaDeMenuView.appTelaDeMenuView = new TelaDeMenuView();

        // Define a operação padrão de fechamento da janela para "EXIT_ON_CLOSE"
        TelaDeMenuView.appTelaDeMenuView.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Fecha a tela de login após abrir a tela de menu
        TelaDeLoginView.appTelaDeLoginView.dispose();
    }
}