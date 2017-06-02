package com.projects.ron.cinecalidaumg;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.projects.ron.cinecalidaumg.Adaptadores.AdaptadorPais;
import com.projects.ron.cinecalidaumg.Helper.DataBase;
import com.projects.ron.cinecalidaumg.Modelos.Pais;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UbicacionPreferenciaActivity extends AppCompatActivity {

    private ListView lista_paises;
    private ArrayList<Pais> paises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_preferencia);


        lista_paises = (ListView) findViewById(R.id.lista_paises);


        ObtenerPaises tarea = new ObtenerPaises();
        tarea.execute();


        lista_paises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"id pais: " + paises.get(position).getIdPais() ,Toast.LENGTH_SHORT).show();
            }
        });



    }


    private class ObtenerPaises extends AsyncTask<String, String, ArrayList<Pais>> {

        private DataBase db;
        private ProgressDialog progress;

        //Se ejecuta la tarea en segundo plano
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            db = new DataBase();
           // progress = new ProgressDialog(getApplicationContext());
            //progress.setMessage("Cargando");
           // progress.onStart();
        }

        @Override
        protected ArrayList<Pais> doInBackground(String... params) {

            ArrayList<Pais> paises = new ArrayList<Pais>();


            String sql = "select * from pais";

            try {
                db.conectar();
                ResultSet res = db.ejecutarConsulta(sql);
                while(res.next()){
                    Pais p = new Pais();

                    p.setIdPais(res.getInt("idpais"));
                    p.setNombre(res.getString("nombre"));

                    paises.add(p);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            return paises;
        }




        //Se ejecuta despues de finalizar la tarea
        @Override
        protected void onPostExecute(ArrayList<Pais> result) {
            super.onPostExecute(result);
            paises = result;
            AdaptadorPais adapter = new AdaptadorPais(getApplicationContext(),result);
            lista_paises.setAdapter(adapter);

           // progress.dismiss();
        }



    }




}
