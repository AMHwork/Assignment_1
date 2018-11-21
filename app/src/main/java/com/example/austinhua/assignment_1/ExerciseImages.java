package com.example.austinhua.assignment_1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by AustinHua on 19/12/2017.
 */

public class ExerciseImages extends BaseAdapter {

    private Context mContext;

    public Integer[] mThumdids = {
            R.mipmap.triceps,  R.mipmap.chest,  R.mipmap.shoulders,
            R.mipmap.biceps,  R.mipmap.back,  R.mipmap.thighs,
            R.mipmap.calf,  R.mipmap.glutes,  R.mipmap.custom,
            R.mipmap.home,  R.mipmap.program_icon,  R.mipmap.workout_icon,
    };

    public ExerciseImages(Context c){
        this.mContext = c;
    }

    @Override
    public int getCount(){
        return mThumdids.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ImageView imageView;
        if (convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        }
        else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumdids[position]);
        return imageView;
    }



}
