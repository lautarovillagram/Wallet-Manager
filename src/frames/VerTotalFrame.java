package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import walletManager.Cuenta;
import walletManager.Wallet;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VerTotalFrame extends JFrame {

	private JPanel contentPane;
	JTextArea textArea = new JTextArea();
	Wallet wallet;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VerTotalFrame(Wallet wallet) {
		setTitle("Total en pesos y dolares");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		this.wallet = wallet;
		contentPane.setLayout(null);

		JButton btnIrAtras = new JButton("Ir atras");
		btnIrAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mainframe main;
				try {
					main = new mainframe();
					main.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnIrAtras.setBounds(10, 13, 86, 20);
		contentPane.add(btnIrAtras);

		JLabel lblTotalEnPesos = new JLabel("Total en pesos");
		lblTotalEnPesos.setBounds(31, 44, 95, 20);
		contentPane.add(lblTotalEnPesos);

		JLabel lblTotalEnDolares = new JLabel("Total en dolares");
		lblTotalEnDolares.setBounds(203, 44, 95, 20);
		contentPane.add(lblTotalEnDolares);

		JLabel lblResultadoTotalPesos = new JLabel("");
		lblResultadoTotalPesos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblResultadoTotalPesos.setBounds(41, 75, 95, 14);
		contentPane.add(lblResultadoTotalPesos);

		JLabel lblResultadoTotalDolares = new JLabel("");
		lblResultadoTotalDolares.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblResultadoTotalDolares.setBounds(213, 75, 99, 14);
		contentPane.add(lblResultadoTotalDolares);

		JScrollPane scrollPane = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 100, 302, 185);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(textArea);

		textArea.setText("");
		for (Cuenta cuenta : wallet.getCuentas()) {

			textArea.append(cuenta.toString() + " | $ " + String.format("%.2f", cuenta.getMonto()) + "\n");
		}
		textArea.append("Dolares: $" + wallet.getDolares() + " | en pesos: $"
				+ String.format("%.2f", wallet.dolaresAPesos()) + "\n");
		lblResultadoTotalPesos.setText(String.format("%,.2f", (wallet.dolaresAPesos() + wallet.totalEnCuentas())));
		lblResultadoTotalDolares.setText(String.format("%,.2f", ((wallet.dolaresAPesos() + wallet.totalEnCuentas()) / wallet.getUltimoValorDolar())));

	}

	public void verTotal() {
		for (Cuenta cuenta : wallet.getCuentas()) {

			textArea.append(cuenta.toString() + "  $" + String.format("%.2f", cuenta.getMonto()) + "\n");
		}
		textArea.append("Dolares: $" + wallet.getDolares() + " | en pesos: $"
				+ String.format("%.2f", wallet.dolaresAPesos()) + "\n");

		textArea.append("________________________" + "\n");

		textArea.append("Total en dolares: $ " + String.format("%,.2f",
				(wallet.dolaresAPesos() + +wallet.totalEnCuentas()) / wallet.getUltimoValorDolar()));

	}

}
