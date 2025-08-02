package Principal;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Converter {


    HttpClient client = HttpClient.newHttpClient();
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    public double convertir(String pair, double monto) throws Exception {
        // Construir URL
        String url = "https://v6.exchangerate-api.com/v6/900e03737a000c764ce3913b/pair"+pair+monto;

        // Crear solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Enviar solicitud y obtener respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Error al consultar la API: " + response.statusCode());
        }

        // Parsear JSON
        JsonObject json = gson.fromJson(response.body(), JsonObject.class);
        double tasa = json.get("conversion_result").getAsDouble();

        // Calcular y devolver resultado
        return tasa;
    }
}