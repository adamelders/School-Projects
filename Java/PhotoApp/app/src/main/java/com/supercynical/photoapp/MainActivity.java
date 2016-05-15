package com.supercynical.photoapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Integer[] Photos = {R.drawable.adam, R.drawable.tony, R.drawable.sherry, R.drawable.katy,
            R.drawable.tyler, R.drawable.kris, R.drawable.daniel, R.drawable.loren};
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gr = (GridView)findViewById(R.id.gridView);
        final ImageView pic = (ImageView)findViewById(R.id.largeImage);
        assert gr != null;
        gr.setAdapter(new ImageAdapter(this));
        gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Selected Image " + (position + 1),
                        Toast.LENGTH_SHORT).show();
                assert pic != null;
                pic.setImageResource(Photos[position]);

            }
        });



    }

    public class ImageAdapter extends BaseAdapter {

        private Context context;

        public ImageAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return Photos.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            pic = new ImageView(context);
            pic.setImageResource(Photos[position]);
            pic.setScaleType(ImageView.ScaleType.FIT_XY);
            pic.setLayoutParams(new GridView.LayoutParams(188,200));
            return pic;
        }
    }
}
