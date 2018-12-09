package lightcube.android_demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lightcube.android_demo.R;
import lightcube.android_demo.model.ContactModel;

/**
 * Created by Dell on 05-12-2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder>
        {
            private Context mContext;
            private List<ContactModel>mList;

            public ContactsAdapter(List<ContactModel> mList)
            {
//                this.mContext = mContext;
                this.mList = mList;
            }


            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_details,parent,false);
                MyViewHolder myViewHolder = new MyViewHolder(view);
                return myViewHolder;
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, int position)
            {
                holder.name.setText(mList.get(position).getName());
                holder.number.setText(mList.get(position).getNumber());
            }

            @Override
            public int getItemCount() {
                return mList.size();
            }

            public class MyViewHolder extends RecyclerView.ViewHolder {
                private TextView name,number;

                public MyViewHolder(View itemView) {
                    super(itemView);
                    name = (TextView)itemView.findViewById(R.id.name);
                    number = (TextView)itemView.findViewById(R.id.number);
                }
            }
        }
