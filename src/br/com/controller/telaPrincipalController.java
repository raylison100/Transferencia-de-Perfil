package br.com.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

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

	String diretorios[] = { "Desktop", "Documents", "Downloads", "AppData\\Roaming\\Microsoft\\Sticky Notes" };

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

			String log = null;
			

			// Copia de cada diretorio
			for (int cont = 0; cont < diretorios.length; cont++) {
				try {
					Process process = Runtime.getRuntime()
							.exec("xcopy " + cp.getOrigem() + diretorios[cont] + " " + cp.getDestino()+diretorios[cont]+"\\" + " /y /s");

					System.out.println("xcopy " + cp.getOrigem() + diretorios[cont] + " " + cp.getDestino() + " /y /s");				
					Scanner leitor = new Scanner(process.getInputStream());
					while (leitor.hasNextLine()) {
						log = leitor.nextLine();
						System.out.println(log);

					}

					leitor.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			JOptionPane.showMessageDialog(null, "FIM DA TRANFERENCIA\nARQUIVO(S) COPIADOS"
					+ "\n\nENTRAR EM CONTATO COM O SERVICE DESK PARA CONFIGURAÇÃO DE EMAIL\nNUMERO: 2965");

		}
	}

}



//Lendo arquivos de um diretorio

// File file = new File(cp.getOrigem());
// File afile[] = file.listFiles();
// int i = 0;

// //Mapeamento de arquivos
// for (int j = afile.length; i < j; i++) {
// File arquivos = afile[i];
// System.out.println(arquivos);
// }

