package to.boosty.cmit.parser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import to.boosty.cmit.parser.lists.Receipts

class ReceiptsAdapter: RecyclerView.Adapter<ReceiptsAdapter.MyViewClass> {

    var receipts :MutableList<Receipts>
    var context: Context

    constructor(receipts :MutableList<Receipts>, context: Context) {
        this.receipts = receipts
        this.context = context
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewClass {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_receipt, parent, false)
        return MyViewClass(view)
    }

    override fun onBindViewHolder(holder: MyViewClass, position: Int) {
        holder.name.text = receipts[position].title
        holder.date.text = receipts[position].description

        holder.itemView.setOnClickListener {
            Toast.makeText(context, position, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return receipts.size
    }

    class MyViewClass: RecyclerView.ViewHolder {
        var name: TextView
        var date: TextView
        constructor(itemView: View) : super(itemView) {
            this.name = itemView.findViewById(R.id.tvTitle)
            this.date = itemView.findViewById(R.id.tvName)
        }
    }
} 