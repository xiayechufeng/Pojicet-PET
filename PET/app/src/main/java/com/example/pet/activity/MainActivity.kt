package com.example.pet.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.pet.R
import com.example.pet.baseclass.Pet
import com.github.jdsjlzx.interfaces.OnItemClickListener
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder


class MainActivity : AppCompatActivity() {


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recyclerview)
    lateinit var recyclerView: RecyclerView


    var unbinder : Unbinder? = null

    private val petList = ArrayList<Pet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        unbinder = ButterKnife.bind(this)

        initPet()
        val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
//        recyclerView.layoutManager = layoutManager
        recyclerView.layoutManager = layoutManager
        val adapter = PetAdapter(petList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : PetAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(applicationContext,"aaa",Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClick(view: View, position: Int) {
                Toast.makeText(applicationContext,"aaa",Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun initPet(){
        petList.add(Pet("aaa",1, R.drawable.interlude_anniversary2))
        petList.add(Pet("aaa",1, R.drawable.ic_launcher_background))
        petList.add(Pet("aaa",1, R.drawable.ic_launcher_background))
        petList.add(Pet("aaa",1, R.drawable.interlude_anniversary2))
        petList.add(Pet("aaa",1, R.drawable.interlude_anniversary2))
        petList.add(Pet("aaa",1, R.drawable.ic_launcher_background))
        petList.add(Pet("aaa",1, R.drawable.interlude_anniversary2))
        petList.add(Pet("aaa",1, R.drawable.ic_launcher_background))
    }

    override fun onDestroy() {
        unbinder?.unbind()
        super.onDestroy()

    }
}

class PetAdapter(val petList:List<Pet>) : RecyclerView.Adapter<PetAdapter.ViewHolder>(){

    private lateinit var onItemClickListener : OnItemClickListener

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val petImage : ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_activity_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pet = petList[position]
        holder.petImage.setImageResource(pet.ImageID)
        holder.petImage.setOnClickListener {
            onItemClickListener.onItemClick(holder.itemView,position)
        }

    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }


    override fun getItemCount() = petList.size

}



