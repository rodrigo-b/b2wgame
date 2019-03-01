package br.com.b2w.b2wgame.client.ws;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;


public class SwapiImpl {

	private Swapi Iswapi;
    private static SwapiImpl swapiImpl;
    
    public static final String BASE_URL = "https://swapi.co/api/";
    public static final String USER_AGENT_NAME = "SWAPI-Java-Client/1.0";
    
    private SwapiImpl() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper().registerModule(new JodaModule())))
                .client(httpClientBuilder.build())
                .build();

        Iswapi = retrofit.create(Swapi.class);
    }
    
    
    public static Swapi getApi() {
        if (swapiImpl == null) {
            swapiImpl = new SwapiImpl();
        }
        return swapiImpl.Iswapi;
    }

    
    

}
