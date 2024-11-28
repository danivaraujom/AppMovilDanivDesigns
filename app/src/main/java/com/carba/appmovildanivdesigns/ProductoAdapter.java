package com.carba.appmovildanivdesigns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> listaProductos;
    private Context contexto;
    private OnItemClickListener listener;

    // Constructor del adaptador
    public ProductoAdapter(List<Producto> listaProductos, Context contexto, OnItemClickListener listener) {
        this.listaProductos = listaProductos;
        this.contexto = contexto;
        this.listener = listener;
    }
    // Actualiza la lista de productos
    public void actualizarLista(List<Producto> nuevaLista) {
        listaProductos = nuevaLista;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductoAdapter.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductoViewHolder(LayoutInflater.from(contexto).inflate(R.layout.item_producto, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        holder.nombreProducto.setText(producto.getNombre());
        holder.descripcionProducto.setText(producto.getDescripcion());
        holder.precioProducto.setText(String.format("%.2f €", producto.getPrecio()));
        holder.fotoProducto.setImageResource(producto.getImagen());
        // Cambia el icono del botonFavorito segun si el producto es favorito
        holder.botonFavorito.setImageResource(producto.esProductoFavorito() ? R.drawable.icono_si_favorito : R.drawable.icono_no_favorito);
        holder.botonFavorito.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClickFavorito(position);
            }
        });
        // Boton para añadir el producto a la cesta
        holder.botonAñadir.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClickAñadirCesta(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public interface OnItemClickListener {
        void onClickFavorito(int position);
        void onClickAñadirCesta(int position);
    }
    // ViewHolder que contiene las vistas de cada elemento del RecyclerView
    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoProducto;
        TextView nombreProducto;
        TextView descripcionProducto;
        TextView precioProducto;
        ImageButton botonFavorito;
        Button botonAñadir;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoProducto = itemView.findViewById(R.id.fotoProducto);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            descripcionProducto = itemView.findViewById(R.id.descripcionProducto);
            precioProducto = itemView.findViewById(R.id.precioProducto);
            botonFavorito = itemView.findViewById(R.id.botonFavorito);
            botonAñadir = itemView.findViewById(R.id.btnAñadir);
        }
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

