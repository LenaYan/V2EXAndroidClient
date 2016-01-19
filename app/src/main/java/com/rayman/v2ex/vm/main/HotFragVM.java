/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:56 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: HotFragVM.
 * Author: Lena; Last Modified: 1/19/16 3:56 PM.
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

package com.rayman.v2ex.vm.main;

import android.view.View;

import com.rayman.v2ex.http.callback.ReqCallback;
import com.rayman.v2ex.http.event.ErrorEvent;
import com.rayman.v2ex.presenter.main.HotFragP;
import com.rayman.v2ex.presenter.main.IHotFragP;
import com.rayman.v2ex.vm.BaseStateVM;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 15:56
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
public class HotFragVM extends BaseStateVM implements IHotFragVM {

    private IHotFragP presenter;

    public HotFragVM(HotFragP presenter) {
        this.presenter = presenter;
    }

    @Override public void onRetryClicked(View view) {

    }

    public void requestHotTopicList() {
        presenter.hot(new ReqCallback<String>() {
            @Override public void onReqStart() {

            }

            @Override public void onNetResp(String response) {

            }

            @Override public void onError(ErrorEvent errorEvent) {

            }
        });
    }

}