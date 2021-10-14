package com.example.trabalho;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView lvGeneros;
    private ArrayAdapter adapter;
    private List<Filmes> listaDeFilmes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvGeneros = findViewById(R.id.lvGeneros);

        carregarFilmes();

        FloatingActionButton fab  = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, FormularioActivity.class);
                intent.putExtra ("acao", "inserir");
                startActivity (intent);
            }
        });

        lvGeneros.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int idFilme = listaDeFilmes.get (position).getId ();
                Intent intent = new Intent (MainActivity.this, FormularioActivity.class);
                intent.putExtra ("acao", "editar");
                intent.putExtra ("idFilme", idFilme);
                startActivity (intent);
            }
        });

        lvGeneros.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener () {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                excluir (position);
                return false;
            }
        });


    }

    private void excluir(int position){
        Filmes film  = listaDeFilmes.get (position);
        AlertDialog.Builder alerta = new AlertDialog.Builder (this);
        alerta.setTitle ("Excluir");
        alerta.setIcon (android.R.drawable.ic_delete);
        alerta.setMessage ("Confirma excluir o filme " + film.getNome ()+" ?" );
        alerta.setNeutralButton ("Cancelar", null);

        alerta.setPositiveButton ("SIM", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FilmeDAO.excluir (MainActivity.this, film.getId ());
                carregarFilmes ();
            }
        });
        alerta.show ();
    }


    @Override
    protected void onRestart() {
        super.onRestart ();
        carregarFilmes ();
    }

    private void carregarFilmes(){
        listaDeFilmes = FilmeDAO.getFilmes (this);
        if(listaDeFilmes.size ()==0){
            Filmes fake = new Filmes ("Sem filme ", "");
            listaDeFilmes.add(fake);
            lvGeneros.setEnabled (false);
        }else{
            lvGeneros.setEnabled (true);
        }



        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeFilmes);
        lvGeneros.setAdapter(adapter);

    }


}