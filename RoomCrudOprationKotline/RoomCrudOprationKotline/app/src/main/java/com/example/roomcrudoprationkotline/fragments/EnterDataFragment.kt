package com.example.roomcrudoprationkotline.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.roomcrudoprationkotline.MainActivity
import com.example.roomcrudoprationkotline.Note
import com.example.roomcrudoprationkotline.NoteDatabase
import com.example.roomcrudoprationkotline.R
import com.example.roomcrudoprationkotline.SharedViewModel
import com.google.android.material.internal.ViewUtils.hideKeyboard
import kotlinx.coroutines.launch

class EnterDataFragment : Fragment() {

    private lateinit var view: View
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var btnSubmit: Button
    private lateinit var editText: EditText
    private lateinit var viewModel: SharedViewModel
    lateinit var noteEdits: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_enter_data, container, false)

        btnSubmit = view.findViewById(R.id.btnSubmit);
        editText = view.findViewById(R.id.etNotes);

        viewModel = (activity as MainActivity).getSharedViewModel()

        viewModel.sharedData.observe(viewLifecycleOwner) { data ->
            noteEdits = data
            editText.setText(noteEdits.title)
            btnSubmit.text = "Update"
        }

        // Initialize the database
        NoteDatabase.getDatabase(requireActivity())
        noteDatabase = NoteDatabase.getDatabase(requireActivity())


        btnSubmit.setOnClickListener {
            if (editText.text == null || editText.text.toString().equals("") ){
                Toast.makeText(requireActivity(),"Please enter data",Toast.LENGTH_SHORT).show()
            }else{
                if (noteEdits != null && noteEdits.title != null &&!noteEdits.title.equals("")){
                    val newNote =
                        Note(title = editText.text.toString(), content = "This is a sample note.", id = noteEdits.id)
                    lifecycleScope.launch {
                        noteDatabase.noteDao().update(newNote)
                        Toast.makeText(requireActivity(), "Data Update successfully", Toast.LENGTH_SHORT)
                            .show()
                        noteEdits = Note(0,"","")
                        btnSubmit.setText("Submit")
                        editText.text.clear()
                    }


                }else {
                    val newNote =
                        Note(title = editText.text.toString(), content = "This is a sample note.")
                    lifecycleScope.launch {
                        noteDatabase.noteDao().insert(newNote)
                    }
                    editText.text.clear()
                    Toast.makeText(requireActivity(), "Data enter successfully", Toast.LENGTH_SHORT)
                        .show()
                }
                editText.hideKeyboard();
            }
        }



        return view;
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}