package youpod;

	import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
	public class BandasSonarasMusic extends Music {
		public BandasSonarasMusic(){
			super();
		}
		public BandasSonarasMusic(String title, String description, String thumbail, String download,String type){
		super( title,description, thumbail, download,type);
		}
		public BandasSonarasMusic(Music music){
			super(music);
	}
	}
