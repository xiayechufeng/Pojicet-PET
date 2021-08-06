package com.example.pet.activity

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.pet.PetAdapter
import com.example.pet.R
import com.example.pet.baseclass.Pet
import com.xuexiang.xui.adapter.simple.XUISimpleAdapter
import com.xuexiang.xui.widget.popupwindow.popup.XUIListPopup


class MainActivity : AppCompatActivity() {


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recyclerview)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.swipe)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    var unbinder : Unbinder? = null

    private val petList = ArrayList<Pet>()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        unbinder = ButterKnife.bind(this)



        //recyclerView 的实现，瀑布布局的实现
        initPet()
        val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView.layoutManager is StaggeredGridLayoutManager){
                    (recyclerView.layoutManager as StaggeredGridLayoutManager).invalidateSpanAssignments()
                }
            }
        })
        recyclerView.layoutManager = layoutManager
        val adapter = PetAdapter(petList)
        recyclerView.adapter = adapter
        //recyclerview点击事件绑定
        adapter.notifyItemRangeChanged(2,petList.size-2)
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
                Toast.makeText(applicationContext,(position+1).toString(),Toast.LENGTH_SHORT).show()
                // TODO: 2021/8/3  加入长按点击事件

            }
        })


        //下拉刷新设置

        swipeRefreshLayout.setColorSchemeResources(R.color.xui_config_color_dark_blue_gray)
        swipeRefreshLayout.setOnRefreshListener {
            adapter.addItem(position = petList.size)
            swipeRefreshLayout.isRefreshing = false
        }


        //显示popup






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





