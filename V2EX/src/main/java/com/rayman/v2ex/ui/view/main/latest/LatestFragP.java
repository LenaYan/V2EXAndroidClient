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

package com.rayman.v2ex.ui.view.main.latest;

import com.ray.mvvm.lib.model.http.ExObserver;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.ray.mvvm.lib.view.base.presenter.BasePresenter;
import com.rayman.v2ex.model.service.TopicService;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 16:36
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
public class LatestFragP extends BasePresenter implements LatestFragContract.Presenter {

    private TopicService topicService;

    @Inject
    LatestFragP(Retrofit retrofit, RefWatcher refWatcher) {
        super(refWatcher);
        this.topicService = retrofit.create(TopicService.class);
    }

    @Override
    public void requestLatestList(ExObserver<List<TopicEntity>> observer) {
        subscribeCommonReq(topicService.latest(), observer);
    }
}
