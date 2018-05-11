package dev.tatuan.hh.TrangChu;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import dev.tatuan.hh.ItemClickListener;
import dev.tatuan.hh.R;
import dev.tatuan.hh.ThongTin.ThongTin;


public class Fragment_TrangChu extends Fragment {

    RecyclerView rc_casio, rc_citizen, rc_bruno, rc_atlantic, rc_jacque, rc_stuhrling;
    StaggeredGridLayoutManager gridLayoutManager;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trangchu2, container, false);

        rc_casio = view.findViewById(R.id.rc_casio);
        casio_data();
        rc_citizen = view.findViewById(R.id.rc_citizen);
        citizen_data();
        rc_bruno = view.findViewById(R.id.rc_bruno);
        bruno_data();
        rc_atlantic = view.findViewById(R.id.rc_atlantic);
        atlantic_data();
        rc_jacque = view.findViewById(R.id.rc_jacque);
        jacque_data();
        rc_stuhrling = view.findViewById(R.id.rc_stuhrling);
        stuhrling_data();
        return view;
    }

    private void stuhrling_data() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Watch").child("Stuhrling Original Tourbillon");
        FirebaseRecyclerAdapter<Data, ItemViewHolder> adapter = new FirebaseRecyclerAdapter<Data, ItemViewHolder>(Data.class, R.layout.item, ItemViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(final ItemViewHolder viewHolder, final Data model, int position) {
                Picasso.with(getActivity()).load(String.valueOf(model.getLinkHA())).into(viewHolder.img_hinhAnh);

                long tien = Long.parseLong(model.getGiaDH());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    viewHolder.tv_giaDongHo.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));
                }
                final Data clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(getContext(), ThongTin.class);

                        intent.putExtra("name", clickItem.getTenDH());
                        intent.putExtra("type", clickItem.getLoaiDH());
                        intent.putExtra("price", clickItem.getGiaDH());
                        intent.putExtra("energy", clickItem.getNangLuong());
                        intent.putExtra("glass", clickItem.getMatKinh());
                        intent.putExtra("madein", clickItem.getXuatXu());
                        intent.putExtra("image", clickItem.getLinkHA());

                        startActivity(intent);
                    }
                });
            }
        };

        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rc_stuhrling.setLayoutManager(gridLayoutManager);
        rc_stuhrling.setAdapter(adapter);
    }

    private void jacque_data() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Watch").child("Jacque Lemans");
        FirebaseRecyclerAdapter<Data, ItemViewHolder> adapter = new FirebaseRecyclerAdapter<Data, ItemViewHolder>(Data.class, R.layout.item, ItemViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(final ItemViewHolder viewHolder, final Data model, int position) {
                Picasso.with(getActivity()).load(String.valueOf(model.getLinkHA())).into(viewHolder.img_hinhAnh);

                long tien = Long.parseLong(model.getGiaDH());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    viewHolder.tv_giaDongHo.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));
                }
                final Data clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(getContext(), ThongTin.class);

                        intent.putExtra("name", clickItem.getTenDH());
                        intent.putExtra("type", clickItem.getLoaiDH());
                        intent.putExtra("price", clickItem.getGiaDH());
                        intent.putExtra("energy", clickItem.getNangLuong());
                        intent.putExtra("glass", clickItem.getMatKinh());
                        intent.putExtra("madein", clickItem.getXuatXu());
                        intent.putExtra("image", clickItem.getLinkHA());

                        startActivity(intent);
                    }
                });
            }
        };

        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rc_jacque.setLayoutManager(gridLayoutManager);
        rc_jacque.setAdapter(adapter);
    }

    private void atlantic_data() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Watch").child("Atlantic");
        FirebaseRecyclerAdapter<Data, ItemViewHolder> adapter = new FirebaseRecyclerAdapter<Data, ItemViewHolder>(Data.class, R.layout.item, ItemViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(final ItemViewHolder viewHolder, final Data model, int position) {
                Picasso.with(getActivity()).load(String.valueOf(model.getLinkHA())).into(viewHolder.img_hinhAnh);

                long tien = Long.parseLong(model.getGiaDH());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    viewHolder.tv_giaDongHo.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));
                }
                final Data clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(getContext(), ThongTin.class);

                        intent.putExtra("name", clickItem.getTenDH());
                        intent.putExtra("type", clickItem.getLoaiDH());
                        intent.putExtra("price", clickItem.getGiaDH());
                        intent.putExtra("energy", clickItem.getNangLuong());
                        intent.putExtra("glass", clickItem.getMatKinh());
                        intent.putExtra("madein", clickItem.getXuatXu());
                        intent.putExtra("image", clickItem.getLinkHA());

                        startActivity(intent);
                    }
                });
            }
        };
        //
        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rc_atlantic.setLayoutManager(gridLayoutManager);
        rc_atlantic.setAdapter(adapter);
    }

    private void bruno_data() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Watch").child("Bruno");
        FirebaseRecyclerAdapter<Data, ItemViewHolder> adapter = new FirebaseRecyclerAdapter<Data, ItemViewHolder>(Data.class, R.layout.item, ItemViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(final ItemViewHolder viewHolder, final Data model, int position) {

                Picasso.with(getActivity()).load(String.valueOf(model.getLinkHA())).into(viewHolder.img_hinhAnh);


                long tien = Long.parseLong(model.getGiaDH());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    viewHolder.tv_giaDongHo.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));
                }
                final Data clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(getContext(), ThongTin.class);

                        intent.putExtra("name", clickItem.getTenDH());
                        intent.putExtra("type", clickItem.getLoaiDH());
                        intent.putExtra("price", clickItem.getGiaDH());
                        intent.putExtra("energy", clickItem.getNangLuong());
                        intent.putExtra("glass", clickItem.getMatKinh());
                        intent.putExtra("madein", clickItem.getXuatXu());
                        intent.putExtra("image", clickItem.getLinkHA());

                        startActivity(intent);
                    }
                });
            }
        };
        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rc_bruno.setLayoutManager(gridLayoutManager);
        rc_bruno.setAdapter(adapter);
    }

    private void citizen_data() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Watch").child("Citizen");
        FirebaseRecyclerAdapter<Data, ItemViewHolder> adapter = new FirebaseRecyclerAdapter<Data, ItemViewHolder>(Data.class, R.layout.item, ItemViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(final ItemViewHolder viewHolder, final Data model, int position) {
                Picasso.with(getActivity()).load(String.valueOf(model.getLinkHA())).into(viewHolder.img_hinhAnh);

                long tien = Long.parseLong(model.getGiaDH());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    viewHolder.tv_giaDongHo.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));
                }
                final Data clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(getContext(), ThongTin.class);

                        intent.putExtra("name", clickItem.getTenDH());
                        intent.putExtra("type", clickItem.getLoaiDH());
                        intent.putExtra("price", clickItem.getGiaDH());
                        intent.putExtra("energy", clickItem.getNangLuong());
                        intent.putExtra("glass", clickItem.getMatKinh());
                        intent.putExtra("madein", clickItem.getXuatXu());
                        intent.putExtra("image", clickItem.getLinkHA());

                        startActivity(intent);
                    }
                });
            }
        };
        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rc_citizen.setLayoutManager(gridLayoutManager);
        rc_citizen.setAdapter(adapter);
    }

    //casio
    public void casio_data() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Watch").child("Casio");
        FirebaseRecyclerAdapter<Data, ItemViewHolder> adapter = new FirebaseRecyclerAdapter<Data, ItemViewHolder>(Data.class, R.layout.item, ItemViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(final ItemViewHolder viewHolder, final Data model, int position) {
                Picasso.with(getActivity()).load(String.valueOf(model.getLinkHA())).into(viewHolder.img_hinhAnh);
//
                long tien = Long.parseLong(model.getGiaDH());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    viewHolder.tv_giaDongHo.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));
                }
                final Data clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(getContext(), ThongTin.class);
                        intent.putExtra("name", clickItem.getTenDH());
                        intent.putExtra("type", clickItem.getLoaiDH());
                        intent.putExtra("price", clickItem.getGiaDH());
                        intent.putExtra("energy", clickItem.getNangLuong());
                        intent.putExtra("glass", clickItem.getMatKinh());
                        intent.putExtra("madein", clickItem.getXuatXu());
                        intent.putExtra("image", clickItem.getLinkHA());

                        startActivity(intent);
                    }
                });
            }
        };

        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rc_casio.setLayoutManager(gridLayoutManager);
        rc_casio.setAdapter(adapter);
    }
}
