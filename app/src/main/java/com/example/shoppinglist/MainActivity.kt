package com.example.shoppinglist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var bar: BottomAppBar
    private lateinit var fab : FloatingActionButton
    private lateinit var menu : Menu

    private var isListViewType = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coordinatorLayout = coordinator_layout
        bar = bottom_app_bar
        fab = floating_action_button
        setSupportActionBar(bar)
        fab.setOnClickListener {
            showSnackbar("FAAAAAAAAAAAAB !")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.app_bar_cards -> showSnackbar("Fav menu item is clicked!")
            R.id.app_bar_view_type -> handleViewType()
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }

        return true
    }

    private fun showSnackbar(text: CharSequence) {
        val anchorView : View = if (fab.visibility == View.VISIBLE) fab else bar
        Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_SHORT)
            .setAnchorView(anchorView)
            .show()
    }

    private fun handleViewType(){
        if (isListViewType){
            isListViewType = false
            showSnackbar("Grid view mode")
            menu.getItem(1).icon = resources.getDrawable(R.drawable.ic_view_list_black_24dp, this.theme)

        } else {
            isListViewType = true
            showSnackbar("List view mode")
            menu.getItem(1).icon = resources.getDrawable(R.drawable.ic_view_module_black_24dp, this.theme)
        }
    }
}
