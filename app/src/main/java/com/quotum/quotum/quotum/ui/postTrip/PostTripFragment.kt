package com.quotum.quotum.quotum.ui.postTrip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.quotum.quotum.quotum.R

class PostTripFragment : Fragment() {

    private lateinit var postTripViewModel: PostTripViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postTripViewModel =
            ViewModelProviders.of(this).get(PostTripViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_posttrip, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        postTripViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}