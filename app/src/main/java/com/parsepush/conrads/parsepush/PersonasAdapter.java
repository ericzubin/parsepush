package com.parsepush.conrads.parsepush;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ERIC on 06/10/2015.
 */
public class PersonasAdapter extends ArrayAdapter<Personas> {
    private Context context;
    private ArrayList<Personas> datos;

    public PersonasAdapter(Context context, ArrayList<Personas> datos) {
        super(context, R.layout.list_item,datos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        PersonasHolder holder;

        if (item == null) {
            item = LayoutInflater.from(context).inflate(R.layout.list_item,
                    null);
            // Inicializamos el holder y guardamos las referencias a los
            // controles.
            holder = new PersonasHolder();
            holder.imgAnimal = (ImageView) item.findViewById(R.id.imageView);
           // holder.tvContent = (TextView) item.findViewById(R.id.tvContent);
            holder.tvField = (TextView) item.findViewById(R.id.textView4);

            // Almacenamos el holder en el Tag de la vista.
            item.setTag(holder);
        }
        // Recuperamos el holder del Tag de la vista.
        holder = (PersonasHolder) item.getTag();

        // A partir del holder, asignamos los valores que queramos a los
        // controles.
        // Le asignamos una foto al ImegeView.
        holder.imgAnimal.setImageResource(datos.get(position)
                .getDrawableImageID(R.mipmap.ic_launcher));

        // Asignamos los textos a los TextView.
        holder.tvContent.setText(datos.get(position).getNombre());
        holder.tvField.setText(String.valueOf(position));

        // Devolvemos la vista para que se muestre en el ListView.
        return item;
    }
}
