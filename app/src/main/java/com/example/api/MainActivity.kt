package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: AlbumViewModel
    lateinit var albumAdapter: AlbumAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(AlbumViewModel::class.java)
        // Initialize the RecyclerView and adapter
        albumAdapter = AlbumAdapter(emptyList()) // Start with an empty list
        binding.albumRecyclerView.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(this@MainActivity) // Use a linear layout manager
        }
        // Observe the LiveData from ViewModel
        viewModel.album.observe(this, Observer { albums ->
            // Update the adapter when new data is available
            albumAdapter.updateAlbums(albums)
        })
    }
    /*
      //  viewModel.fetchAlbums()
        viewModel.album.observe(this, Observer {
            for (i in it){
                Log.i("user",i.userId.toString())
                Log.i("user",i.id.toString())
                Log.i("user",i.title.toString())
            }
        })
    }
 */
}