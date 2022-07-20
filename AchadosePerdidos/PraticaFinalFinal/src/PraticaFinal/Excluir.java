package PraticaFinal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Excluir extends JFrame {

	private JPanel contentPane;
	private JTextField textIDexcluido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Excluir frame = new Excluir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Excluir() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Achados e Perdidos");
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 13));
		lblNewLabel.setBounds(165, 11, 120, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNome = new JLabel("Digite o ID a ser excluido");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNome.setBounds(46, 72, 158, 14);
		contentPane.add(lblNome);
		
		textIDexcluido = new JTextField();
		textIDexcluido.setBounds(214, 69, 167, 20);
		contentPane.add(textIDexcluido);
		textIDexcluido.setColumns(10);
		
		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				achadoseperdidos app = new achadoseperdidos();
				try {
					app.connect();
				
					app.deletar(  Integer.parseInt( textIDexcluido.getText()));
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				JOptionPane.showMessageDialog(null, "Deletado!");
			 }
			 
			
			
			
			
		});
		btnexcluir.setBackground(Color.RED);
		btnexcluir.setBounds(150, 212, 135, 23);
		contentPane.add(btnexcluir);
	}
}