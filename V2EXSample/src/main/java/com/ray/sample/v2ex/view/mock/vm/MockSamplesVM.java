/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 12 Nov 2016 21:57:13 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 12 Nov 2016 21:57:13 +0800.
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

package com.ray.sample.v2ex.view.mock.vm;

import android.view.View;

import com.ray.mvvm.lib.viewmodel.BaseVM;
import com.ray.sample.v2ex.view.mock.ListSampleActivity;
import com.ray.sample.v2ex.view.mock.WrapperListSampleActivity;
import com.ray.sample.v2ex.view.mock.contract.MockSamplesContract;

public class MockSamplesVM extends BaseVM<MockSamplesContract.Presenter, MockSamplesContract.View> {

    public MockSamplesVM(MockSamplesContract.Presenter presenter, MockSamplesContract.View view) {
        super(presenter, view);
    }

    public void onListSampleClicked(View view) {
        this.view.intent(ListSampleActivity.class);
    }

    public void onWrapperListSampleClicked(View view) {
        this.view.intent(WrapperListSampleActivity.class);
    }

}
