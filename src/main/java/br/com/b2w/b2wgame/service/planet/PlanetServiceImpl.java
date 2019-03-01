package br.com.b2w.b2wgame.service.planet;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.b2w.b2wgame.model.Planet;
import br.com.b2w.b2wgame.repository.PlanetRepository;
import br.com.b2w.b2wgame.service.planet.exception.PlanetExistsException;
import br.com.b2w.b2wgame.service.planet.exception.PlanetNotFoundException;

@Service
public class PlanetServiceImpl implements PlanetService{

	private final PlanetRepository planetRepository;
	
	public PlanetServiceImpl(PlanetRepository planetRepository){
		this.planetRepository = planetRepository;
	}
	
	public Planet create(Planet planet) throws PlanetExistsException {
		
		Optional<Planet> optional = planetRepository.findByName(planet.getName());
		
		if(optional.isPresent()) {
			throw new PlanetExistsException("Planet with name " + planet.getName() + " already exists");
		}
		
		Planet newPlanet = new Planet(planet.getId(),
								   planet.getName(),
								   planet.getClimate(),
								   planet.getTerrain(),
								   planet.getTimesInMovie());
		
		return planetRepository.save(planet);
	}
	
	public void delete(Planet planet) throws PlanetNotFoundException{
		
		Optional<Planet> optional = planetRepository.findByName(planet.getName());
		
		if(optional.isPresent()) {
			throw new PlanetNotFoundException("Not exist planet with name: " + planet.getName());
		}
		
		Planet newPlanet = new Planet(planet.getId(),
				   planet.getName(),
				   planet.getClimate(),
				   planet.getTerrain(),
				   planet.getTimesInMovie());
		
		planetRepository.delete(planet);
	}
	
	public Planet findById(Planet planet) throws PlanetNotFoundException {

		final Optional<Planet> optional = planetRepository.findById(planet.getId());
	    return optional.orElseThrow(() -> new PlanetNotFoundException("Not exist planet with id: " + planet.getId()));
	}

	public Planet findByName(Planet planet) throws PlanetNotFoundException {

		final Optional<Planet> optional = planetRepository.findByName(planet.getName());
		return optional.orElseThrow(() ->  new PlanetNotFoundException("Not exist planet with name: " + planet.getName()));
	}
	
	public List<Planet> findAll(){
		return planetRepository.findAll();
	}
	
	

}