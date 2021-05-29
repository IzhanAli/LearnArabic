package com.example.miwok;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class NumbersFragment extends Fragment {
    TextToSpeech tts;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public NumbersFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment NumbersFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static NumbersFragment newInstance(String param1, String param2) {
//        NumbersFragment fragment = new NumbersFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int r = tts.setLanguage(new Locale("ar-XA"));
                    if (r == TextToSpeech.LANG_MISSING_DATA || r == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getActivity(), "Language not supported", Toast.LENGTH_SHORT).show();


                    } else {
                        Log.i("TTS", "lang ok");
                    }
                } else {
                    Toast.makeText(getActivity(), "Init fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one", "wahid", R.drawable.number_one));
        words.add(new Word("two", "isnan", R.drawable.number_two));
        words.add(new Word("three", "salasa", R.drawable.number_three));
        words.add(new Word("four", "arbaa", R.drawable.number_four));
        words.add(new Word("five", "khamsa", R.drawable.number_five));
        words.add(new Word("six", "sittha", R.drawable.number_six));
        words.add(new Word("seven", "sabaa", R.drawable.number_seven));
        words.add(new Word("eight", "samania", R.drawable.number_eight));
        words.add(new Word("nine", "tis aa", R.drawable.number_nine));
        words.add(new Word("ten", "ashraa", R.drawable.number_ten));
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers, tts);
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.

        ListView listView = rootView.findViewById(R.id.global_list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tts.speak(words.get(position).getMivokword(), TextToSpeech.QUEUE_FLUSH, null, String.valueOf(position));
            }
        });

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}