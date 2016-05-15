package com.supercynical.bloodbornelistapp;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[ ] infoList = {"Progress Route Guide", "Weapons", "Items", "Maps", "Bosses",
                "Covenants", "Hints and Secrets", "Wallpaper"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main, R.id.bbList, infoList));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch(position){
            case 0:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://bloodborne.wiki.fextralife.com/Game+Progress+Route")));
                break;
            case 1:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://bloodborne.wiki.fextralife.com/Weapons")));
                break;
            case 2:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://bloodborne.wiki.fextralife.com/Items")));
                break;
            case 3:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://bloodborne.wiki.fextralife.com/Maps")));
                break;
            case 4:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://bloodborne.wiki.fextralife.com/Bosses")));
                break;
            case 5:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://bloodborne.wiki.fextralife.com/Covenants")));
                break;
            case 6:
                startActivity(new Intent(MainActivity.this, Secrets.class));
                break;
            case 7:
                startActivity(new Intent(MainActivity.this, Wallpaper.class));
                break;
            default:
                break;
        }
    }
}
