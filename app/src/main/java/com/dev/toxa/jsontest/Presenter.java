package com.dev.toxa.jsontest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Presenter implements MVPmain.presenter {

    String LOG_TAG = "Presenter: ";

    private final MVPmain.view view;
    String url = "https://jsonplaceholder.typicode.com/";

    public Presenter(MVPmain.view view) {
        this.view = view;
    }

    void postsUrl() {
        int postId = Integer.parseInt(view.getPostId());
        if ((postId <= 100) && (postId >0)) {
            String postUrl = url + "posts/" + String.valueOf(postId);
            sendRequest(postUrl);
        }
    }

    void commentsUrl() {
        int commentId = Integer.parseInt(view.getCommentsId());
        if ((commentId <= 500) && (commentId > 0)) {
            String commentsUrl = url + "comments/" + String.valueOf(commentId);
            sendRequest(commentsUrl);
        }
    }

    void usersUrl() {
        int userId = Integer.parseInt(view.getUsersId());
        if ((userId <= 10) && (userId > 0)) {
            String usersUrl = url + "users/" + String.valueOf(userId);
            sendRequest(usersUrl);
        }
    }

    void usersUrl(String num) {
        for (int i = 1; i <= 5; i++) {
            String userUrl = url + "users/" + i;
            firstRunRequest(userUrl);
        }
    }

    void photosUrl() {
        Log.d(LOG_TAG, "run photosUrl");
        int photoId = Integer.parseInt(view.getPhotosId());
        if ((photoId <= 5000) && (photoId > 0)) {
            final String photoUrl = url + "photos/" + String.valueOf(photoId);
            sendRequest(photoUrl);
        }
    }

    void photosUrl(String num) {
        Log.d(LOG_TAG, "run photosUrl with arg");
        final String photoUrl = url + "photos/" + num;
        firstRunRequest(photoUrl);
    }

    void todosUrl() {
        int todoId = Integer.parseInt(view.getTodosId());
        if ((todoId <= 200) && (todoId > 0)) {
            String todosUrl = url + "todos/" + String.valueOf(todoId);
            sendRequest(todosUrl);
        }
    }

    void todosUrl(String num) {
        String todoUrl = url + "todos/" + num;
        firstRunRequest(todoUrl);
    }

    void firstRunRequest(String url) {
        Log.d(LOG_TAG, "url: " + url);
        AsyncActivityStartRequest asyncActivityStartRequest = new AsyncActivityStartRequest();
        asyncActivityStartRequest.execute(url);
    }

    void sendRequest(String url) {
        Log.d(LOG_TAG, "url: " + url);
        AsyncHttpRequest AsyncHttpRequest = new AsyncHttpRequest();
        AsyncHttpRequest.execute(url);
    }

    int randomId(int limit) {
        Random rnd = new Random(System.currentTimeMillis());
        int number = rnd.nextInt(limit);
        return number;
    }

    @Override
    public void activityLoaded() {
        photosUrl(String.valueOf(3));
        usersUrl(String.valueOf(5));
        todosUrl(String.valueOf(randomId(201)));
    }

    @Override
    public void button_posts_clicked() {
        postsUrl();
    }

    @Override
    public void button_comments_clicked() {
        commentsUrl();
    }

    @Override
    public void button_users_clicked() {
        usersUrl();
    }

    @Override
    public void button_photos_clicked() {
        photosUrl();
    }

    @Override
    public void button_todos_clicked() {
        todosUrl();
    }

    JSONObject request(URL url) {
        JSONObject jsonObject = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[16384];
            int count;
            while ((count = urlConnection.getInputStream().read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, count);
            }
            jsonObject = new JSONObject(byteArrayOutputStream.toString());
            Log.d(LOG_TAG, "json receive: " + jsonObject.toString());
            byteArrayOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return jsonObject;
    }

    Bitmap loadImage(String url) {
        Bitmap bitmapImage = null;
        URL imageUrl = null;
        HttpURLConnection httpURLConnection = null;
        try {
            imageUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) imageUrl.openConnection();
            httpURLConnection.setReadTimeout(5000);
            bitmapImage = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
            Log.d(LOG_TAG, "Runneble: " + "OK");
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "image url error: " + e);
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
            return bitmapImage;
        }

    class AsyncHttpRequest extends AsyncTask<String, Void, JSONObject> {
        String LOG_TAG = "httpRequest: ";
        String param = null;
        Bitmap bitmapImage;

        @Override
        protected JSONObject doInBackground(String... strings) {
            String request = "";
            JSONObject jsonObject = null;
            for (String address : strings) {
                param = address;
                URL url = null;
                try {
                    url = new URL(address);
                } catch (MalformedURLException e) {
                    Log.e(LOG_TAG, "url error: " + e);
                    e.printStackTrace();
                }
                jsonObject = request(url);
            }

            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            Log.d(LOG_TAG, "result: " + result);
            if (param.contains("posts")) {
                view.setPost(result);
            } else if (param.contains("comments")) {
                view.setComments(result);
            } else if (param.contains("users")) {
                view.setUsers(result);
            } else if (param.contains("photos")) {
                AsyncLoadImage asyncLoadImage = new AsyncLoadImage();
                try {
                    asyncLoadImage.execute(result.getString("url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (param.contains("todos")) {
                view.setTodos(result);
            }


        }

    }

    class AsyncActivityStartRequest extends AsyncTask<String, Void, JSONObject> {
        String LOG_TAG = "ActivityStartRequest: ";
        String param = null;

        @Override
        protected JSONObject doInBackground(String... strings) {
            String request = "";
            JSONObject jsonObject = null;
            for (String address : strings) {
                param = address;
                URL url = null;

                try {
                    url = new URL(address);
                } catch (MalformedURLException e) {
                    Log.e(LOG_TAG, "url error: " + e);
                    e.printStackTrace();
                }
                jsonObject = request(url);

            }
            Log.d(LOG_TAG, "read: " + jsonObject.toString());
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            Log.d(LOG_TAG, "result: " + result);
            if (param.contains("users")) {
                view.appendUsers(result);
            } else if (param.contains("todos")) {
                view.appendTodos(result);
            } else if (param.contains("photos")) {
                AsyncLoadImage asyncLoadImage = new AsyncLoadImage();
                try {
                    asyncLoadImage.execute(result.getString("url"));
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "image url from json error: " + e);
                    e.printStackTrace();
                }

            }
        }
    }

    class AsyncLoadImage extends AsyncTask<String, Void, Bitmap> {
        String LOG_TAG = "AsyncLoadImage: ";
        String param = null;
        Bitmap bitmapImage;

        @Override
        protected Bitmap doInBackground(String... strings) {
            String request = "";
            final URL imageUrl = null;
            JSONObject jsonObject = null;
            for (final String address : strings) {
                param = address;
                Log.d(LOG_TAG, "img url: " + address);
                bitmapImage = loadImage(address);
            }
            return bitmapImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmapImg) {
                view.setPhotos(bitmapImg);
        }

    }
}


