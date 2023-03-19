package com.example.tlo1e12411;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tlo1e12411.db.DdbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtPais, txtNombre, txtTelefono, txtNota;
    Button btnGuarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);


        txtPais = findViewById(R.id.txtPais);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtNota = findViewById(R.id.txtNota);
        btnGuarda = findViewById(R.id.btnGuarda);


        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DdbContactos ddbContactos = new DdbContactos(NuevoActivity.this);
                long id = ddbContactos.insertarContacto(txtPais.getText().toString(), txtNombre.getText().toString(), txtTelefono.getText().toString(), txtNota.getText().toString());

                if (id > 0) {

                    Toast.makeText(NuevoActivity.this, "REGISTRO INGRESADO", Toast.LENGTH_LONG).show();
                    limpiar();
                }else {
                    Toast.makeText(NuevoActivity.this, "ERROR AL AGREGAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public void agregar (View V)
    {
        if (validar())
        {
            Toast.makeText(NuevoActivity.this, "REGISTRO INGRESADO", Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    public boolean validar()
    {
        boolean retorno= true;

        String nom = txtNombre.getText().toString() ;
        String tel = txtTelefono.getText().toString();
        String not = txtNota.getText().toString();
        if(nom.isEmpty())
        {
            txtNombre.setError("Debe ingresar un nombre");
            retorno = false;
        }
        if (tel.isEmpty())
        {
            txtTelefono.setError("Debe ingresar un telefono");
            retorno = false;
        }
        if (not.isEmpty())
        {
            txtNota.setError("Debe ingresar una nota");
            retorno = false;
        }

        return retorno;
    }
    private  void limpiar(){
        txtPais.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtNota.setText("");
    }



    public void ir(View vista){
        Intent miintent=new Intent(this,MainActivity.class);
        startActivity(miintent);
    }



}