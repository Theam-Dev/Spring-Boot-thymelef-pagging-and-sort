package com.example.springpagandsort.model;

import javax.persistence.*;

@Entity
@Table(name="post")
public class Post{
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @Column(name="title")
    public String title;
    @Column(name="body")
    public String body;
    public Post(Integer _id,String _title,String _body){
        super();
        id = _id;
        title = _title;
        body = _body;
    }
    public Post() {
        super();
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer _id) {
        this.id = _id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String _title) {
        this.title = _title;
    }
    public String getBody(){
        return body;
    }
    public void setBody(String _body) {
        this.body = _body;
    }
}