package dev.tatuan.hh.GioHanng;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import dev.tatuan.hh.R;


public class GioHangAdapter extends RecyclerView.Adapter<GioHangViewHolder> {
    ArrayList<GioHangData> gioHangData;
    Context mContext;
    //    boolean check = false;
    DatabaseReference mData, db;
    String phone;
//    long tong1 = 0, longTienBanDau;

    public GioHangAdapter(ArrayList<GioHangData> gioHangData, Context mContext) {
        this.gioHangData = gioHangData;
        this.mContext = mContext;
    }

//    public void setCheck(boolean check) {
//        this.check = check;
//    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public GioHangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_giohang, parent, false);


        mData = FirebaseDatabase.getInstance().getReference();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        phone = sharedPreferences.getString("phone", "");

        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GioHangViewHolder holder, final int position) {
        final GioHangData data = gioHangData.get(position);

        final String soLuong = data.getSoluong();
        final String ten = data.getTendh();
        final String tongTien = data.getTongtien();
        final String hinhAnh = data.getHinhanh();

        holder.tv_ten.setText(ten);

        holder.tv_soluong.setText(soLuong);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tv_tien.setText(NumberFormat.getNumberInstance(Locale.US).format(Long.parseLong(tongTien)) + " đ");
        }

        Picasso.with(mContext).load(hinhAnh).into(holder.img_hinhanh);

        final AlertDialog.Builder aler = new AlertDialog.Builder(mContext);// dialog
        //xoá các item trong giỏ hàng
        holder.trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aler.setCancelable(false);
                aler.setMessage("Xoá sản phẩm đã chọn?");
                aler.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String id = data.getId();
                        DatabaseReference myRef = mData.child("giohang").child(phone).child(id);
                        myRef.removeValue();
                        db = mData.child("abc").child(phone).child(String.valueOf(position));
                        db.removeValue();
                    }
                });
                aler.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                aler.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return gioHangData.size();
    }
}
