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

package com.rayman.v2ex.ui.view.test.api;

import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.base.comp.ActivityComp;
import com.rayman.v2ex.ui.view.base.presenter.IPresenter;
import com.rayman.v2ex.viewmodel.test.TestApiServiceVM;
import com.rayman.v2ex.viewmodel.test.TestApiVMModule;

import java.util.List;

import dagger.Component;
import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/1/16
 * Time: 3:29 PM
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
public interface TestApiContract {

    @PerActivity
    @Component(modules = TestApiVMModule.class, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {

        void inject(TestApiActivity testApiActivity);

        TestApiServiceVM viewModel();

    }

    interface View {

    }

    interface Presenter extends IPresenter {

        void requestLastestList(Subscriber<List<TopicEntity>> callback);

        void requestHotList(Subscriber<List<TopicEntity>> callback);

        void requestTopicListByName(String name, Subscriber<List<TopicEntity>> callback);

        void requestTopicListByNodeId(long nodeId, Subscriber<List<TopicEntity>> callback);

        void requestTopicListByNodeName(String nodeName, Subscriber<List<TopicEntity>> callback);

        void requestTopicById(long topicId, Subscriber<List<TopicEntity>> callback);

        void requestNode(String name, Subscriber<NodeEntity> callback);

        void requestNodelist(Subscriber<List<NodeEntity>> callback);

        void requestReplies(long topicId, Subscriber<List<ReplyEntity>> callback);

        void requestMember(String userName, Subscriber<MemberEntity> callback);
    }

}
