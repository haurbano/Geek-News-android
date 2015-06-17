package movil.salt.weeknews.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import movil.salt.weeknews.MainActivity;
import movil.salt.weeknews.R;
import movil.salt.weeknews.adapters.adapterNoticias;
import movil.salt.weeknews.models.Noticia;
import movil.salt.weeknews.net.ejecutarGet;

/**
 * A simple {@link Fragment} subclass.
 */
public class noticiasGenerales extends TitleFragment {


    //List<Noticia> noticias = new ArrayList<>();
    private final String title = "Noticias Generales";
    static ListView listNoticias;
    View v;

    public noticiasGenerales() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_noticias_generales, container, false);


        listNoticias = (ListView) v.findViewById(R.id.list_noticias_generales);
        new ejecutarGet().execute();


        return v;

    }


    @Override
    public String getTtitle() {
        return title;
    }

    public void setNoticias(ArrayList<Noticia> data)
    {
        Log.d("flag-res",data.size()+" ----respuestaFragment-noticias----");
        //adapterNoticias adapter = new adapterNoticias(getActivity(),data);
        //listNoticias.setAdapter(adapter);


    }

}
