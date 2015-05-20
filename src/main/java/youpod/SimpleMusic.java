package youpod;

public class SimpleMusic {
	private String title;
	private Integer length;
	private String link;

	public SimpleMusic() {
	}

	public SimpleMusic(String title, Integer length, String link) {
		this.title = title;
		this.length = length;
		this.link = link;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
