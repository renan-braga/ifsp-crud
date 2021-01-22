package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import conexao.Conexao;
import dao.CarroDAO;
import model.Carro;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class TelaPrincipal {

	private JFrame frame;
	private JTextField txtModelo;
	private JTextField txtAno;
	private DefaultListModel<Carro> defaultListModel;
	private JList<Carro> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		CarroDAO carroDao = new CarroDAO();
//		carroDao.salvarCarro(new Carro(1, "vectra", "1999"));
//		System.out.println(carroDao.pesquisarCarro().get(2));
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 614, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInserirCarro = new JLabel("Carro");
		lblInserirCarro.setBounds(12, 12, 139, 25);
		frame.getContentPane().add(lblInserirCarro);
		
		JLabel lblNome = new JLabel("Modelo");
		lblNome.setBounds(22, 49, 70, 15);
		frame.getContentPane().add(lblNome);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(34, 74, 70, 15);
		frame.getContentPane().add(lblAno);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(100, 37, 210, 25);
		frame.getContentPane().add(txtModelo);
		txtModelo.setColumns(10);
		
		txtAno = new JTextField();
		txtAno.setColumns(10);
		txtAno.setBounds(100, 69, 210, 25);
		frame.getContentPane().add(txtAno);
		defaultListModel = new DefaultListModel<Carro>();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 166, 473, 178);
		frame.getContentPane().add(scrollPane);
		
		list = new JList<Carro>(defaultListModel);
		scrollPane.setViewportView(list);

		defaultListModel.addAll(CarroController.listar());
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Carro carro = list.getSelectedValue();
				if(carro != null) {
					txtModelo.setText(carro.getModelo());
					txtAno.setText(carro.getAno());
				}
			}
		});
		
		JButton btnAlterar = new JButton("Alterar Carro");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Carro carro = list.getSelectedValue();
				CarroController.alterarCarro(carro);
				flush();
			}
		});
		
		btnAlterar.setBounds(337, 110, 180, 44);
		frame.getContentPane().add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar Carro");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Carro carro = list.getSelectedValue();
				defaultListModel.removeElement(carro);
				CarroController.remove(carro);
			}
		});
		btnDeletar.setBounds(337, 59, 180, 44);
		frame.getContentPane().add(btnDeletar);
		
		JButton btnCriar = new JButton("Criar Carro");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Carro carro = new Carro(0, txtModelo.getText(), txtAno.getText());
				CarroController.adicionar(carro);
				flush();
			}
		});
		btnCriar.setBounds(337, 2, 180, 44);
		frame.getContentPane().add(btnCriar);
	}

	protected void flush() {
		defaultListModel.removeAllElements();
		defaultListModel.addAll(CarroController.listar());
		
	}
}
