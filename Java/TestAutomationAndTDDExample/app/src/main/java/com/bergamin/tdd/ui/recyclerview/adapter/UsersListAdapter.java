package com.bergamin.tdd.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bergamin.tdd.R;
import com.bergamin.tdd.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    private final List<User> users = new ArrayList<>();
    private final Context context;

    public UsersListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void add(List<User> users) {
        for (User user : users) {
            add(user);
        }
    }

    public void add(User user) {
        users.add(user);
        notifyItemInserted(getItemCount() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView idWithUserName;

        ViewHolder(View itemView) {
            super(itemView);
            idWithUserName = itemView.findViewById(R.id.user_item_id_with_name);
        }

        void bind(User user) {
            idWithUserName.setText(user.toString());
        }
    }
}
