/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/18/16 10:37 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: AppModule.
 * Author: Lena; Last Modified: 1/18/16 10:37 PM.
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

package com.rayman.v2ex.di.modules;

import android.content.Context;

import com.rayman.v2ex.anotations.ContextType;
import com.rayman.v2ex.di.modules.base.FileCacheModule;
import com.rayman.v2ex.di.modules.base.OkHttpModule;
import com.rayman.v2ex.di.modules.base.RetrofitModule;
import com.rayman.v2ex.di.modules.base.SPModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 22:37
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
@Module(includes = {
        SPModule.class,
        OkHttpModule.class,
        RetrofitModule.class,
        FileCacheModule.class,
        APIServiceModule.class
})
public class AppModule {

    private Context application;

    public AppModule(Context application) {
        this.application = application;
    }

    @Provides @Named(ContextType.APPLICATION) Context provideApp() {
        return application;
    }

}
