package com.minhbka.exampleforecastmvvm.data.provider

import com.minhbka.exampleforecastmvvm.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem():UnitSystem
}