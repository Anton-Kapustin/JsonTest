package com.dev.toxa.jsontest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentContacts extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        String contacts = "Email: kapustinanton2@gmail.com, \nTel: 89087863047 \n";
        TextView textView_contacts = (TextView) rootView.findViewById(R.id.textView_contacts);
        textView_contacts.setText(contacts);
        return rootView;
    }

}
