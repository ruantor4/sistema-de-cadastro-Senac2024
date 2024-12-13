package view;

import controller.*;

import java.awt.*;
import java.awt.event.*;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class InterfaceView extends JFrame {

    // Constantes para definir o tamanho da imagem
    public static final int IMG_WIDTH = 100;
    public static final int IMG_HEIGHT = 100;

    // Variável estática para armazenar o ID do login atual
    public static String idLoginAtual = "";

    // Caminho do diretório onde as imagens da view estão localizadas
    public static final String localViewImgFolder = System.getProperty("user.dir")
            + "/"
            + "src"
            + "/"
            + "view"
            + "/"
            + "img";

    // Caminho do diretório onde os arquivos da view estão localizados
    public static final String localViewFolder = System.getProperty("user.dir")
            + "/"
            + "src"
            + "/"
            + "view";

    
    public static void addComponent(Component component, int row, int column, int width, int height,
            GridBagLayout gbLayout, GridBagConstraints gbConstraints, JFrame frame) {
        // Definindo a forma de preenchimento do componente no layout
        if (height > 1 && width > 1) {
            gbConstraints.fill = GridBagConstraints.BOTH;
        } else if (height > 1) {
            gbConstraints.fill = GridBagConstraints.VERTICAL;
        } else {
            gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        }

        // Definindo as posições do componente no GridBagLayout
        gbConstraints.gridy = row;
        gbConstraints.gridx = column;
        gbConstraints.gridwidth = width;
        gbConstraints.gridheight = height;

        // Definindo o componente no layout
        gbLayout.setConstraints(component, gbConstraints);
        frame.add(component);
    }

    /**
     * Método para verificar o redimensionamento da janela e notificar as dimensões
     * atuais.
     */
    public static void verificarLarguraEAltura(JFrame frame, JLabel label) {
        // Adiciona um ouvinte para detectar mudanças no tamanho da janela
        frame.getRootPane().addComponentListener(
                new ComponentAdapter() {
                    public void componentResized(ComponentEvent e) {
                        // Obtém a largura e altura da tela
                        int larguraTela = frame.getWidth();
                        int alturaTela = frame.getHeight();

                        // Notifica o usuário com as novas dimensões
                        notificarUsuario(String.format("Largura: %s, Altura: %s", larguraTela, alturaTela), label);
                    }
                });
    }

    /**
     * Método para notificar o usuário com uma mensagem formatada.
     * 
     * @param str   A mensagem a ser exibida.
     * @param label O JLabel onde a mensagem será exibida.
     */
    public static void notificarUsuario(String str, JLabel label) {
        // Define o texto do label utilizando o formato HTML
        label.setText(setHtmlFormat(str));
    }

    /**
     * Método para formatar a string como HTML.
     * 
     * 
     */
    public static String setHtmlFormat(String str) {
        return "<html><body>" + str + "</body></html>";
    }

    /**
     * Método para remover imagens inúteis da interface.
     */
    public static void removerImagensInuteis() {
        // Chama o controlador para verificar e remover as imagens desnecessárias
        InterfaceController.verificarApagarImagensInuteis();
    }

    /**
     * Método para definir o ícone da janela principal (JFrame).
    
     */
    public static void definirIcone(JFrame frame) {
        try {
            // Carrega a imagem do ícone a partir do diretório de recursos
            InputStream imageInputStream = frame.getClass().getResourceAsStream("senac-logo.png");
            // Lê a imagem como um objeto BufferedImage
            BufferedImage bufferedImage = ImageIO.read(imageInputStream);
            // Define a imagem como o ícone da janela
            frame.setIconImage(bufferedImage);
        } catch (Exception e) {
            // Em caso de erro, imprime o erro no console
            System.err.println("Erro: " + e);
        }
    }
}
