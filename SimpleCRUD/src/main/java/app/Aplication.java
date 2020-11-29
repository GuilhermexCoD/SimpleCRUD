package app;

import static spark.Spark.*;
import service.ClientService;

public class Aplication {

	private static ClientService clientService = new ClientService();

	public static void main(String[] args) {
		
		//API runnig on port 
		port(6790);
		
		// TODO Create end point register to a client
		// return fields: id,nome 
        post("/api/clientes", (request, response) -> clientService.add(request, response));
		
		// TODO Create end point for getting all clients
		// return fields: nome,endereco,celular,email,cpf 
        get("/api/clientes", (request, response) -> clientService.getAll(request, response));

		// TODO Create end point for getting client with id
		// return fields: nome,endereco,celular,email,cpf 
        get("/api/clientes/:id", (request, response) -> clientService.get(request, response));

		// TODO Create end point for updating a client with id
		// return fields: id,nome
        put("/api/clientes/:id", (request, response) -> clientService.update(request, response));
		
		// TODO Create end point for removing a client with id
		// return fields: id,nome
        delete("/api/clientes/:id", (request, response) -> clientService.remove(request, response));
        
	}

}
