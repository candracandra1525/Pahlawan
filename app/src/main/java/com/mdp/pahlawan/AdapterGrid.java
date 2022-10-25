package com.mdp.pahlawan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ViewHolder> {
    private ArrayList <ModelPahlawan> dataPahlawan;

    public AdapterGrid(ArrayList<ModelPahlawan> dataPahlawan)
    {
        this.dataPahlawan = dataPahlawan;
    }

    @NonNull
    @Override
    public AdapterGrid.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGrid.ViewHolder holder, int position)
    {
        ModelPahlawan pahlawan = dataPahlawan.get(position);

        Glide.with(holder.itemView.getContext())
                .load(pahlawan.getFoto())
                .apply(new RequestOptions()).override(350,550)
                .into(holder.iv_grid);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = pahlawan.getNama();
                String tentang = pahlawan.getTentang();
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
    public int getItemCount() {
        return dataPahlawan.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_grid;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            iv_grid = itemView.findViewById(R.id.iv_grid);
        }
    }
}
