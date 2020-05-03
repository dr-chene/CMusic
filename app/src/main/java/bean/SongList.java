package bean;

import java.util.List;

/**
 * Created by dr-chene on @date 2020/5/2
 */
public class SongList {
    private List<Tracks> tracks;
    private int trackCount;

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public List<Tracks> getTracks() {
        return tracks;
    }

    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }

}
