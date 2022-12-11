package com.mdp.pahlawan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView rvPahlawan;
    private ArrayList<ModelPahlawan> data = new ArrayList<>();

    private int mView = 0; // 0 untuk card dan 1 untuk grid
    static final String STATE_MODE = "MODE VIEW";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPahlawan = findViewById(R.id.rv_pahlawan);
        rvPahlawan.setHasFixedSize(true);

        data.addAll(DataPahlawan.ambilDataPahlawan());
        tampilDataCard();

        if(savedInstanceState != null)
        {
            mView = savedInstanceState.getInt(STATE_MODE);
            if(mView == 0)
            {
                tampilDataCard();
            }
            else
            {
                tampilDataGrid();
            }
        }
        else
        {
            tampilDataCard();
        }
        // tampilDataGrid();
    }

    private void tampilDataCard()
    {
        mView = 0; // card
        // rvPahlawan.setLayoutManager(new LinearLayoutManager(this));
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        rvPahlawan.setLayoutManager(new GridLayoutManager(this, gridColumnCount));
        String appLang = getResources().getString(R.string.app_lang);
        Toast.makeText(this, "Lang:" + appLang, Toast.LENGTH_SHORT).show();
        AdapterCard adapterCard = new AdapterCard(data, appLang);
        rvPahlawan.setAdapter(adapterCard);

    }

    private void tampilDataGrid()
    {
        mView = 1;
        rvPahlawan.setLayoutManager(new GridLayoutManager(this, 2));
        AdapterGrid adapterGrid = new AdapterGrid(data);
        rvPahlawan.setAdapter(adapterGrid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_tampilan, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES)
        {
            menu.findItem(R.id.menu_night).setTitle("Mode Day");
        }
        else
        {
            menu.findItem(R.id.menu_night).setTitle("Mode Night");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_card:

                setTitle("Mode Card");
                tampilDataCard();
                break;

            case R.id.menu_grid:

                setTitle("Mode Grid");
                tampilDataGrid();
                break;

            case R.id.menu_night:

                setTitle("Mode Night");

                int nightMode = AppCompatDelegate.getDefaultNightMode();
                if(nightMode == AppCompatDelegate.MODE_NIGHT_YES)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();
                break;

            case R.id.menu_help:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+62895603231365"));
                if(intent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(intent);
                    break;
                }

        }
        /* if(item.getItemId() == R.id.menu_card)
        {
            setTitle("Mode Menu Card");
            tampilDataCard();
            System.exit(0);
        }
        else if(item.getItemId() == R.id.menu_grid)
        {
            setTitle("Mode Menu Grid");
            tampilDataGrid();
            System.exit(0);
        } */
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STATE_MODE, mView);
        super.onSaveInstanceState(outState);
    }
}