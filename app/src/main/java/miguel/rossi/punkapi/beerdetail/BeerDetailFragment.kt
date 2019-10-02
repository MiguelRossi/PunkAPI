package miguel.rossi.punkapi.beerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import miguel.rossi.punkapi.R
import miguel.rossi.punkapi.databinding.BeerdetailFragmentBinding
import miguel.rossi.punkapi.util.loadImageFitCenter

class BeerDetailFragment : Fragment() {

    private val args: BeerDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: BeerdetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.beerdetail_fragment, container, false)

        bindData(binding)
        setUpActionBar(binding)
        setUpImages(binding)
        setUpFoodList(binding)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bindData(binding: BeerdetailFragmentBinding) {
        binding.beer = args.beer
    }

    private fun setUpActionBar(binding: BeerdetailFragmentBinding) {
        setHasOptionsMenu(true)

        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.beerToolbar)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val title = getString(R.string.beer_detail_title, args.beer.name, args.beer.abv)
        binding.beerToolbarLayout.title = title
        binding.beerToolbarLayout.setExpandedTitleColor(
            ContextCompat.getColor(activity, android.R.color.transparent)
        )
    }

    private fun setUpImages(binding: BeerdetailFragmentBinding) {
        loadImageFitCenter(binding.beerToolbarImage, args.beer.imageUrl, R.drawable.brewdog_logo)
        loadImageFitCenter(binding.beerBackground, args.beer.imageUrl, R.drawable.brewdog_logo)
    }

    private fun setUpFoodList(binding: BeerdetailFragmentBinding) {
        args.beer.foodPairing.forEach {
            val food = TextView(activity!!)
            food.text = it
            binding.beerFoodPairing.addView(food)
        }
    }

}
