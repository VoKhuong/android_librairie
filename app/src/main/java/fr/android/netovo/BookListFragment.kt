package fr.android.netovo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import timber.log.Timber

class BookListFragment : Fragment() {

    private var books = ArrayList<Book>()
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            books = it.getParcelableArrayList<Book>("books") as ArrayList<Book>
        }
        books.forEach {
            Timber.i("LIVRE : " + it.title + "   " + it.price)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridLayoutManager(context, 1)
                adapter = BookListRecyclerViewAdapter(books, listener)
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(book: Book?)
    }

    companion object {

        @JvmStatic
        fun newInstance(bookList: ArrayList<Book>) =
                BookListFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList("books", bookList)
                    }
                }
    }
}
