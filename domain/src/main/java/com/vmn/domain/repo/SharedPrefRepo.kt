package com.vmn.domain.repo

interface SharedPrefRepo {
    fun <T> saveData(key: String, value: T)
    fun <T> loadData(key: String, defaultValue: T): T
}