package com.example.trabalho_n2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO {

    public static void inserir(Context context, Alimento alimento){
        ContentValues values = new ContentValues ();
        values.put ("nome", alimento.getNome ());
        values.put ("categoria", alimento.getCategoria ());

        values.put ( "categoriaR", alimento.getCategoriaR () );

        Banco conn = new Banco (context);
        SQLiteDatabase db  = conn.getWritableDatabase ();

        db.insert ("alimentos",null, values);
    }
    public static void editar(Context context, Alimento alimento){
        ContentValues values = new ContentValues ();
        values.put ("nome", alimento.getNome ());
        values.put ("categoria", alimento.getCategoria ());
        values.put ( "categoriaR", alimento.getCategoriaR () );

        Banco conn = new Banco (context);
        SQLiteDatabase db  = conn.getWritableDatabase ();

        db.update ("alimentos", values, "id = " + alimento.getId (), null);
    }
    public static void excluir(Context context, int  idAlimento){

        Banco conn = new Banco (context);
        SQLiteDatabase db  = conn.getWritableDatabase ();

        db.delete ("alimentos","id = " + idAlimento, null);
    }


    public static List<Alimento> getAlimentos(Context context){
        List<Alimento> lista = new ArrayList<> ();

        Banco conn = new Banco (context);
        SQLiteDatabase db  = conn.getReadableDatabase ();

        Cursor cursor = db.rawQuery ("SELECT * FROM alimentos ORDER BY nome", null);

        if (cursor.getCount () > 0){
            cursor.moveToFirst ();
            do {
                Alimento alimento = new Alimento ();
                alimento.setId (cursor.getInt (0));
                alimento.setNome (cursor.getString (1));
                alimento.setCategoria (cursor.getString (2));
                alimento.setCategoriaR ( cursor.getString ( 3 ) );
                lista.add (alimento);

            }while (cursor.moveToNext ());
        }

        return lista;
    }
    public static Alimento getAlimentoById(Context context, int idAlimento){

        Banco conn = new Banco (context);
        SQLiteDatabase db  = conn.getReadableDatabase ();

        Cursor cursor = db.rawQuery ("SELECT * FROM alimentos WHERE id = " + idAlimento, null);

        if (cursor.getCount () > 0){
            cursor.moveToFirst ();

                Alimento alimento = new Alimento ();
                alimento.setId (cursor.getInt (0));
                alimento.setNome (cursor.getString (1));
                alimento.setCategoria (cursor.getString (2));
                alimento.setCategoriaR ( cursor.getString ( 3 ) );

                return alimento;

            }else {
            return null;
        }
    }
}
