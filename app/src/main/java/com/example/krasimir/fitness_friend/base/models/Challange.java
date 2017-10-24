package com.example.krasimir.fitness_friend.base.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class Challange implements Parcelable {
    private String name;
    private String imageUrl;
    private String description;
    private String repetition;
    private String type;
    private String exercises;

    public Challange(){

    }

    public Challange(String name, String imageUrl, String description, String repetition, String type, String exercises) {
        setName(name);
        setImageUrl(imageUrl);
        setDescription(description);
        setRepetition(repetition);
        setType(type);
        setExercises(exercises);
    }


    protected Challange(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        repetition = in.readString();
        type = in.readString();
        exercises = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(imageUrl);
        out.writeString(description);
        out.writeString(repetition);
        out.writeString(type);
        out.writeString(exercises);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Challange> CREATOR = new Creator<Challange>() {
        @Override
        public Challange createFromParcel(Parcel in) {
            return new Challange(in);
        }

        @Override
        public Challange[] newArray(int size) {
            return new Challange[size];
        }
    };

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {return this.imageUrl;}

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {return this.description;}

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepetition() {return this.repetition;}

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

    public String getType() {return this.type;}

    public void setType(String type) {
        this.type = type;
    }

    public String getExercises() {return this.exercises;}

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }
}
