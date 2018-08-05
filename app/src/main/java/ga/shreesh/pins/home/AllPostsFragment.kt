package ga.shreesh.pins.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ga.shreesh.pins.R

class AllPostsFragment : Fragment(), ChildFragment {
    override lateinit var homePresenter: HomePresenter

    companion object {
        @JvmStatic
        fun create(): Fragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_list, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
