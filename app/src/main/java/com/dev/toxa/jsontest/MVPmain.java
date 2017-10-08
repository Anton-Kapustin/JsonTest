package com.dev.toxa.jsontest;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import org.json.JSONObject;

public interface MVPmain {

    interface view {

        String getPostId();
        String getCommentsId();
        String getUsersId();
        String getPhotosId();
        String getTodosId();

        void setPost(String data);
        void setComments(String data);
        void setUsers(String data);
        void setTodos(String data);
        void setPhotos(Bitmap image);

        void appendUsers(String data);

        void errorPhoto();

    }

    interface presenter {

        void activityLoaded();

        void button_posts_clicked();
        void button_comments_clicked();
        void button_users_clicked();
        void button_photos_clicked();
        void button_todos_clicked();
    }
}
