package com.example.crudapp.firebaseDB.di

import com.example.crudapp.firebaseDB.realtimeDB.repository.RealtimeRepository
import com.example.crudapp.firebaseDB.repository.RealtimeRepositoryimpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRealtimeRepository(
      repo: RealtimeRepositoryimpl
    ):RealtimeRepository

}