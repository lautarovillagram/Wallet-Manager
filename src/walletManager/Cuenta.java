package walletManager;

import java.io.Serializable;

public class Cuenta implements Serializable {
	private float monto;
	private String nombre;

	public void agregarPlata(float cantidad) {
		this.setMonto(this.getMonto() + cantidad);
	}

	public void setMonto(float cantidad) {
		this.monto = cantidad;
	}

	public float getMonto() {
		return monto;
	}

	public Cuenta(String nombreCuenta) {
		this.nombre = nombreCuenta;
		this.monto = 0;
	}

	public String toString() {
		return nombre;
	}

}
