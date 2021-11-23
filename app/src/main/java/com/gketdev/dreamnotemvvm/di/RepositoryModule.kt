package com.gketdev.dreamnotemvvm.di

import com.gketdev.dreamnotemvvm.repository.HomeRepository
import com.gketdev.dreamnotemvvm.source.LocalDataSource
import com.gketdev.dreamnotemvvm.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): HomeRepository {
        return HomeRepository(localDataSource, remoteDataSource)
    }

}