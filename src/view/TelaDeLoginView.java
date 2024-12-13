package view;

import controller.*; // Importando as classes do pacote controller

import java.awt.*; // Importando classes para o gerenciamento de componentes gráficos
import java.awt.event.*; // Importando classes para manipulação de eventos
import javax.swing.*; // Importando classes para a criação da interface gráfica

// Definindo a classe TelaDeLoginView que estende JFrame para criar uma tela de login
public class TelaDeLoginView extends JFrame {
    // Declaração dos componentes da interface gráfica
    public static JLabel lblLogin;
    public static JTextField txtLogin;
    public static JLabel lblSenha;
    public static JPasswordField txtSenha;
    public static JButton btnLogar;
    public static JLabel lblNotificacoes;

    // Declaração para o layout da interface e suas restrições
    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    // Construtor da classe que configura a tela
    public TelaDeLoginView() {
        super("Tela de Login"); // Definindo o título da janela

        // Inicializando o layout
        gbLayout = new GridBagLayout();
        setLayout(gbLayout); // Definindo o layout para o JFrame

        // Inicializando as restrições do GridBagLayout
        gbConstraints = new GridBagConstraints();

        // Inicializando e configurando o JLabel e JTextField para o login
        lblLogin = new JLabel("Login:");
        InterfaceView.addComponent(lblLogin, 0, 0, 1, 1, gbLayout, gbConstraints, this); // Adicionando o componente à
                                                                                         // tela

        txtLogin = new JTextField(10);
        InterfaceView.addComponent(txtLogin, 0, 1, 1, 1, gbLayout, gbConstraints, this); // Adicionando o campo de texto
                                                                                         // à tela

        // Inicializando e configurando o JLabel e JPasswordField para a senha
        lblSenha = new JLabel("Senha:");
        InterfaceView.addComponent(lblSenha, 1, 0, 1, 1, gbLayout, gbConstraints, this); // Adicionando o componente à
                                                                                         // tela

        txtSenha = new JPasswordField(10);
        InterfaceView.addComponent(txtSenha, 1, 1, 1, 1, gbLayout, gbConstraints, this); // Adicionando o campo de senha
                                                                                         // à tela

        // Inicializando o botão de login
        btnLogar = new JButton("Logar");
        InterfaceView.addComponent(btnLogar, 2, 0, 2, 1, gbLayout, gbConstraints, this); // Adicionando o botão à tela

        // Inicializando o JLabel para notificações
        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        // lblNotificacoes.setSize(getContentPane().getWidth(), 40); // Comentado,
        // talvez para ajuste de tamanho
        InterfaceView.addComponent(lblNotificacoes, 3, 0, 2, 1, gbLayout, gbConstraints, this); // Adicionando o
                                                                                                // componente à tela

        // Criando e adicionando um handler para o botão de login
        ButtonHandler buttonHandler = new ButtonHandler();
        btnLogar.addActionListener(buttonHandler); // Associando o evento de clique do botão

        // Adicionando um listener para detectar o pressionamento da tecla Enter na
        // senha
        txtSenha.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        // Verificando se há texto na senha e se a tecla Enter foi pressionada
                        if (String.valueOf(txtSenha.getPassword()).trim().length() > 0) {
                            if (e.getKeyCode() == 10) { // Código da tecla Enter
                                TelaDeLoginController.logarController(); // Chamando o controlador para realizar o login
                            }
                        }
                    }
                });

        // Definindo o ícone da interface
        InterfaceView.definirIcone(this);
        setSize(170, 140); // Definindo o tamanho da janela
        setVisible(true); // Tornando a janela visível
    }

    // Classe interna para tratar o evento de clique no botão de login
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            TelaDeLoginController.logarController(); // Chamando o controlador para realizar o login
        }
    }

    // Instanciando a tela de login
    public static TelaDeLoginView appTelaDeLoginView;

    // Método principal para rodar a aplicação
    public static void main(String[] args) {
        appTelaDeLoginView = new TelaDeLoginView(); // Criando a instância da tela de login
        appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE); // Definindo o comportamento de fechamento da janela
        // InterfaceView.verificarLarguraEAltura(appTelaDeAtualizacaoView,
        // lblNotificacoes); // Comentado, talvez para ajustes na interface
    }
}
