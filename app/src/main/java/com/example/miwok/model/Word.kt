package com.example.miwok.model

class Word {
    var defaultword: String
        private set
    var mivokword: String
        private set
    var imageId = 0
        private set
    var arabicword: String
        private set

    constructor(def: String, mivok: String, arabic: String) {
        defaultword = def
        mivokword = mivok
        arabicword = arabic
    }

    constructor(def: String, mivok: String, arabic: String, res: Int) {
        defaultword = def
        mivokword = mivok
        arabicword = arabic
        imageId = res
    }

    fun hasImg(): Boolean {
        return imageId != IMAGE_PROVIDED
    }

    companion object {
        const val IMAGE_PROVIDED = -1
    }
}