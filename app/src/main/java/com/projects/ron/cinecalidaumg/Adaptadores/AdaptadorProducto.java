package com.projects.ron.cinecalidaumg.Adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.ron.cinecalidaumg.Helper.MyHelper;
import com.projects.ron.cinecalidaumg.Modelos.Producto;
import com.projects.ron.cinecalidaumg.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Soporte on 01/06/2017.
 */

public class AdaptadorProducto extends ArrayAdapter<Producto>{


    public AdaptadorProducto(Context context, List<Producto> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.producto_item,
                    parent,
                    false);
        }

        ImageView imagen = (ImageView) convertView.findViewById(R.id.imagen_producto);
        TextView titulo = (TextView) convertView.findViewById(R.id.titulo);
        TextView precio = (TextView) convertView.findViewById(R.id.precio);


        Producto producto = getItem(position);
        imagen.setImageBitmap(MyHelper.obtenerImagen(producto.getImg()));
        titulo.setText(producto.getTitulo());
        precio.setText(String.valueOf(producto.getPrecio()));

        // Referencias UI.
        //ImageView avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
        //TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        //TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        //TextView company = (TextView) convertView.findViewById(R.id.tv_company);

        // Lead actual.
       // Lead lead = getItem(position);

        // Setup.
       // Glide.with(getContext()).load(lead.getImage()).into(avatar);
      //  name.setText(lead.getName());
       // title.setText(lead.getTitle());
        //company.setText(lead.getCompany());

        return convertView;
    }

}
