package br.com.b2w.b2wgame.service.swapi;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b2w.b2wgame.client.ws.SwapiImpl;
import br.com.b2w.b2wgame.client.ws.json.ModelPlanetJsonList;
import br.com.b2w.b2wgame.client.ws.json.PlanetJson;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class SwapiService {

	
	public List<PlanetJson> returnAllPlanets() throws IOException{
		
		Call<ModelPlanetJsonList<PlanetJson>> allPlanets = SwapiImpl.getApi().getAllPlanets();
		Response<ModelPlanetJsonList<PlanetJson>> response = allPlanets.execute();
		ModelPlanetJsonList<PlanetJson> data = response.body();
		return data.results;
		
	}
	
	public Integer getTimesInMovies(String name) throws IOException {
		
		try {
			 Stream<PlanetJson> stream = returnAllPlanets().stream();
			 
			 PlanetJson planetJson = stream.filter(u -> u.getName().equals(name))
					 				       .findFirst()
					 				       .get();
			 
			 return planetJson.getFilmsUrls().size();
			 
		}catch(NoSuchElementException e) {
			return 0;
		}
		 
	}
	
	
}
