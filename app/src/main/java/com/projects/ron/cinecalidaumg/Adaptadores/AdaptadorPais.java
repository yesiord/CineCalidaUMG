package com.projects.ron.cinecalidaumg.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.ron.cinecalidaumg.Modelos.Pais;
import com.projects.ron.cinecalidaumg.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Soporte on 02/06/2017.
 */

public class AdaptadorPais extends ArrayAdapter{

    private Context context;
    private ArrayList<Pais> datos;

    public AdaptadorPais(Context context, ArrayList<Pais> datos) {
        super(context, R.layout.pais_item, datos);
        // Guardamos los parámetros en variables de clase.
        this.context = context;
        this.datos = datos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.pais_item, null);

        TextView nombre_pais = (TextView) item.findViewById(R.id.nombre_pais);
        ImageView imagen = (ImageView) item.findViewById(R.id.icon_pais);
        imagen.setImageResource(R.mipmap.location_off_48px);
        nombre_pais.setText(datos.get(position).getNombre());

        return item;
    }


}
