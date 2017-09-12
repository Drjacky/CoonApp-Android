package com.jodelapp.features.photos.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jodelapp.R;
import com.jodelapp.features.photos.models.UserPhotoPresentationModel;
import com.squareup.picasso.Picasso;
import java.util.List;

public class UserPhotoListAdapter extends RecyclerView.Adapter<UserPhotoListAdapter.MyViewHolder>{
    private Context mContext;
    private List<UserPhotoPresentationModel> photosList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mId;
        public ImageView mThumbnail;

        public MyViewHolder(View view) {
            super(view);
            mId = view.findViewById(R.id.item_list_photo_txtPhotoId);
            mThumbnail = view.findViewById(R.id.item_list_photo_imgPhotoThumbnail);
        }
    }

    public UserPhotoListAdapter(Context mContext, List<UserPhotoPresentationModel> photosList) {
        this.mContext = mContext;
        this.photosList = photosList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_photo, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final UserPhotoPresentationModel photo = photosList.get(position);
        holder.mId.setText(photo.getId());

        Picasso.with(mContext)
                .load(photo.getThumbnailUrl()) // Thumbnail URL
                .into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return photosList == null ? 0 : photosList.size(); // For the first time (viewInit() in UserPhotoListView class), before we get albumId from server, photosList is null.
    }
}
