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
package com.alipay.hulu.shared.io.bean;

import java.util.List;

import com.alipay.hulu.shared.node.tree.export.bean.OperationStep;

/**
 * Created by lezhou.wyl on 2018/7/18.
 */

public class GeneralOperationLogBean {

    private List<OperationStep> steps;

    private String storePath;

    public List<OperationStep> getSteps() {
        return steps;
    }

    public void setSteps(List<OperationStep> steps) {
        this.steps = steps;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }
}
