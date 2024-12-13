package controller;

import view.*;
import model.*;
import javax.swing.*;
import java.awt.*;

public class TelaDeRemoverController extends TelaDeRemoverView {

    // Método para remover o item selecionado com base no ID da ComboBox
    public static void removerController() {
        // Chama o método do modelo para remover o registro com o ID selecionado
        TelaDeRemoverModel.removerModel(String.valueOf(cbxId.getSelectedItem()));
    }

    // Método para popular a ComboBox com os IDs dos registros
    public static void popularCbxIdController() {
        // Chama o método do modelo para popular a ComboBox com os IDs
        TelaDeRemoverModel.popularCbxIdModel();
    }

    // Método para adicionar um novo item à ComboBox de IDs
    public static void adicionarItemCbxId(String str) {
        // Adiciona um item à ComboBox de IDs
        cbxId.addItem(str);
    }

    // Método para preencher os campos da tela com as informações fornecidas
    public static void preencherCampos(String nome, String email, String foto) {
        // Verifica se a foto existe e tem conteúdo
        if (foto != null) {
            if (foto.length() > 0) {
                // Se a foto existir, exibe a imagem no JLabel
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewImgFolder + "/" + foto).getImage()
                        .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            } else {
                // Se a foto não tiver conteúdo, exibe uma imagem padrão
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "/imagem-padrao.jpg")
                        .getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            }
        } else {
            // Se não houver foto, exibe a imagem padrão
            lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "/imagem-padrao.jpg").getImage()
                    .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        }

        // Preenche os campos de texto com as informações de nome e email
        txtNome.setText(nome);
        txtEmail.setText(email);
    }

    // Método para atualizar os campos da tela com os dados do item selecionado na
    // ComboBox
    public static void atualizarCamposController() {
        // Chama o método do modelo para atualizar os campos com os dados do ID
        // selecionado
        TelaDeRemoverModel.atualizarCamposModel(String.valueOf(cbxId.getSelectedItem()));
    }
}
