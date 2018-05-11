package dev.tatuan.hh.GioHanng;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dev.tatuan.hh.MuaNgay.MuaGioHang;
import dev.tatuan.hh.R;

import static android.content.Context.MODE_PRIVATE;


public class Fragment_GioHang extends Fragment {
    private RecyclerView rcCart;
    private String phone;
    private DatabaseReference mData, db;
    private GioHangAdapter adapter;
    private ArrayList<GioHangData> arrData;
    static TextView tvallMoney;
    private TextView datHang;
    LinearLayoutManager Hori;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giohang, container, false);

//        tvallMoney = view.findViewById(R.id.allMoney);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
        phone = sharedPreferences.getString("phone", "");
        //
        mData = FirebaseDatabase.getInstance().getReference();
        //
        arrData = new ArrayList<>();
        adapter = new GioHangAdapter(arrData, getActivity());

        rcCart = view.findViewById(R.id.rcOfCart);
        Hori = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        getData();
        rcCart.setLayoutManager(Hori);
        rcCart.setAdapter(adapter);

        datHang = view.findViewById(R.id.tv_gh_dathang);
        datHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseDatabase.getInstance().getReference("giohang").child(phone);
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            Toast.makeText(getActivity(), "Check your item", Toast.LENGTH_SHORT).show();
                        } else
                            startActivity(new Intent(getActivity(), MuaGioHang.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;

    }

//    public static void setTongTien(String tongTien) {
//
//        tvallMoney.setText(tongTien);
//    }

    GioHangData Cdata;

    private void getData() {
        mData.child("giohang").child(phone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrData.clear();
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Cdata = data.getValue(GioHangData.class);
                        arrData.add(Cdata);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
