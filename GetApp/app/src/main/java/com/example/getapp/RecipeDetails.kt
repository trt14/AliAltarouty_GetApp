package com.example.getapp

import com.google.gson.annotations.SerializedName

class RecipeDetails {

    var data: List<Datum>? = null

    class Datum {



        @SerializedName("name")
        var title: String? = null



        constructor(title: String?) {
            this.title = title
        }

    }
}