package movil.salt.weeknews.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import movil.salt.weeknews.LoginActivity;
import movil.salt.weeknews.R;
import movil.salt.weeknews.models.User;
import movil.salt.weeknews.net.MongoDAO;

/**
 * Created by pc on 19/06/2015.
 */
public class adapterUsuarios extends BaseAdapter  {

    Context context;
    List<User> data;
    List<String> usuariosSeguidos;

    public adapterUsuarios(Context context, List<User> data, List<String> usuariosSeguidos) {

        this.context = context;
        this.data = data;
        this.usuariosSeguidos = usuariosSeguidos;
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
            v = View.inflate(context, R.layout.adapter_usuarios,null);
        }
        else
        {
            v= view;
        }
        final User user = (User) getItem(i);
        if (LoginActivity.USER_SESSION != data.get(i).getUser()) {

            TextView nombre = (TextView) v.findViewById(R.id.txt_nombre_list);
            nombre.setText(user.getNombres());

            TextView apellidos = (TextView) v.findViewById(R.id.txt_apellido_list);
            apellidos.setText(user.getApellidos());

            TextView user1 = (TextView) v.findViewById(R.id.txt_user_list);
            user1.setText(user.getUser());

            final Button seguir = (Button) v.findViewById(R.id.btn_seguir);

            seguir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("flag-button",user.getUser());
                    MongoDAO mongoDAO = new MongoDAO();
                    mongoDAO.execute("SEGUIR",user.getUser());
                    seguir.setEnabled(false);
                }
            });

            if (usuariosSeguidos != null) {
                for (int k = 0; k < usuariosSeguidos.size(); k++) {
                    Log.d("flag-UserAdapter-K", usuariosSeguidos.get(k));
                    Log.d("flag-UserAdapter-I", data.get(i).getUser());
                    if (data.get(i).getUser().equals(usuariosSeguidos.get(k))) {
                        Log.d("flag-User-If: ", data.get(i).getUser() + " es igual a " + usuariosSeguidos.get(k) + "**********");
                        seguir.setEnabled(false);
                    }

                }
            }
        }




        return v;
    }


}
