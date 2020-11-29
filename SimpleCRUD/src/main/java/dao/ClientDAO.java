package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Client;


public class ClientDAO {
	private List<Client> clients;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public ClientDAO(String filename) throws IOException {

		file = new File(filename);
        clients = new ArrayList<Client>();
        //TODO if the files doesnt exists
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(Client client) {
		try {
			clients.add(client);
			this.saveToFile();
		} catch (Exception e) {
            String error = String.format("ERRO ao gravar Cliente '%s' '%s' no disco!",client.getId(),client.getNome());
			System.out.println(error);
		}
	}

	public Client get(String id) {
        Client resp = null;
		for (Client client : clients) {
			if (id == client.getId()) {
				resp = client;
			}
		}
		return resp;
	}

	public void update(Client client) {
		int index = clients.indexOf(client);
		if (index != -1) {
			clients.set(index, client);
			this.saveToFile();
		}
	}

	public void remove(Client client) {
		int index = clients.indexOf(client);
		if (index != -1) {
			clients.remove(index);
			this.saveToFile();
		}
	}

	public List<Client> getAll() {
		return clients;
	}

	private List<Client> readFromFile() {
		clients.clear();
		Client client = null;
        try (FileInputStream fis = new FileInputStream(file);
        
            ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				client = (Client) inputFile.readObject();
				clients.add(client);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler clientes do disco!");
			e.printStackTrace();
		}
		return clients;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Client client : clients) {
				outputFile.writeObject(client);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar clientes no disco!");
			e.printStackTrace();
		}
	}

	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			this.saveToFile();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao salvar a base de dados no disco!");
			e.printStackTrace();
		}
	}

}
