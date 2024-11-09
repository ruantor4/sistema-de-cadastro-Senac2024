package view;

import controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



public class TelaDeLoginView extends JFrame{

    public static JLabel labelLogin;
    public static JTextField textLogin;
    public static JLabel labelSenha;
    public static JPasswordField textSenha;
    public static JButton btnLogin;
    public static JLabel lblNotificacoes;

    public TelaDeLoginView(){
        

        super("Tela de Login");
        setLayout(new FlowLayout());

        labelLogin = new JLabel("LOGIN: ");
        add(labelLogin);

        textLogin = new JTextField(10);     // ATRIBUINDO VALOR A VARIAVEL "10" CARACTERES                   
        add(textLogin);                     // ADICIONANDO VALOR A VARIAVEL.

        labelSenha = new JLabel("SENHA: ");
        add(labelSenha);

        textSenha = new JPasswordField(10);
        add(textSenha);

        btnLogin = new JButton("LOGAR");
        add(btnLogin);

        lblNotificacoes = new JLabel("Notificações");
        add(lblNotificacoes);

        ButtonHandler buttonHandler = new ButtonHandler();
        btnLogin.addActionListener(buttonHandler);

        textSenha.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (String.valueOf(textSenha.getPassword()).trim().length() > 0) {
                        if (e.getKeyCode() == 10) {
                            System.out.println("Você teclou Enter");
                            TelaDeLoginController.logarController();
                        }
                    }
                }
            }
        );
    }

    public class ButtonHandler implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent event){
        TelaDeLoginController.logarController();

        }
    }

    public static String setHtmlFormat(String txt){
        return "<html><body>" + txt + "</body></html>";
    }

    public static void notificarUsuario(String strtexto){
        lblNotificacoes.setText(setHtmlFormat(strtexto));
    }

    public static TelaDeLoginView appTelaDeLoginView;
    public static void main(String[] args){                         // METODO PRINCIPAL PARA INICIAR O PROGRAMA
        appTelaDeLoginView = new TelaDeLoginView();

        appTelaDeLoginView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appTelaDeLoginView.setSize(150, 200);
        appTelaDeLoginView.setVisible(true);
    }
    
}

