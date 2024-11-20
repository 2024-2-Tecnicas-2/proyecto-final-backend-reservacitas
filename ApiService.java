package com.mycompany.citasapp;

import okhttp3.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

public class ApiService {

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    private static final String BASE_URL = "https://api.tuapp.com/citas";

   
    public List<Cita> obtenerCitas() throws IOException {
        Request request = new Request.Builder()
            .url(BASE_URL)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Error en la respuesta: " + response);

            String responseBody = response.body().string();
          
            return gson.fromJson(responseBody, new TypeToken<List<Cita>>() {}.getType());
        }
    }


    public String crearCita(Cita cita) throws IOException {
        String json = gson.toJson(cita);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        
        Request request = new Request.Builder()
            .url(BASE_URL)
            .post(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Error en la respuesta: " + response);
            return response.body().string();
        }
    }

  
    public String eliminarCita(int idCita) throws IOException {
        String url = BASE_URL + "/" + idCita; 

        Request request = new Request.Builder()
            .url(url)
            .delete()
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Error en la respuesta: " + response);
            return "Cita eliminada exitosamente"; 
        }
    }
}