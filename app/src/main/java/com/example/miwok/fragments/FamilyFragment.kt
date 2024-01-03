package com.example.miwok.fragments

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.miwok.R
import com.example.miwok.model.Word
import com.example.miwok.adapters.WordAdapter
import java.util.*

class FamilyFragment : Fragment() {
    var tts: TextToSpeech? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.word_list, container, false)
        tts = TextToSpeech(activity) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val r = tts!!.setLanguage(Locale("ar-XA"))
                if (r == TextToSpeech.LANG_MISSING_DATA || r == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(activity, "Language not supported", Toast.LENGTH_SHORT).show()
                } else {
                    Log.i("TTS", "lang ok")
                }
            } else {
                Toast.makeText(activity, "Init fail", Toast.LENGTH_SHORT).show()
            }
        }
        val words = ArrayList<Word>()
        words.add(Word("Father", "Ab","أب", R.drawable.family_father))
        words.add(Word("Mother", "Um","أم", R.drawable.family_mother))
        words.add(Word("Son", "Ibn","ابن", R.drawable.family_son))
        words.add(Word("Daughter", "Ibnah","ابنة", R.drawable.family_daughter))
        words.add(Word("Brother", "Akh","أخ", R.drawable.family_older_brother))
        words.add(Word("Sister", "Ukht","أخت", R.drawable.family_older_sister))
        words.add(Word("Grandfather", "Jad","الجد", R.drawable.family_grandfather))
        words.add(Word("Grandmother", "Jaddah","الجدة", R.drawable.family_grandmother))
        words.add(Word("Grandson", "Ḥafeed","حفيد", R.drawable.family_younger_brother))
        words.add(Word("Granddaughter", "Ḥafeedah","حفيدة", R.drawable.family_younger_sister))
        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        val itemsAdapter = WordAdapter(requireActivity(), words, R.color.category_family, tts!!)
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        val listView = rootView.findViewById<ListView>(R.id.global_list)
        listView.adapter = itemsAdapter
        listView.onItemClickListener = OnItemClickListener { parent, view, position, id -> tts!!.speak(words[position].mivokword, TextToSpeech.QUEUE_FLUSH, null, position.toString()) }
        return rootView
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}