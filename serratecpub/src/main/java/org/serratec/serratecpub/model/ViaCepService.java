package org.serratec.serratecpub.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {
    public Endereco preencherEnderecoComCep(String cep) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            Gson gson = new Gson();
            JsonObject enderecoJson = JsonParser.parseString(body).getAsJsonObject();
            
            if (enderecoJson.has("erro")) {
                throw new IllegalArgumentException("CEP inválido ou não encontrado");
            }

            Endereco endereco = new Endereco();
            endereco.setCep(cep);
            endereco.setRua(enderecoJson.get("logradouro").getAsString());
            endereco.setBairro(enderecoJson.get("bairro").getAsString());
            endereco.setCidade(enderecoJson.get("localidade").getAsString());
            endereco.setUf(enderecoJson.get("uf").getAsString());
            return endereco;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("CEP não é válido!");
            return null;
        }
    }
}

