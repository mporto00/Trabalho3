package com.example.marcelo.trabalho3;

import java.io.Serializable;

/**
 * Created by Marcelo on 13/06/2017.
 */

public class Place implements Serializable {

    private String name;
    private String favorite;
    private String description;
    private int pic;

    public Place (String name, String favorite, int pic, String description){

        this.name = name;
        this.favorite = favorite;
        this.pic = pic;
        this.description = description;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getfavorite() {
        return favorite;
    }

    public void setfavorite(String favorite) { this.favorite = favorite; }

    public String getDescription() {return description; }

    public void setDescription(String description) { this.description = description; }
}