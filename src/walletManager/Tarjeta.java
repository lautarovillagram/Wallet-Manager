package walletManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tarjeta implements Serializable {
	private String nombre;
	private LocalDateTime cierre;
	private ArrayList<Producto> productos = new ArrayList<>();

	public List<Producto> getProductos() {
		return this.productos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public LocalDateTime getCierre() {
		return this.cierre;
	}

	public boolean yaCerroEsteMes() {
		return LocalDateTime.now().getDayOfMonth() >= this.getCierre().getDayOfMonth();
	}

	public void agregarCompra(String nombreProducto, int cuotas, float valorTotal, int mes,
			int dia, Boolean compradoDespuesDelVencimiento) {
		productos.add(new Producto(nombreProducto, this, cuotas, valorTotal, mes, dia,
				this.fueCompradoDespuesDelVencimiento(dia, mes)));

	}

	public boolean fueCompradoDespuesDelVencimiento(int diaAgregado, int mesAgregado) {
		LocalDateTime diaDeCompra = LocalDateTime.of(LocalDateTime.now().getYear(), mesAgregado, diaAgregado, 0, 0);
		LocalDateTime diaVencimientoTarjeta = LocalDateTime.of(LocalDateTime.now().getYear(),
				this.getCierre().getMonthValue(), this.getCierre().getDayOfMonth(), 0, 1);

		return diaDeCompra.isAfter(diaVencimientoTarjeta);
	}

	public String toString() {
		return this.getNombre();
	}

	public Tarjeta(String nombreTarjeta) {
		this.nombre = nombreTarjeta;
	}

	public void setCierre(int dia, int mes) {
		this.cierre = LocalDateTime.of(LocalDateTime.now().getYear(), mes, dia, 1, 1);
	}

}