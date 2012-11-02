package com.naw.engine;

import java.util.ArrayList;

import com.naw.tools.Triplet;


import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class HighscoreListAdapter implements ListAdapter {
	
	private ArrayList<Triplet<Integer,String,String>> mData = new ArrayList<Triplet<Integer,String,String>>();
    private LayoutInflater mInflater;
	private Bitmap mIcon;
	private int colorArray[];
	
	public HighscoreListAdapter(Context context) {
		 // Cache the LayoutInflate to avoid asking for a new one each time.
        mInflater = LayoutInflater.from(context);
        colorArray =  new int[5];
        colorArray[0] = Color.argb(255, 21, 135, 168);
        colorArray[1] = Color.argb(255, 38, 188, 38);
        colorArray[2] = Color.argb(255, 214, 233, 57);
        colorArray[3] = Color.argb(255, 255, 153, 32);
        colorArray[4] = Color.argb(255, 217, 63, 212);
	}

	 public void addItem(final Triplet<Integer,String,String> score) {
         mData.add(score);
         //notifyDataSetChanged();
     }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 ViewHolder holder;

		 
         // When convertView is not null, we can reuse it directly, there is no need
         // to reinflate it. We only inflate a new View when the convertView supplied
         // by ListView is null.
         if (convertView == null) {
             convertView = mInflater.inflate(R.layout.highscore_list_item, null);

             // Creates a ViewHolder and store references to the two children views
             // we want to bind data to.
             holder = new ViewHolder();
             holder.score = (TextView) convertView.findViewById(R.id.textScore);
             holder.pseudo = (TextView) convertView.findViewById(R.id.textPseudo);
             holder.date = (TextView) convertView.findViewById(R.id.textDate);
             
             convertView.setTag(holder);
         } else {
             // Get the ViewHolder back to get fast access to the TextView
             // and the ImageView.
             holder = (ViewHolder) convertView.getTag();
         }

         // Bind the data efficiently with the holder.
         //System.out.println("Pseudo : "+mData.get(position).getB()+" et date : "+mData.get(position).getC());
         holder.score.setText(Integer.toString(mData.get(position).getA()));
         holder.pseudo.setText(mData.get(position).getB());
         holder.date.setText(mData.get(position).getC());
         
         
         return convertView;
     }

     static class ViewHolder {
         TextView score;
         TextView pseudo;
         TextView date;
     }

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return true;
	}

}
