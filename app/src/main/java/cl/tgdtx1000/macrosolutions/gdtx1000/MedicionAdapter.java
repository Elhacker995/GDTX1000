package cl.tgdtx1000.macrosolutions.gdtx1000;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jorgejaramillo on 6/29/18.
 */

public class MedicionAdapter extends ArrayAdapter<Medicion> {
    //Atributos
    //Clase Interna
    private static class ViewHolder{
        TextView text1;
    }
    //Constructor de la tabla
    public MedicionAdapter(Context context, ArrayList<Medicion> detector) {
        super(context, android.R.layout.simple_dropdown_item_1line, detector);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Medicion medicion = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            viewHolder.text1 = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text1.setText(medicion.getFecha());

        return convertView;
    }

    @Override
    public long getItemId(int position)
    {
        return getItem(position).getId_Serie();
    }
}
