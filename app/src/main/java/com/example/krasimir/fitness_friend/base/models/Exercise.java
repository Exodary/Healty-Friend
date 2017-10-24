package com.example.krasimir.fitness_friend.base.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class Exercise implements Parcelable {
    private String name;
    private String description;
    private String imageUrl;

    public Exercise(){
    }

    public Exercise(Parcel in) {
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(description);
        out.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public Exercise(String name, String description, String imageUrl){
        setName(name);
        setDescription(description);
        setImageUrl(imageUrl);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {return this.imageUrl;}

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}