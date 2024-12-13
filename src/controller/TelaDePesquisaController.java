package controller;

import view.*;
import model.*;
import javax.swing.*;
import java.awt.*;

public class TelaDePesquisaController extends TelaDePesquisaView {

    // Variáveis estáticas para armazenar dados de pesquisa e cláusulas de pesquisa
    public static String registroDePesquisa = "";
    public static String clausulasDePesquisaComWhere = "";
    public static String clausulasDePesquisaSemWhere = "";

    // Método para navegar até o primeiro registro de pesquisa
    public static void vaParaPrimeiroRegistro() {
        // Chama o método do modelo para ir ao primeiro registro
        TelaDePesquisaModel.vaParaPrimeiroRegistroModel();
    }

    // Método para navegar até o registro anterior na pesquisa
    public static void vaParaRegistroAnterior() {
        // Chama o método do modelo para ir ao registro anterior, passando o ID atual
        TelaDePesquisaModel.vaParaRegistroAnteriorModel(txtId.getText());
    }

    // Método para navegar até o próximo registro na pesquisa
    public static void vaParaProximoRegistro() {
        // Chama o método do modelo para ir ao próximo registro, passando o ID atual
        TelaDePesquisaModel.vaParaProximoRegistroModel(txtId.getText());
    }

    // Método para navegar até o último registro de pesquisa
    public static void vaParaUltimoRegistro() {
        // Chama o método do modelo para ir ao último registro
        TelaDePesquisaModel.vaParaUltimoRegistroModel();
    }

    // Método para registrar a pesquisa com o texto fornecido
    public static void registrarPesquisa() {
        // Chama o método do modelo para registrar a pesquisa, removendo espaços extras
        TelaDePesquisaModel.registrarPesquisaModel(txtPesquisa.getText().trim());
    }

    // Método para inicializar os registros da pesquisa
    public static void inicializacaoDeRegistros() {
        // Chama o método do modelo para inicializar os registros da pesquisa
        TelaDePesquisaModel.inicializacaoDeRegistrosModel();
    }

    // Método para atualizar os campos da tela de pesquisa com os dados fornecidos
    public static void atualizarCampos(String id, String nome, String email, String foto) {
        // Verifica se existe uma foto associada ao registro
        if (foto != null) {
            if (foto.length() > 0) {
                // Se a foto existe e tem conteúdo, exibe a imagem no JLabel
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewImgFolder + "/" + foto).getImage()
                        .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            } else {
                // Caso contrário, exibe uma imagem padrão
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "/imagem-padrao.jpg")
                        .getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            }
        } else {
            // Se a foto for nula, exibe a imagem padrão
            lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "/imagem-padrao.jpg").getImage()
                    .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        }

        // Atualiza os campos de ID, nome e e-mail com os valores fornecidos
        txtId.setText(id);
        txtNome.setText(nome);
        txtEmail.setText(email);
    }
}
