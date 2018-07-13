package cl.tgdtx1000.macrosolutions.gdtx_app;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Eliminar extends AppCompatActivity {

    private Context thisContext = this;
    private ListView lvParticulas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lvParticulas = (ListView) findViewById(R.id.lvParticulas);

        Particulas particulas = new Particulas(thisContext, "dbParticulas.db", null, 1);
        ArrayAdapter<CParticulas> arrayAdapter = new ArrayAdapter<CParticulas>(thisContext,android.R.layout.simple_list_item_1, particulas.getAll());
        lvParticulas.setAdapter(arrayAdapter);

        lvParticulas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CParticulas particulasTemp = ((CParticulas) parent.getItemAtPosition(position));

                Particulas particulas = new Particulas(thisContext, "dbParticulas.db", null, 1);
                particulas.delete(particulasTemp.getId());

                ArrayAdapter<CParticulas> arrayAdapter = new ArrayAdapter<CParticulas>(thisContext,android.R.layout.simple_list_item_1, particulas.getAll());
                lvParticulas.setAdapter(arrayAdapter);
            }
        });
    }

}
