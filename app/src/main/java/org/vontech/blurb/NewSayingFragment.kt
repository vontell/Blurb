package org.vontech.blurb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_new_saying.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.uiThread
import org.vontech.blurb.data.Saying
import org.vontech.blurb.data.SayingDatabase

/**
 * A Fragment which allows users to add new sayings
 */
class NewSayingFragment: Fragment() {

    private var db: SayingDatabase? = null
    private lateinit var sayingEditText: EditText

    companion object {
        fun newInstance(): NewSayingFragment {
            return NewSayingFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_new_saying, container, false)
        sayingEditText = v!!.newSayingEditText
        v.clearButton.onClick { sayingEditText.setText("") }
        v.saveButton.onClick { addSaying() }
        return v
    }

    private fun addSaying() {

        doAsync {
            // Make a random see for this day
            val newSaying = Saying(null, sayingEditText.text.toString())
            db = SayingDatabase.getInstance(sayingEditText.context)
            db?.sayingDao()?.insert(newSaying)
            for (fragment in fragmentManager.fragments) {
                if (fragment is SayingListFragment) {
                    fragment.refreshAllSayings()
                }
                if (fragment is CurrentSayingFragment) {
                    fragment.loadRandomSaying()
                }
            }
            uiThread {
                sayingEditText.setText("")
                Toast.makeText(sayingEditText.context, resources.getString(R.string.savedNotif), Toast.LENGTH_SHORT).show()
            }
        }

    }

}