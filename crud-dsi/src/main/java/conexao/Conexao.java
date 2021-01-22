package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Conexao conexao = null;
	private static Connection dataSource = null;
	private String url = "jdbc:mysql://localhost:3306/ifsp";
	private String usuario = "root";
	private String senha = "";
	
	public Conexao() {
		try {			
			this.dataSource = DriverManager.getConnection(url, usuario, senha);
		}
		catch (SQLException e) {
			System.out.println("Erro ao comunicar com o banco de dados");
			e.printStackTrace();
		}
	}
	
	public Connection getConexao() {
		return dataSource;
	}
	
	public static Conexao getInstance() {
		if(conexao == null) {
			conexao =  new Conexao();
		}
		return conexao;
	}

}
