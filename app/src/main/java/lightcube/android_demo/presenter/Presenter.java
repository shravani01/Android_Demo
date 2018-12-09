package lightcube.android_demo.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import lightcube.android_demo.model.ContactModel;

/**
 * Created by Dell on 05-12-2018.
 */

public class Presenter implements GetDataContract.Presenter,GetDataContract.onGetDataListener
{
    Context mContext;
    Intractor mIntractor;
    GetDataContract.View mView;

    public Presenter(GetDataContract.View mView)
    {
        mIntractor=new Intractor(this);
        this.mView=mView;
    }

    @Override
    public void getContactData(Context context) {
        mIntractor.initDataCall(context);
    }


    @Override
    public void onSuccess(String message, ArrayList<ContactModel> list) {
        mView.onGetDataSuccess("success",list);
    }

    @Override
    public void onFailure(String message) {
        mView.onGetDataFailure("failure");
    }
}
