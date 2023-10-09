package com.example.ultra11.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultra11.R;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private List<YourMatchData> matchList;

    public MatchAdapter(List<YourMatchData> matchList) {
        this.matchList = matchList;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card_layout, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        YourMatchData match = matchList.get(position);

        // Bind match data to the individual views within the card
        holder.matchTitleTextView.setText(match.getMatchTitle());
        holder.dateTextView.setText(match.getDate());
       holder.contest_prize.setText(match.getContest_prize());
        holder.team1ImageView.setImageResource(match.getTeam1ImageResource());
        holder.team2ImageView.setImageResource(match.getTeam2ImageResource());
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView matchTitleTextView;
        TextView dateTextView;
       TextView timeTextView;
        ImageView team1ImageView;
        ImageView team2ImageView;

        TextView contest_prize;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            matchTitleTextView = itemView.findViewById(R.id.league_name);
            team1ImageView = itemView.findViewById(R.id.team1_image);
            dateTextView = itemView.findViewById(R.id.versus_text);
           timeTextView = itemView.findViewById(R.id.versus_text1);

            team2ImageView = itemView.findViewById(R.id.team2_image);

            contest_prize =  itemView.findViewById(R.id.contest_prize);

            // Initialize other views within the card if needed
        }
    }
}
