package dev.tatuan.hh.MuaNgay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.tatuan.hh.R;

/**
 * Created by tatuan on 17/04/2018.
 */

public class MuaNgayViewHolder extends RecyclerView.ViewHolder {

    ImageView img_hinhanh;
    TextView tv_ten, tv_soluong, tv_tien;

    public MuaNgayViewHolder(View itemView) {
        super(itemView);
        img_hinhanh = itemView.findViewById(R.id.img_muangay_hinhanh);


        tv_ten = itemView.findViewById(R.id.tv_muangay_ten);

        tv_soluong = itemView.findViewById(R.id.tv_muangay_soluong);

        tv_tien = itemView.findViewById(R.id.tv_muangay_sotien);
    }
}
