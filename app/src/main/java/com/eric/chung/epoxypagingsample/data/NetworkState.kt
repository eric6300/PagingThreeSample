package com.eric.chung.epoxypagingsample.data

sealed class NetworkState {
    object Success : NetworkState()
    object Loading : NetworkState()
    class Error(val error: Throwable) : NetworkState()
}