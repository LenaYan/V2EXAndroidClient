<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <import type="${packageName}.vm.${pageName}VM" />

        <variable
            name="viewModel"
            type="${pageName}VM" />
    </data>

    <android.support.design.widget.CoordinatorLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        tools:context="${relativePackage}.${activityClass}">

        <android.support.design.widget.AppBarLayout
            android:layout_height="wrap_content"
            android:layout_width="match_parent"
            android:theme="@style/${themeNameAppBarOverlay}">

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                app:popupTheme="@style/${themeNamePopupOverlay}" />

        </android.support.design.widget.AppBarLayout>

        <include 
            layout="@layout/${simpleLayoutName}"
            app:viewModel="@{viewModel}" />

    </android.support.design.widget.CoordinatorLayout>
</layout>