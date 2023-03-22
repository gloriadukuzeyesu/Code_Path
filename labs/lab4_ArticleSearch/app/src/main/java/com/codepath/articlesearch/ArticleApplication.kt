package com.codepath.articlesearch

import android.app.Application
//import com.codepath.articlesearch.AppDatabase.AppDatabase.Companion.getInstance

//import com.codepath.articlesearch.AppDatabase.AppDatabase.Companion.getInstance

class ArticleApplication : Application() {
//    val db by lazy { AppDatabase.getInstance(this) }
    val db by lazy { AppDatabase.getInstance(this)}
}
