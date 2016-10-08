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

import android.os.Bundle;

import com.rayman.v2ex.R;
import com.rayman.v2ex.databinding.ActivityTestApiBinding;
import com.rayman.v2ex.ui.view.base.page.BaseDIActivity;
import com.rayman.v2ex.ui.view.base.view.ILifeCycle;
import com.rayman.v2ex.viewmodel.test.TestApiServiceVM;
import com.rayman.v2ex.viewmodel.test.TestApiVMModule;

import javax.inject.Inject;

public class TestApiActivity extends BaseDIActivity implements TestApiContract.View {

    @Inject
    TestApiServiceVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestApiBinding binding = bindLayout(R.layout.activity_test_api);
        binding.setViewModel(viewModel);
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerTestApiContract_Comp.builder()
                .activityComp(getActivityComp())
                .testApiVMModule(new TestApiVMModule(this))
                .build()
                .inject(this);
    }

}