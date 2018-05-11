package dev.tatuan.hh.Profile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dev.tatuan.hh.DangKy.DData;
import dev.tatuan.hh.R;


public class Fragment_Profile extends Fragment {
    String phone, address, name, pass;
    TextView tv_phone, tv_address, tv_name;
    ImageView img_change;
    Dialog changeDialog;
    EditText edt_ThayDoiTen, edt_ThayDoiDiaChi;
    TextView tv_applyThayDoi;
    DatabaseReference mDataChange;
    String ten, diachi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
        phone = sharedPreferences.getString("phone", "");
        address = sharedPreferences.getString("address", "");
        name = sharedPreferences.getString("name", "");
        pass = sharedPreferences.getString("pass", "");


        //
        tv_name = view.findViewById(R.id.tv_name);
        tv_address = view.findViewById(R.id.tv_diachi);
        tv_phone = view.findViewById(R.id.tv_phone);

        tv_phone.setText(phone);
        tv_name.setText(name);
        tv_address.setText(address);


        //
        img_change = view.findViewById(R.id.img_change);

        //


        changeDialog = new Dialog(getActivity(), R.style.MaterialDialogSheet);
        changeDialog.setContentView(R.layout.thaydoi_thongtin);
        changeDialog.setCancelable(true);
        changeDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        changeDialog.getWindow().setGravity(Gravity.CENTER);


        //
        tv_applyThayDoi = changeDialog.findViewById(R.id.tv_ThayDoi);
        //
        edt_ThayDoiTen = changeDialog.findViewById(R.id.edt_ThayDoiTen);
        edt_ThayDoiDiaChi = changeDialog.findViewById(R.id.edt_ThayDoiDiaChi);


        img_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDialog.show();
            }
        });




        tv_applyThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten = edt_ThayDoiTen.getText().toString().trim();
                diachi = edt_ThayDoiDiaChi.getText().toString().trim();

                mDataChange = FirebaseDatabase.getInstance().getReference("account").child("user").child(phone);

                mDataChange.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            DData dData = new DData(phone, pass, ten, diachi);
                            mDataChange.setValue(dData);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                changeDialog.dismiss();
            }
        });


        return view;

    }
}
