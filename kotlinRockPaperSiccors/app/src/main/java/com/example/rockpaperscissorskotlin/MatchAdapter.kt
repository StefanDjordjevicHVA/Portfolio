package com.example.rockpaperscissorskotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.match_layout.view.*

class MatchAdapter(private val matches: List<Match>) : RecyclerView.Adapter<MatchAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_layout, parent, false))
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchAdapter.ViewHolder, position: Int) = holder.bind(matches[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(match: Match)
        {
            itemView.tvWinner.text = match.winner
            itemView.tvDateMatch.text = match.date
            itemView.ivResultPlayer.setImageResource(match.playerResId)
            itemView.ivResultComp.setImageResource(match.compResId)
        }
    }
}