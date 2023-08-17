package com.santos.awesomemovieapp

import androidx.lifecycle.ViewModel

class MovieViewModel: ViewModel() {
    fun loadMoviedetails(): MovieDetails {
        return MovieDetails( "Nome do filme", "Mussum Ipsum, cacilds vidis litro abertis. Quem num gosta di mim que vai caçá sua turmis! Nullam volutpat risus nec leo commodo, ut interdum diam laoreet. Sed non consequat odio. Interagi no mé, cursus quis, vehicula ac nisi. Quem num gosta di mé, boa gentis num é.\n" +
                "\n" +
                "Copo furadis é disculpa de bebadis, arcu quam euismod magna. Delegadis gente finis, bibendum egestas augue arcu ut est. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Detraxit consequat et quo num tendi nada.",
        "2023")
    }
}