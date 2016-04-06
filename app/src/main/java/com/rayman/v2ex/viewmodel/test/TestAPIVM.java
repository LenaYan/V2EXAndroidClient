package com.rayman.v2ex.viewmodel.test;

import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.model.http.Host;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.model.model.test.TestApiEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.adapter.list.TestApiListAdapter;
import com.rayman.v2ex.ui.view.test.api.TestApiContract;
import com.rayman.v2ex.viewmodel.BaseVM;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 2:39 PM
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
public class TestApiVM extends BaseVM<TestApiContract.Presenter, TestApiContract.View> {

    private TestApiListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public TestApiVM(TestApiContract.Presenter presenter, TestApiContract.View view, RecyclerView.LayoutManager layoutManager) {
        super(presenter, view);
        this.layoutManager = layoutManager;
        adapter = new TestApiListAdapter(this::onItemClick);
        initData();
    }

    public void onItemClick(final int position, final TestApiEntity testApiEntity) {
        switch (position) {
            case 0:
                presenter.requestHotList(getTopicListCallback(position, testApiEntity));
                break;
            case 1:
                presenter.requestLastestList(getTopicListCallback(position, testApiEntity));
                break;
            case 2:
                presenter.requestTopicListByName("Livid", getTopicListCallback(position, testApiEntity));
                break;
            case 3:
                presenter.requestTopicListByNodeId(1, getTopicListCallback(position, testApiEntity));
                break;
            case 4:
                presenter.requestTopicListByNodeName("python", getTopicListCallback(position, testApiEntity));
                break;
            case 5:
                presenter.requestTopicById(268570, new Subscriber<List<TopicEntity>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        testApiEntity.setState(TestApiEntity.LOADING);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onNext(List<TopicEntity> respEntity) {
                        testApiEntity.setState(TestApiEntity.SUCCESS);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable errorEvent) {
                        testApiEntity.setState(TestApiEntity.ERROR);
                        adapter.notifyItemChanged(position);
                    }
                });
                break;
            case 6:
                presenter.requestReplies(265124, new Subscriber<List<ReplyEntity>>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        testApiEntity.setState(TestApiEntity.LOADING);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onNext(List<ReplyEntity> respEntity) {
                        testApiEntity.setState(TestApiEntity.SUCCESS);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable errorEvent) {
                        testApiEntity.setState(TestApiEntity.ERROR);
                        adapter.notifyItemChanged(position);
                    }
                });
                break;
            case 7:
                presenter.requestNode("python", new Subscriber<NodeEntity>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        testApiEntity.setState(TestApiEntity.LOADING);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onNext(NodeEntity respEntity) {
                        testApiEntity.setState(TestApiEntity.SUCCESS);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable errorEvent) {
                        testApiEntity.setState(TestApiEntity.ERROR);
                        adapter.notifyItemChanged(position);
                    }
                });
                break;
            case 8:
                presenter.requestNodelist(new Subscriber<List<NodeEntity>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        testApiEntity.setState(TestApiEntity.LOADING);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onNext(List<NodeEntity> response) {
                        testApiEntity.setState(TestApiEntity.SUCCESS);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable errorEvent) {
                        testApiEntity.setState(TestApiEntity.ERROR);
                        adapter.notifyItemChanged(position);
                    }
                });
                break;

            case 9:
                presenter.requestMember("linpf", new Subscriber<MemberEntity>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        testApiEntity.setState(TestApiEntity.LOADING);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onNext(MemberEntity response) {
                        testApiEntity.setState(TestApiEntity.SUCCESS);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable errorEvent) {
                        testApiEntity.setState(TestApiEntity.ERROR);
                        adapter.notifyItemChanged(position);
                    }
                });
                break;
            default:
                break;
        }
    }

    private Subscriber<List<TopicEntity>> getTopicListCallback(final int position, final TestApiEntity testApiEntity) {
        return new Subscriber<List<TopicEntity>>() {
            @Override
            public void onStart() {
                super.onStart();
                testApiEntity.setState(TestApiEntity.LOADING);
                adapter.notifyItemChanged(position);
            }

            @Override
            public void onNext(List<TopicEntity> respEntity) {
                testApiEntity.setState(TestApiEntity.SUCCESS);
                adapter.notifyItemChanged(position);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable errorEvent) {
                testApiEntity.setState(TestApiEntity.ERROR);
                adapter.notifyItemChanged(position);
            }
        };
    }

    private void initData() {
        TestApiEntity hotApi = new TestApiEntity(Host.BASE_URL + "topics/hot.json");
        TestApiEntity latestedApi = new TestApiEntity(Host.BASE_URL + "topics/latest.json");
        TestApiEntity topicsByNameApi = new TestApiEntity(Host.BASE_URL + "topics/show.json?username=Livid");
        TestApiEntity topicListByNodeIdApi = new TestApiEntity(Host.BASE_URL + "topics/show.json?node_id=1");
        TestApiEntity topicListByNodeNameApi = new TestApiEntity(Host.BASE_URL + "topics/show.json?node_name=python");
        TestApiEntity topicByIdApi = new TestApiEntity(Host.BASE_URL + "topics/show.json?id=265124");

        TestApiEntity repliesApi = new TestApiEntity(Host.BASE_URL + "replies/show.json?id=265124");

        TestApiEntity nodeByNamApi = new TestApiEntity(Host.BASE_URL + "nodes/show.json?name=python");
        TestApiEntity nodeListApi = new TestApiEntity(Host.BASE_URL + "nodes/all.json");

        TestApiEntity member = new TestApiEntity(Host.BASE_URL + "members/show.json?username=linpf");

        List<TestApiEntity> apiEntities = new ArrayList<>();
        apiEntities.add(hotApi);
        apiEntities.add(latestedApi);
        apiEntities.add(topicsByNameApi);
        apiEntities.add(topicListByNodeIdApi);
        apiEntities.add(topicListByNodeNameApi);
        apiEntities.add(topicByIdApi);

        apiEntities.add(repliesApi);

        apiEntities.add(nodeByNamApi);
        apiEntities.add(nodeListApi);

        apiEntities.add(member);

        adapter.setList(apiEntities);
    }

    public TestApiListAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

}