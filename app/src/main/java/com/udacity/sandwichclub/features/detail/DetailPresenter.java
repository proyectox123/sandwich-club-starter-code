package com.udacity.sandwichclub.features.detail;

import android.content.Context;
import android.text.Spanned;

public class DetailPresenter implements DetailNavigator.Presenter, DetailNavigator.InteractorOutput {

    private DetailNavigator.Interactor interactor;
    private DetailNavigator.View view;

    public DetailPresenter(Context context, DetailNavigator.View view){
        this.interactor = new DetailInteractor(context, this);
        this.view = view;
    }

    @Override
    public void validateSandwichList(int position) {
        interactor.validateSandwichList(position);
    }

    @Override
    public void closeOnError() {
        view.closeOnError();
    }

    @Override
    public void loadSandwichTitle(String title) {
        view.loadSandwichTitle(title);
    }

    @Override
    public void loadSandwichImage(String image) {
        view.loadSandwichImage(image);
    }

    @Override
    public void hideSandwichAlsoKnown() {
        view.hideSandwichAlsoKnown();
    }

    @Override
    public void loadSandwichAlsoKnown(Spanned alsoKnownSpanned) {
        view.loadSandwichAlsoKnown(alsoKnownSpanned);
    }

    @Override
    public void hideSandwichPlaceOfOrigin() {
        view.hideSandwichPlaceOfOrigin();
    }

    @Override
    public void loadSandwichPlaceOfOrigin(String placeOfOrigin) {
        view.loadSandwichPlaceOfOrigin(placeOfOrigin);
    }

    @Override
    public void hideSandwichIngredients() {
        view.hideSandwichIngredients();
    }

    @Override
    public void loadSandwichIngredients(Spanned ingredientsSpanned) {
        view.loadSandwichIngredients(ingredientsSpanned);
    }

    @Override
    public void hideSandwichDescription() {
        view.hideSandwichDescription();
    }

    @Override
    public void loadSandwichDescription(String description) {
        view.loadSandwichDescription(description);
    }
}
