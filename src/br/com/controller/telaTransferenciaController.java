package br.com.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import br.com.model.Copiar;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public class telaTransferenciaController implements Initializable {

	@FXML
	private ProgressBar pb_transferi = new ProgressBar(0);

	@FXML
	private ProgressIndicator pi_transferi = new ProgressIndicator(0);

	@FXML
	private Label lb_title;
	
	@FXML
	private Button bt_concluido;

	private String diretorios[] = { "Desktop", "Documents", "Downloads", "AppData\\Roaming\\Microsoft\\Sticky Notes","AppData\\Local\\Google"};
	private Copiar cp;
	private Stage stage;
	private ArrayList<String> endereco = new ArrayList<String>();
	private int desktop;
	private int documentos;
	private int download;
	private int appdata;
	private int google;

	public void initialize(URL location, ResourceBundle resources) {
		
		bt_concluido.setDisable(true);
		bt_concluido.setVisible(false);

	}

	public void executa() {

		// Create new Task and Thread - Bind Progress Property to Task Progress
		@SuppressWarnings("rawtypes")
		Task task = taskCreator(Lerarquivo());
		pb_transferi.progressProperty().unbind();
		pb_transferi.progressProperty().bind(task.progressProperty());
		pi_transferi.progressProperty().unbind();
		pi_transferi.progressProperty().bind(task.progressProperty());
		new Thread(task).start();

	}
	
	
	public void concluido() {
		
		bt_concluido.getScene().getWindow().hide();
	}

	public void setStage(Stage tranferencia) {

		this.stage = tranferencia;
		stage.initStyle(StageStyle.UNDECORATED);

	}

	public void setCp(String origem, String supervisor) {

		this.cp = new Copiar(origem, supervisor);
		executa();

	}

	

	public int Lerarquivo() {

		int totalArquivos;
		int temp = 0;
		// Lendo arquivos de um diretorio

		File file_Desktop = new File(cp.getOrigem() + diretorios[0]);
		File file_Documentos = new File(cp.getOrigem() + diretorios[1]);
		File file_Download = new File(cp.getOrigem() + diretorios[2]);
		File file_Notas = new File(cp.getOrigem() + diretorios[3]);
		File file_Google = new File(cp.getOrigem() + diretorios[4]);

		File afile_desk[] = file_Desktop.listFiles();
		File afile_doc[] = file_Documentos.listFiles();
		File afile_down[] = file_Download.listFiles();
		File afile_notas[] = file_Notas.listFiles();
		File afile_google[] = file_Google.listFiles();
		
		

		totalArquivos = afile_desk.length + afile_doc.length + afile_down.length + afile_notas.length *+ afile_google.length;

		
		// Mapeamento de arquivos
		for (int j = 0; j < afile_desk.length; j++) {
			File arquivos = afile_desk[j];
			endereco.add("\\"+arquivos.getName());
			temp++;
//			System.out.println(arquivos);
		}

		desktop = temp;

		for (int j = 0; j < afile_doc.length; j++) {
			File arquivos = afile_doc[j];
			endereco.add("\\"+arquivos.getName());
			temp++;
//			System.out.println(arquivos);
		}

		documentos = temp;

		for (int j = 0; j < afile_down.length; j++) {
			File arquivos = afile_down[j];
			endereco.add("\\"+arquivos.getName());
			temp++;
//		System.out.println(arquivos);
		}

		download = temp;

		for (int j = 0; j < afile_notas.length; j++) {
			File arquivos = afile_notas[j];
			endereco.add("\\"+arquivos.getName());
			temp++;
//			System.out.println(arquivos);
		}

		appdata = temp;
		
		
		for (int j = 0; j < afile_google.length; j++) {
			File arquivos = afile_google[j];
			endereco.add("\\"+arquivos.getName());
			temp++;
//			System.out.println(arquivos);
		}

		google = temp;		
		

		return totalArquivos;
		
	}

	// Create a New Task
	@SuppressWarnings("rawtypes")
	public Task taskCreator(int totalArquivos) {
		return new Task() {

			protected Object call() throws Exception {

//				System.out.println(totalArquivos);
				for (int i = 0; i < totalArquivos; i++) {

					// Copia de cada diretorio

//					String log = null;

					if (i < desktop) {

						try {
							
												
							
							@SuppressWarnings("unused")
							Process process = Runtime.getRuntime().exec("xcopy " +"\"" + cp.getOrigem()+ diretorios[0] +"\\*"+"\"" + " "
									+ cp.getDestino() + diretorios[0] + "\\" + "/E /Y ");

//							System.out.println("xcopy " +"\""  + cp.getOrigem()+ diretorios[0] + endereco.get(i) +"\""  + " " + cp.getDestino() + diretorios[0] + " /y /s");
//							Scanner leitor = new Scanner(process.getInputStream());
//							while (leitor.hasNextLine()) {
//								log = leitor.nextLine();
//								System.out.println(log);
//
//							}
//							leitor.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}else if (i < documentos) {
						
						try {
							@SuppressWarnings("unused")
							Process process = Runtime.getRuntime().exec("xcopy " +"\"" + cp.getOrigem()+ diretorios[1] +"\\*"+"\"" + " "
									+"\""+ cp.getDestino() + diretorios[1] + "\\"+"\"" + "/E /Y ");

//							System.out.println("xcopy " +"\"" + cp.getOrigem()+ diretorios[1] +"\\*"+"\"" + " " + cp.getDestino() + diretorios[1] + " /E /Y ");
//							Scanner leitor = new Scanner(process.getInputStream());
//							while (leitor.hasNextLine()) {
//								log = leitor.nextLine();
//								System.out.println(log);
//
//							}
//							leitor.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}else if(i < download) {
						
						try {
							@SuppressWarnings("unused")
							Process process = Runtime.getRuntime().exec("xcopy " +"\"" + cp.getOrigem()+ diretorios[2] +"\\*"+"\"" + " "
									+"\""+ cp.getDestino() + diretorios[2] + "\\"+"\"" + "/E /Y ");

//							System.out.println("xcopy " +"\"" + cp.getOrigem()+ diretorios[2] + endereco.get(i) +"\"" + " " + cp.getDestino() + diretorios[2] + " /y /s");
//							Scanner leitor = new Scanner(process.getInputStream());
//							while (leitor.hasNextLine()) {
//								log = leitor.nextLine();
//								System.out.println(log);
//
//							}
//							leitor.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}else if (i < appdata) {
						
						try {
							@SuppressWarnings("unused")
							Process process = Runtime.getRuntime().exec("xcopy " +"\"" + cp.getOrigem()+ diretorios[3] +"\\*"+"\"" + " "
									+"\""+ cp.getDestino() + diretorios[3]+"\\\""  + "/S /E /Y ");

//							System.out.println("xcopy " +"\"" + cp.getOrigem()+ diretorios[3] +"\\*"+"\"" + " "
//									+"\""+ cp.getDestino() + diretorios[3]+"\\\""  + "/S /E /Y ");
//							Scanner leitor = new Scanner(process.getInputStream());
//							while (leitor.hasNextLine()) {
//								log = leitor.nextLine();
//								System.out.println(log);
//
//							}
//							leitor.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}else if (i < google) {
						
						try {
							@SuppressWarnings("unused")
							Process process = Runtime.getRuntime().exec("xcopy " +"\"" + cp.getOrigem()+ diretorios[4] +"\\*"+"\"" + " "
									+"\""+ cp.getDestino() + diretorios[4] + "\\"+"\"" + "/E /Y ");

//							System.out.println("xcopy " +"\"" + cp.getOrigem()+ diretorios[4] + endereco.get(i) +"\"" + " " + cp.getDestino() + diretorios[4] + " /y /s");
//							Scanner leitor = new Scanner(process.getInputStream());
//							while (leitor.hasNextLine()) {
//								log = leitor.nextLine();
//								System.out.println(log);
//
//							}
//							leitor.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}		
					
					updateProgress(i + 1, totalArquivos);
				}

				JOptionPane.showMessageDialog(null,
						"FIM DA TRANFERENCIA DE ARQUIVO(S) COPIADOS\n\nENTRAR EM CONTATO COM O SERVICE DESK PARA CONFIGURAÇÃO DE EMAIL\nNUMERO: 2965");
				bt_concluido.setDisable(false);
				bt_concluido.setVisible(true);
				return true;
				
			}
		};
	}
	
	
}
