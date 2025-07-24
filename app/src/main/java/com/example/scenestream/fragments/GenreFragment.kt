package com.example.scenestream.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.example.scenestream.*
import com.example.scenestream.models.*
import com.example.scenestream.adapters.*

class GenreFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenreAdapter
    private val genreList = mutableListOf<Genre>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_genre, container, false)

        recyclerView = view.findViewById(R.id.genreRecyclerView)
        adapter = GenreAdapter(genreList)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        loadGenres()

        return view
    }

    private fun loadGenres() {
        db.collection("genres")
            .get()
            .addOnSuccessListener { result ->
                genreList.clear()
                for (doc in result) {
                    val name = doc.getString("name") ?: ""
                    val iconUrl = doc.getString("iconUrl") ?: ""
                    genreList.add(Genre(name, iconUrl))
                }
                adapter.notifyDataSetChanged()
            }
    }
}
