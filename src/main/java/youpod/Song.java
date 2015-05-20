package youpod;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; //necesario para la clave primaria
	
	private String title;
	private String author;
	private String type;
	private long duration;
	
	public Song() {
	}
	
	public Song(String title, String author, long duration) {
		this.title = title;
		this.author = author;
		this.duration = duration;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getType() {
		return type;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public long getId() {
		return id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public void setId (long id) {
		this.id = id;
	}

}
