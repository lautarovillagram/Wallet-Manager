package cargarGuardar;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import walletManager.Wallet;

public class GuardarWallet implements Serializable {

	public Wallet wallet = new Wallet();

	String fileName = "C:\\Users\\Lauta\\Documents\\billetera\\wallet.bin";

	public void guardar() throws IOException {
		try {

			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(wallet);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
