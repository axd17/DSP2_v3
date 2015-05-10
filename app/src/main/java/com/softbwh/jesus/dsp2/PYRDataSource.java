package com.softbwh.jesus.dsp2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by afmu on 4/5/15.
 */
public class PYRDataSource {

    //Factorias de enunciados
    private FabricaEnunciadoTexto fTexto;
    private FabricaEnunciadoAudio fAudio;
    private FabricaEnunciadoGrafico fGrafica;

    //Metainformación de la base de datos
    public static final String PREGUNTAS_TABLE_NAME = "Preguntas";
    public static final String RESPUESTAS_TABLE_NAME = "Respuestas";
    public static final String CATEGORIAS_TABLE_NAME = "Categorias";
    public static final String TIPOS_TABLE_NAME = "Tipos";
    public static final String CLASES_TABLE_NAME = "Clases";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";

    //Campos de la tabla Preguntas
    public static class ColumnPreguntas{
        public static final String ID_PREGUNTAS = "preguntas"+BaseColumns._ID;
        public static final String ID_RESPUESTA = ColumnRespuestas.ID_RESPUESTAS;
        public static final String CONTENIDO_PREGUNTAS = "contenido";
        public static final String DESCRIPCION_PREGUNTAS = "descripcion";
        public static final String CATEGORIA_PREGUNTAS = ColumnCategorias.ID_CATEGORIAS;
        public static final String TIPO_PREGUNTAS = ColumnTipos.ID_TIPOS;
        public static final String CLASE_PREGUNTAS = ColumnClases.ID_CLASES;
    }

    //Campos de la tabla Respuestas
    public static class ColumnRespuestas{
        public static final String ID_RESPUESTAS = "respuestas"+BaseColumns._ID;
        public static final String CONTENIDO_RESPUESTAS = "contenido";
        public static final String DESCRIPCION_RESPUESTAS = "descripcion";
        public static final String TIPO_RESPUESTAS = ColumnTipos.ID_TIPOS;
        public static final String CLASE_RESPUESTAS = ColumnClases.ID_CLASES;
    }

    //Campos de la tabla Categorias
    public static class ColumnCategorias{
        public static final String ID_CATEGORIAS = "categorias"+BaseColumns._ID;
        public static final String CONTENIDO_CATEGORIAS = "contenido";
    }

    //Campos de la tabla Tipos
    public static class ColumnTipos{
        public static final String ID_TIPOS = "tipos"+BaseColumns._ID;
        public static final String CONTENIDO_TIPOS = "contenido";
    }

    //Campos de la tabla Clases
    public static class ColumnClases{
        public static final String ID_CLASES = "clases"+BaseColumns._ID;
        public static final String CONTENIDO_CLASES = "contenido";
    }

    //Script de Creación de la tabla Preguntas
    public static final String CREATE_PREGUNTAS_SCRIPT =
            "create table "+PREGUNTAS_TABLE_NAME+"(" +
                    ColumnPreguntas.ID_PREGUNTAS+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnPreguntas.CONTENIDO_PREGUNTAS+" "+STRING_TYPE+" not null," +
                    ColumnPreguntas.DESCRIPCION_PREGUNTAS+" "+STRING_TYPE+" not null," +
                    ColumnPreguntas.ID_RESPUESTA+" "+INT_TYPE+" not null," +
                    ColumnPreguntas.CATEGORIA_PREGUNTAS+" "+INT_TYPE+" not null," +
                    ColumnPreguntas.TIPO_PREGUNTAS+" "+INT_TYPE+" not null," +
                    ColumnPreguntas.CLASE_PREGUNTAS+" "+INT_TYPE+ "not null)";

    //Script de Creación de la tabla Respuestas
    public static final String CREATE_RESPUESTAS_SCRIPT =
            "create table "+RESPUESTAS_TABLE_NAME+"(" +
                    ColumnRespuestas.ID_RESPUESTAS+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnRespuestas.CONTENIDO_RESPUESTAS+" "+STRING_TYPE+" not null," +
                    ColumnRespuestas.DESCRIPCION_RESPUESTAS+" "+STRING_TYPE+" not null," +
                    ColumnRespuestas.TIPO_RESPUESTAS+" "+INT_TYPE+" not null, "+
                    ColumnRespuestas.CLASE_RESPUESTAS+" "+INT_TYPE+ "not null)";

    //Script de Creación de la tabla Categorias
    public static final String CREATE_CATEGORIA_SCRIPT =
            "create table "+CATEGORIAS_TABLE_NAME+"(" +
                    ColumnCategorias.ID_CATEGORIAS+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnCategorias.CONTENIDO_CATEGORIAS+" "+STRING_TYPE+" not null)";

    //Script de Creación de la tabla Tipos
    public static final String CREATE_TIPOS_SCRIPT =
            "create table "+TIPOS_TABLE_NAME+"(" +
                    ColumnTipos.ID_TIPOS+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnTipos.CONTENIDO_TIPOS+" "+STRING_TYPE+" not null)";

    //Script de Creación de la tabla Clases
    public static final String CREATE_CLASES_SCRIPT =
            "create table "+CLASES_TABLE_NAME+"(" +
                    ColumnClases.ID_CLASES+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnClases.CONTENIDO_CLASES+" "+STRING_TYPE+" not null)";

    //Scripts de inserción por defecto
    public static final String INSERT_PREGUNTAS_SCRIPT =
            "INSERT INTO "+PREGUNTAS_TABLE_NAME+" VALUES" +
                    "(1,'¿Cuántas Champions Leagues posee el Real Madrid?','',1,1,1,1)," +
                    "(2,'¿Qué club ganó La Liga BBVA en 2014?','',4,1,2,1)," +
                    "(3,'¿Qué club ganó la Copa del Rey de Fúbol en 2014?','',2,1,2,1)";

    public static final String INSERT_RESPUESTAS_SCRIPT =
            "INSERT INTO "+RESPUESTAS_TABLE_NAME+" VALUES" +
                    "(1,'10','',1,1)," +
                    "(6,'8','',1,1)," +
                    "(7,'9','',1,1)," +
                    "(8,'7','',1,1)," +
                    "(2,'Real Madrid','',2,1)," +
                    "(3,'FC Barcelona','',2,1)," +
                    "(5,'Valencia CF','',2,1)," +
                    "(4,'Atlético de Madrid','',2,1)";

    public static final String INSERT_CATEGORIAS_SCRIPT =
            "INSERT INTO "+CATEGORIAS_TABLE_NAME+" VALUES" +
                    "(1, 'futbol')";

    public static final String INSERT_TIPOS_SCRIPT =
            "INSERT INTO "+TIPOS_TABLE_NAME+" VALUES" +
                    "(1, 'numero'), " +
                    "(2, 'club')";

    public static final String INSERT_CLASES_SCRIPT =
            "INSERT INTO "+CLASES_TABLE_NAME+" VALUES" +
                    "(1, 'texto'), " +
                    "(2, 'audio'), " +
                    "(3, 'grafica')";

    private PYRReaderDBHelper openHelper;
    private SQLiteDatabase database;

    public PYRDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        openHelper = new PYRReaderDBHelper(context);
        database = openHelper.getReadableDatabase();
    }

    public ArrayList<String> obtenerPreguntas(String categoria) {
        //Sacamos el id de la categoria
        String cat="";
        String columns_cat[] = new String[]{ColumnCategorias.ID_CATEGORIAS, ColumnCategorias.CONTENIDO_CATEGORIAS};
        String selection_cat = ColumnCategorias.CONTENIDO_CATEGORIAS + " = ? ";//WHERE contenido = ?
        String selectionArgs_cat[] = new String[]{categoria};
        Cursor c_cat = database.query(CATEGORIAS_TABLE_NAME, columns_cat, selection_cat, selectionArgs_cat, null, null, null);
        if(c_cat.moveToNext())
            cat = c_cat.getString(c_cat.getColumnIndex(ColumnCategorias.ID_CATEGORIAS));
        else{
            System.out.println("No hay preguntas para la categoría: "+categoria);
            return null;
        }
        //Sacamos las preguntas de esa categoría
        ArrayList<String> preguntas = new ArrayList<String>();
        String columns[] = new String[]{ColumnPreguntas.CONTENIDO_PREGUNTAS, ColumnPreguntas.DESCRIPCION_PREGUNTAS, ColumnPreguntas.ID_RESPUESTA,
                ColumnPreguntas.CLASE_PREGUNTAS,  ColumnPreguntas.CATEGORIA_PREGUNTAS, ColumnPreguntas.TIPO_PREGUNTAS};
        String selection = ColumnPreguntas.CATEGORIA_PREGUNTAS + " = ? ";//WHERE categoria = ?
        String selectionArgs[] = new String[]{cat};
        Cursor c = database.query(PREGUNTAS_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        while(c.moveToNext()) {
            preguntas.add(c.getString(c.getColumnIndex(ColumnPreguntas.CONTENIDO_PREGUNTAS)));
        }
        return preguntas;
    }

    public ArrayList<String> obtenerRespuestas(String tipo, String clase){
        //Sacamos el id del tipo
        String tip="";
        String columns_tip[] = new String[]{ColumnTipos.ID_TIPOS, ColumnTipos.CONTENIDO_TIPOS};
        String selection_tip = ColumnTipos.CONTENIDO_TIPOS + " = ? ";//WHERE contenido = ?
        String selectionArgs_tip[] = new String[]{tipo};
        Cursor c_tip = database.query(TIPOS_TABLE_NAME, columns_tip, selection_tip, selectionArgs_tip, null, null, null);
        if(c_tip.moveToNext())
            tip = c_tip.getString(c_tip.getColumnIndex(ColumnTipos.ID_TIPOS));
        else{
            System.out.println("No hay preguntas para el tipo: "+tipo);
            return null;
        }
        //Respuestas para el tipo
        ArrayList<String> respuestas = new ArrayList<String>();
        String columns[] = new String[]{ColumnRespuestas.CONTENIDO_RESPUESTAS, ColumnRespuestas.CLASE_RESPUESTAS, ColumnRespuestas.DESCRIPCION_RESPUESTAS, ColumnRespuestas.TIPO_RESPUESTAS};
        String selection = ColumnRespuestas.TIPO_RESPUESTAS + " = ? ";//WHERE tipo = ?
        String selectionArgs[] = new String[]{tip};
        Cursor c = database.query(RESPUESTAS_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        while(c.moveToNext()){
            respuestas.add(c.getString(c.getColumnIndex(ColumnRespuestas.CONTENIDO_RESPUESTAS)));
        }
        return respuestas;
    }

    public ArrayList<Pregunta> obtenerPreguntas_(String categoria){
        ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
        //Sacamos el id de la categoria
        String cat="";
        String columns_cat[] = new String[]{ColumnCategorias.ID_CATEGORIAS, ColumnCategorias.CONTENIDO_CATEGORIAS};
        String selection_cat = ColumnCategorias.CONTENIDO_CATEGORIAS + " = ? ";//WHERE contenido = ?
        String selectionArgs_cat[] = new String[]{categoria};
        Cursor c_cat = database.query(CATEGORIAS_TABLE_NAME, columns_cat, selection_cat, selectionArgs_cat, null, null, null);
        if(c_cat.moveToNext())
            cat = c_cat.getString(c_cat.getColumnIndex(ColumnCategorias.ID_CATEGORIAS));
        else{
            System.out.println("No hay preguntas para la categoría: "+categoria);
            return null;
        }
        //Sacamos las preguntas de la categoria por el id obtenido
        String columns[] = new String[]{ColumnPreguntas.CONTENIDO_PREGUNTAS, ColumnPreguntas.DESCRIPCION_PREGUNTAS, ColumnPreguntas.ID_RESPUESTA,
                ColumnPreguntas.CLASE_PREGUNTAS,  ColumnPreguntas.CATEGORIA_PREGUNTAS, ColumnPreguntas.TIPO_PREGUNTAS, ColumnPreguntas.CLASE_PREGUNTAS};
        String selection = ColumnPreguntas.CATEGORIA_PREGUNTAS + " = ? ";//WHERE categoria = ?
        String selectionArgs[] = new String[]{String.valueOf(cat)};
        Cursor c = database.query(PREGUNTAS_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        while(c.moveToNext()) {
            String p = c.getString(c.getColumnIndex(ColumnPreguntas.CONTENIDO_PREGUNTAS));
            String d = c.getString(c.getColumnIndex(ColumnPreguntas.DESCRIPCION_PREGUNTAS));
            String t = c.getString(c.getColumnIndex(ColumnPreguntas.TIPO_PREGUNTAS));
            //Con el id de la respuesta correcta la obtenemos
            int id_r = c.getInt(c.getColumnIndex(ColumnPreguntas.ID_RESPUESTA));
            String columns_r[] = new String[]{ColumnRespuestas.CONTENIDO_RESPUESTAS, ColumnRespuestas.DESCRIPCION_RESPUESTAS, ColumnRespuestas.ID_RESPUESTAS};
            String selection_r = ColumnRespuestas.ID_RESPUESTAS + " = ? ";//WHERE id_respuesta = ?
            String selectionArgs_r[] = new String[]{String.valueOf(id_r)};
            Cursor c_r = database.query(RESPUESTAS_TABLE_NAME, columns_r, selection_r, selectionArgs_r, null, null, null);
            //Obtenemos el id de la clase de la pregunta, necesitamos el contenido
            int id_clase = c.getInt(c.getColumnIndex(ColumnPreguntas.CLASE_PREGUNTAS));
            String clase_string="error";
            String columns_clase[] = new String[]{ColumnClases.ID_CLASES, ColumnClases.CONTENIDO_CLASES};
            String selection_clase = ColumnClases.ID_CLASES + " = ? ";//WHERE contenido = ?
            String selectionArgs_clase[] = new String[]{String.valueOf(id_clase)};
            Cursor c_clase = database.query(CLASES_TABLE_NAME, columns_clase, selection_clase, selectionArgs_clase, null, null, null);
            if(c_clase.moveToNext()) {
                clase_string = c_clase.getString(c_clase.getColumnIndex(ColumnClases.CONTENIDO_CLASES));
                System.out.println("Clase: "+clase_string);
            }else{
                System.out.println("No existe la clase");
                return null;
            }
            while (c_r.moveToNext()) {
                //Creamos la pregunta con la respuesta correcta según la clase
                String cont_r = c_r.getString(c_r.getColumnIndex(ColumnRespuestas.CONTENIDO_RESPUESTAS));
                String desc_r = c_r.getString(c_r.getColumnIndex(ColumnRespuestas.DESCRIPCION_RESPUESTAS));
                if (clase_string == "texto")
                    preguntas.add(new PreguntaTexto(p, new RespuestaTexto(cont_r),t));
                else if (clase_string == "audio")
                    preguntas.add(new PreguntaAudio(p, new RespuestaAudio(cont_r, desc_r),d,t));
                else if (clase_string == "grafica")
                    preguntas.add(new PreguntaGrafica(p, new RespuestaGrafica(cont_r, desc_r),d,t));
                else {
                    preguntas.add(new PreguntaTexto(p, new RespuestaTexto(cont_r),t));
                    System.out.println("Error en la clase de las preguntas");
                    return null;
                }
                System.out.println("Pregunta: "+preguntas.get(preguntas.size()-1).getContenido().toString());
            }
        }
        return preguntas;
    }

    /*public ArrayList<Enunciado> obtenerEnunciados(String cat){
        ArrayList<Enunciado> enunciados = new ArrayList<Enunciado>();
        ArrayList<Pregunta> preguntas = obtenerPreguntas_(cat);
        Collections.shuffle(preguntas, new Random(System.nanoTime()));
        System.out.println("Número de preguntas: "+preguntas.size());
        for(int i=1; i<=3; i++){
            Pregunta p = preguntas.get(i);
            String clase = "";
            if(p instanceof PreguntaTexto)
                clase = "texto";
            else if(p instanceof PreguntaAudio)
                clase = "audio";
            else if(p instanceof PreguntaGrafica)
                clase = "grafica";
            else {
                System.out.println("Error en la clase de la respuesta");
                return null;
            }
            ArrayList<Respuesta> respuestas = obtenerRespuestas(p.getTipo(), clase);
            Collections.shuffle(respuestas, new Random(System.nanoTime()));
            ArrayList<Respuesta> r = new ArrayList<Respuesta>();
            r.add(respuestas.get(0));
            r.add(respuestas.get(1));
            r.add(respuestas.get(2));
            if(p instanceof PreguntaTexto)
                enunciados.add(fTexto.crearEnunciado(p, r));
            else if(p instanceof PreguntaAudio)
                enunciados.add(fAudio.crearEnunciado(p, r));
            else if(p instanceof PreguntaGrafica)
                enunciados.add(fGrafica.crearEnunciado(p, r));
            else {
                System.out.println("Error en la creacion del enunciado");
                return null;
            }
        }
        Collections.shuffle(enunciados, new Random(System.nanoTime()));
        for (int i = 0; i <enunciados.size() ; i++) {
            System.out.println(enunciados.get(i).getPreguntaEnunciado());
            System.out.println(enunciados.get(i).getRespuestasEnunciados().get(0));
            System.out.println(enunciados.get(i).getRespuestasEnunciados().get(1));
            System.out.println(enunciados.get(i).getRespuestasEnunciados().get(2));

        }
        return enunciados;
    }*/
}
