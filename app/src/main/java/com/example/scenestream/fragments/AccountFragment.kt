package com.example.scenestream.fragments

import com.example.scenestream.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AccountFragment : Fragment() {

    private lateinit var nameView: TextView
    private lateinit var emailView: TextView
    private lateinit var profilePic: ImageView

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        nameView = view.findViewById(R.id.accountName)
        emailView = view.findViewById(R.id.accountEmail)
        profilePic = view.findViewById(R.id.accountProfilePic)

        val uid = auth.currentUser?.uid
        val email = auth.currentUser?.email

        if (uid != null) {
            db.collection("users").document(uid).get()
                .addOnSuccessListener { doc ->
                    nameView.text = doc.getString("name") ?: "SceneStreamer"
                    emailView.text = email ?: "unknown@email.com"

                    val picUrl = doc.getString("profilePicUrl")
                    if (!picUrl.isNullOrEmpty()) {
                        Glide.with(this)
                            .load(picUrl)
                            .placeholder(R.drawable.ic_profile_placeholder)
                            .circleCrop()
                            .into(profilePic)
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "Failed to load account info: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }

        return view
    }
}
