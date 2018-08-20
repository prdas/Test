package com.od.wetest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.od.wetest.R;
import com.od.wetest.communication.VolleyRequestQueue;
import com.od.wetest.model.DataModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomRecyclerView> {

    private ArrayList<DataModel.Rows> itemList;

    private ImageLoader mImageLoader;


    public RecyclerViewAdapter(Context context, ArrayList<DataModel.Rows> itemList) {
        this.itemList = itemList;
        RequestQueue mRequestQueue = VolleyRequestQueue.getInstance(context).getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    @Override
    public CustomRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, null);
        return new CustomRecyclerView(layoutView);
    }

    @Override
    public void onBindViewHolder(CustomRecyclerView holder, int position) {

        DataModel.Rows myData = itemList.get(position);
        if(myData.getTitle() != null)
        holder.txtTitle.setText(myData.getTitle());
        if(myData.getDescription() != null)
        holder.txtDescription.setText(myData.getDescription());
        if(myData.getImageHref() != null)
        holder.networkImageView.setImageUrl(myData.getImageHref(), mImageLoader);


    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    class CustomRecyclerView extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        NetworkImageView networkImageView;

        CustomRecyclerView(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.imgNetwork);
        }
    }
}