package PraticaFinal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JRadioButton;

public class Cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField textlocalr;
	private JTextField textdatar;
	private JTextField textobsr;
	private JTextField textitemr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Cadastro() {
		setTitle("PraticaFinal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Achados e Perdidos");
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 15));
		lblNewLabel.setBounds(153, 11, 132, 23);
		contentPane.add(lblNewLabel);
		
		
		JLabel lbllocal = new JLabel("Digite o local:");
		lbllocal.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbllocal.setBounds(38, 85, 97, 14);
		contentPane.add(lbllocal);
		
		textlocalr = new JTextField();
		textlocalr.setColumns(10);
		textlocalr.setBounds(184, 83, 199, 20);
		contentPane.add(textlocalr);
		
		JLabel lbldata = new JLabel("Digite a data de hoje:");
		lbldata.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbldata.setBounds(38, 119, 117, 14);
		contentPane.add(lbldata);
		
		textdatar = new JTextField();
		textdatar.setColumns(10);
		textdatar.setBounds(184, 114, 199, 20);
		contentPane.add(textdatar);
		
		JLabel lblobs = new JLabel("Observa\u00E7\u00E3o:");
		lblobs.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblobs.setBounds(38, 153, 117, 14);
		contentPane.add(lblobs);
		
		textobsr = new JTextField();
		textobsr.setBounds(184, 145, 199, 38);
		contentPane.add(textobsr);
		textobsr.setColumns(10);
		
		JLabel lblop = new JLabel("Marque sua op\u00E7\u00E3o:");
		lblop.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblop.setBounds(38, 190, 117, 14);
		contentPane.add(lblop);
		
		JLabel lblitem = new JLabel("Nome do produto:");
		lblitem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblitem.setBounds(38, 48, 117, 26);
		contentPane.add(lblitem);
		
		textitemr = new JTextField();
		textitemr.setColumns(10);
		textitemr.setBounds(184, 52, 199, 20);
		contentPane.add(textitemr);
		
		JRadioButton rdbtnperdido = new JRadioButton("Perdido");
		rdbtnperdido.setBounds(184, 190, 76, 23);
		contentPane.add(rdbtnperdido);
		
		JRadioButton rdbtnEncontrado = new JRadioButton("Encontrado");
		rdbtnEncontrado.setBounds(262, 190, 109, 23);
		contentPane.add(rdbtnEncontrado);
		
		JButton btncadastrar = new JButton("Cadastrar");
		btncadastrar.setForeground(Color.BLACK);
		btncadastrar.setBackground(Color.CYAN);
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			achadoseperdidos app = new achadoseperdidos();
			try {
				app.connect();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String tipo;
			if (rdbtnperdido.isSelected()== true) {
				tipo = "Perdido";
			}
			else
				tipo = "Achado";
			try {
				app.insertRecord(textlocalr.getText(), textdatar.getText(),textitemr.getText(), textobsr.getText(),tipo);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

			}
		});
		btncadastrar.setBounds(130, 231, 208, 23);
		contentPane.add(btncadastrar);
		

	}
}