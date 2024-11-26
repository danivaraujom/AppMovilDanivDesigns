package com.carba.appmovildanivdesigns;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductoAdapter.OnItemClickListener {
    private List<Producto> listaProductos;
    private List<Producto> cestaProductos;
    private ProductoAdapter adaptadorProductos;
    private RecyclerView recyclerView;
    private Switch switchModo;
    private static final String TAG = "MensajesLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Se inicializa el RecyclerView
        recyclerView = findViewById(R.id.listaProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Lista de productos con el nombre, descripcion, precio y la foto del producto
        listaProductos = new ArrayList<>(Arrays.asList(
                new Producto("Pulsera Arcoiris Brillante", "Pulsera hecha a mano con cuentas brillantes de colores arcoiris.", 4.99, R.drawable.pulsera6, false),
                new Producto("Pulsera Natural Zen", "Pulsera de cuentas de madera natural con un diseño sencillo y elegante.", 5.99, R.drawable.pulsera2, false),
                new Producto("Pulsera Bohemia", "Diseño bohemio con cuentas de colores y detalles de hilo tejidos a mano.", 6.49, R.drawable.pulsera3, false),
                new Producto("Pulsera de Cristal Místico", "Pulsera con cuentas de cristal translúcido que reflejan la luz.", 7.99, R.drawable.pulsera4, false),
                new Producto("Pulsera de Amistad", "Pulsera hecha con nudos de colores para celebrar la amistad.", 3.99, R.drawable.pulsera5, false),
                new Producto("Pulsera Elegante de Perlas", "Pulsera con perlas sintéticas y un diseño sofisticado.", 9.49, R.drawable.pulsera6, false),
                new Producto("Pulsera Estilo Playero", "Pulsera con cuentas en tonos azul y arena, ideal para el verano.", 5.49, R.drawable.pulsera7, false),
                new Producto("Pulsera Vintage", "Estilo retro con detalles metálicos y colores apagados.", 6.99, R.drawable.pulsera8, false),
                new Producto("Pulsera de Cuarzo Rosa", "Pulsera hecha con piedras de cuarzo rosa, símbolo de amor y calma.", 12.99, R.drawable.pulsera2, false),
                new Producto("Pulsera Minimalista Negra", "Diseño sencillo con cuentas negras mate, ideal para cualquier ocasión.", 4.99, R.drawable.pulsera10, false),
                new Producto("Pulsera Tribal", "Pulsera con motivos tribales y colores intensos.", 7.49, R.drawable.pulsera11, false),
                new Producto("Pulsera de Hilo Multicolor", "Pulsera tejida a mano con hilos de colores vibrantes.", 3.49, R.drawable.pulsera12, false),
                new Producto("Pulsera con Colgantes", "Pulsera con pequeños colgantes metálicos en forma de estrellas y lunas.", 8.99, R.drawable.pulsera13, false),
                new Producto("Pulsera Natural", "Hecha con materiales reciclados y un diseño ecológico.", 5.99, R.drawable.pulsera14, false),
                new Producto("Pulsera de Aventurina", "Pulsera con piedras de aventurina verde, conocida por atraer la prosperidad.", 10.99, R.drawable.pulsera15, false),
                new Producto("Pulsera Floral", "Pulsera con cuentas en forma de pequeñas flores y colores suaves.", 6.49, R.drawable.pulsera16, false)
        ));

        cestaProductos = new ArrayList<>();
        adaptadorProductos = new ProductoAdapter(listaProductos, this, this);
        recyclerView.setAdapter(adaptadorProductos);

        // Barra superior
        LinearLayout barraSuperior = findViewById(R.id.barraSuperior);
        ImageButton iconoMenu = findViewById(R.id.btnMenu);
        ImageButton iconoInsta = findViewById(R.id.btnInsta);
        ImageButton iconoLlamada = findViewById(R.id.btnLlamada);
        ImageButton iconoMensaje = findViewById(R.id.btnMensaje);

        // Acciones al hacer clic en los botones de la barra superior
        iconoMenu.setOnClickListener(v -> mostrarMensaje("Menú deshabilitado"));
        iconoInsta.setOnClickListener(v -> mostrarMensaje("Instagram: @danivdesigns_"));
        iconoLlamada.setOnClickListener(v -> mostrarMensaje("Número de teléfono: 669392358"));
        iconoMensaje.setOnClickListener(v -> mostrarMensaje("Enviar Mensaje: ¡danivdesigns_@gmail.com!"));

        // Buscador deshabilitado (funcionalidad no implementada)
        SearchView iconoBuscarLista =findViewById(R.id.buscador);

        // Uso del Switch para cambiar el modo de tema claro/oscuro
        switchModo = findViewById(R.id.switchModo);
        switchModo.setOnClickListener(view -> mostrarMensaje("Ha cambiado de modo"));
        switchModo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Barra inferior
        LinearLayout barraInferior = findViewById(R.id.barraInferior);
        ImageButton btnHome = findViewById(R.id.home);
        ImageButton btnCesta = findViewById(R.id.cesta);
        ImageButton btnFavorites = findViewById(R.id.favorito);
        ImageButton btnUsuario = findViewById(R.id.usuario);

        // Acciones al hacer clic en los botones de la barra inferior
        btnHome.setOnClickListener(v -> {
            mostrarMensaje("Inicio seleccionado");
            adaptadorProductos.actualizarLista(listaProductos);
        });
        btnCesta.setOnClickListener(v -> {
            mostrarMensaje("Cesta");
            mostrarProductosCesta();
        });
        btnFavorites.setOnClickListener(v -> {
            mostrarMensaje("Favoritos");
            filtrarFavoritos();
        });
        btnUsuario.setOnClickListener(v -> mostrarMensaje("Perfil de Usuario deshabilitado"));
    }

    // Muestra un mensaje en la pantalla (Toast)
    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    // Filtra los productos favoritos
    private void filtrarFavoritos() {
        try {
            List<Producto> listaFavoritos = new ArrayList<>();
            for (Producto producto : listaProductos) {
                if (producto.esProductoFavorito()) {
                    listaFavoritos.add(producto);
                }
            }
            adaptadorProductos.actualizarLista(listaFavoritos);
        } catch (Exception e) {
            Log.e(TAG, "Error al filtrar favoritos: ", e);
        }
    }

    // Muestra los productos de la cesta
    private void mostrarProductosCesta() {
        try {
            if (cestaProductos.isEmpty()) {
                Log.w(TAG, "La cesta está vacía");
               adaptadorProductos.actualizarLista(new ArrayList<>());
            } else {
                adaptadorProductos.actualizarLista(cestaProductos);
            }
        } catch (Exception e) {
            Log.w(TAG, "Error al mostrar productos en la cesta: ", e);
        }
    }

    // Método al hacer clic en el botón de "favorito"
    @Override
    public void onClickFavorito(int position) {
        try {
            Producto producto = adaptadorProductos.getListaProductos().get(position);
            boolean esFavorito = !producto.esProductoFavorito();
            producto.setEsFavorito(esFavorito);
            if (esFavorito) {
                adaptadorProductos.notifyItemChanged(position);
            } else {
                adaptadorProductos.getListaProductos().remove(position);
                adaptadorProductos.notifyItemRemoved(position);
            }
            mostrarMensaje(esFavorito ? "Añadido a favoritos" : "Eliminado de favoritos");
        } catch (Exception e) {
            Log.e(TAG, "Error al gestionar favoritos: ", e);
        }
    }

    // Método al hacer clic en el botón de "añadir a la cesta"
    @Override
    public void onClickAñadirCesta(int position) {
        try {
            Producto producto = listaProductos.get(position);
            if (!cestaProductos.contains(producto)) {
                cestaProductos.add(producto);
                mostrarMensaje("Producto añadido a la cesta");
            } else {
                Log.w(TAG, "El producto ya estaba en la cesta - " + producto.getNombre());
                mostrarMensaje("El producto ya esta en la cesta");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al añadir producto a la cesta: ", e);
        }
    }
}
