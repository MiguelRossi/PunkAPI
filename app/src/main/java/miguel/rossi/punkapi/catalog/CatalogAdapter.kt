package miguel.rossi.punkapi.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import miguel.rossi.punkapi.R
import miguel.rossi.punkapi.databinding.CatalogItemBinding
import miguel.rossi.punkapi.domain.Beer
import miguel.rossi.punkapi.util.loadImageFitCenter

class CatalogAdapter(
    private val onClickListener: BeerOnClickListener
) : ListAdapter<Beer, RecyclerView.ViewHolder>(BeerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BeerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as BeerViewHolder
        holder.bind(onClickListener, getItem(position))
    }

}

private class BeerViewHolder(
    val binding: CatalogItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: BeerOnClickListener, beer: Beer) {
        binding.beer = beer
        binding.clickListener = clickListener
        binding.executePendingBindings()
        loadImageFitCenter(binding.itemBeerImage, beer.imageUrl, R.drawable.brewdog_logo)
    }

    companion object {
        fun from(parent: ViewGroup): BeerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CatalogItemBinding.inflate(layoutInflater, parent, false)
            return BeerViewHolder(binding)
        }
    }
}

class BeerDiffCallback : DiffUtil.ItemCallback<Beer>() {

    override fun areItemsTheSame(oldItem: Beer, newItem: Beer) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer) = oldItem == newItem

}

class BeerOnClickListener(val clickListener: (beer: Beer) -> Unit) {
    fun onClick(beer: Beer) = clickListener(beer)
}
