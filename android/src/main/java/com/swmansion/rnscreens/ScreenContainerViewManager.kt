package com.swmansion.rnscreens

import android.view.View
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.LayoutShadowNode
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.viewmanagers.RNSScreenContainerManagerDelegate
import com.facebook.react.viewmanagers.RNSScreenContainerManagerInterface

@ReactModule(name = ScreenContainerViewManager.REACT_CLASS)
class ScreenContainerViewManager : ViewGroupManager<ScreenContainer>(), RNSScreenContainerManagerInterface<ScreenContainer> {
    private val delegate: ViewManagerDelegate<ScreenContainer>

    init {
        delegate = RNSScreenContainerManagerDelegate<ScreenContainer, ScreenContainerViewManager>(this)
    }

    protected override fun getDelegate(): ViewManagerDelegate<ScreenContainer> {
        return delegate
    }

    override fun getName(): String = REACT_CLASS

    override fun createViewInstance(reactContext: ThemedReactContext): ScreenContainer = ScreenContainer(reactContext)

    override fun addView(parent: ScreenContainer, child: View, index: Int) {
        require(child is Screen) { "Attempt attach child that is not of type RNScreens" }
        parent.addScreen(child, index)
    }

    override fun removeViewAt(parent: ScreenContainer, index: Int) {
        parent.removeScreenAt(index)
    }

    override fun removeAllViews(parent: ScreenContainer) {
        parent.removeAllScreens()
    }

    override fun getChildCount(parent: ScreenContainer): Int = parent.screenCount

    override fun getChildAt(parent: ScreenContainer, index: Int): View = parent.getScreenAt(index)

    override fun createShadowNodeInstance(context: ReactApplicationContext): LayoutShadowNode = ScreensShadowNode(context)

    override fun needsCustomLayoutForChildren(): Boolean = true

    companion object {
        const val REACT_CLASS = "RNSScreenContainer"
    }
}
