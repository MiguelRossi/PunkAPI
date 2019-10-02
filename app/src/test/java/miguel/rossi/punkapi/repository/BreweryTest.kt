package miguel.rossi.punkapi.repository

import android.net.ConnectivityManager
import com.google.common.truth.Truth.assertThat
import miguel.rossi.punkapi.repository.HangoverType.BRAIN_SURGERY
import miguel.rossi.punkapi.repository.HangoverType.EASY_PEASY
import miguel.rossi.punkapi.util.NetworkUtil
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class BreweryTest {

    @Mock
    private lateinit var mockConnectivityManager: ConnectivityManager

    @Test
    fun getHangover_whenNoInternet_andAnyError_thenEasyPeasyHangover() {
        val mockNetworkUtil = spy(NetworkUtil(mockConnectivityManager))
        doAnswer { false }.`when`(mockNetworkUtil).checkNetwork()

        assertThat(getHangover(Exception(), mockNetworkUtil))
            .isEqualTo(Hangover(EASY_PEASY, "Oops! We couldn't find the internet..."))

        val serverError = HttpException(
            Response.error<Exception>(400, "".toResponseBody("".toMediaTypeOrNull()))
        )
        assertThat(getHangover(serverError, mockNetworkUtil))
            .isEqualTo(Hangover(EASY_PEASY, "Oops! We couldn't find the internet..."))

        val httpException = HttpException(
            Response.error<Exception>(500, "".toResponseBody("".toMediaTypeOrNull()))
        )
        assertThat(getHangover(httpException, mockNetworkUtil))
            .isEqualTo(Hangover(EASY_PEASY, "Oops! We couldn't find the internet..."))
    }

    @Test
    fun getHangover_whenNoHttpException_thenFallingApartHangover() {
        val mockNetworkUtil = spy(NetworkUtil(mockConnectivityManager))
        doAnswer { true }.`when`(mockNetworkUtil).checkNetwork()

        assertThat(getHangover(Exception(), mockNetworkUtil))
            .isEqualTo(Hangover(BRAIN_SURGERY, "This brewery is falling apart..."))
    }

    @Test
    fun getHangover_whenServerError_thenApiClosed() {
        val mockNetworkUtil = spy(NetworkUtil(mockConnectivityManager))
        doAnswer { true }.`when`(mockNetworkUtil).checkNetwork()

        val firstServerError = HttpException(
            Response.error<Exception>(500, "".toResponseBody("".toMediaTypeOrNull()))
        )
        assertThat(getHangover(firstServerError, mockNetworkUtil))
            .isEqualTo(Hangover(BRAIN_SURGERY, "Punk API is closed, come back tomorrow"))

        val lastServerError = HttpException(
            Response.error<Exception>(599, "".toResponseBody("".toMediaTypeOrNull()))
        )
        assertThat(getHangover(lastServerError, mockNetworkUtil))
            .isEqualTo(Hangover(BRAIN_SURGERY, "Punk API is closed, come back tomorrow"))
    }

    @Test
    fun getHangover_whenClientError_thenTooDrunk() {
        val mockNetworkUtil = spy(NetworkUtil(mockConnectivityManager))
        doAnswer { true }.`when`(mockNetworkUtil).checkNetwork()

        val firstClientError = HttpException(
            Response.error<Exception>(400, "".toResponseBody("".toMediaTypeOrNull()))
        )
        assertThat(getHangover(firstClientError, mockNetworkUtil))
            .isEqualTo(Hangover(BRAIN_SURGERY, "You're too drunk, Punk API didn't get it"))

        val lastClientError = HttpException(
            Response.error<Exception>(499, "".toResponseBody("".toMediaTypeOrNull()))
        )
        assertThat(getHangover(lastClientError, mockNetworkUtil))
            .isEqualTo(Hangover(BRAIN_SURGERY, "You're too drunk, Punk API didn't get it"))
    }

}
