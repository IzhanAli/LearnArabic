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

/**
 * A simple [Fragment] subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
class NumbersFragment  //    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;
    : Fragment() {
    var tts: TextToSpeech? = null

    //    /**
    //     * Use this factory method to create a new instance of
    //     * this fragment using the provided parameters.
    //     *
    //     * @param param1 Parameter 1.
    //     * @param param2 Parameter 2.
    //     * @return A new instance of fragment NumbersFragment.
    //     */
    //    public static NumbersFragment newInstance(String param1, String param2) {
    //        NumbersFragment fragment = new NumbersFragment();
    //        Bundle args = new Bundle();
    //        args.putString(ARG_PARAM1, param1);
    //        args.putString(ARG_PARAM2, param2);
    //        fragment.setArguments(args);
    //        return fragment;
    //    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
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
        words.add(Word("one", "wahid","واحد", R.drawable.number_one))
        words.add(Word("two", "isnan","اثنين", R.drawable.number_two))
        words.add(Word("three", "salasa","ثلاثة", R.drawable.number_three))
        words.add(Word("four", "arbaa","أربعة", R.drawable.number_four))
        words.add(Word("five", "khamsa","خمسة", R.drawable.number_five))
        words.add(Word("six", "sittha","ستة", R.drawable.number_six))
        words.add(Word("seven", "sabaa","سبعة", R.drawable.number_seven))
        words.add(Word("eight", "samania","ثمانية", R.drawable.number_eight))
        words.add(Word("nine", "tis aa","تسعة", R.drawable.number_nine))
        words.add(Word("ten", "ashraa","عشرة", R.drawable.number_ten))
        val itemsAdapter = WordAdapter(requireActivity(), words, R.color.category_numbers, tts!!)
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        val listView = rootView.findViewById<ListView>(R.id.global_list)
        listView.adapter = itemsAdapter
        listView.onItemClickListener = OnItemClickListener { parent, view, position, id -> tts!!.speak(words[position].mivokword, TextToSpeech.QUEUE_FLUSH, null, position.toString()) }
        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
    }
}