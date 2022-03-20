package com.example.firstdayjava.ui.views.TypeCardView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;

import java.util.ArrayList;
import java.util.List;

public class TypeCardViewAdapter extends RecyclerView.Adapter<TypeCardViewHolder> implements TypeCardViewHolder.TypeCardListener {
    List<TypeCardData> list = new ArrayList<>();


    @NonNull
    @Override
    public TypeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TypeCardViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_type, parent,false),
                this
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TypeCardViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addTypeCard(String name){
        list.add(
                new TypeCardData(name)
        );
    }

    @Override
    public void onCheckChange(TypeCardData type) {
    }
}
