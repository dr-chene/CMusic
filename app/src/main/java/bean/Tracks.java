package bean;

import java.util.List;

/**
 * Created by dr-chene on @date 2020/5/2
 */
public class Tracks {
    private String name;
    private long id;
    private int pst;
    private int t;
    private List<Ar> ar;
    private int fee;
    private Al al;
    private int dt;

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

    public int getPst() {
        return pst;
    }

    public void setPst(int pst) {
        this.pst = pst;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public List<Ar> getAr() {
        return ar;
    }

    public void setAr(List<Ar> ar) {
        this.ar = ar;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Al getAl() {
        return al;
    }

    public void setAl(Al al) {
        this.al = al;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }
}
