package com.dcoders.satishkumar.g.newsbucket.mainDataClasses;

import java.io.Serializable;

public class MyDataClass implements Serializable {
    public String author;
    public String ntitle;
    public String ndescription;
    public String url;

    public MyDataClass(String author, String ntitle, String ndescription, String url, String imageUrl, String publishedAt) {
        this.author = author;
        this.ntitle = ntitle;
        this.ndescription = ndescription;
        this.url = url;
        this.imageUrl = imageUrl;
        this.publishedAt = publishedAt;
    }

    public String imageUrl;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNdescription() {
        return ndescription;
    }

    public void setNdescription(String ndescription) {
        this.ndescription = ndescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String publishedAt;
}
