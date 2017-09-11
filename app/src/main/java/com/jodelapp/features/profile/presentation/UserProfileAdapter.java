package com.jodelapp.features.profile.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jodelapp.R;
import com.jodelapp.features.profile.models.UserProfilePresentationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class UserProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final PublishSubject<UserProfilePresentationModel> onClickSubject = PublishSubject.create();
    private final List<UserProfilePresentationModel> userDataList = new ArrayList<>();

    public UserProfileAdapter(List<UserProfilePresentationModel> userDataList) {
        this.userDataList.clear();
        this.userDataList.addAll(userDataList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_user, parent, false);

        if(userDataList.size() > 0)
            onClickSubject.onNext(userDataList.get(0)); // To set a default user for profile.

        return new UserItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserProfilePresentationModel userProfilePresentationModel = userDataList.get(position);
        ((UserItemViewHolder) holder).render(userProfilePresentationModel);
    }

    public Observable<UserProfilePresentationModel> getPositionClicks(){
        return onClickSubject;
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }


    class UserItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_txtName)
        TextView txtName;
        @BindView(R.id.user_txtId)
        TextView txtId;

        UserItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void render(UserProfilePresentationModel userProfilePresentationModel) {
            txtName.setText(userProfilePresentationModel.getName());
            txtId.setText(userProfilePresentationModel.getId());

            itemView.setOnClickListener(v -> {
                onClickSubject.onNext(userProfilePresentationModel); //Send selected user Model to the UserProfileView fragment.
            });
        }
    }
}
