package movil.salt.weeknews.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import movil.salt.weeknews.R;
import movil.salt.weeknews.models.Noticia;

/**
 * Created by pc on 16/06/2015.
 */
public class adapterNoticias extends BaseAdapter {

    Context context;
    ArrayList<Noticia> data;

    public adapterNoticias(Context context, ArrayList<Noticia> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if (view == null)
        {
            v = View.inflate(context, R.layout.adapter_noticias,null);
        }
        else
        {
            v= view;
        }

        Noticia noticia = (Noticia) getItem(i);

        TextView title = (TextView) v.findViewById(R.id.title);
        title.setText(noticia.getTitle());

        TextView link = (TextView) v.findViewById(R.id.link);
        link.setText(noticia.getLink());

        TextView contenido = (TextView) v.findViewById(R.id.contenido);
        contenido.setText(noticia.getContenido());

        return v;
    }
}
