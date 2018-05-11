package dev.tatuan.hh.DangKy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dev.tatuan.hh.MainActivity;
import dev.tatuan.hh.R;

import static dev.tatuan.hh.R.id.signUp_Name;
import static dev.tatuan.hh.R.id.signUp_Phone;
import static dev.tatuan.hh.R.id.signUp_passWord;

/**
 * Created by tatuan on 17/04/2018.
 */

public class Register extends AppCompatActivity {
    EditText edt_sdt, edt_MatKhau, edt_HoTen, edt_DiaChi;
    TextView tv_DangKy;
    DatabaseReference dbDangKy;
    ProgressDialog mDialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        dangky();
    }

    private void dangky() {
        dbDangKy = FirebaseDatabase.getInstance().getReference("account").child("user");
        tv_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new ProgressDialog(Register.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                dbDangKy.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edt_sdt.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(Register.this, "Số điện thoại đã được đăng ký!", Toast.LENGTH_SHORT).show();

                        } else {
                            mDialog.dismiss();
                            //
                            DData data = new DData(edt_sdt.getText().toString(),edt_MatKhau.getText().toString(), edt_HoTen.getText().toString(), edt_DiaChi.getText().toString());
                            //
                            dbDangKy.child(edt_sdt.getText().toString()).setValue(data);

                            Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            //
                            intent.putExtra("Name", data.getTen().trim());
                            intent.putExtra("Phone", data.getSodienthoai());
                            intent.putExtra("Address", data.getDiachi().trim());
                            intent.putExtra("Pass", data.getMatkhau().trim());

                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void init() {
        edt_sdt = (EditText) findViewById(signUp_Phone);

        edt_MatKhau = (EditText) findViewById(signUp_passWord);

        edt_HoTen = (EditText) findViewById(signUp_Name);

        edt_DiaChi = (EditText) findViewById(R.id.signUp_DiaChi);

        tv_DangKy = (TextView) findViewById(R.id.tv_signUp);
    }

}
