package controller;

import view.*;
import model.*;

import java.util.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import javax.swing.*;
import java.awt.*;

public class TelaDeAtualizacaoController extends TelaDeAtualizacaoView {

    // Método para popular o ComboBox (cbxId) com os IDs obtidos do modelo
    public static void popularCbxIdController() {
        // Chama o método do modelo para obter os IDs e armazená-los em uma lista
        ArrayList<String> ids = TelaDeAtualizacaoModel.popularCbxIdModel();

        // Adiciona cada ID ao ComboBox cbxId
        for (int i = 0; i < ids.size(); i++) {
            cbxId.addItem(ids.get(i));
        }
    }

    // Método para atualizar os campos de texto e a foto com os dados do modelo
    public static void atualizarCamposController() {
        // Chama o método do modelo para obter os dados associados ao ID selecionado
        ArrayList<String> dados = TelaDeAtualizacaoModel.atualizarCamposModel(String.valueOf(cbxId.getSelectedItem()));

        // Obtém o nome do arquivo de foto
        String foto = dados.get(2);

        // Se houver foto, exibe-a no JLabel lblFoto
        if (foto != null) {
            if (foto.length() > 0) {
                // Se o nome da foto não estiver vazio, carrega a imagem e ajusta o tamanho
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewImgFolder + "/" + foto).getImage()
                        .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                btnRemoverFoto.setEnabled(true); // Habilita o botão de remoção de foto
            } else {
                // Se o nome da foto estiver vazio, exibe a imagem padrão
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "/imagem-padrao.jpg")
                        .getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                btnRemoverFoto.setEnabled(false); // Desabilita o botão de remoção de foto
            }
        } else {
            // Se não houver foto, exibe a imagem padrão
            lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "/imagem-padrao.jpg").getImage()
                    .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            btnRemoverFoto.setEnabled(false); // Desabilita o botão de remoção de foto
        }

        // Preenche os campos de texto com os dados recebidos
        txtNome.setText(dados.get(0));
        txtEmail.setText(dados.get(1));
    }

    // Método para atualizar os dados no modelo com as informações dos campos
    public static void atualizarController() {
        // Chama o método do modelo para atualizar os dados com os valores dos campos
        if (TelaDeAtualizacaoModel.atualizarModel(String.valueOf(cbxId.getSelectedItem()), txtNome.getText().trim(),
                txtEmail.getText().trim(), String.valueOf(txtSenha.getPassword()).trim(), nomeArquivoFoto)) {
            // Se a atualização for bem-sucedida, armazena os novos valores e desabilita o
            // botão de atualização
            txtNomeCarregado = txtNome.getText().trim();
            txtEmailCarregado = txtEmail.getText().trim();
            btnAtualizar.setEnabled(false);
        }
    }

    // Método para carregar uma foto do sistema de arquivos
    public static void carregarFoto() {
        try {
            // Cria um seletor de arquivos para o usuário escolher a foto
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Selecione o arquivo que deseja copiar");
            chooser.setApproveButtonText("Copiar arquivo");

            // Exibe o diálogo de escolha de arquivo e captura a resposta
            int returnVal = chooser.showOpenDialog(null);
            String fileFullPath = "";
            String fileName = "";

            // Se o usuário selecionou um arquivo
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                // Obtém o caminho completo e o nome do arquivo selecionado
                fileFullPath = chooser.getSelectedFile().getAbsolutePath();
                fileName = "file-" + Math.random() + "-" + chooser.getSelectedFile().getName();
            } else {
                // Caso o usuário não selecione um arquivo
                System.out.println("Usuário não selecionou o arquivo para copiar...");
            }

            // Define os caminhos de origem e destino do arquivo
            Path pathOrigin = Paths.get(fileFullPath);
            Path pathDestination = Paths.get(InterfaceView.localViewImgFolder + "/" + fileName);

            // Se o caminho do arquivo de origem não for vazio, copia o arquivo
            if (fileFullPath.length() > 0) {
                Files.copy(pathOrigin, pathDestination, REPLACE_EXISTING); // Copia o arquivo
                System.out.println("Arquivo " + chooser.getSelectedFile().getName() + " copiado/colado com sucesso.");
            } else {
                // Caso não seja possível copiar o arquivo
                System.out.println(
                        "Ops! Não foi possível copiar o arquivo. Por favor, verifique e tente novamente mais tarde.");
            }

            // Armazena o nome do arquivo da foto
            nomeArquivoFoto = fileName;

            // Atualiza o ícone do JLabel lblFoto com a nova imagem
            lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewImgFolder + "/" + nomeArquivoFoto)
                    .getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            btnRemoverFoto.setEnabled(true); // Habilita o botão de remoção de foto
            btnAtualizar.setEnabled(true); // Habilita o botão de atualização
        } catch (Exception e) {
            // Se houver algum erro durante o processo de cópia
            System.out.println("Não foi possível copiar o arquivo.");
        }
    }

    // Método para remover a foto e restaurar a imagem padrão
    public static void removerFoto() {
        // Restaura a imagem padrão no JLabel lblFoto
        lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "/imagem-padrao.jpg").getImage()
                .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        nomeArquivoFoto = ""; // Limpa o nome do arquivo da foto
        btnAtualizar.setEnabled(true); // Habilita o botão de atualização
        btnRemoverFoto.setEnabled(false); // Desabilita o botão de remoção de foto
    }
}
