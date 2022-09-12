package walletManager;

import java.io.IOException;

import cargarGuardar.CargarWallet;
import cargarGuardar.GuardarWallet;
import walletManager.Producto;
import walletManager.Tarjeta;

public class main {

	public static void main(String[] args) throws IOException {
		Wallet wallet = new Wallet();
		GuardarWallet guardar = new GuardarWallet();
		CargarWallet cargar = new CargarWallet();
		
/*
		Cuenta nacion = new Cuenta("Nacion");
		Cuenta lemon = new Cuenta("Lemon");
		Cuenta prex = new Cuenta("Prex");
		Cuenta uala = new Cuenta("Uala");
		Cuenta reba = new Cuenta("Reba");
		Cuenta brubank = new Cuenta("Brubank");
		Cuenta mercadopago = new Cuenta("MercadoPago");
		Cuenta efectivo = new Cuenta("Efectivo");
		
		wallet.getCuentas().add(nacion);
		wallet.getCuentas().add(lemon);
		wallet.getCuentas().add(prex);
		wallet.getCuentas().add(uala);
		wallet.getCuentas().add(reba);
		wallet.getCuentas().add(mercadopago);
		wallet.getCuentas().add(brubank);
		wallet.getCuentas().add(efectivo);
		Tarjeta nativaVisa = new Tarjeta("nativa Visa");
		Tarjeta rebaAmex = new Tarjeta("Reba AmEx");

		rebaAmex.setCierre(22, 9);
		nativaVisa.setCierre(8, 9);
		wallet.getTarjetasDeCredito().add(nativaVisa);
		wallet.getTarjetasDeCredito().add(rebaAmex);

		Producto gpu = new Producto("gpu", nativaVisa, 12, 89314.90f, 5, 30, true);
		nativaVisa.getProductos().add(gpu);
		Producto pandora = new Producto("pandora", rebaAmex, 3, 18700f, 7, 2, false);
		rebaAmex.getProductos().add(pandora);
		Producto giftcard = new Producto("giftcard", nativaVisa, 3, 10000f, 8, 25, true);
		Producto remeras = new Producto("remeras", nativaVisa, 6, 7675.42f, 8, 8, false);
		Producto dominio = new Producto("dominio", nativaVisa, 1, 475f, 8, 22, true);
		nativaVisa.getProductos().add(remeras);
		nativaVisa.getProductos().add(giftcard);
		nativaVisa.getProductos().add(dominio);
		pandora.getCuotas().get(0).setFuePagada(true);	
		pandora.getCuotas().get(1).setFuePagada(true);	
		gpu.getCuotas().get(0).setFuePagada(true);
		gpu.getCuotas().get(1).setFuePagada(true);
		gpu.getCuotas().get(2).setFuePagada(true);
		remeras.getCuotas().get(0).setFuePagada(true);
		
		giftcard.setFueCompradoDespuesDeCerrarLaTarjeta(true);
		dominio.setFueCompradoDespuesDeCerrarLaTarjeta(true);
		gpu.setFueCompradoDespuesDeCerrarLaTarjeta(true);
	*/
		
		try {
			cargar.cargar();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wallet = cargar.wallet;
		wallet.setDolares(wallet.getDolares() -100);
		guardar.wallet = wallet;
		guardar.guardar();
		
	
		
	}

}
