package com.app.eventsproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eventsproject.R;
import com.app.eventsproject.restapi.model.Actor;
import com.app.eventsproject.restapi.model.Event;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter to bind events data
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsHolder> {

    private List<Event> eventList;
    private Context context;

    public EventsAdapter(Context context, List<Event> eventList) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public EventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new EventsHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsHolder holder, int position) {
        final Event event = eventList.get(position);

        if (event == null) {
            return;
        }
        holder.eventType.setText(String.format(context.getString(R.string.event_type_format), event.getEventType()));
        final Actor actor = event.getActor();
        if (actor == null) {
            return;
        }
        if (!TextUtils.isEmpty(actor.getAvatarUrl())) {
            Picasso.with(context).load(actor.getAvatarUrl()).into(holder.thumbNailImg);
        }
        holder.name.setText(String.format(context.getString(R.string.login_name_format), actor.getLogin()));
    }

    @Override
    public int getItemCount() {
        return (eventList != null && eventList.size() > 0) ? eventList.size() : 0;
    }

    public class EventsHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.thumbNail)
        ImageView thumbNailImg;

        @Bind(R.id.name)
        TextView name;

        @Bind(R.id.eventType)
        TextView eventType;

        public EventsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
