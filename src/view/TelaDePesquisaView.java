package view;

import controller.*; // Importando classes do pacote controller

import java.awt.*; // Importando classes para componentes gráficos e layouts
import java.awt.event.*; // Importando classes para manipulação de eventos
import javax.swing.*; // Importando classes para componentes de interface gráfica

// Definindo a classe TelaDePesquisaView que estende JFrame para criar a interface gráfica da tela de pesquisa
public class TelaDePesquisaView extends JFrame {

    // Declaração dos componentes da interface gráfica
    public static JLabel lblFoto;
    public static final JTextField txtPesquisa = new JTextField(20);
    public final JButton btnPesquisar;
    public final JButton btnReiniciarPesquisa;
    public final JButton btnHistoricoPesquisa;

    public final JLabel lblId;
    public static final JTextField txtId = new JTextField(10);

    public final JLabel lblNome;
    public static final JTextField txtNome = new JTextField(10);

    public final JLabel lblEmail;
    public static final JTextField txtEmail = new JTextField(10);

    // Botões de navegação entre registros
    public static final JButton btnPrimeiro = new JButton("<<");
    public static final JButton btnAnterior = new JButton("<");
    public static final JButton btnProximo = new JButton(">");
    public static final JButton btnUltimo = new JButton(">>");

    public static final JLabel lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);

    // Layout da interface
    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    // Construtor da classe que configura a tela de pesquisa
    public TelaDePesquisaView() {
        super("Tela de Pesquisa"); // Definindo o título da janela

        // Inicializando o layout
        gbLayout = new GridBagLayout();
        setLayout(gbLayout); // Definindo o layout do JFrame
        gbConstraints = new GridBagConstraints(); // Definindo as restrições do layout

        // Configuração do componente de imagem (foto)
        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "/imagem-padrao.jpg").getImage()
                .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        InterfaceView.addComponent(lblFoto, 0, 0, 4, 2, gbLayout, gbConstraints, this);

        // Adicionando o campo de pesquisa à tela
        InterfaceView.addComponent(txtPesquisa, 2, 0, 4, 1, gbLayout, gbConstraints, this);

        // Inicializando os botões e adicionando-os à tela
        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setEnabled(false); // Inicialmente desabilitado
        InterfaceView.addComponent(btnPesquisar, 3, 0, 1, 1, gbLayout, gbConstraints, this);

        btnReiniciarPesquisa = new JButton("Reiniciar");
        InterfaceView.addComponent(btnReiniciarPesquisa, 3, 1, 2, 1, gbLayout, gbConstraints, this);

        btnHistoricoPesquisa = new JButton("Histórico");
        InterfaceView.addComponent(btnHistoricoPesquisa, 3, 3, 1, 1, gbLayout, gbConstraints, this);

        // Labels e campos de texto para exibição do id, nome e e-mail
        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblId, 4, 0, 2, 1, gbLayout, gbConstraints, this);

        txtId.setEditable(false);
        InterfaceView.addComponent(txtId, 4, 2, 2, 1, gbLayout, gbConstraints, this);

        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblNome, 5, 0, 2, 1, gbLayout, gbConstraints, this);

        txtNome.setEditable(false);
        InterfaceView.addComponent(txtNome, 5, 2, 2, 1, gbLayout, gbConstraints, this);

        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblEmail, 6, 0, 2, 1, gbLayout, gbConstraints, this);

        txtEmail.setEditable(false);
        InterfaceView.addComponent(txtEmail, 6, 2, 2, 1, gbLayout, gbConstraints, this);

        // Botões de navegação entre registros
        InterfaceView.addComponent(btnPrimeiro, 7, 0, 1, 1, gbLayout, gbConstraints, this);
        InterfaceView.addComponent(btnAnterior, 7, 1, 1, 1, gbLayout, gbConstraints, this);
        InterfaceView.addComponent(btnProximo, 7, 2, 1, 1, gbLayout, gbConstraints, this);
        InterfaceView.addComponent(btnUltimo, 7, 3, 1, 1, gbLayout, gbConstraints, this);

        // Adicionando o campo de notificações à tela
        InterfaceView.addComponent(lblNotificacoes, 8, 0, 4, 1, gbLayout, gbConstraints, this);

        // Adicionando listeners aos botões
        btnPrimeiro.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDePesquisaController.vaParaPrimeiroRegistro();
                    }
                });

        btnAnterior.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDePesquisaController.vaParaRegistroAnterior();
                    }
                });

        btnProximo.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDePesquisaController.vaParaProximoRegistro();
                    }
                });

        btnUltimo.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDePesquisaController.vaParaUltimoRegistro();
                    }
                });

        btnPesquisar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDePesquisaController.registrarPesquisa();
                        txtPesquisa.setText(txtPesquisa.getText().trim());
                    }
                });

        txtPesquisa.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        btnPesquisar.setEnabled(detectarPesquisa());
                        if (e.getKeyCode() == 10 && txtPesquisa.getText().trim().length() > 0) {
                            TelaDePesquisaController.registrarPesquisa();
                            txtPesquisa.setText(txtPesquisa.getText().trim());
                        }
                    }
                });

        btnReiniciarPesquisa.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        txtPesquisa.setText(""); // Limpando a pesquisa
                        TelaDePesquisaController.registroDePesquisa = "";
                        TelaDePesquisaController.clausulasDePesquisaComWhere = "";
                        TelaDePesquisaController.clausulasDePesquisaSemWhere = "";
                        TelaDePesquisaController.vaParaPrimeiroRegistro();
                        btnPesquisar.setEnabled(false); // Desabilitando o botão de pesquisa
                        txtPesquisa.requestFocus(); // Focando no campo de pesquisa
                    }
                });

        btnHistoricoPesquisa.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TelaDeHistoricoView.appTelaDeHistoricoView = new TelaDeHistoricoView(); // Exibindo a tela de
                                                                                                // histórico
                        TelaDeHistoricoView.appTelaDeHistoricoView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                        TelaDeHistoricoView.appTelaDeHistoricoView.addWindowListener(
                                new WindowAdapter() {
                                    public void windowClosing(WindowEvent e) {
                                        TelaDePesquisaView.appTelaDePesquisaView.setVisible(true); // Voltando para a
                                                                                                   // tela de pesquisa
                                    }
                                });

                        setVisible(false); // Ocultando a tela de pesquisa
                    }
                });

        // Definindo o ícone da interface
        InterfaceView.definirIcone(this);
        setSize(300, 340); // Definindo o tamanho da janela
        setVisible(true); // Tornando a janela visível

        // Inicializando os registros de pesquisa
        TelaDePesquisaController.inicializacaoDeRegistros();
    }

    // Métodos para habilitar ou desabilitar os botões de navegação
    public static void habilitarVoltar() {
        btnPrimeiro.setEnabled(true);
        btnAnterior.setEnabled(true);
        btnProximo.setEnabled(false);
        btnUltimo.setEnabled(false);
    }

    public static void habilitarAvancar() {
        btnPrimeiro.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(true);
        btnUltimo.setEnabled(true);
    }

    public static void habilitarTodos() {
        btnPrimeiro.setEnabled(true);
        btnAnterior.setEnabled(true);
        btnProximo.setEnabled(true);
        btnUltimo.setEnabled(true);
    }

    public static void desabilitarTodos() {
        btnPrimeiro.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(false);
        btnUltimo.setEnabled(false);
    }

    // Método para detectar se a pesquisa foi preenchida
    public static boolean detectarPesquisa() {
        if (txtPesquisa.getText().trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // Método para limpar os campos de id, nome e email
    public static void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtEmail.setText("");
    }

    // Método principal para iniciar a aplicação
    public static TelaDePesquisaView appTelaDePesquisaView;

    public static void main(String[] args) {
        // Se o usuário não estiver logado, exibe a tela de login
        if (InterfaceView.idLoginAtual.equals("")) {
            TelaDeLoginView.appTelaDeLoginView = new TelaDeLoginView();
            TelaDeLoginView.appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        } else {
            // Caso contrário, exibe a tela de pesquisa
            appTelaDePesquisaView = new TelaDePesquisaView();
            appTelaDePesquisaView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
}
