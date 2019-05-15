package com.udacity.sandwichclub.features.detail;

import android.text.Spanned;

public interface DetailNavigator {

    interface View {
        void closeOnError();
        void loadSandwichTitle(String title);
        void loadSandwichImage(String image);
        void hideSandwichAlsoKnown();
        void loadSandwichAlsoKnown(Spanned alsoKnownSpanned);
        void hideSandwichPlaceOfOrigin();
        void loadSandwichPlaceOfOrigin(String placeOfOrigin);
        void hideSandwichIngredients();
        void loadSandwichIngredients(Spanned ingredientsSpanned);
        void hideSandwichDescription();
        void loadSandwichDescription(String description);
    }

    interface Presenter {
        void validateSandwichList(int position);
    }

    interface Interactor {
        void validateSandwichList(int position);
    }

    interface InteractorOutput {
        void closeOnError();
        void loadSandwichTitle(String title);
        void loadSandwichImage(String image);
        void hideSandwichAlsoKnown();
        void loadSandwichAlsoKnown(Spanned alsoKnownSpanned);
        void hideSandwichPlaceOfOrigin();
        void loadSandwichPlaceOfOrigin(String placeOfOrigin);
        void hideSandwichIngredients();
        void loadSandwichIngredients(Spanned ingredientsSpanned);
        void hideSandwichDescription();
        void loadSandwichDescription(String description);
    }
}