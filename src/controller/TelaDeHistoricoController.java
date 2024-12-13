package controller;

import view.*;
import model.*;

public class TelaDeHistoricoController extends TelaDeHistoricoView {

    // Método para preencher o histórico chamando o modelo para capturar os dados
    public static String[] preencherHistorico() {
        // Chama o método do modelo que captura o histórico e retorna os dados como um
        // array de Strings
        return TelaDeHistoricoModel.capturarHistorico();
    }
}