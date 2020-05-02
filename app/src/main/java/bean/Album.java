package bean;

import java.util.List;

/**
 * Created by dr-chene on @date 2020/5/2
 */
public class Album {
    private String name;
    private long id;
    private int trackCount;
    private String coverImgUrl;
    private long coverImgId;
    private String description;
    private List<String> tags;
    private AlbumCreator creator;

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

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public long getCoverImgId() {
        return coverImgId;
    }

    public void setCoverImgId(long coverImgId) {
        this.coverImgId = coverImgId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public AlbumCreator getCreator() {
        return creator;
    }

    public void setCreator(AlbumCreator creator) {
        this.creator = creator;
    }
}
