package com.eric.chung.epoxypagingsample.epoxy.base

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.EpoxyHolder
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.util.concurrent.ConcurrentHashMap

class ViewBindingHolder(private val epoxyModelClass: Class<*>) : EpoxyHolder() {
    // Using reflection to get the static binding method.
    // Lazy so it's computed only once by instance, when the 1st ViewHolder is actually created.
    private val bindingMethod by lazy { getBindMethodFrom(epoxyModelClass) }

    internal lateinit var dataBinding: ViewDataBinding
    override fun bindView(itemView: View) {
        // The 1st param is null because the binding method is static.
        dataBinding = bindingMethod.invoke(null, itemView) as ViewDataBinding
    }
}

// Static cache of a method pointer for each type of item used.
private val sBindingMethodByClass = ConcurrentHashMap<Class<*>, Method>()

@Suppress("UNCHECKED_CAST")
@Synchronized
private fun getBindMethodFrom(javaClass: Class<*>): Method =
    sBindingMethodByClass.getOrPut(javaClass) {
        val actualTypeOfThis = getSuperclassParameterizedType(javaClass)
        val viewBindingClass = actualTypeOfThis.actualTypeArguments[0] as Class<ViewDataBinding>
        viewBindingClass.getDeclaredMethod("bind", View::class.java)
            ?: error("The binder class ${javaClass.canonicalName} should have a method bind(View)")
    }

private fun getSuperclassParameterizedType(klass: Class<*>): ParameterizedType {
    val genericSuperclass = klass.genericSuperclass
    return (genericSuperclass as? ParameterizedType)
        ?: getSuperclassParameterizedType(genericSuperclass as Class<*>)
}