package nitt.deltatask1;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button inc,reset;
    TextView tx;
    LinearLayout linearLayout;
    private PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    int count;
    int[] color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        linearLayout = (LinearLayout) findViewById(R.id.Linear);
        inc = (Button) findViewById(R.id.button1);
        reset = (Button) findViewById(R.id.button2);
        tx = (TextView) findViewById(R.id.textView);
        inc.setOnClickListener(new ButtonFunction());
        reset.setOnClickListener(new ButtonFunction());
        count = 0;
        color = new int[]{Color.MAGENTA, Color.GREEN, Color.GRAY, Color.YELLOW, Color.BLUE, Color.RED, Color.CYAN, Color.WHITE,};
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        SharedPreferences sharedPreferences = getPreferences(count);
        count = sharedPreferences.getInt("count",0);
        tx.setText(""+count);
        linearLayout.setBackgroundColor(color[count % 8]);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getInt("count");
        tx.setText("" + count);
    }

    class ButtonFunction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.button1){
                count++;
            }
            else if(v.getId()==R.id.button2){
                count=0;
            }
            tx.setText("" + count);
            linearLayout.setBackgroundColor(color[count%8]);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getPreferences(count);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count",count);
        editor.commit();
    }

}
