package com.example.ktorfitapicall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.ktorfitapicall.data.Person
import com.example.ktorfitapicall.data.StarWarsApi
import com.example.ktorfitapicall.ui.theme.KtorfitApiCallTheme
import de.jensklingenberg.ktorfit.converter.builtin.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.builtin.FlowConverterFactory
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


val ktorfit = ktorfit {
    baseUrl(StarWarsApi.baseUrl)
   httpClient(HttpClient {
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true })
        }
    })
   converterFactories(FlowConverterFactory(), CallConverterFactory(),)
}
val api: StarWarsApi = ktorfit.create<StarWarsApi>()


class MainActivity : ComponentActivity() {

    private val peopleState = mutableStateOf<Person?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorfitApiCallTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    peopleState.value?.let {
                        Text(it.name ?: "")
                    }

                }
            }
        }

        lifecycleScope.launch {
            peopleState.value = api.getPerson(1)
        }
    }
}