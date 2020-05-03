package bean.song;

/**
 * Created by dr-chene on @date 2020/5/3
 */
public class Song {
    private String name;
    private long id;
    private int fee;

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

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
