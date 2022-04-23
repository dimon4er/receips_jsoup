package to.boosty.cmit.parser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
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
        holder.title.text = receipts[position].title
        holder.desc.text = receipts[position].description
        holder.adInfo.text = receipts[position].additionalInfo
        Picasso.get()
            .load(receipts[position].linkImage)
            .into(holder.recImg)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, position, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return receipts.size
    }

    class MyViewClass: RecyclerView.ViewHolder {
        var title: TextView
        var desc: TextView
        var adInfo: TextView
        var recImg: ImageView


        constructor(itemView: View) : super(itemView) {
            this.title = itemView.findViewById(R.id.tvTitle)
            this.desc = itemView.findViewById(R.id.tvDesc)
            this.adInfo = itemView.findViewById(R.id.tvAInfo)
            this.recImg = itemView.findViewById(R.id.ivReceipt)
        }
    }
} 