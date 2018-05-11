package dev.tatuan.hh.GioHanng;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.tatuan.hh.R;


public class GioHangViewHolder extends RecyclerView.ViewHolder {

    //    CheckBox checkBox;
    ImageView img_hinhanh, trash;
    TextView tv_ten, tv_soluong, tv_tien;

    public GioHangViewHolder(View itemView) {
        super(itemView);
        img_hinhanh = itemView.findViewById(R.id.img_adapter_hinhanh);

        trash = itemView.findViewById(R.id.trash);

        tv_ten = itemView.findViewById(R.id.tv_gh_ten);

        tv_soluong = itemView.findViewById(R.id.tv_gh_soluong);

        tv_tien = itemView.findViewById(R.id.tv_gh_sotien);
    }
}
