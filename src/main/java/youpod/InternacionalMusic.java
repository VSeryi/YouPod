package youpod;

	import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
	public class InternacionalMusic extends Music {
		public InternacionalMusic(){
			super();
		}
		public InternacionalMusic(String title, String description, String thumbail, String download,String type){
		super( title,description, thumbail, download,type);
		}
		public InternacionalMusic(Music music){
			super(music);
	}
	}
