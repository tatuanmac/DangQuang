package dev.tatuan.hh;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import dev.tatuan.hh.DonHang.Fragment_DonHang;
import dev.tatuan.hh.GioHanng.Fragment_GioHang;
import dev.tatuan.hh.Profile.Fragment_Profile;
import dev.tatuan.hh.TrangChu.Fragment_TrangChu;

public class MainActivity extends AppCompatActivity {

    Bundle bundle;
    String Phone,Address,Name,Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment_TrangChu()).commit();

        bundle = getIntent().getExtras();
        Phone = bundle.getString("Phone");
        Address = bundle.getString("Address");
        Name = bundle.getString("Name");
        Pass = bundle.getString("Pass");
        //
        SharedPreferences sPre = getSharedPreferences("DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPre.edit();
        editor.putString("phone", Phone);
        editor.putString("address", Address);
        editor.putString("name", Name);
        editor.putString("pass",Pass);
        editor.apply();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
            switch (item.getItemId()) {
                case R.id.nav_trangchu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment_TrangChu()).commit();

                    break;
                case R.id.nav_giohang:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment_GioHang()).commit();

                    break;
                case R.id.nav_donhang:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment_DonHang()).commit();

                    break;
                case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment_Profile()).commit();
                    break;
            }
            return true;
        }
    };

    @Override
    public void onBackPressed() {

    }
}
