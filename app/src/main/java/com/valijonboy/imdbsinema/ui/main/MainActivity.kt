package com.valijonboy.imdbsinema.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import com.valijonboy.imdbsinema.R
import com.valijonboy.imdbsinema.data.api.ApiRepository
import com.valijonboy.imdbsinema.data.model.Movie
import com.valijonboy.imdbsinema.data.model.MovieResponse
import com.valijonboy.imdbsinema.databinding.ActivityMainBinding
import com.valijonboy.imdbsinema.databinding.BottomSheetBinding
import com.valijonboy.imdbsinema.di.Injector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return MainPresenter()
    }

    init {
        Injector.appComponent.inject(this)
    }

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setOnClickListener {
            showBottomSheet()
        }


    }

    private fun showBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet)

        val etTitle: TextInputEditText? = bottomSheetDialog.findViewById(R.id.et_title)
        val etPage: TextInputEditText? = bottomSheetDialog.findViewById(R.id.et_page_count)
        val button123: Button? = bottomSheetDialog.findViewById(R.id.button_apply)

        bottomSheetDialog.show()



        button123?.setOnClickListener {
            Log.d("TAG", "showBottomSheet: button clicked")
            val title: String? = etTitle?.text.toString()
            val page: Int? = etPage?.text.toString().toInt()
            Log.d("TAG", "showBottomSheet: title is: $title")
            Log.d("TAG", "showBottomSheet: page is: $page")
            if (title.isNullOrEmpty() || page == null) {
                Toast.makeText(this, "Please, enter all parametres!", Toast.LENGTH_SHORT).show()
            } else {
                presenter.getMovies(title = title, page = page)
            }

            bottomSheetDialog.dismiss()
        }
    }

    override fun showMovies(list: List<Movie>?) {
        val adapter = MainAdapter(list)
        Log.d("activity", "showMovies: list is: $list")
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    override fun openDetailScreen() {
        TODO("Not yet implemented")
    }

    override fun showErrorText(show: Boolean) {
        binding.errorText.isVisible = show
    }

    override fun showToast(message: String) {
        TODO("Not yet implemented")
    }

    override fun showToast(message: Int) {
        TODO("Not yet implemented")
    }

    override fun showDialog(message: String?) {
        TODO("Not yet implemented")
    }

    override fun setFavoriteButton(favorite: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showProgress(show: Boolean) {
        binding.progressCircular.isVisible = show
    }
}