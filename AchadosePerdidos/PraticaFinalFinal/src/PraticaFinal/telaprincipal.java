package PraticaFinal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class telaprincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaprincipal frame = new telaprincipal();
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
	public telaprincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro cadastrar=new Cadastro();
				cadastrar.setVisible(true);
			}
		});
		btnNewButton.setBounds(159, 90, 120, 23);
		contentPane.add(btnNewButton);
		
		JButton btnalterar = new JButton("Alterar");
		btnalterar.setBackground(Color.LIGHT_GRAY);
		btnalterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alterar alterar=new Alterar();
				alterar.setVisible(true);
				dispose();
			}
			
		});
		btnalterar.setBounds(159, 135, 120, 23);
		contentPane.add(btnalterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excluir excluir=new Excluir();
				excluir.setVisible(true);
				dispose();
			}
		});
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setBounds(159, 188, 120, 23);
		contentPane.add(btnExcluir);
		
		JLabel lblNewLabel = new JLabel("Achados e Perdidos");
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 13));
		lblNewLabel.setBounds(159, 11, 135, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clique na sua op\u00E7\u00E3o:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(159, 53, 135, 14);
		contentPane.add(lblNewLabel_1);
	}

}