package lightcube.android_demo.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import lightcube.android_demo.model.ContactModel;

/**
 * Created by Dell on 05-12-2018.
 */

public interface GetDataContract
{
    interface View
    {
        void onGetDataSuccess(String message,List<ContactModel>list);
        void onGetDataFailure(String message);
    }

    interface Presenter
    {
        void getContactData(Context context);
    }

    interface Interactor
    {
        void initDataCall(Context context);
    }

    interface onGetDataListener
    {
        void onSuccess(String message,ArrayList<ContactModel> list);
        void onFailure(String message);
    }


}
