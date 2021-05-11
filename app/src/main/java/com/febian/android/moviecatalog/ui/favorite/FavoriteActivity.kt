package com.febian.android.moviecatalog.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.PopupMenu
import com.febian.android.moviecatalog.R
import com.febian.android.moviecatalog.databinding.ActivityFavoriteBinding
import com.febian.android.moviecatalog.utils.SortUtils
import dagger.android.support.DaggerAppCompatActivity

class FavoriteActivity : DaggerAppCompatActivity() {

    private lateinit var activityFavoriteBinding: ActivityFavoriteBinding

    private val sortListeners: MutableList<DataSortListener> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowFlag()

        activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        setupViewPager()
        activityFavoriteBinding.btnBack.setOnClickListener { this@FavoriteActivity.finish() }
        activityFavoriteBinding.btnSort.setOnClickListener { showSortMenu() }
    }

    private fun setWindowFlag() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun setupViewPager() {
        val sectionsPagerAdapter = FavoriteSectionsPagerAdapter(this, supportFragmentManager)
        activityFavoriteBinding.viewPagerFavorite.adapter = sectionsPagerAdapter
        activityFavoriteBinding.tabsFavorite.setupWithViewPager(activityFavoriteBinding.viewPagerFavorite)
    }

    @Synchronized
    @SuppressLint("RtlHardcoded")
    private fun showSortMenu() {
        var sort = ""
        val popupMenu = PopupMenu(this, activityFavoriteBinding.btnSort, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.sort_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_title_desc -> sort = SortUtils.TITLE_DESC
                R.id.action_title_asc -> sort = SortUtils.TITLE_ASC
                R.id.action_best_rating -> sort = SortUtils.BEST_RATING
                R.id.action_random_sort -> sort = SortUtils.RANDOM
            }
            menuItem.isChecked = true
            for (listener in sortListeners) {
                listener.onDataSorted(sort)
            }
            true
        }
        popupMenu.show()
    }

    @Synchronized
    fun registerDataSortListener(listener: DataSortListener?) {
        listener?.let { sortListeners.add(it) }
    }

    @Synchronized
    fun unregisterDataSortListener(listener: DataSortListener?) {
        sortListeners.remove(listener)
    }

    interface DataSortListener {
        fun onDataSorted(sort: String)
    }
}