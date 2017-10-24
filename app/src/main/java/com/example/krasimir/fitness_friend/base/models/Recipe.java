package com.example.krasimir.fitness_friend.base.models;

/**
 * Created by Krasimir on 10/9/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;


public class Recipe implements Parcelable {
    private String id;
    private String name;
    private String description;
    private String ingredients;
    private String cookingTime;
    private String preparationTime;
    private String servings;
    private String imageUrl;
    private String callories;

    public Recipe(){

    }

    public Recipe(Parcel in) {
        id =in.readString();
        name = in.readString();
        description = in.readString();
        ingredients = in.readString();
        cookingTime = in.readString();
        preparationTime = in.readString();
        servings = in.readString();
        imageUrl = in.readString();
        callories = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeString(name);
        out.writeString(description);
        out.writeString(ingredients);
        out.writeString(cookingTime);
        out.writeString(preparationTime);
        out.writeString(servings);
        out.writeString(imageUrl);
        out.writeString(callories);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>(){
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public Recipe(String id, String name, String description, String ingredients,  String cookingTime ,
                  String preparationTime, String servings, String imageUrl, String callories) {
        setId(id);
        setName(name);
        setDescription(description);
        setIngredients(ingredients);
        setCookingTime(cookingTime);
        setServings(servings);
        setPreparationTime(preparationTime);
        setImageUrl(imageUrl);
        setCallories(callories);

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

    public String getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCookingTime() {
        return this.cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getServings() {
        return this.servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getPreparationTime() {
        return this.preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getCallories() {
        return this.callories;
    }

    public void setCallories(String callories) {
        this.callories = callories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {return this.imageUrl;}
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
