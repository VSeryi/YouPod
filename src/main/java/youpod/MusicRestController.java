package youpod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/video")
public class MusicRestController {

	@Autowired
	private MusicRepository musicRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Music convertLink(@RequestParam String youtubeLink)
			throws IOException {
		String id = "";
		Music music = Music();
        String[] params = youtubeLink.split("\\?");
        if(params.length==2) {
                String[] keyValuePairs = params[1].split("&");
                for(String kvp : keyValuePairs) {
                        String[] kv = kvp.split("=");
                        if(kv[0].equals("v")) {
                                id = kv[1];
                        }
                }
        }
		URL urlDownload = new URL(
				"http://youtubeinmp3.com/fetch/?api=advanced&format=JSON&video="
						+ youtubeLink);
		URL urlInfo = new URL(
				"https://www.googleapis.com/youtube/v3/videos?id="+id+"&key=AIzaSyCYwYqEMbNK6B_dkNUJJk5N5Ci7H1CRrmM&part=snippet");
		InputStream isDownload = urlDownload.openStream();
		InputStream isInfo = urlInfo.openStream();
		try {
		      BufferedReader rdDownload = new BufferedReader(new InputStreamReader(isDownload, Charset.forName("UTF-8")));
		      BufferedReader rdInfo = new BufferedReader(new InputStreamReader(isInfo, Charset.forName("UTF-8")));
		      String jsonTextDownload = readAll(rdDownload);
		      String jsonTextInfo = readAll(rdInfo);
		      JSONObject jsonDownload = new JSONObject(jsonTextDownload);
		      JSONObject jsonInfo = new JSONObject(jsonTextInfo);
		      String link = (String) jsonDownload.get("link");
		      JSONArray videoData = (JSONArray) jsonInfo.getJSONArray("items");
		      JSONObject snippet = (JSONObject)((JSONObject) videoData.get(0)).get("snippet");
		      String title = (String) snippet.get("title");
		      String description = (String) snippet.get("description");
		      JSONObject thumbail = (JSONObject)((JSONObject) snippet.get("thumbnails")).get("default");
		      String url = (String) thumbail.get("url");
			  music = new Music(title, description, url, link);
		    } finally {
		    	isDownload.close();
		    	isInfo.close();
		    }
		return music;
	}

	private Music Music() {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Music> addMusic(@RequestBody Music music) {
		musicRepository.save(music);
		return new ResponseEntity<>(music, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Integer id) {
		musicRepository.delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Music getMusic(@PathVariable int id) {
		return musicRepository.findOne(id);
	}
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	
}
