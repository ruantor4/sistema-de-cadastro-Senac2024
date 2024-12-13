package view;

import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeAtualizacaoView extends JFrame {

    // Definição de variáveis estáticas para os componentes da interface
    public static JLabel lblFoto;
    public static JButton btnCarregarFoto;
    public static JButton btnRemoverFoto;
    public static String nomeArquivoFoto = "";

    public static JLabel lblId;
    public static JComboBox<String> cbxId;

    public static JLabel lblNome;
    public static JTextField txtNome;
    public static String txtNomeCarregado;

    public static JLabel lblEmail;
    public static JTextField txtEmail;
    public static String txtEmailCarregado;

    public static JLabel lblSenha;
    public static JPasswordField txtSenha;

    public static JButton btnAtualizar;
    public static JButton btnCancelar;

    public static JLabel lblNotificacoes;

    // Definição de variáveis para o GridBagLayout e suas restrições
    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    /**
     * Construtor da tela de atualização.
     * Aqui são inicializados os componentes da interface gráfica e configurados os
     * eventos.
     */
    public TelaDeAtualizacaoView() {
        super("Tela de Atualização");

        // Inicializa o layout e as restrições do GridBagLayout
        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbConstraints = new GridBagConstraints();

        // Inicializa o componente JLabel para foto e adiciona à tela
        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "/imagem-padrao.jpg").getImage()
                .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        InterfaceView.addComponent(lblFoto, 0, 0, 2, 2, gbLayout, gbConstraints, this);

        // Botão para carregar foto e adicionar à tela
        btnCarregarFoto = new JButton("Carregar foto");
        InterfaceView.addComponent(btnCarregarFoto, 2, 0, 1, 1, gbLayout, gbConstraints, this);

        // Botão para remover foto e adicionar à tela
        btnRemoverFoto = new JButton("Remover foto");
        InterfaceView.addComponent(btnRemoverFoto, 2, 1, 1, 1, gbLayout, gbConstraints, this);

        // Inicializa o JLabel e o JComboBox para o Id e os adiciona à tela
        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblId, 3, 0, 1, 1, gbLayout, gbConstraints, this);

        cbxId = new JComboBox<String>();
        TelaDeAtualizacaoController.popularCbxIdController();
        InterfaceView.addComponent(cbxId, 3, 1, 1, 1, gbLayout, gbConstraints, this);

        // Inicializa e adiciona os campos para o Nome e Email
        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblNome, 4, 0, 1, 1, gbLayout, gbConstraints, this);

        txtNome = new JTextField(10);
        InterfaceView.addComponent(txtNome, 4, 1, 1, 1, gbLayout, gbConstraints, this);

        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblEmail, 5, 0, 1, 1, gbLayout, gbConstraints, this);

        txtEmail = new JTextField(10);
        InterfaceView.addComponent(txtEmail, 5, 1, 1, 1, gbLayout, gbConstraints, this);

        // Chama o controlador para atualizar os campos de Nome e Email
        TelaDeAtualizacaoController.atualizarCamposController();

        // Inicializa e adiciona o campo para Senha
        lblSenha = new JLabel("Senha:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblSenha, 6, 0, 1, 1, gbLayout, gbConstraints, this);

        txtSenha = new JPasswordField(10);
        InterfaceView.addComponent(txtSenha, 6, 1, 1, 1, gbLayout, gbConstraints, this);

        // Inicializa os botões Atualizar e Cancelar
        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setEnabled(false); // O botão começa desabilitado
        InterfaceView.addComponent(btnAtualizar, 7, 0, 1, 1, gbLayout, gbConstraints, this);

        btnCancelar = new JButton("Cancelar");
        InterfaceView.addComponent(btnCancelar, 7, 1, 1, 1, gbLayout, gbConstraints, this);

        // Inicializa o JLabel para notificações
        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        InterfaceView.addComponent(lblNotificacoes, 8, 0, 2, 1, gbLayout, gbConstraints, this);

        // Adiciona um ouvinte para o JComboBox de ID para atualizar os campos quando um
        // novo ID for selecionado
        cbxId.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent event) {
                        if (event.getStateChange() == ItemEvent.SELECTED) {
                            TelaDeAtualizacaoController.atualizarCamposController();
                        }
                    }
                });

        // Ação do botão Atualizar, que chama o controlador para realizar a atualização
        btnAtualizar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDeAtualizacaoController.atualizarController();
                    }
                });

        // KeyListener para o campo Nome, que habilita o botão Atualizar se o nome for
        // alterado
        txtNome.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent event) {
                        if (txtNomeCarregado.trim().equals(txtNome.getText().trim())) {
                            btnAtualizar.setEnabled(false);
                        } else {
                            btnAtualizar.setEnabled(true);
                        }
                    }
                });

        // KeyListener para o campo Email, que habilita o botão Atualizar se o email for
        // alterado
        txtEmail.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent event) {
                        if (txtEmailCarregado.trim().equals(txtEmail.getText().trim())) {
                            btnAtualizar.setEnabled(false);
                        } else {
                            btnAtualizar.setEnabled(true);
                        }
                    }
                });

        // KeyListener para o campo Senha, que habilita o botão Atualizar se a senha for
        // alterada
        txtSenha.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent event) {
                        if (String.valueOf(txtSenha.getPassword()).trim().length() == 0) {
                            btnAtualizar.setEnabled(false);
                        } else {
                            btnAtualizar.setEnabled(true);
                        }
                    }
                });

        // Ação do botão Cancelar, que retorna para a tela de menu
        btnCancelar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDeMenuView.appTelaDeMenuView.setVisible(true);
                        dispose();
                    }
                });

        // Ação do botão Carregar Foto, que chama o controlador para carregar a foto
        btnCarregarFoto.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDeAtualizacaoController.carregarFoto();
                    }
                });

        // Ação do botão Remover Foto, que chama o controlador para remover a foto
        btnRemoverFoto.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDeAtualizacaoController.removerFoto();
                    }
                });

        // Define o ícone da tela
        InterfaceView.definirIcone(this);

        // Define o tamanho da janela
        setSize(350, 350);
        setVisible(true);
    }

    // Instância estática da tela de atualização
    public static TelaDeAtualizacaoView appTelaDeAtualizacaoView;

    // Método main para inicializar a tela de atualização ou de login
    public static void main(String[] args) {
        // Caso o idLoginAtual esteja vazio, abre a tela de login
        if (InterfaceView.idLoginAtual.equals("")) {
            TelaDeLoginView.appTelaDeLoginView = new TelaDeLoginView();
            TelaDeLoginView.appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        } else {
            // Caso contrário, abre a tela de atualização
            appTelaDeAtualizacaoView = new TelaDeAtualizacaoView();
            appTelaDeAtualizacaoView.setDefaultCloseOperation(EXIT_ON_CLOSE);
            // InterfaceView.verificarLarguraEAltura(appTelaDeAtualizacaoView,lblNotificacoes);
        }
    }
}
