package dev.tatuan.hh.DonHang;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import dev.tatuan.hh.R;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_DonHang extends Fragment {

    private RecyclerView recylerDonHang;

    private DonHangAdapter adapter;

    private DatabaseReference mData;

    private String phone;

    private ArrayList<String> arrListDH;

    private HashMap<String, ArrayList<DonHangData>> mapDonHang;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donhang, container, false);

        recylerDonHang = view.findViewById(R.id.rcOfDonHang);


        getData();

        new LoadData().execute();

        return view;
    }

    private void getData() {
        mData = FirebaseDatabase.getInstance().getReference();
        mapDonHang = new HashMap<>();

        //
        arrListDH = new ArrayList<>();

        //
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
        phone = sharedPreferences.getString("phone", "");

        //
        adapter = new DonHangAdapter(arrListDH, getContext());

        recylerDonHang.setAdapter(adapter);
        recylerDonHang.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private class LoadData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //child tới node đơn hàng + số điện thoại
            mData.child("donhangcuatoi").child(phone).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    arrListDH.clear();
                    // lấy ra các value của node
                    if (dataSnapshot.getValue() != null) {
                        for (final DataSnapshot data : dataSnapshot.getChildren()) {

                            arrListDH.add(data.getKey());

                            final ArrayList<DonHangData> arrDonHang = new ArrayList<>();

                            //child tới node đơn hàng của tôi trong giỏ hàng
                            mData.child("donhangcuatoi").child(phone).child(data.getKey()).addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() != null) {
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                            arrDonHang.add(dataSnapshot1.getValue(DonHangData.class));
                                        }
                                        mapDonHang.put(data.getKey(), arrDonHang);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                publishProgress();
                            }
                        }, 500);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            adapter.setMapDonHang(mapDonHang);
            adapter.notifyDataSetChanged();
        }
    }

}
