package youpod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Integer> getMusic(@RequestParam String email,
			@RequestParam String password) {
		List<User> users = userRepository.findByEmail(email);
		if (!users.isEmpty()) {
			if (users.get(0).getPassword().equals(password))
				return new ResponseEntity<>(users.get(0).getId(), HttpStatus.OK);
		}
		return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable int id) {
		return new ResponseEntity<>(userRepository.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/friends", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers(@PathVariable int id) {
		List<User> listaUsuarios = new ArrayList<User>();
		User usuario = userRepository.findOne(id);
		for (Integer i : usuario.getFriends()) {
			listaUsuarios.add(userRepository.findOne(i));
		}
		return new ResponseEntity<>(listaUsuarios, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}/add", method = RequestMethod.POST)
	public ResponseEntity<Boolean> getAdd(@PathVariable int id,
			@RequestBody User user) {
		System.out.println("EMPEZAMOS WIIIIIIII");
		User usuarioyo = userRepository.findOne(id);
		List<User> usuarios = userRepository.findByEmail(user.getEmail());
		System.out.println(user.getEmail());
		System.out.println(userRepository.findByEmail(user.getEmail()));
		if (usuarios.isEmpty()) {
			System.out.println("NOT FOUND WIIIIIIII");
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		} else {
			System.out.println("AGREGAN WIIIIIIIIIIII WIIIIIIII");
			User usuarioel = usuarios.get(0);
			usuarioyo.addFriend(usuarioel.getId());
			usuarioel.addFriend(id);
			userRepository.saveAndFlush(usuarioyo);
			userRepository.saveAndFlush(usuarioel);
			return new ResponseEntity<>(true, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> getDelete(@PathVariable int id,
			@RequestBody User usuario) {
		User usuarioyo = userRepository.findOne(id);
		User usuarioel = userRepository.findOne(usuario.getId());
		usuarioyo.removeFriend(usuario.getId());
		usuarioel.removeFriend(id);
		userRepository.saveAndFlush(usuarioyo);
		userRepository.saveAndFlush(usuarioel);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> getEdit(@PathVariable int id,
			@RequestBody User usuario) {
		User usuario2 = userRepository.findOne(id);
		usuario2.setEmail(usuario.getEmail());
		usuario2.setPassword(usuario.getPassword());
		userRepository.saveAndFlush(usuario2);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User usuario) {
		System.out.println(usuario.getEmail());
		if (userRepository.findByEmail(usuario.getEmail()).isEmpty()) {
			userRepository.save(usuario);
			return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(usuario, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value = "/facebook", method = RequestMethod.POST)
	public ResponseEntity<User> addFacebook(@RequestBody String json) {
		User facebook = new User(readClientId(json), 1);
		String clientId = readClientId(json);
		if (userRepository.findByFacebookId(clientId).isEmpty()) {
			userRepository.save(facebook);
			return new ResponseEntity<>(facebook, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(facebook, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/google", method = RequestMethod.POST)
	public ResponseEntity<User> addGoogle(@RequestBody String json) {
		User google = new User(readClientId(json), 0);
		String clientId = readClientId(json);
		if (userRepository.findByGoogleId(clientId).isEmpty()) {
			userRepository.save(google);
			return new ResponseEntity<>(google, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(google, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Integer id) {
		userRepository.delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User updatedUser,
			@PathVariable Integer id) {
		updatedUser.setId(id);
		User user = userRepository.saveAndFlush(updatedUser);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	private String readClientId(String json) {
		JSONObject jsonDownload = new JSONObject(json);
		return (String) jsonDownload.get("clientId");

	}
	
	@RequestMapping(value = "/music/{id}", method = RequestMethod.POST)
	public ResponseEntity<Music> addMusic(@RequestBody Music music, @PathVariable int id) {
		User usuario = userRepository.findOne(id);
		if (music.getType() == "Nacional"){
			usuario.addMusicNacional(music.getId());
		}else{
			if (music.getType() == "Internacional"){
				usuario.addMusicInter(music.getId());
			}else{
				usuario.addMusicBs(music.getId());
			}
		}
		
		userRepository.saveAndFlush(usuario);
		return new ResponseEntity<>(music, HttpStatus.CREATED);
	}

}
