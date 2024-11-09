package controller;

import view.*;
import model.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class TelaDeLoginController extends TelaDeLoginView {
    public static void logarController(){
        ArrayList<String> resultados = new ArrayList<String>(TelaDeLoginModel.logarModel(textLogin.getText(), String.valueOf(textSenha.getPassword())));

    }
    
}