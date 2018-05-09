package br.com.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
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
	private TextField tx_destino;

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

		} else if (tx_destino.getText().equals(null) || tx_destino.getText().equals("")) {
			
			JOptionPane.showMessageDialog(null, " COMPUTADOR DE DESTINO NAO SELECIONADO");

		} else if (tx_loginRede.getText().equals(null) || tx_loginRede.getText().equals("")) {
			
			JOptionPane.showMessageDialog(null, " PERFIL DE REDE NÂO SELECIONADO");

		} else {
			
						
			Copiar cp = new Copiar(tx_origem.getText(), tx_destino.getText(), tx_loginRede.getText());

			InputStream in;
			OutputStream out;
			try {
				in = new FileInputStream(cp.getOrigem());
				out = new FileOutputStream(cp.getDestino());
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				JOptionPane.showMessageDialog(null, " FIM DA TRANFERENCIA");
				in.close();
				out.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	// int confirm = JOptionPane.showConfirmDialog(null, "CONCLUIDO\nEXITO: "
	// + ok + " Nao encontradas: " + falhas
	// + "\n\nDeseja ver o relatorio de erros?:", null,
	// JOptionPane.YES_OPTION);
	//
	// String temp = new String();
	// if (confirm == 0) {
	// for (String s : falhasLog) {
	// temp += s + "\n";
	// }
	// textArea.setText(temp);
	// } else {
	// textArea.setText("");
	// }
	//
	// falhasLog.clear();
	//
	// JOptionPane.showMessageDialog(null, " Diretorio não selecionado");

}
