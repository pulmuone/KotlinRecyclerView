package com.example.kotlinexam

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexam.databinding.ItemPersonBinding
import com.example.kotlinexam.databinding.ItemSubjectBinding
import com.example.kotlinexam.item01.Item01Activity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SubjectAdapter { subject ->
            var intent = Intent(this, subject.clazz)
            startActivity(intent)
        }
        recycler_view.adapter = adapter

//        val people = arrayListOf<Person>()
//        for (i in 0..10) {
//            people.add(Person("사람 $i", 20))
//        }
        
        val subjects = arrayListOf<Subject>()
        subjects.add(Subject("프래그먼트에서 액티비티에 값 전달", Item01Activity::class.java))

        adapter.items = subjects
        adapter.notifyDataSetChanged()
    }
}

//
data class Subject(val title: String, val clazz: Class<Item01Activity>)

//Adapter
class SubjectAdapter(val clickListener: (person: Subject)-> Unit) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    var items = arrayListOf<Subject>()

    //ViewHolder
    class SubjectViewHolder(var binding: ItemSubjectBinding)
        : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subject, parent, false)

        val holder =SubjectViewHolder(ItemSubjectBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(items[holder.adapterPosition])
        }
        return  holder
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.binding.subject = items[position]
    }

    override fun getItemCount(): Int {
        return items.size;
    }
}


