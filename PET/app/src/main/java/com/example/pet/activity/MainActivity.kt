package com.example.pet.activity

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.pet.R
import com.example.pet.baseclass.Pet
import com.xuexiang.xui.adapter.simple.XUISimpleAdapter
import com.xuexiang.xui.utils.DensityUtils
import com.xuexiang.xui.widget.button.roundbutton.RoundButton
import com.xuexiang.xui.widget.popupwindow.popup.XUIListPopup
import com.xuexiang.xui.widget.popupwindow.popup.XUIPopup


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
        recyclerView.layoutManager = layoutManager
        val adapter = PetAdapter(petList)
        recyclerView.adapter = adapter
        //recyclerview点击事件绑定
        adapter.setOnItemClickListener(object : PetAdapter.OnItemClickListener{
            @SuppressLint("CutPasteId")
            override fun onItemClick(view: View, position: Int) {

                Toast.makeText(applicationContext,"点击",Toast.LENGTH_SHORT).show()
                // TODO: 2021/8/3  加入点击事件
                //点击动画
                val intent = Intent(this@MainActivity,PetShowPage::class.java)
                val shareView = (recyclerView.layoutManager as StaggeredGridLayoutManager).findViewByPosition(position)
                val option = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity,shareView,"petImage")
                intent.putExtra("image",petList[position].ImageID)
                startActivity(intent,option.toBundle())
                
            }

            override fun onItemLongClick(view: View, position: Int) {
                Toast.makeText(applicationContext,"长按",Toast.LENGTH_SHORT).show()
                // TODO: 2021/8/3  加入长按点击事件

            }
        })



    }


    //初始化宠物界面（连接数据库）
    private fun initPet(){
        petList.add(Pet("aaa",1, R.drawable.interlude_anniversary2))
        petList.add(Pet("aaa",1, R.drawable.ic_launcher_background))
        petList.add(Pet("aaa",1, R.drawable.ic_launcher_background))
        petList.add(Pet("aaa",1, R.drawable.interlude_anniversary2))
        petList.add(Pet("aaa",1, R.drawable.interlude_anniversary2))
        petList.add(Pet("aaa",1, R.drawable.ic_launcher_background))
        petList.add(Pet("aaa",1, R.drawable.interlude_anniversary2))
        petList.add(Pet("aaa",1, R.drawable.ic_launcher_background))
        // TODO: 2021/8/3 连接数据库
    }

    override fun onDestroy() {
        unbinder?.unbind()
        super.onDestroy()

    }
}


/**
 * recyclerview 加载器
 */
class PetAdapter(val petList:List<Pet>) : RecyclerView.Adapter<PetAdapter.ViewHolder>(){

    private lateinit var onItemClickListener : OnItemClickListener

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val petImage : ImageView = view.findViewById(R.id.item_image)
        val roundButton:RoundButton = view.findViewById(R.id.delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_activity_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pet = petList[position]
        holder.petImage.setImageResource(pet.ImageID)
        holder.roundButton.setOnClickListener {
            // TODO: 2021/8/3 添加删除操作 
        }
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


    override fun getItemCount() = petList.size

}



