package org.vontech.blurb

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_current_saying.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.vontech.blurb.data.Saying
import org.vontech.blurb.data.SayingDatabase
import java.text.SimpleDateFormat
import java.util.*

/**
 * A Fragment which allows users to view the daily chosen saying
 */
class CurrentSayingFragment: Fragment() {

    private var db: SayingDatabase? = null
    private var currentTextView: View? = null

    companion object {
        fun newInstance(): CurrentSayingFragment {
            return CurrentSayingFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the view and load a random saying if available
        currentTextView =  inflater?.inflate(R.layout.fragment_current_saying, container, false)
        loadRandomSaying()
        return currentTextView
    }

    @SuppressLint("SimpleDateFormat")
    fun loadRandomSaying() {

        doAsync {
            // Make a random see for this day
            val date = SimpleDateFormat("dd-MM-yyyy").format(Date())
            db = SayingDatabase.getInstance(currentTextView!!.context)
            val sayings = db?.sayingDao()?.getAll()
            var sayingChosen: Saying? = null
            if (sayings!!.isNotEmpty()) {
                sayingChosen = sayings[Random(date.hashCode().toLong()).nextInt(sayings.size)]
            }
            uiThread {
                if (sayingChosen != null) {
                    currentTextView!!.currentSayingTextView.text = sayingChosen.content
                }
            }
        }

    }




}