package youpod;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
		userRepository.save(usuario);
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		}
	
	@RequestMapping(value = "/facebook",method = RequestMethod.POST)
	public ResponseEntity<User> addFacebook(@RequestBody String json) {
		User facebook = new User(readClientId(json),1);
		userRepository.save(facebook);
		return new ResponseEntity<>(facebook, HttpStatus.CREATED);
		}
	
	@RequestMapping(value = "/google",method = RequestMethod.POST)
	public ResponseEntity<User> addGoogle(@RequestBody String json) {
		System.out.println("ole2");
		User google = new User(readClientId(json),0);
		userRepository.save(google);
		return new ResponseEntity<>(google, HttpStatus.CREATED);
		}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Integer id) {
		userRepository.delete(id);
	}
	
	private String readClientId(String json){
	      JSONObject jsonDownload = new JSONObject(json);
	      return (String) jsonDownload.get("clientId");
		
	}
}
