package com.example.getapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.getapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var recipeDetails: List<RecipeDetails.Datum>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val responseText = findViewById<View>(R.id.tvName) as TextView

        getRecipes(onResult = {
            recipeDetails = it
            Log.e("Data", recipeDetails.toString())

            var stringToBePritined:String? = "";
            for(recipe in recipeDetails!!){
                stringToBePritined = stringToBePritined +recipe.title +"\n\n"
            }
            responseText.text= stringToBePritined
        } );

    }
    private fun getRecipes(onResult: (List<RecipeDetails.Datum>?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        if (apiInterface != null) {
            apiInterface.getNames()?.enqueue(object : Callback<List<RecipeDetails.Datum>> {
                override fun onResponse(
                    call: Call<List<RecipeDetails.Datum>>,
                    response: Response<List<RecipeDetails.Datum>>
                ) {
                    onResult(response.body())
                    progressDialog.dismiss()

                }

                override fun onFailure(call: Call<List<RecipeDetails.Datum>>, t: Throwable) {
                    onResult(null)
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, ""+t.message, Toast.LENGTH_SHORT).show();
                }

            })
        }
    }
}