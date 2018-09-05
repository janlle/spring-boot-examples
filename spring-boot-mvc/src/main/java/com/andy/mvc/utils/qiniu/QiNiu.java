package com.andy.mvc.utils.qiniu;

/**
 * <p>
 *
 * @author Leone
 **/
public class QiNiu {

    private String hash;

    private String key;

    public QiNiu() {
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "QiNiu{" +
                "hash='" + hash + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

}