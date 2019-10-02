package miguel.rossi.punkapi.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.catalog_fragment.view.*
import miguel.rossi.punkapi.R
import miguel.rossi.punkapi.databinding.CatalogFragmentBinding
import miguel.rossi.punkapi.repository.HangoverType.EASY_PEASY

private const val GRID_COLUMNS = 2

class CatalogFragment : Fragment() {

    private val viewModel by lazy { CatalogViewModel.get(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.beerPlease()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: CatalogFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.catalog_fragment, container, false)

        bindData(binding)
        setUpActionBar(binding)
        setUpHangovers()
        setUpBeerSelection()
        setUpAdapter(binding)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Avoid leaks
        val binding = DataBindingUtil.findBinding<ViewDataBinding>(view!!)
        binding?.let {
            binding.root.catalog_grid.adapter = null
        }
    }

    private fun setUpActionBar(binding: CatalogFragmentBinding) {
        setHasOptionsMenu(false)

        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.catalogToolbar)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    private fun bindData(binding: CatalogFragmentBinding) {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setUpHangovers() {
        viewModel.hangover.observe(this, Observer {
            if (it != null) {
                val length = if (it.type == EASY_PEASY) {
                    Snackbar.LENGTH_INDEFINITE
                } else {
                    Snackbar.LENGTH_LONG
                }

                Snackbar.make(view!!, it.message, length).apply {
                    if (it.type == EASY_PEASY) {
                        setAction(R.string.retry) { viewModel.beerPlease() }
                        setActionTextColor(ContextCompat.getColor(activity!!, R.color.colorPrimary))
                    }
                }.show()

                viewModel.hangoverShown()
            }
        })
    }

    private fun setUpBeerSelection() {
        viewModel.selectedBeer.observe(this, Observer {
            it?.let {
                val action = CatalogFragmentDirections.fromCatalogToBeerdetail(it)
                findNavController().navigate(action)
                viewModel.beerSelectionFinished()
            }
        })
    }

    private fun setUpAdapter(binding: CatalogFragmentBinding) {
        val adapter = CatalogAdapter(BeerOnClickListener { beer -> viewModel.beerSelected(beer) })
        binding.catalogGrid.adapter = adapter
        viewModel.catalog.observe(this, Observer {
            adapter.submitList(it.page)
        })

        val layoutManager = GridLayoutManager(activity, GRID_COLUMNS)
        binding.catalogGrid.layoutManager = layoutManager
        binding.catalogGrid.addOnScrollListener(object : OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleBeer = layoutManager.findLastVisibleItemPosition()
                val beerInCatalog = layoutManager.itemCount

                viewModel.catalogScrolled(lastVisibleBeer, beerInCatalog)
            }

        })
    }

}
