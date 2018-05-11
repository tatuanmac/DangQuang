package dev.tatuan.hh.ThongTin;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import dev.tatuan.hh.GioHanng.GioHangData;
import dev.tatuan.hh.MuaNgay.MuaNgayAdapter;
import dev.tatuan.hh.MuaNgay.MuaNgayData;
import dev.tatuan.hh.MuaNgay.MuaNgayActivity;
import dev.tatuan.hh.R;

import static android.os.Build.VERSION_CODES.N;

public class ThongTin extends AppCompatActivity {
    TextView tvif_giaDH, tvif_loaiDH, tvif_matKinh,
            tvif_xuatXu, tvif_tenDH, tvif_nangLuong;
    ImageView img_tt_hinhanh;
    TextView tv_addtocart, tvbuynow, tv_dathang;

    ImageView img_minus, img_plus;
    TextView tv_amount, tv_done;
    String name, type, price, energy, glass, madein, image;
    //dialog boottom
    private Dialog mBottommSheetDialog;

    int amount = 1;

    Bundle bundle;
    String phone, soLuong, tien;

    long gia, tongtien;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_tin_san_pham_layout);

        init();
        information();
        themVaoGioHang();
        muaNgay();

        SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        phone = sharedPreferences.getString("phone", "");

    }

    void init() {
        tv_addtocart = (TextView) findViewById(R.id.tv_addtocart);
        tvbuynow = (TextView) findViewById(R.id.tvmuangay);

        tvif_loaiDH = (TextView) findViewById(R.id.tvif_loaiDH);
        tvif_tenDH = (TextView) findViewById(R.id.tvif_tenDH);
        tvif_giaDH = (TextView) findViewById(R.id.tvif_giaDH);
        tvif_matKinh = (TextView) findViewById(R.id.tvif_matKinh);
        tvif_nangLuong = (TextView) findViewById(R.id.tvif_nangLuong);
        tvif_xuatXu = (TextView) findViewById(R.id.tvif_xuatXu);
        img_tt_hinhanh = (ImageView) findViewById(R.id.imgif_DH);

        //
        mBottommSheetDialog = new Dialog(this, R.style.MaterialDialogSheet);
        mBottommSheetDialog.setContentView(R.layout.soluong);
        mBottommSheetDialog.setCancelable(true);
        mBottommSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottommSheetDialog.getWindow().setGravity(Gravity.BOTTOM);

        img_minus = mBottommSheetDialog.findViewById(R.id.img_minus);
        img_plus = mBottommSheetDialog.findViewById(R.id.img_plus);
        tv_amount = mBottommSheetDialog.findViewById(R.id.tv_amount);
        tv_done = mBottommSheetDialog.findViewById(R.id.tv_done);

    }

    public void information() {
        bundle = getIntent().getExtras();
        name = bundle.getString("name");
        type = bundle.getString("type");
        energy = bundle.getString("energy");
        glass = bundle.getString("glass");
        madein = bundle.getString("madein");
        image = bundle.getString("image");
        price = bundle.getString("price");

        gia = Long.parseLong(price);


        Picasso.with(getApplicationContext()).load(image).into(img_tt_hinhanh);
        tvif_tenDH.setText(name);
        tvif_loaiDH.setText(type);
        if (Build.VERSION.SDK_INT >= N) {
            tvif_giaDH.setText(NumberFormat.getNumberInstance(Locale.US).format(gia));
        }
        tvif_matKinh.setText(glass);
        tvif_nangLuong.setText(energy);
        tvif_xuatXu.setText(madein);

    }

    private void amount() {

        mBottommSheetDialog.show();
        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount += 1;
                tv_amount.setText(String.valueOf("" + amount));
            }
        });
        img_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (amount <= 1) {
                    amount = 1;
                } else {
                    amount = amount - 1;
                }

                tv_amount.setText(String.valueOf("" + amount));
            }
        });

    }


    public void themVaoGioHang() {
        final String tendh = tvif_tenDH.getText().toString().trim();

        //add to cart
        tv_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount();

                tv_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //post string to cart
                        final DatabaseReference DonHang = FirebaseDatabase.getInstance().getReference("giohang").child(phone);
                        final String id = DonHang.push().getKey();
                        final DatabaseReference myRef = DonHang.child(id);


                        soLuong = tv_amount.getText().toString();
                        Long soluongLong = Long.parseLong(soLuong);
                        tongtien = soluongLong * gia;

                        tien = String.valueOf(tongtien);

                        // Read from the database
                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getValue() == null) {

                                    GioHangData GioHangData = new GioHangData(id,
                                            soLuong,
                                            tendh,
                                            tien,
                                            image);

                                    myRef.setValue(GioHangData);

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });
                        mBottommSheetDialog.dismiss();
                        finish();
                    }
                });
            }

        });

    }
    public void muaNgay() {
        final String tendh = tvif_tenDH.getText().toString().trim();

        tvbuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount();

                tv_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        soLuong = tv_amount.getText().toString();
                        Long soluongLong = Long.parseLong(soLuong);

                        tongtien = soluongLong * gia;

                        tien = String.valueOf(tongtien);

                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("abcd").child(phone);
                        final String id = db.push().getKey();
                        final DatabaseReference myRef = db.child(id);



                        SharedPreferences sPre = getSharedPreferences("DATA", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sPre.edit();
                        editor.putString("idd", id);
                        editor.apply();



                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                GioHangData Data = new GioHangData(id, soLuong, tendh, tien, image);
                                myRef.setValue(Data);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        mBottommSheetDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),MuaNgayActivity.class));
                        finish();
                    }
                });

            }
        });
    }

}
