package com.projects.ron.cinecalidaumg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.projects.ron.cinecalidaumg.Adaptadores.AdaptadorProducto;
import com.projects.ron.cinecalidaumg.Helper.DataBase;
import com.projects.ron.cinecalidaumg.Modelos.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaProductoActivity extends AppCompatActivity {


    private GridView grdProducto;
    private List<Producto> producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_producto);

        grdProducto = (GridView) findViewById(R.id.gridProducto);

        grdProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(producto != null){
                    System.out.println("pos: " + position + "id: " + id);
                    Toast.makeText(getApplicationContext(),"Click: " + producto.get(position).getTitulo(),Toast.LENGTH_SHORT ).show();
                    //Intent intent = new Intent(ListMoviesActivity.this,ScrollingActivity.class);
                    //startActivity(intent);
                }
            }
        });

        CargarProducto tarea = new CargarProducto();
        tarea.execute();


    }

    private class CargarProducto extends AsyncTask<String, String, ArrayList<Producto>> {

        DataBase db;
        ProgressDialog dialogo;

        //Se ejecuta la tarea en segundo plano
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogo = new ProgressDialog(ListaProductoActivity.this);
            db = new DataBase();
            dialogo.setMessage("Cargando...");
            dialogo.onStart();
        }

        // Ejecuta antes de iniciar la tarea
        @Override
        protected ArrayList<Producto> doInBackground(String... params) {
            ArrayList<Producto> productos = new ArrayList<Producto>();
            try {
                db.conectar();
                String sql = "select producto.idproducto, producto.precio, producto.titulo, imagen.data from producto \n" +
                        "inner join imagen \n" +
                        "on producto.idImagen = imagen.idImagen";
                ResultSet rs = db.ejecutarConsulta(sql);

                while(rs.next()){
                    Producto item = new Producto();
                    item.setIdProducto(rs.getInt("idProducto"));
                    item.setImg(rs.getBytes("data"));
                    System.out.println(item.getImg());
                    item.setPrecio(rs.getDouble("precio"));
                    item.setTitulo(rs.getString("titulo"));
                    productos.add(item);
                }

            } catch (SQLException e) {
                //publishProgress("# - Error Log: " + e.getMessage() + "\n");
                productos = null;
            }finally {
                try {
                    db.cerrarConexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            return productos;
        }




        //Se ejecuta despues de finalizar la tarea
        @Override
        protected void onPostExecute(ArrayList<Producto> result) {
            super.onPostExecute(result);
            if(result != null){
                AdaptadorProducto adaptador = new AdaptadorProducto(ListaProductoActivity.this,result);
                grdProducto.setAdapter(adaptador);
            }
            dialogo.dismiss();
        }

        //Verificar el progreso de las variables o actualizar el progreso de la tarea
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
           // textLog.append(values[0]);
        }
    }
}
