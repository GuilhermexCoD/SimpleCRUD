package app;

import static spark.Spark.*;
import spark.Filter;
import spark.Spark;
import service.ClientService;

public class Aplication {

	private static ClientService clientService = new ClientService();

	public static void main(String[] args) {

		// API runnig on port
		port(6790);

		options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "OK";
		});

		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

		// return fields: id,nome
		Spark.post("/api/clientes", (request, response) -> clientService.add(request, response));

		// return fields: nome,endereco,celular,email,cpf
		Spark.get("/api/clientes", (request, response) -> clientService.getAll(request, response));

		// return fields: nome,endereco,celular,email,cpf
		Spark.get("/api/clientes/:id", (request, response) -> clientService.get(request, response));

		// return fields: id,nome
		Spark.put("/api/clientes/:id", (request, response) -> clientService.update(request, response));

		// return fields: id,nome
		Spark.delete("/api/clientes/:id", (request, response) -> clientService.remove(request, response));

	}

}
