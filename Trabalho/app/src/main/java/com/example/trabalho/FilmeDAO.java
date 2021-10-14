package com.example.trabalho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    public static void inserir(Context context, Filmes filmes){
        ContentValues values  = new ContentValues();
        values.put("nome", filmes.getNome());
        values.put("categoria", filmes.getCategoria());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("filmes", null, values);

    }
    public static void editar(Context context, Filmes filmes){
        ContentValues values  = new ContentValues();
        values.put("nome", filmes.getNome());
        values.put("categoria", filmes.getCategoria());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("filmes", values, "id = "+ filmes.getId(), null);

    }

    public static void excluir(Context context, int idFilmes){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("filmes", "id = "+ idFilmes, null);

    }

    public static List<Filmes> getFilmes(Context context){
        List<Filmes> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM filmes ORDER BY nome", null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            do {
                Filmes film = new Filmes();
                film.setId(cursor.getInt(0));
                film.setNome(cursor.getString(1));
                film.setCategoria(cursor.getString(2));
                lista.add(film);
            }while(cursor.moveToNext());

        }
        return lista;
    }
    public static Filmes getFilmeById(Context context, int idFilme){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM filmes WHERE id = "+idFilme, null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();


            Filmes film = new Filmes ();
            film.setId (cursor.getInt (0));
            film.setNome (cursor.getString (1));
            film.setCategoria (cursor.getString (2));
            return film;
        }else{
            return null;
        }
    }

}
