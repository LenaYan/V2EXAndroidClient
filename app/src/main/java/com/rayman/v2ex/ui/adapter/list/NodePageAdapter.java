package com.rayman.v2ex.ui.adapter.list;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rayman.v2ex.databinding.ListCellTopicBinding;
import com.rayman.v2ex.databinding.ListHeaderNodeBinding;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.adapter.list.base.BaseHeaderAdapter;
import com.rayman.v2ex.ui.adapter.list.base.CellVM;
import com.rayman.v2ex.ui.view.common.ITopicCellView;
import com.rayman.v2ex.viewmodel.topic.TopicCellVM;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/7/16
 * Time: 11:28 AM
 * \ ----------------------------------------
 * \| A small leak will sink a great ship.!  |
 * \ ----------------------------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class NodePageAdapter extends BaseHeaderAdapter<TopicEntity> {

    private static final int VIEW_HEADER = 0;
    private static final int VIEW_CELL = 1;
    private static final int HEADER_COUNINT = 1;

    private NodeEntity nodeEntity;
    private final ITopicCellView topicCellView;

    public NodePageAdapter(ITopicCellView topicCellView) {
        this.topicCellView = topicCellView;
    }

    public void setNodeEntity(NodeEntity nodeEntity) {
        this.nodeEntity = nodeEntity;
        notifyItemChanged(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        }
        return VIEW_CELL;
    }

    @Override
    protected int getHeaderCount() {
        return HEADER_COUNINT;
    }

    @Override
    protected ViewDataBinding createBinding(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_HEADER:
                return ListHeaderNodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            default:
            case VIEW_CELL:
                return ListCellTopicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        }
    }

    @Override
    protected Object createViewModel(int position) {
        switch (getItemViewType(position)) {
            case VIEW_HEADER:
                return new CellVM<>(nodeEntity);
            default:
            case VIEW_CELL:
                return new TopicCellVM(getItem(position), topicCellView);
        }
    }

}