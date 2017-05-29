package com.projects.ron.cinecalidaumg;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projects.ron.cinecalidaumg.Helper.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PruebaActivity extends AppCompatActivity {

    TextView textLog;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        textLog = (TextView) findViewById(R.id.textLog);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConectarSqlServer con = new ConectarSqlServer();
                con.execute();
            }
        });

    }


    private class ConectarSqlServer extends AsyncTask<String, String, String> {

        DataBase db;

        //Se ejecuta la tarea en segundo plano
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btn.setEnabled(false);
            textLog.setText("");
            db = new DataBase();
            textLog.append("# - DRIVER: " + db.DRIVER + "\n");
            textLog.append("# - SERVIDOR: " + db.IP + "\n");
            textLog.append("# - DB: " + db.DB + "\n");
            textLog.append("# - USER: " + db.USER + "\n");
            textLog.append("# - Conectando...\n");
        }

        // Ejecuta antes de iniciar la tarea
        @Override
        protected String doInBackground(String... params) {
            String data;
            try {
                db.conectar();
                publishProgress("# - Conectado Exitosamente\n");
                publishProgress("# - Consultando...\n");

                String sql  = "SELECT * FROM TblPelicula";
                publishProgress("# - " + sql + "\n");
                ResultSet rs = db.ejecutarConsulta(sql);
                publishProgress("# - Resultado\n");
                while(rs.next()){
                    publishProgress("$ - Pelicula: " + rs.getString("titulo") + "\n");
                }
                publishProgress("# - Consulta Realizada \n");

            } catch (SQLException e) {
                publishProgress("# - Error Log: " + e.getMessage() + "\n");
            }finally {
                try {
                    db.cerrarConexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return "";
        }




        //Se ejecuta despues de finalizar la tarea
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //textLog.append(result);
            textLog.append("# - Operacion Finalizada...\n");
            btn.setEnabled(true);
        }

        //Verificar el progreso de las variables o actualizar el progreso de la tarea
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            textLog.append(values[0]);
        }
    }


}
