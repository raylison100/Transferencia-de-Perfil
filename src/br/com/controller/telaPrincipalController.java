package br.com.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;


import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class telaPrincipalController implements Initializable {

	@FXML
	private Button bt_fechar;

	@FXML
	private TextField tx_origem;

	@FXML
	private TextField tx_loginRede;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void fechar() {
		bt_fechar.getScene().getWindow().hide();
	}

	public void btCopiar() {

		try {
			
//			String log;
//			Process process = null;
//			try {
//				process = Runtime.getRuntime().exec("ping " + tx_origem.getText());
//				System.out.println("ping " + tx_origem.getText());
//				Scanner leitor = new Scanner(process.getInputStream());
//				while (leitor.hasNextLine()) {
//					log = leitor.nextLine();
//					System.out.println(log);
//
//				}
//				leitor.close();
//				
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
			
			
			

			if (tx_origem.getText().equals(null) || tx_origem.getText().equals("")) {

				JOptionPane.showMessageDialog(null, " COMPUTADOR DE ORIGEM NAO SELECIONADO");
				

			} else if (tx_loginRede.getText().equals(null) || tx_loginRede.getText().equals("")) {

				JOptionPane.showMessageDialog(null, " PERFIL DE REDE NÂO SELECIONADO");

			} else {

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(telaTransferenciaController.class.getResource("/br/com/view/fxml_transferencia.fxml"));
				AnchorPane page;
				page = (AnchorPane) loader.load();
				Stage tranferencia = new Stage();
				Scene scene = new Scene(page);
				tranferencia.setScene(scene);
				tranferencia.setResizable(false);
				telaTransferenciaController controller = loader.getController();
				controller.setCp(tx_origem.getText(), tx_loginRede.getText());
				controller.setStage(tranferencia);
				tranferencia.showAndWait();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
