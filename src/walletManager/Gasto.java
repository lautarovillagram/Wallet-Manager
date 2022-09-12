package walletManager;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Gasto implements Serializable {
	private String descripcion;
	private float monto;
	private Cuenta metodoDePago;
	private LocalDateTime fecha;
	
	
	public Gasto(String desc, float amount, Cuenta metodo) {
		this.descripcion = desc;
		this.monto = amount;
		this.metodoDePago = metodo;
		this.fecha = LocalDateTime.now();
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public Cuenta getMetodoDePago() {
		return this.metodoDePago;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}

	public String getDiaYMes() {
		return Integer.toString(getFecha().getDayOfMonth()) + "/" + Integer.toString(getFecha().getMonthValue());
	}
}