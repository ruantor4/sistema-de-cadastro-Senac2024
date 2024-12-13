package controller;

import view.*;
import model.*;

import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import javax.swing.*;
import java.awt.*;

public class TelaDeCadastroController extends TelaDeCadastroView {

    // Método para cadastrar um novo usuário chamando o modelo de cadastro
    public static void cadastrarController() {
        // Chama o método do modelo para cadastrar o novo usuário com os dados
        // fornecidos
        TelaDeCadastroModel.cadastrarModel(txtNome.getText(), txtEmail.getText(),
                String.valueOf(txtSenha.getPassword()), nomeArquivoFoto);
    }

    // Método para carregar uma foto do sistema de arquivos
    public static void carregarFoto() {
        try {
            // Cria um seletor de arquivos para o usuário escolher a foto
            JFileChooser chooser = new JFileChooser();

            // Configura o título do diálogo e o texto do botão
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
    }
}
