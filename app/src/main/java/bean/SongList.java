package bean;

import java.util.List;

/**
 * Created by dr-chene on @date 2020/5/2
 */
public class SongList {
    private AlbumCreator creator;
    private List<Tracks> tracks;
    private List<TrackId> trackIds;
    private long createTime;
    private String coverImgUrl;
    private int trackCount;
    private String description;
    private String name;

    public AlbumCreator getCreator() {
        return creator;
    }

    public void setCreator(AlbumCreator creator) {
        this.creator = creator;
    }

    public List<Tracks> getTracks() {
        return tracks;
    }

    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }

    public List<TrackId> getTrackIds() {
        return trackIds;
    }

    public void setTrackIds(List<TrackId> trackIds) {
        this.trackIds = trackIds;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;
}
