package br.com.b2w.b2wgame.client.ws.json;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelPlanetJsonList<T> implements Serializable {
    
	public List<T> results;

}
