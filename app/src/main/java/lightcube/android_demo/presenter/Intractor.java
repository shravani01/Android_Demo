package lightcube.android_demo.presenter;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

import lightcube.android_demo.model.ContactModel;

/**
 * Created by Dell on 05-12-2018.
 */

public class Intractor implements GetDataContract.Interactor
{
     GetDataContract.onGetDataListener mOnGetDataListener;
     ArrayList<ContactModel> mContactModelArrayList = new ArrayList<>();

    public Intractor(GetDataContract.onGetDataListener mOnGetDataListener)
    {
        this.mOnGetDataListener=mOnGetDataListener;
    }

    @Override
    public void initDataCall(Context context)
    {
        Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            ContactModel contactModel = new ContactModel();
            contactModel.setName(name);
            contactModel.setNumber(phoneNumber);
            Log.e(context.getClass().getName(),name+"  "+phoneNumber);
            mContactModelArrayList.add(contactModel);

            if(mContactModelArrayList!=null && mContactModelArrayList.size()>0)
            {
                mOnGetDataListener.onSuccess("success",mContactModelArrayList);
            }
            else
            {
                mOnGetDataListener.onFailure("failure");
            }
        }
        phones.close();
    }
}
