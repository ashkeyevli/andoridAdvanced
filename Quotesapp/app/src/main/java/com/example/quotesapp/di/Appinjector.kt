package com.example.quotesapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.quotesapp.data.api.ApiClient
import com.example.quotesapp.data.repository.LoginDataStore
import com.example.quotesapp.data.repository.PostQuoteDataStore
import com.example.quotesapp.data.repository.QuoteListDataStore
import com.example.quotesapp.data.repository.UserProfileDataStore
import com.example.quotesapp.domain.*
import com.example.quotesapp.viewModel.PostQuoteViewModel
import com.example.quotesapp.viewModel.QuotesListViewModel
import com.example.quotesapp.viewModel.SignInViewModel
import com.example.quotesapp.viewModel.UserProfileViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModeModule = module{
    viewModel { QuotesListViewModel(get( ))

    }
    viewModel { UserProfileViewModel(get( ))

    }
    viewModel { PostQuoteViewModel(get( ))

    }


}

val SignInViewModeModule = module{
    viewModel {SignInViewModel(get())
    }
}

val useCaseModule = module{

    single { GetQuoteListUseCase(get<QuoteListDataStore>())

    }
    single { GetUserProfileUseCase(get<UserProfileDataStore>())

    }
    single { PostQuoteUseCase(get<PostQuoteDataStore>())

    }

}

val SignInUseCaseModule = module{
    single {
        LoginUseCase(get<LoginDataStore>())
    }
    }



val repositoryModule = module{
    single { QuoteListDataStore(get())
        }
    single { UserProfileDataStore(get())
    }
    single { PostQuoteDataStore(get())
    }
    }




val SignInRepositoryModule = module {
    single {
        LoginDataStore(get())

    }
}

val networkModule = module {
    single { ApiClient.create(okHttpClient = get()) }
    single { ApiClient.getOkHttpClient(authInterceptor = get()) }
    single { ApiClient.getAuthInterceptor(sharedPreferences = get())}
}

val sharedPrefModule = module {
    single {
        androidApplication().getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    single<SharedPreferences.Editor> {
        androidApplication().getSharedPreferences("default", Context.MODE_PRIVATE)
            .edit()
    }
}