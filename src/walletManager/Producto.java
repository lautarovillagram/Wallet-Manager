package walletManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Producto implements Serializable {
	private String concepto;
	private Tarjeta tarjeta;
	private int cantidadCuotas;
	private float valorCuota;
	private ArrayList<Cuota> cuotas = new ArrayList<>();
	private LocalDateTime fecha;
	private boolean compradoDespuesDeCerrarLaTarjeta;
	

	public Producto(String nombre, Tarjeta tarjetaUtilizada, int cuotas, float valorTotal, int mes, int dia,
			Boolean compradoDespuesDelVencimiento) {
		this.setFecha(LocalDateTime.of(LocalDateTime.now().getYear(), mes, dia, 1, 1));
		this.setConcepto(nombre);
		this.setTarjeta(tarjetaUtilizada);
		this.setCantidadCuotas(cuotas);
		this.setValorCuota(valorTotal / cuotas);
		this.agregarCuotasQueCorrespondan(mes, dia, cuotas, valorTotal, nombre, tarjetaUtilizada,
				compradoDespuesDelVencimiento);
		this.compradoDespuesDeCerrarLaTarjeta = compradoDespuesDelVencimiento;
	}

	public boolean fueCompradoDespuesDeCerrarLaTarjeta() {
		return compradoDespuesDeCerrarLaTarjeta;
	}

	public void setFueCompradoDespuesDeCerrarLaTarjeta(boolean vof) {
		this.compradoDespuesDeCerrarLaTarjeta = vof;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Cuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(ArrayList<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public int getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	public float getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(float valorCuota) {
		this.valorCuota = valorCuota;
	}
	
	public boolean sePagaronTodasLasCuotas() {
		List<Cuota> cuotasAbonadas = this.getCuotas().stream().filter(c -> c.getFuePagada()).toList();
		return cuotasAbonadas.size() == cantidadCuotas;
	}

	public int cantidadCuotasAPagar() {

		LocalDateTime inicioDeEsteMes = LocalDateTime.of(LocalDateTime.now().getYear(),
				LocalDateTime.now().getMonthValue(), 1, 1, 1);

//		List<Cuota> cuotasRestantes = this.getCuotas().stream().filter(c -> c.getFecha().isAfter(inicioDeEsteMes))
//				.toList();
		List<Cuota> cuotasRestantes = new ArrayList<>();
		for (Cuota cuota : this.getCuotas()) {
			if (cuota.getFecha().isAfter(inicioDeEsteMes) && !cuota.getFuePagada()) {
				cuotasRestantes.add(cuota);
			}

		}

		return cuotasRestantes.size();
	}

	public List<Cuota> getCuotasAPagar() {
		LocalDateTime inicioDeEsteMes = LocalDateTime.of(LocalDateTime.now().getYear(),
				LocalDateTime.now().getMonthValue(), 1, 1, 1);

//		List<Cuota> cuotasRestantes = this.getCuotas().stream().filter(c -> c.getFecha().isAfter(inicioDeEsteMes))
//				.toList();
		List<Cuota> cuotasRestantes = new ArrayList<>();
		for (Cuota cuota : this.getCuotas()) {
			if (cuota.getFecha().isAfter(inicioDeEsteMes) && !cuota.getFuePagada()) {
				cuotasRestantes.add(cuota);
			}

		}
		return cuotasRestantes;
	}

	public String toString() {
		return concepto;
	}

	public boolean fueCompradoDespuesDeVencerLaTarjeta(int mes, int dia) {
		return LocalDateTime
				.of(LocalDateTime.now().getYear(), this.getTarjeta().getCierre().getMonth(),
						this.getTarjeta().getCierre().getDayOfMonth(), 0, 0)
				.isAfter(LocalDateTime.of(LocalDateTime.now().getYear(), mes, dia, 1, 1));
	}

	public void agregarCuotasQueCorrespondan(int mes, int dia, int cuotas, float valorDelProducto,
			String nombreProducto, Tarjeta tarjetaUtilizada, Boolean compradoDespuesDeCerrar) {
		LocalDateTime fechaDeLaCompra = LocalDateTime.of(LocalDateTime.now().getYear(), mes, dia, 1, 1);

		if (compradoDespuesDeCerrar) {
			for (int i = 1; i <= cuotas; i++) {
				this.getCuotas().add(
						new Cuota(fechaDeLaCompra.plusMonths(i), valorDelProducto / cuotas, nombreProducto, i, cuotas));

			}
		} else {
			for (int i = 0; i < cuotas; i++) {
				this.getCuotas().add(new Cuota(fechaDeLaCompra.plusMonths(i), valorDelProducto / cuotas, nombreProducto,
						i + 1, cuotas));

			}
		}

	}

	public Cuota getCuotaCorrespondienteSiExiste(int index) {

		if (!(this.getCuotasAPagar().get(index) == null)) {
			return this.getCuotasAPagar().get(index);
		} else
			return null;
	}
}