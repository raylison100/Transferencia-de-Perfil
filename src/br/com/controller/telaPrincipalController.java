package br.com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;

import br.com.model.Copiar;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

		if (tx_origem.getText().equals(null) || tx_origem.getText().equals("")) {

			JOptionPane.showMessageDialog(null, " COMPUTADOR DE ORIGEM NAO SELECIONADO");

		} else if (tx_loginRede.getText().equals(null) || tx_loginRede.getText().equals("")) {

			JOptionPane.showMessageDialog(null, " PERFIL DE REDE NÂO SELECIONADO");

		} else {

			Copiar cp = new Copiar(tx_origem.getText(), tx_loginRede.getText());

			File file = new File(cp.getOrigem());
			File afile[] = file.listFiles();
			String log = null;
			int i = 0;
			
			// for (int j = afile.length; i < j; i++) {
			// File arquivos = afile[i];
			// }

			try {
				Process process = Runtime.getRuntime()
						.exec("xcopy " + cp.getOrigem() + " " + cp.getDestino() + " /y /s");
				
				String s = "xcopy " + cp.getOrigem() + " " + cp.getDestino() + " /y /s";
				System.out.println(s);
				Scanner leitor = new Scanner(process.getInputStream());
				while (leitor.hasNextLine()) {
					log = leitor.nextLine();
					System.out.println(log);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			

			JOptionPane.showMessageDialog(null, " FIM DA TRANFERENCIA\n" + log
					+ "\n\nENTRAR EM CONTATO COM O SERVICE DESK PARA CONFIGURAÇÃO DE EMAIL\nNUMERO: 2965");

		}
	}

	

}
