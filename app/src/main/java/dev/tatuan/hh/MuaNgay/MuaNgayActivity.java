package dev.tatuan.hh.MuaNgay;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

import dev.tatuan.hh.R;

/**
 * Created by tatuan on 18/04/2018.
 */

public class MuaNgayActivity extends AppCompatActivity {
    RecyclerView rcItem;

    ArrayList<MuaNgayData> muaNgayDatas;
    MuaNgayAdapter muaNgayAdapter;

    LinearLayoutManager Verti;

    MuaNgayData Cdata;

    String phone;

    DatabaseReference databaseReference, dathang;

    TextView tv_dathang;

    //Calenda
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.muahanglayout);


        SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        phone = sharedPreferences.getString("phone", "");

        muaNgayDatas = new ArrayList<>();
        muaNgayAdapter = new MuaNgayAdapter(getApplicationContext(), muaNgayDatas);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        rcItem = (RecyclerView) findViewById(R.id.rcItem);
        Verti = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        getData();
        rcItem.setLayoutManager(Verti);
        rcItem.setAdapter(muaNgayAdapter);


        datHang();
    }

    private void getData() {
        databaseReference.child("abcd").child(phone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Cdata = data.getValue(MuaNgayData.class);
                        muaNgayDatas.add(Cdata);
                    }
                }
                muaNgayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void datHang() {
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = simpleDateFormat.format(calendar.getTime());
        tv_dathang = (TextView) findViewById(R.id.tv_dathangg);
        tv_dathang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dathang = FirebaseDatabase.getInstance().getReference("donhangcuatoi").child(phone);

                final DatabaseReference db = dathang.child(Date);

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        db.setValue(muaNgayDatas);

                        DatabaseReference kk = FirebaseDatabase.getInstance().getReference("abcd").child(phone);
                        kk.removeValue();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DatabaseReference kk = FirebaseDatabase.getInstance().getReference("abcd").child(phone);
        kk.removeValue();
        super.onBackPressed();

    }
}
