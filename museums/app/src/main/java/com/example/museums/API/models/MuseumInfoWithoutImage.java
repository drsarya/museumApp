package com.example.museums.API.models;

import androidx.room.ColumnInfo;

public class MuseumInfoWithoutImage {
    @ColumnInfo(name = "id")
    public Integer id;

    @ColumnInfo(name = "nameMuseum")
    public String name;

    @ColumnInfo(name = "address")
    public String address;
    @ColumnInfo(name = "description")
    public String description;

    @Override
    public String toString() {
        return "MuseumInfoWithoutImage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
