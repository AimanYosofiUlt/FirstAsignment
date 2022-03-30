package com.example.firstdayjava.ui.views.SubCategoryCardView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.local.entities.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryViewHolder> implements SubCategoryCardListener {
    List<SubCategoryCard> list = new ArrayList<>();
    SubCategoryViewHolder currentHolder;
    SubCategoryAdapterListener listener;

    public SubCategoryAdapter(SubCategoryAdapterListener listener) {
        this.listener = listener;
    }

    public void setList(List<SubCategory> subCategories) {
        list.clear();
        for (SubCategory subCategory : subCategories) {
            SubCategoryCard card = new SubCategoryCard(subCategory);
            list.add(card);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubCategoryViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_type, parent, false),
                this
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onCheckChange(SubCategoryViewHolder holder) {
        reorderListCheckValue(holder);
        setHolderCheck(holder);
    }

    private void setHolderCheck(SubCategoryViewHolder holder) {

        String code = "";
        if (currentHolder != holder) {
            holder.setChecked();
            if (currentHolder != null) {
                currentHolder.setNotChecked();
            }
            currentHolder = holder;

            code = holder.card.getSubCategory().getCode();
        } else {
            holder.setNotChecked();
            currentHolder = null;
        }

        listener.onSubCategoryChange(code);
    }

    private void reorderListCheckValue(SubCategoryViewHolder holder) {
        for (SubCategoryCard card : list) {
            card.isChecked = false;
        }
        list.get(list.indexOf(holder.card)).isChecked = holder.card.isChecked;
    }
}
