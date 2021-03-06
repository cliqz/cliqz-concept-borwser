package com.cliqz.components.search

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.facebook.react.ReactRootView

/**
 * @author Sam Macbeth
 */
abstract class LazyReactView  @JvmOverloads constructor (
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    abstract val reactView: ReactRootView
    var viewIsLoaded = false

    private fun loadView() {
        if (reactView.parent != null) {
            val parentView = reactView.parent as FrameLayout
            parentView.removeView(reactView)
        }

        addView(reactView)
        viewIsLoaded = true
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        if (!viewIsLoaded && visibility == View.VISIBLE) {
            loadView()
        }
        super.onVisibilityChanged(changedView, visibility)
    }

    fun onDestroy() {
        removeView(reactView)
    }
}