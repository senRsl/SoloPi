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
package com.alipay.hulu.activity;

import java.util.ArrayList;
import java.util.List;

import com.alipay.hulu.R;
import com.alipay.hulu.common.application.LauncherApplication;
import com.alipay.hulu.common.utils.ClassUtil;
import com.alipay.hulu.common.utils.patch.PatchLoadResult;
import com.alipay.hulu.ui.HeadControlPanel;
import com.alipay.hulu.upgrade.PatchRequest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class PatchStatusActivity extends BaseActivity {
    private final List<PatchLoadResult> patches = new ArrayList<>();
    private HeadControlPanel header;
    private ListView patchList;
    private BaseAdapter patchItemAdapter;
    private View emptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patch_status);

        initView();
        initData();
    }

    private void initView() {
        header = _findViewById(R.id.patch_status_header);
        patchList = _findViewById(R.id.patch_status_list);
        emptyView = findViewById(R.id.patch_status_empty_view);

        patchList.setEmptyView(emptyView);

        header.setMiddleTitle(getString(R.string.settings__plugin_list));


        header.setBackIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initData() {
        final LayoutInflater inflater = LayoutInflater.from(this);

        patchItemAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return patches.size();
            }

            @Override
            public Object getItem(int position) {
                return patches.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, final ViewGroup parent) {
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_patch_status, parent, false);
                    View delete = convertView.findViewById(R.id.item_patch_delete);
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final PatchLoadResult patch = (PatchLoadResult) v.getTag();
                            new AlertDialog.Builder(PatchStatusActivity.this)
                                    .setMessage(getString(R.string.patch_status__delete_plugin, patch.name))
                                    .setPositiveButton(R.string.constant__confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ClassUtil.removePatch(patch.name);
                                            dialog.dismiss();
                                            reloadData();
                                        }
                                    })
                                    .setNegativeButton(R.string.constant__cancel, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                        }
                    });
                }

                PatchLoadResult patch = (PatchLoadResult) getItem(position);

                TextView title = (TextView) convertView.findViewById(R.id.item_patch_name);
                TextView version = (TextView) convertView.findViewById(R.id.item_patch_version);
                TextView filter = (TextView) convertView.findViewById(R.id.item_patch_filter);
                View delete = convertView.findViewById(R.id.item_patch_delete);

                title.setText(patch.name);
                version.setText(getString(R.string.patch_status__version_code, patch.version));
                filter.setText(patch.filter);
                delete.setTag(patch);

                return convertView;
            }
        };
        patchList.setAdapter(patchItemAdapter);

        header.setInfoIconClickListener(R.drawable.icon_reload, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog(getString(R.string.patch_status__loading_plugin));
                PatchRequest.updatePatchList(new PatchRequest.LoadPatchCallback() {
                    @Override
                    public void onLoaded() {
                        dismissProgressDialog();
                        toastShort(R.string.patch_status__load_success);

                        // 避免过快插件还未加载完毕
                        LauncherApplication.getInstance().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                reloadData();
                            }
                        }, 1000);
                    }

                    @Override
                    public void onFailed() {
                        dismissProgressDialog();
                        toastShort(R.string.patch_status__load_failed);
                    }
                });
            }
        });

        reloadData();
    }

    /**
     * 通知数据变化
     */
    private void reloadData() {
        patches.clear();
        patches.addAll(ClassUtil.getAllPatches());
        patchItemAdapter.notifyDataSetChanged();
    }

}
