package com.udacity.sandwichclub.utils;

import android.support.annotation.Nullable;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    @Nullable
    public static Sandwich parseSandwichJson(String json) {
        try{
            JSONObject sandwichJSON = new JSONObject(json);
            JSONObject nameJSON = sandwichJSON.getJSONObject("name");

            String mainName = nameJSON.getString("mainName");
            List<String> alsoKnownAs = parseAlsoKnownAsList(nameJSON);

            String placeOfOrigin = sandwichJSON.getString("placeOfOrigin");
            String description = sandwichJSON.getString("description");
            String image = sandwichJSON.getString("image");

            List<String> ingredients = parseIngredientList(sandwichJSON);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> parseAlsoKnownAsList(JSONObject nameJSON) throws JSONException{
        JSONArray alsoKnownAsJSON = nameJSON.getJSONArray("alsoKnownAs");
        List<String> alsoKnownAs = new ArrayList<>();
        if (alsoKnownAsJSON != null) {
            for (int i=0; i<alsoKnownAsJSON.length(); i++){
                alsoKnownAs.add(alsoKnownAsJSON.getString(i));
            }
        }

        return alsoKnownAs;
    }

    private static List<String> parseIngredientList(JSONObject sandwichJSON) throws JSONException{
        JSONArray ingredientsJSON = sandwichJSON.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();
        if (ingredientsJSON != null) {
            for (int i=0; i<ingredientsJSON.length(); i++){
                ingredients.add(ingredientsJSON.getString(i));
            }
        }

        return ingredients;
    }
}