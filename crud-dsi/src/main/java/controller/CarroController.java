package controller;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.CarroDAO;
import model.Carro;

public class CarroController {
	
	private static CarroDAO carroDAO = new CarroDAO();

	public static void remove(Carro carro) {
		carroDAO.excluirCarro(carro);
	}

	public static void adicionar(Carro carro) {
		try {
			carroDAO.salvarCarro(carro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro criando carro");
		}		
	}
	
	public static ArrayList<Carro> listar(){
		return carroDAO.pesquisarCarro();
	}

	public static void alterarCarro(Carro carro) {
		carroDAO.alterarCarro(carro);
	}
}
