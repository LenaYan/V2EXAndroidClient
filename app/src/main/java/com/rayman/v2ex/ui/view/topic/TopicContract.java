package com.rayman.v2ex.ui.view.topic;

import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.base.comp.ActivityComp;
import com.rayman.v2ex.viewmodel.topic.TopicVMModule;

import java.util.List;

import dagger.Component;
import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/5/16
 * Time: 3:25 PM
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
public interface TopicContract {

    @PerActivity
    @Component(modules = {TopicVMModule.class}, dependencies = ActivityComp.class)
    interface TopicComp extends ActivityComp {

        void inject(TopicActivity topicActivity);

    }

    interface View {
    }

    interface Preenter {
        void requestDetail(long id, Subscriber<List<TopicEntity>> subscriber);

        void requestReplies(long id, Subscriber<List<ReplyEntity>> subscriber);
    }

}
