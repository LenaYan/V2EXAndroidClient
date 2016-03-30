package com.rayman.v2ex.presenter.test;

import com.rayman.v2ex.presenter.BasePresenter;
import com.squareup.leakcanary.RefWatcher;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/30/16
 * Time: 2:25 PM
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
public class TestRxP extends BasePresenter implements ITestRxP {

    @Inject
    public TestRxP(RefWatcher refWatcher) {
        super(refWatcher);
    }

    @Override
    public void testThread() {
        subscribe(
                Observable
                        .create(subscriber1 -> {
                                    Timber.i("Begin Main Thread %d", Thread.currentThread().getId());
                                    Observable
                                            .create((subscriber) -> {
                                                Timber.i("First %d", Thread.currentThread().getId());
                                                subscriber.onNext("First");
                                            })
//                                    .doOnSubscribe(() -> Timber.i("doOnSubscribe %d", Thread.currentThread().getId()))
                                            .observeOn(Schedulers.newThread())
                                            .map((s) -> {
                                                Timber.i("Second %d", Thread.currentThread().getId());
                                                return s + " Second";
                                            })
                                            .subscribeOn(Schedulers.newThread())
                                            .map((s) -> {
                                                Timber.i("Third %d", Thread.currentThread().getId());
                                                return s + " Third";
                                            })
                                            .observeOn(Schedulers.newThread())
                                            .map((s) -> {
                                                Timber.i("Fourth %d", Thread.currentThread().getId());
                                                return s + " Fourth";
                                            })
                                            .observeOn(Schedulers.newThread())
                                            .subscribe(new Subscriber<String>() {
                                                @Override
                                                public void onStart() {
                                                    super.onStart();
                                                    Timber.i("onStart %d", Thread.currentThread().getId());
                                                }

                                                @Override
                                                public void onCompleted() {
                                                    Timber.i("onCompleted %d", Thread.currentThread().getId());
                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    Timber.i("onError %d", Thread.currentThread().getId());
                                                }

                                                @Override
                                                public void onNext(String s) {
                                                    Timber.i("onNext %d", Thread.currentThread().getId());
                                                    Timber.i("onNext %s", s);
                                                }
                                            });
                                }
                        )
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.newThread())
                        .subscribe((s) -> Timber.i("End"))
        );
    }

    @Override
    public void testEmpty() {
        subscribe(
                Observable
                        .empty()
                        .subscribe(new Subscriber<Object>() {
                            @Override
                            public void onCompleted() {
                                Timber.i("testEmpty onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.i("testEmpty onError");
                            }

                            @Override
                            public void onNext(Object o) {
                                Timber.i("testEmpty onNext");
                            }
                        })
        );
    }

    @Override
    public void testNever() {
        subscribe(
                Observable
                        .never()
                        .subscribe(new Subscriber<Object>() {
                            @Override
                            public void onCompleted() {
                                Timber.i("testNever onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.i("testNever onError");
                            }

                            @Override
                            public void onNext(Object o) {
                                Timber.i("testNever onNext");
                            }
                        })
        );
    }

    @Override
    public void testFrom() {
        String[] strings = {"Test1", "Test2"};
        subscribe(
                Observable
                        .from(strings)
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                Timber.i("testFrom onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.i("testFrom onError");
                            }

                            @Override
                            public void onNext(String s) {
                                Timber.i("testFrom onNext %s", s);
                            }
                        })
        );
    }

    @Override
    public void testFromFuture() {
        subscribe(
                Observable
                        .from(new Future<String>() {
                            @Override
                            public boolean cancel(boolean mayInterruptIfRunning) {
                                Timber.i("testFromFuture cancel");
                                return false;
                            }

                            @Override
                            public boolean isCancelled() {
                                Timber.i("testFromFuture isCancelled");
                                return false;
                            }

                            @Override
                            public boolean isDone() {
                                Timber.i("testFromFuture isDone");
                                return false;
                            }

                            @Override
                            public String get() throws InterruptedException, ExecutionException {
                                Timber.i("testFromFuture get1");
                                return "Get1";
                            }

                            @Override
                            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                                Timber.i("testFromFuture get2");
                                return "Get2";
                            }
                        })
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                Timber.i("testFromFuture onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.i("testFromFuture onError");
                            }

                            @Override
                            public void onNext(String s) {
                                Timber.i("testFromFuture onNext %s", s);
                            }
                        })
        );
    }

    @Override
    public void testInterval() {
        subscribe(
                Observable
                        .interval(10, 5, TimeUnit.SECONDS)
                        .doOnUnsubscribe(() -> Timber.i("testInterval doOnUnsubscribe"))
                        .subscribe(new Subscriber<Long>() {
                            @Override
                            public void onCompleted() {
                                Timber.i("testInterval onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.i("testInterval onError");
                            }

                            @Override
                            public void onNext(Long aLong) {
                                Timber.i("testInterval %d", aLong);
                            }
                        })
        );
    }

    @Override
    public void testJust() {
        subscribe(
                Observable
                        .just("Test1", "Test2", "Test3")
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                Timber.i("testJust onComplete");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.i("testJust onError");
                            }

                            @Override
                            public void onNext(String s) {
                                Timber.i("testJust onNext %s", s);
                            }
                        })
        );
    }

    @Override
    public void testRange() {
        subscribe(
                Observable
                        .range(3, 6)
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                Timber.i("testRange onComplete");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.i("testRange onError");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                Timber.i("testRange onNext %d", integer);
                            }
                        })
        );
    }

    @Override
    public void testRepeat() {
        subscribe(
                Observable
                        .create((Subscriber<? super String> subscriber) -> {
                            Timber.i("testRepeat Call");
                            subscriber.onNext("testRepeat Call");
                        })
                        .repeat(3)
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                Timber.i("testRepeat onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.i("testRepeat onError");
                            }

                            @Override
                            public void onNext(String s) {
                                Timber.i("testRepeat onNext %s", s);
                            }
                        })
        );
    }

    @Override
    public void testStartWith() {
        subscribe(
                Observable
                        .create((Subscriber<? super String> subscriber) -> {
                            Timber.i("testStartWith call");
                            subscriber.onNext("call");
                        })
                        .startWith(Observable.create((Subscriber<? super String> subscriber) -> {
                            Timber.i("testStartWith startWithcall");
                            subscriber.onNext("startWithcall");
                        }))
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                Timber.i("testStartWith onComplete");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.i("testStartWith onError");
                            }

                            @Override
                            public void onNext(String s) {
                                Timber.i("testStartWith onNext %s", s);
                            }
                        })
        );
    }
}
