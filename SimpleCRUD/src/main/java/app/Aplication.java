package app;

import static spark.Spark.*;
import service.ClientService;

public class Aplication {

	private static ClientService clientService = new ClientService();

	public static void main(String[] args) {
		
		//API runnig on port 
		port(6790);
		
		// return fields: id,nome 
        post("/api/clientes", (request, response) -> clientService.add(request, response));
		
		// return fields: nome,endereco,celular,email,cpf 
        get("/api/clientes", (request, response) -> clientService.getAll(request, response));

		// return fields: nome,endereco,celular,email,cpf 
        get("/api/clientes/:id", (request, response) -> clientService.get(request, response));

		// return fields: id,nome
        put("/api/clientes/:id", (request, response) -> clientService.update(request, response));
		
		// return fields: id,nome
        delete("/api/clientes/:id", (request, response) -> clientService.remove(request, response));
        
	}

}
