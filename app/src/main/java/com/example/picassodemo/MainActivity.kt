package com.example.picassodemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.picassodemo.Adapter.DataAdapter
import com.example.picassodemo.Model.ImageData
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray



class MainActivity : AppCompatActivity() {
    private var mList: ArrayList<ImageData> = ArrayList()
    private var adapterImg: DataAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        generateData()
        adapterImg = DataAdapter(this, mList)
        recycler_view.layoutManager = GridLayoutManager(this, 3)
        recycler_view.adapter = adapterImg
    }

    private fun generateData(){
        var url = "https://jsonplaceholder.typicode.com/photos"
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            url,
            {
                var jsonDataArray = JSONArray(it)
                for (i in 0 until jsonDataArray.length()) {
                    var jsonObj = jsonDataArray.getJSONObject(i)
                    var src = jsonObj.getString("url")
                    mList.add(ImageData(src))
                }
                adapterImg?.setData(mList)
            },
            {

            }
        )
        requestQueue.add(request)
    }
}