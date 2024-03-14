import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bitc.plumMarket.Activity.AlleramActivity


class AlleramAdapter(alleramActivity: AlleramActivity) : FragmentStateAdapter(alleramActivity) {
    var fragments: ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }

    fun removeFragment() {
        if (fragments.isNotEmpty()) {
            fragments.removeAt(fragments.size - 1)
            notifyDataSetChanged()
        }
    }
}
