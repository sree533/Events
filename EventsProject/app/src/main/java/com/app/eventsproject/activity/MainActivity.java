package com.app.eventsproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.eventsproject.R;
import com.app.eventsproject.adapter.EventsAdapter;
import com.app.eventsproject.restapi.api.BaseURL;
import com.app.eventsproject.restapi.api.EventsAPI;
import com.app.eventsproject.restapi.model.Event;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.eventsRecycler)
    RecyclerView eventsRecycler;

    private EventsAPI eventsAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Since the given requirement is list view, setting a linear layout manager.
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //To add divider to recycler view row items
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        eventsRecycler.addItemDecoration(dividerItemDecoration);
        eventsRecycler.setLayoutManager(linearLayoutManager);

        eventsAPI = BaseURL.getAPI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareEventsCall();
    }

    /**
     * Method to make events web service call
     */
    private void prepareEventsCall() {
        Call<List<Event>> call = eventsAPI.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    setUI(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Method to set data to the UI
     */
    private void setUI(List<Event> eventList) {
        final EventsAdapter adapter = new EventsAdapter(this, eventList);
        eventsRecycler.setAdapter(adapter);
    }
}
