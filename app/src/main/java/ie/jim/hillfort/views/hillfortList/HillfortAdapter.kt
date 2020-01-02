package ie.jim.hillfort.views.hillfortList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ie.jim.hillfort.R
import ie.jim.hillfort.helpers.readImageFromPath
import ie.jim.hillfort.models.HillfortModel
import kotlinx.android.synthetic.main.activity_hillfort.view.*

import kotlinx.android.synthetic.main.card_hillfort.view.favourite
import kotlinx.android.synthetic.main.card_hillfort.view.*
import kotlinx.android.synthetic.main.card_hillfort.view.description
import kotlinx.android.synthetic.main.card_hillfort.view.hillfortName
import kotlinx.android.synthetic.main.card_hillfort.view.ratingBar2


// introduce a listener interface to allow interaction.
interface HillfortListener {
    fun onHillfortClick(hillfort: HillfortModel)
}

class HillfortAdapter constructor(private var hillforts: List<HillfortModel>,
                                  private val listener: HillfortListener) :
    RecyclerView.Adapter<HillfortAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_hillfort,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val hillfort = hillforts[holder.adapterPosition]
        holder.bind(hillfort, listener)
    }

    override fun getItemCount(): Int = hillforts.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(hillfort: HillfortModel, listener: HillfortListener) {
            itemView.hillfortName.text = hillfort.title
            itemView.description.text = hillfort.description
// set the status of the favourite heart
            if (hillfort.favourite != false)
            {
                itemView.favourite.setImageResource(R.drawable.ic_favorite_black_24dp)
            } else {
                itemView.favourite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            }

// set the visible stars amount to amount saved in hillfortFireStore model
           itemView.ratingBar2.setRating(hillfort.rating)




            Glide.with(itemView.context).load(hillfort.image).into(itemView.imageIcon);

            itemView.setOnClickListener { listener.onHillfortClick(hillfort)}

            }
    }
}

