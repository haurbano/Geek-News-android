package movil.salt.weeknews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import movil.salt.weeknews.models.User;
import movil.salt.weeknews.net.MongoDAO;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    public static final String SHARED_NAME = "session";
    public static final String KEY_SESSION = "usuario";

    public static String USER_SESSION= "";

    EditText user,pass;
    Button ingresar,registrar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Geek News");

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        ingresar = (Button) findViewById(R.id.ingresar);
        registrar = (Button) findViewById(R.id.registrar);

        ingresar.setOnClickListener(this);
        registrar.setOnClickListener(this);



        preferences = getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        String usu = preferences.getString(KEY_SESSION, "null");

        Log.d("flag-shared-fueraIF",usu);
        /**
        if (usu != "null")
        {
            Log.d("flag-shared-dentroIF",usu);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        **/

    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ingresar:
                MongoDAO mongoDAO = new MongoDAO(this);
                mongoDAO.execute("LOGIN",user.getText().toString(),pass.getText().toString());


                break;
            case R.id.registrar:
                Intent intent1 = new Intent(this,RegisterActivity.class);
                startActivity(intent1);
                break;
        }

    }

    public void ResultLogin(Boolean aBoolean, User user1)
    {
        if (aBoolean)
        {
            user.setText("");
            pass.setText("");

            SharedPreferences.Editor edit = preferences.edit();

            edit.putString(KEY_SESSION,user1.getUser());
            edit.commit();
            USER_SESSION = user1.getUser();

            Log.d("flag-user que llega desde TASK",user1.getUser());

            String usu = preferences.getString(KEY_SESSION, "null");
            Log.d("flag-shared-ensegioda de ingresado",usu);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

            finish();
        }
        else
        {
            user.setText("");
            pass.setText("");
            Toast.makeText(this,"Usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show();
        }
    }



}
