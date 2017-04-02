package com.example.truon.music_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by truon on 3/7/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Song>  implements View.OnClickListener {
    ArrayList<Song> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView title;
        TextView artist;
    }

    public ListViewAdapter(ArrayList<Song> data, Context context) {
        super(context, R.layout.item_song, data);
        this.dataSet = data;
        this.mContext = context;

    }
    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Song song=(Song) object;

        switch (v.getId())
        {
            case R.id.playing:
                Toast.makeText(mContext,"ngu nguoi",Toast.LENGTH_LONG).show();
                break;
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_song, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.Title);
            viewHolder.artist = (TextView) convertView.findViewById(R.id.Artist);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.title.setText(song.getTitle());
        viewHolder.artist.setText(song.getArtist());

        return convertView;
    }
}
