package cn.nitsc.hlz.keylogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.robv.android.xposed.XSharedPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tview;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tview = (TextView) findViewById(R.id.textView);
        tview.setMovementMethod(ScrollingMovementMethod.getInstance());
        tview.setText(R.string.longtext);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {

        tview.setText("");
    }

}