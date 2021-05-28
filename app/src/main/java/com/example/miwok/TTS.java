package com.example.miwok;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

public class TTS extends TextToSpeech {
    public TTS(Context context, OnInitListener listener) {
        super(context, listener);
    }

    @Override
    public int speak(CharSequence text, int queueMode, Bundle params, String utteranceId) {
        return super.speak(text, queueMode, params, utteranceId);
    }


}
