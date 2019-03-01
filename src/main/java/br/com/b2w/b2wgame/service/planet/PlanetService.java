package br.com.b2w.b2wgame.service.planet;


import java.util.List;

import br.com.b2w.b2wgame.model.Planet;
import br.com.b2w.b2wgame.service.planet.exception.PlanetExistsException;
import br.com.b2w.b2wgame.service.planet.exception.PlanetNotFoundException;

public interface PlanetService {

	Planet findById(Planet planet) throws PlanetNotFoundException;
	Planet findByName(Planet planet) throws PlanetNotFoundException;
	Planet create(Planet planet) throws PlanetExistsException;
	void  delete(Planet planet) throws PlanetNotFoundException;
	List<Planet> findAll();
	
}
