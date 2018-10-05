package com.bergamin.tdd.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bergamin.tdd.R;
import com.bergamin.tdd.model.Auction;

import java.util.List;

public class AuctionsListAdapter extends RecyclerView.Adapter<AuctionsListAdapter.ViewHolder> {

    private final List<Auction> auctions;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public AuctionsListAdapter(Context context, List<Auction> auctions) {
        this.context = context;
        this.auctions = auctions;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.auction_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getAuctionAt(position));
    }

    @Override
    public int getItemCount() {
        return auctions.size();
    }

    public Auction getAuctionAt(int position) {
        return this.auctions.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(Auction auction);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView description;
        private Auction auction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.auction_item_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(auction);
                }
            });
        }

        public void bind(Auction auction) {
            this.auction = auction;
            description.setText(auction.getDescription());
        }
    }
}
