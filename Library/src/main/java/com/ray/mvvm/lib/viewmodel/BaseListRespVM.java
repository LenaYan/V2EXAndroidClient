/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:56:12 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:56:12 +0800.
 *  This file is originally created by Lena.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.mvvm.lib.viewmodel;

import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ray.mvvm.lib.BR;
import com.ray.mvvm.lib.model.model.ListRespEntity;
import com.ray.mvvm.lib.view.adapter.list.base.BaseListAdapter;
import com.ray.mvvm.lib.view.base.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.mvvm.lib.widget.anotations.PageState;
import com.ray.mvvm.lib.widget.anotations.RequestType;

/**
 * Created by Android Studio.
 * ProjectName: IndustrialControlCircle
 * Author:  Lena
 * Date: 4/28/16
 * Time: 10:31 AM
 * \ ----------------------------------------
 * \| A small leak will sink a great ship.!  |
 * \ ----------------------------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public abstract class BaseListRespVM<T extends IPresenter, R extends IView, Q> extends BaseSwipStateVM<T, R, ListRespEntity<Q>> {

    private final RecyclerView.LayoutManager layoutManager;
    BaseListAdapter<Q> adapter;

    public BaseListRespVM(T presenter, R view, RecyclerView.LayoutManager layoutManager, BaseListAdapter<Q> adapter) {
        super(presenter, view);
        this.layoutManager = layoutManager;
        this.adapter = adapter;
    }

    @Override
    public void initiallyReq(@RequestType int requestType) {
        setRequestType(requestType);
        exeRequest();
    }

    @Override
    protected final boolean isRespNull(ListRespEntity<Q> data) {
        return data == null || data.getList() == null || data.getList().size() == 0;
    }

    public BaseListAdapter<Q> getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Override
    protected void bindResp(ListRespEntity<Q> data) {
        adapter.setList(data.getList());
        layoutManager.scrollToPosition(0);
    }

    @Override
    public void setState(@PageState int state) {
        super.setState(state);
        if (state == PageState.ERROR) {
            adapter.resetList();
        }
        notifyPropertyChanged(BR.listVisibility);
    }

    @Bindable
    public int getListVisibility() {
        return (getState() == PageState.EMPTY || getState() == PageState.ERROR || getState() == PageState.LOADING) ? View.GONE : View.VISIBLE;
    }

}
