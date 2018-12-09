package lightcube.android_demo;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Presentation;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lightcube.android_demo.adapter.ContactsAdapter;
import lightcube.android_demo.model.ContactModel;
import lightcube.android_demo.presenter.GetDataContract;
import lightcube.android_demo.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements GetDataContract.View {

    public static final int PERMISSION_REQUEST_CONTACT = 79;
    private Presenter mPresenter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ContactsAdapter mContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askForContactPermission();

    }

    public void getContactData()
    {
        mPresenter = new Presenter(this);
        mPresenter.getContactData(getApplicationContext());

    }


    public void askForContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Contacts access needed");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("please confirm Contacts access");//TODO put real question
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {Manifest.permission.READ_CONTACTS}
                                    , PERMISSION_REQUEST_CONTACT);
                        }
                    });
                    builder.show();
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            PERMISSION_REQUEST_CONTACT);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                getContactData();
            }
        } else {
            getContactData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContactData();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this, "No permission for contacts", Toast.LENGTH_LONG).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    @Override
    public void onGetDataSuccess(String message, List<ContactModel> list) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_contacts);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mContactAdapter = new ContactsAdapter(list);
        recyclerView.setAdapter(mContactAdapter);
    }

    @Override
    public void onGetDataFailure(String message) {

        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
    }
}
