package by.krukouski.triviaapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import by.krukouski.triviaapp.R
import by.krukouski.triviaapp.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false
        )
        binding.nextMatchButton.setOnClickListener {view: View ->
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToTitleFragment())
        }
        //Toast.makeText(context, "NumCorrect: ${args?.numCorrect}, NumQuestions: ${args?.numQuestions}", Toast.LENGTH_SHORT).show()
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)
        if(null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            menu?.findItem(R.id.share)?.isVisible = false
        }
    }

    private fun getShareIntent() : Intent {
        val args = arguments?.let { GameWonFragmentArgs.fromBundle(it) }
        return ShareCompat.IntentBuilder.from(activity)
            .setText(getString(R.string.share_success_text, args?.numCorrect, args?.numQuestions))
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
