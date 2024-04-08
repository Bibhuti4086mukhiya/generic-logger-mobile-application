import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.np.genericlogger.Logger
import com.np.genericlogger.LoggerDatabaseHelper
import com.np.genericlogger.R
import com.np.genericlogger.UpdateLoggerActivity

class LoggerAdapter(private var loggers: List<Logger>, private val context: Context) :
    RecyclerView.Adapter<LoggerAdapter.LoggerViewHolder>() {
    private val db: LoggerDatabaseHelper = LoggerDatabaseHelper(context)

    inner class LoggerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
//        val updateButton: ImageView = itemView.findViewById(R.id.updateLoggerButton)
//        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
        private val popUpBtn: ImageButton = itemView.findViewById(R.id.editDeleteOptionButton)

        init {
            popUpBtn.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            showPopupMenu(v)
        }

        private fun showPopupMenu(view: View?) {
            val popupMenu = PopupMenu(context, view)
            popupMenu.inflate(R.menu.popup_menu_item) // assuming you have defined a menu XML file
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.editText -> {
                        // Handle edit action
                        val intent = Intent(context, UpdateLoggerActivity::class.java).apply {
                            putExtra("note_id", loggers[adapterPosition].id)
                        }
                        context.startActivity(intent)
                        true
                    }

                    R.id.delete -> {
                        // Handle delete action
                        db.deleteLogger(loggers[adapterPosition].id)
                        refreshData(db.getAllLogger())
                        Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoggerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.logger_list, parent, false)
        return LoggerViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoggerViewHolder, position: Int) {
        val logger = loggers[position]
        holder.titleTextView.text = logger.title

//        holder.updateButton.setOnClickListener {
//            val intent = Intent(context, UpdateLoggerActivity::class.java).apply {
//                putExtra("note_id", logger.id)
//            }
//            context.startActivity(intent)
//        }
//
//        holder.deleteButton.setOnClickListener {
//            db.deleteNotes(logger.id)
//            refreshData(db.getAllLogger())
//            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
//        }
    }

    fun refreshData(newLoggers: List<Logger>) {
        loggers = newLoggers
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = loggers.size
}