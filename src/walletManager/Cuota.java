package walletManager;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Cuota implements Serializable {
	private LocalDateTime fecha;
	private float monto;
	private String producto;
	private boolean fuePagada;
	private String numeroDeCuota;

	public boolean getFuePagada() {
		return this.fuePagada;
	}

	public void setFuePagada(Boolean pagada) {
		this.fuePagada = pagada;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getNumeroDeCuota() {
		return numeroDeCuota;
	}

	public void setNumeroDeCuota(String numeroDeCuota) {
		this.numeroDeCuota = numeroDeCuota;
	}

	public Cuota(LocalDateTime fechaCuota, float montoCuota, String productoCuota, int numeroDeCuota, int cantidadDeCuotas) {
		this.setFecha(fechaCuota);
		this.setMonto(montoCuota);
		this.setProducto(productoCuota);
		this.setFuePagada(false);
		this.setNumeroDeCuota(numeroDeCuota + "/" + cantidadDeCuotas );
	}

	public void pagarCuota() {
		this.setFuePagada(true);
	}

}
