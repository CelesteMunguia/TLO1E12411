package com.example.tlo1e12411;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tlo1e12411.adaptadores.ListaContactosAdapter;
import com.example.tlo1e12411.db.DbHelper;
import com.example.tlo1e12411.db.DdbContactos;
import com.example.tlo1e12411.entidades.Contactos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
SearchView txtBuscar;
    RecyclerView listacontactos;
    ListaContactosAdapter adapter;
ArrayList<Contactos> listaArraycontactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBuscar = findViewById(R.id.txtBuscar);
        listacontactos = findViewById(R.id.listacontactos);
        listacontactos.setLayoutManager(new LinearLayoutManager(this));

        DdbContactos dbContactos = new DdbContactos(MainActivity.this);

        listaArraycontactos = new ArrayList<>();
        adapter = new ListaContactosAdapter(dbContactos.mostrarContactos());
        listacontactos.setAdapter(adapter);
txtBuscar.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}