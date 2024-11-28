package com.example.roomcrudoprationkotline.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcrudoprationkotline.MainActivity
import com.example.roomcrudoprationkotline.Note
import com.example.roomcrudoprationkotline.NoteDatabase
import com.example.roomcrudoprationkotline.R
import com.example.roomcrudoprationkotline.SharedViewModel
import com.example.roomcrudoprationkotline.fragments.adapter.CustomAdapter
import kotlinx.coroutines.launch

class DisplayDataFragment : Fragment() {

    private lateinit var view: View;
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var list: List<Note>
    lateinit var customAdapter: CustomAdapter
    private lateinit var viewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_display_data, container, false)

        viewModel = (activity as MainActivity).getSharedViewModel()

        // Initialize the database
        NoteDatabase.getDatabase(requireActivity())
        noteDatabase = NoteDatabase.getDatabase(requireActivity())

        noteDatabase.noteDao().getAllNotes().observe(viewLifecycleOwner, {
            Log.e("", "database :$it");
            list = it
            customAdapter = CustomAdapter(list, { flag, pos ->
                if (flag == 1) {
                    lifecycleScope.launch {
                        noteDatabase.noteDao().delete(list[pos])
                        customAdapter.notifyItemChanged(pos)
                    }
                } else {
                    (activity as? MainActivity)?.navigateToPage(0,list[pos]) // Navigate to the second fragment
                    viewModel.sharedData.value = list[pos]
                }
            });

            val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(requireActivity())
            recyclerView.adapter = customAdapter
        });

        //val dataset = arrayOf("January", "February", "March")


        return view;
    }
}