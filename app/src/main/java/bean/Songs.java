package bean;

/**
 * Created by dr-chene on @date 2020/5/2
 */
public class Songs {
    private int code;
    private String relatedVideos;
    private SongList playlist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRelatedVideos() {
        return relatedVideos;
    }

    public void setRelatedVideos(String relatedVideos) {
        this.relatedVideos = relatedVideos;
    }

    public SongList getPlaylist() {
        return playlist;
    }

    public void setPlaylist(SongList playlist) {
        this.playlist = playlist;
    }
}
