package dev.tatuan.hh.DonHang;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.tatuan.hh.R;

/**
 * Created by Anh on 21/04/2018.
 */

public class ItemDonHangViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgDonHang;
    public TextView txtWatchName, txtCountWatch, txtAmount;
    public ItemDonHangViewHolder(View itemView) {
        super(itemView);
        imgDonHang = itemView.findViewById(R.id.imgHinhAnhDH);
        txtCountWatch = itemView.findViewById(R.id.txtCountWatch);
        txtWatchName = itemView.findViewById(R.id.txtWatchName);
        txtAmount = itemView.findViewById(R.id.txtAmount);
    }
}
