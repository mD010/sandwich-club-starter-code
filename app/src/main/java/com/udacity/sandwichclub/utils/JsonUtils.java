package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static String TAG = JsonUtils.class.getName();

    private static String NAME = "name";
    private static String MAIN_NAME = "mainName";
    private static String ALSO_KNOWN_AS = "alsoKnownAs";
    private static String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static String DESCRIPTION = "description";
    private static String IMAGE = "image";
    private static String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject object = new JSONObject(json);
            Sandwich sandwich = new Sandwich();
            JSONObject name = object.getJSONObject(NAME);
            if (name != null) {
                sandwich.setMainName(name.getString(MAIN_NAME));
                JSONArray alsoKnownAsArray = name.getJSONArray(ALSO_KNOWN_AS);
                if (alsoKnownAsArray != null) {
                    sandwich.setAlsoKnownAs(parseList(alsoKnownAsArray));
                }
            }
            sandwich.setPlaceOfOrigin(object.getString(PLACE_OF_ORIGIN));
            sandwich.setDescription(object.getString(DESCRIPTION));
            sandwich.setImage(object.getString(IMAGE));
            JSONArray ingredientsArray = object.getJSONArray(INGREDIENTS);
            if (ingredientsArray != null) {
                sandwich.setIngredients(parseList(ingredientsArray));
            }
            return sandwich;
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
            Log.d(TAG, "parseSandwichJson: json parsing failed. json=" + json);
            return null;
        }
    }

    private static List<String> parseList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String curr = jsonArray.getString(i);
            list.add(curr);
        }
        return list;
    }
}
