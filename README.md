
# UniversalRecycleView 简介

UniversalRecycleView 对RecycleView Adapter 进行二次封装，同时集成了当前流行的DataBinding。目的是让开发者不在关注Adapter代码开发，集中精力在业务ItemView的开发。

## 使用方法

1、定义ItemSupplier

~~~ kotlin
class MySupplier : BaseItemViewSupplier<Any>() {
    
    companion object {
        val default = MySupplier()
    }

    override fun onItemBind(holder: BaseViewHolder<DataModel<*>>, item: Any?, position: Int) {
        TODO("Not yet implemented")
    }

    override val layoutResId: Int
        get() = TODO("Not yet implemented")
}

~~~

2、创建Adapter并设置

~~~ kotlin
   val mAdapter = UniversalDataModelAdapter().also {
        it.setup(MySupplier.default)
    }
~~~

3、设置数据

~~~ kotlin
  val dataModel = DataModel<Any>().also {
            it.itemViewType = MySupplier.default.itemViewType
            it.data = "hello world"
        }
    mAdapter.dataList.add(dataModel)
~~~

## 五、联系与支持

如在使用本App过程中遇到任何问题或建议，欢迎通过以下方式联系我们：

邮箱：[jixiongxu2017@gmail.com]
我们将竭诚为您提供帮助和支持。

本开源库将持续更新和优化功能，以满足用户的不断需求。感谢您的使用！
