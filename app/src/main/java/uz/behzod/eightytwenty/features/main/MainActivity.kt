package uz.behzod.eightytwenty.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.ActivityMainBinding
import uz.behzod.eightytwenty.utils.ext.gone
import uz.behzod.eightytwenty.utils.ext.show
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullContent()
        setContentView(binding.root)
        setupNavHost()
    }

    private fun setupNavHost() {
        val navController = supportFragmentManager.findFragmentById(R.id.eighty_twenty_nav_host)
        navHost = navController as NavHostFragment?
            ?: return

        NavigationUI.setupWithNavController(binding.bottomNav, navHost.navController)

        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.newNoteFragment, R.id.categoryNoteFragment
                -> {
                    binding.bottomNav.gone()
                }
                else -> {
                    binding.bottomNav.show()
                }
            }
        }
    }



            private fun setFullContent() {
                requestWindowFeature(Window.FEATURE_NO_TITLE)

                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )

                supportActionBar?.hide()
            }
        }