package com.example.kotlinexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexam.databinding.ItemPersonBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PersonAdapter { person ->
            toast(person.toString())
        }
        recycler_view.adapter = adapter

        val people = arrayListOf<Person>()
        for (i in 0..10) {
            people.add(Person("사람 $i", 20))
        }
        adapter.items = people
        adapter.notifyDataSetChanged()
    }
}

//모델
data class Person(var name: String, var age: Int)

//Adapter
class PersonAdapter(val callback: (person: Person)-> Unit) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    var items = arrayListOf<Person>()

    //ViewHolder
    class PersonViewHolder(var binding: ItemPersonBinding)
        : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)

        val holder =PersonViewHolder(ItemPersonBinding.bind(view))
        view.setOnClickListener {
            callback.invoke(items[holder.adapterPosition])
        }
        return  holder
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.person = items[position]
    }

    override fun getItemCount(): Int {
        return items.size;
    }
}