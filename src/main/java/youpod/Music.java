package youpod;

	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;

	@Entity
	public class Music {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		private String thumbail;
		private String title;
		private String description;
		private String download;
		private String type;

		public Music(String title, String description, String thumbail, String download, String type) {
			this.thumbail = thumbail;
			this.title = title;
			this.description = description;
			this.download = download;
			this.type = type;
		}

		public Music(Music music) {
			this.thumbail = music.getThumbail();
			this.title = music.getTitle();
			this.description = music.getDescription();
			this.download = music.getDownload();
			this.type = music.type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getThumbail() {
			return thumbail;
		}

		public void setThumbail(String thumbail) {
			this.thumbail = thumbail;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}	

		public String getDownload() {
			return download;
		}

		public void setDownload(String download) {
			this.download = download;
		}

		@Override
		public String toString() {
			return "Post [id=" + id + ", name=" + thumbail + ", title=" + title
					+ ", text=" + description + "]";
		}
	}
