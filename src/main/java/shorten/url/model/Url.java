package shorten.url.model;

import javax.persistence.*;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String longUrl;

    @Column
    private String shortUrl;

    @Column
    private String bitlinkId;

    @Column
    private int shortened;

    private int clicks;


    public Url() {
    }


    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getShortened() {
        return shortened;
    }

    public void setShortened(int shortened) {
        this.shortened = shortened;
    }

    public String getBitlinkId() {
        return bitlinkId;
    }

    public void setBitlinkId(String bitlinkId) {
        this.bitlinkId = bitlinkId;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }
}
