package br.com.b2w.b2wgame.client.ws;



import br.com.b2w.b2wgame.client.ws.json.ModelPlanetJsonList;
import br.com.b2w.b2wgame.client.ws.json.PlanetJson;
import retrofit2.http.GET;
public interface Swapi {

    @GET("planets/")
    retrofit2.Call<ModelPlanetJsonList<PlanetJson>> getAllPlanets();

}
