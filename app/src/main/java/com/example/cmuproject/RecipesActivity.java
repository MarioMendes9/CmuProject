package com.example.cmuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class RecipesActivity extends AppCompatActivity implements  FilterFragment.OnFragmentFilterInteractionListener, ListRecipesFragment.OnFragmentListInteractionListener, RecipeDetailsFragment.OnFragmentDetailsInteractionListener {




    private Toolbar myToolbar;
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ThemeLight);
        mSettings= getSharedPreferences("themeMode", MODE_PRIVATE);
        String s = mSettings.getString("mode","");
        if(s.equals("light")){
            setTheme(R.style.ThemeLight);
        } else if(s.equals("dark")){
            setTheme(R.style.ThemeDark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipies);


        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.setting));
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FilterFragment filterFragment = new FilterFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container,filterFragment);
        ft.commit();




    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_def:
                Intent mIntent = new Intent(this, SettingsActivity.class);
                mIntent.putExtra("theme", mSettings.getString("mode",""));
                startActivity(mIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onFragmentFilterInteraction(Uri uri) {
        FilterFragment changeFrag = new FilterFragment();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container, changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void getList(String aler, String diet) {
        ListRecipesFragment changeFrag = new ListRecipesFragment();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putString("aler", aler);
        args.putString("diet", diet);
        changeFrag.setArguments(args);
        tr.replace(R.id.fragment_container, changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }


    @Override
    public void onFragmentListInteraction(Uri uri) {
        ListRecipesFragment changeFrag = new ListRecipesFragment();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container, changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void getDetails(String id) {
        RecipeDetailsFragment changeFrag = new RecipeDetailsFragment();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putString("id", id);
        changeFrag.setArguments(args);
        tr.replace(R.id.fragment_container, changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void onFragmentDetailsInteraction(Uri uri) {
        RecipeDetailsFragment changeFrag = new RecipeDetailsFragment();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container, changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }
}
