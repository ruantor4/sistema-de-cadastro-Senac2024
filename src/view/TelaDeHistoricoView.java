package view;

import controller.*; // Importando as classes do pacote controller

import java.awt.*; // Importando classes para o gerenciamento de componentes gráficos
import java.awt.event.*; // Importando classes para manipulação de eventos

import javax.swing.*; // Importando classes para a criação da interface gráfica
import javax.swing.event.*; // Importando classes para eventos de listas

// Definindo a classe TelaDeHistoricoView que estende JFrame para criar uma tela
public class TelaDeHistoricoView extends JFrame {
    // Declaração dos componentes da interface gráfica
    public static JList<String> lstHistorico;
    public static JButton btnEnviarHistorico;

    // Declaração para o layout da interface e suas restrições
    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    // Construtor da classe que configura a tela
    public TelaDeHistoricoView() {
        super("Tela de Histórico"); // Definindo o título da janela

        // Inicializando o layout
        gbLayout = new GridBagLayout();
        setLayout(gbLayout); // Definindo o layout para o JFrame

        // Inicializando as restrições do GridBagLayout
        gbConstraints = new GridBagConstraints();

        // Inicializando e configurando o JList para exibir o histórico
        lstHistorico = new JList<String>(TelaDeHistoricoController.preencherHistorico());
        lstHistorico.setVisibleRowCount(5); // Definindo o número de linhas visíveis na lista
        lstHistorico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permitindo apenas uma seleção por vez
        InterfaceView.addComponent(lstHistorico, 0, 0, 1, 1, gbLayout, gbConstraints, this); // Adicionando o componente
                                                                                             // à tela

        // Inicializando o botão para enviar o histórico
        btnEnviarHistorico = new JButton("Enviar Histórico");
        btnEnviarHistorico.setEnabled(false); // Desabilitando o botão inicialmente
        InterfaceView.addComponent(btnEnviarHistorico, 1, 0, 1, 1, gbLayout, gbConstraints, this); // Adicionando o
                                                                                                   // botão à tela

        // Adicionando um listener para o evento de seleção na lista
        lstHistorico.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent event) {
                        btnEnviarHistorico.setEnabled(true); // Habilitando o botão quando um item for selecionado na
                                                             // lista
                    }
                });

        // Adicionando um listener para o evento de clique no botão
        btnEnviarHistorico.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Colocando o valor selecionado na lista no campo de pesquisa
                        TelaDePesquisaView.txtPesquisa.setText(lstHistorico.getSelectedValue());
                        // Exibindo a tela de pesquisa
                        TelaDePesquisaView.appTelaDePesquisaView.setVisible(true);
                        dispose(); // Fechando a tela de histórico
                    }
                });

        // Definindo o ícone da interface
        InterfaceView.definirIcone(this);
        setSize(200, 200); // Definindo o tamanho da janela
        setVisible(true); // Tornando a janela visível
    }

    // Instanciando a tela de histórico
    public static TelaDeHistoricoView appTelaDeHistoricoView;

    // Método principal para rodar a aplicação
    public static void main(String[] args) {
        // Condição para verificar se o idLoginAtual está vazio
        if (InterfaceView.idLoginAtual.equals("")) {
            TelaDeLoginView.appTelaDeLoginView = new TelaDeLoginView();
            TelaDeLoginView.appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE); // Configurando o comportamento
                                                                                        // de fechamento
        } else {
            appTelaDeHistoricoView = new TelaDeHistoricoView();
            appTelaDeHistoricoView.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Configurando o fechamento da tela de
                                                                               // histórico

            // Adicionando um listener para o evento de fechamento da janela
            appTelaDeHistoricoView.addWindowListener(
                    new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            // Quando a janela for fechada, exibe a tela de pesquisa novamente
                            TelaDePesquisaView.appTelaDePesquisaView.setVisible(true);
                            appTelaDeHistoricoView.dispose(); // Fecha a tela de histórico
                        }
                    });
        }
    }
}
