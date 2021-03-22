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
package com.alipay.hulu.common.utils.patch;

import java.io.File;

import com.alipay.hulu.common.application.LauncherApplication;
import com.alipay.hulu.common.injector.InjectorService;
import com.alipay.hulu.common.tools.AbstCmdLine;
import com.alipay.hulu.common.tools.CmdTools;
import com.alipay.hulu.common.utils.FileUtils;

import android.content.Context;

/**
 * Created by qiaoruikai on 2019-04-19 16:29.
 */
public class PatchContextImpl extends PatchContext {
    private final File rootFolder;
    private final File assetsFolder;
    private PatchLoadResult patch;


    public PatchContextImpl(PatchLoadResult patch) {
        this.patch = patch;
        rootFolder = new File(patch.root);

        String assetsFolderPath = patch.assetsPath;
        if (assetsFolderPath != null) {
            assetsFolder = new File(patch.assetsPath);
        } else {
            assetsFolder = new File(rootFolder, "assets");
            assetsFolder.mkdirs();
        }
    }

    @Override
    public File getPatchRoot() {
        return rootFolder;
    }

    @Override
    public File getAssetsRoot() {
        return assetsFolder;
    }

    @Override
    public File getDataFolder(String name) {
        return FileUtils.getSubDir(name);
    }

    @Override
    public AbstCmdLine startCmdLine() {
        return CmdTools.openCmdLine();
    }

    @Override
    public AbstCmdLine startCmdLine(String cmd) {
        return CmdTools.openAdbStream(cmd);
    }

    @Override
    public String executeHighPrivilegeCmd(String cmd) {
        return CmdTools.execHighPrivilegeCmd(cmd);
    }

    @Override
    public String executeHighPrivilegeCmd(String cmd, int timeout) {
        return CmdTools.execHighPrivilegeCmd(cmd, timeout);
    }

    @Override
    public void register(Object object) {
        InjectorService.g().register(object);
    }

    @Override
    public void unregister(Object object) {
        InjectorService.g().unregister(object);
    }

    @Override
    public Context getApplicationContext() {
        return LauncherApplication.getContext();
    }

    @Override
    public Context getTopActivity() {
        return LauncherApplication.getInstance().loadActivityOnTop();
    }

    @Override
    public Context getRunningService() {
        return LauncherApplication.getInstance().loadRunningService();
    }
}
