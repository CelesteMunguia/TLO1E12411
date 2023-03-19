package com.example.tlo1e12411;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.tlo1e12411.db.DdbContactos;
import com.example.tlo1e12411.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtPais, txtNombre, txtTelefono, txtNota;


    Button btnGuarda, btnEdit, btnEliminar;
    boolean correcto = false;
    Contactos contacto;
    int id = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtPais = findViewById(R.id.txtPais);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtNota = findViewById(R.id.txtNota);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setVisibility(View.INVISIBLE);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();

            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }
        DdbContactos ddbContactos = new DdbContactos(EditarActivity.this);
        contacto = ddbContactos.verContacto(id);

        if (contacto != null){
            txtPais.setText(contacto.getPais());
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtNota.setText(contacto.getNota());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtPais.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtNota.getText().toString().equals("")){
                    correcto = ddbContactos.editarContacto(id, txtPais.getText().toString(), txtNombre.getText().toString(), txtTelefono.getText().toString(), txtNota.getText().toString());

                    if(correcto){
                       Toast.makeText(EditarActivity.this, "REGISTRO EDITADO", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(EditarActivity.this, "ERROR A EDITAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(EditarActivity.this, "ES OBLIGATORIO LLENAR LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
