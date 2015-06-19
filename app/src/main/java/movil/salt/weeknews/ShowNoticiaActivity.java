package movil.salt.weeknews;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import movil.salt.weeknews.models.Noticia;


public class ShowNoticiaActivity extends ActionBarActivity {

    TextView title,link;
    WebView contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_noticia);

        title = (TextView) findViewById(R.id.title_show);
        contenido = (WebView) findViewById(R.id.contenido_show);
        link = (TextView) findViewById(R.id.link_show);

        title.setText(getIntent().getStringExtra("title"));
        contenido.loadData(getIntent().getStringExtra("contenido"),"text/html",null);
        link.setText(getIntent().getStringExtra("link"));
    }


}
