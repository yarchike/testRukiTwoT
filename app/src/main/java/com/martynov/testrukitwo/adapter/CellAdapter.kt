package com.martynov.testrukitwo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.martynov.testrukitwo.R
import com.martynov.testrukitwo.model.Cell
import com.martynov.testrukitwo.model.State

class CellAdapter(var listCell: ArrayList<Cell>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cellView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false)
        return CellViewHolder(this, cellView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cellIndex = position
        when (holder) {
            is CellViewHolder -> holder.bind(listCell[cellIndex])
        }
    }

    override fun getItemCount(): Int {
        return listCell.size
    }
    fun refreshCell(cell: ArrayList<Cell>) {
        this.listCell = cell
        notifyDataSetChanged()

    }

    class CellViewHolder(val adapter: CellAdapter, view: View) : RecyclerView.ViewHolder(view) {
        fun bind(cell: Cell) {
            val imageViewState = itemView.findViewById<ImageView>(R.id.imageViewState)
            val textViewState = itemView.findViewById<TextView>(R.id.textViewState)
            val textViewMessange = itemView.findViewById<TextView>(R.id.textViewMessange)
            with(itemView) {
                when (cell.state) {
                    State.LIVE -> {
                        loadImage(imageViewState, R.drawable.live)
                        textViewState.text = context.getString(R.string.live)
                        textViewMessange.text = context.getString(R.string.and_moves)
                    }
                    State.DEAD -> {
                        loadImage(imageViewState, R.drawable.dead)
                        textViewState.text = context.getString(R.string.dead)
                        textViewMessange.text = context.getString(R.string.or_pretending_to_be)

                    }
                    State.LIFE -> {
                        loadImage(imageViewState, R.drawable.life)
                        textViewState.text = context.getString(R.string.life)
                        textViewMessange.text = context.getString(R.string.ku_ku)
                    }

                }
            }

        }

        private fun loadImage(photoImg: ImageView, id: Int) {
            Glide.with(photoImg.context)
                .load(id)
                .into(photoImg)
        }

    }
}