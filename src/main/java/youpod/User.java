package youpod;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;
	private String password;
	private boolean activate;
	private ArrayList<Integer> friends;
	private ArrayList<Integer> music;

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}

	public Iterable<Integer> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<Integer> friends) {
		this.friends = friends;
	}

	public Iterable<Integer> getMusic() {
		return music;
	}

	public boolean addMusic(Integer music) {
		return this.music.add(music);
	}
	
	
}