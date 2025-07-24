// This file is completed
package com.example.scenestream.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.example.scenestream.adapters.*
import com.example.scenestream.models.*
import com.example.scenestream.*


class MovieFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private val movieList = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        recyclerView = view.findViewById(R.id.movieGrid)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = MovieAdapter(movieList)
        recyclerView.adapter = adapter

        fetchMovies()

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchMovies() {
        val db = FirebaseFirestore.getInstance()
        db.collection("movies").get()
            .addOnSuccessListener { result ->
                movieList.clear()
                for (document in result) {
                    val title = document.getString("title") ?: ""
                    val posterUrl = document.getString("posterUrl") ?: ""
                    movieList.add(Movie(title, posterUrl))
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Failed to load movies: ${exception.message}", Toast.LENGTH_LONG).show()            }
    }
}