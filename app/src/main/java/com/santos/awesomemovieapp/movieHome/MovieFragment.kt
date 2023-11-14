package com.santos.awesomemovieapp.movieHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.santos.awesomemovieapp.databinding.FragmentItemListBinding
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.google.android.material.snackbar.Snackbar
import com.santos.awesomemovieapp.MovieViewModel
import com.santos.awesomemovieapp.R


/**
 * A fragment representing a list of Items.
 */
class MovieFragment : Fragment(), MovieItemListener {

    private lateinit var adapter: MyMovieRecyclerViewAdapter
    private val viewModel by hiltNavGraphViewModels<MovieViewModel>(R.id.movie_graph)

    lateinit var binding: FragmentItemListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(inflater)

        val view = binding.root
        val recycleView = binding.list

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = MyMovieRecyclerViewAdapter(this)

        adapter.setOnScrollListener(object: OnScrollListener {
            override fun onScrolled() {

            }
        })

        recycleView.apply {
            this.adapter = this@MovieFragment.adapter
            this.layoutManager = LinearLayoutManager(context)
        }

        initObservers()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when {
            ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CAMERA
            ) === PackageManager.PERMISSION_GRANTED -> {
                // Usuário aceitou
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                // Usuário negou a permissão, mostrar necessidade da permissão
                Snackbar.make(
                    view,
                    "Precisamos da permissão de uso da câmera.",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Pedir permissão", {
                    permissionResultLauncher.launch(Manifest.permission.CAMERA)
                }).show()
            }
            else -> {
                 // Pedir permissão
                permissionResultLauncher.launch(Manifest.permission.CAMERA)
            }
        }

    }

    fun initObservers() {

        viewModel.movieListLiveData.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.updateData(it)
            }
        })

        viewModel.navigateToDetailsLiveData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment()
                findNavController().navigate(action)
            }

        })
    }

    override fun onItemSelected(position: Int) {
        viewModel.onMovieSelected(position)
//        findNavController().navigate(R.id.movieDetailsFragment)

    }

    private var permissionResultLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> {
                    // Usuário aceitou
                }
                !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    view?.let {
                        Snackbar.make(
                            it,
                            "Precisamos da permissão de uso da câmera.",
                            Snackbar.LENGTH_INDEFINITE
                        ).setAction("Pedir permissão", {
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts("package", requireActivity().packageName, null)
                            intent.data = uri
                            startActivity(intent)
                        }).show()
                    }
                }
                else -> {}
            }

        }

}