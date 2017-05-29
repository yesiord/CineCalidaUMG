package com.projects.ron.cinecalidaumg;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.ron.cinecalidaumg.Helper.MyHelper;
import com.projects.ron.cinecalidaumg.dialogos.DialogoCalendario;

import java.text.ParseException;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {

    TextView textFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        textFecha = (TextView) findViewById(R.id.textFecha);
        textFecha.setText(MyHelper.getFecha());

        textFecha.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DialogoCalendario dialogo = new DialogoCalendario();
                dialogo.show(getSupportFragmentManager(), "tagAlerta");
            }
        });



/*

        fecha = (EditText) findViewById(R.id.textFecha);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        fecha.setText(day + "-" + month + "-" + year);


        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentManager fragmentManager = getSupportFragmentManager();


                DialogoCalendario dialogo = new DialogoCalendario();
                dialogo.show(getSupportFragmentManager(), "tagAlerta");


            }
        });*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cartelera) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(),"Cartelera",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_bistro) {
            Toast.makeText(getApplicationContext(),"Bistro",Toast.LENGTH_SHORT).show();

        }else if (id == R.id.nav_proximos) {
            Toast.makeText(getApplicationContext(),"Proximos",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_promocion) {
            Toast.makeText(getApplicationContext(),"promociones",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_dulceria) {
            Toast.makeText(getApplicationContext(),"Dulceria",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_boletos) {
            Toast.makeText(getApplicationContext(),"Boletos",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_sugerencia) {
            Toast.makeText(getApplicationContext(),"sugerencias",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_ubicacion) {
            Toast.makeText(getApplicationContext(),"ubicacion",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_beneficios) {
            Toast.makeText(getApplicationContext(),"beneficios",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_tarjeta_credito) {
            Toast.makeText(getApplicationContext(),"tarjeta credito",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_pruebas){
            Intent intent = new Intent(getApplicationContext(), PruebaActivity.class);
            startActivity(intent);

        }

       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        /*try {
            Toast.makeText(getApplicationContext(),"Fecha: " + year + "-" + monthOfYear + "-" + dayOfMonth, Toast.LENGTH_SHORT).show();
            textFecha.setText(MyHelper.getFecha(year + "-" + monthOfYear + "-" + dayOfMonth));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

    }

}