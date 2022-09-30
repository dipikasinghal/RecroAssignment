package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var responseModel: MainResponseModel
    private lateinit var recycler: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var toolbar: Toolbar
    private var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        recycler = findViewById(R.id.list)
        swipeRefresh = findViewById(R.id.swipe_refresh)
        setResponseOfScreen()
        swipeRefresh.setOnRefreshListener {
            if (swipeRefresh.isRefreshing) {
                swipeRefresh.isRefreshing = false
                setResponseOfScreen()
            }
        }
    }

    private fun setResponseOfScreen() {
        val response =
            "{\"title\":\"About Canada\",\"rows\":[{\"title\":\"Beavers\",\"description\":\"Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony\",\"imageHref\":\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"},{\"title\":\"Flag\",\"description\":null,\"imageHref\":\"http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png\"},{\"title\":\"Transportation\",\"description\":\"It is a well known fact that polar bears are the main mode of transportation in Canada. They consume far less gas and have the added benefit of being difficult to steal.\",\"imageHref\":\"http://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg\"},{\"title\":\"Hockey Night in Canada\",\"description\":\"These Saturday night CBC broadcasts originally aired on radio in 1931. In 1952 they debuted on television and continue to unite (and divide) the nation each week.\",\"imageHref\":\"http://fyimusic.ca/wp-content/uploads/2008/06/hockey-night-in-canada.thumbnail.jpg\"},{\"title\":\"Eh\",\"description\":\"A chiefly Canadian interrogative utterance, usually expressing surprise or doubt or seeking confirmation.\",\"imageHref\":null},{\"title\":\"Housing\",\"description\":\"Warmer than you might think.\",\"imageHref\":\"http://icons.iconarchive.com/icons/iconshock/alaska/256/Igloo-icon.png\"},{\"title\":\"Public Shame\",\"description\":\" Sadly it's true.\",\"imageHref\":\"http://static.guim.co.uk/sys-images/Music/Pix/site_furniture/2007/04/19/avril_lavigne.jpg\"},{\"title\":null,\"description\":null,\"imageHref\":null},{\"title\":\"Space Program\",\"description\":\"Canada hopes to soon launch a man to the moon.\",\"imageHref\":\"http://files.turbosquid.com/Preview/Content_2009_07_14__10_25_15/trebucheta.jpgdf3f3bf4-935d-40ff-84b2-6ce718a327a9Larger.jpg\"},{\"title\":\"Meese\",\"description\":\"A moose is a common sight in Canada. Tall and majestic, they represent many of the values which Canadians imagine that they possess. They grow up to 2.7 metres long and can weigh over 700 kg. They swim at 10 km/h. Moose antlers weigh roughly 20 kg. The plural of moose is actually 'meese', despite what most dictionaries, encyclopedias, and experts will tell you.\",\"imageHref\":\"http://caroldeckerwildlifeartstudio.net/wp-content/uploads/2011/04/IMG_2418%20majestic%20moose%201%20copy%20(Small)-96x96.jpg\"},{\"title\":\"Geography\",\"description\":\"It's really big.\",\"imageHref\":null},{\"title\":\"Kittens...\",\"description\":\"Ã‰are illegal. Cats are fine.\",\"imageHref\":\"http://www.donegalhimalayans.com/images/That%20fish%20was%20this%20big.jpg\"},{\"title\":\"Mounties\",\"description\":\"They are the law. They are also Canada's foreign espionage service. Subtle.\",\"imageHref\":\"http://3.bp.blogspot.com/__mokxbTmuJM/RnWuJ6cE9cI/AAAAAAAAATw/6z3m3w9JDiU/s400/019843_31.jpg\"},{\"title\":\"Language\",\"description\":\"Nous parlons tous les langues importants.\",\"imageHref\":null}]}"
        var responseTitle: String? = null
        var responseRows: String?= null

        try {
            responseTitle = JSONObject(response).optString("title")
            Log.v("title", responseTitle)
            responseRows = JSONObject(response).optString("rows")
            Log.v("row", responseRows)

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val typeRow = object : TypeToken<List<ListModel>>() {}.type
        responseModel = MainResponseModel(responseTitle, gson.fromJson(responseRows, typeRow))
        toolbar.title = responseModel?.title
        recycler?.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerListAdapter(this@MainActivity, responseModel.rows)
        recycler?.adapter = adapter
        recycler.itemAnimator
    }
}
