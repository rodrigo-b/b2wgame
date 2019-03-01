package br.com.b2w.b2wgame.resource;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.b2wgame.client.ws.json.PlanetJson;
import br.com.b2w.b2wgame.service.swapi.SwapiService;

@RestController
@RequestMapping("/swapi")
public class SwapiResource {

	@Autowired
	SwapiService swapiService;
	
	@GetMapping
	public ResponseEntity <List<PlanetJson>> findSwapiPlanets() throws IOException{
		
		List<PlanetJson> allPlanets = swapiService.returnAllPlanets();
		return new ResponseEntity<>(allPlanets,HttpStatus.OK);
	}
	
}
