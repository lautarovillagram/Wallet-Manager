package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cargarGuardar.CargarWallet;
import cargarGuardar.GuardarWallet;

import walletManager.Wallet;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class mainframe extends JFrame {

	private JPanel contentPane;
	private JTextField textCotizacion;
	private Wallet wallet;
	private Wallet walletBackup;
	CargarWallet cargado = new CargarWallet();
	GuardarWallet guardado = new GuardarWallet();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainframe frame = new mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Wallet getWalletBackup() {
		return walletBackup;
	}

	public void setWalletBackup(Wallet walletBackup) {
		this.walletBackup = walletBackup;
	}

	public mainframe() throws ClassNotFoundException, IOException {
		setTitle("Wallet Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 236, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		cargado.cargar();
		this.setWallet(cargado.wallet);
		this.setWalletBackup(cargado.wallet);

		JButton btnIngresos = new JButton("Ingresos");
		btnIngresos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnIngresos) {
					IngresosFrame ingresosFrame = new IngresosFrame(wallet);
					ingresosFrame.setVisible(true);
					dispose();

				}
			}
		});
		btnIngresos.setBounds(69, 73, 89, 23);
		contentPane.add(btnIngresos);

		JButton btnGastos = new JButton("Gastos");
		btnGastos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnGastos) {
					dispose();
					GastosFrame gastos = new GastosFrame(wallet);
					gastos.setVisible(true);

				}
			}
		});
		btnGastos.setBounds(10, 107, 89, 23);
		contentPane.add(btnGastos);

		JButton btnTransferencias = new JButton("Transferencias");
		btnTransferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnTransferencias) {
					dispose();
					TransferenciasFrame transferencias = new TransferenciasFrame(wallet);
					transferencias.setVisible(true);
				}
			}
		});
		btnTransferencias.setBounds(43, 141, 135, 23);
		contentPane.add(btnTransferencias);

		JButton btnDolares = new JButton("Dolares");
		btnDolares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()== btnDolares) {
					dispose();
					DolaresFrame dolares = new DolaresFrame(wallet);
					dolares.setVisible(true);
				}
			}
		});
		btnDolares.setBounds(69, 209, 89, 23);
		contentPane.add(btnDolares);

		textCotizacion = new JTextField();
		textCotizacion.setBounds(69, 268, 86, 20);
		contentPane.add(textCotizacion);
		textCotizacion.setColumns(10);
		textCotizacion.setText(Float.toString(wallet.getUltimoValorDolar()));

		JLabel lblCotizacion = new JLabel("Cotizaci\u00F3n:");
		lblCotizacion.setBounds(79, 243, 68, 14);
		contentPane.add(lblCotizacion);

		JButton btnViaticos = new JButton("Viaticos");
		btnViaticos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnViaticos) {
					dispose();
					ViaticosFrame viaticos = new ViaticosFrame(wallet);
					viaticos.setVisible(true);
				}
			}
		});
		btnViaticos.setBounds(69, 175, 89, 23);
		contentPane.add(btnViaticos);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnGuardar) {
					wallet.setUltimoValorDolar(Float.parseFloat(textCotizacion.getText()));
					guardado.wallet = wallet;
					try {
						guardado.guardar();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}

			}
		});
		btnGuardar.setBounds(69, 11, 89, 23);
		contentPane.add(btnGuardar);

		JButton btnVerTotal = new JButton("ver total");
		btnVerTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerTotal) {
					dispose();
					VerTotalFrame verTotal = new VerTotalFrame(wallet);
					verTotal.setVisible(true);
				}
			}
		});
		btnVerTotal.setBounds(69, 43, 89, 23);
		contentPane.add(btnVerTotal);

		JButton btnCuotas = new JButton("Cuotas");
		btnCuotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCuotas) {
					dispose();
					CuotasFrame cuotas = new CuotasFrame(wallet);
					cuotas.setVisible(true);
				}
			}
		});
		btnCuotas.setBounds(121, 107, 89, 23);
		contentPane.add(btnCuotas);
	}
}
