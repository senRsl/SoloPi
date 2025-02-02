/*
 * Copyright (C) 2015-present, Ant Financial Services Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.hulu.shared.io;

import com.alipay.hulu.shared.io.bean.RecordCaseInfo;
import com.alipay.hulu.shared.node.tree.export.bean.OperationStep;

import android.content.Context;

/**
 * Created by qiaoruikai on 2019-08-06 14:20.
 */
public interface OperationStepProcessor {
    void onStartRecord(RecordCaseInfo recordCaseInfo);

    boolean onStopRecord(Context context);

    void onOperationStep(int operationIdx, OperationStep step);
}
