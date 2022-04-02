package com.example.firstdayjava.ui.fragments.order_detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentOrderDetailBinding;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.models.orde_detile.OrderDetailsData;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;
import com.example.firstdayjava.ui.views.StepperView;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderDetailedFragment extends BaseFragment {

    @Inject
    OrderDetailedFragmentViewModel viewModel;
    FragmentOrderDetailBinding bd;
    String orderId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentOrderDetailBinding.inflate(inflater, container, false);
        orderId = OrderDetailedFragmentArgs.fromBundle(getArguments()).getOrderID();

        initViewModel();
        initModelView();
        initEvent();
        return bd.getRoot();
    }

    @Override
    protected void initViewModel() {
        viewModel.detailedListMDL.observe(getViewLifecycleOwner(), new Observer<List<OrderDetailsData>>() {
            @Override
            public void onChanged(List<OrderDetailsData> orderDetailsData) {
                float subTotal = 0;
                float taxes = 0;

                for (OrderDetailsData data : orderDetailsData) {
                    arrangeDetailsData(data);
                    subTotal += Float.parseFloat(data.getPrice()) * Float.parseFloat(data.getItemQty());
                    taxes += Float.parseFloat(data.getTax());
                }

                float total = subTotal + taxes;
                bd.subtTotalTV.setText(String.valueOf(subTotal));
                bd.taxesTV.setText(String.valueOf(taxes));
                bd.totalTV.setText(String.valueOf(total));
            }

            private void arrangeDetailsData(OrderDetailsData data) {
                TableRow.LayoutParams params = new TableRow.LayoutParams();
                params.gravity = Gravity.START;
                params.setMarginStart(10);

                TableRow rowLayout = new TableRow(requireContext());
                TextView nameTV = new TextView(requireContext());
                nameTV.setText(data.getItemName());
                nameTV.setLayoutParams(params);
                rowLayout.addView(nameTV);

                TextView quantityTV = new TextView(requireContext());
                quantityTV.setText(data.getItemQty());
                rowLayout.addView(quantityTV);

                TextView priceTV = new TextView(requireContext());
                priceTV.setText(data.getPrice());
                rowLayout.addView(priceTV);

                bd.tableLayout.addView(rowLayout);
            }
        });

        viewModel.cancelResponseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (responseState.isSuccessful()) {
                    NavHostFragment.findNavController(requireParentFragment()).popBackStack();
                    Toast.makeText(requireContext(), getString(R.string.cancled), Toast.LENGTH_SHORT).show();
                } else {
                    showErrorDialog(responseState.getMessage());
                }
            }

            private void showErrorDialog(String message) {
                new AlertDialog.Builder(requireContext())
                        .setMessage(message)
                        .setNegativeButton(getString(R.string.done), (dialogInterface, i) -> dialogInterface.cancel())
                        .create()
                        .show();
            }
        });
    }

    @Override
    protected void initModelView() {
        viewModel.getDetails(orderId);

        StepperView stepperView = new StepperView(requireContext());
        bd.stepperFL.addView(stepperView.getView());
    }

    @Override
    protected void initEvent() {
        bd.backBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment()).popBackStack());

        bd.cancleOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog((dialogInterface, i) -> viewModel.cancelOrder(orderId));
            }

            private void showDeleteDialog(DialogInterface.OnClickListener onClickListener) {
                new AlertDialog.Builder(requireContext())
                        .setMessage(getString(R.string.cancel_order_msg))
                        .setPositiveButton(getString(R.string.done), onClickListener)
                        .setNegativeButton(getString(R.string.cancle), (dialogInterface, i) -> dialogInterface.cancel())
                        .create()
                        .show();
            }
        });
    }
}
