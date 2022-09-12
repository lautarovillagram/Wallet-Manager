package walletManager;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ingreso implements Serializable {
	public String concepto;
	public float monto;
	public LocalDateTime fecha;
	public Cuenta cuenta;

	public Ingreso(String conceptoIngreso, float montoIngreso, Cuenta cuentaIngreso) {
		this.concepto = conceptoIngreso;
		this.monto = montoIngreso;
		this.fecha = LocalDateTime.now();
		this.cuenta = cuentaIngreso;
	}

	public String getConcepto() {
		return concepto;
	}

	public float getMonto() {
		return monto;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public String getDiaYMes() {
		return Integer.toString(getFecha().getDayOfMonth()) + "/" + Integer.toString(getFecha().getMonthValue());
	}

}
