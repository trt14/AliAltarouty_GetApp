package com.example.getapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

//https://dojo-recipes.herokuapp.com/recipes/

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/people/")
    fun getNames(): Call<List<RecipeDetails.Datum>>




}