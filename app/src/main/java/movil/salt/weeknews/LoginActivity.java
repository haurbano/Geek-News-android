package movil.salt.weeknews;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    EditText user,pass;
    Button ingresar,registrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        ingresar = (Button) findViewById(R.id.ingresar);
        registrar = (Button) findViewById(R.id.registrar);

        ingresar.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ingresar:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.registrar:
                Intent intent1 = new Intent(this,RegisterActivity.class);
                startActivity(intent1);
                break;
        }

    }
}
