package controller;

import view.*;
import model.*;

import java.io.*;
import java.util.*;

public class InterfaceController extends InterfaceView {

    // Método para verificar e apagar imagens consideradas inúteis
    public static void verificarApagarImagensInuteis() {
        // Cria um objeto File com base no caminho da pasta de imagens definido em
        // localViewImgFolder
        final File folder = new File(localViewImgFolder);

        // Lista os nomes dos arquivos na pasta e armazena na lista strImagens
        ArrayList<String> strImagens = listFilesForFolder(folder);

        // Chama o método para validar as imagens listadas, removendo as inúteis
        InterfaceModel.validarImagens(strImagens);
    }

    // Método para listar todos os arquivos de uma pasta e subpastas
    public static ArrayList<String> listFilesForFolder(final File folder) {
        // Cria uma lista para armazenar os nomes dos arquivos encontrados
        ArrayList<String> strFiles = new ArrayList<String>();

        // Itera sobre todos os arquivos e diretórios dentro da pasta especificada
        for (final File fileEntry : folder.listFiles()) {
            // Verifica se a entrada atual é um diretório
            if (fileEntry.isDirectory()) {
                // Se for um diretório, chama o método recursivamente para listar os arquivos
                // dentro dele
                listFilesForFolder(fileEntry);
            } else {
                // Se for um arquivo, adiciona o nome do arquivo à lista
                strFiles.add(fileEntry.getName());
                // Linha de debug comentada para imprimir o nome do arquivo
                // System.out.println(fileEntry.getName());
            }
        }

        // Retorna a lista com os nomes dos arquivos encontrados
        return strFiles;
    }
}
