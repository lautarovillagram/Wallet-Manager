package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cargarGuardar.CargarWallet;
import cargarGuardar.GuardarWallet;
import walletManager.Cuota;
import walletManager.Tarjeta;
import walletManager.Producto;
import walletManager.Wallet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CuotasFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textMonto;
	private JTextField textCuotas;
	private JTextField textConcepto;
	private JTextField textDia;
	private JTextField textMes;
	private JTextField textDiaCierre;
	private JTextField textMesCierre;
	Wallet wallet;
	Wallet walletBackup;
	GuardarWallet guardar = new GuardarWallet();
	CargarWallet cargar = new CargarWallet();

	JComboBox comboTarjetaAUtilizar = new JComboBox();
	JComboBox comboProductos = new JComboBox();
	JComboBox comboTarjetaAActualizar = new JComboBox();
	JComboBox comboTarjetas = new JComboBox();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public void llenarCombos() {
		comboProductos.removeAllItems();
		comboTarjetaAUtilizar.removeAllItems();
		comboTarjetas.removeAllItems();
		comboTarjetaAActualizar.removeAllItems();
		for (Tarjeta tarjeta : wallet.getTarjetasDeCredito()) {
			comboTarjetaAUtilizar.addItem(tarjeta);
			comboTarjetas.addItem(tarjeta);
			comboTarjetaAActualizar.addItem(tarjeta);
		}
		for (Producto producto : wallet.productosConDeuda()) {
			comboProductos.addItem(producto);
		}
	}

	public CuotasFrame(Wallet billetera) {
		setTitle("Gestion de cuotas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		this.wallet = billetera;
		llenarCombos();
		JLabel lblMostrar = new JLabel("mostrar");
		lblMostrar.setBounds(10, 131, 46, 14);
		contentPane.add(lblMostrar);

		comboTarjetas.setBounds(61, 127, 96, 22);
		contentPane.add(comboTarjetas);

		JLabel lblMes1 = new JLabel("");
		lblMes1.setBounds(41, 351, 69, 14);
		contentPane.add(lblMes1);

		JLabel lblMes2 = new JLabel("");
		lblMes2.setBounds(188, 351, 69, 14);
		contentPane.add(lblMes2);

		JLabel lblMes3 = new JLabel("");
		lblMes3.setBounds(340, 351, 69, 14);
		contentPane.add(lblMes3);

		JLabel lblMes4 = new JLabel("");
		lblMes4.setBounds(483, 351, 69, 14);
		contentPane.add(lblMes4);

		JLabel lblMes5 = new JLabel("");
		lblMes5.setBounds(630, 351, 69, 14);
		contentPane.add(lblMes5);

		JLabel lblMes6 = new JLabel("");
		lblMes6.setBounds(777, 351, 69, 14);
		contentPane.add(lblMes6);

		JLabel lblMes7 = new JLabel("");
		lblMes7.setBounds(41, 534, 69, 14);
		contentPane.add(lblMes7);

		JLabel lblMes8 = new JLabel("");
		lblMes8.setBounds(188, 534, 69, 14);
		contentPane.add(lblMes8);

		JLabel lblMes9 = new JLabel("");
		lblMes9.setBounds(340, 537, 69, 14);
		contentPane.add(lblMes9);

		JLabel lblMes10 = new JLabel("");
		lblMes10.setBounds(483, 537, 69, 14);
		contentPane.add(lblMes10);

		JLabel lblMes11 = new JLabel("");
		lblMes11.setBounds(630, 537, 69, 14);
		contentPane.add(lblMes11);

		JLabel lblMes12 = new JLabel("");
		lblMes12.setBounds(777, 537, 69, 14);
		contentPane.add(lblMes12);

		JLabel lblAgregarCompra = new JLabel("Agregar compra");
		lblAgregarCompra.setBounds(10, 42, 100, 17);
		contentPane.add(lblAgregarCompra);

		textMonto = new JTextField();
		textMonto.setColumns(10);
		textMonto.setBounds(115, 39, 86, 20);
		contentPane.add(textMonto);

		JLabel lblEn = new JLabel("en");
		lblEn.setBounds(211, 42, 24, 14);
		contentPane.add(lblEn);

		textCuotas = new JTextField();
		textCuotas.setColumns(10);
		textCuotas.setBounds(237, 39, 46, 20);
		contentPane.add(textCuotas);

		JLabel lblCuotasUtilizando = new JLabel("cuotas, utilizando");
		lblCuotasUtilizando.setBounds(293, 42, 116, 14);
		contentPane.add(lblCuotasUtilizando);

		comboTarjetaAUtilizar.setBounds(400, 39, 96, 22);
		contentPane.add(comboTarjetaAUtilizar);

		JButton btnAgregarCompra = new JButton("agregar");
		btnAgregarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAgregarCompra) {
					Tarjeta tarjetaSeleccionada = (Tarjeta) comboTarjetaAUtilizar.getSelectedItem();
					tarjetaSeleccionada.agregarCompra(textConcepto.getText(), Integer.parseInt(textCuotas.getText()),
							Float.parseFloat(textMonto.getText()), Integer.parseInt(textMes.getText()),
							Integer.parseInt(textDia.getText()), tarjetaSeleccionada.fueCompradoDespuesDelVencimiento(
									Integer.parseInt(textDia.getText()), Integer.parseInt(textMes.getText())));

					JOptionPane.showMessageDialog(null, "Compra agregada correctamente");
					llenarCombos();
					textConcepto.setText("");
					textCuotas.setText("");
					textMonto.setText("");
					textDia.setText("");
					textMes.setText("");

				}

			}
		});
		btnAgregarCompra.setBounds(518, 39, 89, 23);
		contentPane.add(btnAgregarCompra);

		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(10, 70, 69, 19);
		contentPane.add(lblConcepto);

		textConcepto = new JTextField();
		textConcepto.setColumns(10);
		textConcepto.setBounds(76, 67, 207, 22);
		contentPane.add(textConcepto);

		JLabel lblFecha = new JLabel("fecha");
		lblFecha.setBounds(304, 70, 46, 14);
		contentPane.add(lblFecha);

		textDia = new JTextField();
		textDia.setColumns(10);
		textDia.setBounds(340, 69, 33, 20);
		contentPane.add(textDia);

		JLabel lblDe = new JLabel("de");
		lblDe.setBounds(383, 72, 46, 14);
		contentPane.add(lblDe);

		textMes = new JTextField();
		textMes.setColumns(10);
		textMes.setBounds(408, 69, 33, 20);
		contentPane.add(textMes);

		JButton btnGuardar = new JButton("guardar");
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
		btnGuardar.setBounds(793, 11, 89, 23);
		contentPane.add(btnGuardar);

		JButton btnRevertir = new JButton("revertir");
		btnRevertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnRevertir) {
					wallet = walletBackup;

				}

			}
		});
		btnRevertir.setBounds(694, 11, 89, 23);
		contentPane.add(btnRevertir);

		JScrollPane scrollMes1 = new JScrollPane();
		scrollMes1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes1.setBounds(10, 193, 137, 147);
		contentPane.add(scrollMes1);

		JTextArea textArea = new JTextArea();
		scrollMes1.setViewportView(textArea);

		JScrollPane scrollMes2 = new JScrollPane();
		scrollMes2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes2.setBounds(157, 193, 137, 147);
		contentPane.add(scrollMes2);

		JTextArea textArea_1 = new JTextArea();
		scrollMes2.setViewportView(textArea_1);

		JScrollPane scrollMes3 = new JScrollPane();
		scrollMes3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes3.setBounds(304, 193, 137, 147);
		contentPane.add(scrollMes3);

		JTextArea textArea_2 = new JTextArea();
		scrollMes3.setViewportView(textArea_2);

		JScrollPane scrollMes4 = new JScrollPane();
		scrollMes4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes4.setBounds(451, 193, 137, 147);
		contentPane.add(scrollMes4);

		JTextArea textArea_3 = new JTextArea();
		scrollMes4.setViewportView(textArea_3);

		JScrollPane scrollMes5 = new JScrollPane();
		scrollMes5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes5.setBounds(598, 193, 137, 147);
		contentPane.add(scrollMes5);

		JTextArea textArea_4 = new JTextArea();
		scrollMes5.setViewportView(textArea_4);

		JScrollPane scrollMes6 = new JScrollPane();
		scrollMes6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes6.setBounds(745, 193, 137, 147);
		contentPane.add(scrollMes6);

		JTextArea textArea_5 = new JTextArea();
		scrollMes6.setViewportView(textArea_5);

		JScrollPane scrollMes7 = new JScrollPane();
		scrollMes7.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes7.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes7.setBounds(10, 379, 137, 147);
		contentPane.add(scrollMes7);

		JTextArea textArea_6 = new JTextArea();
		scrollMes7.setViewportView(textArea_6);

		JScrollPane scrollMes8 = new JScrollPane();
		scrollMes8.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes8.setBounds(157, 379, 137, 147);
		contentPane.add(scrollMes8);

		JTextArea textArea_7 = new JTextArea();
		scrollMes8.setViewportView(textArea_7);

		JScrollPane scrollMes9 = new JScrollPane();
		scrollMes9.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes9.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes9.setBounds(304, 379, 137, 147);
		contentPane.add(scrollMes9);

		JTextArea textArea_8 = new JTextArea();
		scrollMes9.setViewportView(textArea_8);

		JScrollPane scrollMes10 = new JScrollPane();
		scrollMes10.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes10.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes10.setBounds(451, 379, 137, 147);
		contentPane.add(scrollMes10);

		JTextArea textArea_9 = new JTextArea();
		scrollMes10.setViewportView(textArea_9);

		JScrollPane scrollMes11 = new JScrollPane();
		scrollMes11.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes11.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes11.setBounds(598, 379, 137, 147);
		contentPane.add(scrollMes11);

		JTextArea textArea_10 = new JTextArea();
		scrollMes11.setViewportView(textArea_10);

		JScrollPane scrollMes12 = new JScrollPane();
		scrollMes12.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes12.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes12.setBounds(745, 379, 137, 147);
		contentPane.add(scrollMes12);

		JTextArea textArea_11 = new JTextArea();
		scrollMes12.setViewportView(textArea_11);

		JButton btnMostrar = new JButton("mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnMostrar) {
					Tarjeta tarjetaSeleccionada = (Tarjeta) comboTarjetas.getSelectedItem();
					List<Producto> productos = new ArrayList<>();
					productos.clear();

					productos = tarjetaSeleccionada.getProductos();

					List<Cuota> todasLasCuotas = new ArrayList<>();
					for (Producto producto : productos) {
						List<Cuota> cuotasSinPagar = producto.getCuotas().stream().filter(c -> !c.getFuePagada())
								.toList();
						todasLasCuotas.addAll(cuotasSinPagar);
					}
					LocalDateTime esteMes = LocalDateTime.of(LocalDateTime.now().getYear(),
							LocalDateTime.now().getMonthValue(), 1, 1, 1);

					List<Cuota> cuotasMes1 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.getMonthValue()).toList();
					List<Cuota> cuotasMes2 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(1).getMonthValue())
							.toList();
					List<Cuota> cuotasMes3 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(2).getMonthValue())
							.toList();
					List<Cuota> cuotasMes4 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(3).getMonthValue())
							.toList();
					List<Cuota> cuotasMes5 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(4).getMonthValue())
							.toList();
					List<Cuota> cuotasMes6 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(5).getMonthValue())
							.toList();
					List<Cuota> cuotasMes7 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(6).getMonthValue())
							.toList();
					List<Cuota> cuotasMes8 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(7).getMonthValue())
							.toList();
					List<Cuota> cuotasMes9 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(8).getMonthValue())
							.toList();
					List<Cuota> cuotasMes10 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(9).getMonthValue())
							.toList();
					List<Cuota> cuotasMes11 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(10).getMonthValue())
							.toList();
					List<Cuota> cuotasMes12 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(11).getMonthValue())
							.toList();

					textArea.setText("");
					if (!cuotasMes1.isEmpty()) {
						for (Cuota cuota : cuotasMes1) {

							textArea.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
									+ String.format("%,.2f", cuota.getMonto()) + "\n");

						}
					} else {
						textArea.append("Mes actual abonado");
					}

					lblMes1.setText("");
					lblMes1.setText(
							"$ " + String.format("%,.2f", cuotasMes1.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_1.setText("");
					for (Cuota cuota : cuotasMes2) {

						textArea_1.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes2.setText("");
					lblMes2.setText(
							"$ " + String.format("%,.2f", cuotasMes2.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_2.setText("");
					for (Cuota cuota : cuotasMes3) {

						textArea_2.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes3.setText("");
					lblMes3.setText(
							"$ " + String.format("%,.2f", cuotasMes3.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_3.setText("");
					for (Cuota cuota : cuotasMes4) {

						textArea_3.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes4.setText("");
					lblMes4.setText(
							"$ " + String.format("%,.2f", cuotasMes4.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_4.setText("");
					for (Cuota cuota : cuotasMes5) {

						textArea_4.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes5.setText("");
					lblMes5.setText(
							"$ " + String.format("%,.2f", cuotasMes5.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_5.setText("");
					for (Cuota cuota : cuotasMes6) {

						textArea_5.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes6.setText("");
					lblMes6.setText(
							"$ " + String.format("%,.2f", cuotasMes6.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_6.setText("");
					for (Cuota cuota : cuotasMes7) {

						textArea_6.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes7.setText("");
					lblMes7.setText(
							"$ " + String.format("%,.2f", cuotasMes7.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_7.setText("");
					for (Cuota cuota : cuotasMes8) {

						textArea_7.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes8.setText("");
					lblMes8.setText(
							"$ " + String.format("%,.2f", cuotasMes8.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_8.setText("");
					for (Cuota cuota : cuotasMes9) {

						textArea_8.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes9.setText("");
					lblMes9.setText(
							"$ " + String.format("%,.2f", cuotasMes9.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_9.setText("");
					for (Cuota cuota : cuotasMes10) {

						textArea_9.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes10.setText("");
					lblMes10.setText(
							"$ " + String.format("%,.2f", cuotasMes10.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_10.setText("");
					for (Cuota cuota : cuotasMes11) {

						textArea_10.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes11.setText("");
					lblMes11.setText(
							"$ " + String.format("%,.2f", cuotasMes12.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_11.setText("");
					for (Cuota cuota : cuotasMes12) {

						textArea_11.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes12.setText("");
					lblMes12.setText(
							"$ " + String.format("%,.2f", cuotasMes12.stream().mapToDouble(c -> c.getMonto()).sum()));

				}
			}
		});
		btnMostrar.setBounds(168, 127, 89, 23);
		contentPane.add(btnMostrar);

		JLabel lblActualizarCierre = new JLabel("Actualizar cierre de");
		lblActualizarCierre.setBounds(408, 131, 158, 14);
		contentPane.add(lblActualizarCierre);

		comboTarjetaAActualizar.setBounds(541, 127, 96, 22);
		contentPane.add(comboTarjetaAActualizar);

		textDiaCierre = new JTextField();
		textDiaCierre.setColumns(10);
		textDiaCierre.setBounds(438, 160, 33, 22);
		contentPane.add(textDiaCierre);

		JLabel lblBarra = new JLabel("/");
		lblBarra.setBounds(481, 163, 46, 14);
		contentPane.add(lblBarra);

		textMesCierre = new JTextField();
		textMesCierre.setColumns(10);
		textMesCierre.setBounds(491, 160, 33, 22);
		contentPane.add(textMesCierre);

		JButton btnActualizarVencimiento = new JButton("actualizar");
		btnActualizarVencimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnActualizarVencimiento) {
					Tarjeta tarjetaAActualizar = (Tarjeta) comboTarjetaAActualizar.getSelectedItem();
					tarjetaAActualizar.setCierre(Integer.parseInt(textDiaCierre.getText()),
							Integer.parseInt(textMesCierre.getText()));
					JOptionPane.showMessageDialog(null, "Cierre actualizado correctamente");
				}
			}
		});
		btnActualizarVencimiento.setBounds(537, 160, 100, 22);
		contentPane.add(btnActualizarVencimiento);

		JLabel lblVerDeuda = new JLabel("Ver deuda de");
		lblVerDeuda.setBounds(528, 73, 91, 14);
		contentPane.add(lblVerDeuda);

		comboProductos.setBounds(630, 68, 169, 22);
		contentPane.add(comboProductos);

		JLabel lblDeuda = new JLabel(" ");
		lblDeuda.setBounds(675, 100, 207, 82);
		contentPane.add(lblDeuda);

		JButton btnDeuda = new JButton("ver");
		btnDeuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnDeuda) {
					Producto productoSeleccionado = (Producto) comboProductos.getSelectedItem();
					lblDeuda.setText("");
					lblDeuda.setText("<html>Cuotas restantes: " + productoSeleccionado.cantidadCuotasAPagar() + "<br/>"
							+ "Monto a pagar: $"
							+ String.format("%,.2f",
									productoSeleccionado.cantidadCuotasAPagar() * productoSeleccionado.getValorCuota())
							+ "</html>");

				}
			}
		});
		btnDeuda.setBounds(809, 68, 69, 22);
		contentPane.add(btnDeuda);

		JButton btnMostrarAmbas = new JButton("Mostrar todas las tarjetas");
		btnMostrarAmbas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnMostrarAmbas) {
					List<Producto> productos = new ArrayList<>();
					productos.clear();

					productos = wallet.getTodosLosProductos();
					List<Cuota> todasLasCuotas = new ArrayList<>();
					for (Producto producto : productos) {
						List<Cuota> cuotasSinPagar = producto.getCuotas().stream().filter(c -> !c.getFuePagada())
								.toList();
						todasLasCuotas.addAll(cuotasSinPagar);
					}
					LocalDateTime esteMes = LocalDateTime.of(LocalDateTime.now().getYear(),
							LocalDateTime.now().getMonthValue(), 1, 1, 1);

					List<Cuota> cuotasMes1 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.getMonthValue()).toList();
					List<Cuota> cuotasMes2 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(1).getMonthValue())
							.toList();
					List<Cuota> cuotasMes3 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(2).getMonthValue())
							.toList();
					List<Cuota> cuotasMes4 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(3).getMonthValue())
							.toList();
					List<Cuota> cuotasMes5 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(4).getMonthValue())
							.toList();
					List<Cuota> cuotasMes6 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(5).getMonthValue())
							.toList();
					List<Cuota> cuotasMes7 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(6).getMonthValue())
							.toList();
					List<Cuota> cuotasMes8 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(7).getMonthValue())
							.toList();
					List<Cuota> cuotasMes9 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(8).getMonthValue())
							.toList();
					List<Cuota> cuotasMes10 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(9).getMonthValue())
							.toList();
					List<Cuota> cuotasMes11 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(10).getMonthValue())
							.toList();
					List<Cuota> cuotasMes12 = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == esteMes.plusMonths(11).getMonthValue())
							.toList();
					// cuotasMes1.clear();

					textArea.setText("");
					if (!cuotasMes1.isEmpty()) {
						for (Cuota cuota : cuotasMes1) {

							textArea.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
									+ String.format("%,.2f", cuota.getMonto()) + "\n");

						}
					} else {
						textArea.append("Mes actual abonado");
					}

					lblMes1.setText("");
					if (cuotasMes1.isEmpty()) {
						lblMes1.setText("$ 0.0");
					} else {
						lblMes1.setText("$ "
								+ String.format("%,.2f", cuotasMes1.stream().mapToDouble(c -> c.getMonto()).sum()));
						textArea_1.setText("");
					}
					textArea_1.setText("");
					for (Cuota cuota : cuotasMes2) {

						textArea_1.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes2.setText("");
					lblMes2.setText(
							"$ " + String.format("%,.2f", cuotasMes2.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_2.setText("");
					for (Cuota cuota : cuotasMes3) {

						textArea_2.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes3.setText("");
					lblMes3.setText(
							"$ " + String.format("%,.2f", cuotasMes3.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_3.setText("");
					for (Cuota cuota : cuotasMes4) {

						textArea_3.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes4.setText("");
					lblMes4.setText(
							"$ " + String.format("%,.2f", cuotasMes4.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_4.setText("");
					for (Cuota cuota : cuotasMes5) {

						textArea_4.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes5.setText("");
					lblMes5.setText(
							"$ " + String.format("%,.2f", cuotasMes5.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_5.setText("");
					for (Cuota cuota : cuotasMes6) {

						textArea_5.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes6.setText("");
					lblMes6.setText(
							"$ " + String.format("%,.2f", cuotasMes6.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_6.setText("");
					for (Cuota cuota : cuotasMes7) {

						textArea_6.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes7.setText("");
					lblMes7.setText(
							"$ " + String.format("%,.2f", cuotasMes7.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_7.setText("");
					for (Cuota cuota : cuotasMes8) {

						textArea_7.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes8.setText("");
					lblMes8.setText(
							"$ " + String.format("%,.2f", cuotasMes8.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_8.setText("");
					for (Cuota cuota : cuotasMes9) {

						textArea_8.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes9.setText("");
					lblMes9.setText(
							"$ " + String.format("%,.2f", cuotasMes9.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_9.setText("");
					for (Cuota cuota : cuotasMes10) {

						textArea_9.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes10.setText("");
					lblMes10.setText(
							"$ " + String.format("%,.2f", cuotasMes10.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_10.setText("");
					for (Cuota cuota : cuotasMes11) {

						textArea_10.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes11.setText("");
					lblMes11.setText(
							"$ " + String.format("%,.2f", cuotasMes12.stream().mapToDouble(c -> c.getMonto()).sum()));
					textArea_11.setText("");
					for (Cuota cuota : cuotasMes12) {

						textArea_11.append(cuota.getProducto() + " | " + cuota.getNumeroDeCuota() + " | $"
								+ String.format("%,.2f", cuota.getMonto()) + "\n");

					}

					lblMes12.setText("");
					lblMes12.setText(
							"$ " + String.format("%,.2f", cuotasMes12.stream().mapToDouble(c -> c.getMonto()).sum()));

				}
			}
		});
		btnMostrarAmbas.setBounds(40, 156, 217, 23);
		contentPane.add(btnMostrarAmbas);

		JButton btnAbonado = new JButton("abonado");
		btnAbonado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAbonado) {
					Tarjeta tarjetaSeleccionada = (Tarjeta) comboTarjetas.getSelectedItem();
					List<Producto> productos = tarjetaSeleccionada.getProductos();
					List<Cuota> todasLasCuotas = new ArrayList<>();
					for (Producto producto : productos) {

						List<Cuota> cuotas = producto.getCuotas().stream().filter(c -> !c.getFuePagada()).toList();
						todasLasCuotas.addAll(cuotas);

					}

					List<Cuota> cuotasMesActual = todasLasCuotas.stream()
							.filter(c -> c.getFecha().getMonthValue() == LocalDateTime.now().getMonthValue()
									&& !c.getFuePagada())
							.toList();
					cuotasMesActual.stream().forEach(c -> c.setFuePagada(true));
					llenarCombos();
					JOptionPane.showMessageDialog(null, "mes actual abonado en la tarjeta seleccionada");

				}
			}
		});
		btnAbonado.setBounds(270, 127, 103, 49);
		contentPane.add(btnAbonado);

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
		btnIrAtras.setBounds(10, 11, 89, 23);
		contentPane.add(btnIrAtras);

	}
}
