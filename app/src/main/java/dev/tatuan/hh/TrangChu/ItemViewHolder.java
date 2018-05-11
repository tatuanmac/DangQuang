package dev.tatuan.hh.TrangChu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.tatuan.hh.ItemClickListener;
import dev.tatuan.hh.R;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView img_hinhAnh;
    public TextView tv_tenDongHo, tv_giaDongHo;
    private ItemClickListener itemClickListener;

    public ItemViewHolder(View itemView) {
        super(itemView);
        img_hinhAnh = itemView.findViewById(R.id.img_hinhAnh);

//        tv_tenDongHo = itemView.findViewById(R.id.tv_tenDongHo);
        tv_giaDongHo = itemView.findViewById(R.id.tv_giaDongHo);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}