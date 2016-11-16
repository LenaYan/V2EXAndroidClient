/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:47:37 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:47:37 +0800.
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

package com.ray.mvvm.lib.view.adapter.list.base;

import android.databinding.ViewDataBinding;
import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ray.mvvm.lib.app.Constants;
import com.ray.mvvm.lib.databinding.ListStateEmptyBinding;
import com.ray.mvvm.lib.databinding.ListStateErrorBinding;
import com.ray.mvvm.lib.databinding.ListStateLoadingBinding;
import com.ray.mvvm.lib.view.adapter.OnItemClick;
import com.ray.mvvm.lib.view.adapter.list.viewholder.BaseViewHolder;
import com.ray.mvvm.lib.viewmodel.ListStateVM;
import com.ray.mvvm.lib.widget.anotations.PageState;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public abstract class ListAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int TYPE_EMPTY = 391;
    private static final int TYPE_LOADING = 392;
    private static final int TYPE_ERROR = 393;
    private static final int TYPE_ITEM = 394;

    private static final int NO_INDEX = -99;
    private List<T> list;
    private LongSparseArray<T> wrapMap = new LongSparseArray<>();
    private OnItemClick<T> itemClick;
    private int state = PageState.CONTENT;

    public ListAdapter() {
    }

    public ListAdapter(OnItemClick<T> itemClick) {
        this.itemClick = itemClick;
    }

    public ListAdapter(List<T> list) {
        this.list = list;
    }

    public ListAdapter(List<T> list, OnItemClick<T> itemClick) {
        this.list = list;
        this.itemClick = itemClick;
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding;
        switch (state) {
            case PageState.EMPTY:
                viewDataBinding = ListStateEmptyBinding.inflate(inflater, parent, false);
                break;
            case PageState.ERROR:
                viewDataBinding = ListStateErrorBinding.inflate(inflater, parent, false);
                break;
            case PageState.LOADING:
            case TYPE_LOADING:
                viewDataBinding = ListStateLoadingBinding.inflate(inflater, parent, false);
                break;
            default:
                viewDataBinding = createBinding(LayoutInflater.from(parent.getContext()), parent, viewType);
                break;
        }
        return new BaseViewHolder(viewDataBinding);
    }

    @Override
    public int getItemCount() {
        switch (state) {
            case PageState.EMPTY:
            case PageState.ERROR:
            case PageState.LOADING:
                return 1;
            default:
                return getDataCount() + getHeaderCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (state) {
            case PageState.EMPTY:
                return TYPE_EMPTY;
            case PageState.ERROR:
                return TYPE_ERROR;
            case PageState.LOADING:
                return TYPE_LOADING;
            default:
                return TYPE_ITEM;
        }
    }

    public int getDataCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public final void onBindViewHolder(BaseViewHolder holder, int position) {
        bindingViewHolder(holder, position);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        bindingViewHolder(holder, position);
    }

    private void bindingViewHolder(BaseViewHolder holder, int position) {
        switch (state) {
            case PageState.EMPTY:
            case PageState.ERROR:
            case PageState.LOADING:
                holder.bindData(new ListStateVM(state));
                break;
            default:
                holder.bindData(createViewModel(getItemViewType(position), position));
                break;
        }
    }

    protected abstract ViewDataBinding createBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType);

    protected Object createViewModel(int viewType, int position) {
        return new CellVM<>(getItem(position), position, itemClick);
    }

    public void setState(@PageState int state) {
        if (state == this.state || state == PageState.REFRESH)
            return;
        this.state = state;
    }

    public void setList(List<T> list) {
        this.list = list;
        wrapMap.clear();
        if (getItemCount() > 0) {
            for (T t : this.list) {
                final long index = getIndex(t);
                if (index == NO_INDEX)
                    break;
                wrapMap.put(index, t);
            }
        }
        notifyDataSetChanged();
    }

    public void setListWithoutHeader(List<T> list) {
        int oldSize = 0;
        if (this.list != null) {
            oldSize = this.list.size();
        }
        this.list = list;
        wrapMap.clear();
        if (getItemCount() > 0) {
            for (T t : this.list) {
                final long index = getIndex(t);
                if (index == NO_INDEX)
                    break;
                wrapMap.put(index, t);
            }
        }
        notifyItemRangeRemoved(getHeaderCount(), oldSize);
        notifyItemRangeInserted(getHeaderCount(), list.size());
    }

    public List<T> getList() {
        return list;
    }

    public void setItemClick(OnItemClick<T> itemClick) {
        this.itemClick = itemClick;
    }

    public final T getItem(int position) {
        final int headerCount = getHeaderCount();
        return list == null ? null : list.get(position - headerCount);
    }

    public T getItemByIndex(long id) {
        return wrapMap.get(id);
    }

    public void addItem(T t) {
        addItem(0, t);
    }

    public void addItem(int position, T t) {
        if (t == null)
            return;
        if (list == null) {
            list = new ArrayList<>();
            wrapMap.clear();
        }
        list.add(position, t);
        final long index = getIndex(t);
        if (index != NO_INDEX)
            wrapMap.put(index, t);
        notifyItemInserted(position);
    }

    public void addItems(List<T> insertList) {
        if (insertList == null || insertList.size() == 0)
            return;
        if (list == null) {
            list = new ArrayList<>();
            wrapMap.clear();
        }
        int oldCount = getItemCount();
        int insertCount = insertList.size();
        list.addAll(insertList);
        for (T t : insertList) {
            long index = getIndex(t);
            if (index == NO_INDEX)
                break;
            wrapMap.put(index, t);
        }
        notifyItemRangeInserted(oldCount, insertCount);
//        notifyDataSetChanged();
    }

    public void updateItem(int position, T item) {
        if (position != Constants.NO_POSITION && item != null) {
            T origin = getItem(position);
            if (origin != null && getIndex(origin) == getIndex(item)) {
                final int headerCount = getHeaderCount();
                list.set(position - headerCount, item);
                notifyItemChanged(position, null);
            }
        }
    }

    public boolean notifyItem(T t) {
        if (t == null) return false;
        final long key = getIndex(t);
        if (key == NO_INDEX) return false;
        T localItem = wrapMap.get(key);
        if (localItem == null) return false;
        final int position = list.indexOf(localItem);
        if (position == -1) return false;
        final int headerCount = getHeaderCount();
        notifyItemChanged(position + headerCount);
        return true;
    }

    public boolean updateItem(T t) {
        if (t == null) return false;
        final long key = getIndex(t);
        if (key == NO_INDEX) return false;
        T localItem = wrapMap.get(key);
        if (localItem == null) return false;
        final int position = list.indexOf(localItem);
        if (position == -1) return false;
        final int headerCount = getHeaderCount();
        wrapMap.put(key, t);
        list.set(position, t);
        notifyItemChanged(position + headerCount);
        return true;
    }

    public boolean updateItemByIndex(long id) {
        T localItem = wrapMap.get(id);
        if (localItem == null) return false;
        final int position = list.indexOf(localItem);
        if (position == -1) return false;
        final int headerCount = getHeaderCount();
        notifyItemChanged(position + headerCount);
        return true;
    }

    public void removeItemByIndex(long key) {
        T localItem = wrapMap.get(id);
        if (localItem == null) return;
        final int position = list.indexOf(localItem);
        if (position == -1) return;
        final int headerCount = getHeaderCount();
        wrapMap.remove(key);
        list.remove(position);
        notifyItemRemoved(position + headerCount);
    }

    public void removeItemByPosition(int position) {
        T t = list.get(position);
        if (t == null) return;
        final long index = getIndex(t);
        if (index == NO_INDEX) return;
        wrapMap.remove(index);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(T t) {
        removeItem(t, false);
    }

    public void removeItem(T t, boolean notifyAll) {
        if (t == null) return;
        long key = getIndex(t);
        if (key == NO_INDEX) return;
        T localItem = wrapMap.get(key);
        if (localItem == null) return;
        int position = list.indexOf(localItem);
        if (position == -1) return;
        final int viewPosition = position + getHeaderCount();
        wrapMap.remove(key);
        list.remove(position);
        if (notifyAll || list.size() == 0) {
            notifyDataSetChanged();
        } else {
            notifyItemRemoved(viewPosition);
        }
    }

    public void removeItemNotifySelf(T t) {
        if (t == null) return;
        long key = getIndex(t);
        if (key == NO_INDEX) return;
        T localItem = wrapMap.get(key);
        if (localItem == null) return;
        int position = list.indexOf(localItem);
        if (position == -1) return;
        final int viewPosition = position + getHeaderCount();
        wrapMap.remove(key);
        list.remove(position);
        notifyItemChanged(viewPosition);
    }

    public int getRealPosition(T t) {
        if (t == null) return RecyclerView.NO_POSITION;
        final long key = getIndex(t);
        if (key == NO_INDEX) return RecyclerView.NO_POSITION;
        T localItem = wrapMap.get(key);
        if (localItem == null) return RecyclerView.NO_POSITION;
        return list.indexOf(localItem);
    }

    public void clearList() {
        if (getItemCount() > 0) {
            list.clear();
            notifyDataSetChanged();
            wrapMap.clear();
        }
    }

    public void resetList() {
        if (getItemCount() > 0) {
            list = new ArrayList<>();
            notifyDataSetChanged();
            wrapMap.clear();
        }
    }

    public ArrayList<T> getArrayList() {
        if (list != null)
            return new ArrayList<>(list);
        return null;
    }

    public long getIndex(T t) {
        return NO_INDEX;
    }

    public int getHeaderCount() {
        return 0;
    }

}