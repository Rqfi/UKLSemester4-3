package com.example.uklaplikasiku

import android.app.DatePickerDialog
import android.icu.util.ULocale
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class FrenAddFragment : Fragment() {
    private var db: AppDatabase? = null
    private var frenDao: FrenDao? = null
    private var namaInput: String = ""
    private var emailInput: String = ""
    private var genderInput: String = ""
    private var usernameInput: String = ""
    private var passwordInput: String = ""
    private var edtName: TextInputEditText? = null
    private var edtEmail: TextInputEditText? = null
    private var edtUsername: TextInputEditText? = null
    private var edtPassword: TextInputEditText? = null
    private var btnSave: Button? = null

    companion object{
        fun newInstance() = FrenAddFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()
    }

    private fun initLocalDB() {
        db = AppDatabase.getAppDataBase(requireActivity())
        frenDao = db?.frenDao()
    }


    private  fun initView(){
        edtName = activity?.findViewById(R.id.textInputTextName)
        edtEmail = activity?.findViewById(R.id.textInputTextEmail)
        edtUsername = activity?.findViewById(R.id.textInputTextUsername)
        edtPassword = activity?.findViewById(R.id.textInputTextPassword)
        btnSave = activity?.findViewById(R.id.btnSingup)
        btnSave?.setOnClickListener{
            validasiInput()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        db?.close()
        edtName = null
        edtEmail = null
        edtUsername = null
        edtPassword = null
        btnSave = null
    }

    private fun validasiInput(){
        namaInput = edtName?.text.toString()
        emailInput = edtEmail?.text.toString()
        usernameInput = edtUsername?.text.toString()
        passwordInput = edtPassword?.text.toString()
        when {
            namaInput.isEmpty() -> edtName?.error = "Nama tidak boleh kosong"
            emailInput.isEmpty() -> edtEmail?.error = "Email tidak boleh kosong"
            usernameInput.isEmpty() -> edtUsername?.error = "Username tidak boleh kosong"
            passwordInput.isEmpty() -> edtPassword?.error = "Password tidak boleh kosong"
            else -> {
                val teman = Fren(
                    nama = namaInput,
                    email = emailInput,
                    username = usernameInput,
                    password = passwordInput
                )
                tambahDataTeman(teman)
            }
        }
    }

    private fun tampilToast(message: String){
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun tambahDataTeman(teman: Fren) : Job {
        return GlobalScope.launch{
            frenDao?.tambahTeman(teman)
            (activity as FrenActivity).tampilMyFriendFragment()
        }
    }
}