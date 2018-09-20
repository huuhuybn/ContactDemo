package vn.edu.poly.contactdemo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.edu.poly.contactdemo.R;

public class ContactHolder extends RecyclerView.ViewHolder {
    public final TextView tvId;
    public final TextView tvName;

    public ContactHolder(View convertView) {
        super(convertView);

        tvId = convertView.findViewById(R.id.tvId);
        tvName = convertView.findViewById(R.id.tvName);


    }
}
