package bean.mycenter;

import bean.SongList;

/**
 * Created by dr-chene on @date 2020/5/3
 */
public class Profile {
    private int city;
    private String nickname;
    private int followeds;
    private int follows;
    private int playlistCount;

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getFolloweds() {
        return followeds;
    }

    public void setFolloweds(int followeds) {
        this.followeds = followeds;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public int getPlaylistCount() {
        return playlistCount;
    }

    public void setPlaylistCount(int playlistCount) {
        this.playlistCount = playlistCount;
    }
}
