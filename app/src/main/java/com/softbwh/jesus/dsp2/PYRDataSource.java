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
                    "(3,'¿Qué club ganó la Copa del Rey de Fútbol en 2014?','',2,1,2,1)";

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

    public ArrayList<Pregunta> obtenerPreguntas(String categoria) {
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
        ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
        String columns[] = new String[]{ColumnPreguntas.CONTENIDO_PREGUNTAS, ColumnPreguntas.DESCRIPCION_PREGUNTAS, ColumnPreguntas.ID_RESPUESTA,
                ColumnPreguntas.CLASE_PREGUNTAS,  ColumnPreguntas.CATEGORIA_PREGUNTAS, ColumnPreguntas.TIPO_PREGUNTAS};
        String selection = ColumnPreguntas.CATEGORIA_PREGUNTAS + " = ? ";//WHERE categoria = ?
        String selectionArgs[] = new String[]{cat};
        Cursor c = database.query(PREGUNTAS_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        while(c.moveToNext()) {
            String id_tipo = c.getString(c.getColumnIndex(ColumnPreguntas.TIPO_PREGUNTAS));
            String c_p = c.getString(c.getColumnIndex(ColumnPreguntas.CONTENIDO_PREGUNTAS));
            String d_p = c.getString(c.getColumnIndex(ColumnPreguntas.DESCRIPCION_PREGUNTAS));
            String id_r = c.getString(c.getColumnIndex(ColumnPreguntas.ID_RESPUESTA));
            //Respuesta correcta para la pregunta
            String columns_r[] = new String[]{ColumnRespuestas.CONTENIDO_RESPUESTAS, ColumnRespuestas.CLASE_RESPUESTAS,
                    ColumnRespuestas.DESCRIPCION_RESPUESTAS, ColumnRespuestas.TIPO_RESPUESTAS};
            String selection_r = ColumnRespuestas.ID_RESPUESTAS + " = ? ";//WHERE id_respuesta = ?
            String selectionArgs_r[] = new String[]{id_r};
            Cursor c_resp = database.query(RESPUESTAS_TABLE_NAME, columns_r, selection_r, selectionArgs_r, null, null, null);
            //Sacamos el contenido de la clase
            String id_clase = c.getString(c.getColumnIndex(ColumnPreguntas.CLASE_PREGUNTAS));
            String clase="";
            String columns_clas[] = new String[]{ColumnClases.ID_CLASES, ColumnClases.CONTENIDO_CLASES};
            String selection_clas = ColumnClases.ID_CLASES + " = ? ";//WHERE id_clases = ?
            String selectionArgs_clas[] = new String[]{id_clase};
            Cursor c_clas = database.query(CLASES_TABLE_NAME, columns_clas, selection_clas, selectionArgs_clas, null, null, null);
            if(c_clas.moveToNext())
                clase = c_clas.getString(c_clas.getColumnIndex(ColumnClases.CONTENIDO_CLASES));
            else{
                System.out.println("No hay preguntas para la clase: "+clase);
                return null;
            }
            if(c_resp.moveToNext()){
                String c_r = c_resp.getString(c.getColumnIndex(ColumnRespuestas.CONTENIDO_RESPUESTAS));
                String d_r = c_resp.getString(c.getColumnIndex(ColumnRespuestas.DESCRIPCION_RESPUESTAS));
                if (clase.matches("texto"))
                    preguntas.add(new PreguntaTexto(c_p, new RespuestaTexto(c_r, id_r), 0, id_tipo));
                else if (clase.matches("audio"))
                    preguntas.add(new PreguntaAudio(c_p, new RespuestaAudio(c_r, d_r, id_r), Integer.valueOf(d_p), id_tipo));
                else if (clase.matches("grafica"))
                    preguntas.add(new PreguntaGrafica(c_p, new RespuestaGrafica(c_r, d_r, id_r), Integer.valueOf(d_p), id_tipo));
                else {
                    System.out.println("Error en la clase de las respuestas");
                    return null;
                }
            }
        }
        return preguntas;
    }

    public ArrayList<Respuesta> obtenerRespuestas(String tipo, String clase, String id_correcta){
        //Sacamos el id de la clase
        String clas="";
        String columns_clas[] = new String[]{ColumnClases.ID_CLASES, ColumnClases.CONTENIDO_CLASES};
        String selection_clas = ColumnClases.CONTENIDO_CLASES + " = ? ";//WHERE contenido = ?
        String selectionArgs_clas[] = new String[]{clase};
        Cursor c_clas = database.query(CLASES_TABLE_NAME, columns_clas, selection_clas, selectionArgs_clas, null, null, null);
        if(c_clas.moveToNext())
            clas = c_clas.getString(c_clas.getColumnIndex(ColumnClases.ID_CLASES));
        else{
            System.out.println("No hay respuestas para la clase: "+clase);
            return null;
        }
        //Respuestas para el tipo y clase
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        String columns[] = new String[]{ColumnRespuestas.ID_RESPUESTAS, ColumnRespuestas.CONTENIDO_RESPUESTAS, ColumnRespuestas.CLASE_RESPUESTAS,
                ColumnRespuestas.DESCRIPCION_RESPUESTAS, ColumnRespuestas.TIPO_RESPUESTAS};
        String selection = ColumnRespuestas.TIPO_RESPUESTAS + " = ? AND "+ColumnRespuestas.CLASE_RESPUESTAS+" = ?";//WHERE tipo = ?
        String selectionArgs[] = new String[]{tipo, clas};
        Cursor c = database.query(RESPUESTAS_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        while(c.moveToNext()){
            String id_r = c.getString(c.getColumnIndex(ColumnRespuestas.ID_RESPUESTAS));
            String c_r = c.getString(c.getColumnIndex(ColumnRespuestas.CONTENIDO_RESPUESTAS));
            String d_r = c.getString(c.getColumnIndex(ColumnRespuestas.DESCRIPCION_RESPUESTAS));
            if(!(id_r.matches(id_correcta))) {//Si no es la respuesta correcta
                if (clase.matches("texto"))
                    respuestas.add(new RespuestaTexto(c_r, id_r));
                else if (clase.matches("audio"))
                    respuestas.add(new RespuestaAudio(c_r, d_r, id_r));
                else if (clase.matches("grafica"))
                    respuestas.add(new RespuestaGrafica(c_r, d_r, id_r));
                else {
                    System.out.println("Error en la clase de las respuestas");
                    return null;
                }
            }
        }
        return respuestas;
    }

    public ArrayList<Enunciado> obtenerEnunciados(String cat){
        fTexto = new FabricaEnunciadoTexto();
        fAudio = new FabricaEnunciadoAudio();
        fGrafica = new FabricaEnunciadoGrafico();
        ArrayList<Enunciado> enunciados = new ArrayList<>();
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas = obtenerPreguntas(cat);
        Collections.shuffle(preguntas, new Random(System.nanoTime()));
        System.out.println("Número de preguntas: "+preguntas.size());
        for(Pregunta p:preguntas){
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
            ArrayList<Respuesta> r = new ArrayList<>();
            System.out.println("Tipo: "+p.getTipo()+", Clase: "+clase+", Id respuesta: "+p.getIdRespuesta());
            //Comprobar tipos
            if(p.getTipo().matches("1")){
                r = generarRespuestasAleatorias(p.getIdRespuesta());
            }else {
                ArrayList<Respuesta> respuestas = new ArrayList<>();
                respuestas = obtenerRespuestas(p.getTipo(), clase, p.getIdRespuesta());
                Collections.shuffle(respuestas, new Random(System.nanoTime()));
                r.add(respuestas.get(0));
                r.add(respuestas.get(1));
                r.add(respuestas.get(2));
            }
            if (p instanceof PreguntaTexto)
                enunciados.add(fTexto.crearEnunciado(p, r));
            else if (p instanceof PreguntaAudio)
                enunciados.add(fAudio.crearEnunciado(p, r));
            else if (p instanceof PreguntaGrafica)
                enunciados.add(fGrafica.crearEnunciado(p, r));
            else {
                System.out.println("Error en la creacion del enunciado");
                return null;
            }
        }
        Collections.shuffle(enunciados, new Random(System.nanoTime()));
        return enunciados;
    }

    private ArrayList<Respuesta> generarRespuestasAleatorias(String id_r){
        //Sacamos el contenido del id de la respuesta
        ArrayList<Respuesta> r = new ArrayList<>();
        String resp="";
        String columns[] = new String[]{ColumnRespuestas.ID_RESPUESTAS, ColumnRespuestas.CONTENIDO_RESPUESTAS};
        String selection = ColumnRespuestas.ID_RESPUESTAS + " = ? ";//WHERE id_respuesta = ?
        String selectionArgs[] = new String[]{id_r};
        Cursor c = database.query(RESPUESTAS_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        if(c.moveToNext())
            resp = c.getString(c.getColumnIndex(ColumnRespuestas.CONTENIDO_RESPUESTAS));
        else{
            System.out.println("Error en el contenido de la respuesta");
            return null;
        }
        int valor=Integer.valueOf(resp);
        for(int i=0; i<3; i++) {
            String numerito="";
            do {
                Random rand = new Random();
                int randomNum = rand.nextInt((valor - valor / 2) + 1) + 1;
                numerito = Integer.toString(randomNum + valor / 2);
            }while(r.contains(numerito));
            r.add(new RespuestaTexto(numerito, "0"));
        }
        return r;
    }
}
