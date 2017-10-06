package com.dev.toxa.jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements MVPmain.view {

    String LOG_TAG = "MainActivity: ";

    TextView posts;
    TextView users;
    TextView comments;
    TextView photos;
    TextView todos;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts = (TextView) findViewById(R.id.textview_posts);
        comments = (TextView) findViewById(R.id.textview_comments);
        users = (TextView) findViewById(R.id.textview_users);
        photos = (TextView) findViewById(R.id.textView_photos);
        todos = (TextView) findViewById(R.id.textview_todos);

        editText_posts = (EditText) findViewById(R.id.editText_posts);
        editText_comments = (EditText) findViewById(R.id.editText_comments);
        editText_users = (EditText) findViewById(R.id.editText_users);
        editText_photos = (EditText) findViewById(R.id.editText_photos);
        editText_todos = (EditText) findViewById(R.id.editText_todos);

        button_posts_submit = (Button) findViewById(R.id.button_posts_submit);
        button_comments_submit = (Button) findViewById(R.id.button_comments_submit);
        button_users_submit = (Button) findViewById(R.id.button_users_submit);
        button_photos_submit = (Button) findViewById(R.id.button_photos_submit);
        button_todos_submit = (Button) findViewById(R.id.button_todos_submit);

        button_posts_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.postsUrl();
            }
        });
        button_users_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.usersUrl();
            }
        });
        button_comments_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.commentsUrl();
            }
        });
        button_photos_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.photosUrl();
            }
        });
        button_todos_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.todosUrl();
            }
        });

        presenter.activityLoaded();

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
    public void setPost(JSONObject jsonObject) {
        posts.setText((CharSequence) jsonObject.toString());
    }
    @Override
    public void setComments(JSONObject jsonObject) {
        comments.setText((CharSequence) jsonObject.toString());
    }
    @Override
    public void setUsers(JSONObject jsonObject) {
        users.setText((CharSequence) jsonObject.toString());
    }
    @Override
    public void setPhotos(JSONObject jsonObject) {
        photos.setText((CharSequence) jsonObject.toString());
    }
    @Override
    public void setTodos(JSONObject jsonObject) {
        todos.setText((CharSequence) jsonObject.toString());
    }

    @Override
    public void appendUsers(JSONObject jsonObject) {
        users.append(jsonObject.toString());
    }
    @Override
    public void appendPhotos(JSONObject jsonObject) {

    }
    @Override
    public void appendTodos(JSONObject jsonObject) {

    }

}
