package com.widget.universal

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.universal.lib.core.ListSpacingInset
import com.universal.lib.core.base.UniversalDataModelAdapter
import com.universal.lib.core.base.model.DataModel

class MainActivity : Activity() {

    private var mRecyclerView: RecyclerView? = null

    private val mAdapter = UniversalDataModelAdapter().also {
        it.setup(DemoSupplier2.default)
        it.setup(DemoSupplier1.default)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        testData()
    }

    private fun initView() {
        mRecyclerView = findViewById(R.id.recycle_view)
        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.addItemDecoration(ListSpacingInset(20))
        mRecyclerView?.adapter = mAdapter
    }

    private fun testData() {
        val modelSupplier1 = DataModel<String>().also {
            it.itemViewType = DemoSupplier1.default.itemViewType
            it.data = "hello world"
        }
        val modelSupplier2 = DataModel<ModelOfSupplier2>().also {
            it.itemViewType = DemoSupplier2.default.itemViewType
            it.data = ModelOfSupplier2("jack", 22)
        }
        mAdapter.dataList.add(modelSupplier1)
        mAdapter.dataList.add(modelSupplier2)
    }

}
