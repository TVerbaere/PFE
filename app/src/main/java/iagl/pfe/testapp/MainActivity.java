package iagl.pfe.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import iagl.pfe.deactivation.DeactivationService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CheckBox box = (CheckBox)findViewById(R.id.box);

        box.setOnClickListener(new CheckBox.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                registerForContextMenu(arg0);
                openContextMenu(arg0);

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, R.string.action_settings, Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_exit) {
            Toast.makeText(this, R.string.action_exit, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_second) {
            Toast.makeText(this, R.string.action_second, Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_first) {
            Toast.makeText(this, R.string.action_first, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);

    }

    public void startServicewithScript(String script) {
        Intent _intent = new Intent(MainActivity.this, DeactivationService.class);
        _intent.putExtra("script",script);
        startService(_intent);
    }
}
