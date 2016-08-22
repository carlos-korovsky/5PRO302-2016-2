/*
 * Copyright (C) 2016 UDESC - CEPLAN
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.udesc.ceplan.prog3.exemplo01.cliente;

import br.udesc.ceplan.prog3.exemplo01.cliente.persistencia.Persistencia;
import br.udesc.ceplan.prog3.exemplo01.cliente.persistencia.PersistenciaEmArquivo;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class ClienteGUIController implements Initializable, OnNewMessageListener<String> {

    @FXML
    private TextField txMensagem;
    @FXML
    private Button btEnviar;
    @FXML
    private Button btConectar;
    @FXML
    private ListView<String> lstMensagensServidor;
    
    private final Logger logger;
    private final ObservableList<String> mensagens;
    private Socket socket;
    private PrintWriter socketOut;
    private Thread monitorConexao;
    
    private final Persistencia persistencia;

    public ClienteGUIController() {
        this.mensagens = FXCollections.observableArrayList();
        this.logger = LoggerFactory.getLogger(getClass());
        this.persistencia = new PersistenciaEmArquivo();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstMensagensServidor.setItems(mensagens);
    }    

    @FXML
    private void onBtEnviarClick(ActionEvent event) {
        String userInput = txMensagem.getText();
        if (!userInput.isEmpty()) {
            logger.debug("Enviando mensagem: {}", userInput);
            mensagens.add(" > " + userInput);
            this.persistencia.salva(" Enviado: " + userInput);
            socketOut.println(userInput);
        }
        txMensagem.setText("");
    }

    @FXML
    private void onBtConectarClick(ActionEvent event) {
        if (this.socket == null) {
            try {
                this.socket = new Socket("127.0.0.1", 3377);
                socketOut = new PrintWriter(socket.getOutputStream(), true);
                MonitorConexao monitorConexaoRunnable = new MonitorConexao(this.socket, this);
                this.monitorConexao = new Thread(monitorConexaoRunnable);
                this.monitorConexao.start();
                btConectar.setText("Desconectar");
                btEnviar.setDisable(false);
                txMensagem.setDisable(false);
                String nomeArquivo = this.socket.getLocalAddress().toString() + " - "+ this.socket.getLocalPort() + ".log";
                this.logger.debug("Nome do arquivo de log: " + nomeArquivo);
                persistencia.abrir(new File(nomeArquivo));
                
            } catch (IOException ex) {
                logger.error(ex.getLocalizedMessage());
                this.socket = null;
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Erro de conexão");
                alert.setContentText(ex.getLocalizedMessage());
                alert.showAndWait();
            }
        } else {
            try {
                logger.debug("Desconectado socket do servidor...");
                this.socket.close();
                this.monitorConexao.join(1000);
            } catch (IOException | InterruptedException ex) {
                logger.error(ex.getLocalizedMessage());
            } finally {
                this.socket = null;
                this.persistencia.fechar();
                btConectar.setText("Conectar");
                btEnviar.setDisable(true);
                txMensagem.setDisable(true);
            }
        }
    }

    @Override
    public void OnNewMessage(String message) {
        mensagens.add(" < " + message);
        this.persistencia.salva(" Recebido: " + message);
    }
    
}
