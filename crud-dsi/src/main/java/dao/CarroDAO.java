package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import conexao.Conexao;
import model.Carro;

public class CarroDAO {
	
	public Conexao con = null;
	ResultSet rs;
	ArrayList<Carro> lista = new ArrayList<Carro>();
	
	public int carroSize() {
		String sql = "select count(*) from carros";
		try {
			PreparedStatement pstm = con.getConexao().prepareStatement(sql);
			pstm.execute();
			return pstm.getResultSet().getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public void salvarCarro(Carro carro) {
		
		try {
			int tamanho = pesquisarCarro().size();
			this.con = Conexao.getInstance();
		
			String sql = "insert into carros (id, nome, ano) values (?, ?, ?)";
			PreparedStatement pstm = con.getConexao().prepareStatement(sql);
			pstm.setInt(1, ++tamanho);
			pstm.setString(2, carro.getModelo());
			pstm.setString(3, carro.getAno());
			pstm.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Registro salvo com sucesso !");
			
		} catch(SQLException e) {
			System.out.println("Erro inserindo carro: " + e);
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Carro> pesquisarCarro(){
		try {
			this.con = Conexao.getInstance();
			String sql = "select * from carros";
			PreparedStatement pstm = con.getConexao().prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Carro carro = new Carro();
				carro.setId(rs.getInt("id"));
				carro.setModelo(rs.getString("nome"));
				carro.setAno(rs.getString("ano"));
				
				lista.add(carro);
			}
			
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro na pesquisa:" + e);
		}
		return lista;
	}

	public void excluirCarro(Carro carro) {
		try {
			this.con = Conexao.getInstance();
			String sql = "delete from carros where id = ?";
			PreparedStatement pstm = con.getConexao().prepareStatement(sql);
			pstm.setInt(1, carro.getId());
			pstm.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Registro excluido  com sucesso!");
		}catch(SQLException erro) {
			JOptionPane.showMessageDialog(null, "Funcionario EXCLUIR" + erro);
		}
	}	
	
	public void alterarCarro(Carro carro) {
		
		try {
		
		this.con = Conexao.getInstance();
		String sql = "update carros set nome = ?, ano = ? where id = ? ";
		PreparedStatement pstm = con.getConexao().prepareStatement(sql);
		pstm.setString(1, carro.getModelo());
		pstm.setString(2, carro.getAno());
		pstm.setInt(3, carro.getId());
		pstm.executeUpdate();
		
		JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
		
	
		}catch(SQLException erro) {
			JOptionPane.showMessageDialog(null, "Funcionario ALTERAR" + erro);
	}
}
}
