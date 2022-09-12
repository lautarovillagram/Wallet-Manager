package walletManager;

import java.io.Serializable;

public class Viatico implements Serializable {
	private float valor;
	private String fecha;
	public boolean isFueCobrado() {
		return fueCobrado;
	}


	public void setFueCobrado(boolean fueCobrado) {
		this.fueCobrado = fueCobrado;
	}





	private boolean fueCobrado;
	
	
	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	
	
	public Viatico(float monto, String date) {
		this.valor = monto;
		this.fecha = date;
	}

}

