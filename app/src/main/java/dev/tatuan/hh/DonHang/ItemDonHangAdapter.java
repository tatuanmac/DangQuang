package dev.tatuan.hh.DonHang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import dev.tatuan.hh.R;

/**
 * Created by Anh on 21/04/2018.
 */

public class ItemDonHangAdapter extends RecyclerView.Adapter<ItemDonHangViewHolder> {

    private ArrayList<DonHangData> arrDonHang;
    private Context mContext;

    public ItemDonHangAdapter(ArrayList<DonHangData> arrDonHang, Context mContext) {
        this.arrDonHang = arrDonHang;
        this.mContext = mContext;
    }

    @Override
    public ItemDonHangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_child_donhang, parent, false);
        return new ItemDonHangViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ItemDonHangViewHolder holder, int position) {
        DonHangData donHangData = arrDonHang.get(position);

        Picasso.with(mContext)
                .load(donHangData.getHinhanh())
                .into(holder.imgDonHang);

        holder.txtWatchName.setText(donHangData.getTendh());

        holder.txtCountWatch.setText(donHangData.getSoluong());

        long tien = Long.parseLong(donHangData.getTongtien());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.txtAmount.setText(NumberFormat.getNumberInstance(Locale.US).format(tien) + " đ");
        }
    }

    @Override
    public int getItemCount() {
        return arrDonHang.size();
    }
}
