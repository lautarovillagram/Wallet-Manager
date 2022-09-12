package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cargarGuardar.GuardarWallet;
import walletManager.Wallet;
import walletManager.Compra;
import walletManager.CompraDeDolares;
import walletManager.Cuenta;
import walletManager.Venta;

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
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DolaresFrame extends JFrame implements Serializable {

	private JPanel contentPane;
	private JTextField textDolaresCompra;
	private JTextField textPesosCompra;
	private JTextField textDolaresVenta;
	private JTextField textPesosVenta;
	Wallet wallet;
	Wallet walletBackup;
	GuardarWallet guardar = new GuardarWallet();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public DolaresFrame(Wallet billetera) {
		setTitle("Compra y venta de dolares");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.wallet = billetera;
		this.walletBackup = billetera;
		JLabel lblCantidadDolares = new JLabel("");
		lblCantidadDolares.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidadDolares.setBounds(116, 43, 79, 20);
		contentPane.add(lblCantidadDolares);
		lblCantidadDolares.setText("USD " + wallet.getDolares());

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
		btnIrAtras.setBounds(10, 12, 86, 20);
		contentPane.add(btnIrAtras);

		JLabel lblDolaresTotales = new JLabel("");
		lblDolaresTotales.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDolaresTotales.setBounds(10, 216, 102, 20);
		contentPane.add(lblDolaresTotales);

		JButton btnRevertir = new JButton("Revertir");
		btnRevertir.setBounds(127, 12, 79, 20);
		contentPane.add(btnRevertir);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnGuardar) {

					guardar.wallet = wallet;
					try {
						guardar.guardar();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}

			}
		});
		btnGuardar.setBounds(226, 10, 86, 24);
		contentPane.add(btnGuardar);

		JLabel lblCompraDeDolares = new JLabel("Compra de dolares");
		lblCompraDeDolares.setBounds(105, 66, 130, 20);
		contentPane.add(lblCompraDeDolares);

		textDolaresCompra = new JTextField();
		textDolaresCompra.setBounds(10, 94, 86, 20);
		contentPane.add(textDolaresCompra);
		textDolaresCompra.setColumns(10);

		JLabel lblCompraA = new JLabel("a");
		lblCompraA.setBounds(106, 97, 46, 14);
		contentPane.add(lblCompraA);

		textPesosCompra = new JTextField();
		textPesosCompra.setColumns(10);
		textPesosCompra.setBounds(127, 94, 86, 20);
		contentPane.add(textPesosCompra);

		JScrollPane scrollPane = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 237, 299, 184);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton btnAgregarCompra = new JButton("agregar");
		btnAgregarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAgregarCompra) {
					wallet.getComprasDeDolares().add(0,
							new CompraDeDolares(Float.parseFloat(textDolaresCompra.getText()),
									Float.parseFloat(textPesosCompra.getText()), new Compra()));
					wallet.setDolares(wallet.getDolares() + Float.parseFloat(textDolaresCompra.getText()));
					JOptionPane.showMessageDialog(null, "Dolares comprados");
					textDolaresCompra.setText("");
					textPesosCompra.setText("");
					lblCantidadDolares.setText("USD " +String.format("%,.2f", wallet.getDolares()));

				}
			}
		});

		btnAgregarCompra.setBounds(223, 93, 89, 23);
		contentPane.add(btnAgregarCompra);

		JLabel lblVentaDeDolares = new JLabel("Venta de dolares");
		lblVentaDeDolares.setBounds(105, 122, 130, 20);
		contentPane.add(lblVentaDeDolares);

		textDolaresVenta = new JTextField();
		textDolaresVenta.setColumns(10);
		textDolaresVenta.setBounds(10, 154, 86, 20);
		contentPane.add(textDolaresVenta);

		JButton btnVender = new JButton("vender");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVender) {
					
					wallet.getComprasDeDolares().add(0,
							new CompraDeDolares(Float.parseFloat(textDolaresVenta.getText()),
									Float.parseFloat(textPesosVenta.getText()), new Venta()));
					wallet.setDolares(wallet.getDolares() - Float.parseFloat(textDolaresVenta.getText()));
					JOptionPane.showMessageDialog(null, "Venta concretada");
					textDolaresVenta.setText("");
					textPesosVenta.setText("");
					lblCantidadDolares.setText("USD " + String.format("%,.2f", wallet.getDolares()));
				}
			}
		});
		btnVender.setBounds(223, 153, 89, 23);
		contentPane.add(btnVender);

		textPesosVenta = new JTextField();
		textPesosVenta.setColumns(10);
		textPesosVenta.setBounds(127, 154, 86, 20);
		contentPane.add(textPesosVenta);

		JLabel lblVentaA = new JLabel("a");
		lblVentaA.setBounds(106, 157, 46, 14);
		contentPane.add(lblVentaA);

		JLabel lblDolaresAPesos = new JLabel("");
		lblDolaresAPesos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDolaresAPesos.setBounds(195, 216, 113, 20);
		contentPane.add(lblDolaresAPesos);

		JButton btnComprasDeDolares = new JButton("ver compras");
		btnComprasDeDolares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnComprasDeDolares) {
					textArea.setText("");
					lblDolaresTotales.setText("");
					lblDolaresAPesos.setText("");
					Compra compra = new Compra();
					List<CompraDeDolares> compras = wallet.getComprasDeDolares().stream()
							.filter(c -> c.getTipoDeTransaccion().getClass() == compra.getClass()).toList();
					for (CompraDeDolares compraDolares : compras) {
						textArea.append("USD " + compraDolares.getMontoDolares() + " a $"
								+ String.format("%,.2f", compraDolares.getCotizacionAlMomentoDeLaCompra()) + " | Fecha: "
								+ compraDolares.getFecha().getDayOfMonth() + "/"
								+ compraDolares.getFecha().getMonthValue() + "\n");
					}
					lblDolaresTotales.setText("USD " + String.format("%,.2f",compras.stream().mapToDouble(c -> c.getMontoDolares()).sum()));
					lblDolaresAPesos.setText("$ " + String.format("%,.2f",compras.stream().mapToDouble(c -> c.getMontoPesos()).sum()));

				}
			}
		});
		btnComprasDeDolares.setBounds(10, 185, 130, 20);
		contentPane.add(btnComprasDeDolares);

		JButton btnVerVentas = new JButton("ver ventas");
		btnVerVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerVentas) {
					textArea.setText("");
					lblDolaresTotales.setText("");
					lblDolaresAPesos.setText("");
					Venta venta = new Venta();
					List<CompraDeDolares> ventas = wallet.getComprasDeDolares().stream()
							.filter(c -> c.getTipoDeTransaccion().getClass() == venta.getClass()).toList();
					for (CompraDeDolares compra : ventas) {
						textArea.append("USD " + compra.getMontoDolares() + " a $"
								+ compra.getCotizacionAlMomentoDeLaCompra() + " | Fecha: "
								+ compra.getFecha().getDayOfMonth() + "/" + compra.getFecha().getMonthValue() + "\n");
					}
					lblDolaresTotales.setText("USD " + ventas.stream().mapToDouble(c -> c.getMontoDolares()).sum());
					lblDolaresAPesos.setText("$ " + ventas.stream().mapToDouble(c -> c.getMontoPesos()).sum());

				}
			}
		});
		btnVerVentas.setBounds(179, 185, 130, 20);
		contentPane.add(btnVerVentas);

		JLabel lblDolares = new JLabel("Dolares:");
		lblDolares.setBounds(40, 43, 56, 14);
		contentPane.add(lblDolares);

	}
}
