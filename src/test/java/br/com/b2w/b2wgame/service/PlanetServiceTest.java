package br.com.b2w.b2wgame.service;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.b2wgame.model.Planet;
import br.com.b2w.b2wgame.repository.PlanetRepository;
import br.com.b2w.b2wgame.service.planet.PlanetService;
import br.com.b2w.b2wgame.service.planet.PlanetServiceImpl;
import br.com.b2w.b2wgame.service.planet.exception.PlanetNotFoundException;

@RunWith(SpringRunner.class)
public class PlanetServiceTest {

	@MockBean
	private PlanetRepository planetRepository;
	
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
	
	private PlanetService sut;
	private Planet planet;
	
	private static final Long ID = 1l;
	private static final String NAME = "Alderaan";
	private static final String TERRAIN = "grasslands, mountains";
	private static final String CLIMATE = "temperate";
	private static final Integer TIMES_IN_MOVIE = 2; 
	
	@Before
	public void setUp() throws Exception{
		sut = new PlanetServiceImpl(planetRepository);
		planet = new Planet(ID,NAME,CLIMATE,TERRAIN,TIMES_IN_MOVIE);
	}
	
	@Test
	public void should_find_planet_by_id() throws Exception{
		
		when(planetRepository.findById(planet.getId())).thenReturn(Optional.of(planet));
		
		Planet planetTest = sut.findById(planet);
		
		verify(planetRepository).findById(ID);
		
		assertThat(planetTest).isNotNull();
		assertThat(planet.getName()).isEqualTo(NAME);
		
	}
	
	@Test
	public void should_not_find_planet_by_id() throws Exception{
		expectedException.expect(PlanetNotFoundException.class);
		expectedException.expectMessage("Not exist planet with id: " + planet.getId());
		
		sut.findById(planet);
	}
	
	
	
	@Test
	public void should_find_planet_by_name()throws Exception{
		
		when(planetRepository.findByName(planet.getName())).thenReturn(Optional.of(planet));
		
		Planet planetTest = sut.findByName(planet);
		
		verify(planetRepository).findByName(NAME);
		
		assertThat(planetTest).isNotNull();
		assertThat(planet.getId()).isEqualTo(ID);
		
	}
	
	@Test
	public void should_not_find_planet_by_name() throws Exception{
		expectedException.expect(PlanetNotFoundException.class);
		expectedException.expectMessage("Not exist planet with name: " + planet.getName());
		
		sut.findByName(planet);
	}
	
	
	
	@Test
	public void should_save_planet()throws Exception {
		
		sut.create(planet);
		verify(planetRepository).save(planet);
	}
	
	@Test
	public void should_delete_planet() throws Exception{
		
		sut.delete(planet);
		verify(planetRepository).delete(planet);
	}
	
	
}
