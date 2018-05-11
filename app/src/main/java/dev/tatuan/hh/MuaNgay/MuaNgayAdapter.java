package dev.tatuan.hh.MuaNgay;

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
 * Created by tatuan on 17/04/2018.
 */

public class MuaNgayAdapter extends RecyclerView.Adapter<MuaNgayViewHolder> {
    Context mContext;
    ArrayList<MuaNgayData> datas;

    public MuaNgayAdapter(Context mContext, ArrayList<MuaNgayData> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public MuaNgayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_muangay,parent,false);


        return new MuaNgayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MuaNgayViewHolder holder, int position) {
        MuaNgayData data = datas.get(position);

        final String soLuong = data.getSoluong();
        final String ten = data.getTendh();
        final String tongTien = data.getTongtien();
        final String hinhAnh = data.getHinhanh();

        holder.tv_ten.setText(ten);

        holder.tv_soluong.setText(soLuong);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tv_tien.setText(NumberFormat.getNumberInstance(Locale.US).format(Long.parseLong(tongTien)));
        }

        Picasso.with(mContext).load(hinhAnh).into(holder.img_hinhanh);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
