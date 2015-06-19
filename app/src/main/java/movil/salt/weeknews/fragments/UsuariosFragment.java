package movil.salt.weeknews.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import movil.salt.weeknews.R;
import movil.salt.weeknews.adapters.adapterUsuarios;
import movil.salt.weeknews.models.User;
import movil.salt.weeknews.net.MongoDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuariosFragment extends TitleFragment {

    private final String title = "Personas";

    ListView listUsuarios;
    View v;

    public UsuariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_usuarios, container, false);

        listUsuarios = (ListView) v.findViewById(R.id.list_usuarios);



        MongoDAO mongoDAO = new MongoDAO(this);
        mongoDAO.execute("USERS");
        return v;
    }


    @Override
    public String getTtitle() {
        return title;
    }

    public void CargarUsuarios(List<User> usuarios, List<String> usuariosSeguidos)
    {
        adapterUsuarios adapterUsuarios = new adapterUsuarios(v.getContext(),usuarios,usuariosSeguidos);
        listUsuarios.setAdapter(adapterUsuarios);
    }
}
