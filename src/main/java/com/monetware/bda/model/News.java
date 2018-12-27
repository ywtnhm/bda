package com.monetware.bda.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;


/**
 * @author zengcd 2018/12/27
 * @since 1.0.0
 */
@Document(indexName = News.INDEX, type = News.ORDER_TYPE, shards = 6, replicas = 2, refreshInterval = "-1")
public class News implements Serializable {

    public News() {

    }

    public News(String title, String content, String media, String pubTime, String url) {

        this.title = title;
        this.content = content;
        this.media = media;
        this.pubTime = pubTime;
        this.url = url;
    }

    //建立索引
    public static final String INDEX = "ringspider";
    //类型
    public static final String ORDER_TYPE = "news";


    @Field(type = FieldType.Text, searchAnalyzer = "ik", analyzer = "ik")
    private String title;

    @Field(type = FieldType.Date)
    private String pubTime;

    private String id;
    /**
     * 可以通过ik 分词器进行分词
     */
    @Field(type = FieldType.Text, searchAnalyzer = "ik", analyzer = "ik")
    private String content;

    private String media;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
