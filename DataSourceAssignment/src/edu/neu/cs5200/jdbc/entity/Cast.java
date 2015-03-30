package edu.neu.cs5200.jdbc.entity;

public class Cast {
	
	private String castId;
	private String characterName;
	private String movieId;
	private String actorId;
	
	public Cast(){
	}
	
	public Cast(String characterName, String movieId, String actorId) {
		this.characterName = characterName;
		this.movieId = movieId;
		this.actorId = actorId;
	}
	
	public String getCastId() {
		return castId;
	}

	public void setCastId(String castId) {
		this.castId = castId;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getActorId() {
		return actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
	
	

}
