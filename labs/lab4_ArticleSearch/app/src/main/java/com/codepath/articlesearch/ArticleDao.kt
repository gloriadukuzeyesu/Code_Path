package com.codepath.articlesearch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.codepath.articlesearch.ArticleEntity as ArticleEntity1

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article_table")
    fun getAll(): Flow<List<ArticleEntity1>>

    @Insert
    fun insertAll(articles: List<ArticleEntity1>)

    @Query("DELETE FROM article_table")
    fun deleteAll()
}

