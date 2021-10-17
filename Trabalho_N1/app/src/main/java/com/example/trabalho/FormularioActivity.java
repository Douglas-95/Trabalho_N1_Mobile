package com.example.trabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etFilme;
    private Spinner spCategoria;
    private Button btnSalvar;
    private String acao;
    private Filmes filmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etFilme = findViewById (R.id.etFilme);
        spCategoria = findViewById (R.id.spCategoria);
        btnSalvar = findViewById (R.id.btnSalvar);

        acao = getIntent ().getStringExtra ("acao");
        if(acao.equals ("editar")){
            carregarFormulario();
        }



        btnSalvar.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                salvar ();
            }
        });
    }

    private void carregarFormulario(){
        int id = getIntent ().getIntExtra ("idFilme", 0);
        filmes = FilmeDAO.getFilmeById (this, id);
        etFilme.setText (filmes.getNome ());

        String[] categorias = getResources ().getStringArray (R.array.categorias);
        for (int i = 1; i < categorias.length ;i++){
            if (filmes.getCategoria ().equals (categorias[i])){
                spCategoria.setSelection (i);
                break;
            }
        }

    }


    private void salvar(){
        String filme = etFilme.getText().toString ();

        if(filme.isEmpty () || spCategoria.getSelectedItemPosition () ==0){
            Toast.makeText (this, "Deve ser preenchido todos os campos", Toast.LENGTH_LONG).show ();
        }else{
            if (acao.equals ("inserir")){
                filmes = new Filmes ();
            }
            filmes.setNome (filme);
            filmes.setCategoria (spCategoria.getSelectedItem ().toString ());
            if (acao.equals ("inserir")){
                FilmeDAO.inserir (this, filmes);
                etFilme.setText ("");
                spCategoria.setSelection (0,true);
            }else{
                FilmeDAO.editar(this, filmes);
                finish ();
            }
        }
    }


}