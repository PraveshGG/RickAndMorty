package com.example.rmt1.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rmt1.R
import com.example.rmt1.adapters.CharacterAdapter
import com.example.rmt1.models.CharactersData
import com.example.rmt1.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.coroutines.*


class CharactersFragment : Fragment(), CharacterAdapter.OnItemClickListener, SearchView.OnQueryTextListener {

    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var viewModel: MainActivityViewModel
    lateinit var items: PagedList<CharactersData>
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        lateinit var view: View
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModelForLocationList()
        initRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(isAdded){
            viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

            // Inflate the layout for this fragment
            lateinit var view: View
            view = inflater.inflate(R.layout.fragment_characters, container, false)
            return view
        }
        return null
    }



    private fun initRecyclerView() {
        characterAdapter = CharacterAdapter(this)
        recyclerview1.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }

    }

    private fun initViewModelForLocationList() {
        viewModel.beginPaging("charactersFragment")

        viewModel.getCharacterListData()
            ?.observe(viewLifecycleOwner, Observer<PagedList<CharactersData>> {
                if (it != null) {
                    characterAdapter.submitList(it)
                    items = it
                }

                if (loading != null) {
                    job = GlobalScope.launch(Dispatchers.Main) {
                        delay(500)
                        if (loading.visibility != INVISIBLE)
                            loading.visibility = INVISIBLE
                    }
                }
            })
    }

    override fun onItemClick(position: Int) {
        var customBottomSheetDialogFragment = CustomBottomSheetDialogFragment(
            items[position]!!
        )
        var fragmentMgr: FragmentManager = parentFragmentManager

        customBottomSheetDialogFragment.apply {
            show(fragmentMgr, CustomBottomSheetDialogFragment.TAG)
        }
    }


    override fun onPause() {
        job?.cancel()
//        job = null
        super.onPause()
    }

    override fun setHasOptionsMenu(hasMenu: Boolean) {
        super.setHasOptionsMenu(hasMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView: SearchView? = searchItem?.actionView as SearchView?
        searchView?.queryHint = "Search Characters By Name"
        searchView?.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        performFilteringAndSetAdapter(query!!)
        return false

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        performFilteringAndSetAdapter(newText!!)
        return true
    }

    fun performFilteringAndSetAdapter(textOrChar: String){
        if(view!=null){
            viewModel.beginFilterPaging(textOrChar)
            viewModel.getCharacterFilteredListData()
                    ?.observe(viewLifecycleOwner, Observer<PagedList<CharactersData>> {
                        if (it != null) {
                            characterAdapter.submitList(it)
                            items = it
                        }
                    })
        }

    }
}