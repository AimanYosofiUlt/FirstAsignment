package com.example.firstdayjava.ui.views.UserView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.local.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    List<User> list = new ArrayList<>();

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    UserViewListener listener;


    public UserAdapter(UserViewListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user,parent,false),
                listener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
