package com.example.truon.music_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController.MediaPlayerControl;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Song> Songs;
    AutoCompleteTextView autotxt;
    ListView lv;
    Song si;
    Cursor cursor;
    private static ListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 2909);
            } else {
                // continue with your code
            }
        } else {
            // continue with your code
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Songs = new ArrayList<>();

            lv = (ListView) findViewById(R.id.lv);
            autotxt = (AutoCompleteTextView) findViewById(R.id.autotxt);
            cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
            cursor.moveToFirst();
            do {
                si = new Song();
                si.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                si.artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                si.id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                si.artistID = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                Songs.add(si);
            } while (cursor.moveToNext());
            cursor.close();

        adapter = new ListViewAdapter(Songs, getApplicationContext());
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        autotxt.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Play_Song.class);
                intent.putParcelableArrayListExtra("ds", Songs);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_bar) {
            Toast.makeText(MainActivity.this, "Anh test thoi", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.share) {
            Toast.makeText(MainActivity.this, "Anh test thoi ma :)))))", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
