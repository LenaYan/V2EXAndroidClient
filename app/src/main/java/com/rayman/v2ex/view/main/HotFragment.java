/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 11:49 AM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: HotFragment.
 * Author: Lena; Last Modified: 1/19/16 11:48 AM.
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

package com.rayman.v2ex.view.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayman.v2ex.databinding.FragmentHotBinding;
import com.rayman.v2ex.di.component.view.main.DaggerHotFragComp;
import com.rayman.v2ex.di.component.view.main.HotFragComp;
import com.rayman.v2ex.di.modules.vm.main.HotFragVMModule;
import com.rayman.v2ex.view.base.BaseFragment;
import com.rayman.v2ex.vm.main.HotFragVM;

import javax.inject.Inject;

public class HotFragment extends BaseFragment {

    @Inject HotFragVM viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHotBinding binding = FragmentHotBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.requestHotTopicList();
    }

    @Override public HotFragComp buildComp() {
        return DaggerHotFragComp.builder()
                .fragmentComp(super.buildComp())
                .hotFragVMModule(new HotFragVMModule())
                .build();
    }

    @Override public void onInject() {
        buildComp().inject(this);
    }

}
