package view;

import java.util.ArrayList;

import dao.CarroDAO;
import model.Carro;

public class CarroController {
	
	private static CarroDAO carroDAO = new CarroDAO();

	public static void remove(Carro carro) {
		carroDAO.excluirCarro(carro);
	}

	public static void adicionar(Carro carro) {
		carroDAO.salvarCarro(carro);		
	}
	
	public static ArrayList<Carro> listar(){
		return carroDAO.pesquisarCarro();
	}

	public static void alterarCarro(Carro carro) {
		carroDAO.alterarCarro(carro);
	}
}
