package com.example.rusalfood.di

import com.example.rusalfood.data.repositories.LoginRepositoryImpl
import com.example.rusalfood.data.repositories.MainRepositoryImpl
import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.usecases.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UseCasesModule {
    @Singleton
    @Binds
    fun bindDisplayAllPlacesUseCaseImplToDisplayAllPlacesUseCase(displayAllPlacesUseCaseImpl: DisplayAllPlacesUseCaseImpl) : DisplayAllPlacesUseCase

    @Singleton
    @Binds
    fun bindGetIntoPlaceUseCaseImplToGetIntoPlaceUseCase(getIntoPlaceUseCaseImpl: GetIntoPlaceUseCaseImpl) : GetIntoPlaceUseCase

    @Singleton
    @Binds
    fun bindSignInUseCaseImplToSignInUseCase(signInUseCaseImpl: SignInUseCaseImpl) : SignInUseCase

    @Singleton
    @Binds
    fun bindSignUpUseCaseImplToSignUpUseCase(signUpUseCaseImpl: SignUpUseCaseImpl) : SignUpUseCase

    @Singleton
    @Binds
    fun bindGetFoodListUseCaseImplToGetFoodListUseCase(etFoodListUseCaseImpl: GetFoodListUseCaseImpl) : GetFoodListUseCase

}