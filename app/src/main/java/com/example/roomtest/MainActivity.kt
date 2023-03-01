package com.example.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.roomtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val db = MainDb.getDb(this)

        db.getDao().getAllItem().asLiveData().observe(this){
            binding.tvList.text=""
            for (item in it) {
                val text = "Id: ${item.id} Name: ${item.name} Price: ${item.price}\n"
                binding.tvList.append(text)
            }
        }

        binding.btnSave.setOnClickListener{
            val item = Item(null,
                binding.etName.text.toString(),
                binding.etPrice.text.toString())

            Thread{
                db.getDao().insertItem(item)
            }.start()
        }
    }
}