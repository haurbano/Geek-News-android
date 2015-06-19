package movil.salt.weeknews;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import movil.salt.weeknews.net.MongoDAO;


public class RegisterActivity extends ActionBarActivity implements View.OnClickListener {



    Button registrar;
    EditText nombre,apellido,email,user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registrar = (Button) findViewById(R.id.btn_registrar);

        nombre = (EditText) findViewById(R.id.txt_nombre);
        apellido = (EditText) findViewById(R.id.txt_apellido);
        email = (EditText) findViewById(R.id.txt_email);
        user = (EditText) findViewById(R.id.txt_usuario);
        pass = (EditText) findViewById(R.id.txt_contraseña);

        registrar.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        MongoDAO mongoDAO = new MongoDAO(this);

        mongoDAO.execute(
                "REGISTRAR",
                nombre.getText().toString(),
                apellido.getText().toString(),
                email.getText().toString(),
                user.getText().toString(),
                pass.getText().toString());

    }

    public void ResultAsync(Boolean aBoolean)
    {
        if (aBoolean) {
            nombre.setText("");
            apellido.setText("");
            email.setText("");
            user.setText("");
            pass.setText("");
            Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"Usuario NO Registrado",Toast.LENGTH_SHORT).show();
    }


}
