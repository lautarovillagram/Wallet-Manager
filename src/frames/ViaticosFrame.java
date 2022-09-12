package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cargarGuardar.GuardarWallet;
import walletManager.Cuenta;
import walletManager.Viatico;
import walletManager.Wallet;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ViaticosFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textMonto;
	private JTextField textFecha;
	private JTextField textMontoCobrado;
	Wallet wallet;
	Wallet walletBackup;
	GuardarWallet guardar = new GuardarWallet();
	JComboBox comboCuentas = new JComboBox();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public void llenarCombos() {
		for (Cuenta cuenta : wallet.getCuentas()) {
			comboCuentas.addItem(cuenta);
		}
	}

	public ViaticosFrame(Wallet billetera) {
		setTitle("Viaticos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 289, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		this.wallet = billetera;
		this.walletBackup = billetera;
		llenarCombos();

		contentPane.setLayout(null);

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

		JButton btnRevertir = new JButton("Revertir");
		btnRevertir.setBounds(99, 12, 79, 20);
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
		btnGuardar.setBounds(181, 11, 86, 24);
		contentPane.add(btnGuardar);

		JButton btnAgregar = new JButton("agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAgregar) {
					wallet.agregarViatico(Float.parseFloat(textMonto.getText()), textFecha.getText());
					JOptionPane.showMessageDialog(null, "viatico agregado efectivamente");
					textMonto.setText("");
					textFecha.setText("");

				}

			}
		});
		btnAgregar.setBounds(176, 57, 89, 23);
		contentPane.add(btnAgregar);

		textMonto = new JTextField();
		textMonto.setBounds(10, 58, 68, 20);
		contentPane.add(textMonto);
		textMonto.setColumns(10);

		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(88, 58, 68, 20);
		contentPane.add(textFecha);

		JScrollPane scrollPane = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 175, 257, 220);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(20, 43, 46, 14);
		contentPane.add(lblMonto);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(99, 43, 46, 14);
		contentPane.add(lblFecha);

		JButton btnCobrar = new JButton("Cobrar");
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Viatico viaticoConElMismoMonto = null;
				for (Viatico viatico : wallet.getViaticos()) {
					if (viatico.getValor() == Float.parseFloat(textMontoCobrado.getText())) {
						viaticoConElMismoMonto = viatico;
					}
				}
				viaticoConElMismoMonto.setFueCobrado(true);
				Cuenta cuentaADepositar = (Cuenta) comboCuentas.getSelectedItem();
				cuentaADepositar.setMonto(cuentaADepositar.getMonto() + viaticoConElMismoMonto.getValor());
				JOptionPane.showMessageDialog(null, "Viatico cobrado");
				textMontoCobrado.setText("");

			}
		});
		btnCobrar.setBounds(176, 91, 89, 23);
		contentPane.add(btnCobrar);

		JLabel lblValorViaticos = new JLabel("");
		lblValorViaticos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblValorViaticos.setBounds(99, 148, 136, 20);
		contentPane.add(lblValorViaticos);

		textMontoCobrado = new JTextField();
		textMontoCobrado.setColumns(10);
		textMontoCobrado.setBounds(10, 92, 68, 20);
		contentPane.add(textMontoCobrado);

		comboCuentas.setBounds(88, 92, 68, 20);
		contentPane.add(comboCuentas);

		JButton btnVerACobrar = new JButton("ver a cobrar");
		btnVerACobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerACobrar) {
					textArea.setText("");
					lblValorViaticos.setText("");
					for (Viatico viatico : billetera.viaticosACobrar()) {
						textArea.append("$ " + viatico.getValor() + " | Fecha: " + viatico.getFecha() + "\n");
					}
					lblValorViaticos.setText("$ " + wallet.montoViaticosACobrar() );

				}
			}
		});
		btnVerACobrar.setBounds(10, 123, 126, 20);
		contentPane.add(btnVerACobrar);

		JButton btnVerCobrados = new JButton("ver cobrados");
		btnVerCobrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == btnVerCobrados) {
					textArea.setText("");
					lblValorViaticos.setText("");

					for (Viatico viatico : billetera.viaticosCobrados()) {
						textArea.append(" $ " + viatico.getValor() + " | Fecha: " + viatico.getFecha() + "\n");
					}

					lblValorViaticos.setText("$ " + wallet.montoViaticosCobrados() );

				}

			}
		});
		btnVerCobrados.setBounds(137, 123, 126, 20);
		contentPane.add(btnVerCobrados);

	}

}
