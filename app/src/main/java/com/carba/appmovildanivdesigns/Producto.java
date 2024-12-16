package com.carba.appmovildanivdesigns;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Producto implements Parcelable {
    private String nombre;
    private String descripcion;
    private double precio;
    private int imagen;
    private boolean esFavorito;
    private String tipo;
    protected Producto(){

    }
    protected Producto(Parcel in){
        nombre = in.readString();
        descripcion = in.readString();
        precio = in.readDouble();
        imagen = in.readInt();
        esFavorito = in.readByte() != 0; // Convertir byte a boolean
        tipo = in.readString();
    }

    public Producto(String nombre, String descripcion, double precio, int imagen, boolean esFavorito, String tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.esFavorito = esFavorito;
        this.tipo = tipo;
    }

    public final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public boolean esProductoFavorito() {
        return esFavorito;
    }

    public void setEsFavorito(boolean esFavorito) {
        this.esFavorito = esFavorito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", imagen=" + imagen +
                ", esFavorito=" + esFavorito +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(descripcion);
        parcel.writeDouble(precio);
        parcel.writeInt(imagen);
        parcel.writeByte((byte) (esFavorito ? 1 : 0));
        parcel.writeString(tipo);
        }

    }



