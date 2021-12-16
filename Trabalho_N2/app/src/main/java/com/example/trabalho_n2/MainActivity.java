package com.example.trabalho_n2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvAlimentos;
    private ArrayAdapter adapter;
    private List<Alimento> listaDeAlimentos;
    private TextView tvDirecao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


        lvAlimentos = findViewById (R.id.lvAlimentos);

        carregarAlimentos ();
       //ColorStateList linhaedits = ContextCompat.getColorStateList(getApplicationContext(), R.color.white);
        //ViewCompat.setBackgroundTintList(tvDirecao,linhaedits);

        FloatingActionButton fab = findViewById (R.id.fab);

        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, FormularioActivity.class);
                intent.putExtra ( "acao", "inserir" );
                startActivity ( intent );
            }
        });

        lvAlimentos.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idAlimento = listaDeAlimentos.get ( position ).getId ();
                Intent intent = new Intent (MainActivity.this, FormularioActivity.class);
                intent.putExtra ( "acao", "editar" );
                intent.putExtra ( "idAlimento", idAlimento );
                startActivity ( intent );
            }
        } );

        lvAlimentos.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                excluir ( position );
                return true;
            }
        } );
    }

    private void excluir (int posicao){
        Alimento alimento = listaDeAlimentos.get ( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder ( this );
        alerta.setTitle ( "Delete" );
        alerta.setIcon ( android.R.drawable.ic_delete );
        alerta.setMessage ( "Delete " + alimento.getNome () + " ?" );
        alerta.setNeutralButton ( "Cancel", null );

        alerta.setPositiveButton ( "YES", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlimentoDAO.excluir (MainActivity.this, alimento.getId ());
                carregarAlimentos ();
            }
        } );
        alerta.show ();
    }


    @Override
    protected void onRestart() {
        super.onRestart ();
        carregarAlimentos ();
    }



    private void carregarAlimentos(){

        listaDeAlimentos = AlimentoDAO.getAlimentos ( this );
        if (listaDeAlimentos.size () == 0){
            Alimento fake = new Alimento ("No Diet","","");
            listaDeAlimentos.add ( fake );
            lvAlimentos.setEnabled ( false );
        }else{
            lvAlimentos.setEnabled ( true );
        }



        adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_1, listaDeAlimentos);
        lvAlimentos.setAdapter (adapter);

    }










    @Override
    public Resources getResources() {
        return super.getResources ();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

}