package walletManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wallet implements Serializable {

	public Wallet() {
		super();
	}

	public List<Cuenta> cuentas = new ArrayList<>();
	public float dolares;
	private float ultimoValorDolar = 0;

	private List<Gasto> gastos = new ArrayList<>();
	private List<Viatico> viaticos = new ArrayList<>();
	private List<Viatico> viaticosCobrados = new ArrayList<>();
	private List<Ingreso> ingresos = new ArrayList<>();
	private List<Tarjeta> tarjetasDeCredito = new ArrayList<>();
	private List<CompraDeDolares> comprasDeDolares = new ArrayList<>();
	private List<TransferenciaEntreCuentas> transferencias = new ArrayList<>();

	public List<TransferenciaEntreCuentas> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(List<TransferenciaEntreCuentas> transferencias) {
		this.transferencias = transferencias;
	}

	public List<Tarjeta> getTarjetasDeCredito() {
		return this.tarjetasDeCredito;
	}

	public void setTarjetasDeCredito(List<Tarjeta> tarjetasDeCredito) {
		this.tarjetasDeCredito = tarjetasDeCredito;
	}

	public void setViaticos(List<Viatico> viaticosFiltrados) {
		viaticos = viaticosFiltrados;
	}

	public float getUltimoValorDolar() {
		return ultimoValorDolar;
	}

	public void setUltimoValorDolar(float ultimoValorDolar) {
		this.ultimoValorDolar = ultimoValorDolar;
	}

	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public float getDolares() {
		return dolares;
	}

	public void setDolares(float dolares) {
		this.dolares = dolares;
	}

	public List<CompraDeDolares> getComprasDeDolares() {
		return comprasDeDolares;
	}

	public void setComprasDeDolares(List<CompraDeDolares> comprasDeDolares) {
		this.comprasDeDolares = comprasDeDolares;
	}

	public void agregarGasto(String descripcion, float monto, Cuenta metodo) {
		this.getGastos().add(0, new Gasto(descripcion, monto, metodo));
	}

	public List<Gasto> getGastos() {
		return this.gastos;
	}

	public void agregarViatico(float monto, String fecha) {
		this.getViaticos().add(new Viatico(monto, fecha));

	}

	public List<Viatico> getViaticos() {
		return this.viaticos;
	}

	public void viaticoCobrado(float valor) {
		Viatico viaticoConElMismoValor = null;
		for (Viatico viatico : viaticos) {
			if (viatico.getValor() == valor) {
				viaticoConElMismoValor = viatico;
			}
		}
		this.getViaticos().remove(viaticoConElMismoValor);
	}

	public void cobrarSueldo(float valor) {
		// this.getNacion().setMonto(this.getNacion().getMonto() + valor);
		System.out.println("como me gusta la platita");
	}

	public void pasarA(Cuenta remitente, Cuenta destino, float cantidad) {
		remitente.setMonto(remitente.getMonto() - cantidad);
		destino.setMonto(destino.getMonto() + cantidad);
		System.out.println("transferencia exitosa");
	}

	public void compraDeDolares(float cantidad, float pesos) {
		this.setDolares(this.getDolares() + cantidad);
		// nacion.setMonto(nacion.getMonto() - pesos);
		System.out.println("compraste " + cantidad + " dolares a " + pesos / cantidad);
	}

	public double totalEnCuentas() {
		return this.getCuentas().stream().mapToDouble(c -> c.getMonto()).sum();
	}

	public List<Gasto> gastosUltimos30Dias() {
		return this.getGastos().stream().filter(g -> g.getFecha().isAfter(LocalDateTime.now().minusDays(30))).toList();
	}

	public void agregarCuentaNueva(Cuenta cuenta) {
		cuentas.add(cuenta);
	}

	public List<Ingreso> ingresosUltimos30Dias() {
		return this.getIngresos().stream().filter(i -> i.getFecha().isAfter(LocalDateTime.now().minusDays(30)))
				.toList();
	}

	public List<Ingreso> getIngresos() {
		return ingresos;
	}

	public void agregarIngreso(String concepto, float monto, Cuenta cuenta) {
		ingresos.add(0, new Ingreso(concepto, monto, cuenta));

	}

	public void restarDolares(float cantidad) {
		this.setDolares(this.getDolares() - cantidad);

	}

	public void agregarCompraDeDolares(float cantidadDolares, float cantidadPesos, TipoTransaccion tipo) {
		this.getComprasDeDolares().add(new CompraDeDolares(cantidadDolares, cantidadPesos, tipo));
	}

	public float dolaresAPesos() {
		return this.getDolares() * this.getUltimoValorDolar();
	}

	public List<Producto> getComprasDe(String tarjetaAVer) {
		Tarjeta tarjetaSeleccionada = null;
		for (Tarjeta tarjeta : this.getTarjetasDeCredito()) {
			if (tarjeta.toString() == tarjetaAVer) {
				tarjetaSeleccionada = tarjeta;
			}

		}
		return tarjetaSeleccionada.getProductos();

	}

	public List<Producto> getTodosLosProductos() {
		List<Producto> productosAcumulados = new ArrayList();
		for (Tarjeta tarjeta : this.getTarjetasDeCredito()) {
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

	public List<Producto> getLosProductosDelaTarjetaConCuotaEn(Tarjeta tarjetaConsultada, int mes) {
		List<Producto> productosConCuotaEnEsteMes = new ArrayList<>();

		for (Producto producto : tarjetaConsultada.getProductos()) {
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

	public float getMontoAPagarEnElMesDeTarjetaEspecifica(Tarjeta tarjetaConsultada, int mes) {
		Float montoTotal = null;
		for (Producto producto : this.getLosProductosDelaTarjetaConCuotaEn(tarjetaConsultada, mes)) {
			montoTotal = montoTotal + producto.getValorCuota();
		}

		return montoTotal;
	}

	public List<Producto> productosConDeuda() {
		return this.getTodosLosProductos().stream().filter(p -> !p.sePagaronTodasLasCuotas()).toList();

	}

	public List<TransferenciaEntreCuentas> getTransferenciasUltimos30Dias() {
		return this.getTransferencias().stream().filter(t -> t.getFecha().isAfter(LocalDateTime.now().minusDays(30)))
				.toList();

	}
	
	public double montoViaticosACobrar() {
		return this.viaticosACobrar().stream().mapToDouble(v -> v.getValor()).sum();
	}
	
	public double montoViaticosCobrados() {
		return this.viaticosCobrados().stream().mapToDouble(v -> v.getValor()).sum();
	}

	public List<Viatico> viaticosACobrar() {
		return this.getViaticos().stream().filter(v -> v.isFueCobrado() == false).toList();
	}

	public List<Viatico> viaticosCobrados() {
		return this.getViaticos().stream().filter(v -> v.isFueCobrado() == true).toList();
	}
}
