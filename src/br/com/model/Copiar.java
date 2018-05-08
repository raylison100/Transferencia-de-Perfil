package br.com.model;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Copiar {
	
	private BufferedInputStream bis;
	private FileOutputStream fos;
	private String origem;
	private String destino;

	public String botaoSelecionarOri() {

		String diretorio = new String();
		JFileChooser fc = new JFileChooser();
		int res = 0;
		boolean dir = true;
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		res = fc.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {

			diretorio = fc.getSelectedFile().getAbsolutePath();

		} else {
			dir = false;
			JOptionPane.showMessageDialog(null, " Diretorio não selecionado");
		}
		if (dir == true) {
			origem = diretorio;

		}

		return diretorio;
	}

	public String botaoSelecionardesti() {
		String diretorio = new String();
		JFileChooser fc = new JFileChooser();
		int res = 0;
		boolean dir = true;
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		res = fc.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {

			diretorio = fc.getSelectedFile().getAbsolutePath();

		} else {
			dir = false;
			JOptionPane.showMessageDialog(null, " Diretorio não selecionado");
		}
		if (dir == true)
			destino = diretorio;

		return diretorio;
	}

	public void botaoCopiar(JTextArea textArea, String extensao) {

		ArrayList<String> falhasLog = new ArrayList<String>();
		String separaLinhas[] = textArea.getText().toString().split("\n");

		int ok = 0, falhas = 0;

		for (int cont = 0; cont < separaLinhas.length; cont++) {

			boolean transferido = false;

			try {
				File fOrigem = new File(origem.toString() + "\\"
						+ separaLinhas[cont].toString() + extensao.toString());
				File fDest = new File(destino.toString() + "\\"
						+ separaLinhas[cont].toString() + extensao.toString());

				bis = new BufferedInputStream(new FileInputStream(fOrigem));
				fos = new FileOutputStream(fDest);

				int count = 0;
				byte[] bytes = new byte[1024];
				while ((count = bis.read(bytes)) >= 0) {
					fos.write(bytes, 0, count);
					transferido = true;

				}

			} catch (FileNotFoundException e1) {
				System.out.println(e1.getMessage());
			} catch (IOException e1) {

				System.out.println(e1.getMessage());
			} finally {

				if (fos != null) {
					try {
						fos.close();
						bis.close();
					} catch (IOException e2) {/* Silent exception */
					}
				}
			}

			if (transferido != false) {
				ok++;
			} else {
				falhas++;
				falhasLog.add(separaLinhas[cont].toString());
			}

		}

		int confirm = JOptionPane.showConfirmDialog(null, "CONCLUIDO\nEXITO: "
				+ ok + "  Nao encontradas: " + falhas
				+ "\n\nDeseja ver o relatorio de erros?:", null,
				JOptionPane.YES_OPTION);

		String temp = new String();
		if (confirm == 0) {
			for (String s : falhasLog) {
				temp += s + "\n";
			}
			textArea.setText(temp);
		} else {
			textArea.setText("");
		}

		falhasLog.clear();
	}

}
