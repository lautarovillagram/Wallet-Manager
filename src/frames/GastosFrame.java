package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import walletManager.Cuenta;
import walletManager.Gasto;
import cargarGuardar.GuardarWallet;
import walletManager.Wallet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.awt.event.ActionEvent;

public class GastosFrame extends JFrame implements Serializable {

	private JPanel contentPane;
	private JTextField textGasto;
	GuardarWallet guardar = new GuardarWallet();
	Wallet wallet;
	Wallet walletBackup;
	private JTextField textConcepto;
	JComboBox comboTarjetaAVer = new JComboBox();
	JComboBox comboTarjetaAUtilizar = new JComboBox();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public void cargarCombos() {
		for (Cuenta cuenta : wallet.getCuentas()) {
			comboTarjetaAVer.addItem(cuenta);
			comboTarjetaAUtilizar.addItem(cuenta);

		}
	}

	public GastosFrame(Wallet billetera) {
		setTitle("Wallet Manager - Gastos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		this.wallet = billetera;
		this.walletBackup = billetera;
		cargarCombos();

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
		btnIrAtras.setBounds(26, 11, 89, 23);
		contentPane.add(btnIrAtras);

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
		btnGuardar.setBounds(224, 11, 89, 23);
		contentPane.add(btnGuardar);

		JButton btnRevertir = new JButton("Revertir");
		btnRevertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnRevertir) {
					wallet = walletBackup;
				}
			}
		});
		btnRevertir.setBounds(125, 11, 89, 23);
		contentPane.add(btnRevertir);

		JLabel lblAñadirGasto = new JLabel("a\u00F1adir gasto $");
		lblAñadirGasto.setBounds(10, 52, 102, 14);
		contentPane.add(lblAñadirGasto);

		textGasto = new JTextField();
		textGasto.setColumns(10);
		textGasto.setBounds(122, 49, 86, 20);
		contentPane.add(textGasto);

		JScrollPane scrollPane = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 208, 322, 292);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		comboTarjetaAUtilizar.setBounds(218, 49, 89, 21);
		contentPane.add(comboTarjetaAUtilizar);

		JButton btnAñadir = new JButton("A\u00F1adir");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAñadir) {
					Cuenta cuentaSeleccionada = (Cuenta) comboTarjetaAUtilizar.getSelectedItem();
					wallet.agregarGasto(textConcepto.getText(), Float.parseFloat(textGasto.getText()),
							cuentaSeleccionada);
					cuentaSeleccionada.setMonto(cuentaSeleccionada.getMonto() - Float.parseFloat(textGasto.getText()));
					JOptionPane.showMessageDialog(null, "Gasto añadido");
					textConcepto.setText("");
					textGasto.setText("");
				}

			}
		});
		btnAñadir.setBounds(211, 81, 89, 23);
		contentPane.add(btnAñadir);

		JLabel lblGastosUltimos = new JLabel("Gastos ultimos 30 dias");
		lblGastosUltimos.setBounds(52, 115, 146, 14);
		contentPane.add(lblGastosUltimos);

		comboTarjetaAVer.setBounds(10, 140, 103, 23);
		contentPane.add(comboTarjetaAVer);

		JButton btnVer30Dias = new JButton("Ver");
		btnVer30Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVer30Dias) {
					textArea.setText("");
					Cuenta cuentaSeleccionada = (Cuenta) comboTarjetaAVer.getSelectedItem();
					List<Gasto> gastos = wallet.getGastos().stream()
							.filter(g -> g.getFecha().isAfter(LocalDateTime.now().minusDays(30))
									&& g.getMetodoDePago() == cuentaSeleccionada)
							.toList();
					for (Gasto gasto : gastos) {
						textArea.append("$ " + gasto.getMonto() + " | " + gasto.getDescripcion() + " | fecha: "
								+ gasto.getDiaYMes() + "\n");

					}

				}
			}
		});
		btnVer30Dias.setBounds(112, 140, 89, 23);
		contentPane.add(btnVer30Dias);

		JButton btnVerTodas30Dias = new JButton("todas las cuentas");
		btnVerTodas30Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerTodas30Dias) {
					textArea.setText("");
					List<Gasto> gastos = wallet.getGastos().stream()
							.filter(g -> g.getFecha().isAfter(LocalDateTime.now().minusDays(30))).toList();
					for (Gasto gasto : gastos) {
						textArea.append("$ " + gasto.getMonto() + " | " + gasto.getMetodoDePago().toString() + " | "
								+ gasto.getDescripcion() + " | fecha: " + gasto.getDiaYMes() + "\n");

					}

				}
			}
		});
		btnVerTodas30Dias.setBounds(10, 174, 167, 23);
		contentPane.add(btnVerTodas30Dias);

		JButton btnVerHistorico = new JButton("Ver historico");
		btnVerHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerHistorico) {
					textArea.setText("");
					Cuenta cuentaSeleccionada = (Cuenta) comboTarjetaAVer.getSelectedItem();
					List<Gasto> gastos = wallet.getGastos().stream()
							.filter(g -> g.getMetodoDePago() == cuentaSeleccionada).toList();
					for (Gasto gasto : gastos) {
						textArea.append("$ " + gasto.getMonto() + " | " + gasto.getDescripcion() + " | fecha: "
								+ gasto.getDiaYMes() + "\n");
					}
				}
			}
		});
		btnVerHistorico.setBounds(211, 140, 122, 23);
		contentPane.add(btnVerHistorico);

		JButton btnVerTodasHistorico = new JButton("todas historico");
		btnVerTodasHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerTodasHistorico) {
					textArea.setText("");
					List<Gasto> gastos = wallet.getGastos();
					for (Gasto gasto : gastos) {
						textArea.append("$ " + gasto.getMonto() + " | " + gasto.getMetodoDePago().toString() + " | "
								+ gasto.getDescripcion() + " | fecha: " + gasto.getDiaYMes() + "\n");
					}
				}
			}
		});
		btnVerTodasHistorico.setBounds(187, 174, 146, 23);
		contentPane.add(btnVerTodasHistorico);

		JLabel lblTotal30Dias = new JLabel("");
		lblTotal30Dias.setBounds(112, 511, 111, 21);
		contentPane.add(lblTotal30Dias);

		JLabel lblTotalHistorico = new JLabel("");
		lblTotalHistorico.setBounds(309, 511, 111, 21);
		contentPane.add(lblTotalHistorico);

		textConcepto = new JTextField();
		textConcepto.setColumns(10);
		textConcepto.setBounds(26, 82, 151, 20);
		contentPane.add(textConcepto);
	}
}
