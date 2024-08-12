package com.example.alienstage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResenaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resena)

        data class Review(val userName: String, val comment: String)


        class ReviewAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

            inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
                val commentTextView: TextView = itemView.findViewById(R.id.commentTextView)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
                return ReviewViewHolder(view)
            }

            override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
                val review = reviews[position]
                holder.userNameTextView.text = review.userName
                holder.commentTextView.text = review.comment
            }

            override fun getItemCount(): Int = reviews.size
        }

        class ReviewsActivity : AppCompatActivity() {

            private lateinit var reviewsRecyclerView: RecyclerView
            private lateinit var reviewAdapter: ReviewAdapter
            private val reviewsList = mutableListOf<Review>()

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_resena)

                reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView)
                reviewAdapter = ReviewAdapter(reviewsList)
                reviewsRecyclerView.adapter = reviewAdapter
                reviewsRecyclerView.layoutManager = LinearLayoutManager(this)

                val submitReviewButton = findViewById<Button>(R.id.submitReviewButton)
                val reviewEditText = findViewById<EditText>(R.id.reviewEditText)

                submitReviewButton.setOnClickListener {
                    val newReview = Review("Usuario", reviewEditText.text.toString())
                    reviewsList.add(newReview)
                    reviewAdapter.notifyItemInserted(reviewsList.size - 1)
                    reviewEditText.text.clear()
                }
            }
        }



    }
}
