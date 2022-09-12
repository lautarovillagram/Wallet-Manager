package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cargarGuardar.GuardarWallet;
import walletManager.Cuenta;
import walletManager.TransferenciaEntreCuentas;
import walletManager.Wallet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

public class TransferenciasFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textMonto;
	Wallet wallet;
	Wallet walletBackup;
	JComboBox comboCuentaOrigen = new JComboBox();
	JComboBox comboCuentaDestino = new JComboBox();
	GuardarWallet guardar = new GuardarWallet();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public void llenarCombos() {
		for (Cuenta cuenta : wallet.getCuentas()) {
			comboCuentaOrigen.addItem(cuenta);
			comboCuentaDestino.addItem(cuenta);
		}
	}

	public TransferenciasFrame(Wallet billetera) {
		setTitle("Wallet Manager - Transferencias entre cuentas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 293, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		this.wallet = billetera;
		this.walletBackup = billetera;
		llenarCombos();

		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(10, 74, 66, 14);
		contentPane.add(lblMonto);

		textMonto = new JTextField();
		textMonto.setBounds(68, 72, 86, 20);
		contentPane.add(textMonto);
		textMonto.setColumns(10);

		comboCuentaOrigen.setBounds(10, 42, 103, 21);
		contentPane.add(comboCuentaOrigen);

		comboCuentaDestino.setBounds(164, 42, 103, 21);
		contentPane.add(comboCuentaDestino);

		JLabel lblA = new JLabel("a");
		lblA.setBounds(132, 45, 46, 14);
		contentPane.add(lblA);

		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cuenta cuentaOrigen = (Cuenta) comboCuentaOrigen.getSelectedItem();
				Cuenta cuentaDestino = (Cuenta) comboCuentaDestino.getSelectedItem();
				cuentaOrigen.setMonto(cuentaOrigen.getMonto() - Float.parseFloat(textMonto.getText()));
				cuentaDestino.setMonto(cuentaDestino.getMonto() + Float.parseFloat(textMonto.getText()));
				wallet.getTransferencias().add(0, new TransferenciaEntreCuentas(cuentaOrigen, cuentaDestino,
						Float.parseFloat(textMonto.getText())));
				JOptionPane.showMessageDialog(null, "transferencia realizada");
				textMonto.setText("");
			}
		});
		btnTransferir.setBounds(174, 71, 91, 21);
		contentPane.add(btnTransferir);

		JScrollPane scrollPane = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 161, 258, 295);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton btnTransferencias30Dias = new JButton("transferencias ultimos 30 dias");
		btnTransferencias30Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnTransferencias30Dias) {
					textArea.setText("");
					List<TransferenciaEntreCuentas> transferencias30Dias = wallet.getTransferenciasUltimos30Dias();
					for (TransferenciaEntreCuentas transferencia : transferencias30Dias) {
						textArea.append("$ " + transferencia.getMontoTransferencia() + " | de "
								+ transferencia.getCuentaOrigen() + " a " + transferencia.getCuentaDestino()
								+ " | fecha: " + transferencia.getFecha().getDayOfMonth() + "/"
								+ transferencia.getFecha().getMonthValue() + "\n");
					}
				}
			}
		});
		btnTransferencias30Dias.setBounds(10, 106, 252, 20);
		contentPane.add(btnTransferencias30Dias);

		JButton btnTransferenciasHistoricas = new JButton("transferencias historicas");
		btnTransferenciasHistoricas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnTransferencias30Dias) {
					textArea.setText("");
					for (TransferenciaEntreCuentas transferencia : wallet.getTransferencias()) {
						textArea.append("$ " + transferencia.getMontoTransferencia() + " | de "
								+ transferencia.getCuentaOrigen() + " a " + transferencia.getCuentaDestino()
								+ " | fecha: " + transferencia.getFecha().getDayOfMonth() + "/"
								+ transferencia.getFecha().getMonthValue() + "\n");
					}
				}
			}
		});
		btnTransferenciasHistoricas.setBounds(10, 130, 252, 20);
		contentPane.add(btnTransferenciasHistoricas);

		JButton btnIrAtras = new JButton("Ir atras");
		btnIrAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnIrAtras) {
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
			}
		});
		btnIrAtras.setBounds(10, 8, 86, 20);
		contentPane.add(btnIrAtras);

		JButton btnRevertir = new JButton("Revertir");
		btnRevertir.setBounds(99, 8, 79, 20);
		contentPane.add(btnRevertir);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnGuardar) {
					guardar.wallet = wallet;
					try {
						guardar.guardar();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnGuardar.setBounds(181, 7, 86, 24);
		contentPane.add(btnGuardar);
	}
}
