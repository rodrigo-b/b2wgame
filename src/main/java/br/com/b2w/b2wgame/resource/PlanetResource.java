package br.com.b2w.b2wgame.resource;



import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.b2w.b2wgame.model.Planet;
import br.com.b2w.b2wgame.repository.PlanetRepository;
import br.com.b2w.b2wgame.service.planet.PlanetService;
import br.com.b2w.b2wgame.service.planet.exception.PlanetExistsException;
import br.com.b2w.b2wgame.service.planet.exception.PlanetNotFoundException;
import br.com.b2w.b2wgame.service.planet.exception.helper.ErrorHelper;
import br.com.b2w.b2wgame.service.swapi.SwapiService;

@RestController
@RequestMapping("/planets")
public class PlanetResource{

	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private PlanetService planetService;
	
	@Autowired
	private SwapiService swapiService;
	
	@PostMapping
	public ResponseEntity create(@RequestBody Planet planet, HttpServletResponse response) throws Exception {
	
		Integer timesInMovies = swapiService.getTimesInMovies(planet.getName());
		planet.setTimesInMovie(timesInMovies);
		
		final Planet createdPlanet = planetService.create(planet);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id/{id}")
				.buildAndExpand(planet.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return new ResponseEntity<>(planet,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity findById(@PathVariable("id") Long id) throws Exception {
		Planet planet = new Planet(id);
		Planet planetReturned = planetService.findById(planet);
		return new ResponseEntity<Planet>(planetReturned, HttpStatus.OK);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity findByName(@PathVariable("name") String name)throws Exception{
		Planet planet = new Planet(name);
		Planet planetReturned = planetService.findByName(planet);
		return new ResponseEntity<Planet>(planetReturned, HttpStatus.OK);
	}	

	@GetMapping("/all")
	public ResponseEntity <List<Planet>>findAll(){
		List<Planet> planets = planetRepository.findAll();
		return new ResponseEntity<>(planets,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete (@PathVariable("id") Long id) throws PlanetNotFoundException{
		Planet planet = new Planet(id);
		planetService.delete(planet);
		return new ResponseEntity<>("Planeta deletado com sucesso", HttpStatus.OK);
	}
	
	@ExceptionHandler({PlanetNotFoundException.class})
	public ResponseEntity<ErrorHelper> handlePlanetNotFoundException(PlanetNotFoundException e){
		return new ResponseEntity<>(new ErrorHelper(e.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({PlanetExistsException.class})
	public ResponseEntity<ErrorHelper> handlePlanetExistsException(PlanetExistsException e){
		return new ResponseEntity<>(new ErrorHelper(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
