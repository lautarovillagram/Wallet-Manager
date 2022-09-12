package walletManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GastoEnCuotas implements Serializable {
	private ArrayList<Tarjeta> tarjetas = new ArrayList<>();
	private List<Producto> productosAcumulados;

	public List<Tarjeta> getTarjetas() {
		return this.tarjetas;
	}

	public List<Producto> getComprasDe(String tarjetaAVer) {
		Tarjeta tarjetaSeleccionada = null;
		for (Tarjeta tarjeta : this.getTarjetas()) {
			if (tarjeta.toString() == tarjetaAVer) {
				tarjetaSeleccionada = tarjeta;
			}

		}
		return tarjetaSeleccionada.getProductos();

	}

	public List<Producto> getTodosLosProductos() {
		List <Producto> productosAcumulados = new ArrayList();
		for (Tarjeta tarjeta : this.getTarjetas()) {
			productosAcumulados.addAll(tarjeta.getProductos());
		}
		return productosAcumulados;
	}

	public Producto getProducto(String nombreProducto) {
		Producto productoSeleccionado = null;
		for (Producto producto : this.getTodosLosProductos()) {
			if (producto.toString() == nombreProducto) {
				productoSeleccionado = producto;
			}
		}

		return productoSeleccionado;

	}

	public List<Producto> getTodosLosProductosConCuotaEn(int mes) {
		List<Producto> productosConCuotaEnEsteMes = new ArrayList<>();
		for (Producto producto : this.getTodosLosProductos()) {
			for (Cuota cuota : producto.getCuotas()) {
				if (cuota.getFecha().getMonthValue() == mes) {
					productosConCuotaEnEsteMes.add(producto);
				}
			}
		}
		return productosConCuotaEnEsteMes;
	}

	public List<Producto> getLosProductosDelaTarjetaConCuotaEn(String tarjetaConsultada, int mes) {
		List<Producto> productosConCuotaEnEsteMes = new ArrayList<>();

		for (Producto producto : this.getTarjetaSeleccionada(tarjetaConsultada).getProductos()) {
			for (Cuota cuota : producto.getCuotas()) {
				if (cuota.getFecha().getMonthValue() == mes) {
					productosConCuotaEnEsteMes.add(producto);
				}
			}

		}

		return productosConCuotaEnEsteMes;

	}

	public float getMontoAPagarEnElMesTodasLasTarjetas(int mes) {
		Float montoTotal = null;
		for (Producto producto : this.getTodosLosProductosConCuotaEn(mes)) {
			montoTotal = montoTotal + producto.getValorCuota();
		}

		return montoTotal;
	}

	public Tarjeta getTarjetaSeleccionada(String tarjetaASeleccionar) {
		Tarjeta tarjetaSeleccionada = null;
		for (Tarjeta tarjeta : this.getTarjetas()) {
			if (tarjeta.toString() == tarjetaASeleccionar) {
				tarjetaSeleccionada = tarjeta;
			}

		}

		return tarjetaSeleccionada;

	}

	public float getMontoAPagarEnElMesDeTarjetaEspecifica(String tarjetaConsultada, int mes) {
		Float montoTotal = null;
		for (Producto producto : this.getLosProductosDelaTarjetaConCuotaEn(tarjetaConsultada, mes)) {
			montoTotal = montoTotal + producto.getValorCuota();
		}

		return montoTotal;
	}
	
	

}
