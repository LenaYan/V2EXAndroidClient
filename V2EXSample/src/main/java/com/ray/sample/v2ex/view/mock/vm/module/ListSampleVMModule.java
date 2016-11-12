/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 12 Nov 2016 21:29:47 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 12 Nov 2016 21:29:47 +0800.
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

package com.ray.sample.v2ex.view.mock.vm.module;

import android.support.v7.widget.LinearLayoutManager;

import com.ray.mvvm.lib.di.modules.LayoutManagerModule;
import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.mvvm.lib.widget.anotations.ListType;
import com.ray.sample.v2ex.adapter.TestListAdapter;
import com.ray.sample.v2ex.view.mock.contract.ListSampleContract;
import com.ray.sample.v2ex.view.mock.presenter.ListSampleP;
import com.ray.sample.v2ex.view.mock.vm.ListSampleVM;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = LayoutManagerModule.class)
public class ListSampleVMModule {

    private ListSampleContract.View view;

    public ListSampleVMModule(ListSampleContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    ListSampleVM provideVM(ListSampleP presenter, @Named(ListType.VERTICAL) LinearLayoutManager layoutManager) {
        return new ListSampleVM(presenter, view, layoutManager, new TestListAdapter(view));
    }

}
