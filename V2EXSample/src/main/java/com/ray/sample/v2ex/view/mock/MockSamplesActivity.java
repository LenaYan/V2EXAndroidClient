/*
 *  Copyright (C) 2015 Rayman Yan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.sample.v2ex.view.mock;

import android.os.Bundle;

import com.ray.mvvm.lib.view.base.page.BaseDIActivity;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.view.mock.contract.DaggerMockSamplesContract_Comp;
import com.ray.sample.v2ex.view.mock.contract.MockSamplesContract;
import com.ray.sample.v2ex.view.mock.vm.MockSamplesVM;
import com.ray.sample.v2ex.view.mock.vm.module.MockSamplesVMModule;

import javax.inject.Inject;

public class MockSamplesActivity extends BaseDIActivity implements MockSamplesContract.View {

    @Inject MockSamplesVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout(R.layout.activity_mock_samples, viewModel);
    }

    @Override
    public void buildComp() {
        DaggerMockSamplesContract_Comp
                .builder()
                .activityComp(getActivityComp())
                .mockSamplesVMModule(new MockSamplesVMModule(this))
                .build()
                .inject(this);

    }
}
