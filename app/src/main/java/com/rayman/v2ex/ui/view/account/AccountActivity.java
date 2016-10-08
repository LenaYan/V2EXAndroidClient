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

package com.rayman.v2ex.ui.view.account;

import android.content.Intent;
import android.os.Bundle;

import com.rayman.v2ex.R;
import com.rayman.v2ex.app.ParaKeys;
import com.rayman.v2ex.databinding.ActivityAccountBinding;
import com.rayman.v2ex.model.model.member.MemberBaseEntity;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.base.page.BaseDIActivity;
import com.rayman.v2ex.ui.view.base.view.ILifeCycle;
import com.rayman.v2ex.ui.view.node.NodeActivity;
import com.rayman.v2ex.ui.view.topic.TopicActivity;
import com.rayman.v2ex.viewmodel.account.AccountVM;
import com.rayman.v2ex.viewmodel.account.AccountVMModule;

import javax.inject.Inject;

public class AccountActivity extends BaseDIActivity implements AccountContract.View {

    @Inject AccountVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        MemberBaseEntity memberBaseEntity = intent.getParcelableExtra(ParaKeys.MEMBER_BASE_KEY);
        ActivityAccountBinding binding = bindLayout(R.layout.activity_account);
        binding.setViewModel(viewModel);
        viewModel.init(new MemberEntity(memberBaseEntity));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityComp().refWatcher().watch(viewModel);
    }

    @Override
    public void buildComp() {
        DaggerAccountContract_Comp.builder()
                .activityComp(getActivityComp())
                .accountVMModule(new AccountVMModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void onTopicCliced(TopicEntity topicEntity) {
        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra(ParaKeys.TOPIC_KEY, topicEntity);
        intent(intent);
    }

    @Override
    public void onNodeClicked(NodeEntity nodeEntity) {
        Intent intent = new Intent(this, NodeActivity.class);
        intent.putExtra(ParaKeys.NODE_KEY, nodeEntity);
        intent(intent);
    }

}
