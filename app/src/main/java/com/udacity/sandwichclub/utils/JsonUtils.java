package com.udacity.sandwichclub.utils;

import android.support.annotation.Nullable;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private final static String JSON_OBJECT_NAME = "name";
    private final static String JSON_OBJECT_MAIN_NAME = "mainName";
    private final static String JSON_OBJECT_PLACE_OF_ORIGIN = "placeOfOrigin";
    private final static String JSON_OBJECT_DESCRIPTION = "description";
    private final static String JSON_OBJECT_IMAGE = "image";
    private final static String JSON_OBJECT_ALSO_KNOWN_AS = "alsoKnownAs";
    private final static String JSON_OBJECT_INGREDIENTS = "ingredients";

    @Nullable
    public static Sandwich parseSandwichJson(String json) {
        try{
            JSONObject sandwichJSON = new JSONObject(json);
            JSONObject nameJSON = sandwichJSON.getJSONObject(JSON_OBJECT_NAME);

            String mainName = nameJSON.getString(JSON_OBJECT_MAIN_NAME);
            List<String> alsoKnownAs = parseAlsoKnownAsList(nameJSON);

            String placeOfOrigin = sandwichJSON.getString(JSON_OBJECT_PLACE_OF_ORIGIN);
            String description = sandwichJSON.getString(JSON_OBJECT_DESCRIPTION);
            String image = sandwichJSON.getString(JSON_OBJECT_IMAGE);

            List<String> ingredients = parseIngredientList(sandwichJSON);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> parseAlsoKnownAsList(JSONObject nameJSON) throws JSONException{
        List<String> alsoKnownAs = new ArrayList<>();
        JSONArray alsoKnownAsJSON = nameJSON.getJSONArray(JSON_OBJECT_ALSO_KNOWN_AS);
        if (alsoKnownAsJSON != null) {
            for (int i=0; i<alsoKnownAsJSON.length(); i++){
                alsoKnownAs.add(alsoKnownAsJSON.getString(i));
            }
        }

        return alsoKnownAs;
    }

    private static List<String> parseIngredientList(JSONObject sandwichJSON) throws JSONException{
        List<String> ingredients = new ArrayList<>();
        JSONArray ingredientsJSON = sandwichJSON.getJSONArray(JSON_OBJECT_INGREDIENTS);
        if (ingredientsJSON != null) {
            for (int i=0; i<ingredientsJSON.length(); i++){
                ingredients.add(ingredientsJSON.getString(i));
            }
        }

        return ingredients;
    }
}