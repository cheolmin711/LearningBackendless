package com.example.cheolmin.learningbackendless;

/**
 * Created by cheolminhwang11 on 2/22/18.
 */

public class Boshulleng {
    private String name, genre, address, ownerID, objectId;
    private int rating, price;

    //need getters and setters for backendless to work
    //need an EMPTY, DEFAULT CONSTRUCtOR for backendless to work


    public Boshulleng() {
    }

    @Override
    public String toString() {
        return "Boshulleng{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", address='" + address + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", objectId='" + objectId + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }

    public Boshulleng(String name, String genre, String address, String ownerID, String objectId, int rating, int price) {
        this.name = name;
        this.genre = genre;
        this.address = address;
        this.ownerID = ownerID;
        this.objectId = objectId;
        this.rating = rating;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
