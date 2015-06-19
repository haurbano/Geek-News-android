package movil.salt.weeknews.net;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.util.ArrayList;
import java.util.List;

import movil.salt.weeknews.LoginActivity;
import movil.salt.weeknews.RegisterActivity;
import movil.salt.weeknews.fragments.UsuariosFragment;
import movil.salt.weeknews.models.User;

/**
 * Created by pc on 18/06/2015.
 */
public class MongoDAO extends AsyncTask<String, Integer, Boolean > {

    static final String REGISTRAR = "1";
    static final String LOGIN = "0";
    static final String USUARIOS = "2";
    static final String SEGUIR = "3";

    String tipoAccion = "";
    Boolean flag;

    RegisterActivity registerActivity;
    LoginActivity loginActivity;
    UsuariosFragment usuariosFragment;

    static final String DB_NAME = "geeknews";
    static final String HOST = "mongodb://root:1234@ds047911.mongolab.com:47911/geeknews";

    DBObject user;
    List<User> usuarios;
    List<String> usuariosSeguidos;

    public MongoDAO(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;
    }

    public MongoDAO(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }
    public MongoDAO(UsuariosFragment usuariosFragment) {
        this.usuariosFragment = usuariosFragment;
        usuarios = new ArrayList<>();
        usuariosSeguidos = new ArrayList<>();
    }
    public MongoDAO() {
    }


    @Override
    protected Boolean doInBackground(String... datos) {


        try {
            //Creamos el cliente mongo
            MongoClientURI uri = new MongoClientURI(HOST);
            MongoClient mongoClient = new MongoClient(uri);
            DB db = mongoClient.getDB(uri.getDatabase());

            Log.d("flag-dbname", db.getName());

            DBCollection users = db.getCollection("users");
            DBCollection aristas = db.getCollection("aristas");
            Log.d("flag-usuarios", users.count() + "");

            switch (datos[0])
            {
                //region REGISTRAR
                case "REGISTRAR":
                    tipoAccion = REGISTRAR;
                    BasicDBObject user1 = new BasicDBObject();
                    user1.put("nombre", datos[1]);
                    user1.put("apellido", datos[2]);
                    user1.put("email", datos[3]);
                    user1.put("user", datos[4]);
                    user1.put("contraseña", datos[5]);

                    users.insert(user1);
                    flag = true;
                break;
                //endregion

                //region LOGIN
                case "LOGIN":
                    tipoAccion = LOGIN;

                    BasicDBObject findQueryUser = new BasicDBObject("user",datos[1]).append("contraseña",datos[2]);
                    DBCursor cursor = users.find(findQueryUser);
                    Log.d("flag-u",cursor.size()+"");


                    if (cursor.size() > 0) {
                        flag = true;
                        user = cursor.next();
                    } else {
                        flag = false;
                    }

                    break;
                //endregion

                //region USERS
                case "USERS":
                    tipoAccion = USUARIOS ;

                    //Seguidos
                    BasicDBObject findUsersLike = new BasicDBObject("Target",LoginActivity.USER_SESSION);
                    DBCursor cursorLike = aristas.find(findUsersLike);
                    Log.d("flag-usersLike.Sise",cursorLike.size()+"");
                    while (cursorLike.hasNext())
                    {
                        String uSeguidoTxt = new String();
                        DBObject uSeguido = cursorLike.next();
                        uSeguidoTxt = uSeguido.get("Source").toString();

                        Log.d("flag-userLike",uSeguidoTxt);
                        usuariosSeguidos.add(uSeguidoTxt);
                    }

                    //USUARIOS
                    DBCursor cursorUsers = users.find();
                    Log.d("flag-usuarios",cursorUsers.size()+"");

                    while (cursorUsers.hasNext())
                    {
                        User userL = new User();
                        DBObject userA = cursorUsers.next();


                        userL.setNombres(userA.get("nombre").toString());
                        userL.setApellidos(userA.get("apellido").toString());
                        userL.setEmail(userA.get("email").toString());
                        userL.setUser(userA.get("user").toString());
                        userL.setContraseña(userA.get("contraseña").toString());

                        usuarios.add(userL);

                    }
                    flag = true;
                    break;

                //endregion

                //region SEGUIR
                case "SEGUIR":
                    tipoAccion = SEGUIR;
                    BasicDBObject seguir = new BasicDBObject();
                    seguir.put("Target",LoginActivity.USER_SESSION);
                    seguir.put("Source",datos[1]);

                    aristas.insert(seguir);
                    break;
                //endregion
                default:
                    flag = false;
                    break;

            }
                if (flag)
                    return true;
                else
                    return false;

        } catch (Exception c) {
            Log.d("flag-errorMongo", c.getMessage().toString() + "");
            return false;

        }

    }





    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        if (tipoAccion == REGISTRAR && aBoolean == true)
        {
            registerActivity.ResultAsync(aBoolean);
        }
        if (tipoAccion == LOGIN && aBoolean == true)
        {
            User usuarioActual = new User();
            usuarioActual.setNombres(user.get("nombre").toString());
            usuarioActual.setApellidos(user.get("apellido").toString());
            usuarioActual.setEmail(user.get("email").toString());
            usuarioActual.setUser(user.get("user").toString());
            usuarioActual.setContraseña(user.get("contraseña").toString());

            loginActivity.ResultLogin(aBoolean,usuarioActual);
        }
        else if(tipoAccion == LOGIN && aBoolean == false)
        {
            loginActivity.ResultLogin(aBoolean,null);
        }
         if (tipoAccion == USUARIOS && aBoolean == true)
        {
            usuariosFragment.CargarUsuarios(usuarios,usuariosSeguidos);
        }


    }



}
