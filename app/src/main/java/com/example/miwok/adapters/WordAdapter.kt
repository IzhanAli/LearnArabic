package com.example.miwok.adapters

import android.content.Context
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.miwok.R
import com.example.miwok.model.Word

class WordAdapter(context: Context, wordArrayList: ArrayList<Word>?, private val mColorRes: Int, private val textToSpeech: TextToSpeech) : ArrayAdapter<Any?>(context, 0, wordArrayList!! as List<Any?>) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(
                    R.layout.material_list_item, parent, false)
        }
        val word = getItem(position) as Word?
        val eng = listItemView!!.findViewById<TextView>(R.id.text1)
        eng.text = word!!.defaultword
        eng.setTextColor(ContextCompat.getColor(context, mColorRes))
        val miv = listItemView.findViewById<TextView>(R.id.mtrl_list_item_secondary_text)
        miv.text = word.mivokword
        val arb = listItemView.findViewById<TextView>(R.id.mtrl_list_item_tertiary_text)
        arb.text = word.arabicword
        val img = listItemView.findViewById<ImageView>(R.id.mtrl_list_item_icon)
        if (word.hasImg()) {
            img.setImageResource(word.imageId)
            img.visibility = View.VISIBLE
        } else {
            img.visibility = View.GONE
        }

        val play = listItemView.findViewById<ImageView>(R.id.listplay)
        play.setOnClickListener { textToSpeech.speak(word.arabicword, TextToSpeech.QUEUE_FLUSH, null, position.toString()) }
        return listItemView
    }
}