package cargarGuardar;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import walletManager.Wallet;

public class CargarWallet {

	public Wallet wallet = new Wallet();

	String fileName = "C:\\Users\\Lauta\\Documents\\billetera\\wallet.bin";

	public void cargar() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);

			Object o;

			while (true) {
				try {
					o = ois.readObject();
					if (o instanceof Wallet) {
						wallet = (Wallet) o;
					}

				} catch (EOFException ex) {
					break;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
