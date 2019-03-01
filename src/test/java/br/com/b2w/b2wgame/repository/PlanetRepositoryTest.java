package br.com.b2w.b2wgame.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.b2wgame.model.Planet;





@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PlanetRepositoryTest {

	@Autowired
	private PlanetRepository sut;
	
	@Test	
	public void should_find_planet_by_id(){
		
		Optional<Planet> optional = sut.findById(1L);
		assertThat(optional.isPresent()).isTrue();
		
		Planet planet = optional.get();
		
		assertThat(planet.getName()).isEqualTo("Alderaan");
		assertThat(planet.getClimate()).isEqualTo("temperate");
		assertThat(planet.getTerrain()).isEqualTo("grasslands, mountains");
		assertThat(planet.getTimesInMovie()).isEqualTo(2);
		assertThat(planet.getId()).isEqualTo(1L);
	}
	
	@Test 
	public void should_not_find_planet_by_id(){
		Optional<Planet> optional = sut.findById(9999999L);
		assertThat(optional.isPresent()).isFalse();
	}
	
	@Test
	public void should_find_planet_by_name(){
		
		Optional<Planet> optional = sut.findByName("Alderaan");
		assertThat(optional.isPresent()).isTrue();
		
		Planet planet = optional.get();
		assertThat(planet.getName()).isEqualTo("Alderaan");
		assertThat(planet.getClimate()).isEqualTo("temperate");
		assertThat(planet.getTerrain()).isEqualTo("grasslands, mountains");
		assertThat(planet.getTimesInMovie()).isEqualTo(2);
		assertThat(planet.getId()).isEqualTo(1L);
	}
	
	@Test
	public void should_not_find_planet_by_name(){
		
		Optional<Planet> optional = sut.findByName("marte");
		assertThat(optional.isPresent()).isFalse();
		
	}
	
	
}
