package com.dev.toxa.jsontest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentRequests extends Fragment implements MVPmain.view {

    String LOG_TAG = getClass().getName() + ": ";

    TextView posts;
    TextView users;
    TextView comments;
    TextView todos;

    ImageView photos;

    EditText editText_posts;
    EditText editText_comments;
    EditText editText_users;
    EditText editText_photos;
    EditText editText_todos;

    Button button_posts_submit;
    Button button_comments_submit;
    Button button_users_submit;
    Button button_photos_submit;
    Button button_todos_submit;

    Presenter presenter = new Presenter(this);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "OnCreate");
        View rootView = inflater.inflate(R.layout.fragment_requests, container, false);
        posts = (TextView) rootView.findViewById(R.id.textview_posts);
        comments = (TextView) rootView.findViewById(R.id.textview_comments);
        users = (TextView) rootView.findViewById(R.id.textview_users);
        photos = (ImageView) rootView.findViewById(R.id.imageView_photos);
        todos = (TextView) rootView.findViewById(R.id.textview_todos);

        editText_posts = (EditText) rootView.findViewById(R.id.editText_posts);
        editText_comments = (EditText) rootView.findViewById(R.id.editText_comments);
        editText_users = (EditText) rootView.findViewById(R.id.editText_users);
        editText_photos = (EditText) rootView.findViewById(R.id.editText_photos);
        editText_todos = (EditText) rootView.findViewById(R.id.editText_todos);

        button_posts_submit = (Button) rootView.findViewById(R.id.button_posts_submit);
        button_comments_submit = (Button) rootView.findViewById(R.id.button_comments_submit);
        button_users_submit = (Button) rootView.findViewById(R.id.button_users_submit);
        button_photos_submit = (Button) rootView.findViewById(R.id.button_photos_submit);
        button_todos_submit = (Button) rootView.findViewById(R.id.button_todos_submit);

        button_posts_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.button_posts_clicked();
            }
        });
        button_users_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.button_users_clicked();
            }
        });
        button_comments_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.button_comments_clicked();
            }
        });
        button_photos_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.button_photos_clicked();
            }
        });
        button_todos_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.button_todos_clicked();
            }
        });

        presenter.activityLoaded();
        return rootView;
    }

    @Override
    public String getPostId(){
        return editText_posts.getText().toString();
    }
    @Override
    public String getCommentsId(){
        return editText_comments.getText().toString();
    }
    @Override
    public String getUsersId(){
        return editText_users.getText().toString();
    }
    @Override
    public String getPhotosId(){
        return editText_photos.getText().toString();
    }
    @Override
    public String getTodosId(){
        return editText_todos.getText().toString();
    }

    @Override
    public void setPost(String data) {
        posts.setText((CharSequence) data.toString());
    }
    @Override
    public void setComments(String data) {
        comments.setText((CharSequence) data.toString());
    }
    @Override
    public void setUsers(String data) {
        users.setText((CharSequence) data.toString());
    }

    @Override
    public void setPhotos(Bitmap image) {
        photos.setImageBitmap(image);
    }
    @Override
    public void setTodos(String data) {
        todos.setText((CharSequence) data.toString());
    }

    @Override
    public void appendUsers(String data) {
        users.append(data);
    }

    @Override
    public void errorPhoto() {
        photos.setImageResource(R.drawable.ic_block_black_24dp);
    }
}
