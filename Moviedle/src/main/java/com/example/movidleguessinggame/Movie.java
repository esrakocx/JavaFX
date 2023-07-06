package com.example.movidleguessinggame;

public class Movie {

    private String title;
    private String year;
    private String genre;
    private String origin;
    private String director;
    private String star;

    public Movie(String title, String year,String genre, String origin, String director, String star){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.origin = origin;
        this.director = director;
        this.star = star;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String tile){
        this.title = title;
    }
    public String getYear(){
        return year;
    }
    public void setYear(String year){
        this.year = year;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getOrigin(){
        return origin;
    }
    public void setOrigin(String origin){
        this.origin = origin;
    }
    public String getDirector(){
        return director;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public String getStar(){
        return star;
    }
    public void setStar(String star){
        this.star = star;
    }

}
