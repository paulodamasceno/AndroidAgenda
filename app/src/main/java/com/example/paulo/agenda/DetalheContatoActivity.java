package com.example.paulo.agenda;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulo.agenda.model.Contato;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DetalheContatoActivity extends BaseActivity {

    @InjectView(R.id.dfoto_contato)
    ImageView fotoContato;

    @InjectView(R.id.dnome_contato)
    TextView nomeContato;

    @InjectView(R.id.demail_contato)
    TextView emailContato;

    @InjectView(R.id.dfone_contato)
    TextView phoneContato;

    @InjectView(R.id.dcelular_contato)
    TextView cellPhoneContato;

    @InjectView(R.id.dendereco_contato)
    TextView addressContato;

    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_contato);
        ButterKnife.inject(this);
        initToolbar();

        if(!getIntent().hasExtra("contato")){
            return;
        }

        contato = (Contato) getIntent().getSerializableExtra("contato");

        if(!TextUtils.isEmpty(contato.getPhoto())){
            Picasso.with(this).load(contato.getPhoto())
                    .into(fotoContato);
        }

        nomeContato.setText(contato.getName());
        emailContato.setText(contato.getEmail());
        phoneContato.setText(contato.getPhone());
        cellPhoneContato.setText(contato.getCellphone());
        addressContato.setText(contato.getCellphone());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe_contato, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_map) {

            Intent intent = new Intent(DetalheContatoActivity.this,MapActivity.class);
            intent.putExtra("contato",contato);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
