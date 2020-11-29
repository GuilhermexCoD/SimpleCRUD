package service;

import java.util.UUID;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.json.JSONArray;

import dao.ClientDAO;
import model.Client;
import spark.Request;
import spark.Response;

public class ClientService {

    private ClientDAO dao;

	public ClientService() {
		try {
			dao = new ClientDAO("client.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {

		String nome = request.queryParams("nome");
		String endereco = request.queryParams("endereco");
		String celular = request.queryParams("celular");
		String email = request.queryParams("email");
		String cpf = request.queryParams("cpf");

        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        
		Client client = new Client(id, nome, endereco, celular, email, cpf);

		dao.add(client);

        response.status(201); // 201 Created
        
        return Integer.valueOf(id);
	}

	public Object get(Request request, Response response) {
        Object resp = null;

		String id = request.params(":id");
		
		Client client = (Client) dao.get(id);

		response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
		
        if (client != null) {
        	
            resp = client.toJson();

        } else {
            response.status(404); // 404 Not found
            response.redirect("/notfound.html");
        }

        return resp;
	}

	public Object update(Request request, Response response) {
        Object resp = null;

        String id = request.params(":id");
        
		Client client = (Client) dao.get(id);

        if (client != null) {
        	client.setNome(request.queryParams("nome"));
        	client.setEndereco(request.queryParams("endereco"));
        	client.setCelular(request.queryParams("celular"));
        	client.setEmail(request.queryParams("email"));
        	client.setCpf(request.queryParams("cpf"));

        	dao.update(client);
            
            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("nome", client.getNome());

            resp = json;
        } else {
            response.status(404); // 404 Not found
    	    response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");
            String notFoundString = String.format("Cliente ID = '%s' não encontrado",id);
            resp = ServiceException(notFoundString).add("id", id).toJson();
        }

        return resp;

	}

	public Object remove(Request request, Response response) {
        Object resp = null;

        String id = request.params(":id");
        
		Client client = (Client) dao.get(id);

        if (client != null) {

        	dao.remove(client);

            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("nome", client.getNome());

            resp = json;
            
        } else {
            response.status(404); // 404 Not found
    	    response.header("Content-Type", "application/json");
    	    response.header("Content-Encoding", "UTF-8");
            String notFoundString = String.format("Cliente ID = '%s' não encontrado",id);
            resp = ServiceException(notFoundString).add("id", id).toJson();
        }

        return resp;
	}

	public Object getAll(Request request, Response response) {
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
		JSONArray allClients = new JSONArray();
		for (Client item : dao.getAll()) {
			Client client = (Client) item;
			allClients.put(client.toJson());
		}

		return allClients;
	}

}
