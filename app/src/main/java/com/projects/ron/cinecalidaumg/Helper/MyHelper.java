package com.projects.ron.cinecalidaumg.Helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.sql.Blob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Soporte on 25/05/2017.
 */

public class MyHelper {

    public static String getFecha(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        Date date = Calendar.getInstance().getTime();
        // DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static String getFecha(String fecha) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-d");
        Date date = fmt.parse(fecha);

        // DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }


    public static Bitmap obtenerImagen(byte[] data){
       // byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
       // Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        //imagen_pelicula.setImageBitmap(decodedByte);
        byte[] byteArray = data;

        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
        return bm;
    }
}
