package com.themealdb.blsa.utils


import com.themealdb.blsa.utils.Status.SUCCESS
import com.themealdb.blsa.utils.Status.LOADING;
import com.themealdb.blsa.utils.Status.ERROR;

data class Resource<out T>(val status: Status, val data: T?, val message: String?,val responseCode: Int?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = SUCCESS, data = data, message = null,responseCode = null)

        fun <T> error(data: T?, message: String,responseCode:Int?=null): Resource<T> =
            Resource(status = ERROR, data = data, message = message,responseCode=responseCode)

        fun <T> loading(): Resource<T> = Resource(status = LOADING, data = null, message = null,responseCode = null)
    }
}
