package com.example.trabalho_n2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private Spinner spCategorias;
    private Spinner spCategoriasR;
    private Button btnSalvar;
    private String acao;
    private Alimento alimento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_formulario);

        etNome = findViewById ( R.id.etNome );
        spCategorias = findViewById ( R.id.spCategoria );
        spCategoriasR = findViewById ( R.id.spCategoriaR );
        btnSalvar = findViewById ( R.id.btnSalvar );

        acao = getIntent ().getStringExtra ( "acao" );
        if (acao.equals ( "editar" )){
            carregarFormulario ();
        }

        btnSalvar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                salvar ();
            }
        } );

    }

    private void carregarFormulario(){
        int id = getIntent ().getIntExtra ( "idAlimento", 0 );
        alimento = AlimentoDAO.getAlimentoById ( this, id );
        etNome.setText ( alimento.getNome () );

        String[] categorias = getResources ().getStringArray ( R.array.categorias );
        for (int  i = 1; i < categorias.length ; i++){
            if (alimento.getCategoria ().equals ( categorias[i] )){
                spCategorias.setSelection ( i );
                break;
            }
        }
        String[] categoriasR = getResources ().getStringArray ( R.array.categoriasR );
        for (int  i = 1; i < categorias.length ; i++){
            if (alimento.getCategoria ().equals ( categorias[i] )){
                spCategoriasR.setSelection ( i );
                break;
            }
        }


    }

    private void salvar(){
        String nome = etNome.getText ().toString ();
        if(nome.isEmpty () || spCategorias.getSelectedItemPosition () == 0 ){
            Toast.makeText ( this, "You must fill in all fields.", Toast.LENGTH_LONG ).show ();
        }else{
            if (acao.equals ( "inserir" )){
                alimento = new Alimento ();
            }

            alimento.setNome ( nome );
            alimento.setCategoria ( spCategorias.getSelectedItem ().toString () );
            alimento.setCategoriaR ( spCategoriasR.getSelectedItem ().toString () );

            if (acao.equals ( "inserir" )) {
                AlimentoDAO.inserir ( this, alimento );
                etNome.setText ( "" );
                spCategorias.setSelection ( 0, true );
                spCategoriasR.setSelection ( 0, true );
            }else{
                AlimentoDAO.editar ( this, alimento );
                finish ();
            }
        }
    }


}