package com.mdp.pahlawan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AdapterCard extends RecyclerView.Adapter<AdapterCard.ViewHolder>
{
    private ArrayList<ModelPahlawan> dataPahlawan;
    private String appLang;

    public AdapterCard(ArrayList<ModelPahlawan> dataPahlawan, String appLang)
    {
        this.dataPahlawan = dataPahlawan;
        this.appLang = appLang;
    }

    @NonNull
    @Override
    public AdapterCard.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCard.ViewHolder holder, int position)
    {
        // Tampilkan data
        ModelPahlawan pahlawan = dataPahlawan.get(position);
        holder.tv_Nama.setText(pahlawan.getNama());
        holder.tv_Tentang.setText((appLang.equals("id")) ? pahlawan.getTentang() : pahlawan.getAbout());
        Glide.with(holder.itemView.getContext())
                .load(pahlawan.getFoto())
                .apply(new RequestOptions()
                .override(350,550))
                .into(holder.iv_foto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = pahlawan.getNama();
                String tentang = (appLang.equals("id")) ? pahlawan.getTentang() : pahlawan.getAbout();
                String foto = pahlawan.getFoto();
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("varNama", nama);
                intent.putExtra("varTentang", tentang);
                intent.putExtra("varFoto", foto);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataPahlawan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_foto;
        TextView tv_Nama, tv_Tentang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_foto = itemView.findViewById(R.id.iv_foto);
            tv_Nama = itemView.findViewById(R.id.tv_nama);
            tv_Tentang = itemView.findViewById(R.id.tv_tentang);
        }
    }
}
