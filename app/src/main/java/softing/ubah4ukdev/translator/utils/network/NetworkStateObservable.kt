package softing.ubah4ukdev.translator.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable
import io.reactivex.disposables.Disposable

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.utils.network
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.09.25
 *
 *   v1.0
 */
class NetworkStateObservable(
    private val context: Context
) : Observable<NetworkState>() {

    override fun subscribeActual(observer: Observer<in NetworkState>) {
        val listener = NetworkStateListener(context, observer)
        observer.onSubscribe(listener)
    }

    class NetworkStateListener(
        private val context: Context,
        private val observer: Observer<in NetworkState>
    ) : ConnectivityManager.NetworkCallback(), Disposable {

        private val disposable = object : MainThreadDisposable() {
            override fun onDispose() {
                connectivityManager.unregisterNetworkCallback(this@NetworkStateListener)
            }
        }

        private val connectivityManager: ConnectivityManager by lazy {
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }

        init {
            connectivityManager
                .registerNetworkCallback(NetworkRequest.Builder().build(), this)
        }

        override fun onAvailable(network: Network) {
            observer.onNext(NetworkState.CONNECTED)
        }

        override fun onUnavailable() {
            observer.onNext(NetworkState.DISCONNECTED)
        }

        override fun onLost(network: Network) {
            observer.onNext(NetworkState.DISCONNECTED)
        }

        override fun dispose() = disposable.dispose()

        override fun isDisposed(): Boolean = disposable.isDisposed
    }
}