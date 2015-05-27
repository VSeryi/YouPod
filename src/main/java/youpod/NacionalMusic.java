package youpod;

	import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
	public class NacionalMusic extends Music {
		
		public NacionalMusic(){
			super();
		}
		
		public NacionalMusic(String title, String description, String thumbail, String download,String type){
		super( title,description, thumbail, download, type);
		}
		
		public NacionalMusic(Music music){
			super(music);
		}
	}
