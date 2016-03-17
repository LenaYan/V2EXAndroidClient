/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 4:14 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: HotFragComp.
 * Author: Lena; Last Modified: 1/19/16 4:14 PM.
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

package com.rayman.v2ex.di.component.view.main;

import com.rayman.v2ex.di.component.view.base.FragmentComp;
import com.rayman.v2ex.di.modules.vm.main.HotFragVMModule;
import com.rayman.v2ex.di.scope.PerBindingFragment;
import com.rayman.v2ex.presenter.main.HotFragP;
import com.rayman.v2ex.ui.view.main.HotFragment;
import com.rayman.v2ex.vm.main.HotFragVM;

import dagger.Component;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 16:14
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
@PerBindingFragment
@Component(modules = {HotFragVMModule.class}, dependencies = FragmentComp.class)
public interface HotFragComp extends FragmentComp {

    void inject(HotFragment hotFragment);

    HotFragVM viewModel();

    HotFragP presenter();
}
