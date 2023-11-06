package com.example.rawfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    int resourceId;
    private final String FILENAME = "text.txt";
    TextView tV;
    EditText eT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String fileName = FILENAME.substring(0, FILENAME.length() - 4);
        resourceId = this.getResources().getIdentifier(fileName, "raw", this.getPackageName());
        setContentView(R.layout.activity_main);
        tV = (TextView) findViewById(R.id.textView);
        eT = (EditText) findViewById(R.id.editTextTextMultiLine);
    }

    /**
     * get the text from the raw file
     * @param view
     */
    public void rawb(View view) {
        try {
            InputStream iS = this.getResources().openRawResource(resourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            tV.setText(sB.toString());
        }
        catch (IOException e) {
            Log.e("MainActivity", e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return super.onCreateOptionsMenu(menu);
    }
    public void textb(View view) {
        tV.setText(eT.getText());
    }


    /**
     * this method matches the credit to the option that is selected.
     * @param item The menu item that was selected.
     *
     * @return
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st = item.getTitle().toString();
        if (st.equals("Credits screen")) {
            Intent si = new Intent(this, com.example.rawfile.MainActivity2.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }

}