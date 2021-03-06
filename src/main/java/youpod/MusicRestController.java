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
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
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
	private NacionalMusicRepository nacionalRepository;
	
	@Autowired
	private InternacionalMusicRepository interRepository;
	
	@Autowired
	private BandasSonarasMusicRepository bsRepository;
	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Music> convertLink(@RequestParam String youtubeLink, @RequestParam String type)
			throws IOException {
		String id = "";
		Music music = new Music();
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
        boolean error;
        do{
        error = false;
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
		      if(description.length()>254)
		    	  description = description.substring(0, 254);
		      JSONObject thumbail = (JSONObject)((JSONObject) snippet.get("thumbnails")).get("high");
		      String url = (String) thumbail.get("url");
			  music = new Music(title, description, url, link, type);
		    } catch (JSONException e){
		    	error = true;
		    }finally {
		    	isDownload.close();
		    	isInfo.close();
		    }
		}while(error);
		return new ResponseEntity<>(music, HttpStatus.CREATED);
	}


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Music> addMusic(@RequestBody Music music) {
		if (music.getType().equals("Nacional")){
			NacionalMusic musica = new NacionalMusic(music);
			nacionalRepository.save(musica);
		}else{
			if (music.getType().equals("Internacional")){
				InternacionalMusic musi = new InternacionalMusic (music);
				interRepository.save(musi);
			}else{
				BandasSonarasMusic mu = new BandasSonarasMusic (music);
				bsRepository.save(mu);
			}
		}
		return new ResponseEntity<>(music, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.POST)
	public ResponseEntity<Music> addMusic(@PathVariable int id, @RequestBody Music music) {
		if (music.getType() == "Nacional"){
			NacionalMusic musica = new NacionalMusic(music);
			nacionalRepository.save(musica);
		}else{
			if (music.getType() == "Internacional"){
				InternacionalMusic musi = new InternacionalMusic (music);
				interRepository.save(musi);
			}else{
				BandasSonarasMusic mu = new BandasSonarasMusic (music);
				bsRepository.save(mu);
			}
		}
		return new ResponseEntity<>(music, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Integer id, @RequestBody Music music) {
		if (music.getType() == "Nacional"){
			nacionalRepository.delete(id);
		}else{
			if (music.getType() == "Internacional"){
				interRepository.delete(id);
			}else{
				bsRepository.delete(id);
			}
		}
			
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Music getMusic(@PathVariable int id,@RequestBody Music music ) {
		if (music.getType() == "Nacional"){
			return nacionalRepository.findOne(id);
		}else{
			if (music.getType() == "Internacional"){
				return interRepository.findOne(id);
			}else{
				return bsRepository.findOne(id);
			}
		}
	}
	
	@RequestMapping(value = "/nacional", method = RequestMethod.GET)
	public ResponseEntity<List<NacionalMusic>> getMusicNacional() {
		List<NacionalMusic> listaN = new ArrayList<NacionalMusic>();
		List<NacionalMusic> lista2 = nacionalRepository.findAll();
		int i = lista2.size()-1;
		while((i>=0)&&(i>=lista2.size()-4)){
			listaN.add(lista2.get(i));
			i--;
		}
		return new ResponseEntity<>(listaN, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/internacional", method = RequestMethod.GET)
	public ResponseEntity<List<InternacionalMusic>> getMusicInternacional() {
		List<InternacionalMusic> listaI = new ArrayList<InternacionalMusic>();
		List<InternacionalMusic> lista2 = interRepository.findAll();
		int i = lista2.size()-1;
		while((i>=0)&&(i>=lista2.size()-4)){
			listaI.add(lista2.get(i));
			i--;
		}
		return new ResponseEntity<>(listaI, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/bandassonarasmusic", method = RequestMethod.GET)
	public ResponseEntity<List<BandasSonarasMusic>> getBandasSonarasMusic() {
		List<BandasSonarasMusic> listaB = new ArrayList<BandasSonarasMusic>();
		List<BandasSonarasMusic> lista2 = bsRepository.findAll();
		int i = lista2.size()-1;
		while((i>=0)&&(i>=lista2.size()-4)){
			listaB.add(lista2.get(i));
			i--;
		}
		return new ResponseEntity<>(listaB, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/nac", method = RequestMethod.GET)
	public ResponseEntity<List<NacionalMusic>> getNacional() {
		List<NacionalMusic> listaB = new ArrayList<NacionalMusic>();
		List<NacionalMusic> lista2 = nacionalRepository.findAll();
		int i = lista2.size()-1;
		while((i>=0)&&(i<lista2.size())){
			listaB.add(lista2.get(i));
			i--;
		}
		return new ResponseEntity<>(listaB, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/inter", method = RequestMethod.GET)
	public ResponseEntity<List<InternacionalMusic>> getInternacional() {
		List<InternacionalMusic> listaB = new ArrayList<InternacionalMusic>();
		List<InternacionalMusic> lista2 = interRepository.findAll();
		int i = lista2.size()-1;
		while((i>=0)&&(i<lista2.size())){
			listaB.add(lista2.get(i));
			i--;
		}
		return new ResponseEntity<>(listaB, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/son", method = RequestMethod.GET)
	public ResponseEntity<List<BandasSonarasMusic>> getSonoras() {
		List<BandasSonarasMusic> listaB = new ArrayList<BandasSonarasMusic>();
		List<BandasSonarasMusic> lista2 = bsRepository.findAll();
		int i = lista2.size()-1;
		while((i>=0)&&(i<lista2.size())){
			listaB.add(lista2.get(i));
			i--;
		}
		return new ResponseEntity<>(listaB, HttpStatus.CREATED);
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
