package youpod;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")

public class UserRestController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable int id) {
		return userRepository.findOne(id);
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User usuario) {
		System.out.println("Usuario: " + usuario.getEmail());
		System.out.println("Password: " + usuario.getPassword());
		if (userRepository.findByEmail(usuario.getEmail()).isEmpty()){
			System.out.println("guardado");
			userRepository.save(usuario);
			return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		}else{
			System.out.println("no guardado");
			return new ResponseEntity<>(usuario, HttpStatus.NOT_ACCEPTABLE);
		}
		}
	
	@RequestMapping(value = "/facebook",method = RequestMethod.POST)
	public ResponseEntity<User> addFacebook(@RequestBody String json) {
		User facebook = new User(readClientId(json),1);
		String clientId = readClientId(json);
		if (userRepository.findByFacebookId(clientId).isEmpty()) {
			System.out.println("guardaduco ole");
			userRepository.save(facebook);
			return new ResponseEntity<>(facebook, HttpStatus.CREATED);
		}
		else {
			System.out.println("no guardaduco");
			return new ResponseEntity<>(facebook, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value = "/google",method = RequestMethod.POST)
	public ResponseEntity<User> addGoogle(@RequestBody String json) {
		User google = new User(readClientId(json),0);
		String clientId = readClientId(json);
		if (userRepository.findByGoogleId(clientId).isEmpty()) {
			System.out.println("Reven");
			userRepository.save(google);
			return new ResponseEntity<>(google, HttpStatus.CREATED);
		}
		else  {
			System.out.println("No reven no party");
			return new ResponseEntity<>(google, HttpStatus.NOT_ACCEPTABLE);
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
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	private String readClientId(String json){
	      JSONObject jsonDownload = new JSONObject(json);
	      return (String) jsonDownload.get("clientId");
		
	}
	
}
