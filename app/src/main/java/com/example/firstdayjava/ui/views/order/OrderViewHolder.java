package com.example.firstdayjava.ui.views.order;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewOrderBinding;
import com.example.firstdayjava.pojo.remote.models.getOrder.OrderMaster;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    ViewOrderBinding bd;
    OrderMaster master;
    OrderViewListener listener;

    public OrderViewHolder(@NonNull View itemView, OrderViewListener listener) {
        super(itemView);
        bd = ViewOrderBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    private void initEvent() {
        bd.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onShowReq(master.getOrderID());
            }
        });
    }

    void bind(OrderMaster master) {
        this.master = master;
        if (master.getStatus().equals("0"))
            bd.stateTV.setText(itemView.getContext().getString(R.string.processed));
        else if (master.getStatus().equals("1"))
            bd.stateTV.setText(itemView.getContext().getString(R.string.shipped));
        else
            bd.stateTV.setText(itemView.getContext().getString(R.string.delivered));

        int orderAmount = master.getOrderTotal() + master.getOrderTax() + master.getDeliveryCharge() - master.getOrderDiscount();
        bd.orderCode.setText("#" + master.getOrderID());
        bd.totalED.setText(String.valueOf(orderAmount));
        bd.dateED.setText(master.getOrderDate());
    }
}
