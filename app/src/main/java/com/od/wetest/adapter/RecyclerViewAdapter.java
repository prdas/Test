package com.od.wetest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.od.wetest.R;
import com.od.wetest.model.DataModel;
import com.od.wetest.util.PicassoTrustAll;
import com.od.wetest.view.ScaleImageView;

import java.util.ArrayList;

/**
 * The type Recycler view adapter.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomRecyclerView> {

    private ArrayList<DataModel.Rows> itemList;

    private ImageLoader mImageLoader;
    private Context context;


    /**
     * Instantiates a new Recycler view adapter.
     *
     * @param context  the context
     * @param itemList the item list
     */
    public RecyclerViewAdapter(Context context, ArrayList<DataModel.Rows> itemList) {
        this.itemList = itemList;
        this.context = context;
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
        if(myData.getImageHref() != null) {
            PicassoTrustAll.getInstance(context)
                    .load(myData.getImageHref())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.networkImageView);
        }

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    /**
     * The type Custom recycler view.
     */
    class CustomRecyclerView extends RecyclerView.ViewHolder {
        /**
         * The Txt title.
         */
        TextView txtTitle;
        /**
         * The Txt description.
         */
        TextView txtDescription;
        /**
         * The Network image view.
         */
        ScaleImageView networkImageView;

        /**
         * Instantiates a new Custom recycler view.
         *
         * @param itemView the item view
         */
        CustomRecyclerView(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            networkImageView = (ScaleImageView) itemView.findViewById(R.id.imgNetwork);
        }
    }
}