package com.dev.toxa.jsontest;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by toxa on 04.10.17.
 */
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
        for (int i = 0; i <= 5; i++) {
            String usersUrl = url + "users/" + num;
            sendRequest(usersUrl);
        }
    }

    void photosUrl() {
        int photoId = Integer.parseInt(view.getPhotosId());
        if ((photoId <= 5000) && (photoId > 0)) {
            String photosUrl = url + "photos/" + String.valueOf(photoId);
            sendRequest(photosUrl);
        }
    }

    void photosUrl(String num) {
        String usersUrl = url + "photos/" + num;
        sendRequest(usersUrl);
    }

    void todosUrl() {
        int todoId = Integer.parseInt(view.getTodosId());
        if ((todoId <= 200) && (todoId > 0)) {
            String todosUrl = url + "todos/" + String.valueOf(todoId);
            sendRequest(todosUrl);
        }
    }

    void todosUrl(String num) {
        String usersUrl = url + "todos/" + num;
        sendRequest(usersUrl);
    }

    void firstRunRequest(String url) {
        Log.d(LOG_TAG, "url: " + url);
        FirstRequest firstRequest = new FirstRequest();
        firstRequest.execute(url);
    }

    void sendRequest(String url) {
        Log.d(LOG_TAG, "url: " + url);
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.execute(url);
    }

    int randomId(int limit) {
        Random rnd = new Random(System.currentTimeMillis());
        int number = rnd.nextInt(limit);
        return number;
    }

    @Override
    public void activityLoaded() {
        usersUrl(String.valueOf(5));
        photosUrl(String.valueOf(3));
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

    class HttpRequest extends AsyncTask<String, Void, JSONObject> {
        String LOG_TAG = "httpRequest: ";
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
//                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//                    while (in.readLine() != null) {
//                        request += in.readLine();
//                        Log.d(LOG_TAG, "input: " + request);
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            }
            Log.d(LOG_TAG, "read: " + jsonObject.toString());
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
                view.setPhotos(result);
            } else if (param.contains("todos")) {
                view.setTodos(result);
            }


        }

    }

    class FirstRequest extends AsyncTask<String, Void, JSONObject> {
        String LOG_TAG = "FirstRequest: ";
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
//                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//                    while (in.readLine() != null) {
//                        request += in.readLine();
//                        Log.d(LOG_TAG, "input: " + request);
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            }
            Log.d(LOG_TAG, "read: " + jsonObject.toString());
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            Log.d(LOG_TAG, "result: " + result);
            if (param.contains("users")) {
                view.appendUsers(result);
            } else if (param.contains("photos")) {
                view.appendPhotos(result);
            } else if (param.contains("todos")) {
                view.appendTodos(result);
            }


        }

    }
}


