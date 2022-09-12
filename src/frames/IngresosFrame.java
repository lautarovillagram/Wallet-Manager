package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cargarGuardar.GuardarWallet;
import walletManager.Cuenta;
import walletManager.Ingreso;
import walletManager.Wallet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;

public class IngresosFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textMonto;

	JComboBox comboCuentaAUtilizar = new JComboBox();
	JComboBox comboCuentaAVer = new JComboBox();
	private Wallet wallet;
	private Wallet walletBackup;
	private JTextField textConcepto;
	GuardarWallet guardar = new GuardarWallet();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public void cargarCombos() {
		for (Cuenta cuenta : wallet.getCuentas()) {
			comboCuentaAUtilizar.addItem(cuenta);
			comboCuentaAVer.addItem(cuenta);

		}
	}

	public IngresosFrame(Wallet walletPasada) {

		setTitle("Wallet Manager - Ingresos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.wallet = walletPasada;
		this.walletBackup = walletPasada;
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
		btnIrAtras.setBounds(25, 11, 89, 23);
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
		btnGuardar.setBounds(259, 11, 89, 23);
		contentPane.add(btnGuardar);

		JButton btnRevertir = new JButton("Revertir");
		btnRevertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnRevertir) {
					wallet = walletBackup;
					JOptionPane.showMessageDialog(null, "Cambios revertidos");

				}
			}
		});
		btnRevertir.setBounds(160, 11, 89, 23);
		contentPane.add(btnRevertir);

		JLabel lblAgregar = new JLabel("Depositar");
		lblAgregar.setBounds(25, 45, 66, 14);
		contentPane.add(lblAgregar);

		textConcepto = new JTextField();
		textConcepto.setBounds(101, 77, 86, 20);
		contentPane.add(textConcepto);
		textConcepto.setColumns(10);

		textMonto = new JTextField();
		textMonto.setBounds(101, 45, 86, 20);
		contentPane.add(textMonto);
		textMonto.setColumns(10);

		JLabel lblUtilizando = new JLabel("En");
		lblUtilizando.setBounds(197, 48, 71, 14);
		contentPane.add(lblUtilizando);
		JTextArea textArea = new JTextArea();

		comboCuentaAUtilizar.setBounds(227, 45, 89, 21);
		contentPane.add(comboCuentaAUtilizar);

		JButton btnAgregar = new JButton("agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAgregar) {
					Cuenta cuentaSeleccionada = (Cuenta) comboCuentaAUtilizar.getSelectedItem();
					wallet.agregarIngreso(textConcepto.getText(), Float.parseFloat(textMonto.getText()),
							cuentaSeleccionada);
					cuentaSeleccionada.setMonto(cuentaSeleccionada.getMonto() + Float.parseFloat(textMonto.getText()));
					

					JOptionPane.showMessageDialog(null,
							"Ingreso de $" + textMonto.getText() + " agregado en " + cuentaSeleccionada.toString());
					textConcepto.setText("");
					textMonto.setText("");
				}
			}
		});
		btnAgregar.setBounds(227, 76, 89, 23);
		contentPane.add(btnAgregar);

		comboCuentaAVer.setBounds(21, 131, 103, 23);
		contentPane.add(comboCuentaAVer);

		JButton btnVer30Dias = new JButton("Ver");
		btnVer30Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVer30Dias) {
					textArea.setText("");
					Cuenta cuentaSeleccionada = (Cuenta) comboCuentaAVer.getSelectedItem();
					List<Ingreso> ingresos = wallet.getIngresos().stream()
							.filter(i -> i.getCuenta() == cuentaSeleccionada
									&& i.getFecha().isAfter(LocalDateTime.now().minusDays(30)))
							.toList();

					for (Ingreso ingreso : ingresos) {
						textArea.append("$ " + ingreso.getMonto() + " | " + ingreso.getConcepto() + " | fecha: "
								+ ingreso.getDiaYMes() + "\n");
					}

				}
			}
		});
		btnVer30Dias.setBounds(134, 131, 89, 23);
		contentPane.add(btnVer30Dias);

		JButton btnVerTodas30Dias = new JButton("todas las cuentas");
		btnVerTodas30Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerTodas30Dias) {
					textArea.setText("");
					List<Ingreso> ingresos = wallet.getIngresos().stream()
							.filter(i -> i.getFecha().isAfter(LocalDateTime.now().minusDays(30))).toList();

					for (Ingreso ingreso : ingresos) {
						textArea.append("$ " + ingreso.getMonto() + " | " + ingreso.getConcepto() + " | "
								+ ingreso.getCuenta() + " | fecha: " + ingreso.getDiaYMes() + "\n");
					}
				}

			}
		});
		btnVerTodas30Dias.setBounds(25, 165, 173, 23);
		contentPane.add(btnVerTodas30Dias);

		JButton btnVerHistorico = new JButton("Ver historico");
		btnVerHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerHistorico) {
					textArea.setText("");
					Cuenta cuentaSeleccionada = (Cuenta) comboCuentaAVer.getSelectedItem();
					List<Ingreso> ingresos = wallet.getIngresos().stream()
							.filter(i -> i.getCuenta() == cuentaSeleccionada).toList();

					for (Ingreso ingreso : ingresos) {
						textArea.append("$ " + ingreso.getMonto() + " | " + ingreso.getConcepto() + " | fecha: "
								+ ingreso.getDiaYMes() + "\n");
					}

				}
			}
		});
		btnVerHistorico.setBounds(227, 131, 120, 23);
		contentPane.add(btnVerHistorico);

		JButton btnVerTodasHistorico = new JButton("todas historico");
		btnVerTodasHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerTodasHistorico) {
					textArea.setText("");
					List<Ingreso> ingresos = wallet.getIngresos();

					for (Ingreso ingreso : ingresos) {
						textArea.append("$ " + ingreso.getMonto() + " | " + ingreso.getConcepto() + " | "
								+ ingreso.getCuenta() + " | fecha: " + ingreso.getDiaYMes() + "\n");
					}
				}
			}
		});
		btnVerTodasHistorico.setBounds(208, 165, 136, 23);
		contentPane.add(btnVerTodasHistorico);

		JScrollPane scrollPane = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(25, 199, 323, 324);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(textArea);

		JLabel lblTotal30Dias = new JLabel("");
		lblTotal30Dias.setBounds(67, 502, 111, 21);
		contentPane.add(lblTotal30Dias);

		JLabel lblTotalHistorico = new JLabel("");
		lblTotalHistorico.setBounds(309, 502, 111, 21);
		contentPane.add(lblTotalHistorico);

		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(25, 80, 66, 14);
		contentPane.add(lblConcepto);

		JLabel lblNewLabel = new JLabel("Ver ultimos 30 dias");
		lblNewLabel.setBounds(67, 108, 120, 14);
		contentPane.add(lblNewLabel);

	}
}
