package br.com.b2w.b2wgame.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Table(name="planet")
@JsonRootName(value = "Planet")
public class Planet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String terrain;
	
	private String climate;
	
	@Column(name="times_in_movie")
	private Integer  timesInMovie;
	
	public Planet() {
	}

	public Planet(String name, String climate, String terrain) {

		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	
	}

	public Planet(Long id) {
		this.id = id;
	}

	public Planet(String name) {
		this.name = name;
	}

	public Planet(Long id, String name, String climate, String terrain, Integer timesInMovie) {
		this.id = id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.timesInMovie = timesInMovie;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTerrain() {
		return terrain;
	}

	public String getClimate() {
		return climate;
	}

	public Integer getTimesInMovie() {
		return timesInMovie;
	}

	public void setTimesInMovie(Integer timesInMovie) {
		this.timesInMovie = timesInMovie;
	}
	
	
	
}
