package br.com.b2w.b2wgame.client.ws.json;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetJson implements Serializable {
    public String name;
    public String climate;
    public String terrain;

    @JsonProperty("films")
    public List<String> filmsUrls;

	public List<String> getFilmsUrls() {
		return filmsUrls;
	}

	public String getName() {
		return name;
	}
	
}
