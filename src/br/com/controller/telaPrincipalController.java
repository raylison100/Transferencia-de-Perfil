package br.com.controller;


import java.io.File;
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
			
			
			
             //Lendo arquivos de um diretorio
			
			 File file_Desktop = new File(cp.getOrigem()+diretorios[0]);
			 File file_Documentos = new File(cp.getOrigem()+diretorios[1]);
			 File file_Download = new File(cp.getOrigem()+diretorios[2]);
			 File file_Notas = new File(cp.getOrigem()+diretorios[3]);
			 
			 File afile_desk[] = file_Desktop.listFiles();
			 File afile_doc[] = file_Documentos.listFiles();
			 File afile_down[] = file_Download.listFiles();
			 File afile_notas[] = file_Notas.listFiles();
			 
			 int totalArquivos = afile_desk.length + afile_doc.length + afile_down.length + afile_notas.length;

			 //Mapeamento de arquivos
			 for (int j = 0 ;j < afile_desk.length; j++) {
			  File arquivos = afile_desk[j];
			 System.out.println(arquivos);
			 }
			 
			 for (int j = 0 ;j < afile_doc.length; j++) {
				  File arquivos = afile_doc[j];
				 System.out.println(arquivos);
				 }
			 
			 for (int j = 0 ;j < afile_down.length; j++) {
				  File arquivos = afile_down[j];
				 System.out.println(arquivos);
				 }
			 
			 for (int j = 0 ;j < afile_notas.length; j++) {
				  File arquivos = afile_notas[j];
				 System.out.println(arquivos);
				 }
			

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



