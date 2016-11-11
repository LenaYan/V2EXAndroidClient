/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:11:30 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:11:30 +0800.
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

package com.ray.mvvm.lib.widget.anotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.ray.mvvm.lib.widget.anotations.RequestType.CONTENT_LOADING;
import static com.ray.mvvm.lib.widget.anotations.RequestType.LOAD_MORE;
import static com.ray.mvvm.lib.widget.anotations.RequestType.SILENT;
import static com.ray.mvvm.lib.widget.anotations.RequestType.SWIP_REFRESH;

@Documented
@Retention(RetentionPolicy.SOURCE)
@IntDef({CONTENT_LOADING, SWIP_REFRESH, SILENT,LOAD_MORE})
public @interface RequestType {

    int CONTENT_LOADING = 0;
    int SWIP_REFRESH = 1;
    int SILENT = 2;
    int LOAD_MORE = 3;

}