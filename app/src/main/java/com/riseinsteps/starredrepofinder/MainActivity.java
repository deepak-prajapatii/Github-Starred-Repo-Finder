package com.riseinsteps.starredrepofinder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<User> userList;
    private GithubApi githubApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText userName = findViewById(R.id.edit_text_username);

        Button btn = findViewById(R.id.button_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResponse(userName.getText().toString());
            }
        });
    }

    private void getResponse(String userName) {
        GithubApi githubApi = GithubApi.getInstance();
        Call<List<User>> user = githubApi.getStarredRepo(userName);

        user.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Something went wrong in getting Response", Toast.LENGTH_SHORT).show();
                    return;
                }

                ListView listView = findViewById(R.id.list_view_repos);
                List<User> users = response.body();

                Adapter adapter = new Adapter(users, MainActivity.this);

                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}