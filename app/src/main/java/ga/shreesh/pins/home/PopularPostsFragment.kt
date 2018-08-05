package ga.shreesh.pins.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ga.shreesh.pins.R
import kotlinx.android.synthetic.main.fragment_list.*


class PopularPostsFragment : Fragment(), ChildFragment {
    lateinit var homePresenter: HomePresenter
    
    companion object {
        @JvmStatic
        fun create(): Fragment = PopularPostsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun setPresenter(presenter: HomePresenter) {
        homePresenter = presenter
        homePresenter.fetchPopularPosts(
                showPosts = {

                },
                showError = {

                })
    }
}