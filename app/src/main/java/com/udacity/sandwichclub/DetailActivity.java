package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.features.detail.DetailNavigator;
import com.udacity.sandwichclub.features.detail.DetailPresenter;

public class DetailActivity extends AppCompatActivity implements DetailNavigator.View {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView ingredientsIv;
    private TextView alsoKnownTv;
    private TextView originTv;
    private TextView descriptionTv;
    private TextView ingredientsTv;
    private View nameContentV;
    private View originContentV;
    private View descriptionContentV;
    private View ingredientsContentV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        initData();
    }

    @Override
    public void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadSandwichTitle(String title) {
        setTitle(title);
    }

    @Override
    public void loadSandwichImage(String image) {
        Picasso.with(this)
                .load(image)
                .into(ingredientsIv);
    }

    @Override
    public void hideSandwichAlsoKnown() {
        nameContentV.setVisibility(View.GONE);
    }

    @Override
    public void loadSandwichAlsoKnown(Spanned alsoKnownSpanned) {
        alsoKnownTv.setText(alsoKnownSpanned);
    }

    @Override
    public void hideSandwichPlaceOfOrigin() {
        originContentV.setVisibility(View.GONE);
    }

    @Override
    public void loadSandwichPlaceOfOrigin(String placeOfOrigin) {
        originContentV.setVisibility(View.VISIBLE);
        originTv.setText(placeOfOrigin);
    }

    @Override
    public void hideSandwichIngredients() {
        ingredientsContentV.setVisibility(View.GONE);
    }

    @Override
    public void loadSandwichIngredients(Spanned ingredientsSpanned) {
        ingredientsTv.setText(ingredientsSpanned);
    }

    @Override
    public void hideSandwichDescription() {
        descriptionContentV.setVisibility(View.GONE);
    }

    @Override
    public void loadSandwichDescription(String description) {
        descriptionContentV.setVisibility(View.VISIBLE);
        descriptionTv.setText(description);
    }

    private void initView(){
        ingredientsIv = findViewById(R.id.image_iv);
        alsoKnownTv = findViewById(R.id.also_known_tv);
        originTv = findViewById(R.id.origin_tv);
        descriptionTv = findViewById(R.id.description_tv);
        ingredientsTv = findViewById(R.id.ingredients_tv);
        nameContentV = findViewById(R.id.name_content);
        originContentV = findViewById(R.id.origin_content);
        descriptionContentV = findViewById(R.id.description_content);
        ingredientsContentV = findViewById(R.id.ingredients_content);
    }

    private void initData(){
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
            return;
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            closeOnError();
            return;
        }

        DetailPresenter presenter = new DetailPresenter(this, this);
        presenter.validateSandwichList(position);
    }

}