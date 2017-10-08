package com.dev.toxa.jsontest;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements MVPmain.view {

    String LOG_TAG = "MainActivity: ";

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts = (TextView) findViewById(R.id.textview_posts);
        comments = (TextView) findViewById(R.id.textview_comments);
        users = (TextView) findViewById(R.id.textview_users);
        photos = (ImageView) findViewById(R.id.imageView_photos);
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
