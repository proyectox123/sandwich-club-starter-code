package com.udacity.sandwichclub.features.detail;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailInteractor implements DetailNavigator.Interactor {

    private Context context;
    private DetailNavigator.InteractorOutput interactorOutput;

    public DetailInteractor(Context context, DetailNavigator.InteractorOutput interactorOutput){
        this.context = context;
        this.interactorOutput = interactorOutput;
    }

    @Override
    public void validateSandwichList(int position) {
        String[] sandwiches = context.getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            interactorOutput.closeOnError();
            return;
        }

        populateUI(sandwich);
    }

    private void populateUI(Sandwich sandwich) {
        interactorOutput.loadSandwichTitle(sandwich.getMainName());
        interactorOutput.loadSandwichImage(sandwich.getImage());

        String alsoKnown = getListLabel(sandwich.getAlsoKnownAs());
        if (alsoKnown.trim().length() == 0) {
            interactorOutput.hideSandwichAlsoKnown();
        } else {
            interactorOutput.loadSandwichAlsoKnown(getSpannedLabel(alsoKnown));
        }

        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        if (placeOfOrigin.trim().length() == 0) {
            interactorOutput.hideSandwichPlaceOfOrigin();
        } else {
            interactorOutput.loadSandwichPlaceOfOrigin(placeOfOrigin);
        }

        String ingredients = getListLabel(sandwich.getIngredients());
        if (ingredients.trim().length() == 0) {
            interactorOutput.hideSandwichIngredients();
        } else {
            interactorOutput.loadSandwichIngredients(getSpannedLabel(ingredients));
        }

        String description = sandwich.getDescription();
        if (description.trim().length() == 0) {
            interactorOutput.hideSandwichDescription();
        } else {
            interactorOutput.loadSandwichDescription(description);
        }
    }

    private String getListLabel(List<String> list){
        StringBuilder label = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            label.append("&#8226; ").append(list.get(i));
            if(i != list.size() - 1){
                label.append("<br/>");
            }
        }

        return label.toString().trim();
    }

    private Spanned getSpannedLabel(String label){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(label, Html.FROM_HTML_MODE_LEGACY);
        }else{
            return Html.fromHtml(label);
        }
    }
}