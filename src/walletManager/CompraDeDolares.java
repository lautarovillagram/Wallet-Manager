package walletManager;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CompraDeDolares implements Serializable {
	private LocalDateTime fecha;
	private float montoDolares;
	private float montoPesos;
	private TipoTransaccion tipoDeTransaccion;

	public LocalDateTime getFecha() {
		return this.fecha;
	}

	public float getMontoDolares() {
		return this.montoDolares;
	}

	public float getMontoPesos() {
		return this.montoPesos;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public void setMontoDolares(float montoDolares) {
		this.montoDolares = montoDolares;
	}

	public void setMontoPesos(float montoPesos) {
		this.montoPesos = montoPesos;
	}

	public CompraDeDolares(float montoDolares, float montoPesos, TipoTransaccion tipo) {
		super();
		this.fecha = LocalDateTime.now();
		this.montoDolares = montoDolares;
		this.montoPesos = montoPesos;
		this.tipoDeTransaccion = tipo;
	}

	public float getCotizacionAlMomentoDeLaCompra() {
		return getMontoPesos() / getMontoDolares();
	}

	public TipoTransaccion getTipoDeTransaccion() {
		return this.tipoDeTransaccion;
	}

	public void setTipoDeTransaccion(TipoTransaccion tipo) {
		this.tipoDeTransaccion = tipo;
	}

}
