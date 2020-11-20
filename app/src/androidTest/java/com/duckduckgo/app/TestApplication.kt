/*
 * Copyright (c) 2018 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.app

import com.duckduckgo.app.di.DaggerTestAppComponent
import com.duckduckgo.app.global.DuckDuckGoApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.TestCoroutineScope

class TestApplication : DuckDuckGoApplication() {

    /**
     * Use TestAppComponents for android tests which lets us provide alternative test modules
     *
     * See [com.duckduckgo.app.di.TestAppComponent]
     */

    @ExperimentalCoroutinesApi
    private val applicationCoroutineScope = TestCoroutineScope(SupervisorJob())

    @ExperimentalCoroutinesApi
    override fun configureDependencyInjection() {
        daggerAppComponent = DaggerTestAppComponent.builder()
            .application(this)
            .applicationCoroutineScope(applicationCoroutineScope)
            .build()
        daggerAppComponent.inject(this)
    }
}
