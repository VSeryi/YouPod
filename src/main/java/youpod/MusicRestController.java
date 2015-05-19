package youpod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/video")
public class MusicRestController {

		@Autowired
		private MusicRepository musicRepository;

		@RequestMapping(value = "/{youtubeLink}", method = RequestMethod.GET)
		public SimpleMusic convertLink(@PathVariable String youtubeLink) {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://youtubeinmp3.com/fetch/?api=advanced&format=JSON&video="+youtubeLink;
			SimpleMusic simpleMusic = restTemplate.getForObject(url, SimpleMusic.class);
			return simpleMusic;
		}

		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<Music> addMusic(@RequestBody Music music) {
			musicRepository.save(music);		
			return new ResponseEntity<>(music,HttpStatus.CREATED);
		}
		
		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		public void deleteItem(@PathVariable Integer id) {
			musicRepository.delete(id);
		}

		@RequestMapping(value = "/{id}", method = RequestMethod.GET)
		public Music getMusic(@PathVariable int id) {
			return musicRepository.findOne(id);
		}
}

