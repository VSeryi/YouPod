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
	private ArrayList<Integer> friends;
	private ArrayList<Integer> nacional;
	private ArrayList<Integer> internacional;
	private ArrayList<Integer> bs;
	private String facebookId;
	private String googleId;

	public User() {
		friends = new ArrayList<>();
		nacional = new ArrayList<>();
		internacional = new ArrayList<>();
		bs = new ArrayList<>();
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(String socialId, int type) {
		super();
		if (type == 0) {
			this.googleId = socialId;
		} else {
			this.facebookId = socialId;
		}
	}

	public ArrayList<Integer> getNacional() {
		return nacional;
	}

	public void setNacional(ArrayList<Integer> nacional) {
		this.nacional = nacional;
	}

	public ArrayList<Integer> getInternacional() {
		return internacional;
	}

	public void setInternacional(ArrayList<Integer> internacional) {
		this.internacional = internacional;
	}

	public ArrayList<Integer> getBs() {
		return bs;
	}

	public void setBs(ArrayList<Integer> bs) {
		this.bs = bs;
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

	

	public Iterable<Integer> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<Integer> friends) {
		this.friends = friends;
	}

	public boolean addMusicNacional(Integer music) {
		return this.nacional.add(music);
	}
	
	public boolean addMusicInter(Integer music) {
		return this.internacional.add(music);
	}
	public boolean addMusicBs(Integer music) {
		return this.bs.add(music);
	}
	
	public boolean addFriend(Integer friend) {
		return this.friends.add(friend);
	}
	
	public boolean removeFriend(Integer friend) {
		return this.friends.remove(friend);
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

}