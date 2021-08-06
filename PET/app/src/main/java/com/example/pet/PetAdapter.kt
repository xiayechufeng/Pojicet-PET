package com.example.pet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.pet.baseclass.Pet

/**
 * recyclerview 加载器
 */
class PetAdapter(val petList:ArrayList<Pet>) : RecyclerView.Adapter<PetAdapter.ViewHolder>(){

    private lateinit var onItemClickListener : OnItemClickListener

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val petImage : ImageView = view.findViewById(R.id.item_image)
//        val roundButton:RoundButton = view.findViewById(R.id.delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_activity_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pet = petList[position]
        holder.petImage.setImageResource(pet.ImageID)

//        holder.roundButton.setOnClickListener {
//            if (petList.size <= 1){
//                Toast.makeText(holder.roundButton.context,"不能删除了",Toast.LENGTH_SHORT).show()
//            }else{
//                remove(position)
//
//            }
//        }

        holder.petImage.setOnClickListener {
            onItemClickListener.onItemClick(holder.itemView,position)
        }
        holder.petImage.setOnLongClickListener{
            onItemClickListener.onItemLongClick(holder.itemView,position)
            true
        }

    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    public fun remove(position: Int){
        petList.removeAt(position)
        notifyItemRemoved(position)
        if (position != petList.size) {
            notifyItemRangeChanged(position, petList.size - position);
        }

    }

    public fun addItem(position: Int){
        petList.add(position, Pet("aaa",1, R.drawable.interlude_anniversary2))
        notifyItemInserted(position)
        if (position != petList.size) {
            notifyItemRangeChanged(position, petList.size - position);
        }
    }




    override fun getItemCount() = petList.size

}
