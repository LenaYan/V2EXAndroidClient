/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 2:58 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: IFileControl.
 * Author: Lena; Last Modified: 1/19/16 2:57 PM.
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

package com.rayman.v2ex.cache;

public interface IFileControl {

    <T> T get(String key);

    <T> T get(String key, T defaultValue);

    <T> void put(String key, T value);

    int remove(String key);

    boolean exists(String key);

    void cacheCheck();

    void cleanCacheUpdateVersion();

    String requestCacheFloderPath();
}
