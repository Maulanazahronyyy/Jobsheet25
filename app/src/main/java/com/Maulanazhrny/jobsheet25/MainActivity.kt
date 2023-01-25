package com.Maulanazhrny.jobsheet25

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var siswaView: RecyclerView
    lateinit var siswaAdapter: SiswaAdapter
    var db = DBHelper(this, null)
    var list = ArrayList<Siswa>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        siswaView = findViewById(R.id.RVdata)
        siswaView.layoutManager = LinearLayoutManager(this)

        try {
            db.createDataBase()
            db.openDataBase()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        list = db.getSiswa()!!
        siswaAdapter = SiswaAdapter(list)
        siswaView.setAdapter(siswaAdapter)
    }
}