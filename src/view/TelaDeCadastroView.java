// Pacote da view, onde a tela de cadastro está implementada
package view;

// Importação das classes necessárias para eventos e componentes gráficos
import controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Classe que representa a tela de cadastro
public class TelaDeCadastroView extends JFrame {

    // Componentes gráficos da tela
    public static JLabel lblFoto; // Label para exibir a foto
    public static JButton btnCarregarFoto; // Botão para carregar foto
    public static JButton btnRemoverFoto; // Botão para remover foto
    public static String nomeArquivoFoto = ""; // Nome do arquivo da foto

    public static JLabel lblNome; // Label para o campo Nome
    public static JTextField txtNome; // Campo de texto para o nome

    public static JLabel lblEmail; // Label para o campo Email
    public static JTextField txtEmail; // Campo de texto para o email

    public static JLabel lblSenha; // Label para o campo Senha
    public static JPasswordField txtSenha; // Campo de senha (senha oculta)

    public static JButton btnCadastrar; // Botão para cadastrar o usuário
    public static JButton btnCancelar; // Botão para cancelar o cadastro

    public static JLabel lblNotificacoes; // Label para exibir notificações ou mensagens de erro

    public static GridBagLayout gbLayout; // Layout GridBagLayout para a tela
    public static GridBagConstraints gbConstraints; // Restrições do layout

    // Construtor da classe, responsável pela configuração da tela
    public TelaDeCadastroView() {
        super("Tela de Cadastro"); // Define o título da janela

        // Inicializa o layout e as restrições
        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbConstraints = new GridBagConstraints();
        
        // Configura o label da foto com uma imagem padrão
        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "src/view/imagem-padrao.jpg").getImage()
                .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        InterfaceView.addComponent(lblFoto, 0, 0, 2, 2, gbLayout, gbConstraints, this);
     
        System.out.println(InterfaceView.localViewFolder + "src/view/imagem-padrao.jpg");

        // Configura o botão para carregar foto
        btnCarregarFoto = new JButton("Carregar foto");
        InterfaceView.addComponent(btnCarregarFoto, 2, 0, 1, 1, gbLayout, gbConstraints, this);

        // Configura o botão para remover foto
        btnRemoverFoto = new JButton("Remover foto");
        InterfaceView.addComponent(btnRemoverFoto, 2, 1, 1, 1, gbLayout, gbConstraints, this);

        // Configura o label e o campo de texto para o nome
        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblNome, 3, 0, 1, 1, gbLayout, gbConstraints, this);
        txtNome = new JTextField(10);
        InterfaceView.addComponent(txtNome, 3, 1, 1, 1, gbLayout, gbConstraints, this);

        // Configura o label e o campo de texto para o email
        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblEmail, 4, 0, 1, 1, gbLayout, gbConstraints, this);
        txtEmail = new JTextField(10);
        InterfaceView.addComponent(txtEmail, 4, 1, 1, 1, gbLayout, gbConstraints, this);

        // Configura o label e o campo de senha
        lblSenha = new JLabel("Senha:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblSenha, 5, 0, 1, 1, gbLayout, gbConstraints, this);
        txtSenha = new JPasswordField(10);
        InterfaceView.addComponent(txtSenha, 5, 1, 1, 1, gbLayout, gbConstraints, this);

        // Configura o botão de cadastro
        btnCadastrar = new JButton("Cadastrar");
        InterfaceView.addComponent(btnCadastrar, 6, 0, 1, 1, gbLayout, gbConstraints, this);

        // Configura o botão de cancelar
        btnCancelar = new JButton("Cancelar");
        InterfaceView.addComponent(btnCancelar, 6, 1, 1, 1, gbLayout, gbConstraints, this);

        // Configura o label para exibir notificações
        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        InterfaceView.addComponent(lblNotificacoes, 7, 0, 2, 1, gbLayout, gbConstraints, this);

        // Ação para o botão "Cadastrar"
        btnCadastrar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        // Verificação se o nome foi preenchido
                        if (txtNome.getText().trim().length() == 0) {
                            lblNotificacoes.setText(
                                    "É necessário digitar alguma coisa no campo Nome. Por favor, digite um caracter válido no campo Nome para prosseguir.");
                            txtNome.requestFocus();
                            return;
                        }

                        // Verificação se o email foi preenchido
                        if (txtEmail.getText().trim().length() == 0) {
                            lblNotificacoes.setText(
                                    "É necessário digitar alguma coisa no campo Email. Por favor, digite um caracter válido no campo Email para prosseguir.");
                            txtEmail.requestFocus();
                            return;
                        }

                        // Verificação se o email contém '@'
                        if (txtEmail.getText().trim().indexOf('@') < 0) {
                            lblNotificacoes.setText(
                                    "É necessário digitar um @ no campo Email. Por favor, digite um @ no campo Email para prosseguir.");
                            txtEmail.requestFocus();
                            return;
                        }

                        // Verificação se o email contém '.'
                        if (txtEmail.getText().trim().indexOf('.') < 0) {
                            lblNotificacoes.setText(
                                    "É necessário digitar um . no campo Email. Por favor, digite um . no campo Email para prosseguir.");
                            txtEmail.requestFocus();
                            return;
                        }

                        // Verificação se o email tem ao menos 10 caracteres
                        if (txtEmail.getText().trim().length() < 10) {
                            lblNotificacoes.setText(
                                    "É necessário digitar no mínimo dez caracteres no campo Email. Por favor, digite no mínimo dez caracteres no campo Email para prosseguir.");
                            txtEmail.requestFocus();
                            return;
                        }

                        // Verificação do tamanho da parte antes do '@' no email
                        int antesDoArroba = txtEmail.getText().trim().lastIndexOf('@');
                        String strAntesDoArroba = txtEmail.getText().trim().substring(0, antesDoArroba);
                        if (strAntesDoArroba.length() < 3) {
                            lblNotificacoes.setText(
                                    "É necessário digitar no mínimo três caracteres antes do @ no campo Email. Por favor, digite no mínimo três caracteres antes do @ no campo Email para prosseguir.");
                            txtEmail.requestFocus();
                            return;
                        }

                        // Verificação do tamanho da parte entre '@' e '.' no email
                        int antesDoPonto = txtEmail.getText().trim().lastIndexOf('.');
                        if ((antesDoPonto - antesDoArroba) < 4) {
                            lblNotificacoes.setText(
                                    "É necessário digitar no mínimo três caracteres depois do @ e antes do . no campo Email. Por favor, digite no mínimo três caracteres depois do @ e antes do . no campo Email para prosseguir.");
                            txtEmail.requestFocus();
                            return;
                        }

                        // Verificação do tamanho da parte depois do '.' no email
                        String strDepoisDoPonto = txtEmail.getText().trim().substring(antesDoPonto + 1);
                        if (strDepoisDoPonto.length() < 2) {
                            lblNotificacoes.setText(
                                    "É necessário digitar no mínimo dois caracteres depois do . no campo Email. Por favor, digite no mínimo dois caracteres depois do . no campo Email para prosseguir.");
                            txtEmail.requestFocus();
                            return;
                        }

                        // Verificação se a senha foi preenchida
                        if (String.valueOf(txtSenha.getPassword()).trim().length() == 0) {
                            lblNotificacoes.setText(
                                    "É necessário digitar alguma coisa no campo Senha. Por favor, digite um caracter válido no campo Senha para prosseguir.");
                            txtSenha.requestFocus();
                            return;
                        }

                        // Chama o método do controller para cadastrar o usuário
                        TelaDeCadastroController.cadastrarController();
                    }
                });

        // Ação para o botão "Cancelar" (voltar para o menu)
        btnCancelar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDeMenuView.appTelaDeMenuView.setVisible(true); // Exibe a tela de menu
                        dispose(); // Fecha a tela de cadastro
                    }
                });

        // Ação para o botão "Carregar Foto"
        btnCarregarFoto.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDeCadastroController.carregarFoto(); // Chama o método do controller para carregar foto
                    }
                });

        // Ação para o botão "Remover Foto"
        btnRemoverFoto.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TelaDeCadastroController.removerFoto(); // Chama o método do controller para remover foto
                    }
                });

        // Define o ícone da janela
        InterfaceView.definirIcone(this);

        // Define o tamanho da janela e a exibe
        setSize(280, 280);
        setVisible(true);
    }

    // Instância da tela de cadastro
    public static TelaDeCadastroView appTelaDeCadastroView;

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        // Verifica se o login atual está vazio
        if (InterfaceView.idLoginAtual.equals("")) {
            TelaDeLoginView.appTelaDeLoginView = new TelaDeLoginView(); // Exibe a tela de login
            TelaDeLoginView.appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        } else {
            appTelaDeCadastroView = new TelaDeCadastroView(); // Exibe a tela de cadastro
            appTelaDeCadastroView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
