package com.np.genericlogger

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LoggerAdapter  ( private var loggers: List<Logger>,context: Context):
    RecyclerView.Adapter<LoggerAdapter.LoggerViewHolder>() {
    class LoggerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateLoggerButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoggerViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.logger_list,parent,false)
        return LoggerViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoggerViewHolder, position: Int) {
        val logger=loggers[position]
        holder.titleTextView.text=logger.title

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context,UpdateLoggerActivity::class.java).apply {
                putExtra("note_id",logger.id)
            }
            holder.itemView.context.startActivities(arrayOf(intent))
        }
    }

    fun refreshData(newLoggers:List<Logger>){
        loggers = newLoggers
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = loggers.size
}