package view;

import controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Classe que define a interface gráfica para remover registros
public class TelaDeRemoverView extends JFrame {

    // Declaração dos componentes da interface gráfica
    public static JLabel lblFoto;
    public static JLabel lblId;
    public static JComboBox<String> cbxId;
    public static JLabel lblNome;
    public static JTextField txtNome;
    public static JLabel lblEmail;
    public static JTextField txtEmail;
    public static JButton btnRemover;
    public static JButton btnCancelar;
    public static JLabel lblNotificacoes;

    // Layout de GridBag
    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    // Construtor que configura a tela de remoção
    public TelaDeRemoverView() {
        super("Tela de Remover"); // Definindo o título da janela

        // Inicialização do layout
        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbConstraints = new GridBagConstraints();

        // Configuração da foto (imagem padrão)
        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "/imagem-padrao.jpg").getImage()
                .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        InterfaceView.addComponent(lblFoto, 0, 0, 4, 2, gbLayout, gbConstraints, this);

        // Configuração do campo para ID
        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblId, 2, 0, 1, 1, gbLayout, gbConstraints, this);

        cbxId = new JComboBox<String>();
        TelaDeRemoverController.popularCbxIdController(); // Preenchendo o ComboBox com IDs
        InterfaceView.addComponent(cbxId, 2, 1, 1, 1, gbLayout, gbConstraints, this);

        // Configuração do campo para Nome
        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblNome, 3, 0, 1, 1, gbLayout, gbConstraints, this);

        txtNome = new JTextField(10);
        txtNome.setEditable(false); // Campo de nome somente leitura
        InterfaceView.addComponent(txtNome, 3, 1, 1, 1, gbLayout, gbConstraints, this);

        // Configuração do campo para Email
        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblEmail, 4, 0, 1, 1, gbLayout, gbConstraints, this);

        txtEmail = new JTextField(10);
        txtEmail.setEditable(false); // Campo de email somente leitura
        InterfaceView.addComponent(txtEmail, 4, 1, 1, 1, gbLayout, gbConstraints, this);

        TelaDeRemoverController.atualizarCamposController(); // Atualizando os campos com dados de ID selecionado

        // Botão para remover
        btnRemover = new JButton("Remover");
        InterfaceView.addComponent(btnRemover, 5, 0, 1, 1, gbLayout, gbConstraints, this);

        // Botão para cancelar
        btnCancelar = new JButton("Cancelar");
        InterfaceView.addComponent(btnCancelar, 5, 1, 1, 1, gbLayout, gbConstraints, this);

        // Label para notificações
        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        InterfaceView.addComponent(lblNotificacoes, 6, 0, 2, 1, gbLayout, gbConstraints, this);

        // Ação para o ComboBox de ID
        cbxId.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    TelaDeRemoverController.atualizarCamposController(); // Atualizando os campos ao selecionar um ID
                }
                // Desabilitando o botão de remover se o ID selecionado for o mesmo do login
                // atual
                if (event.getItem().equals(InterfaceView.idLoginAtual)) {
                    btnRemover.setEnabled(false);
                } else {
                    btnRemover.setEnabled(true);
                }
            }
        });

        // Ação para o botão de remover
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Confirmando a remoção do ID selecionado
                if (JOptionPane.showConfirmDialog(null, "Tem certeza de que você deseja remover o id: "
                        + String.valueOf(cbxId.getSelectedItem()) + "?") == 0) {
                    TelaDeRemoverController.removerController(); // Chamando o controller para realizar a remoção
                    try {
                        // Atualizando o ComboBox após remoção
                        cbxId.setSelectedIndex(cbxId.getSelectedIndex() + 1);
                        cbxId.removeItemAt(cbxId.getSelectedIndex() - 1);
                    } catch (Exception e) {
                        // Caso ocorra algum erro, ajustando a seleção do ComboBox
                        cbxId.setSelectedIndex(cbxId.getSelectedIndex() - 1);
                        cbxId.removeItemAt(cbxId.getSelectedIndex() + 1);
                    }
                    TelaDeRemoverController.atualizarCamposController(); // Atualizando os campos após remoção
                }
            }
        });

        // Ação para o botão de cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                TelaDeMenuView.appTelaDeMenuView.setVisible(true); // Mostrando a tela de menu
                dispose(); // Fechando a tela de remoção
            }
        });

        // Definindo o ícone da interface
        InterfaceView.definirIcone(this);
        setSize(300, 300); // Definindo o tamanho da janela
        setVisible(true); // Tornando a janela visível
    }

    // Instância da tela de remoção
    public static TelaDeRemoverView appTelaDeRemoverView;

    // Método principal para iniciar a tela de remoção
    public static void main(String[] args) {
        // Se o ID de login atual estiver vazio, exibe a tela de login
        if (InterfaceView.idLoginAtual.equals("")) {
            TelaDeLoginView.appTelaDeLoginView = new TelaDeLoginView();
            TelaDeLoginView.appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        } else {
            // Caso contrário, exibe a tela de remoção
            appTelaDeRemoverView = new TelaDeRemoverView();
            appTelaDeRemoverView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
