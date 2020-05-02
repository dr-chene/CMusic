package bean;

import java.util.List;

/**
 * Created by dr-chene on @date 2020/5/2
 */
public class Albums {
    private List<Album> playlists;
    private int total;
    private int code;
    private Boolean more;
    private String cat;

    public List<Album> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Album> playlists) {
        this.playlists = playlists;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Boolean getMore() {
        return more;
    }

    public void setMore(Boolean more) {
        this.more = more;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
