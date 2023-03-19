package com.example.tlo1e12411.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlo1e12411.R;
import com.example.tlo1e12411.VerActivity;
import com.example.tlo1e12411.entidades.Contactos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.contactoViewHolder> {

    ArrayList<Contactos> listaContactos;
    ArrayList<Contactos> listaoriginal;
    public ListaContactosAdapter(ArrayList<Contactos> listaContactos){
      this.listaContactos = listaContactos;
      listaoriginal=new ArrayList<>();
      listaoriginal.addAll(listaContactos);
    }

    @NonNull
    @Override
    public ListaContactosAdapter.contactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null, false);
        return new contactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaContactosAdapter.contactoViewHolder holder, int position) {
holder.viewPais.setText(listaContactos.get(position).getPais());
        holder.viewNombre.setText(listaContactos.get(position).getNombre());
        holder.viewTelefono.setText(listaContactos.get(position).getTelefono());
        holder.viewnota.setText(listaContactos.get(position).getNota());
    }
public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
    if (longitud == 0) {
        listaContactos.clear();
        listaContactos.addAll(listaoriginal);
    }else{
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            List<Contactos> colletion = listaContactos.stream()
                    .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            listaContactos.clear();
            listaContactos.addAll(colletion);
        }else{
            for(Contactos c: listaoriginal){
                if(c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                    listaContactos.add(c);
                }
            }
        }
    }
    notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class contactoViewHolder extends RecyclerView.ViewHolder{

        TextView viewPais, viewNombre, viewTelefono, viewnota;
        public contactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewPais = itemView.findViewById(R.id.viewPais);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewnota = itemView.findViewById(R.id.viewNota);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, VerActivity.class);
                        intent.putExtra("ID", listaContactos.get(getAdapterPosition()).getId());
                        context.startActivity(intent);
                    }
                });
                }
            }
        }


