package dto;

public class Video {
    private int videoId;
    private String url;
    private String part;
    private String title;
    private String content;
    private int count;

    public Video() {

    }

    //받기용 생성자
    public Video(int videoId, String url, String part, String title, String content, int count) {
        this.videoId = videoId;
        this.url = url;
        this.part = part;
        this.title = title;
        this.content = content;
        this.count = count;
    }

    //생성용 생성자
    public Video(String url, String part, String title, String content) {
        super();
        this.url = url;
        this.part = part;
        this.title = title;
        this.content = content;
    }

public int getVideoId() {
    return videoId;
}

public void setVideoId(int videoId) {
    this.videoId = videoId;
}

public String getUrl() {
    return url;
}

public void setUrl(String url) {
    this.url = url;
}

public String getPart() {
    return part;
}

public void setPart(String part) {
    this.part = part;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getContent() {
    return content;
}

public void setContent(String content) {
    this.content = content;
}

public int getCount() {
    return count;
}

public void setCount(int count) {
    this.count = count;
}
}