package dev.tatuan.hh.DonHang;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dev.tatuan.hh.R;


public class DonHangViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView recyclerDonHang;
    TextView tv_time;

    public DonHangViewHolder(View itemView) {
        super(itemView);
        tv_time = itemView.findViewById(R.id.tv_time);
        recyclerDonHang = itemView.findViewById(R.id.recyclerDonHang);
    }
}
