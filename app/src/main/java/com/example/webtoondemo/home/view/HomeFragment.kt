package com.example.webtoondemo.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import com.example.webtoondemo.home.viewModel.HomeViewModelFactory
import com.example.webtoondemo.fragmentComposeView
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    
    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    companion object {
        fun newInstance() = HomeFragment()
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = fragmentComposeView {
        ProvideWindowInsets {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                viewModelFactory = viewModelFactory
            )
        }
    }
}
