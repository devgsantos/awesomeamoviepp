package com.santos.awesomemovieapp.MovieDetails
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.navGraphViewModels
import com.santos.awesomemovieapp.MovieViewModel
import com.santos.awesomemovieapp.R
import com.santos.awesomemovieapp.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.movie_graph) { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_movie_details,
            container,
            false)

        binding.lifecycleOwner = this
        binding.movieViewModel = viewModel

        return binding.root

    }
}