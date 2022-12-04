package com.example.itsmeme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {
  public var currentUrl:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    loadMeme()
    }
    private fun loadMeme(){
// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"
// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                 currentUrl=response.getString("url")
                val memeImageView=findViewById<ImageView>(R.id.memeImageView)

                Glide.with(this).load(currentUrl).into(memeImageView)
            },
            Response.ErrorListener { error ->

            }
        )

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
    fun next(view: View) {
loadMeme()
    }
    fun send(view: View) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"It's a meme from ItzMeme\n$currentUrl")
        val chooser=Intent.createChooser(intent,"Share using...")
        startActivity(chooser)
    }
}