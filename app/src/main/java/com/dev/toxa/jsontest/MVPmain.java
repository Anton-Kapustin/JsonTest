package com.dev.toxa.jsontest;

import org.json.JSONObject;

public interface MVPmain {

    interface view {

        String getPostId();
        String getCommentsId();
        String getUsersId();
        String getPhotosId();
        String getTodosId();

        void setPost(JSONObject jsonObject);
        void setComments(JSONObject jsonObject);
        void setUsers(JSONObject jsonObject);
        void setPhotos(JSONObject jsonObject);
        void setTodos(JSONObject jsonObject);

        void appendUsers(JSONObject jsonObject);
        void appendPhotos(JSONObject jsonObject);
        void appendTodos(JSONObject jsonObject);

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
