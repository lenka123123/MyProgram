package com.wokun.tysl.asset.ui.activity;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Administrator on 2018\11\29 0029.
 */

public abstract class  GuardedAsyncTask <Params, Progress>
        extends AsyncTask<Params, Progress, Void> {
    private final Context mReactContext;

    protected GuardedAsyncTask(Context reactContext) {
        mReactContext = reactContext;
    }

    @Override
    protected final Void doInBackground(Params... params) {
        try {
            doInBackgroundGuarded(params);
        } catch (RuntimeException e) {
        }
        return null;
    }

    protected abstract void doInBackgroundGuarded(Params... params);

}
