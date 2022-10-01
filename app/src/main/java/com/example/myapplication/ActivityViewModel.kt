package com.example.myapplication

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

@BindingAdapter("valueList", "viewModel")
fun bindRecycler(
    recyclerView: RecyclerView,
    valueList: ArrayList<ListModel>?,
    viewModel: ActivityViewModel
) {
    if (recyclerView.adapter == null) {
        if (valueList!!.isNotEmpty()) {
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.adapter =
                RecyclerListAdapter(recyclerView.context, valueList)
        }
    }
}

class ActivityViewModel : BaseObservable() {
    private lateinit var responseModel: MainResponseModel
    protected val parentJob = Job()
    protected val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO
    val scope = CoroutineScope(coroutineContext)
    var gson = Gson()

    @Bindable
    var title: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @Bindable
    var list: ArrayList<ListModel>? = ArrayList()
        set(value) {
            field = value
            notifyPropertyChanged(BR.list)
        }

    @Bindable
    var loading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }

    init {
        scope.launch {
            setResponseOfScreen()
        }
    }

    private fun setResponseOfScreen() {
        val response = CommonUtil.Json
        var responseTitle: String? = null
        var responseRows: String? = null

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
        title = responseModel.title
        list = responseModel.rows
    }

    fun refresh() {
        loading = true
        scope.launch {
            setResponseOfScreen()
        }
        loading = false
    }
}