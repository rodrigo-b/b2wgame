package br.com.b2w.b2wgame.service.planet.exception.helper;


public class ErrorHelper{
	private final String error;
	
	public ErrorHelper(String error) {
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
}