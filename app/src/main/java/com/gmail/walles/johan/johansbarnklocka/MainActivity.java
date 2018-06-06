package com.gmail.walles.johan.johansbarnklocka;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Barnklocka";

    private AnalogClock analogClock;
    private final TimeReadout timeReadout = new TimeReadout();

    @Nullable
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        analogClock = findViewById(R.id.analogClock);
        analogClock.setOnTimeChanged(new AnalogClock.OnTimeChanged() {
            @Override
            public void onTimeChanged(int newHour, int newMinute) {
                setTime(newHour, newMinute);
            }
        });
        setTime(analogClock.getHour(), analogClock.getMinute());

        final Button analogReadout = findViewById(R.id.analogReadout);
        final Button digitalClock = findViewById(R.id.digitalClock);
        final Button digitalReadout = findViewById(R.id.digitalReadout);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.SUCCESS) {
                    Log.e(TAG, "Text-to-speech initialization failed: " + status);
                    tts = null;
                    return;
                }

                if (tts == null) {
                    Log.e(TAG, "TTS is null while setting up, giving up");
                    return;
                }

                Locale swedish = new Locale("swe");
                int setLanguageResult = tts.setLanguage(swedish);
                if (setLanguageResult == TextToSpeech.LANG_MISSING_DATA || setLanguageResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.w(TAG, "Swedish not available: " + setLanguageResult);
                    Toast.makeText(getApplicationContext(),
                            "Jag kan prata med dig om du fixar en svensk talsyntes!",
                            Toast.LENGTH_LONG).show();
                    tts = null;
                    return;
                }

                Log.i(TAG, "Swedish TTS set up, enabling buttons");

                analogReadout.setEnabled(true);
                digitalClock.setEnabled(true);
                digitalReadout.setEnabled(true);
            }
        });
        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                Log.d(TAG, "Speech started: " + utteranceId);
            }

            @Override
            public void onDone(String utteranceId) {
                Log.d(TAG, "Speech done: " + utteranceId);
            }

            @Override
            public void onError(String utteranceId) {
                Log.w(TAG, "Speech failed: " + utteranceId);
            }
        });
        analogReadout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(analogReadout.getText());
            }
        });
        digitalClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Speak the digital readout text
                speak(digitalReadout.getText());
            }
        });
        digitalReadout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(digitalReadout.getText());
            }
        });
    }

    private void speak(CharSequence text) {
        if (tts == null) {
            Log.w(TAG, "Enqueueing speech failed, TTS is null");
            return;
        }

        HashMap<String, String> speechParameters = new HashMap<>();
        speechParameters.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, text.toString());
        int result = tts.speak(text.toString(), TextToSpeech.QUEUE_FLUSH, speechParameters);
        if (result == TextToSpeech.SUCCESS) {
            Log.i(TAG, "Speech enqueued: " + text);
        } else {
            Log.w(TAG, "Enqueueing speech failed with code " + result);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (tts != null) {
            tts.shutdown();
        }
    }

    private void setTime(int hour, int minute) {
        final Button digitalClock = findViewById(R.id.digitalClock);
        digitalClock.setText(
                String.format(Locale.getDefault(),
                        "%02d:%02d", analogClock.getHour(), analogClock.getMinute()));

        final Button analogReadout = findViewById(R.id.analogReadout);
        analogReadout.setText(timeReadout.formatAnalog(hour, minute));

        final Button digitalReadout = findViewById(R.id.digitalReadout);
        digitalReadout.setText(timeReadout.formatDigital(hour, minute));
    }
}
