package io.gritacademy.texttospeach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editRead;
    private Button readButton;
    TextToSpeech toSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readButton = findViewById(R.id.readButton);

        toSpeech = new TextToSpeech(this, status ->  {

            try {
                toSpeech.setLanguage(new Locale("en","US"));
            }catch(Exception e){
                e.printStackTrace();
                Log.wtf("WTS", "OnInit: " , e);
            }

        });

        readButton.setOnClickListener(this);


    }

    @Override
    public void onStop() {
        super.onStop();
        toSpeech.stop();
        toSpeech.shutdown();
    }

    @Override
    public void onClick(View v) {


        String toReadStr = editRead.getText().toString();


        toSpeech.speak(toReadStr, TextToSpeech.QUEUE_FLUSH, null);

    }
}