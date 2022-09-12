package walletManager;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransferenciaEntreCuentas implements Serializable{
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private float montoTransferencia;
	private LocalDateTime fecha;
	
	public TransferenciaEntreCuentas(Cuenta origen, Cuenta destino, float monto) {
		this.cuentaOrigen = origen;
		this.cuentaDestino = destino;
		this.montoTransferencia = monto;
		fecha = LocalDateTime.now();
	}

	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public float getMontoTransferencia() {
		return montoTransferencia;
	}

	public void setMontoTransferencia(float montoTransferencia) {
		this.montoTransferencia = montoTransferencia;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	

}
