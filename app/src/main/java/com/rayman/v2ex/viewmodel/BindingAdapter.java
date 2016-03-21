/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/18/16 9:56 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: BindingAdapter.
 * Author: Lena; Last Modified: 1/18/16 9:56 PM.
 * This file is originally created by Lena.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rayman.v2ex.viewmodel;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.rayman.v2ex.R;
import com.rayman.v2ex.widget.utils.StringUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 21:56
 * \ ___________________
 * \| Happy New Year!  |
 * \ -------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class BindingAdapter {

    @android.databinding.BindingAdapter(value = {"normalTitleColor", "selectedTitleColor"}, requireAll = true)
    public static void setTabLayoutTextColor(TabLayout tabLayout, int normalTitleColor, int selectedTitleColor) {
        tabLayout.setTabTextColors(normalTitleColor, selectedTitleColor);
    }

    @android.databinding.BindingAdapter(value = {"viewPager", "adapter"})
    public static void setTabLayoutIcons(TabLayout tabLayout, int viewPagerId, PagerAdapter pagerAdapter) {
        ViewPager viewPager = (ViewPager) tabLayout.getRootView().findViewById(viewPagerId);
        if (viewPager.getAdapter() == null)
            viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @android.databinding.BindingAdapter(value = {"isDrawerOpen", "drawerGravity"})
    public static void controlDrawer(DrawerLayout drawerLayout, boolean isDrawerOpen, int gravity) {
        if (isDrawerOpen) {
            drawerLayout.openDrawer(gravity);
        } else {
            drawerLayout.closeDrawer(gravity);
        }
    }

    @android.databinding.BindingAdapter(value = "android:src")
    public static void loadImageByUrl(ImageView imageView, String url) {
        if (StringUtil.isEmpty(url)) return;
        Picasso.with(imageView.getContext()).load(getFormatImageUrl(url)).placeholder(R.mipmap.ic_launcher).into(imageView);
    }

    @android.databinding.BindingAdapter(value = "android:src")
    public static void loadImageByUrl(RoundedImageView imageView, String url) {
        if (StringUtil.isEmpty(url)) return;
        Picasso.with(imageView.getContext()).load(getFormatImageUrl(url)).placeholder(R.mipmap.ic_launcher).into(imageView);
    }

    public static String getFormatImageUrl(String imageUrl) {
        if (imageUrl.startsWith("//"))
            imageUrl = "http:" + imageUrl;
        return imageUrl;
    }

}
