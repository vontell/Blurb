package org.vontech.blurb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_saying_list.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.vontech.blurb.data.SayingDatabase

/**
 * A Fragment which shows a list of saying created by the user
 */
class SayingListFragment: Fragment() {

    private var db: SayingDatabase? = null
    private lateinit var linearLayoutList: LinearLayout

    companion object {
        fun newInstance(): SayingListFragment {
            return SayingListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_saying_list, container, false)
        linearLayoutList = v!!.allSayingsLinearLayout
        refreshAllSayings()
        return v
    }

    fun refreshAllSayings() {

        doAsync {
            // Make a random see for this day
            db = SayingDatabase.getInstance(linearLayoutList.context)
            val sayings = db?.sayingDao()?.getAll()
            uiThread {
                linearLayoutList.removeAllViews()
                if (sayings!!.isNotEmpty()) {
                    for (saying in sayings) {
                        val toAdd = TextView(linearLayoutList.context)
                        toAdd.text = saying.content
                        linearLayoutList.addView(toAdd)
                    }
                } else {
                    val toAdd = TextView(linearLayoutList.context)
                    toAdd.text = toAdd.resources.getString(R.string.noSayings)
                    linearLayoutList.addView(toAdd)
                }
            }
        }

    }

}