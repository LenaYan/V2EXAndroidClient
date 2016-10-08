/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 22:22:35 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 22:22:35 +0800.
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

package com.rayman.v2ex.ui.view.main.hot;

import com.rayman.v2ex.di.scope.PerBindingFragment;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.common.IMemberCellView;
import com.rayman.v2ex.ui.view.common.ITopicCellView;
import com.rayman.v2ex.ui.view.base.comp.FragmentComp;
import com.rayman.v2ex.ui.view.base.presenter.IPresenter;
import com.rayman.v2ex.viewmodel.main.HotFragVM;
import com.rayman.v2ex.viewmodel.main.HotFragVMModule;

import java.util.List;

import dagger.Component;
import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/1/16
 * Time: 3:10 PM
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
public interface HotFragContract {

    @PerBindingFragment
    @Component(modules = {HotFragVMModule.class}, dependencies = FragmentComp.class)
    interface Comp extends FragmentComp {

        void inject(HotFragment hotFragment);

        HotFragVM viewModel();

        HotFragP presenter();
    }

    interface Presenter extends IPresenter {
        void requestHotList(Subscriber<List<TopicEntity>> subscriber);
    }

    interface View extends ITopicCellView, IMemberCellView {
    }

}