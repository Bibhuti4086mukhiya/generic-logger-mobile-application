package com.np.genericlogger

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LoggerAdapter  ( private var loggers: List<Logger>,context: Context):
    RecyclerView.Adapter<LoggerAdapter.LoggerViewHolder>() {
    class LoggerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoggerViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.logger_list,parent,false)
        return LoggerViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoggerViewHolder, position: Int) {
        val logger=loggers[position]
        holder.titleTextView.text=logger.title
    }

    fun refreshData(newLoggers:List<Logger>){
        loggers = newLoggers
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = loggers.size
}